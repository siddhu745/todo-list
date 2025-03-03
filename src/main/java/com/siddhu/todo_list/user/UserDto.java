package com.siddhu.todo_list.user;

import jakarta.validation.constraints.*;

public record UserDto(
        @NotNull(message = "email must not be null")
        @NotBlank(message = "email must not be blank")
        @Email(message = "invalid email")
        String email,

        @NotNull(message = "password must not be null")
        @NotBlank(message = "password must not be blank")
        @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
        String password
) {
}
