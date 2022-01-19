package top.huhuiyu.springboot.template.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import top.huhuiyu.springboot.template.dao.UtilDAO;
import top.huhuiyu.springboot.template.service.WebSocketService;
import top.huhuiyu.springboot.template.websocket.base.BaseWsInfo;

/**
 * 定时任务
 * 
 * @author DarkKnight
 */
@Component
public class ScheduleTask {
  private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);
  @Autowired
  private WebSocketService webSocketService;
  @Autowired
  private UtilDAO utilDAO;

  @Scheduled(initialDelay = 5 * 1000, fixedDelay = 60 * 1000)
  public void timestamp() {
    try {
      log.debug("正在广播时间戳");
      BaseWsInfo baseWsInfo = BaseWsInfo.getSuccessInfo(utilDAO.queryTimestamp());
      baseWsInfo.setType(BaseWsInfo.TYPE_TIME);
      webSocketService.broadcast(baseWsInfo);
    } catch (Exception ex) {
      log.error("广播时间戳", ex);
    }
  }
}