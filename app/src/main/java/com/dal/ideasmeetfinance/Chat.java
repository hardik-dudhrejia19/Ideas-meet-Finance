//Class to define the structure of a message being exchanged between two users

package com.dal.ideasmeetfinance;

import java.lang.reflect.Constructor;

public class Chat {

    private String sender, receiver, message;

//    Constructor

    public Chat(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public Chat()
    {

    }

//    Getter and setter functions

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        System.out.println("Returning message from con:"+message);
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
