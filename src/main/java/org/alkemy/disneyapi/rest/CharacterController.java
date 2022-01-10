package org.alkemy.disneyapi.rest;

import java.net.URI;
import java.util.List;

import org.alkemy.disneyapi.entity.Character;
import org.alkemy.disneyapi.exception.CharacterNotFoundException;
import org.alkemy.disneyapi.projection.CharacterProjection;
import org.alkemy.disneyapi.service.CharacterService;
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
public class CharacterController {

	private final CharacterService characterService;
	
	
	@GetMapping("/characters")
	public ResponseEntity<List<CharacterProjection>> getCharacters(){
		
		return ResponseEntity.ok().body(characterService.getCharacters());
	}
	
	@GetMapping("/character/details/{id}")
	public ResponseEntity<Character> getCharacterDetails(@PathVariable(value="id") Long id){
		
		Character tempCharacter = characterService.findById(id);

		if (tempCharacter == null) {
			throw new CharacterNotFoundException("Character id not found -" + id);
		}

		
		return ResponseEntity.ok().body(tempCharacter);
	}
	
	
	@PostMapping("/character")
	public ResponseEntity<Character> addCharacter(@RequestBody Character character){
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/character").toUriString());
		
		return ResponseEntity.created(uri).body(characterService.addCharacter(character));
	}
	
	
	@DeleteMapping("/character/{id}")
	public String removeCharacter(@PathVariable(value="id") Long id){
		
		Character tempCharacter = characterService.findById(id);
		
		if(tempCharacter == null) {
			
			throw new CharacterNotFoundException("Character id not found -" + id);
		}
		
		characterService.deleteById(id);
		
		return "Delete character id " + id;
	}
	
	
	@PutMapping("/character/{id}")
	public ResponseEntity<?> saveOrUpdateCharacter(@RequestBody Character character, @PathVariable(value="id") Long id ){
		
		Character tempCharacter = characterService.findById(id);
		
		if(tempCharacter == null) {
			
			throw new CharacterNotFoundException("Character id not found -" + id);
		}
		
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/character").toUriString());
		
		return ResponseEntity.created(uri).body(characterService.addCharacter(character));
	}
	
	
	@RequestMapping(value = "/characters", params = "name")
	public ResponseEntity<List<Character>> getByName(@RequestParam String name) {

		List<Character> result = characterService.getByName(name);
		
		if(result.isEmpty()) {
			
			throw new CharacterNotFoundException("The character " + name + " doesn't exist");
		}
		
		return ResponseEntity.ok().body(characterService.getByName(name));
	}
	

	@RequestMapping(value = "/characters", params = "age")
	public ResponseEntity<List<Character>> getByAge(@RequestParam int age) {
		
		List<Character> result = characterService.getByAge(age);

		if (result.isEmpty()) {

			throw new CharacterNotFoundException("There are no characters with that age");
					}

		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value = "/characters", params = "idMovie")
	public ResponseEntity<List<Character>> getByIdMovie(@RequestParam Long idMovie) {

		List<Character> result = characterService.getByIdMovie(idMovie);

		if (result.isEmpty()) {

			throw new CharacterNotFoundException("There are no characters for movie id - " + idMovie);
		}
		
		return ResponseEntity.ok().body(result);
	}


	
}
