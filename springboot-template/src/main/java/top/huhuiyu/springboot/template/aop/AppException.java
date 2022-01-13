package top.huhuiyu.springboot.template.aop;

import lombok.Getter;
import lombok.Setter;
import top.huhuiyu.springboot.template.utils.SystemConstants;

/**
 * 自定义异常信息，当业务处理需要回滚事务时抛出
 * 
 * @author DarkKnight
 *
 */
@Getter
@Setter
public class AppException extends Exception {
  private static final long serialVersionUID = -3159270816815121266L;
  private int code;
  private String token;

  public AppException(String message) {
    this(500, message, "");
  }

  public AppException(int code, String message) {
    this(code, message, "");
  }

  public AppException(String message, String token) {
    this(SystemConstants.APP_ERROR_CODE, message, token);
  }

  public AppException(int code, String message, String token) {
    super(message);
    this.code = code;
    this.token = token;
  }

  public static AppException getAppException(int code, String message) {
    return new AppException(code, message);
  }

  public static AppException getAppException(int code, String message, String token) {
    return new AppException(code, message, token);
  }

  public static AppException getAppException(String message) {
    return new AppException(SystemConstants.APP_ERROR_CODE, message);
  }

  public static AppException getAppException(String message, String token) {
    return new AppException(SystemConstants.APP_ERROR_CODE, message, token);
  }
}
