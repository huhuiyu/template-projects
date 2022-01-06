package top.huhuiyu.springboot.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import top.huhuiyu.springboot.template.base.BaseResult;

/**
 * 主测试
 * 
 * @author DarkKnight
 *
 */
@SpringBootTest
public class MainTest {

  private static final Logger log = LoggerFactory.getLogger(MainTest.class);
  @Autowired
  private StringEncryptor encryptor;

  @Test
  public void lombok() throws Exception {
    BaseResult result1 = new BaseResult(200, "hello", true, "lombok");
    BaseResult result2 = new BaseResult();
    System.out.println(String.format("%s%n%s", result1, result2));
  }

  @Test
  public void res() throws Exception {
    Scanner scanner = new Scanner(MainTest.class.getResourceAsStream("/test.txt"));
    String info = scanner.next();
    assertEquals("一个资源测试文件", info);
  }

  @Test
  public void encrypt() throws Exception {
    log.debug(encryptor.encrypt("user-pwd"));
  }

  @Test
  public void decrypt() throws Exception {
    log.debug(encryptor.decrypt("g86U4xgLKixpsWxWguiOWI3iQfp2qk12dGSsXjTKoQvwzb59pPeflwRhhIWwAD6L"));
  }

}
