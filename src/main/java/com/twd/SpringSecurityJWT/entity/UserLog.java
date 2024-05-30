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
import jakarta.persistence.Table;
import lombok.Data;

@Data	
@Entity
@Table(name = "user_log")
public class UserLog {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_seq")
    private int logSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;

    @Column(name = "log_time", nullable = false)
    private LocalDateTime logTime;

    @Column(name = "code_value", length = 3)
    private String codeValue;

    @Column(name = "log_ip", length = 20)
    private String logIp;
}
