package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE (u.email = :identifier OR u.username = :identifier OR u.phoneNumber = :identifier)")
    Optional<User> findByIdentifier(@Param("identifier") String identifier);
}
