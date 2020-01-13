package pl.dmuszynski.scs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmuszynski.scs.api.model.User;
import pl.dmuszynski.scs.api.repository.RoleRepository;
import pl.dmuszynski.scs.api.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(final BCryptPasswordEncoder encoder, final UserRepository userRepository,
                           final RoleRepository roleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByNick(String nick) {
        return this.userRepository.findUserByNick(nick);
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public void updateUserActivation(Boolean active, String activationCode) {
        this.userRepository.updateUserActivation(active, activationCode);
    }

    @Override
    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void updateUserNick(String nick, Long id) {
        this.userRepository.updateUserNick(nick, id);
    }

    @Override
    public void updateUserEmail(String email, Long id) {
        this.userRepository.updateUserEmail(email, id);
    }

    @Override
    public void updateUserPassword(String password, Long id) {
        this.userRepository.updateUserPassword(password, id);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteUser(id);
    }

    @Override
    public User saveUser(User u) {
        var savedUser = new User.UserBuilder()
            .nick(u.getNick())
            .email(u.getEmail())
            .password(this.encoder.encode(u.getPassword()))
            .activationCode(activationCodeGenerator())
            .roles(new HashSet<>(
                Collections.singletonList(
                    this.roleRepository.findByName("ROLE_USER")
                )))
            .build();

        return this.userRepository.save(savedUser);
    }

    private String activationCodeGenerator() {
        final StringBuilder activationCodeBuilder = new StringBuilder();
        final String SIGNS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int ACTIVATION_CODE_LENGTH = 32;

        Random randSign = new Random();
        for (int i = 0; i < ACTIVATION_CODE_LENGTH; ++i) {
            int index = randSign.nextInt(SIGNS.length());
            activationCodeBuilder.append(SIGNS, index, index + 1);
        }
        return activationCodeBuilder.toString();
    }
}
