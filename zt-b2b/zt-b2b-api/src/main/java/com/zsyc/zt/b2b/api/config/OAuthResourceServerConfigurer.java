package com.zsyc.zt.b2b.api.config;

import com.zsyc.webapp.config.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Created by lcs on 2019-01-13.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Slf4j
public class OAuthResourceServerConfigurer extends ResourceServerConfigurerAdapter {
	private static final String RESOURCE_ID = "zt-b2b-api";
//	private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('user')";

	@Value("${zsyc.oauth2.request.white-list}")
	private String[] whiteList;
	@Value("${zsyc.oauth2.store-redis-key}")
	private String storeRedisKey;
	@Autowired
	private ZSYCFilterInvocationSecurityMetadataSource zsycFilterInvocationSecurityMetadataSource;
	@Autowired
	private ZSYCAccessDecisionManager zsycAccessDecisionManager;

	@Bean
	public TokenStore getTokenStore(RedisConnectionFactory redisConnectionFactory){
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix(this.storeRedisKey);
		return tokenStore;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
		OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
		authenticationEntryPoint.setExceptionTranslator((WebResponseExceptionTranslator) e -> ResponseEntity.ok().body(errorBody(e)));
		resources.authenticationEntryPoint(authenticationEntryPoint);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests().antMatchers(this.whiteList).permitAll()
				.and()
				.authorizeRequests().antMatchers("/api/**").authenticated();

		FilterSecurityInterceptor filter = new FilterSecurityInterceptor();
		filter.setAccessDecisionManager(this.zsycAccessDecisionManager);
		filter.setSecurityMetadataSource(this.zsycFilterInvocationSecurityMetadataSource);
		http.addFilter(filter);

	}

	private ResponseJson errorBody(Exception e) {
		log.error("error", e);
		return ResponseJson.builder()
				.errorMessage(e.getMessage())
				.errorCode("401")
				.build();
	}

}
