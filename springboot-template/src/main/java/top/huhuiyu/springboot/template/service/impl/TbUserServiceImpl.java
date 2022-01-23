package top.huhuiyu.springboot.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import top.huhuiyu.springboot.template.dao.TbUserDAO;
import top.huhuiyu.springboot.template.entity.AuthInfo;
import top.huhuiyu.springboot.template.entity.TbUser;
import top.huhuiyu.springboot.template.message.TbUserMessage;
import top.huhuiyu.springboot.template.service.RedisService;
import top.huhuiyu.springboot.template.service.TbUserService;
import top.huhuiyu.springboot.template.utils.ApplicationUtil;

/**
 * 用户信息服务实现
 * 
 * @author DarkKnight
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TbUserServiceImpl implements TbUserService {

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
    TbUserMessage message = new TbUserMessage();
    // 按照用户名查询
    QueryWrapper<TbUser> wrapper = new QueryWrapper<TbUser>();
    wrapper.eq("username", user.getUsername());
    TbUser check = tbUserDAO.selectOne(wrapper);
    if (check == null) {
      message.setFailInfo("用户不存在");
      return message;
    }
    // 校验密码
    String pwd = DigestUtils.md5DigestAsHex((user.getPassword() + check.getSalt()).getBytes());
    if (!check.getPassword().equals(pwd)) {
      message.setFailInfo("密码不正确");
      return message;
    }
    // 保存用户登录信息到redis
    processUserInfo(check);
    AuthInfo authInfo = ApplicationUtil.getBean(AuthInfo.class);
    authInfo.setLoginUser(check);
    redisService.saveUser(authInfo.getToken(), check);
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
    message.setMessage("");
    message.setSuccess(authInfo.getLoginUser() != null);
    return message;
  }

}
