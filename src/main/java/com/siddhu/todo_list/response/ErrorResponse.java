package com.siddhu.todo_list.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {

    @JsonProperty
    private Date timestamp;
    @JsonProperty
    private int status;
    @JsonProperty
    private String error;
    @JsonProperty
    private String message;
    @JsonProperty
    private String path;

    public ErrorResponse(Date timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
