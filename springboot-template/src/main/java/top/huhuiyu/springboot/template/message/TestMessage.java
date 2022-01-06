package top.huhuiyu.springboot.template.message;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.base.BaseResult;

/**
 * 测试应答消息
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestMessage extends BaseResult {
  private static final long serialVersionUID = 6114709789926646572L;

  private Date timestamp;

}
