package top.huhuiyu.springboot.template.service;

import top.huhuiyu.springboot.template.entity.RedisTokenInfo;
import top.huhuiyu.springboot.template.entity.TbAdmin;

/**
 * redis服务
 * 
 * @author DarkKnight
 *
 */
public interface RedisService {

  /**
   * 保存ip信息
   * 
   * @param token token信息
   * @param ip    ip信息
   * 
   * @return 完整的token信息
   * 
   * @throws Exception 处理发生异常
   */
  RedisTokenInfo saveIp(String token, String ip) throws Exception;

  /**
   * 校验token信息，不存在就返回一个新的，否则就返回原值
   * 
   * @param token 要校验的token信息
   * 
   * @return token信息
   * 
   * @throws Exception 处理发生异常
   */
  String checkToken(String token) throws Exception;

  /**
   * 保存用户信息
   * 
   * @param token   token信息
   * @param tbAdmin 用户信息
   * 
   * @return 完整的token信息
   * 
   * @throws Exception
   */
  RedisTokenInfo saveUser(String token, TbAdmin tbAdmin) throws Exception;

  /**
   * 读取token信息
   * 
   * @param token
   * 
   * @return 
   * 
   * @throws Exception
   */
  RedisTokenInfo readTokenInfo(String token) throws Exception;

}
