package pl.dmuszynski.scs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Timespan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Table(name = "ACHIEVEMENT_LIST")
public class AchievementList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achievement_list_generator")
    @SequenceGenerator(name="achievement_list_generator", sequenceName = "achievement_list_seq")
    @Column(name = "achievement_list_id", unique = true, nullable = false)
    private Long id;

    @NotNull
    private int goldMedals;

    @NotNull
    private int silverMedals;

    @NotNull
    private int bronzeMedals;

    @Timespan
    private Time bestTime;

    @OneToOne
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private Character character;

    public AchievementList() {
        this.id = 0L;
        this.goldMedals = 0;
        this.silverMedals = 0;
        this.bronzeMedals = 0;

        this.bestTime = null;
        this.character = null;
    }

    public AchievementList(Character character) {
        this();
        this.character = character;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGoldMedals() {
        return goldMedals;
    }

    public void setGoldMedals(int goldMedals) {
        this.goldMedals = goldMedals;
    }

    public int getSilverMedals() {
        return silverMedals;
    }

    public void setSilverMedals(int silverMedals) {
        this.silverMedals = silverMedals;
    }

    public int getBronzeMedals() {
        return bronzeMedals;
    }

    public void setBronzeMedals(int bronzeMedals) {
        this.bronzeMedals = bronzeMedals;
    }

    public Time getBestTime() {
        return bestTime;
    }

    public void setBestTime(Time bestTime) {
        this.bestTime = bestTime;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
