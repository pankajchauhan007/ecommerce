package com.ecommerce.userService.service.impl;

import com.ecommerce.userService.exceptions.UserNotFoundException;
import com.ecommerce.userService.model.ChangeStatus;
import com.ecommerce.userService.model.User;
import com.ecommerce.userService.payload.UserRequest;
import com.ecommerce.userService.payload.UserResponse;
import com.ecommerce.userService.payload.UserResponsePagination;
import com.ecommerce.userService.repository.UserRepository;
import com.ecommerce.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User addUser(UserRequest userRequest) {
        User user = User.builder()
                .userId(userRequest.getUserId())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .address(userRequest.getAddress())
                .changeStatus(ChangeStatus.ACTIVE)
                .deleted(false)
                .build();
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(int userId, UserRequest userRequest) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with userId {} " + userRequest.getUserId()));
        log.info("User not found with userId {} ", userRequest.getUserId());
        User users = User.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .address(user.getAddress())
                .changeStatus(ChangeStatus.ACTIVE)
                .deleted(false)
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

    @Override
    public User changeStatus(int userId, String changeStatus) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent()) {
            User users = user.get();
            users.setChangeStatus(ChangeStatus.valueOf(changeStatus));
            return this.userRepository.save(users);
        }

        throw new UserNotFoundException("User not found with userId {} " + userId);
    }

    @Override
    public void isDeleted(int userId) {
        Optional<User> user = this.userRepository.findById(userId);
        user.ifPresent(userFound -> {
            userFound.setDeleted(true);
            this.userRepository.save(userFound);
        });
    }

    @Override
    public List<UserResponsePagination> pagination(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pages = PageRequest.of(pageNumber, pageSize,sort);
        Page<User> userList = this.userRepository.findAll(pages);
        List<User> userListContent = userList.getContent();
        List<UserResponse> userResponseList = userListContent.stream().map((this::mapToUserResponse)).toList();
        UserResponsePagination userResponsePagination = UserResponsePagination.builder()
                .userResponseList(userResponseList)
                .pageNumber(userList.getTotalPages())
                .pageSize(userList.getSize())
                .totalElements(userList.getTotalElements())
                .totalPages(userList.getTotalPages())
                .lastPage(userList.isLast())
                .build();
        log.info("User list using pagination {} " ,userListContent);
        return Collections.singletonList(userResponsePagination);
    }


    private UserResponse mapToUserResponse(User users) {

        return UserResponse.builder()
                .userId(users.getUserId())
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getPassword())
                .address(users.getAddress())
                .changeStatus(users.getChangeStatus())
                .build();
    }
}
