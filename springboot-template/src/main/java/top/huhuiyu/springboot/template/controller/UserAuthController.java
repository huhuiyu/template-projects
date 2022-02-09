package top.huhuiyu.springboot.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

  @ApiOperation(value = "用户登录", notes = "用户登录，需要token信息")
  @ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "登录名", required = true), @ApiImplicitParam(name = "password", value = "登录密码，需要md5加密", required = true) })
  @PostMapping("/login")
  public TbUserMessage login(@Validated(TbUserValidate.Auth.class) TbUser user) throws Exception {
    return tbUserService.login(user);
  }

  @ApiOperation(value = "用户登出", notes = "用户登出，需要token信息")
  @PostMapping("/logout")
  public TbUserMessage logout() throws Exception {
    return tbUserService.logout();
  }

  @ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息，需要token信息，没有登录success为false")
  @PostMapping("/getUserInfo")
  public TbUserMessage getUserInfo() throws Exception {
    return tbUserService.getUserInfo();
  }

}
