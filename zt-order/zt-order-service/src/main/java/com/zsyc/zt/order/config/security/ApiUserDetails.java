package com.zsyc.zt.order.config.security;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * B2b 用户信息
 *
 * @author
 */
@Setter
@Getter
public class ApiUserDetails extends User {
    public Long customId;
    public Long  b2bCustomId;


    public ApiUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Long customId, Long b2bCustomId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,new ArrayList<>());
        this.customId = customId;
        this.b2bCustomId = b2bCustomId;
    }
}
