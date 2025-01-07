package com.siddhu.todo_list.user;

public record UserDto(
        String username,
        String email,
        String password
) {
}
