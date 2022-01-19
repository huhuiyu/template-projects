package top.huhuiyu.springboot.template.utils;

/**
 * 系统常量定义
 * 
 * @author DarkKnight
 *
 */
public interface SystemConstants {

  /**
   * 正常应答代码
   */
  int OK_CODE = 200;
  /**
   * 失败应答代码
   */
  int ERROR_CODE = 500;

  /**
   * ip信息最小长度
   */
  int IP_MIN_LENGTH = 15;
  /**
   * ip分隔字符
   */
  String IP_SPLIT = ",";
  /**
   * 未知ip
   */
  String UNKNOWN_IP = "unknown";
  /**
   * 本机ip
   */
  String LOCAL_IP = "127.0.0.1";

  /**
   * websocket成功应答代码
   */
  int WS_SUCCESS_CODE = 200;
  /**
   * websocket失败应答代码
   */
  int WS_FAIL_CODE = 500;
  /**
   * websocket类型（时间戳）
   */
  String WS_TYPE_TIME = "timestamp";
  /**
   * websocket类型（回声）
   */
  String WS_TYPE_ECHO = "echo";
  /**
   * websocket类型（聊天）
   */
  String WS_TYPE_CHAT = "chat";

  /**
   * 数据库最大分页配置
   */
  Long MAX_PAGE_SIZE = 1000l;

  /**
   * 应用错误代码
   */
  int APP_ERROR_CODE = 500;

  /**
   * 手机号码正则
   */
  String PHONE_CHECK = "^1[3-9]\\d{9}$";
  /**
   * 32位md5正则
   */
  String MD5_CHECK = "^[a-zA-Z0-9]{32}$";

  /**
   * 邮箱正则
   */
  String EMAIL_CHECK = "^\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}$";

  /**
   * 系统配置key;
   */
  String SYSTEM_CONFIG_KEY = "springboot_template_system_config";
}
