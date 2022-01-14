package top.huhuiyu.springboot.template.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseEntity;
import top.huhuiyu.springboot.template.entity.MailInfo;
import top.huhuiyu.springboot.template.entity.TbAdmin;

/**
 * 测试model
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "测试Model")
public class TestModel extends BaseEntity {
  private static final long serialVersionUID = -5150604283449799420L;
  @ApiModelProperty(value = "用户信息")
  private TbAdmin user;
  @ApiModelProperty(value = "邮件列表")
  private List<MailInfo> mailList;
}
