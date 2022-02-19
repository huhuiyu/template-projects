package top.huhuiyu.springboot.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import top.huhuiyu.springboot.template.base.BaseResult;
import top.huhuiyu.springboot.template.utils.SystemConstants;

/**
 * 主测试
 * 
 * @author DarkKnight
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainTest {

  private static final Logger log = LoggerFactory.getLogger(MainTest.class);
  @Autowired
  private StringEncryptor encryptor;

  @Test
  public void lombok() throws Exception {
    BaseResult result1 = new BaseResult(200, "hello", true, "lombok");
    BaseResult result2 = new BaseResult();
    log.debug(String.format("%s%n%s", result1, result2));
  }

  @Test
  public void res() throws Exception {
    Scanner scanner = new Scanner(MainTest.class.getResourceAsStream("/test.txt"));
    String info = scanner.next();
    assertEquals("一个资源测试文件", info);
  }

  @Test
  public void md5() throws Exception {
    log.debug("随机字符串======>{}", SystemConstants.randomString(5));
   
    log.debug("md5加密{}的结果:{}", "admin", DigestUtils.md5DigestAsHex("admin".getBytes()));
    log.debug("md5加salt的结果:{}", DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex("admin".getBytes()) + "WF5QS7").getBytes()));
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
