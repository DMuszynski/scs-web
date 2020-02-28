package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.scs.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
    User findUserByNick(String nick);
    User findUserById(Long id);

    @Modifying
    @Query(value = "UPDATE User u SET u.active = :active WHERE u.activationCode = :activationCode")
    void updateUserActivation(@Param("active") Boolean active, @Param("activationCode") String activationCode);

    @Modifying
    @Query(value = "UPDATE User u SET u = :user")
    void updateUser(@Param("user") User user);

    @Modifying
    @Query(value = "UPDATE User u SET u.nick = :nick WHERE u.id = :id")
    void updateUserNick(@Param("nick") String nick, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE User u SET u.email = :email WHERE u.id = :id")
    void updateUserEmail(@Param("email") String email, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE User u SET u.logged = :logged WHERE u.id = :id")
    void updateUserLogged(@Param("logged") boolean logged, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE User u SET u.firstLogin = :firstLogin WHERE u.id = :id")
    void updateUserFirstLogin(@Param("firstLogin") boolean firstLogin, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updateUserPassword(@Param("password") String password, @Param("id") Long id);

    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.id = :id")
    void deleteUser(@Param("id") Long idUser);
}
