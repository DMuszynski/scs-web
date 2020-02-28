package pl.dmuszynski.scs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmuszynski.scs.api.model.Equipment;
import pl.dmuszynski.scs.api.repository.EquipmentRepository;

import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(final EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<Equipment> findAllByCharacterId(Long id) {
        return this.equipmentRepository.findAllByCharacterId(id);
    }

    @Override
    public void save(Equipment equipment) {
        this.equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipmentById(Long id) {
        this.equipmentRepository.deleteEquipmentById(id);
    }

    @Override
    public void updateEquipmentEquipped(boolean equipped, Long id) {
        this.equipmentRepository.updateEquipmentEquipped(equipped, id);
    }
}
