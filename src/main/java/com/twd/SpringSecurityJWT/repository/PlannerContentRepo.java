package com.twd.SpringSecurityJWT.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.PlannerContent;

public interface PlannerContentRepo extends JpaRepository<PlannerContent, Integer> {
    List<PlannerContent> findByPlannerPlannerId(int plannerId);
    Optional<PlannerContent> findByPlannerContentId(int plannerContentId);
}