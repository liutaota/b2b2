package com.zsyc.zt.b2b.oauth.granter.b2bmember;

import com.zsyc.framework.oauth.config.ZSYCAuthenticationProvider;

import com.zsyc.webapp.oauth.BaseAccountHelper;
import com.zsyc.webapp.oauth.ZSYCUserDetails;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.service.LoginService;
import com.zsyc.zt.b2b.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * Created by lcs on 2019-04-26.
 */
@Component
@Slf4j
public class B2bMemberAuthenticationProvider extends ZSYCAuthenticationProvider<B2bMemberAuthenticationToken> {

    @Reference
    private LoginService loginService;
    @Autowired
    private OauthService oauthService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    private static BaseAccountHelper baseAccountHelper = new BaseAccountHelper();

    @Override
    public UserDetails subAuthenticate(B2bMemberAuthenticationToken authentication) throws AuthenticationException {
        Member member = this.loginService.memberLogin(authentication.getTelephone(), authentication.getPassword(), authentication.getCode(), baseAccountHelper.getIP(this.httpServletRequest));

      /*  if (StringUtils.isNoneBlank(authentication.getRePassword(),authentication.getCode())) {
            member = this.loginService.register(authentication.getTelephone(), authentication.getCode(), authentication.getPassword(), authentication.getRePassword(), baseAccountHelper.getIP(this.httpServletRequest));

        } else {
            member = this.loginService.memberLogin(authentication.getTelephone(), authentication.getPassword(), authentication.getCode(), baseAccountHelper.getIP(this.httpServletRequest));
        }*/
        if(this.loginService.loginList(authentication.getTelephone())){
            this.oauthService.kickOutB2BMember(member.getTelephone());
        }
        return ZSYCUserDetails.builder()
                .accountId(member.getId())
                .username(member.getTelephone())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
                .build();

    }
}
