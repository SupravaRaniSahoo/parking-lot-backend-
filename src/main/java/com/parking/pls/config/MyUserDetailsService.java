package com.parking.pls.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.parking.pls.dto.LoginDTO;
import com.parking.pls.entity.Login;
import com.parking.pls.repository.LoginRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private LoginRepository loginRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> user = loginRepository.findByUsername(username);
		if(user.isPresent()) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			Arrays.asList(user.get().getUsername().split(username)).stream().forEach(authority -> {
				authorities.add(new SimpleGrantedAuthority(authority));
			});
		return new User(user.get().getUsername(), user.get().getPassword(), authorities);	
		}else {
			throw new UsernameNotFoundException("User "+username+" does not exist...");
		}
		
//		if(user == null) {
//			throw new UsernameNotFoundException("User not found!!");
//		}else {
//			return new CustomerDetails(user);
//		}
		
	}
	
	public Login save(LoginDTO user) {
		Login newUser = new Login();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		return loginRepository.save(newUser);
	}

	
}
