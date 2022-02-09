package top.huhuiyu.springboot.template.message;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseResult;
import top.huhuiyu.springboot.template.entity.PageBean;
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
@ApiModel(value = "用户管理应答信息", description = "用户管理相关功能的应答信息")
public class TbUserManageMessage extends BaseResult {
  private static final long serialVersionUID = 2911159105807813468L;
  @ApiModelProperty(value = "分页信息")
  private PageBean page;
  @ApiModelProperty(value = "用户列表")
  private List<TbUser> list;

}
