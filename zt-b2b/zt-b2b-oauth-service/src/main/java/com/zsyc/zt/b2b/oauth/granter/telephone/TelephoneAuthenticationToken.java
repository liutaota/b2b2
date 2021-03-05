package com.zsyc.zt.b2b.oauth.granter.telephone;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by lcs on 2019-04-26.
 */
@Data
public class TelephoneAuthenticationToken extends AbstractAuthenticationToken {

	private String telephone;
	private String code;
	private String loginCode;
	private String password;
	private Object principal;

	public TelephoneAuthenticationToken(String telephone, String code,String loginCode,String password) {
		super(null);
		this.telephone = telephone;
		this.code = code;
		this.loginCode = loginCode;
		this.password = password;
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
