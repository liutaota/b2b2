package com.zsyc.zt.b2b.oauth.granter.memberid;

import com.zsyc.framework.oauth.config.ZSYCAuthenticationProvider;

import com.zsyc.webapp.oauth.ZSYCUserDetails;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
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
public class MemberIdAuthenticationProvider extends ZSYCAuthenticationProvider<MemberIdAuthenticationToken>{

	@Reference
	private MemberService memberService;

	@Override
	public UserDetails subAuthenticate(MemberIdAuthenticationToken authentication) throws AuthenticationException {
		Member member = this.memberService.getCustomerVoInfo(authentication.getMemberId());
		return ZSYCUserDetails.builder()
				.accountId(member.getId())
				.username(member.getTelephone())
				.authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
				.build();
	}

}
