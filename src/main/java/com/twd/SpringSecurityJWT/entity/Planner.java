package com.twd.SpringSecurityJWT.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "planner")
public class Planner {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planner_id")
    private int plannerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;

    @Column(name = "planner_name", nullable = false, length = 40)
    private String plannerName;

    @Column(name = "planner_cnt_dt")
    private Integer plannerCntDt = 1;
    @JsonIgnore
    @OneToMany(mappedBy = "planner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlannerContent> plannerContents = new ArrayList<>();
    
    
    @PrePersist
    @PreUpdate
    private void validatePlannerCntDt() {
        if (plannerCntDt == null) {
            plannerCntDt = 1;
        } else {
            plannerCntDt = Math.max(1, Math.min(plannerCntDt, 14));
        }
    }
	
}
