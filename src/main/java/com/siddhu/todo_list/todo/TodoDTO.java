package com.siddhu.todo_list.todo;

import java.time.LocalDateTime;

public record TodoDTO(
        int id,
        String name,
        String description,
        String state,
        LocalDateTime cAt
) {
    public TodoDTO(Todo todo) {
        this(todo.getId(), todo.getName(), todo.getDescription(), todo.getState(), todo.getCAt());
    }
}
