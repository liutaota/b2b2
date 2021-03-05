package com.zsyc.zt.b2b.oauth.service;

import com.zsyc.webapp.config.OauthClientProperties;
import com.zsyc.zt.b2b.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by lcs on 2019-06-13.
 */
@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

	@Autowired
	private TokenStore tokenStore;
	@Autowired
	private OauthClientProperties clientProperties;

	private final static String CLIENT_KEY = "wechat";

	private final static String B2B_KEY = "b2b";

	private final static String ADMIN_KEY = "admin";

	@Override
	public void kickOutWechatMember(String openId) {
		this.kickOut(CLIENT_KEY, openId);
	}

	@Override
	public void kickOutB2BMember(String telephone) {
		this.kickOut(B2B_KEY,telephone);
	}

	@Override
	public void kickOutUser(String userId) {
		this.kickOut(ADMIN_KEY, userId);
	}

	private void kickOut(String clientId, String username) {
		BaseClientDetails clientDetails = this.clientProperties.getClients().get(clientId);
		if (clientDetails == null) {
			log.warn("client detail key [{}] not exist", CLIENT_KEY);
			return;
		}

		this.tokenStore
				.findTokensByClientIdAndUserName(clientDetails.getClientId(), username)
				.stream()
				.forEach(OAuth2AccessToken -> {
					this.tokenStore.removeAccessToken(OAuth2AccessToken);
					this.tokenStore.removeRefreshToken(OAuth2AccessToken.getRefreshToken());
				});
	}
}
