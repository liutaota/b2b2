//package com.zsyc.oauth.granter.account;
//
//import lombok.Data;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//
///**
// * Created by lcs on 2019-01-13.
// */
//@Data
//public class AccountAuthenticationToken extends AbstractAuthenticationToken {
//	private String account;
//	private String password;
//
//	public AccountAuthenticationToken(String account, String password) {
//		super(null);
//		this.account = account;
//		this.password = password;
//		this.setAuthenticated(false);
//
//	}
//
//	@Override
//	public Object getCredentials() {
//		return null;
//	}
//
//	@Override
//	public Object getPrincipal() {
//		return this.account;
//	}
//}
