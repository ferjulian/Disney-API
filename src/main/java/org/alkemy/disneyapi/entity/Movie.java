package org.alkemy.disneyapi.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity  @Table(name="movies") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Movie {
	

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String image;
	private String title;
	private Date creationDate;
	private int rating;
	
	
	@ManyToMany(mappedBy = "movies", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Character> characters = new HashSet<Character>();



	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="movies_genres",
			joinColumns = {@JoinColumn(name="id_movie")},
			inverseJoinColumns = {@JoinColumn(name="id_genre")}
			)
	@ToString.Exclude
	private Set<Genre> genres = new HashSet<Genre>();
	
	
	
	
	
}
