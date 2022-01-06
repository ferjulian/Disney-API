package org.alkemy.disneyapi.service;

import java.util.List;

import org.alkemy.disneyapi.entity.Movie;
import org.alkemy.disneyapi.projection.MovieProjection;

public interface MovieService {

	List<MovieProjection> getMovies();

	List<Movie> getTodasMovies();
}
