package top.huhuiyu.springboot.template.entity;

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
public class TbAdmin extends BaseEntity {

  private static final long serialVersionUID = 3250142504095437911L;

  private java.lang.Integer aid;
  private java.lang.String username;
  private java.lang.String password;
  private java.lang.String salt;
  private java.lang.String nickname;
  private java.lang.String accessKey;
  private java.lang.String role;
  private java.lang.String enable;
  private java.util.Date lastupdate;

}