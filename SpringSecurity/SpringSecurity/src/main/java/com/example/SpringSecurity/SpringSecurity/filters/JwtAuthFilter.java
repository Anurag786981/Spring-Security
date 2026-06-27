package com.example.SpringSecurity.SpringSecurity.filters;

import com.example.SpringSecurity.SpringSecurity.service.CustomUserDetailsService;
import com.example.SpringSecurity.SpringSecurity.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter  extends OncePerRequestFilter {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Extract the Token from request
        String authHeader=request.getHeader("Authorization");
String token=null;
String username=null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
             username= jwtUtil.extractUserName(token);
        }
        //Validate Token
        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            //fetch User by using User name
            UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);

            //if Token is validated set inside Security Context
            if(jwtUtil.validateToken(username,userDetails,token)){
               org.springframework.security.authentication.UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                //set to Spring context
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            }


        }
        //Call the next filter

        filterChain.doFilter(request,response);


    }
}
