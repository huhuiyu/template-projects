package top.huhuiyu.springboot.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.huhuiyu.springboot.template.entity.TbUser;
import top.huhuiyu.springboot.template.message.TbUserMessage;
import top.huhuiyu.springboot.template.service.TbUserService;
import top.huhuiyu.springboot.template.validate.TbUserValidate;

/**
 * 首页控制器
 * 
 * @author DarkKnight
 *
 */
@RestController
@Api(tags = "用户认证接口")
@RequestMapping("/user/auth")
public class UserAuthController {
  @Autowired
  private TbUserService tbUserService;

  @ApiOperation(value = "用户登录", notes = "用户登录，需要token信息，用户名，md加密后的密码")
  @PostMapping("/login")
  public TbUserMessage login(@Validated(TbUserValidate.Auth.class) @RequestBody TbUser user) throws Exception {
    return tbUserService.login(user);
  }

}
