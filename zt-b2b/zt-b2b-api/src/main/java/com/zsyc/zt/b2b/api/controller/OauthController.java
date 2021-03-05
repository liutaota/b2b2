package com.zsyc.zt.b2b.api.controller;

import io.swagger.annotations.*;
import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lcs on 2019-01-30.
 * api doc for TokenEndpoint
 *
 * @see org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
 */
@Api
@RestController
public class OauthController {
    @ApiOperation("oauth2.0 , get token , 这个接口还要 http basic 授权")
    @PostMapping("oauth/token （不要下划线的）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "可选:[user_token,wechat_token,telephone_token]", required = true, example = "user_token/wechat_token"),
            @ApiImplicitParam(name = "【wechat_token】loginCode", value = "微信授权code", required = true, example = ""),
            @ApiImplicitParam(name = "【mini_program_token】loginCode", value = "微信授权code", required = true, example = ""),
            @ApiImplicitParam(name = "【mini_program_token】encryptedData", value = "加密信息", required = true, example = ""),
            @ApiImplicitParam(name = "【mini_program_token】iv", value = "加密信息iv", required = true, example = ""),
            @ApiImplicitParam(name = "【telephone_token】telephone", value = "手机号", required = true, example = ""),
            @ApiImplicitParam(name = "【telephone_token】password", value = "密码", required = true, example = ""),
            @ApiImplicitParam(name = "【telephone_token】code", value = "验证码", required = true, example = ""),
            @ApiImplicitParam(name = "【telephone_token】loginCode", value = "微信授权code", required = true, example = ""),
            @ApiImplicitParam(name = "【user_token】userName", value = "帐号", required = true, example = "admin"),
            @ApiImplicitParam(name = "【user_token】password", value = "密码", required = true, example = "zsyc2018"),
            @ApiImplicitParam(name = "【b2b_member_token】telephone", value = "手机号", required = true, example = ""),
            @ApiImplicitParam(name = "【b2b_member_token】code", value = "验证码", required = true, example = ""),
            @ApiImplicitParam(name = "【b2b_member_token】password", value = "密码", required = true, example = ""),
            @ApiImplicitParam(name = "【b2b_member_token】rePassword", value = "密码", required = true, example = ""),
            @ApiImplicitParam(name = "【app_telephone_token】telephone", value = "手机号", required = true, example = ""),
            @ApiImplicitParam(name = "【app_telephone_token】password", value = "密码", required = true, example = ""),
            @ApiImplicitParam(name = "【app_telephone_token】code", value = "验证码", required = true, example = ""),


    })
    @ApiResponses({
            @ApiResponse(code = 403, message = "error 为0，跳注册页面；error 为1，跳公众号；error 为2，直接弹窗提示【error_description】内容")
    })
    public Res token() {
        throw new NotImplementedException("");
    }

    @Data
    @ApiModel
    private static class Res {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private Long expires_in;
        private String scope;
    }
}
