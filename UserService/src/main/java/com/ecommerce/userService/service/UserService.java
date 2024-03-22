package com.ecommerce.userService.service;

import com.ecommerce.userService.model.ChangeStatus;
import com.ecommerce.userService.model.User;
import com.ecommerce.userService.payload.UserRequest;
import com.ecommerce.userService.payload.UserResponse;

import java.util.List;

public interface UserService {

    //add user
    User addUser(UserRequest userRequest);

    //update user
    User updateUser(int userId, UserRequest userRequest);

    //get all user
    List<UserResponse> listUsers();

    //get by id
    UserResponse getById(int userId);

    //delete
    void deleteById(int userId);

    //change status
    User changeStatus(int userId, ChangeStatus changeStatus);
    void isDeleted(int userId);
}
