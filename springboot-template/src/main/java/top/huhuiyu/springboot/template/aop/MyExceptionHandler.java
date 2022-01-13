package top.huhuiyu.springboot.template.aop;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import top.huhuiyu.springboot.template.base.BaseResult;

/**
 * 控制器全局错误处理器，ControllerAdvice注解表示是控制器层拦截处理，ExceptionHandler注解表示方法为错误处理器，参数是错误的类型
 * 
 * @author DarkKnight
 *
 */
@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public BaseResult handleException(Exception ex) {
    if (ex instanceof AppException) {
      AppException appException = (AppException) ex;
      BaseResult result = BaseResult.getFail(appException.getCode(), appException.getMessage());
      result.setToken(appException.getToken());
      log.debug("自定义异常token：{}", appException.getToken());
      return result;
    }
    if (ex instanceof MethodArgumentNotValidException) {
      // 校验没有通过的情况
      MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
      // 获取校验错误信息
      List<ObjectError> errors = manve.getBindingResult().getAllErrors();
      // 拼接错误信息
      StringBuilder sb = new StringBuilder();
      for (ObjectError oe : errors) {
        sb.append(oe.getDefaultMessage()).append(",");
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      BaseResult result = BaseResult.getFail(sb.toString());
      log.debug("校验异常：{}", manve.getMessage());
      return result;

    }
    if (ex instanceof NoHandlerFoundException) {
      // 配置spring.mvc.throw-exception-if-no-handler-found: true
      // 且spring.web.resources.add-mappings: false
      // 在控制器路径无法访问时会抛出本异常信息
      return BaseResult.getFail(404, "资源不存在");
    }
    log.error("服务器发生错误", ex);
    return BaseResult.getFail("服务器忙，请稍后重试！");
  }

}
