package com.zsyc.framework.oauth.config;

import com.alibaba.fastjson.JSONObject;
import com.zsyc.framework.base.ZSYCOAuthException;
import com.zsyc.framework.base.ZSYCRuntimeException;
import com.zsyc.framework.oauth.exception.ZSYCOAuth2AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.ParameterizedType;

/**
 * Created by lcs on 2019-01-13.
 */
@Slf4j
public abstract class ZSYCAuthenticationProvider<T extends Authentication> implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails userDetails = this.trySubAuthenticate((T) authentication);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(userDetails);
		return usernamePasswordAuthenticationToken;
	}

	private UserDetails trySubAuthenticate(T authentication) {
		try {
			return this.subAuthenticate( authentication);
		}catch (ZSYCOAuthException e){
			log.error("ZSYCOAuthException", e);
			ZSYCOAuth2AccessDeniedException zsycoAuth2AccessDeniedException = new ZSYCOAuth2AccessDeniedException(e.getMessage(), e.getCode(), e);
			if( e.getOtherInfo() != null ){
				log.error("ZSYCOAuthException info {}", JSONObject.toJSONString(e.getOtherInfo()));
				e.getOtherInfo().entrySet().forEach(stringStringEntry -> {
					zsycoAuth2AccessDeniedException.addAdditionalInformation(stringStringEntry.getKey(), stringStringEntry.getValue());
				});
			}
			throw  zsycoAuth2AccessDeniedException;
		} catch (ZSYCRuntimeException e){
			log.error("ZSYCRuntimeException error", e);
			throw new ZSYCOAuth2AccessDeniedException(e.getMessage(), "access_denied", e);
		}
	}

	public abstract UserDetails subAuthenticate(T authentication) throws AuthenticationException;

	@Override
	public boolean supports(Class<?> authentication) {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz.isAssignableFrom(authentication);
	}

}
