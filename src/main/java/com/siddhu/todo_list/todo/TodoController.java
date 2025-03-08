package com.siddhu.todo_list.todo;

import com.siddhu.todo_list.response.SuccessResponse;
import com.siddhu.todo_list.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    final private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    ResponseEntity<?> createTodo(
            @RequestBody TodoDTO todoDTO,
            @AuthenticationPrincipal User user
    ) {
        todoService.insertTodo(todoDTO, user);
        return ResponseEntity.ok(new SuccessResponse(
                "todo created",
                null,
                1
        ));
    }

    @GetMapping("/get")
    ResponseEntity<?> getTodos(@AuthenticationPrincipal User user) {
        List<TodoDTO> todos = todoService.getTodos(user);
        System.out.println(todos);
        return ResponseEntity.ok(new SuccessResponse(
                "data fetched successfully",
                todos,
                1
        ));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTodo(@PathVariable int id, TodoDTO todo) {
        return null;
    }

    @PatchMapping("/{id}/{state}")
    ResponseEntity<?> updateState(@PathVariable int id, @PathVariable String state) {
        System.out.println("reached controller with" + id + state);
        TodoDTO updatedTodo = todoService.updateState(id, state);
        if (updatedTodo == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(new SuccessResponse(
                "state updated successfully",
                updatedTodo,
                1
        ));
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteTodo(@RequestParam int id) {
        TodoDTO deletedTodo = todoService.deleteTodo(id);
        return ResponseEntity.ok(new SuccessResponse(
                "deleted successfully", deletedTodo, 1
        ));
    }
}
