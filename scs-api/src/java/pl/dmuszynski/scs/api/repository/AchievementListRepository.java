package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.scs.api.model.AchievementList;

public interface AchievementListRepository extends JpaRepository<AchievementList, Long> {
    AchievementList findByCharacterId(Long id);
}
