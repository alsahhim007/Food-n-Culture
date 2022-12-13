package com.dalhousie.foodnculture.models;

public class ChatModel {

    String message;
    String sender;
    String receiver;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }



    public ChatModel() {
    }


    public ChatModel(String receiver, String message, String sender) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }


}
