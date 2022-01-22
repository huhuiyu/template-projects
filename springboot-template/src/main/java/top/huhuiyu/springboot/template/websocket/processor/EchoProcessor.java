package top.huhuiyu.springboot.template.websocket.processor;

import javax.websocket.Session;

import top.huhuiyu.springboot.template.service.WebSocketService;
import top.huhuiyu.springboot.template.utils.ApplicationUtil;
import top.huhuiyu.springboot.template.utils.JsonUtil;
import top.huhuiyu.springboot.template.websocket.WebSocket;
import top.huhuiyu.springboot.template.websocket.base.BaseProcessor;
import top.huhuiyu.springboot.template.websocket.base.BaseWebSocketResult;

/**
 * echo应答处理器
 * 
 * @author DarkKnight
 *
 */
public class EchoProcessor implements BaseProcessor {

  @Override
  public void onOpen(Session session) throws Exception {
    WebSocketService webSocketService = ApplicationUtil.getBean(WebSocketService.class);
    webSocketService.addSession(session);
  }

  @Override
  public void onMessage(String message, Session session) throws Exception {
    WebSocketService webSocketService = ApplicationUtil.getBean(WebSocketService.class);
    BaseWebSocketResult result = BaseWebSocketResult.getSuccessInfo("服务器应答:" + message);
    result.setType(WebSocket.TYPE_ECHO);
    webSocketService.sendMessage(session, JsonUtil.stringify(result));
  }

}
