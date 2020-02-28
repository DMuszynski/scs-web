package pl.dmuszynski.scs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "CHARACTERS")
@EntityListeners(AuditingEntityListener.class)
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_generator")
    @SequenceGenerator(name = "character_generator", sequenceName = "character_seq")
    @Column(name = "character_id", unique = true, nullable = false)
    private Long id;

    @Column(unique = true, updatable = false, nullable = false, length = 20)
    private String name;

    @NotNull
    private int score;

    @NotNull
    private int level;

    @NotNull
    private int experience;

    @NotNull
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    @JsonIgnore
    private AttributeList attributeList;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    @JsonIgnore
    private AchievementList achievementList;

    public Character() {
        this.id = 0L;
        this.name = "";
        this.score = 0;
        this.level = 1;
        this.experience = 0;

        this.user = null;
        this.created = LocalDateTime.now();
        this.attributeList = new AttributeList(this);
        this.achievementList = new AchievementList(this);
    }

    public Character(String name, User user){
        this();
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public AttributeList getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(AttributeList attributeList) {
        this.attributeList = attributeList;
    }

    public AchievementList getAchievementList() {
        return achievementList;
    }

    public void setAchievementList(AchievementList achievementList) {
        this.achievementList = achievementList;
    }
}
