package com.example.SpringSecurity.SpringSecurity.Config;


import com.example.SpringSecurity.SpringSecurity.entity.Permission;
import com.example.SpringSecurity.SpringSecurity.entity.Role;
import com.example.SpringSecurity.SpringSecurity.filters.JwtAuthFilter;
import com.example.SpringSecurity.SpringSecurity.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth->
                        auth.requestMatchers("/authenticate").permitAll()
                                //here we are controlling the access permission define in enum here we are checking if GET call and READ Access then we are allowing
                                //.requestMatchers(HttpMethod.GET,"/weather/**").hasAuthority(Permission.WEATHER_READ.name())
                                //this will only ADMIN can access bcz post and write only admin have checked enum we have defined the permission
                               // .requestMatchers(HttpMethod.POST, "/weather/**").hasAuthority(Permission.WEATHER_WRITE.name())
                                //.requestMatchers(HttpMethod.DELETE, "/weather/**").hasAuthority(Permission.WEATHER_DELETE.name())
                                .anyRequest().authenticated());
                //.httpBasic(withDefaults());
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){

     DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider(userDetailsService);
     daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
     return new ProviderManager(daoAuthenticationProvider);


    }
}
