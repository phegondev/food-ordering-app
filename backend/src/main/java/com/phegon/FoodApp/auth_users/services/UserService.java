package com.phegon.FoodApp.auth_users.services;

import com.phegon.FoodApp.auth_users.dtos.UserDTO;
import com.phegon.FoodApp.auth_users.entity.User;
import com.phegon.FoodApp.response.Response;

import java.util.List;

public interface UserService {


    User getCurrentLoggedInUser();

    Response<List<UserDTO>> getAllUsers();

    Response<UserDTO> getOwnAccountDetails();

    Response<?> updateOwnAccount(UserDTO userDTO);

    Response<?> deactivateOwnAccount();

}
