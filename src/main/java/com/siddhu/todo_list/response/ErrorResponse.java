package com.siddhu.todo_list.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ErrorResponse {

    @JsonProperty
    String timestamp;
    @JsonProperty
    int status;
    @JsonProperty
    String error;
    @JsonProperty
    String message;
    @JsonProperty
    String path;

    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = new Date().toString();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
