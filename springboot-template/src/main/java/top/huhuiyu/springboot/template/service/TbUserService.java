package top.huhuiyu.springboot.template.service;

import top.huhuiyu.springboot.template.entity.TbUser;
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

}
