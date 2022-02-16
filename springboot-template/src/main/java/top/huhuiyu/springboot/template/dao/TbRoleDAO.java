package top.huhuiyu.springboot.template.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.huhuiyu.springboot.template.entity.TbRole;

/**
 * tb_role表的dao
 * 
 * @author DarkKnight
 */
@Mapper
public interface TbRoleDAO extends BaseMapper<TbRole> {

}