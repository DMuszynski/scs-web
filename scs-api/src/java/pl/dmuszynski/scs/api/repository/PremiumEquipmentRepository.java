package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.scs.api.model.PremiumEquipment;

@Repository
public interface PremiumEquipmentRepository extends JpaRepository<PremiumEquipment, Long> {
}
