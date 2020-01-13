package pl.dmuszynski.scs.api.model;

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

    @OneToOne
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

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
}
