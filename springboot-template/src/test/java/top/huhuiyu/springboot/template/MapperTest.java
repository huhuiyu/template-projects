package top.huhuiyu.springboot.template;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import top.huhuiyu.springboot.template.dao.TbAdminDAO;
import top.huhuiyu.springboot.template.entity.TbAdmin;

/**
 * mapper功能测试
 * 
 * @author DarkKnight
 *
 */
@SpringBootTest
public class MapperTest {

  private static final Logger log = LoggerFactory.getLogger(MapperTest.class);

  @Autowired
  private TbAdminDAO tbAdminDAO;

  @Test
  public void testQueryAdmin() throws Exception {
    Page<TbAdmin> page = new Page<TbAdmin>(2, 10);
    TbAdmin admin = new TbAdmin();
    IPage<TbAdmin> list = tbAdminDAO.queryAll(page, admin);
    log.debug("分页信息：{},{},{}", list.getTotal(), list.getPages(), list.getCurrent());
    log.debug("分页数据：{}", list.getRecords());

    admin.setAccessKey("c89bceb4-161f-4ea7-803a-fcf78803686e");
    page.setCurrent(10);
    list = tbAdminDAO.queryAll(page, admin);
    log.debug("分页信息：{},{},{}", list.getTotal(), list.getPages(), list.getCurrent());
    log.debug("分页数据：{}", list.getRecords());

  }
}
