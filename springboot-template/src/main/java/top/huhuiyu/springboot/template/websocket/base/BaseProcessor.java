package top.huhuiyu.springboot.template.websocket.base;

import javax.websocket.Session;

/**
 * websocket处理器
 * 
 * @author DarkKnight
 *
 */
public interface BaseProcessor {
  default void onOpen(Session session) throws Exception {
  }

  default void onMessage(String message, Session session) throws Exception {
  }
}
