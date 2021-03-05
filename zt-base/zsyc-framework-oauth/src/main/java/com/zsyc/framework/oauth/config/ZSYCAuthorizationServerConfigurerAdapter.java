package com.zsyc.framework.oauth.config;

import com.zsyc.webapp.config.OauthClientProperties;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lcs on 2019-01-02.
 */

@Component
public class ZSYCAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private TokenGranter[] tokenGranters;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private OauthClientProperties clientProperties;

	private AuthorizationServerEndpointsConfigurer endpointsConfigurer;

	@PostConstruct
	public void init( ){
		initTokenGranters(endpointsConfigurer);
	}
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()")
				.passwordEncoder(new PasswordEncoder() {
					@Override
					public String encode(CharSequence charSequence) {
						return charSequence.toString();
					}

					@Override
					public boolean matches(CharSequence charSequence, String s) {
						return s.equals(charSequence);
					}
				})
				.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		this.clientProperties.getClients().entrySet().stream().forEach(item->{
			val client = item.getValue();

			builder.withClient(client.getClientId())
					.secret(client.getClientSecret())
					.resourceIds(client.getResourceIds().toArray(new String[0]))
					.authorizedGrantTypes(client.getAuthorizedGrantTypes().toArray(new String[0]))
					.scopes(client.getScope().toArray(new String[0]))
					.accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
					.refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
					.autoApprove(true);

		});
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(this.tokenStore)
				.userDetailsService(this.userDetailsService)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
		initTokenGranters(endpoints);
		this.endpointsConfigurer = endpoints;
	}



	private void initTokenGranters(AuthorizationServerEndpointsConfigurer endpoints) {
		if (this.tokenGranters == null) return;

		List<TokenGranter> list = new ArrayList<>();
		list.add(endpoints.getTokenGranter());
		list.addAll(Arrays.asList(this.tokenGranters));
		endpoints.tokenGranter(new CompositeTokenGranter(list));
	}
}
