package top.huhuiyu.springboot.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping("/index")
  public String index(String test) throws Exception {
    return test;
  }

}
