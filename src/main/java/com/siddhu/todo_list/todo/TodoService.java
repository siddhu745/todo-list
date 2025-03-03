package com.siddhu.todo_list.todo;

import com.siddhu.todo_list.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public void insertTodo(TodoDTO todoDTO,User user) {
        Todo todo = new Todo(
             todoDTO.name(),
             todoDTO.description(),
             "TODO",
             user
        );
        todoDao.save(todo);
    }

}
