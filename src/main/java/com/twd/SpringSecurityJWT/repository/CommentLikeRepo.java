package com.twd.SpringSecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.Comment;
import com.twd.SpringSecurityJWT.entity.CommentLike;
import com.twd.SpringSecurityJWT.entity.User;

public interface CommentLikeRepo extends JpaRepository<CommentLike, Integer> {
	Optional<CommentLike> findByUserAndComment(User user, Comment comment);

}