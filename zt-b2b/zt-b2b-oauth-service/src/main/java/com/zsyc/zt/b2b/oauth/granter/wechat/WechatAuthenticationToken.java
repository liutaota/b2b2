package com.zsyc.zt.b2b.oauth.granter.wechat;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by lcs on 2019-04-26.
 */
@Data
public class WechatAuthenticationToken extends AbstractAuthenticationToken {

	private String scanCode;
    private String code;
	private Object principal;

	public WechatAuthenticationToken( String code,String scanCode) {
		super(null);
		this.code = code;
        this.scanCode = scanCode;
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
