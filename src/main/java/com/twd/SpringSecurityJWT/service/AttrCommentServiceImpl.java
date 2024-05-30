package com.twd.SpringSecurityJWT.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.dto.AttrCommentDto;
import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.entity.AttrComment;
import com.twd.SpringSecurityJWT.entity.AttrCommentLike;
import com.twd.SpringSecurityJWT.entity.Comment;
import com.twd.SpringSecurityJWT.entity.CommentLike;
import com.twd.SpringSecurityJWT.entity.User;
import com.twd.SpringSecurityJWT.repository.AttrCommentLikeRepo;
import com.twd.SpringSecurityJWT.repository.AttrCommentRepo;
import com.twd.SpringSecurityJWT.repository.UserRepo;

@Service
public class AttrCommentServiceImpl implements AttrCommentService{
	
	@Autowired
	private AttrCommentRepo attrCommentRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AttrCommentLikeRepo attrCommentLikeRepo;
	
	@Override
	public List<AttrCommentDto> attrCommentList(int userSeq, String contentId) {
		List<AttrComment> attrComments = attrCommentRepo.findByContentId(contentId);
		Optional<User> user = userRepo.findByUserSeq(userSeq);
		return attrComments.stream()
				.map(attrComment -> {
					AttrCommentDto attrCommentDto = new AttrCommentDto();
					attrCommentDto.setAttrCmtId(attrComment.getAttrCmtId());
					attrCommentDto.setAttrCmtStar(attrComment.getAttrCmtStar());
					attrCommentDto.setCmtCntLkd(attrComment.getAttrCommentLikesCount());
					attrCommentDto.setContentId(attrComment.getContentId());
					attrCommentDto.setAttrCmtDate(attrComment.getAttrCmtDate());
					attrCommentDto.setAttrCmtContent(attrComment.getAttrCmtContent());	
					attrCommentDto.setAttrCmtId(attrComment.getAttrCmtId());
					attrCommentDto.setUserSeq(attrComment.getUser().getUserSeq());
					attrCommentDto.setUserNickname(attrComment.getUser().getUserNickname());
					if(user.isPresent()) attrCommentDto.setLiked(attrComment.isLikedByUser(user.get()));
					return attrCommentDto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public CommonRes postAttrComment(AttrCommentDto dto) {
		CommonRes res = new CommonRes();
		
		try {
			int userSeq = dto.getUserSeq();
			User user = userRepo.findByUserSeq(userSeq)
					.orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
			

			AttrComment attrComment = new AttrComment();
			attrComment.setUser(user);
			attrComment.setContentId(dto.getContentId());
			attrComment.setAttrCmtContent(dto.getAttrCmtContent());
			attrComment.setAttrCmtStar(dto.getAttrCmtStar());
			attrCommentRepo.save(attrComment);
			
			res.claimSuccess("댓글 등록에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("댓글 등록에 실패했습니다.");
		}
		
		return res;
	}
	

	@Override
	public CommonRes attrCommentDelete(int userSeq, int attrCmtId) {
		CommonRes res = new CommonRes();
		try {
			AttrComment attrComment = attrCommentRepo.findByAttrCmtId(attrCmtId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid commentId: " + attrCmtId));
			if(attrComment.getUser().getUserSeq() != userSeq) throw new Exception("Invalid User");
			attrCommentRepo.delete(attrComment);
			res.claimSuccess("댓글 삭제에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("댓글 삭제에 실패했습니다.");
		}
		return res;
	}
	
	
	@Override
	public int attrCommentUpdate(AttrCommentDto dto) {
		return 0;
	}

	@Override
	public CommonRes createAttrCommentLike(int userSeq, int uniqueId) {
		CommonRes res = new CommonRes();
		try {
			User user = userRepo.findById(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
	        AttrComment attrComment = attrCommentRepo.findByAttrCmtId(uniqueId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid boardId: " + uniqueId));
	        
	        AttrCommentLike attrCommentLike = new AttrCommentLike();
	        attrCommentLike.setUser(user);
	        attrCommentLike.setAttrComment(attrComment);
	        attrCommentLikeRepo.save(attrCommentLike);
	        res.claimSuccess("저장에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("저장에 실패했습니다.");
		} 
		return res;
	}

	@Override
	public CommonRes deleteAttrCommentLike(int userSeq, int uniqueId) {
		CommonRes res = new CommonRes();
		try {
			User user = userRepo.findById(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
			AttrComment attrComment = attrCommentRepo.findByAttrCmtId(uniqueId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid boardId: " + uniqueId));
	        
	        AttrCommentLike attrCommentLike = attrCommentLikeRepo.findByUserAndAttrComment(user, attrComment)
	        		.orElseThrow(() -> new IllegalArgumentException("not found for userSeq: " + userSeq + " and boardId: " + uniqueId));
	        attrCommentLikeRepo.delete(attrCommentLike);
	        res.claimSuccess("삭제에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("삭제에 실패했습니다.");
		} 
		return res;
	}

}
