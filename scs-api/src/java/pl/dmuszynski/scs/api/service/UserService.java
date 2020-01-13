package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.User;

public interface UserService {
    User findUserByEmail(String email);
    User findUserByNick(String nick);
    User findUserById(Long id);
    User saveUser(User u);

    void updateUserActivation(Boolean active, String activationCode);
    void updateUserNick(String nick, Long id);
    void updateUserEmail(String email, Long id);
    void updateUserPassword(String password, Long id);
    void updateUser(User user);
    void deleteUser(Long id);
}
