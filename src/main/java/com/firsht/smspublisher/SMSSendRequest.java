package com.firsht.smspublisher;

public class SMSSendRequest {
    private String phoneNumber;

    private String message;

    public SMSSendRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    // Default initializer used for json deserialization
    public SMSSendRequest() {}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
