package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> findAllByCharacterId(Long id);

    void save(Equipment equipment);
    void deleteEquipmentById(Long id);
    void updateEquipmentEquipped(boolean equipped, Long id);
}
