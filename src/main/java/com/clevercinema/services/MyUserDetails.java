package com.clevercinema.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.clevercinema.entity.Authorities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.clevercinema.entity.Users;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Users user;

	public MyUserDetails() {
	}

	public MyUserDetails(Users user) {
		this.user = user;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> roleList = new ArrayList<>();
		Set<Authorities> authorities = user.getAuthorities();

		for (Authorities role : authorities) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthority().trim());
			roleList.add(authority);
		}
		System.out.println(user.getAuthorities());
		return roleList;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

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
		return user.isEnabled();
	}

}
