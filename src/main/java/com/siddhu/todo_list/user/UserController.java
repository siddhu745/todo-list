package com.siddhu.todo_list.user;


import com.siddhu.todo_list.response.SuccessResponse;
import com.siddhu.todo_list.response.TodoAuthenticationResponse;
import com.siddhu.todo_list.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping("/validate-token")
    String validateToken() {
        return "token validated successfully";
    }


    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) throws Exception {
        userService.registerUser(userDto);
        return login(new UserAuthDto(userDto.email(),userDto.password()));
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody UserAuthDto userAuthDto) {

        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(userAuthDto.email(), userAuthDto.password());

        Authentication authenticationResponse =
                authenticationManager.authenticate(authenticationRequest);

        String token = jwtService.generateToken(authenticationResponse,userAuthDto);

        TodoAuthenticationResponse todoAuthenticationResponse = new TodoAuthenticationResponse(
                authenticationResponse.getName(),
                userAuthDto.email(),
                token
        );

        return ResponseEntity.ok(new SuccessResponse(
                "login successful",
                todoAuthenticationResponse,
                1
        ));
    }
}
