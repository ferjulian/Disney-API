package org.alkemy.disneyapi.rest;

import java.util.List;

import org.alkemy.disneyapi.entity.Movie;
import org.alkemy.disneyapi.projection.MovieProjection;
import org.alkemy.disneyapi.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	@GetMapping("/moviestodas")
	public ResponseEntity<List<Movie>> getTodasMovies(){
		return ResponseEntity.ok().body(movieService.getTodasMovies());
	}
}
