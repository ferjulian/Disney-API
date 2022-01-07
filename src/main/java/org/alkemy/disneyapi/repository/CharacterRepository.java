package org.alkemy.disneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.alkemy.disneyapi.entity.Character;
import org.alkemy.disneyapi.projection.CharacterProjection;

public interface CharacterRepository extends JpaRepository<Character, Long> {
	
	@Query(value="Select * from characters", nativeQuery=true)
	List<CharacterProjection> getAllCharacters();

	List<Character> findByName(String name);
	
	List<Character> findByAge(int age);
	
	List<Character> findByMoviesId(Long movieId);
	
	
}
