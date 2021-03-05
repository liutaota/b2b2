package com.zsyc.zt.b2b.oauth.granter.b2bmember;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by lcs on 2019-04-26.
 */
@Data
public class B2bMemberAuthenticationToken extends AbstractAuthenticationToken {

    private String telephone;
    private String password;
	private String rePassword;
	private String code;
	private Object principal;

	public B2bMemberAuthenticationToken(String telephone, String password,String rePassword, String code) {
		super(null);
		this.telephone = telephone;
        this.password = password;
		this.rePassword = rePassword;
		this.code = code;
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
