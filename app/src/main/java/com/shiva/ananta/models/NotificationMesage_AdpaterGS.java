package com.shiva.ananta.models;

public class NotificationMesage_AdpaterGS {
    String message;
    String time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public NotificationMesage_AdpaterGS(String message, String time) {
        this.message = message;
        this.time = time;
    }
}
