package org.alkemy.disneyapi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Table(name="genres") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Genre {
	
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String image;
	
	@ManyToMany(mappedBy = "genres", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Movie> movies = new HashSet<Movie>();

}
