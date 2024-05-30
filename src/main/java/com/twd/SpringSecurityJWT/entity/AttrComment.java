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
@Table(name = "attr_comment")
public class AttrComment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attr_cmt_id")
    private int attrCmtId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;
	
	@Column(name = "content_id", length = 10)
    private String contentId;

    @Column(name = "attr_cmt_content", nullable = false, length = 400)
    private String attrCmtContent;

    @Column(name = "attr_cmt_date", nullable = false)
    private LocalDateTime attrCmtDate;

    @Column(name = "attr_cmt_star", nullable = false)
    private Integer attrCmtStar;
    
    @JsonIgnore
    @OneToMany(mappedBy = "attrComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttrCommentLike> attrCommentLikes = new ArrayList<>();
    
    public boolean isLikedByUser(User user) {
    	return attrCommentLikes.stream()
    			.anyMatch(like -> like.getUser().equals(user));
    }
    
    public int getAttrCommentLikesCount() {
        return attrCommentLikes.size();
    }
    
    
    
    @PrePersist
    public void setCreatedCmtDate() {
        this.attrCmtDate = LocalDateTime.now();
        if (attrCmtStar == null) {
        	attrCmtStar = 0;
        } else {
        	attrCmtStar = Math.max(0, Math.min(attrCmtStar, 5));
        }
    }
    
    @PreUpdate
    public void setUpdatedCmtDate() {
        this.attrCmtDate = LocalDateTime.now();
    }

}
