package top.huhuiyu.springboot.template;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import top.huhuiyu.springboot.template.dao.TbUserDAO;
import top.huhuiyu.springboot.template.entity.TbUser;

/**
 * mapper功能测试
 * 
 * @author DarkKnight
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapperTest {

  private static final Logger log = LoggerFactory.getLogger(MapperTest.class);

  @Autowired
  private TbUserDAO tbUserDAO;

  @Test
  public void testQueryUser() throws Exception {
    Page<TbUser> page = new Page<TbUser>(1, 2);
    TbUser user = new TbUser();
    IPage<TbUser> list = tbUserDAO.queryAll(page, user);
    log.debug("分页信息：{},{},{}", list.getTotal(), list.getPages(), list.getCurrent());
    showUserList(list.getRecords(), "分页数据");
    page.setSize(1000);
    user.setUsername(String.format("%%%s%%", "ad"));
    list = tbUserDAO.queryAll(page, user);
    log.debug("分页信息：{},{},{}", list.getTotal(), list.getPages(), list.getCurrent());
    showUserList(list.getRecords(), "条件查询");
  }

  private void showUserList(List<TbUser> list, String info) {
    log.debug(info);
    for (TbUser tbUser : list) {
      log.debug("{}", tbUser);
    }
  }

  @Test
  public void testUserInfo() throws Exception {
    TbUser user = new TbUser();
    user.setAccessKey(UUID.randomUUID().toString());
    user.setNickname("添加测试");
    user.setPassword("dddd");
    user.setRole("user");
    user.setSalt("abc123");
    user.setUsername("user" + new Random().nextInt());
    log.debug("添加前用户信息：{}", user);
    tbUserDAO.insert(user);
    log.debug("添加后用户信息：{}", user);
    TbUser check = tbUserDAO.selectById(user.getAid());
    log.debug("主键查询用户信息：{}", check);
    check.setNickname("修改昵称" + new Random().nextInt());
    tbUserDAO.updateById(check);
    check = tbUserDAO.selectById(user.getAid());
    log.debug("主键修改后的用户信息：{}", check);
    int result = tbUserDAO.deleteById(check);
    log.debug("主键删除用户信息的结果：{}", result);
    QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
    wrapper.eq("username", "admin");
    check = tbUserDAO.selectOne(wrapper);
    log.debug("单值查询结果：{}", check);
  }

}
