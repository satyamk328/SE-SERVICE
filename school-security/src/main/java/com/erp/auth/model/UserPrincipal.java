package com.erp.auth.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.erp.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String firstName;
	private String lastName;
	private Long phone;
	private String email;
	@JsonIgnore
	private String password;

	private GrantedAuthority authorities;

	public UserPrincipal(Long userId, String firstName, String lastName, Long phone, String email, String password,
			GrantedAuthority authorities) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal build(User user) {
		GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().getRoleName());
		return new UserPrincipal(user.getUserId(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
				user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(authorities);
		return grantedAuthorities;
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
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrincipal user = (UserPrincipal) o;
		return Objects.equals(userId, user.userId);
	}
}
