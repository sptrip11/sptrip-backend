package com.twd.SpringSecurityJWT.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name="planner_content", uniqueConstraints = { @UniqueConstraint(columnNames = {"planner_id", "planner_dt", "content_id"}) })
public class PlannerContent {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planner_content_id")
    private int plannerContentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planner_id", referencedColumnName = "planner_id")
    private Planner planner;
	
	@Column(name = "content_id", nullable = false, length = 10)
    private String contentId;
	
	@Column(name = "planner_dt", nullable = false, columnDefinition = "Integer default 1")
	private Integer plannerDt = 1;
	
	@Column(name = "planner_pq", nullable = false, columnDefinition = "int default 1")
    private int plannerPq = 1;
	
    @PrePersist
    public void checkPlannerDt() {
    	plannerDt = Math.max(1, Math.min(plannerDt, 14));
    }
}
