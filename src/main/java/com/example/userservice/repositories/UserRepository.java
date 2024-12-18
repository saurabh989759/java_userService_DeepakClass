package com.example.userservice.repositories;

import com.example.userservice.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Correct import
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable pageable); // Now uses the correct Pageable type
}
