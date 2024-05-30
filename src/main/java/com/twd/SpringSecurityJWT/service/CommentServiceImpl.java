package com.twd.SpringSecurityJWT.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.dto.CommentDto;
import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.entity.Board;
import com.twd.SpringSecurityJWT.entity.Comment;
import com.twd.SpringSecurityJWT.entity.CommentLike;
import com.twd.SpringSecurityJWT.entity.User;
import com.twd.SpringSecurityJWT.repository.BoardRepo;
import com.twd.SpringSecurityJWT.repository.CommentLikeRepo;
import com.twd.SpringSecurityJWT.repository.CommentRepo;
import com.twd.SpringSecurityJWT.repository.UserRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BoardRepo boardRepo;
	@Autowired
	private CommentLikeRepo commentLikeRepo;

	@Override
	public List<CommentDto> getComment(int userSeq, int boardId) {
		List<Comment> comments = commentRepo.findByBoardBoardId(boardId);
		Optional<User> user = userRepo.findByUserSeq(userSeq);
		return comments.stream()
				.map(comment -> {
					CommentDto commentDto = new CommentDto();
					commentDto.setBoardId(comment.getBoard().getBoardId());
					commentDto.setUserSeq(comment.getUser().getUserSeq());
					commentDto.setUserNickname(comment.getUser().getUserNickname());
					commentDto.setCmtId(comment.getCmtId());
					commentDto.setCmtContent(comment.getCmtContent());
					commentDto.setCmtDate(comment.getCmtDate());
					commentDto.setCmtCntLkd(comment.getCommentLikesCount());
					if(user.isPresent()) commentDto.setLiked(comment.isLikedByUser(user.get()));
					return commentDto;
				})
				.collect(Collectors.toList());
	}

	
	@Override
	public CommonRes postComment(CommentDto dto) {
		CommonRes res = new CommonRes();
		
		try {
			int userSeq = dto.getUserSeq();
			int boardId = dto.getBoardId();
			User user = userRepo.findByUserSeq(userSeq)
					.orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
			
			Board board = boardRepo.findByBoardId(boardId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid boardId: " + boardId));

			Comment comment = new Comment();
			comment.setUser(user);
			comment.setBoard(board);
			comment.setCmtContent(dto.getCmtContent());
			commentRepo.save(comment);
			
			res.claimSuccess("댓글 등록에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("댓글 등록에 실패했습니다.");
		}
		
		return res;
	}

	@Override
	public CommonRes patchComment(CommentDto dto, int commentId) {
		CommonRes res = new CommonRes();
		
		try {
			Comment comment = commentRepo.findByCmtId(commentId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid commentId: " + commentId));
			if(comment.getUser().getUserSeq() != dto.getUserSeq()) throw new Exception("Invalid User");
			
			comment.setCmtContent(dto.getCmtContent());
			commentRepo.save(comment);
			res.claimSuccess("댓글 수정에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("댓글 수정에 실패했습니다.");
		}
		return res;
	}

	@Override
	public CommonRes deleteComment(int userSeq, int commentId) {
		CommonRes res = new CommonRes();
		try {
			Comment comment = commentRepo.findByCmtId(commentId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid commentId: " + commentId));
			if(comment.getUser().getUserSeq() != userSeq) throw new Exception("Invalid User");
			commentRepo.delete(comment);
			res.claimSuccess("댓글 삭제에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("댓글 삭제에 실패했습니다.");
		}
		return res;
	}

	@Override
	public CommonRes createCommentLike(int userSeq, int uniqueId) {
		CommonRes res = new CommonRes();
		System.out.println("TEST => " + userSeq + " " +  uniqueId);
		try {
			User user = userRepo.findById(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
	        Comment comment = commentRepo.findByCmtId(uniqueId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid commentId: " + uniqueId));
	        
	        CommentLike commentLike = new CommentLike();
	        commentLike.setUser(user);
	        commentLike.setComment(comment);
	        commentLikeRepo.save(commentLike);
	        res.claimSuccess("저장에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("저장에 실패했습니다.");
		} 
		return res;
	}

	@Override
	public CommonRes deleteCommentLike(int userSeq, int uniqueId) {
		CommonRes res = new CommonRes();
		try {
			User user = userRepo.findById(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
	        Comment comment = commentRepo.findByCmtId(uniqueId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid commentId: " + uniqueId));
	        
	        CommentLike commentLike = commentLikeRepo.findByUserAndComment(user, comment)
	        		.orElseThrow(() -> new IllegalArgumentException("BoardLike not found for userSeq: " + userSeq + " and boardId: " + uniqueId));
	        commentLikeRepo.delete(commentLike);
	        res.claimSuccess("삭제에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("삭제에 실패했습니다.");
		} 
		return res;
	}



}