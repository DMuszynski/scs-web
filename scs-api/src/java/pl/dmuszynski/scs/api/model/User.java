package pl.dmuszynski.scs.api.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "USER")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "user_seq")
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String nick;

    @Column(unique = true, nullable = false, length = 35)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @NotNull
    private boolean active;

    @NotNull
    private boolean logged;

    @NotNull
    @Column(name = "premium_currency")
    private int premiumCurrency;

    @NotNull
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @NotNull
    @Column(name = "activation_code")
    private String activationCode;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public static final class UserBuilder {
        private Long id;
        private String nick;
        private String email;
        private String password;
        private Set<Role> roles;
        private LocalDateTime created;
        private String activationCode;
        private boolean active = false;
        private boolean logged = false;
        private int premiumCurrency = 0;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder nick(String nick) {
            this.nick = nick;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder created(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public UserBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public UserBuilder logged(boolean logged) {
            this.logged = logged;
            return this;
        }

        public UserBuilder premiumCurrency(int premiumCurrency) {
            this.premiumCurrency = premiumCurrency;
            return this;
        }

        public UserBuilder activationCode(String activationCode) {
            this.activationCode = activationCode;
            return this;
        }

        public User build() {
            if(roles.isEmpty())
                throw new IllegalStateException("roles cannot be empty");

            User user = new User();
            user.id = this.id;
            user.nick = this.nick;
            user.email = this.email;
            user.password = this.password;
            user.roles = this.roles;
            user.created = this.created;
            user.active = this.active;
            user.logged = this.logged;
            user.premiumCurrency = this.premiumCurrency;
            user.activationCode = this.activationCode;
            return user;
        }
    }

    public Long getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public int getPremiumCurrency() {
        return premiumCurrency;
    }

    public boolean isLogged() {
        return logged;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getActivationCode() {
        return activationCode;
    }
}
