package pl.dmuszynski.scs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "QUEST_STATUS")
public class QuestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quest_status_generator")
    @SequenceGenerator(name="quest_status_generator", sequenceName = "quest_status_seq")
    @Column(name = "quest_status_id", unique = true, nullable = false)
    private Long id;

    @NotNull
    private int number;

    @NotNull
    private boolean taken;

    @NotNull
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "character_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Character character;

    public QuestStatus() {
        this.id = 0L;
        this.number = 0;
        this.taken = true;
        this.completed = false;

        this.character = null;
    }

    public QuestStatus(int number, Character character) {
        this();

        this.number = number;
        this.character = character;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
