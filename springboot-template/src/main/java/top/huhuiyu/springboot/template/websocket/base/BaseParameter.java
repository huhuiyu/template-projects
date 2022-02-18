package top.huhuiyu.springboot.template.websocket.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseEntity;

/**
 * 请求参数类型
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseParameter extends BaseEntity {
  private static final long serialVersionUID = 4057777932437152179L;
  /**
   * 请求的动作（时间戳回应）
   */
  public static final String ACTION_TIMESTAMP = "timestamp";
  
  /**
   * 请求的动作
   */
  String action;

}
