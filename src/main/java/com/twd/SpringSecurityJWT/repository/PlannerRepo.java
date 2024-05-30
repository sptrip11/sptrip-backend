package com.twd.SpringSecurityJWT.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.Comment;
import com.twd.SpringSecurityJWT.entity.Planner;

public interface PlannerRepo extends JpaRepository<Planner, Integer> {
    List<Planner> findByUserUserSeq(int userSeq);
    Optional<Planner> findByPlannerId(int plannerId);
}