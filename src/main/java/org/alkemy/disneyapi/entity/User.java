package org.alkemy.disneyapi.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="users") @Data @NoArgsConstructor @AllArgsConstructor   
public class User {
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> authorities =new ArrayList<>();

}
