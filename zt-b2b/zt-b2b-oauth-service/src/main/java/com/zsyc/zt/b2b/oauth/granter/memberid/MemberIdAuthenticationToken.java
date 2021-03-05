package com.zsyc.zt.b2b.oauth.granter.memberid;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by lcs on 2019-04-26.
 */
@Data
public class MemberIdAuthenticationToken extends AbstractAuthenticationToken {

	private Long memberId;
	private Object principal;

	public MemberIdAuthenticationToken(Long memberId) {
		super(null);
		this.memberId = memberId;
		this.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}
}
