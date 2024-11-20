package com.example.finance_web_demo.util.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
public class UserErrorResponse {
    private String message;
    private HttpStatusCode code;

}
