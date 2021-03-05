package com.zsyc.framework.oauth.exception;

import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;

/**
 * Created by lcs on 2019-04-28.
 */
public class ZSYCOAuth2AccessDeniedException extends OAuth2AccessDeniedException {
	private String code;

	public ZSYCOAuth2AccessDeniedException(String message,String code,Throwable t){
		super(message, null, t);
		this.code = code;
	}
	@Override
	public String getOAuth2ErrorCode() {
		return this.code;
	}
}
