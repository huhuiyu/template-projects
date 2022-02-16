package top.huhuiyu.springboot.template.service;

import top.huhuiyu.springboot.template.entity.TbActions;

/**
 * 接口信息服务
 * 
 * @author DarkKnight
 *
 */
public interface TbActionsService {

  /**
   * 通过url查询接口信息
   * 
   * @param tbActions url信息
   * 
   * @return url对应的接口信息
   * 
   * @throws Exception 查询发生错误
   */
  TbActions queryByUrl(TbActions tbActions) throws Exception;

}
