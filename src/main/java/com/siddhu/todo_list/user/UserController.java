package com.siddhu.todo_list.user;


import com.siddhu.todo_list.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) throws Exception {
        userService.registerUser(userDto);
        return login(userDto);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(userDto.email(), userDto.password());
        Authentication authenticationResponse =
                authenticationManager.authenticate(authenticationRequest);
        System.out.println(authenticationResponse);
        return ResponseEntity.ok(new SuccessResponse(
                "login successful",
                authenticationResponse,
                1)
        );
    }
}
