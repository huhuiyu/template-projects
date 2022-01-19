package top.huhuiyu.springboot.template.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 控制器切点定义
 * 
 * @author DarkKnight
 *
 */
public abstract class BaseControllerAop {

  @Pointcut("execution(public * top.huhuiyu.springboot.template.controller..*.*(..))")
  public void controller() {
  }

}