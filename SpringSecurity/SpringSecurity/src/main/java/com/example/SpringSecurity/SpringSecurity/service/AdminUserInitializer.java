package com.example.SpringSecurity.SpringSecurity.service;

import com.example.SpringSecurity.SpringSecurity.entity.Role;
import com.example.SpringSecurity.SpringSecurity.entity.Users;
import com.example.SpringSecurity.SpringSecurity.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UserDetailsRepository userRepository, PasswordEncoder passwordEncoder){
        return args->{
            if (userRepository.findByUsername("admin").isEmpty()){
                Users admin= new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Default admin User created !");
            }
            if (userRepository.findByUsername("username").isEmpty()){
                Users admin= new Users();
                admin.setUsername("user");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole(Role.USER);
                userRepository.save(admin);
                System.out.println("Default admin User created !");
            }
    };

    }

}
