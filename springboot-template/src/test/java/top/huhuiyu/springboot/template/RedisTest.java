package top.huhuiyu.springboot.template;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import top.huhuiyu.springboot.template.entity.RedisTokenInfo;
import top.huhuiyu.springboot.template.entity.TbUser;
import top.huhuiyu.springboot.template.service.RedisService;
import top.huhuiyu.springboot.template.utils.IpUtil;

/**
 * redis测试
 * 
 * @author DarkKnight
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {

  private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  @Autowired
  private RedisService redisService;

  @Test
  public void test() {
    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
    operations.set("test-mailinfo-write", "最简单的保存字符数据");
  }

  @Test
  public void testService() throws Exception {
    log.debug("系统配置：{}", redisService.readSystemConfig());
    String token = redisService.checkToken("f154b74c-780d-48db-b8bb-d7ee660ec9ae");
    redisService.saveIp(token, IpUtil.getIpAddress());
    TbUser tbUser = new TbUser();
    tbUser.setAid(1);
    tbUser.setUsername("admin");
    tbUser.setNickname("内置管理员");
    redisService.saveUser(token, tbUser);
    RedisTokenInfo redisTokenInfo = redisService.readTokenInfo(token);
    log.debug("redis信息：{}", redisTokenInfo);

  }
}
