/**
 *
 */
package com.servustech.spring.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.servustech.spring.login.UserDetailsImpl;
import com.servustech.spring.model.User;
import com.servustech.spring.model.UserRole;

public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		System.out.println("Login service");
		
		User user = userService.getUser(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Username not found!");
		}
		
		String password = user.getParola();
		Boolean enabled = user.getInactiv();
		Set<UserRole> roluri = user.getRoluri();
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
		if(!roluri.isEmpty()) {
			for(UserRole role : roluri) {
				authorities.add(new SimpleGrantedAuthority(role.getNume()));
			}
		}
		
		return new UserDetailsImpl(username, password, enabled, true, true, true, authorities);
	}
}