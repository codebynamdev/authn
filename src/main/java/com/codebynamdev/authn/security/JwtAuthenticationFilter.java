package com.codebynamdev.authn.security;

import io.jsonwebtoken.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    //skipping when login request or signup
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        // ✅ Skip filter for public endpoints
        System.out.println("path " + path);
        return path.startsWith("/api/signup/create_user") || path.equals("/api/login");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("🔥 Filter processing: " + request.getMethod() + " " + request.getRequestURI());
        String header = request.getHeader("Authorization");
        System.out.println("📋 Auth header: " + (header != null ? "Present" : "Not present"));

            //check for header is notNull and in correct format
            if(header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            //Extract token from it
            String token = header.substring(7);
            //check no context set already for the token
            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                //Now Extract UserName from token in our case it is email-id
                String userName = jwtUtil.extractUsername(token);


                //Now userName should not be NULL
                if(userName != null) {
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    //set in Security Context
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
    }
}
