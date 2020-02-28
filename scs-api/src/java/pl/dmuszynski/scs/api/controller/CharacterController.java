package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dmuszynski.scs.api.model.User;
import pl.dmuszynski.scs.api.service.CharacterService;
import pl.dmuszynski.scs.api.model.Character;
import pl.dmuszynski.scs.api.service.UserService;
import pl.dmuszynski.scs.sb.app.exception.ResourceNotFoundException;

import javax.security.auth.callback.CallbackHandler;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CharacterController {

    private final CharacterService characterService;
    private final UserService userService;

    @Autowired
    public CharacterController(final CharacterService characterService, final UserService userService) {
        this.characterService = characterService;
        this.userService = userService;
    }

    @GetMapping(value = "/users/characters")
    public List<Character> findAll() {
        return this.characterService.findAll();
    }

    @GetMapping(value = "/user/{id}/characters")
    public List<Character> findUserCharacter(@PathVariable(value = "id") Long id) {
        return this.characterService.findAllByUserId(id);
    }

    @GetMapping(value = "/user/characters/{id}/get")
    public Character findCharacterById(@PathVariable(value = "id") Long id) {
        return this.characterService.findCharacterById(id);
    }

    @DeleteMapping(value = "/user/characters/{id}")
    public void deleteCharacter(@PathVariable(value = "id") Long id) {
        this.characterService.deleteCharacter(id);
    }

    @PostMapping(value = "/user/{userID}/character/{characterNick}/create")
    @CrossOrigin
    public void createCharacter(@PathVariable(value = "userID") long userID,
                                @PathVariable(value = "characterNick") String characterNick) {
        try {
            User user = userService.findUserById(userID);
            Character character = new Character(characterNick, user);

            characterService.saveCharacter(character);
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PatchMapping(value = "/user/character/update")
    @CrossOrigin
    public void updateCharacter(@RequestBody Character character) {
        try {
            this.characterService.updateCharacter(character.getLevel(), character.getExperience(), character.getScore(), character.getId());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}

