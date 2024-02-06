package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.api.dto.LoginBody;
import com.mrcinkowski.ShoppingApp.api.dto.RegistrationBody;
import com.mrcinkowski.ShoppingApp.exception.UserAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;

public interface UserService {
    LocalUserEntity registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException;
    String loginUser(LoginBody loginBody);
}
