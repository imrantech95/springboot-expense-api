package com.aitech.expense.security;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aitech.expense.entity.User;
import com.aitech.expense.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	 @Autowired
	    private UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User existingUser = userRepository.findByEmail(email)
	                .orElseThrow(() ->
	                        new UsernameNotFoundException("User not found with username or email: " + email));

			/*
			 * Set<GrantedAuthority> authorities = user.getRoles() .stream() .map((role) ->
			 * new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
			 */
	        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(),
	        		existingUser.getPassword(), new ArrayList<>());
	        //authorities
	    }
}
