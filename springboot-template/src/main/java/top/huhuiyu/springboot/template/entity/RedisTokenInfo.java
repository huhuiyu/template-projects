package top.huhuiyu.springboot.template.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseEntity;

/**
 * redis中的关联token的信息
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RedisTokenInfo extends BaseEntity {
  private static final long serialVersionUID = 7709017633598351261L;
  private String ip;
  private TbUser tbUser;
}
