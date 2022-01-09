package org.alkemy.disneyapi.rest;

import java.net.URI;
import java.util.List;

import org.alkemy.disneyapi.entity.User;
import org.alkemy.disneyapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List <User> > getUsers() {

	return ResponseEntity.ok().body(userService.getUsers());
	}
	
	
	@PostMapping("/user/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {

	URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
	
	return ResponseEntity.created(uri).body(userService.saveUser(user));
	}
	
}
