package com.dalhousie.server.model;

public class EmailDetails {
    
    private String recipient;
    private String message;
    private String subject;

    public EmailDetails() {
        
    }

    public EmailDetails(String recipient, String message, String subject) {
        this.recipient = recipient;
        this.message = message;
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
