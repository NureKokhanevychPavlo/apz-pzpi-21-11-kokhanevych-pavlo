package com.pet.hotel.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;


@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String EXPECTED_API_KEY = "$2a$10$Upagga3BReZ1B9JgRixh7eiMAAgJRfPLJ4.qy0VnEaLL6Jyz.2g7K";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                // If the user is not authenticated, check the API key
                String apiKey = request.getHeader("X-API-Key");
                if (apiKey != null && apiKey.equals(EXPECTED_API_KEY)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(apiKey, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            // If the user is authenticated or the API key is invalid, we continue the filter chain
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
