package top.huhuiyu.springboot.template.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import top.huhuiyu.springboot.template.base.BaseResult;
import top.huhuiyu.springboot.template.entity.AuthInfo;
import top.huhuiyu.springboot.template.entity.RedisTokenInfo;
import top.huhuiyu.springboot.template.service.RedisService;
import top.huhuiyu.springboot.template.utils.ApplicationUtil;
import top.huhuiyu.springboot.template.utils.IpUtil;
import top.huhuiyu.springboot.template.utils.SystemConstants;

/**
 * 控制器token切面
 *
 * @author 胡辉煜
 */
@Aspect
@Component
public class ControllerToken implements BaseControllerAop {

  private static final Logger log = LoggerFactory.getLogger(ControllerToken.class);
  @Autowired
  private RedisService redisService;

  @Around("controller()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    log.debug("控制器切面token处理");
    // 处理token信息 =====================================================
    String token = processToken(pjp);
    // token存在就保存ip地址信息并更新token相关信息
    if (StringUtils.hasText(token)) {
      processAuthInfo(token);
    }
    // 执行控制器方法 =======================================================
    Object result = pjp.proceed();
    // 处理应答结果 =========================================================
    // 如果控制器返回的是标准应答结果就添加token信息
    if ((StringUtils.hasText(token)) && (result instanceof BaseResult)) {
      BaseResult message = (BaseResult) result;
      message.setToken(token);
    }
    return result;
  }

  /**
   * 处理token信息
   * 
   * @param pjp 切面信息
   * 
   * @return token信息
   * 
   * @throws Exception 处理发生错误
   */
  private String processToken(ProceedingJoinPoint pjp) throws Exception {
    // 通过注解获取是否需要token信息
    AnnoNoToken annoNoToken = pjp.getTarget().getClass().getAnnotation(AnnoNoToken.class);
    if (annoNoToken != null) {
      log.debug("获取到AnnoNoToken信息的对象:{}", pjp.getTarget().getClass().getName());
      return "";
    }
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    annoNoToken = methodSignature.getMethod().getAnnotation(AnnoNoToken.class);
    if (annoNoToken != null) {
      log.debug("获取到AnnoNoToken信息的方法:{}", methodSignature.getMethod().getName());
      return "";
    }
    // 获取请求中的token信息
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    String token = request.getHeader(SystemConstants.TOKEN_KEY);
    // 校验token信息
    token = redisService.checkToken(token);
    return token;
  }

  /**
   * 处理token中的认证信息和ip信息
   * 
   * @param token token值
   * 
   * @throws Exception 处理发生错误
   */
  private void processAuthInfo(String token) throws Exception {
    redisService.saveIp(token, IpUtil.getIpAddress());
    RedisTokenInfo redisTokenInfo = redisService.readTokenInfo(token);
    AuthInfo authInfo = ApplicationUtil.getBean(AuthInfo.class);
    authInfo.setToken(token);
    authInfo.setLoginUser(redisTokenInfo.getTbUser());
    authInfo.setIp(redisTokenInfo.getIp());
  }
}