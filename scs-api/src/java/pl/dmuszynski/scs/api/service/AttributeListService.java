package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.AttributeList;

public interface AttributeListService {
    AttributeList findByCharacterId(Long id);

    void updateCharacterAttributeList(int strength, int agility, int resistance, int availablePoints, Long id);
}
