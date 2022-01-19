package top.huhuiyu.springboot.template.websocket.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * websocket工具
 * 
 * @author DarkKnight
 */
@Component
public class WebSocketUtil implements ApplicationContextAware {

  private static final Logger log = LoggerFactory.getLogger(WebSocketUtil.class);

  private static ApplicationContext applicationContext;

  /**
   * 获取applicationContext
   *
   * @return ApplicationContext
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static <T> T getBean(Class<T> clazz) {
    return getApplicationContext().getBean(clazz);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    log.debug("设置应用程序上下文：{}", applicationContext);
    if (WebSocketUtil.applicationContext == null) {
      WebSocketUtil.applicationContext = applicationContext;
    }
  }

}