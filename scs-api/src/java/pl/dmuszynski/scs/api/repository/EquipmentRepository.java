package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.scs.api.model.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByCharacterId(Long id);

    void deleteEquipmentById(Long id);

    @Modifying
    @Query(value = "UPDATE Equipment e SET e.equipped = :equipped WHERE e.id = :id")
    void updateEquipmentEquipped(@Param("equipped") boolean equipped, @Param("id") Long id);
}
