package top.huhuiyu.springboot.template.message;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "测试应答信息", description = "测试接口的应答信息")
public class TestMessage extends BaseResult {
  private static final long serialVersionUID = 6114709789926646572L;

  @ApiModelProperty(value = "当前时间，精确到毫秒的时间戳")
  private Date now;
  @ApiModelProperty(value = "当前时间戳，精确到秒的时间戳")
  private Long timestamp;

}
