package org.alkemy.disneyapi.repository;

import java.util.List;

import org.alkemy.disneyapi.entity.Movie;
import org.alkemy.disneyapi.projection.MovieProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	
	//@Query(value="Select * from movies", nativeQuery=true)
	@Query("Select m from Movie m")
	List<MovieProjection> getAllMovies();
}
