package com.zsyc.framework.oauth.config;

import com.zsyc.webapp.config.OauthClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Created by lcs on 2019-05-05.
 */
@Configuration
public class OAuthConfig {

	@Autowired
	private OauthClientProperties clientProperties;

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	@Bean
	public TokenStore getTokenStore(){
		RedisTokenStore tokenStore = new RedisTokenStore(this.redisConnectionFactory);
		tokenStore.setPrefix(this.clientProperties.getStoreRedisKey());
		return tokenStore;
	}
}
