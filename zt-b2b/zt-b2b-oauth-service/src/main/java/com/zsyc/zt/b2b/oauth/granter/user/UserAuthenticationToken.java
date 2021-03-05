package com.zsyc.zt.b2b.oauth.granter.user;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by lcs on 2020/7/15.
 */
@Data
public class UserAuthenticationToken extends AbstractAuthenticationToken {
    private String userName;
    private String password;
    private Object principal;

    public UserAuthenticationToken(String userName, String password) {
        super(null);
        this.userName = userName;
        this.password = password;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
