package top.huhuiyu.springboot.template.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseEntity;
import top.huhuiyu.springboot.template.utils.SystemConstants;
import top.huhuiyu.springboot.template.validate.MailInfoValidate;

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
  @NotBlank(message = "邮箱必须填写", groups = { MailInfoValidate.Main.class, MailInfoValidate.Demo.class })
  @Pattern(regexp = SystemConstants.EMAIL_CHECK, message = "邮箱格式不正确", groups = { MailInfoValidate.Main.class, MailInfoValidate.Demo.class })
  @ApiModelProperty(value = "收件人邮箱", example = "123456@qq.com")
  private String to;
  @NotBlank(message = "邮件主题必须填写", groups = { MailInfoValidate.Main.class })
  @ApiModelProperty(value = "邮件主题", example = "邮箱验证码", required = true)
  private String subject;
  @NotBlank(message = "邮件内容必须填写", groups = { MailInfoValidate.Main.class })
  @ApiModelProperty(value = "邮件内容", example = "<h1>邮件验证码123456</h1>")
  private String content;
}
