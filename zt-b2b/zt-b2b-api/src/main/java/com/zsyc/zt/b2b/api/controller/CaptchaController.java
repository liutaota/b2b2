package com.zsyc.zt.b2b.api.controller;

import com.google.code.kaptcha.Producer;
import com.zsyc.framework.util.EnumScan;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by lcs on 2020/9/9.
 */
@Api
@Controller
@Slf4j
public class CaptchaController {
    @Reference
    private LoginService loginService;

    @Autowired
    private Producer captchaProducer;

    @ApiOperation("图片验证码，返回110x40的图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "codeKey", value = "使用手机号", required = true, dataType = "String")
    })
    @GetMapping({"/captcha/{codeKey}"})
    public void captcha(@PathVariable("codeKey") String codeKey, HttpServletResponse response) throws IOException {
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        ServletOutputStream responseOutputStream = response.getOutputStream();
        String createText = this.loginService.genCaptchaCode(codeKey);
        log.debug("createText {}", createText);
        BufferedImage challenge = captchaProducer.createImage(createText);
        ImageIO.write(challenge, "jpg", jpegOutputStream);
        byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
