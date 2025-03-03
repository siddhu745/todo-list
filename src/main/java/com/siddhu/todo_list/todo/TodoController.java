package com.siddhu.todo_list.todo;

import com.siddhu.todo_list.response.SuccessResponse;
import com.siddhu.todo_list.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    ResponseEntity<?> createTodo(
            @RequestBody TodoDTO todoDTO,
            @AuthenticationPrincipal User user
    ) {
        todoService.insertTodo(todoDTO,user);
        return ResponseEntity.ok(new SuccessResponse(
            "todo created",
            null,
            1
        ));
    }
}
