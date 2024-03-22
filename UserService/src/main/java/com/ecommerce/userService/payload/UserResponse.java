package com.ecommerce.userService.payload;

import com.ecommerce.userService.model.Address;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserResponse {
    private int userId;
    private String name;
    private String password;
    private String email;
    private List<Address> address;
}
