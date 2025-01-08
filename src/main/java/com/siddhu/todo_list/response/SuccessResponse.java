package com.siddhu.todo_list.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SuccessResponse {

    @JsonProperty
    private String status;

    @JsonProperty
    private String message;

    @JsonProperty
    private Object data;

    @JsonProperty
    private Object meta;

    @JsonProperty
    private Date timestamp;

    public SuccessResponse(String message,Object data,Object meta) {
        this.status = "success";
        this.message = message;
        this.data = data;
        this.meta = meta;
        this.timestamp = new Date();
    }
}
