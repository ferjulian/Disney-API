package org.alkemy.disneyapi.rest;

import java.net.URI;
import java.util.List;

import org.alkemy.disneyapi.entity.Movie;
import org.alkemy.disneyapi.projection.MovieProjection;
import org.alkemy.disneyapi.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {

	private final MovieService movieService;
	
	@GetMapping("/movies")
	public ResponseEntity<List<MovieProjection>> getMovies(){
		return ResponseEntity.ok().body(movieService.getMovies());
	}
	
	
	
	@GetMapping("/movie/details/{id}")
	public ResponseEntity<Movie> getMovieDetails(@PathVariable(value="id") Long id){
		
		Movie tempMovie = movieService.findById(id);

		if (tempMovie == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		
		return ResponseEntity.ok().body(tempMovie);
	}

	
	@PostMapping("/movie")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/movie").toUriString());
		
		return ResponseEntity.created(uri).body(movieService.addMovie(movie));
	}
	
	
	@DeleteMapping("/movie/{id}")
	public String removeMovie(@PathVariable(value="id") Long id){
		
		Movie tempMovie = movieService.findById(id);
		
		if(tempMovie == null) {
			
			throw new RuntimeException("Character id not found -" + id);
		}
		
		movieService.deleteById(id);
		
		return "Delete movie id" + id;
	}
	
	
	@PutMapping("/movie/{id}")
	public ResponseEntity<?> saveOrUpdateCharacter(@RequestBody Movie movie, @PathVariable(value="id") Long id ){
		
		Movie tempMovie = movieService.findById(id);
		
		if(tempMovie == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/movie").toUriString());
		
		return ResponseEntity.created(uri).body(movieService.addMovie(movie));
	}
	
}
