package com.zsyc.zt.b2b.oauth.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by lcs on 2019-01-13.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    //	@Reference
//	private LoginService loginService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Account account = this.loginService.get(username);
//		AssertExt.notNull(account, "account[%s] not exist");
//		return ZSYCUserDetails.builder()
//				.accountId(account.getId())
//				.username(username)
//				.authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
//				.build();
        throw new NotImplementedException("");

    }
}
