package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.dmuszynski.scs.api.model.AchievementList;
import pl.dmuszynski.scs.api.service.AchievementListService;

@RestController
public class AchievementListController {

    private final AchievementListService achievementListService;

    @Autowired
    public AchievementListController(final AchievementListService achievementListService) {
        this.achievementListService = achievementListService;
    }

    @GetMapping(value = "/user/character/{id}/achievementList")
    @CrossOrigin
    public AchievementList findAttributeListByCharacterId(@PathVariable(value = "id") Long id) {
        return achievementListService.findByCharacterId(id);
    }
}
