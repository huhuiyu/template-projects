package top.huhuiyu.springboot.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.huhuiyu.springboot.template.entity.PageBean;
import top.huhuiyu.springboot.template.entity.TbUser;
import top.huhuiyu.springboot.template.message.TbUserManageMessage;
import top.huhuiyu.springboot.template.service.TbUserService;

@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/admin/user")
public class UserAdminController {
  @Autowired
  private TbUserService tbUserService;

  @ApiOperation(value = "查询用户信息", notes = "查询用户信息，需要token信息，需要管理员角色登录")
  @ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "登录名模糊查询"), @ApiImplicitParam(name = "nickname", value = "用户名模糊查询"), @ApiImplicitParam(name = "enable", value = "是否启用"),
      @ApiImplicitParam(name = "pageSize", value = "分页大小"), @ApiImplicitParam(name = "pageNumber", value = "当前页码") })
  @PostMapping("/query")
  public TbUserManageMessage query(PageBean pageBean, TbUser user) throws Exception {
    return tbUserService.query(pageBean, user);
  }

}
