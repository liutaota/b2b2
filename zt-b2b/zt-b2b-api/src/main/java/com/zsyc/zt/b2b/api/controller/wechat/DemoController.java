package com.zsyc.zt.b2b.api.controller.wechat;

import com.zsyc.zt.inca.service.DemoService;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lcs on 2019-05-10.
 */
//@Api
@RestController
@RequestMapping("api/demo")
//@PreAuthorize("#oauth2.hasScope('admin')")
//@PreAuthorize("hasAuthority('ADMIN')")
public class DemoController {
    @Autowired
    private AccountHelper accountHelper;

    @Reference
	private AdminService adminService;
    @Reference
	private DemoService demoService;

    @ApiImplicitParams({})
    @GetMapping("memberInfo")
    public Object memberDemo() {
        return this.accountHelper.userDetail();
    }

    @ApiImplicitParams({})
    @GetMapping("memberInfo2")
//	@PreAuthorize("#oauth2.hasScope('admin')")
    @PreAuthorize("hasAuthority('USER')")
    public Object memberDemo2() {
        return this.accountHelper.userDetail();
    }

    @ApiOperation("获取用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "用户ID", value = "userId", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "用户", value = "userName", required = false, dataType = "String"),
	})
	@GetMapping("userInfo")
	public Object serviceInfo(Long userId) {
		return this.adminService.getUserInfo(userId);
	}


	@ApiImplicitParams({
			@ApiImplicitParam(name = "hello", value = "userId", required = true, dataType = "String")
	})
	@GetMapping("echo")
	public Object echo(String hello) {
		return this.demoService.echo(hello);
	}
}
