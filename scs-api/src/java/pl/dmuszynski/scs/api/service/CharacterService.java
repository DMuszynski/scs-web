package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.Character;

import java.util.List;

public interface CharacterService {
    List<Character> findAllByUserId(Long id);
    List<Character> findAll();

    Character findCharacterById(Long id);
    Character saveCharacter(Character character);

    void deleteCharacter(Long id);
    void updateCharacter(int level, int experience, int score, Long id);
}
