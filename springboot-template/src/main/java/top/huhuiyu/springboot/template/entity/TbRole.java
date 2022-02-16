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
 * tb_role表
 * 
 * @author DarkKnight
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色信息")
public class TbRole extends BaseEntity {

  private static final long serialVersionUID = -8995419054149524573L;

  @ApiModelProperty(value = "角色名", example = "admin")
  @ApiParam(hidden = true)
  private java.lang.String roleName;
  @ApiModelProperty(value = "角色组", example = "admin")
  @ApiParam(hidden = true)
  private java.lang.String roleGroup;
  @ApiModelProperty(value = "角色描述", example = "管理员")
  @ApiParam(hidden = true)
  private java.lang.String roleInfo;
  @ApiModelProperty(value = "是否启用（y/n）", example = "y")
  @ApiParam(hidden = true)
  private java.lang.String enable;

}