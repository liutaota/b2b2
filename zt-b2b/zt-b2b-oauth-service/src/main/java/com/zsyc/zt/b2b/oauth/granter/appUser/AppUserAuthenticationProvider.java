package com.zsyc.zt.b2b.oauth.granter.appUser;

import com.zsyc.framework.oauth.config.ZSYCAuthenticationProvider;
import com.zsyc.webapp.oauth.BaseAccountHelper;
import com.zsyc.webapp.oauth.ZSYCUserDetails;
import com.zsyc.zt.b2b.entity.User;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * Created by lcs on 2020/7/15.
 */
@Component
@Slf4j
public class AppUserAuthenticationProvider extends ZSYCAuthenticationProvider<AppUserAuthenticationToken> {

    @Reference
    private AdminService adminService;
    @Autowired
    private OauthService oauthService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private static BaseAccountHelper baseAccountHelper = new BaseAccountHelper();

    @Override
    public UserDetails subAuthenticate(AppUserAuthenticationToken authentication) throws AuthenticationException {
        User user = new User();
        user.setUserName(authentication.getUserName());
        user.setPassword(authentication.getPassword());

        user = this.adminService.login(user,baseAccountHelper.getIP(this.httpServletRequest));
        this.oauthService.kickOutUser(user.getUserName());

        return ZSYCUserDetails.builder()
                .accountId(user.getId())
                .username(user.getUserName())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ADMIN")))
                .build();

    }
}

