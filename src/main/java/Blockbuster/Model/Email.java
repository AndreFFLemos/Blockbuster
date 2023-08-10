package Blockbuster.Model;

import java.util.List;

public class Email {

    private String subject;
    private String body;
    private String sender;
    private List<String> receivers;

    public Email(String subject, String body, String sender, List<String> receivers) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receivers = receivers;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }
}
