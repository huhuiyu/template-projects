package top.huhuiyu.springboot.template;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import top.huhuiyu.springboot.template.utils.SystemConstants;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OtherTest {
  private static final Logger log = LoggerFactory.getLogger(MainTest.class);

  @Test
  public void rand() throws Exception {
    log.debug(SystemConstants.randomString(5));
  }
}
