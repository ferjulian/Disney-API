package org.alkemy.disneyapi.service;

import java.util.List;
import java.util.Optional;

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

		return movieRepository.getAllMovies();
	}
	

	@Override
	public Movie findById(Long id) {
		
Optional<Movie> result = movieRepository.findById(id);
		
		Movie theMovie = null;
		
		if (result.isPresent()) {
			
			theMovie = result.get();
		}
		
		return theMovie;
	}

	
	@Override
	public void deleteById(Long id) {
		
		movieRepository.deleteById(id);
		
	}
	

	@Override
	public Movie addMovie(Movie movie) {
		
		return movieRepository.save(movie);
	}


	@Override
	public List<Movie> getByName(String name) {
		
		return movieRepository.findByTitle(name);
	}


	@Override
	public List<Movie> getByIdGenre(Long genre) {
		
		return movieRepository.findByGenresId(genre);
	}


	@Override
	public List<Movie> sortMovies(String order) {
		
		String result = order.toUpperCase();
		
		if(result.equals("ASC")){
			
			return movieRepository.findAllByOrderByCreationDateAsc();
		
		}else if( result.equals("DESC") ) {
			
			return movieRepository.findAllByOrderByCreationDateDesc();
		}
		
		return null;
	}

	
	

}
