//package com.zsyc.oauth.granter.account;
//
//import com.zsyc.oauth.config.ZSYCTokenGranter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Request;
//import org.springframework.security.oauth2.provider.TokenRequest;
//import org.springframework.stereotype.Component;
//
///**
// * Created by lcs on 2019-01-13.
// */
//@Component
//@Lazy
//public class AccountTokenGranter extends ZSYCTokenGranter {
//	private final static String GRANT_TYPE = "account_token";
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	public AccountTokenGranter() {
//		super(GRANT_TYPE);
//	}
//
//	@Override
//	public OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
//		String account = tokenRequest.getRequestParameters().get("account");
//		String password = tokenRequest.getRequestParameters().get("password");
//
//		Authentication authenticate = authenticationManager.authenticate(new AccountAuthenticationToken(account, password));
//
//		if (authenticate == null || !authenticate.isAuthenticated()) {
//			throw new InvalidGrantException("Could not authenticate user: " + account);
//		}
//
//		OAuth2Request storedOAuth2Request = super.auth2RequestFactory.createOAuth2Request(client, tokenRequest);
//		return new OAuth2Authentication(storedOAuth2Request, authenticate);
//	}
//
//}
