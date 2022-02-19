package top.huhuiyu.springboot.template.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import top.huhuiyu.springboot.template.dao.TbUserDAO;
import top.huhuiyu.springboot.template.entity.AuthInfo;
import top.huhuiyu.springboot.template.entity.PageBean;
import top.huhuiyu.springboot.template.entity.SystemConfig;
import top.huhuiyu.springboot.template.entity.TbUser;
import top.huhuiyu.springboot.template.message.TbUserManageMessage;
import top.huhuiyu.springboot.template.message.TbUserMessage;
import top.huhuiyu.springboot.template.service.RedisService;
import top.huhuiyu.springboot.template.service.TbUserService;
import top.huhuiyu.springboot.template.utils.ApplicationUtil;
import top.huhuiyu.springboot.template.utils.SystemConstants;

/**
 * 用户信息服务实现
 * 
 * @author DarkKnight
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TbUserServiceImpl implements TbUserService {

  private static final Logger log = LoggerFactory.getLogger(TbUserServiceImpl.class);

  @Autowired
  private TbUserDAO tbUserDAO;
  @Autowired
  private RedisService redisService;

  /**
   * 处理用户信息，不要回传敏感信息
   * 
   * @param user 要处理的用户信息
   */
  private void processUserInfo(TbUser user) {
    if (user == null) {
      return;
    }
    user.setAid(null);
    user.setPassword(null);
    user.setEnable(null);
    user.setSalt(null);
  }

  @Override
  public TbUserMessage login(TbUser user) throws Exception {
    SystemConfig systemConfig = redisService.readSystemConfig();
    TbUserMessage message = new TbUserMessage();
    TbUser check = queryByName(user);
    if (check == null) {
      message.setFailInfo("用户不存在");
      return message;
    }
    // 用户错误次数限制
    String userkey = String.format(SystemConstants.PASSWORD_ERROR_KEY, check.getAid());
    Integer userCount = redisService.loadInfo(userkey, Integer.class);
    userCount = userCount == null ? 0 : userCount;
    if (userCount >= systemConfig.getLoginPasswordErrorLimit()) {
      message.setFailInfo(SystemConstants.PASSWORD_ERROR_INFO);
      return message;
    }
    // 校验启用状态
    if (!SystemConstants.ENABLE.equalsIgnoreCase(check.getEnable())) {
      message.setFailInfo("用户已经被禁用");
      return message;
    }
    // 校验密码
    String pwd = DigestUtils.md5DigestAsHex((user.getPassword() + check.getSalt()).getBytes());
    if (!check.getPassword().equals(pwd)) {
      // 记录错误次数
      redisService.saveInfo(userkey, userCount + 1, systemConfig.getLoginPasswordErrorTimeout());
      message.setFailInfo("密码不正确");
      return message;
    }
    // 保存用户登录信息到redis
    AuthInfo authInfo = ApplicationUtil.getBean(AuthInfo.class);
    authInfo.setLoginUser(check);
    redisService.saveUser(authInfo.getToken(), check);
    processUserInfo(check);
    // 返回用户信息
    message.setSuccessInfo("登录成功");
    message.setTbUser(check);
    return message;
  }

  @Override
  public TbUserMessage logout() throws Exception {
    TbUserMessage message = new TbUserMessage();
    AuthInfo authInfo = ApplicationUtil.getBean(AuthInfo.class);
    redisService.removeUser(authInfo.getToken());
    message.setSuccessInfo("安全退出成功");
    return message;
  }

  @Override
  public TbUserMessage getUserInfo() throws Exception {
    TbUserMessage message = new TbUserMessage();
    AuthInfo authInfo = ApplicationUtil.getBean(AuthInfo.class);
    message.setTbUser(authInfo.getLoginUser());
    processUserInfo(message.getTbUser());
    message.setMessage("");
    message.setSuccess(authInfo.getLoginUser() != null);
    return message;
  }

  @Override
  public TbUserManageMessage query(PageBean pageBean, TbUser user, Integer orderBy) throws Exception {
    // 处理分页信息
    if (pageBean == null) {
      pageBean = new PageBean();
    }
    IPage<TbUser> page = new Page<TbUser>();
    pageBean.toIPage(page);
    // 处理查询参数
    if (user == null) {
      user = new TbUser();
    }
    QueryWrapper<TbUser> wrapper = new QueryWrapper<TbUser>();
    if (StringUtils.hasText(user.getUsername())) {
      wrapper.like("username", String.format(SystemConstants.LIKE_INFO, user.getUsername().trim()));
    }
    if (StringUtils.hasText(user.getNickname())) {
      wrapper.like("nickname", String.format(SystemConstants.LIKE_INFO, user.getNickname().trim()));
    }
    if (StringUtils.hasText(user.getEnable())) {
      wrapper.eq("enable", user.getEnable().trim());
    }
    // 排序
    switch (orderBy) {
      case 1:
        wrapper.orderByDesc("aid");
        break;
      case 2:
        wrapper.orderByAsc("aid");
        break;
      case 3:
        wrapper.orderByAsc("username");
        break;
      case 4:
        wrapper.orderByDesc("username");
        break;
      case 5:
        wrapper.orderByAsc("enable");
        break;
      default:
        break;
    }
    // 分页查询
    page = tbUserDAO.selectPage(page, wrapper);
    // 应答消息
    TbUserManageMessage message = new TbUserManageMessage();
    message.setPage(pageBean.fromIPage(page));
    message.setList(page.getRecords());
    message.setSuccessInfo("");
    return message;
  }

  @Override
  public TbUserMessage updateEnable(TbUser user, boolean enable) throws Exception {
    // 应答消息
    TbUserMessage message = new TbUserMessage();
    user = tbUserDAO.selectById(user.getAid());
    if (user == null) {
      message.setFailInfo("用户不存在");
      return message;
    }
    user.setEnable(enable ? SystemConstants.ENABLE : SystemConstants.DISABLE);
    int result = tbUserDAO.updateById(user);
    if (result == 1) {
      message.setSuccessInfo("修改用户启用状态成功");
    } else {
      message.setFailInfo("修改用户启用状态失败");
    }

    return message;
  }

  @Override
  public TbUserMessage reg(TbUser user) throws Exception {
    TbUserMessage message = new TbUserMessage();
    // 校验用户是否存在
    TbUser check = queryByName(user);
    if (check != null) {
      message.setFailInfo("用户名已经被占用");
      return message;
    }
    // 密码加盐处理
    user.setSalt(SystemConstants.randomString(SystemConstants.SALT_LENGTH));
    String pwd = DigestUtils.md5DigestAsHex((user.getPassword() + user.getSalt()).getBytes());
    user.setPassword(pwd);
    // 启用状态,开发key,角色
    user.setEnable(SystemConstants.ENABLE);
    user.setRole(SystemConstants.ROLE_USER);
    user.setAccessKey(UUID.randomUUID().toString());
    // 保存数据
    int result = tbUserDAO.insert(user);
    if (result == 1) {
      log.debug("注册用户信息：{}", user);
      message.setSuccessInfo("用户注册成功");
    } else {
      message.setFailInfo("用户注册失败");
    }
    return message;
  }

  @Override
  public TbUserMessage updatePwd(TbUser user) throws Exception {
    // 应答消息
    TbUserMessage message = new TbUserMessage();
    // 修改密码
    TbUser loginUser = ApplicationUtil.getBean(AuthInfo.class).getLoginUser();
    String pwd = DigestUtils.md5DigestAsHex((user.getPassword() + loginUser.getSalt()).getBytes());
    loginUser.setPassword(pwd);
    // 保存数据
    int result = tbUserDAO.updateById(loginUser);
    if (result == 1) {
      message.setSuccessInfo("修改密码成功");
    } else {
      message.setFailInfo("修改密码失败");
    }
    return message;
  }

  /**
   * 按照用户名查询用户信息
   * 
   * @param user 查询参数
   * 
   * @return 用户名对应用户信息
   */
  private TbUser queryByName(TbUser user) {
    // 按照用户名查询
    QueryWrapper<TbUser> wrapper = new QueryWrapper<TbUser>();
    wrapper.eq("username", user.getUsername());
    TbUser check = tbUserDAO.selectOne(wrapper);
    return check;
  }

}
