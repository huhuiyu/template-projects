package top.huhuiyu.springboot.template.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import top.huhuiyu.springboot.template.entity.MailInfo;
import top.huhuiyu.springboot.template.service.MailService;

/**
 * 邮件服务实现
 * 
 * @author DarkKnight
 *
 */
@Service
public class MailServiceImpl implements MailService {
  @Value("${spring.mail.username}")
  private String from;

  @Autowired
  private JavaMailSender sender;

  @Override
  public synchronized void sendSimpleMail(MailInfo mailInfo) throws Exception {
    SimpleMailMessage message = new SimpleMailMessage();
    // 收信人
    message.setTo(mailInfo.getTo());
    // 主题
    message.setSubject(mailInfo.getSubject());
    // 内容
    message.setText(mailInfo.getContent());
    // 发信人
    message.setFrom(from);
    sender.send(message);
  }

  @Override
  public synchronized void sendHtmlMail(MailInfo mailInfo) throws Exception {
    // 使用MimeMessage，MIME协议
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper;
    // MimeMessageHelper帮助我们设置更丰富的内容
    helper = new MimeMessageHelper(message, true);
    helper.setFrom(from);
    helper.setTo(mailInfo.getTo());
    helper.setSubject(mailInfo.getSubject());
    // true代表支持html
    helper.setText(mailInfo.getContent(), true);
    sender.send(message);
  }

}
