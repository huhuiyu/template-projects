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
}
