package org.alkemy.disneyapi.service;

import java.util.List;

import org.alkemy.disneyapi.entity.Movie;
import org.alkemy.disneyapi.projection.MovieProjection;
import org.alkemy.disneyapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepository;

	@Override
	public List<MovieProjection> getMovies() {
		// TODO Auto-generated method stub
		return movieRepository.getAllMovies();
	}

	@Override
	public List<Movie> getTodasMovies() {
		// TODO Auto-generated method stub
		return movieRepository.findAll();
	}

}
