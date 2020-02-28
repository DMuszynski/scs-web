package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dmuszynski.scs.api.model.AttributeList;
import pl.dmuszynski.scs.api.service.AttributeListService;
import pl.dmuszynski.scs.sb.app.exception.ResourceNotFoundException;

@RestController
public class AttributeListController {

    private final AttributeListService attributeListService;

    @Autowired
    public AttributeListController(final AttributeListService attributeListService) {
        this.attributeListService = attributeListService;
    }

    @GetMapping(value = "/user/character/{id}/attributeList")
    @CrossOrigin
    public AttributeList findAttributeListByCharacterId(@PathVariable(value = "id") Long id) {
        return attributeListService.findByCharacterId(id);
    }

    @PatchMapping(value = "/user/character/update/attributeList")
    @CrossOrigin
    public void updateCharacterAttributeList(@RequestBody AttributeList attributeList) {
        try {
            attributeListService.updateCharacterAttributeList(attributeList.getStrength(), attributeList.getAgility(),
                attributeList.getResistance(), attributeList.getAvailablePoints(), attributeList.getId());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
