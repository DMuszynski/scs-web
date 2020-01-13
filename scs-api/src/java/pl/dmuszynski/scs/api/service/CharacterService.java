package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.Character;

import java.util.List;

public interface CharacterService {
    List<Character> findAllByUserId(Long id);
    List<Character> findAll();

    void deleteCharacter(Long id);
}
