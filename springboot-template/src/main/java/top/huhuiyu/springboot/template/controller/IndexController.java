package top.huhuiyu.springboot.template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.huhuiyu.springboot.template.base.BaseResult;

/**
 * 首页控制器
 * 
 * @author DarkKnight
 *
 */
@RestController
public class IndexController {

  private static final Logger log = LoggerFactory.getLogger(IndexController.class);

  @RequestMapping("")
  public BaseResult index(String name) throws Exception {
    name = StringUtils.hasText(name) ? name.trim() : "";
    log.debug("姓名参数：{}", name);
    BaseResult result = new BaseResult();
    result.setSuccessInfo("hello " + name);
    return result;
  }
}
