package top.huhuiyu.springboot.template.websocket.processor;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.huhuiyu.springboot.template.service.WebSocketService;
import top.huhuiyu.springboot.template.utils.JsonUtil;
import top.huhuiyu.springboot.template.websocket.WebSocket;
import top.huhuiyu.springboot.template.websocket.base.BaseProcessor;
import top.huhuiyu.springboot.template.websocket.base.BaseWebSocketResult;
import top.huhuiyu.springboot.template.websocket.entity.ChatInfo;
import top.huhuiyu.springboot.template.websocket.util.WebSocketUtil;

/**
 * 聊天室处理器
 * 
 * @author DarkKnight
 *
 */
public class ChatProcessor implements BaseProcessor {

  private static final Logger log = LoggerFactory.getLogger(ChatProcessor.class);

  @Override
  public void onOpen(Session session) throws Exception {
    WebSocketService webSocketService = WebSocketUtil.getBean(WebSocketService.class);
    webSocketService.addSession(session);
    // 添加到聊天
    webSocketService.subscription(WebSocket.CHANNEL_CHAT, session);
  }

  @Override
  public void onMessage(String message, Session session) throws Exception {
    WebSocketService webSocketService = WebSocketUtil.getBean(WebSocketService.class);
    webSocketService.addSession(session);
    BaseWebSocketResult result;
    try {
      ChatInfo chatInfo = JsonUtil.parse(message, ChatInfo.class);
      log.debug("聊天信息：", chatInfo);
      // 广播到聊天频道
      result = BaseWebSocketResult.getSuccessInfo(chatInfo);
    } catch (Exception ex) {
      // 格式错误应答
      result = BaseWebSocketResult.getFailInfo(String.format("错误的信息格式：%s", ex.getMessage()));
      result.setType(WebSocket.TYPE_CHAT);
    }
    result.setType(WebSocket.TYPE_CHAT);
    webSocketService.publish(WebSocket.APP_CHAT, result);
  }

}
