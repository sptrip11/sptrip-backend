package com.twd.SpringSecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.twd.SpringSecurityJWT.entity.Board;

public interface BoardRepo extends JpaRepository<Board, Integer> {
    Page<Board> findAll(Pageable pageable);
    Optional<Board> findByBoardId(int boardId);
}