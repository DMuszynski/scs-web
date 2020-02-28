package pl.dmuszynski.scs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmuszynski.scs.api.repository.CharacterRepository;
import pl.dmuszynski.scs.api.model.Character;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    CharacterServiceImpl(final CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<Character> findAllByUserId(Long id) {
        return this.characterRepository.findAllByUserId(id);
    }

    @Override
    public List<Character> findAll() {
        return this.characterRepository.findAll();
    }

    @Override
    public Character findCharacterById(Long id) {
        return characterRepository.findCharacterById(id);
    }

    @Override
    @Transactional
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    @Override
    @Transactional
    public void deleteCharacter(Long id) {
        this.characterRepository.deleteCharacter(id);
    }

    @Override
    @Transactional
    public void updateCharacter(int level, int experience, int score, Long id) {
        this.characterRepository.updateCharacter(level, experience, score, id);
    }
}
