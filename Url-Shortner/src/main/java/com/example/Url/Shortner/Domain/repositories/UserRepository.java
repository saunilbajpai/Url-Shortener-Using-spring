package com.example.Url.Shortner.Domain.repositories;

import com.example.Url.Shortner.Domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
