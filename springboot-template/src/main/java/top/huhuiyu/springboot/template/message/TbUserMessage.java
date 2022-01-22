package top.huhuiyu.springboot.template.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseResult;
import top.huhuiyu.springboot.template.entity.TbUser;

/**
 * 测试应答消息
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户应答信息", description = "用户相关功能的应答信息")
public class TbUserMessage extends BaseResult {
  private static final long serialVersionUID = 4488655858210710047L;
  @ApiModelProperty(value = "用户信息")
  private TbUser tbUser;

}
