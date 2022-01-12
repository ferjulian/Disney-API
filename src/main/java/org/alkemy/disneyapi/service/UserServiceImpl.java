package org.alkemy.disneyapi.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


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
	private final EmailService emailService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByUsername(username);

		if(user == null){
		throw new UsernameNotFoundException("User not found in the Db");
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		user.getAuthorities().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role.getName() )); 
		});

		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),  authorities ); 
	}

	
	@Override
	public String saveUser(User user){
		
		
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		
		if (username == null)
			throw new RuntimeException("the username was not entered");
		else if (password == null)
			throw new RuntimeException("the password was not entered");
		else if (email == null)
			throw new RuntimeException("the email was not entered");

		if (username.isBlank() || password.isBlank())
			throw new RuntimeException("Username and password cannot be blank");
		
	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
		
		try {
			emailService.sendEmail(email, username);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return user.getUsername() + " your registration was successful!";
		
		}
	
	
}
