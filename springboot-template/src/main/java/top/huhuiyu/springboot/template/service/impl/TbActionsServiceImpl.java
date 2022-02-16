package top.huhuiyu.springboot.template.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import top.huhuiyu.springboot.template.dao.TbActionsDAO;
import top.huhuiyu.springboot.template.entity.TbActions;
import top.huhuiyu.springboot.template.service.TbActionsService;

/**
 * 接口信息服务实现
 * 
 * @author DarkKnight
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TbActionsServiceImpl implements TbActionsService {
  @Autowired
  private TbActionsDAO tbActionsDAO;

  @Override
  public TbActions queryByUrl(TbActions tbActions) throws Exception {
    QueryWrapper<TbActions> wrapper = new QueryWrapper<TbActions>();
    wrapper.eq("url", tbActions.getUrl());
    return tbActionsDAO.selectOne(wrapper);
  }
}
