package com.twd.SpringSecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.User;
import com.twd.SpringSecurityJWT.entity.Wish;

public interface WishRepo extends JpaRepository<Wish, Integer> {
	Optional<Wish> findByUserAndContentId(User user, String contentId);
}