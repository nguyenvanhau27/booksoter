package com.example.bookstore.config;

import com.example.bookstore.exception.CustomAuthenticationException;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.UserService;
//import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;

    private final UserRepository userRepository;
    private static final String[] WHITE_LIST_URL = {
            "/api/auth/",
            "/api/material/"};
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean flag = false;
        for(String url : WHITE_LIST_URL) {
            if (request.getServletPath().contains(url)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
//            if (request.getServletPath().contains("/api/v1/auth") || request.getServletPath().contains("/api/v1/material")) {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

//        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
//            throw new CustomAuthenticationException("Unauthorized");
//        }
        if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){

            var userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            var user = userRepository.findByEmail(userEmail).orElseThrow();

            user.build(user); //update role

            userDetails = user;

            if(jwtService.isTokenValid(jwt, userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }

        }
        filterChain.doFilter(request,response);
    }
}
