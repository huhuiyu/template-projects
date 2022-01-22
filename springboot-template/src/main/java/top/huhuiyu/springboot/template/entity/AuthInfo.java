package top.huhuiyu.springboot.template.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseEntity;

/**
 * 保存在请求中的认证信息
 * 
 * @author DarkKnight
 *
 */
@Component
@Scope("request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo extends BaseEntity {

  private static final long serialVersionUID = -4942813805993502549L;
  private String token;
  private TbUser loginUser;
  private String ip;

}
