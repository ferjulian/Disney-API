package org.alkemy.disneyapi.entity;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Table(name="characters") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Character {
	
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String image;
	private String name;
	private int age;
	private int weight;
	private String story;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="characters_movies",
			joinColumns = {@JoinColumn(name="id_character")},
			inverseJoinColumns = {@JoinColumn(name="id_movie")}
			)
	private Set<Movie> movies = new HashSet<Movie>();
}