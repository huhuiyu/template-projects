package top.huhuiyu.springboot.template.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import top.huhuiyu.springboot.template.entity.TbAdmin;

/**
 * tb_admin表的dao
 * 
 * @author DarkKnight
 */
@Mapper
public interface TbAdminDAO extends BaseMapper<TbAdmin> {

  /**
   * 查询用户信息
   * 
   * @param page    分页信息
   * @param tbAdmin 查询条件
   * 
   * @return 用户信息列表
   * 
   * @throws Exception
   */
  IPage<TbAdmin> queryAll(IPage<TbAdmin> page, @Param("tbAdmin") TbAdmin tbAdmin) throws Exception;

}