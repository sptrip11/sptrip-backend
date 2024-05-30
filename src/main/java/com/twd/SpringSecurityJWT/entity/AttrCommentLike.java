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
@Table(name = "attr_comment_like", uniqueConstraints = { @UniqueConstraint(columnNames = {"user_seq", "attr_cmt_id"}) })
public class AttrCommentLike {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "like_id")
    private int likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attr_cmt_id", referencedColumnName = "attr_cmt_id")
    private AttrComment attrComment;
}