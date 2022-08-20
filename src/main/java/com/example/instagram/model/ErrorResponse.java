package com.example.instagram.model;

public class ErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse [message=" + message + ", status=" + status + ", timeStamp=" + timeStamp + "]";
    }

}
