package com.siddhu.todo_list.todo;

import com.siddhu.todo_list.exception.NotFoundException;
import com.siddhu.todo_list.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public void insertTodo(TodoDTO todoDTO, User user) {
        Todo todo = new Todo(
                todoDTO.name(),
                todoDTO.description(),
                "TODO",
                user
        );
        todoDao.save(todo);
    }

    public List<TodoDTO> getTodos(User user) {
        return todoDao.findByUser(user).stream().map(TodoDTO::new).toList();
    }

    private Todo getById(int id) {
        Optional<Todo> todo = todoDao.findById(id);
        if (todo.isEmpty()) {
            throw new NotFoundException("Todo with Id " + id + "not found");
        }
        return todo.get();
    }

    public void updateTodo(int id) {
        Todo todo = getById(id);

    }


    public TodoDTO updateState(int id, String state) {
        Todo todo = getById(id);
        if (!todo.getState().equals(state)) {
            todo.setState(state);
            todo = todoDao.save(todo);
            return mapTodoDto(todo);
        }
        return null;
    }

    @NotNull
    private static TodoDTO mapTodoDto(Todo todo) {
        return new TodoDTO(
                todo.getId(),
                todo.getName(),
                todo.getDescription(),
                todo.getState(),
                todo.getCAt());
    }

    public TodoDTO deleteTodo(int id) {
        Todo todo = getById(id);
        todoDao.deleteById(id);
        System.out.println("Todo with Id " + id + "deleted successfully");
        return mapTodoDto(todo);
    }

}
