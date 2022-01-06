package org.alkemy.disneyapi.service;

import java.util.List;

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

}
