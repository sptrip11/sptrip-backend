package com.twd.SpringSecurityJWT.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    Optional<Comment> findByCmtId(int cmtId);
    List<Comment> findByBoardBoardId(int boardId);
    
}