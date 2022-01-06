package top.huhuiyu.springboot.template.base;

import java.io.Serializable;

import top.huhuiyu.springboot.template.utils.JsonUtil;

/**
 * 实体类基类
 * 
 * @author DarkKnight
 *
 */
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 4922796893616602773L;

  @Override
  public String toString() {
    try {
      return JsonUtil.stringify(this);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
