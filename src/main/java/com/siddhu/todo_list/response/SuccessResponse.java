package com.siddhu.todo_list.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class SuccessResponse {

    @JsonProperty
    String status;

    @JsonProperty
    String message;

    @JsonProperty
    Object data;

    @JsonProperty
    Object meta;

    @JsonProperty
    Date timestamp;

    public SuccessResponse(String message,Object data,Object meta) {
        this.status = "success";
        this.message = message;
        this.data = data;
        this.meta = meta;
        this.timestamp = new Date();
    }
}
