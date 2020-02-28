package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.scs.api.model.QuestStatus;

import java.util.List;

@Repository
public interface QuestStatusRepository extends JpaRepository<QuestStatus, Long> {
    List<QuestStatus> findAllByCharacterId(final Long id);

    @Modifying
    @Query(value = "UPDATE QuestStatus q SET q.completed = :completed WHERE q.id = :id")
    void updateQuestStatusCompleted(@Param("completed") boolean completed, @Param("id") Long id);
}
