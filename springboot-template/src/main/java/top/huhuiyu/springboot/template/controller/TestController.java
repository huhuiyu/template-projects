package top.huhuiyu.springboot.template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.huhuiyu.springboot.template.dao.UtilDAO;
import top.huhuiyu.springboot.template.entity.MailInfo;
import top.huhuiyu.springboot.template.message.TestMessage;
import top.huhuiyu.springboot.template.service.MailService;
import top.huhuiyu.springboot.template.validate.MailInfoValidate;

/**
 * 测试用控制器
 * 
 * @author DarkKnight
 *
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试接口")
public class TestController {

  private static final Logger log = LoggerFactory.getLogger(TestController.class);

  @Autowired
  private MailService mailService;
  @Autowired
  private UtilDAO utilDAO;

  @ApiOperation(value = "lombok字段", notes = "lombok字段功能测试")
  @ApiImplicitParams({ @ApiImplicitParam(name = "info", value = "测试info字段参数") })
  @PostMapping("/lombok")
  public TestMessage lombok(String info) throws Exception {
    TestMessage result = new TestMessage();
    result.setSuccessInfo(info);
    result.setTimestamp(utilDAO.queryTimestamp());
    result.setNow(utilDAO.queryTime());
    return result;
  }

  @ApiOperation(value = "发送邮件", notes = "发送邮件测试，全部参数都必须填写")
  @PostMapping("/mail")
  public TestMessage mail(@RequestBody @Validated(value = { MailInfoValidate.Main.class }) MailInfo mainInfo) throws Exception {
    log.debug("邮件信息{}", mainInfo);
    TestMessage result = new TestMessage();
    mailService.sendHtmlMail(mainInfo);
    result.setSuccessInfo("邮件发送成功");
    return result;
  }

  @ApiOperation(value = "表单校验", notes = "测试表单校验，只有收件人邮箱必须填写")
  @PostMapping("/validate")
  public TestMessage validate(@RequestBody @Validated(value = { MailInfoValidate.Demo.class }) MailInfo mainInfo) throws Exception {
    log.debug("邮件信息{}", mainInfo);
    TestMessage result = new TestMessage();
    result.setSuccessInfo("校验成功");
    return result;
  }

}
