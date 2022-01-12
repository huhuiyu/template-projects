package top.huhuiyu.springboot.template.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
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
@ApiModel(value = "邮件信息")
public class MailInfo extends BaseEntity {
  private static final long serialVersionUID = -1515983089763957511L;
  @ApiModelProperty(value = "收件人邮箱")
  @ApiParam(hidden = true)
  private String to;
  @ApiModelProperty(value = "邮件主题")
  @ApiParam(hidden = true)
  private String subject;
  @ApiModelProperty(value = "邮件内容")
  @ApiParam(hidden = true)
  private String content;
}
