package top.huhuiyu.springboot.template.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.websocket.base.BaseParameter;

/**
 * 聊天信息
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatInfo extends BaseParameter {
  private static final long serialVersionUID = -9021743231503483831L;
  private String name;
  private String info;

}
