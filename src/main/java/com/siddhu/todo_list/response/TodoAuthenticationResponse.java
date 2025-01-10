package com.siddhu.todo_list.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TodoAuthenticationResponse {
    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    @JsonProperty
    private String token;

    public TodoAuthenticationResponse(String username, String email, String token) {
        this.username = username;
        this.email = email;
        this.token = token;
    }
}
