package pl.dmuszynski.scs.api.service;

public interface EmailService {
    void sendEmail(final String to, final String subject, final String content);
}
