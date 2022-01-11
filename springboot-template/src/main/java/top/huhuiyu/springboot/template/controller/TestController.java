package top.huhuiyu.springboot.template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.huhuiyu.springboot.template.dao.UtilDAO;
import top.huhuiyu.springboot.template.entity.MailInfo;
import top.huhuiyu.springboot.template.message.TestMessage;
import top.huhuiyu.springboot.template.service.MailService;

/**
 * 测试用控制器
 * 
 * @author DarkKnight
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {

  private static final Logger log = LoggerFactory.getLogger(TestController.class);

  @Autowired
  private MailService mailService;
  @Autowired
  private UtilDAO utilDAO;

  @GetMapping("/index")
  public TestMessage index(String test) throws Exception {
    TestMessage message = new TestMessage();
    message.setSuccessInfo(test);
    return message;
  }

  @PostMapping("/lombok")
  public TestMessage lombok(String info) throws Exception {
    TestMessage result = new TestMessage();
    result.setSuccessInfo(info);
    result.setTimestamp(utilDAO.queryTimestamp());
    result.setNow(utilDAO.queryTime());
    return result;
  }

  @PostMapping("/mail")
  public TestMessage mail(MailInfo mainInfo) throws Exception {
    log.debug("邮件信息{}", mainInfo);
    TestMessage result = new TestMessage();
    mailService.sendHtmlMail(mainInfo);
    result.setSuccessInfo("邮件发送成功");
    return result;
  }

}
