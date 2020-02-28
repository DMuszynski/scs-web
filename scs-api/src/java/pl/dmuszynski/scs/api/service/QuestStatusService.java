package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.QuestStatus;

import java.util.List;

public interface QuestStatusService {
    QuestStatus saveQuestStatus(QuestStatus questStatus);
    List<QuestStatus> findAllByCharacterId(final Long id);

    void updateQuestStatusCompleted(boolean completed, Long id);
}
