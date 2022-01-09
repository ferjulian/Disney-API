package org.alkemy.disneyapi.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="users") @Data @NoArgsConstructor @AllArgsConstructor   
public class User {
	
	@Id  
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String username;
	private String password;
	private int enabled;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles =new ArrayList<>();

}
