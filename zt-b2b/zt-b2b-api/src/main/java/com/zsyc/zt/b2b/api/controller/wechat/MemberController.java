package com.zsyc.zt.b2b.api.controller.wechat;

import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.service.LoginService;
import com.zsyc.zt.b2b.service.MemberService;
import com.zsyc.zt.b2b.vo.ScanInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tang on 2020-09-14.
 */
@Api
@RestController
@RequestMapping("api/member")
@Slf4j
public class MemberController {
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private MemberService memberService;
    @Reference
    private LoginService loginService;

    @ApiOperation("确认扫码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scanCode", value = "scanCode", required = true, dataType = "String"),
            @ApiImplicitParam(name = "loginCode", value = "loginCode", required = true, dataType = "String")
    })
    @GetMapping("scanCodeConfirmAuth")
    public void scanCodeConfirmAuth(String scanCode,String loginCode){
        this.loginService.scanCodeConfirmAuth(scanCode,loginCode);
    }

}
