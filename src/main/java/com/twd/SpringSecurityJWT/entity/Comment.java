package com.twd.SpringSecurityJWT.entity;

import java.time.LocalDateTime;
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
@Table(name = "comment")
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_id")
    private int cmtId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "board_id")
    private Board board;
	
	@Column(name = "cmt_content", nullable = false, length = 400)
    private String cmtContent;

    @Column(name = "cmt_date")
    private LocalDateTime cmtDate;
	
    @JsonIgnore
	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();
    
    public boolean isLikedByUser(User user) {
    	return commentLikes.stream()
    			.anyMatch(like -> like.getUser().equals(user));
    }
    
    public int getCommentLikesCount() {
        return commentLikes.size();
    }
    
	
    @PrePersist
    public void setCreatedCmtDate() {
        this.cmtDate = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdatedCmtDate() {
        this.cmtDate = LocalDateTime.now();
    }
    
    
}
