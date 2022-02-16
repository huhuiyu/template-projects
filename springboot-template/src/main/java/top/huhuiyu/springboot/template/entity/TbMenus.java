package top.huhuiyu.springboot.template.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseEntity;

/**
 * tb_menus表
 * 
 * @author DarkKnight
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "菜单信息")
public class TbMenus extends BaseEntity {

  private static final long serialVersionUID = 3114887525746678043L;

  @TableId(type = IdType.AUTO)
  @ApiModelProperty(value = "菜单编号", example = "100")
  @ApiParam(hidden = true)
  private java.lang.Integer mid;
  @ApiModelProperty(value = "上级菜单编号", example = "10")
  @ApiParam(hidden = true)
  private java.lang.Integer parent;
  @ApiModelProperty(value = "菜单url", example = "/user/manage/index")
  @ApiParam(hidden = true)
  private java.lang.String url;
  @ApiModelProperty(value = "菜单文本", example = "用户信息管理")
  @ApiParam(hidden = true)
  private java.lang.String text;
  @ApiModelProperty(value = "菜单描述", example = "用户信息管理首页")
  @ApiParam(hidden = true)
  private java.lang.String info;
  @ApiModelProperty(value = "菜单图标", example = "#abc134")
  @ApiParam(hidden = true)
  private java.lang.String icon;
  @ApiModelProperty(value = "菜单访问权限角色", example = "admin,user")
  @ApiParam(hidden = true)
  private java.lang.String role;
  @ApiModelProperty(value = "菜单是否启用（y/n）", example = "y")
  @ApiParam(hidden = true)
  private java.lang.String enable;
  @TableField(updateStrategy = FieldStrategy.NEVER, insertStrategy = FieldStrategy.NEVER)
  @ApiModelProperty(value = "信息最后修改时间")
  @ApiParam(hidden = true)
  private java.util.Date lastupdate;

}