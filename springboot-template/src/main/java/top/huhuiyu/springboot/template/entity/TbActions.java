package top.huhuiyu.springboot.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * tb_actions表
 * 
 * @author DarkKnight
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "后端接口信息")
public class TbActions extends BaseEntity {

  private static final long serialVersionUID = -2895459696106713202L;

  @TableId(type = IdType.AUTO)
  @ApiModelProperty(value = "接口编号", example = "100")
  @ApiParam(hidden = true)
  private java.lang.Integer aid;
  @ApiModelProperty(value = "接口地址", example = "/user/info")
  @ApiParam(hidden = true)
  private java.lang.String url;
  @ApiModelProperty(value = "接口地址描述", example = "查询用户信息")
  @ApiParam(hidden = true)
  private java.lang.String info;
  @ApiModelProperty(value = "接口访问权限角色", example = "admin,user")
  @ApiParam(hidden = true)
  private java.lang.String role;

}