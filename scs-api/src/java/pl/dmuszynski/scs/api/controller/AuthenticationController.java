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
public class AuthenticationController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public AuthenticationController(final UserService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping(value = "/login")
    @CrossOrigin
    public void authenticationUser(@RequestBody User user) {
        try {
            User userExist = this.userService.findUserByNick(user.getNick());
            if (userExist == null)
                throw new ResourceNotFoundException("Niepoprawne dane logowania !");
            if(!this.encoder.matches(user.getPassword(),userExist.getPassword()))
                throw new ResourceNotFoundException("Podane hasło jest nieprawidłowe !");
            if(!userExist.isActive())
                throw new ResourceNotFoundException("Konto nie jest aktywowane !");
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
