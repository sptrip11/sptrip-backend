package com.twd.SpringSecurityJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.AttrComment;
import com.twd.SpringSecurityJWT.entity.AttrCommentLike;
import com.twd.SpringSecurityJWT.entity.User;

public interface AttrCommentLikeRepo extends JpaRepository<AttrCommentLike, Integer> {
	Optional<AttrCommentLike> findByUserAndAttrComment(User user, AttrComment attrComment);

}