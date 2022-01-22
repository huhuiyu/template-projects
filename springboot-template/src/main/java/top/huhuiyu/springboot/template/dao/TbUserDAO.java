package top.huhuiyu.springboot.template.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import top.huhuiyu.springboot.template.entity.TbUser;

/**
 * tb_user表的dao
 * 
 * @author DarkKnight
 */
@Mapper
public interface TbUserDAO extends BaseMapper<TbUser> {

  /**
   * 查询用户列表
   * 
   * @param page   分页参数
   * @param tbUser 查询参数
   * 
   * @return 用户列表
   * 
   * @throws Exception 查询发生异常
   */
  IPage<TbUser> queryAll(IPage<TbUser> page, @Param("tbUser") TbUser tbUser) throws Exception;

}