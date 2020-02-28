package pl.dmuszynski.scs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.scs.api.model.AchievementList;
import pl.dmuszynski.scs.api.repository.AchievementListRepository;

@Service
public class AchievementListServiceImpl implements  AchievementListService{

    private final AchievementListRepository achievementListRepository;

    @Autowired
    public AchievementListServiceImpl(final AchievementListRepository achievementListRepository) {
        this.achievementListRepository = achievementListRepository;
    }

    @Override
    public AchievementList findByCharacterId(Long id) {
        return achievementListRepository.findByCharacterId(id);
    }
}
