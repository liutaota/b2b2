package com.zsyc.framework.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * Created by lcs on 2019-01-13.
 */

public abstract class ZSYCTokenGranter implements TokenGranter {

	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;
	@Autowired
	private ClientDetailsService clientDetailsService;

	protected OAuth2RequestFactory auth2RequestFactory;

	private TokenGranter innerTokenGranter = null;

	private String grantType;

	public ZSYCTokenGranter(String grantType) {
		this.grantType = grantType;
	}
	@Override
	public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
		if(this.innerTokenGranter == null){
			this.auth2RequestFactory = new DefaultOAuth2RequestFactory(this.clientDetailsService);
			this.innerTokenGranter = new AbstractTokenGranter(
					this.authorizationServerTokenServices,
					this.clientDetailsService,
					this.auth2RequestFactory,
					this.grantType) {
				protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
					return ZSYCTokenGranter.this.getOAuth2Authentication(client, tokenRequest);
				}
			};
		}
		return this.innerTokenGranter.grant(grantType, tokenRequest);
	}

	public abstract OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest);
}
