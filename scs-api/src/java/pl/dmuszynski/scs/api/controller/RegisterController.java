package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import pl.dmuszynski.scs.api.model.User;
import pl.dmuszynski.scs.api.service.EmailService;
import pl.dmuszynski.scs.api.service.UserService;
import pl.dmuszynski.scs.sb.app.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {

    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public RegisterController(final UserService userService, final EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody User user) {
        try {
            if(this.userService.findUserByNick(user.getNick()) != null)
                throw new ResourceNotFoundException("Podana nazwa użytkownika jest zajęta !");
            if(this.userService.findUserByEmail(user.getEmail()) != null)
                throw new ResourceNotFoundException("Podana adres email jest zajęty !");

            User newUser = this.userService.saveUser(user);
            this.sendActivationCode(newUser.getEmail(), newUser.getActivationCode());
        } catch (ResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @RequestMapping(value = "/activatelink/{activationCode}", method = { RequestMethod.GET, RequestMethod.PATCH })
    public ResponseEntity<String> activateAccount(@PathVariable(value = "activationCode") String activationCode) {
        final String activationCorrectMessage = "<h3>Rejestracja przebiegła pomyślnie!</h3>";
        final String backToServiceLink = "<a href='http://localhost:4200'>Przejdź do serwisu</a>";
        this.userService.updateUserActivation(true, activationCode);

        return new ResponseEntity<>(activationCorrectMessage + backToServiceLink, HttpStatus.OK);
    }

    private void sendActivationCode(String userEmail, String activationCode) {
        final String emailSubject = "Potwierdzenie rejestracji konta SCS";
        final String emailContent = "Wymagane potwierdzenie rejestracji. Aby aktywować konto kliknij w poniższy link \n"
            + "http://localhost:8080/activatelink/" + activationCode;

        this.emailService.sendEmail(userEmail, emailSubject, emailContent);
    }
}
