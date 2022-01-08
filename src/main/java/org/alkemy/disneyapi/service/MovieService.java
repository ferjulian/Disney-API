package org.alkemy.disneyapi.service;

import java.util.List;

import org.alkemy.disneyapi.entity.Movie;
import org.alkemy.disneyapi.projection.MovieProjection;

public interface MovieService {

	List<MovieProjection> getMovies();

	Movie findById(Long id);

	void deleteById(Long id);

	Movie addMovie(Movie movie);

	List<Movie> getByName(String name);

	List<Movie> getByIdGenre(Long genre);

	List<Movie> sortMovies(String order);

}
