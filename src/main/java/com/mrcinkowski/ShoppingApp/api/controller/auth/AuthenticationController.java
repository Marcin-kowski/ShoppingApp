package com.mrcinkowski.ShoppingApp.api.controller.auth;

import com.mrcinkowski.ShoppingApp.api.dto.LoginBody;
import com.mrcinkowski.ShoppingApp.api.dto.LoginResponse;
import com.mrcinkowski.ShoppingApp.api.dto.RegistrationBody;
import com.mrcinkowski.ShoppingApp.exception.UserAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<URI> registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userService.registerUser(registrationBody).getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            return ResponseEntity.ok(loginResponse);
            }
        }

    @GetMapping("/me")
    public LocalUserEntity getLoggedInUserProfile(@AuthenticationPrincipal LocalUserEntity user) {
        return user;
    }
}
