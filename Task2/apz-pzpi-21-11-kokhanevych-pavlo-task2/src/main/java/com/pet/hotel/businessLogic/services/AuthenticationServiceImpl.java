package com.pet.hotel.businessLogic.services;

import com.pet.hotel.businessLogic.domain.interfaces.AuthenticationService;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.JwtAuthenticationResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.RefreshTokenRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.SignInRequest;
import com.pet.hotel.businessLogic.utils.Validation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 24 hours in milliseconds
     */
    private static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 24;

    /**
     * 7 days in milliseconds
     */
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 604800000;

    private final static String SECRET_KEY = "413F442847284862506553685660597033733676397924422645294848406351";


    @Override
    @Async
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        if (signInRequest == null) return null;
        if (!Validation.isEmailCorrect(signInRequest.getEmail()) ||
                !Validation.isPasswordCorrect(signInRequest.getPassword()))  return null;

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword())
        );

        var user = customUserDetailsServiceImpl.loadUserByUsername(signInRequest.getEmail());
        var webToken = generationToken(user);
        var refreshToken = generationRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(webToken);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }


    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigninKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String email = extractUserName(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String generationToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generationRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        String email = extractUserName(refreshToken.getToken());
        UserDetails user = customUserDetailsServiceImpl.loadUserByUsername(email);

        if (isTokenValid(refreshToken.getToken(), user)) {
            var webToken = generationToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(webToken);
            jwtAuthenticationResponse.setRefreshToken(refreshToken.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}

