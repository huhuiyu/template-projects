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
   * 
   * @return 用户信息列表
   * 
   * @throws Exception 处理发生异常
   */
  TbUserManageMessage query(PageBean pageBean, TbUser user) throws Exception;

}
