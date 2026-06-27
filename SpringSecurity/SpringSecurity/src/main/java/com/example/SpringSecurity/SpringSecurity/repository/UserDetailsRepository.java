package com.example.SpringSecurity.SpringSecurity.repository;

import com.example.SpringSecurity.SpringSecurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String userName);
}
