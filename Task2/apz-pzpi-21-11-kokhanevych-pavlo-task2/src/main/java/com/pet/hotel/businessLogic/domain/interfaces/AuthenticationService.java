package com.pet.hotel.businessLogic.domain.interfaces;

import com.pet.hotel.businessLogic.domain.transferObjects.authentication.JwtAuthenticationResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.RefreshTokenRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.SignInRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface AuthenticationService {

    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generationToken(UserDetails userDetails);

    String generationRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
