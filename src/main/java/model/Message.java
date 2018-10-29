package model;

import java.io.Serializable;

public class Message implements Serializable {
    private String recepient, message;

    public Message(String recepient, String message) {
        this.recepient = recepient;
        this.message = message;
    }

    public String getRecepient() {
        return recepient;
    }

    public void setRecepient(String recepient) {
        this.recepient = recepient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

