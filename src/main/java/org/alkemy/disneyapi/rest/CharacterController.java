package org.alkemy.disneyapi.rest;

import java.net.URI;
import java.util.List;

import org.alkemy.disneyapi.entity.Character;
import org.alkemy.disneyapi.projection.CharacterProjection;
import org.alkemy.disneyapi.service.CharacterService;
import org.springframework.http.HttpStatus;
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
			
			throw new RuntimeException("Character id not found -" + id);
		}
		
		characterService.deleteById(id);
		
		return "Delete employee id" + id;
	}
	
	
	@PutMapping("/character/{id}")
	public ResponseEntity<?> saveOrUpdateCharacter(@RequestBody Character character, @PathVariable(value="id") Long id ){
		
		Character tempCharacter = characterService.findById(id);
		
		if(tempCharacter == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/character").toUriString());
		
		return ResponseEntity.created(uri).body(characterService.addCharacter(character));
	}
	
	
	@RequestMapping(value = "/characters", params = "name")
	public ResponseEntity<List<Character>> getByName(@RequestParam String name) {

		return ResponseEntity.ok().body(characterService.getByName(name));
	}
	

	@RequestMapping(value = "/characters", params = "age")
	public ResponseEntity<List<Character>> getByAge(@RequestParam int age) {

		return ResponseEntity.ok().body(characterService.getByAge(age));
	}
	
	@RequestMapping(value = "/characters", params = "idMovie")
	public ResponseEntity<List<Character>> getByIdMovie(@RequestParam Long idMovie) {

		return ResponseEntity.ok().body(characterService.getByIdMovie(idMovie));
	}


	
}
