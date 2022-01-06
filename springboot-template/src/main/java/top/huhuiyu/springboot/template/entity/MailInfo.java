package top.huhuiyu.springboot.template.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseEntity;

/**
 * 邮件信息
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo extends BaseEntity {
  private static final long serialVersionUID = -1515983089763957511L;
  private String to;
  private String subject;
  private String content;
}
