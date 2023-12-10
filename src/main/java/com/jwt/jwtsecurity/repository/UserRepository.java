package com.jwt.jwtsecurity.repository;

import com.jwt.jwtsecurity.entities.Role;
import com.jwt.jwtsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
