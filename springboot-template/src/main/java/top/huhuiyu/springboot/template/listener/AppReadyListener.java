package top.huhuiyu.springboot.template.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import top.huhuiyu.springboot.template.entity.SystemConfig;
import top.huhuiyu.springboot.template.service.RedisService;

/**
 * 监听应用程序启动
 * 
 * @author DarkKnight
 *
 */
public class AppReadyListener implements ApplicationListener<ApplicationReadyEvent> {

  private static final Logger log = LoggerFactory.getLogger(AppReadyListener.class);

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    // 处理系统配置初始化
    RedisService redisService = event.getApplicationContext().getBean(RedisService.class);
    try {
      SystemConfig config = redisService.readSystemConfig();
      log.debug("应用程序启动完成：{}", config);
    } catch (Exception ex) {
      log.error("应用程序启动发生异常", ex);
      throw new RuntimeException(ex);
    }
  }

}
