package pl.dmuszynski.scs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ATTRIBUTE_LIST")
public class AttributeList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_list_generator")
    @SequenceGenerator(name="attribute_list_generator", sequenceName = "attribute_list_seq")
    @Column(name = "attribute_list_id", unique = true, nullable = false)
    private Long id;

    @NotNull
    private int strength;

    @NotNull
    private int agility;

    @NotNull
    private int resistance;

    @NotNull
    @Column(name = "available_points")
    private int availablePoints;

    @OneToOne
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private Character character;

    public AttributeList() {
        this.id = 0L;
        this.agility = 5;
        this.strength = 5;
        this.resistance = 5;
        this.availablePoints = 0;

        this.character = null;
    }

    public AttributeList(Character character) {
        this();
        this.character = character;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }
}
