package com.zsyc.zt.b2b.oauth.granter.miniProgram;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by lcs on 2019-04-26.
 */
@Data
public class MiniProgramAuthenticationToken extends AbstractAuthenticationToken {

	private String encryptedData;
	private String iv;
	private String loginCode;
	private Object principal;

	public MiniProgramAuthenticationToken(String encryptedData, String iv, String loginCode) {
		super(null);
		this.encryptedData = encryptedData;
		this.iv = iv;
		this.loginCode = loginCode;
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
