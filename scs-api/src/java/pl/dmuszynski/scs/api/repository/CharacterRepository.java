package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.dmuszynski.scs.api.model.Character;


import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByUserId(Long idUser);
    Character findCharacterById(Long id);

    @Modifying
    @Query(value = "UPDATE Character ch SET ch.level = :level,ch.experience = :experience, ch.score = :score WHERE ch.id = :id")
    void updateCharacter(@Param("level") int level,@Param("experience") int experience, @Param("score") int score, @Param("id") Long id);

    @Modifying
    @Query(value = "DELETE FROM Character ch WHERE ch.id = :id")
    void deleteCharacter(@Param("id") Long idCharacter);
}
