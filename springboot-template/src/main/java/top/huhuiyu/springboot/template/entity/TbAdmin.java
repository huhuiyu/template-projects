package top.huhuiyu.springboot.template.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseEntity;

/**
 * tb_admin表
 * 
 * @author DarkKnight
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户基本信息")
public class TbAdmin extends BaseEntity {

  private static final long serialVersionUID = 3250142504095437911L;

  @ApiModelProperty(value = "用户编号", example = "1000")
  private java.lang.Integer aid;
  @ApiModelProperty(value = "登录名", example = "user")
  private java.lang.String username;
  @ApiModelProperty(value = "密码（需要md5加密）", example = "900150983cd24fb0d6963f7d28e17f72")
  private java.lang.String password;
  @ApiModelProperty(hidden = true)
  private java.lang.String salt;
  @ApiModelProperty(value = "用户名", example = "黑暗骑士")
  private java.lang.String nickname;
  @ApiModelProperty(value = "二次开发key", example = "55172732-xxxx-yyyy-zzzz-ecb08d2cce66")
  private java.lang.String accessKey;
  @ApiModelProperty(value = "用户角色", example = "user")
  private java.lang.String role;
  @ApiModelProperty(value = "是否启用（y/n）", example = "y")
  private java.lang.String enable;
  @ApiModelProperty(value = "信息最后修改时间")
  private java.util.Date lastupdate;

}