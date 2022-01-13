package org.alkemy.disneyapi.service;

import java.util.List;
import java.util.Optional;

import org.alkemy.disneyapi.entity.Character;
import org.alkemy.disneyapi.projection.CharacterProjection;
import org.alkemy.disneyapi.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
	
	private final CharacterRepository characterRepository;

	@Override
	public List<CharacterProjection> getCharacters() {
		return characterRepository.getAllCharacters();
	}
	

	@Override
	public Character addCharacter(Character character) {
		
		return characterRepository.save(character);
	}

	
	@Override
	public Character findById(Long id) {
		
		Optional<Character> result = characterRepository.findById(id);
		
		Character theCharacter = null;
		
		if (result.isPresent()) {
			
			theCharacter = result.get();
		}
		
		return theCharacter;
	}


	@Override
	public void deleteById(Long id) {
		
	characterRepository.deleteById(id);
		
	}


	@Override
	public List<Character> getByName(String name) {
		
		return characterRepository.findByName(name);
	}


	@Override
	public List<Character> getByAge(int age) {
		return characterRepository.findByAge(age);
	}


	@Override
	public List<Character> getByIdMovie(Long idMovie) {
		
		return characterRepository.findByMoviesId(idMovie);
	}


	@Override
	public List<Character> getByWeight(int weight) {
		
		return characterRepository.findByWeight(weight);
	}



}
