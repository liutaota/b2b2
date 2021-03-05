package com.zsyc.zt.b2b.oauth.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by lcs on 2019-01-02.
 */

@Component
public class LifeAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenEnhancer((accessToken, authentication) -> {
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
				defaultOAuth2AccessToken.setValue(String.format("%s-%s", UUID.randomUUID().toString().replaceAll("-", ""), System.currentTimeMillis()));
            }
            return accessToken;
        });
    }
}
