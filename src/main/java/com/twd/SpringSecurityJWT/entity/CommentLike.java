package com.twd.SpringSecurityJWT.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "comment_like", uniqueConstraints = { @UniqueConstraint(columnNames = {"user_seq", "cmt_id"}) })
public class CommentLike {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private int likeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cmt_id", referencedColumnName = "cmt_id")
	private Comment comment;
	
}
