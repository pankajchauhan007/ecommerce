package com.ecommerce.userService.service.impl;

import com.ecommerce.userService.exceptions.UserNotFoundException.UserNotFoundException;
import com.ecommerce.userService.model.User;
import com.ecommerce.userService.payload.UserRequest;
import com.ecommerce.userService.payload.UserResponse;
import com.ecommerce.userService.repository.UserRepository;
import com.ecommerce.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private  final UserRepository userRepository;
    @Override
    public User addUser(UserRequest userRequest) {
        User user = User.builder()
                .userId(userRequest.getUserId())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .address(userRequest.getAddress())
                .build();
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(int userId, UserRequest userRequest) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with userId {} " + userRequest.getUserId()));
        log.info("User not found with userId {} " , userRequest.getUserId());
        User users = User.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .address(user.getAddress())
                .build();
        return this.userRepository.save(users);
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> userList = this.userRepository.findAll();
        return userList.stream().map(this::mapToUserResponse).toList();
    }

    @Override
    public UserResponse getById(int userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with userId {} " + userId));
        return mapToUserResponse(user);
    }

    @Override
    public void deleteById(int userId) {
        this.userRepository.deleteById(userId);
    }

    private UserResponse mapToUserResponse(User users) {

        return UserResponse.builder()
                .userId(users.getUserId())
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getPassword())
                .address(users.getAddress())
                .build();
    }
}
