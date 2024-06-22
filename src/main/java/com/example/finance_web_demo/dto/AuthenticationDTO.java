package com.example.finance_web_demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 50, message = "Username should be between 2 and 50 characters")
    private String username;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 2, max = 100, message = "password should be between 2 and 100 characters")
    private String password;
}
