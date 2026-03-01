package com.codebynamdev.authn.repository;

import com.codebynamdev.authn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByemail(String email);
    boolean existsByEmail(String email);
}
