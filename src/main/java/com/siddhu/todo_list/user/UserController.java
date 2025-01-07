package com.siddhu.todo_list.user;


import com.siddhu.todo_list.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws Exception {
        userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse(
                "success",
                "registration successful",
                null,
                null,
                new Date()
        ));
    }
}
