package com.example.finance_web_demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@NoArgsConstructor
@ToString
public class UserDTO {
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 50, message = "Username should be between 2 and 50 characters")
    public String username;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 2, max = 100, message = "password should be between 2 and 100 characters")
    public String password;

    @Email
    @NotEmpty(message = "Email should not be empty")
    public String email;

}
