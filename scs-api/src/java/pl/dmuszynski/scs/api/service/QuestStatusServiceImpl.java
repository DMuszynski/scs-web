package pl.dmuszynski.scs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmuszynski.scs.api.model.QuestStatus;
import pl.dmuszynski.scs.api.repository.QuestStatusRepository;

import java.util.List;

@Service
public class QuestStatusServiceImpl implements QuestStatusService {

    private final QuestStatusRepository questStatusRepository;

    @Autowired
    public QuestStatusServiceImpl(final QuestStatusRepository questStatusRepository) {
        this.questStatusRepository = questStatusRepository;
    }

    @Override
    @Transactional
    public QuestStatus saveQuestStatus(QuestStatus questStatus) {
        return this.questStatusRepository.save(questStatus);
    }

    @Override
    public List<QuestStatus> findAllByCharacterId(Long id) {
        return this.questStatusRepository.findAllByCharacterId(id);
    }

    @Override
    @Transactional
    public void updateQuestStatusCompleted(boolean completed, Long id) {
        this.questStatusRepository.updateQuestStatusCompleted(completed, id);
    }
}
