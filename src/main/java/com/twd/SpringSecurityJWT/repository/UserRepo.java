package com.twd.SpringSecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserSeq(int userSeq);
    Optional<User> findByUserNickname(String userNickname);
}