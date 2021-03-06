package top.huhuiyu.springboot.template.service;

import top.huhuiyu.springboot.template.entity.PageBean;
import top.huhuiyu.springboot.template.entity.TbUser;
import top.huhuiyu.springboot.template.message.TbUserManageMessage;
import top.huhuiyu.springboot.template.message.TbUserMessage;

/**
 * 用户信息服务
 * 
 * @author DarkKnight
 *
 */
public interface TbUserService {

  /**
   * 用户登录
   * 
   * @param user 用登录信息
   * 
   * @return 用户登录结果
   * 
   * @throws Exception 处理发生异常
   */
  TbUserMessage login(TbUser user) throws Exception;

  /**
   * 用户登出
   * 
   * @return 用户登出结果
   * 
   * @throws Exception 处理发生异常
   */
  TbUserMessage logout() throws Exception;

  /**
   * 获取登录用户信息
   * 
   * @return 登录用户信息
   * 
   * @throws Exception 处理发生异常
   */
  TbUserMessage getUserInfo() throws Exception;

  /**
   * 查询用户信息列表
   * 
   * @param pageBean 分页信息
   * @param user     过滤条件
   * @param orderBy  排序条件
   * 
   * @return 用户信息列表
   * 
   * @throws Exception 处理发生异常
   */
  TbUserManageMessage query(PageBean pageBean, TbUser user, Integer orderBy) throws Exception;

  /**
   * 修改用户启用状态
   * 
   * @param user   用户信息
   * @param enable 是否启用
   * 
   * @return 修改用户启用状态的结果
   * 
   * @throws Exception 处理发生异常
   */
  TbUserMessage updateEnable(TbUser user, boolean enable) throws Exception;

  /**
   * 用户注册
   * 
   * @param user 用户信息
   * 
   * @return 用户注册的结果
   * 
   * @throws Exception 处理发生异常
   */
  TbUserMessage reg(TbUser user) throws Exception;

  /**
   * 修改密码
   * 
   * @param user 用户信息
   * 
   * @return 修改密码的结果
   * 
   * @throws Exception 处理发生异常
   */
  TbUserMessage updatePwd(TbUser user) throws Exception;

}
