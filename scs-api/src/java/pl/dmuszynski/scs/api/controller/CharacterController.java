package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.scs.api.service.CharacterService;
import pl.dmuszynski.scs.api.model.Character;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(final CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping(value = "/users/characters")
    public List<Character> findAll() {
        return this.characterService.findAll();
    }

    @GetMapping(value = "/user/{id}/characters")
    public List<Character> findUserCharacter(@PathVariable(value = "id") Long id) {
        return this.characterService.findAllByUserId(id);
    }

    @DeleteMapping(value = "/user/characters/{id}")
    public void deleteCharacter(@PathVariable(value = "id") Long id) {
        this.characterService.deleteCharacter(id);
    }
}

