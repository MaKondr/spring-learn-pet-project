package com.example.finance_web_demo.controllers.rest;


import com.example.finance_web_demo.dto.UserDTO;
import com.example.finance_web_demo.models.User;
import com.example.finance_web_demo.services.UserService;
import com.example.finance_web_demo.util.user.UserErrorResponse;
import com.example.finance_web_demo.util.user.UserNotCreatedException;
import com.example.finance_web_demo.util.user.UserNotFoundException;
import com.example.finance_web_demo.util.user.UserNotUpdatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class RestUserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestUserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<UserDTO> listUsers() {
        return userService.findAllUsers().stream().map(this::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(convertUserToUserDTO(userService.findUserById(id)));
    }


    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO,
                                              BindingResult bindingResult) {
        this.hasErrors(bindingResult);

        userService.createUser(convertUserDTOToUser(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @RequestBody @Valid UserDTO userDTO,
                                              BindingResult bindingResult) {
        this.hasErrors(bindingResult);
        userService.updateUser(id, convertUserDTOToUser(userDTO));
        return ResponseEntity.ok(userDTO);

    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        System.out.println("DELETE METHOD");
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions() {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id wasn't found",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotCreatedException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions(UserNotCreatedException ex) {
        UserErrorResponse response = new UserErrorResponse(
                ex.getMessage(),
                HttpStatus.valueOf(409)
        );
        return new ResponseEntity<>(response, HttpStatus.valueOf(409));
    }

    @ExceptionHandler(UserNotUpdatedException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions(UserNotUpdatedException ex) {


        UserErrorResponse response = new UserErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    private ResponseEntity<UserErrorResponse> handleAllExceptions(SQLException ex) {
        UserErrorResponse response = new UserErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private User convertUserDTOToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertUserToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private void hasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMsg.toString());
        }
    }
}
