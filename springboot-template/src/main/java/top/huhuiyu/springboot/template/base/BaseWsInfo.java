package top.huhuiyu.springboot.template.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.huhuiyu.springboot.template.utils.SystemConstants;

/**
 * WebSocket统一应答对象
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseWsInfo extends BaseEntity {
  private static final long serialVersionUID = -7452439994480200880L;

  /**
   * 应答是否成功
   */
  private boolean success;
  /**
   * 应答代码
   */
  private int code;
  /**
   * 应答数据
   */
  private Object message;

  /**
   * 消息类型
   */
  private String type;

  public static BaseWsInfo getBaseWsInfo(Boolean success, int code, Object message) {
    BaseWsInfo baseWsInfo = new BaseWsInfo();
    baseWsInfo.setCode(code);
    baseWsInfo.setSuccess(success);
    baseWsInfo.setMessage(message);
    return baseWsInfo;
  }

  public static BaseWsInfo getSuccessInfo(int code, Object message) {
    return getBaseWsInfo(true, code, message);
  }

  public static BaseWsInfo getSuccessInfo(Object message) {
    return getBaseWsInfo(true, SystemConstants.WS_SUCCESS_CODE, message);
  }

  public static BaseWsInfo getFailInfo(int code, Object message) {
    return getBaseWsInfo(false, code, message);
  }

  public static BaseWsInfo getFailInfo(Object message) {
    return getBaseWsInfo(false, SystemConstants.WS_FAIL_CODE, message);
  }

}
