package top.huhuiyu.springboot.template.websocket.processor;

import javax.websocket.Session;

import top.huhuiyu.springboot.template.service.WebSocketService;
import top.huhuiyu.springboot.template.utils.JsonUtil;
import top.huhuiyu.springboot.template.websocket.base.BaseProcessor;
import top.huhuiyu.springboot.template.websocket.base.BaseWsInfo;
import top.huhuiyu.springboot.template.websocket.util.WebSocketUtil;

/**
 * echo应答处理器
 * 
 * @author DarkKnight
 *
 */
public class EchoProcessor implements BaseProcessor {

  @Override
  public void onOpen(Session session) throws Exception {
    WebSocketService webSocketService = WebSocketUtil.getBean(WebSocketService.class);
    webSocketService.addSession(session);
  }

  @Override
  public void onMessage(String message, Session session) throws Exception {
    WebSocketService webSocketService = WebSocketUtil.getBean(WebSocketService.class);
    BaseWsInfo baseWsInfo = BaseWsInfo.getSuccessInfo("服务器应答:" + message);
    baseWsInfo.setType(BaseWsInfo.TYPE_ECHO);
    webSocketService.sendMessage(session, JsonUtil.stringify(baseWsInfo));
  }

}
