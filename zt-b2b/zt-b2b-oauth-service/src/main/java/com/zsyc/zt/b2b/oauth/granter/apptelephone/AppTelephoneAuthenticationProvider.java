package com.zsyc.zt.b2b.oauth.granter.apptelephone;

import com.zsyc.framework.oauth.config.ZSYCAuthenticationProvider;
import com.zsyc.webapp.oauth.ZSYCUserDetails;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.service.LoginService;
import com.zsyc.zt.b2b.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by lcs on 2019-04-26.
 */
@Component
@Slf4j
public class AppTelephoneAuthenticationProvider extends ZSYCAuthenticationProvider<AppTelephoneAuthenticationToken> {

	@Reference
	private LoginService loginService;
	@Autowired
	private OauthService oauthService;

	@Override
	public UserDetails subAuthenticate(AppTelephoneAuthenticationToken authentication) throws AuthenticationException {
		Member member = this.loginService.telephoneAppAuth(authentication.getTelephone(), authentication.getCode(),authentication.getPassword());
		if(this.loginService.loginList(authentication.getTelephone())){
			this.oauthService.kickOutB2BMember(member.getTelephone());
		}

		return ZSYCUserDetails.builder()
				.accountId(member.getId())
				.username(member.getTelephone())
				.authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
				.build().setData("originOpenid", member.getOpenid());

	}
}
