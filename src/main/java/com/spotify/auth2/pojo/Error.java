package com.spotify.auth2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {
    int status;
    String message;

    @JsonProperty("status")
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}


