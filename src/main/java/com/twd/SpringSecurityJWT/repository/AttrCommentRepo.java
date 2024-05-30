package com.twd.SpringSecurityJWT.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.AttrComment;

public interface AttrCommentRepo extends JpaRepository<AttrComment, Integer> {
    Optional<AttrComment> findByAttrCmtId(int attrCmtId);
    List<AttrComment> findByContentId(String contentId);
    
}