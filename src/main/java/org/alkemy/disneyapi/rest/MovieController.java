package org.alkemy.disneyapi.rest;

import java.net.URI;
import java.util.List;

import org.alkemy.disneyapi.entity.Movie;
import org.alkemy.disneyapi.exception.MovieNotFoundException;
import org.alkemy.disneyapi.projection.MovieProjection;
import org.alkemy.disneyapi.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			throw new MovieNotFoundException("Movie id not found -" + id);
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
			
			throw new MovieNotFoundException("Movie id not found - " + id);
		}
		
		movieService.deleteById(id);
		
		return "Delete movie id " + id;
	}
	
	
	@PutMapping("/movie/{id}")
	public ResponseEntity<?> saveOrUpdateCharacter(@RequestBody Movie movie, @PathVariable(value="id") Long id ){
		
		Movie tempMovie = movieService.findById(id);
		
		if(tempMovie == null) {
			throw new MovieNotFoundException("Movie id not found - " + id);
		}
		
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/movie").toUriString());
		
		return ResponseEntity.created(uri).body(movieService.addMovie(movie));
	}
	
	
	
	@RequestMapping(value = "/movies", params = "name")
	public ResponseEntity<List<Movie>> getByName(@RequestParam String name) {

		List<Movie> result = movieService.getByName(name);

		if (result.isEmpty()) {

			throw new MovieNotFoundException("The movie " + name + " doesn't exist");
		}
		
		return ResponseEntity.ok().body(result);
	}
	
	
	@RequestMapping(value = "/movies", params = "idGenre")
	public ResponseEntity<List<Movie>> getByIdGenre(@RequestParam Long idGenre) {

		List<Movie> result = movieService.getByIdGenre(idGenre);

		if (result.isEmpty()) {

			throw new MovieNotFoundException("There are no movies for genre id - " + idGenre);
		}

		
		return ResponseEntity.ok().body(result);
	}
	
	
	@RequestMapping(value = "/movies", params = "order")
	public ResponseEntity<List<Movie>> getSortedMovies(@RequestParam String order) {

		List<Movie> result = movieService.sortMovies(order);

		if (result == null) {

			throw new MovieNotFoundException(order + " --> is not a correct parameter");
		}
		
		
		return ResponseEntity.ok().body(result);
	}

	
	

	
}
