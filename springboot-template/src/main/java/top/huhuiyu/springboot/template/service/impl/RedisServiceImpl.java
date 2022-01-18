package top.huhuiyu.springboot.template.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import top.huhuiyu.springboot.template.entity.RedisTokenInfo;
import top.huhuiyu.springboot.template.entity.TbAdmin;
import top.huhuiyu.springboot.template.service.RedisService;
import top.huhuiyu.springboot.template.utils.JsonUtil;

/**
 * redis服务实现
 * 
 * @author DarkKnight
 *
 */
@Service
public class RedisServiceImpl implements RedisService {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public RedisTokenInfo readTokenInfo(String token) throws Exception {
    token = checkToken(token);
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    RedisTokenInfo redisTokenInfo = JsonUtil.parse(valueOperations.get(token), RedisTokenInfo.class);
    return redisTokenInfo;
  }

  @Override
  public RedisTokenInfo saveIp(String token, String ip) throws Exception {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    RedisTokenInfo redisTokenInfo;
    if (stringRedisTemplate.hasKey(token)) {
      // token存在就直接获取
      redisTokenInfo = JsonUtil.parse(valueOperations.get(token), RedisTokenInfo.class);
    } else {
      // token不存在就新建
      token = UUID.randomUUID().toString();
      redisTokenInfo = new RedisTokenInfo();
    }
    // 更新ip信息
    redisTokenInfo.setIp(ip);
    valueOperations.set(token, JsonUtil.stringify(redisTokenInfo));
    return redisTokenInfo;
  }

  @Override
  public RedisTokenInfo saveUser(String token, TbAdmin tbAdmin) throws Exception {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    RedisTokenInfo redisTokenInfo;
    if (stringRedisTemplate.hasKey(token)) {
      // token存在就直接获取
      redisTokenInfo = JsonUtil.parse(valueOperations.get(token), RedisTokenInfo.class);
    } else {
      // token不存在就新建
      token = UUID.randomUUID().toString();
      redisTokenInfo = new RedisTokenInfo();
    }
    // 更新用户信息
    redisTokenInfo.setTbAdmin(tbAdmin);
    valueOperations.set(token, JsonUtil.stringify(redisTokenInfo));
    return redisTokenInfo;
  }

  public String checkToken(String token) throws Exception {
    token = StringUtils.hasText(token) ? token : "";
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    if (stringRedisTemplate.hasKey(token)) {
      return token;
    }
    // token不存在就新建
    token = UUID.randomUUID().toString();
    RedisTokenInfo redisTokenInfo = new RedisTokenInfo();
    valueOperations.set(token, JsonUtil.stringify(redisTokenInfo));
    return token;
  }
}
