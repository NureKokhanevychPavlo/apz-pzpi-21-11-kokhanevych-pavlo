package com.pet.hotel.presentation.controllers;

import com.pet.hotel.businessLogic.domain.interfaces.AuthenticationService;
import com.pet.hotel.businessLogic.domain.interfaces.UserService;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.JwtAuthenticationResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.RefreshTokenRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.SignInRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    @Async
    public ResponseEntity<JwtAuthenticationResponse> singIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    @Async
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/register")
    @Async
    public ResponseEntity<Void> registerUser(@RequestBody UserDto user, @RequestBody MultipartFile file) {
        if (userService.saveUser(user, file)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
