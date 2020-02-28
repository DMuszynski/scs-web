package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.PremiumEquipment;

import java.util.List;

public interface PremiumEquipmentService {
    List<PremiumEquipment> findAllByUserId(Long id);

//    void deletePremiumEquipmentById(Long id);
    PremiumEquipment save(PremiumEquipment premiumEquipment);
}
