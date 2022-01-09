package org.alkemy.disneyapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.alkemy.disneyapi.entity.User;
import org.alkemy.disneyapi.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByUsername(username);

		if(user == null){
		throw new UsernameNotFoundException("User not found in the Db");
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		user.getRoles().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role.getName() )); 
		});

		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),  authorities ); 
	}

	
	@Override
	public User saveUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	
}
