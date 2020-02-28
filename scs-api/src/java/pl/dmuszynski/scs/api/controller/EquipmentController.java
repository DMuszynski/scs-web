package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dmuszynski.scs.api.model.Character;
import pl.dmuszynski.scs.api.model.Equipment;
import pl.dmuszynski.scs.api.model.Item;
import pl.dmuszynski.scs.api.service.CharacterService;
import pl.dmuszynski.scs.api.service.EquipmentService;
import pl.dmuszynski.scs.api.service.ItemService;

import java.util.List;

@RestController
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final CharacterService characterService;
    private final ItemService itemService;

    @Autowired
    public EquipmentController(final EquipmentService equipmentService, final CharacterService characterService,
                               final ItemService itemService)
    {
        this.equipmentService = equipmentService;
        this.characterService = characterService;
        this.itemService = itemService;
    }

    @GetMapping(value = "/user/character/{characterId}/equipment")
    @CrossOrigin
    public List<Equipment> findAllByCharacterId(@PathVariable(value = "characterId") Long id) {
        System.out.println("JEST " + id);
        return this.equipmentService.findAllByCharacterId(id);
    }

    @PostMapping(value = "/user/character/{characterId}/equipment/item/{itemId}/create")
    @CrossOrigin
    public void createEquipmentItem(@PathVariable(value = "characterId") Long characterId, @PathVariable(value = "itemId") Long itemId) {
        try {
            Character character = characterService.findCharacterById(characterId);
            Item item = itemService.findItemById(itemId);
            Equipment equipmentItem = new Equipment(character, item);

            this.equipmentService.save(equipmentItem);
        } catch (pl.dmuszynski.scs.sb.app.exception.ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PatchMapping(value = "/user/character/equipment/update/equipped")
    @CrossOrigin
    public void updateQuestStatusCompleted(@RequestBody Equipment equipment) {
        try {
            this.equipmentService.updateEquipmentEquipped(equipment.isEquipped(), equipment.getId());
        } catch (pl.dmuszynski.scs.sb.app.exception.ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @DeleteMapping(value = "/equipment/delete/{id}")
    public void deleteEquipmentItem(@PathVariable(value = "id") final Long id) {
        this.equipmentService.deleteEquipmentById(id);
    }
}
