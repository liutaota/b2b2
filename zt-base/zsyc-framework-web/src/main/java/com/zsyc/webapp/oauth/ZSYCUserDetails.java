package com.zsyc.webapp.oauth;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by lcs on 2019-01-13.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZSYCUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;

	private Long accountId;

	@Generated
	private HashMap<String, Object> data = null;

	public ZSYCUserDetails setData(final String key, final Object val) {
		if (this.data == null) {
			this.data = new HashMap<>();
		}
		this.data.put(key, val);
		return this;
	}

	public <T> T getData(final String key){
		return (T)this.data.get(key);
	}

	private Collection<SimpleGrantedAuthority> authorities;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword(){
		return null;
	}
}
