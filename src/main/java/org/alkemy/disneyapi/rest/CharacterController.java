package org.alkemy.disneyapi.rest;

import java.util.List;

import org.alkemy.disneyapi.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.alkemy.disneyapi.entity.Character;
import org.alkemy.disneyapi.projection.CharacterProjection;

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
}
