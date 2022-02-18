package ${builderUtil.getSubPackage("aop")};

import org.aspectj.lang.annotation.Pointcut;

/**
 * 控制器切点定义
 * 
 * @author ${baseInfo.author}
 *
 */
public interface BaseControllerAop {

  @Pointcut("execution(public * ${builderUtil.getSubPackage("controller")}..*.*(..))")
  default void controller() {
  }

}