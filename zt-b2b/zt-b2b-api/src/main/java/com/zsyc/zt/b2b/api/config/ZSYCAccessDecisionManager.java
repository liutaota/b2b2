package com.zsyc.zt.b2b.api.config;

import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Setting;
import com.zsyc.zt.b2b.service.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * Created by lcs on 2020/12/25.
 */
@Service
@Slf4j
public class ZSYCAccessDecisionManager implements AccessDecisionManager {
    @Value("${zsyc.oauth2.request.white-list}")
    private String[] whiteList;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Reference
    private SettingService settingService;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        String uri = ((FilterInvocation) object).getHttpRequest().getRequestURI();
        if (this.isPass(uri)) {
            return;
        }

        if (!(authentication instanceof OAuth2Authentication)) {
            throw new AccessDeniedException("no OAuth2Authentication");
        }

        // TODO 每个授权都会访问这里

        OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;

        String clientId = auth2Authentication.getOAuth2Request().getClientId();


        if (clientId.equals("b2b")) {
            Setting setting = this.settingService.genPCMINTime("PC_STATUS");
            if (setting.getValue().equals("0")) {
                throw new AccessDeniedException("b2b no OAuth2Authentication");
            }
        } else if (clientId.equals("wechat")) {
            Setting setting = this.settingService.genPCMINTime("MIN_STATUS");
            if (setting.getValue().equals("0")) {
                throw new AccessDeniedException("wechat no OAuth2Authentication");
            }
        }


    }

    /**
     * 是否过滤权限判断
     *
     * @param uri
     * @return
     */
    private boolean isPass(String uri) {
        if (this.whiteList == null) {
            return false;
        }

        for (String path : this.whiteList) {
            if (this.antPathMatcher.match(path, uri))
                return true;
        }
        return false;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
