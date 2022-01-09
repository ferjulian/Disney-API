package org.alkemy.disneyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DisneyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisneyapiApplication.class, args);
	}

	
	@Bean
	PasswordEncoder passwordEncoded(){
	return new BCryptPasswordEncoder();
	}
}
