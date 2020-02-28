package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dmuszynski.scs.api.model.AttributeList;

public interface AttributeListRepository extends JpaRepository<AttributeList, Long> {
    AttributeList findByCharacterId(Long id);

    @Modifying
    @Query(value = "UPDATE AttributeList a SET a.strength = :strength, a.agility = :agility, a.resistance = :resistance, a.availablePoints = :availablePoints WHERE a.id = :id")
    void updateCharacterAttributeList(@Param("strength") int strength, @Param("agility") int agility, @Param("resistance") int resistance, @Param("availablePoints") int availablePoints, @Param("id") Long id);
}
