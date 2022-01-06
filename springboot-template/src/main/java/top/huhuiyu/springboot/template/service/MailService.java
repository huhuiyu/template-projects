package top.huhuiyu.springboot.template.service;

import top.huhuiyu.springboot.template.entity.MailInfo;

/**
 * 邮件服务
 * 
 * @author DarkKnight
 *
 */
public interface MailService {

  /**
   * 发送文本邮件
   * 
   * @param mailInfo 邮件信息
   * 
   * @throws Exception 发送邮件发生错误
   */
  void sendSimpleMail(MailInfo mailInfo) throws Exception;

  /**
   * 发送html邮件
   * 
   * @param mailInfo 邮件信息
   * 
   * @throws Exception 发送邮件发生错误
   */
  void sendHtmlMail(MailInfo mailInfo) throws Exception;

}
