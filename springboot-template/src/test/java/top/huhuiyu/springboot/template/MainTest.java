package top.huhuiyu.springboot.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import top.huhuiyu.springboot.template.controller.TestController;

@WebMvcTest(TestController.class)
public class MainTest {
  @Autowired
  private MockMvc mvc;

  @Test
  public void res() throws Exception {
    Scanner scanner = new Scanner(MainTest.class.getResourceAsStream("/test.txt"));
    String info = scanner.next();
    assertEquals("一个资源测试文件", info);
  }

  @Test
  public void index() throws Exception {
    final String param = "abc" + new Random().nextInt();
    ResultActions action = this.mvc.perform(get("/test/index").param("test", param));
    MvcResult result = action.andReturn();
    assertEquals(param, result.getResponse().getContentAsString());
  }
}
