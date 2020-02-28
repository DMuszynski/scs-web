package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.AchievementList;

public interface AchievementListService {
    AchievementList findByCharacterId(Long id);
}
