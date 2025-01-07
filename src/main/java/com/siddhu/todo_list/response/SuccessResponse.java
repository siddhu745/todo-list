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

    public SuccessResponse(String status,String message,Object data,Object meta,Date timestamp) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.meta = meta;
        this.timestamp = timestamp;
    }
}
