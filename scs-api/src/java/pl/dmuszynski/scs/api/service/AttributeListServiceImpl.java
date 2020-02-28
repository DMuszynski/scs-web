package pl.dmuszynski.scs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmuszynski.scs.api.model.AttributeList;
import pl.dmuszynski.scs.api.repository.AttributeListRepository;

@Service
@Transactional
public class AttributeListServiceImpl implements AttributeListService{

    private final AttributeListRepository attributeListRepository;

    @Autowired
    public AttributeListServiceImpl(final AttributeListRepository attributeListRepository) {
        this.attributeListRepository = attributeListRepository;
    }

    @Override
    public AttributeList findByCharacterId(Long id) {
        return this.attributeListRepository.findByCharacterId(id);
    }

    @Override
    public void updateCharacterAttributeList(int strength, int agility, int resistance, int availablePoints, Long id) {
        this.attributeListRepository.updateCharacterAttributeList(strength, agility, resistance, availablePoints, id);
    }
}
