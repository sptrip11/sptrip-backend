package com.twd.SpringSecurityJWT.service;

import java.util.List;

import com.twd.SpringSecurityJWT.dto.AttrCommentDto;
import com.twd.SpringSecurityJWT.dto.CommonRes;

public interface AttrCommentService {
	List<AttrCommentDto> attrCommentList(int userSeq, String contentId);
	public CommonRes postAttrComment(AttrCommentDto dto);
	public int attrCommentUpdate(AttrCommentDto dto);
	public CommonRes attrCommentDelete(int userSeq, int attrCmtId);
	
	public CommonRes createAttrCommentLike(int userSeq, int uniqueId);
	public CommonRes deleteAttrCommentLike(int userSeq, int uniqueId);
	
}
