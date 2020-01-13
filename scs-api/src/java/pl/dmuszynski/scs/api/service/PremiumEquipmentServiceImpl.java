package pl.dmuszynski.scs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmuszynski.scs.api.model.PremiumEquipment;
import pl.dmuszynski.scs.api.repository.PremiumEquipmentRepository;

@Service
@Transactional
public class PremiumEquipmentServiceImpl implements PremiumEquipmentService{

    private final PremiumEquipmentRepository premiumEquipmentRepository;

    @Autowired
    public PremiumEquipmentServiceImpl(final PremiumEquipmentRepository premiumEquipmentRepository) {
        this.premiumEquipmentRepository = premiumEquipmentRepository;
    }


    @Override
    public PremiumEquipment save(PremiumEquipment premiumEquipment) {
        return premiumEquipmentRepository.save(premiumEquipment);
    }
}
