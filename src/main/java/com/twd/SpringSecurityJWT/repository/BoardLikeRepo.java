package com.twd.SpringSecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.Board;
import com.twd.SpringSecurityJWT.entity.BoardLike;
import com.twd.SpringSecurityJWT.entity.User;

public interface BoardLikeRepo extends JpaRepository<BoardLike, Integer> {
	Optional<BoardLike> findByUserAndBoard(User user, Board board);
}