package pl.dmuszynski.scs.api.model;

public class Email {

    private String from;
    private String to;
    private String subject;
    private String content;

    Email() { }

    Email(String from, String to, String subject, String content) {
        this.from =  from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
