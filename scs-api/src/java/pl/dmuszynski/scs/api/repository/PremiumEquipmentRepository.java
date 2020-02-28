package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.scs.api.model.PremiumEquipment;

import java.util.List;

@Repository
public interface PremiumEquipmentRepository extends JpaRepository<PremiumEquipment, Long> {
    List<PremiumEquipment> findAllByUserId(Long id);

    //void deletePremiumEquipmentById(Long id);
}
