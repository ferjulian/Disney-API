package org.alkemy.disneyapi.service;

import java.util.List;
import org.alkemy.disneyapi.entity.Character;
import org.alkemy.disneyapi.projection.CharacterProjection;


public interface CharacterService {

	List<CharacterProjection> getCharacters();
}
