package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dmuszynski.scs.api.model.User;
import pl.dmuszynski.scs.api.service.UserService;
import pl.dmuszynski.scs.sb.app.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserProfileController(final UserService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping(value = "/{nick}")
    public User findUserByNick(@PathVariable(value = "nick") String nick) {
        return this.userService.findUserByNick(nick);
    }

    @PatchMapping(value = "/update/nick")
    public void updateUserNick(@RequestBody User user) {
        try {
            if(this.userService.findUserByNick(user.getNick()) != null)
                throw new ResourceNotFoundException("Podany nick jest zajęty !");

            this.userService.updateUserNick(user.getNick(), user.getId());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PatchMapping(value = "/update/email")
    public void updateUserEmail(@RequestBody User user) {
        try {
            if(this.userService.findUserByEmail(user.getEmail()) != null)
                throw new ResourceNotFoundException("Podany email jest zajęty !");

            this.userService.updateUserEmail(user.getEmail(), user.getId());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PatchMapping(value = "/update/logged")
    public void updateUserLogged(@RequestBody User user) {
        try {
            this.userService.updateUserLogged(user.isLogged(), user.getId());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PatchMapping(value = "/update/firstLogin")
    public void updateUserFirstLogin(@RequestBody User user) {
        try {
            this.userService.updateUserFirstLogin(user.isFirstLogin(), user.getId());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PatchMapping(value = "/update/password")
    public void updateUserPassword(@RequestBody User user) {
        try {
            if(this.encoder.matches(user.getPassword(), this.userService.findUserById(user.getId()).getPassword()))
                throw new ResourceNotFoundException("Podane hasło jest nieprawidłowe !");

            this.userService.updateUserPassword(encoder.encode(user.getPassword()), user.getId());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") final Long id) {
        this.userService.deleteUser(id);
    }
}

