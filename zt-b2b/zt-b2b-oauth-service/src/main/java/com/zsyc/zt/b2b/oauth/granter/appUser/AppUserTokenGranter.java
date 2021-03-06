package com.zsyc.zt.b2b.oauth.granter.appUser;

import com.zsyc.framework.oauth.config.ZSYCTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by lcs on 2020/7/15.
 */
@Component
@Lazy
public class AppUserTokenGranter extends ZSYCTokenGranter {
    private final static String GRANT_TYPE = "app_user_token";

    @Autowired
    private AuthenticationManager authenticationManager;

    public AppUserTokenGranter() {
        super(GRANT_TYPE);
    }

    @Override
    public OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> params = tokenRequest.getRequestParameters();

        Authentication authenticate = authenticationManager.authenticate(new AppUserAuthenticationToken(
                params.get("userName"), params.get("password")
        ));

        if (authenticate == null || !authenticate.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + authenticate);
        }

        OAuth2Request storedOAuth2Request = super.auth2RequestFactory.createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, authenticate);
    }
}
