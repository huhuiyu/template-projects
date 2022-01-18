package top.huhuiyu.springboot.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import top.huhuiyu.springboot.template.entity.RedisTokenInfo;
import top.huhuiyu.springboot.template.entity.TbAdmin;
import top.huhuiyu.springboot.template.service.RedisService;
import top.huhuiyu.springboot.template.utils.IpUtil;

/**
 * redis测试
 * 
 * @author DarkKnight
 *
 */
@SpringBootTest
public class RedisTest {
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
    String token = redisService.checkToken("f154b74c-780d-48db-b8bb-d7ee660ec9ae");
    redisService.saveIp(token, IpUtil.getIpAddress());
    TbAdmin tbAdmin = new TbAdmin();
    tbAdmin.setAid(1);
    tbAdmin.setUsername("admin");
    tbAdmin.setNickname("内置管理员");
    redisService.saveUser(token, tbAdmin);
    RedisTokenInfo redisTokenInfo = redisService.readTokenInfo(token);
    System.out.println(redisTokenInfo);
  }
}
