package org.alkemy.disneyapi.service;

import java.util.List;

import org.alkemy.disneyapi.entity.User;

public interface UserService {

	List<User> getUsers();

	User saveUser(User user);

}
