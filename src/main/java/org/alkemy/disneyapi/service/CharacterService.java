package org.alkemy.disneyapi.service;

import java.util.List;
import org.alkemy.disneyapi.entity.Character;
import org.alkemy.disneyapi.projection.CharacterProjection;


public interface CharacterService {

	List<CharacterProjection> getCharacters();

	Character addCharacter(Character character);

	Character findById(Long id);

	void deleteById(Long id);

	List<Character> getByName(String name);

	List<Character> getByAge(int age);

	List<Character> getByIdMovie(Long idMovie);

	List<Character> getByWeight(int weight);
	
	
	
}
