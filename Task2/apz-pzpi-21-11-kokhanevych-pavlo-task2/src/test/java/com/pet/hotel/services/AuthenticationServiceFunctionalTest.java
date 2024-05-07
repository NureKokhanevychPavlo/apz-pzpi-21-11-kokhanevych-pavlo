package com.pet.hotel.services;

import com.pet.hotel.businessLogic.domain.interfaces.AuthenticationService;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.JwtAuthenticationResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.RefreshTokenRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.SignInRequest;
import com.pet.hotel.businessLogic.services.CustomUserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationServiceFunctionalTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;


    @Test
    public void testSignInValidCredentials() {
        // Arrange
        SignInRequest signInRequest = new SignInRequest("pasakane990@gmail.com", "Pavlo");

        // Act
        JwtAuthenticationResponse authenticationResponse = authenticationService.signIn(signInRequest);

        // Assert
        assertNotNull(authenticationResponse);
        assertNotNull(authenticationResponse.getToken());
        assertNotNull(authenticationResponse.getRefreshToken());
    }

    @Test
    public void extractUserNameReturnsEmail() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MTM5NDY3MTMsImV4cCI6MTcxMzk0ODE1M30.iBl76hZsIz6HidggTTO2QH3CLOLUO8PiFRhtVgLZdfk";
        String username = "pasakane990@gmail.com";

        String result = authenticationService.extractUserName(token);

        assertEquals(username, result);
    }

    @Test
    public void isTokenValidTest() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MTM5NDY3MTMsImV4cCI6MTcxMzk0ODE1M30.iBl76hZsIz6HidggTTO2QH3CLOLUO8PiFRhtVgLZdfk";
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "pasakane990@gmail.com",
                "1234",
                Collections.emptyList()
        );

        boolean result = authenticationService.isTokenValid(token, userDetails);

        assertTrue(result);
    }
}
