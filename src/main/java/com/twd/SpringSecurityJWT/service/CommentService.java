package com.twd.SpringSecurityJWT.service;

import java.util.List;

import com.twd.SpringSecurityJWT.dto.CommentDto;
import com.twd.SpringSecurityJWT.dto.CommonRes;

public interface CommentService {

	public List<CommentDto> getComment(int userSeq, int boardId);
	public CommonRes postComment(CommentDto dto);
	public CommonRes patchComment(CommentDto dto, int commentId);
	public CommonRes deleteComment(int userSeq, int commentId);

	public CommonRes createCommentLike(int userSeq, int uniqueId);
	public CommonRes deleteCommentLike(int userSeq, int uniqueId);
}