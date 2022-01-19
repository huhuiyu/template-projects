package top.huhuiyu.springboot.template.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import top.huhuiyu.springboot.template.service.WebSocketService;
import top.huhuiyu.springboot.template.utils.JsonUtil;
import top.huhuiyu.springboot.template.websocket.base.BaseProcessor;
import top.huhuiyu.springboot.template.websocket.base.BaseWsInfo;
import top.huhuiyu.springboot.template.websocket.processor.BlankProcessor;
import top.huhuiyu.springboot.template.websocket.processor.ChatProcessor;
import top.huhuiyu.springboot.template.websocket.processor.EchoProcessor;
import top.huhuiyu.springboot.template.websocket.util.WebSocketUtil;

/**
 * WebSocket监听
 * 
 * @author 胡辉煜
 */
@ServerEndpoint(value = "/ws/{app}")
@Component
public class WebSocket {

  private static final Logger log = LoggerFactory.getLogger(WebSocket.class);
  public static final String APP_ECHO = "echo";
  public static final String APP_CHAT = "chat";
  public static final String APP_TIMESTAMP = "timestamp";
  private static final Map<String, BaseProcessor> PROCESSORM_MAP = new HashMap<>();

  public WebSocket() {
    PROCESSORM_MAP.put(APP_CHAT, new ChatProcessor());
    PROCESSORM_MAP.put(APP_ECHO, new EchoProcessor());
    PROCESSORM_MAP.put(APP_TIMESTAMP, new BlankProcessor());
  }

  /**
   * 连接建立成功调用的方法
   * 
   * @throws Exception
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("app") String app) throws Exception {
    WebSocketService webSocketService = WebSocketUtil.getBean(WebSocketService.class);
    log.debug("open app is:" + app);
    if (PROCESSORM_MAP.containsKey(app)) {
      PROCESSORM_MAP.get(app).onOpen(session);
    } else {
      webSocketService.sendMessage(session, JsonUtil.stringify(BaseWsInfo.getFailInfo("无效的请求路径")));
    }
    log.debug("onOpen：" + session.getId());
  }

  /**
   * 连接关闭调用的方法
   */
  @OnClose
  public void onClose(Session session, @PathParam("app") String app) {
    WebSocketService webSocketService = WebSocketUtil.getBean(WebSocketService.class);
    log.debug("close app is:" + app);
    webSocketService.removeSession(session);
    log.debug("onClose：" + session.getId());
  }

  /**
   * 收到客户端消息后调用的方法
   */
  @OnMessage
  public void onMessage(String message, Session session, @PathParam("app") String app) throws Exception {
    WebSocketService webSocketService = WebSocketUtil.getBean(WebSocketService.class);
    log.debug("message app is:" + app);
    if (PROCESSORM_MAP.containsKey(app)) {
      PROCESSORM_MAP.get(app).onMessage(message, session);
    } else {
      webSocketService.sendMessage(session, JsonUtil.stringify(BaseWsInfo.getFailInfo("无效的请求路径")));
    }
    log.debug("onMessage：" + message);
  }

  /**
   * 发生错误时调用
   */
  @OnError
  public void onError(Session session, Throwable error) {
    log.error("onError:", error);
  }
}