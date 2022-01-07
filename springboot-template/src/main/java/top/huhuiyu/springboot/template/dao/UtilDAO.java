package top.huhuiyu.springboot.template.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

/**
 * 工具dao
 * 
 * @author DarkKnight
 *
 */
@Mapper
public interface UtilDAO {
  /**
   * 获取数据库当前时间
   * 
   * @return 数据库当前时间
   * 
   * @throws Exception
   */
  Date queryTime() throws Exception;

  /**
   * 获取数据库当前时间戳
   * 
   * @return 数据库当前时间戳
   * 
   * @throws Exception
   */
  Long queryTimestamp() throws Exception;
}
