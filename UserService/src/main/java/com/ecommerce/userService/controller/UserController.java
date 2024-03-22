package com.ecommerce.userService.controller;

import com.ecommerce.userService.model.User;
import com.ecommerce.userService.payload.UserRequest;
import com.ecommerce.userService.payload.UserResponse;
import com.ecommerce.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody UserRequest userRequest) {
        User user = this.userService.addUser(userRequest);
        log.info("User added successfully");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody UserRequest userRequest, @PathVariable int userId) {
        User updateUser = this.userService.updateUser(userId, userRequest);
        log.info("User updated successfully");
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int userId) {
        UserResponse user = this.userService.getById(userId);
        log.info("User found successfully");
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUserList() {
        List<UserResponse> userResponses = this.userService.listUsers();
        log.info("user list found successfully");
        return new ResponseEntity<>(userResponses, HttpStatus.FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        this.userService.deleteById(userId);
        log.info("User deleted successfully");
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

}
