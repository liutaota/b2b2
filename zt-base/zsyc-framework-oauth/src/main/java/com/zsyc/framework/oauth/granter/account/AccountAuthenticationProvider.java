//package com.zsyc.oauth.granter.account;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.zsyc.account.entity.Account;
//import com.zsyc.account.service.LoginService;
//import com.zsyc.common.AssertExt;
//import com.zsyc.oauth.config.ZSYCAuthenticationProvider;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.stereotype.Component;
//
///**
// * Created by lcs on 2019-01-13.
// */
//@Component
//@Slf4j
//public class AccountAuthenticationProvider extends ZSYCAuthenticationProvider<AccountAuthenticationToken> {
//
//	@Reference
//	private LoginService loginService;
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Override
//	public UserDetails subAuthenticate(AccountAuthenticationToken authentication) throws AuthenticationException {
//		try {
//			Account account = this.loginService.login(Account.builder()
//					.account(authentication.getAccount())
//					.password(authentication.getPassword())
//					.build());
//			AssertExt.notNull(account, "login error");
//			return this.userDetailsService.loadUserByUsername(account.getAccount());
//		}catch (Exception e){
//			log.error("subAuthenticate error", e);
//			throw new OAuth2Exception(e.getMessage(), e);
//		}
//	}
//}
