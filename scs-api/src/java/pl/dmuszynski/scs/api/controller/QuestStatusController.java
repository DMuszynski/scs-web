package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dmuszynski.scs.api.model.QuestStatus;
import pl.dmuszynski.scs.api.model.Character;
import pl.dmuszynski.scs.api.service.CharacterService;
import pl.dmuszynski.scs.api.service.QuestStatusService;
import pl.dmuszynski.scs.sb.app.exception.ResourceNotFoundException;

import java.util.List;

@RestController
public class QuestStatusController {

    private final QuestStatusService questStatusService;
    private final CharacterService characterService;

    @Autowired
    public QuestStatusController(final QuestStatusService questStatusService, final CharacterService characterService) {
        this.questStatusService = questStatusService;
        this.characterService = characterService;
    }

    @GetMapping(value = "/user/character/{id}/questStatus")
    @CrossOrigin
    public List<QuestStatus> findAllByCharacterId(@PathVariable(value = "id") Long id) {
        return this.questStatusService.findAllByCharacterId(id);
    }

    @PostMapping(value = "/user/character/{id}/questStatus/{number}/create")
    @CrossOrigin
    public void createQuestStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "number") int number) {
        try {
            Character character = characterService.findCharacterById(id);
            QuestStatus questStatus = new QuestStatus(number, character);

            questStatusService.saveQuestStatus(questStatus);
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PatchMapping(value = "/user/character/update/questStatus/completed")
    @CrossOrigin
    public void updateQuestStatusCompleted(@RequestBody QuestStatus questStatus) {
        try {
            this.questStatusService.updateQuestStatusCompleted(questStatus.isCompleted(), questStatus.getId());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
