package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.scs.api.model.Email;
import pl.dmuszynski.scs.api.service.EmailService;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public void sendContactEmail(@RequestBody Email email) {
        final String SERVICE_ADMIN_EMAIL = "scsplayer1@gmail.com";
        final String content = createContentMessage(email.getFrom(), email.getContent());

        this.emailService.sendEmail(SERVICE_ADMIN_EMAIL, email.getSubject(), content);
    }

    private String createContentMessage(String emailFrom, String content) {
        return emailFrom + " sends a message: " + content;
    }
}

