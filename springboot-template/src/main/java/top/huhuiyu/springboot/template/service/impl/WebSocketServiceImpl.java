package top.huhuiyu.springboot.template.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.huhuiyu.springboot.template.dao.UtilDAO;
import top.huhuiyu.springboot.template.service.WebSocketService;
import top.huhuiyu.springboot.template.utils.JsonUtil;

/**
 * WebSocket服务实现
 * 
 * @author DarkKnight
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WebSocketServiceImpl implements WebSocketService {

  private static final Logger log = LoggerFactory.getLogger(WebSocketServiceImpl.class);
  @Autowired
  private UtilDAO utilDAO;

  private Set<Session> sessions = new HashSet<>();
  private Map<String, Set<Session>> publishes = new HashMap<String, Set<Session>>();

  @Override
  public void addSession(Session session) {
    sessions.add(session);
  }

  @Override
  public void removeSession(Session session) {
    sessions.remove(session);
    // 删除订阅消息session
    for (String key : publishes.keySet()) {
      Set<Session> sessionSet = publishes.get(key);
      sessionSet.remove(session);
    }
  }

  @Override
  public void broadcast(Object message) throws Exception {
    String json = JsonUtil.stringify(message);
    for (Session session : sessions) {
      session.getBasicRemote().sendText(json);
    }
  }

  @Override
  public void subscription(String channel, Session session) {
    if (!publishes.containsKey(channel)) {
      publishes.put(channel, new HashSet<Session>());
    }
    publishes.get(channel).add(session);
    log.debug("publishes:" + publishes.toString());
  }

  @Override
  public void publish(String channel, Object message) throws Exception {
    String json = JsonUtil.stringify(message);
    Set<Session> set = publishes.get(channel);
    log.debug(String.format("set:%s", set));
    if (set == null) {
      return;
    }
    for (Session session : set) {
      session.getBasicRemote().sendText(json);
    }
  }

  @Override
  public void sendTimestamp(Session session) throws Exception {
    session.getBasicRemote().sendText("" + utilDAO.queryTimestamp());
  }

  @Override
  public void sendMessage(Session session, String message) throws Exception {
    session.getBasicRemote().sendText(message);
  }

}