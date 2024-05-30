package com.twd.SpringSecurityJWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.dto.CommentDto;
import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.LikeReq;
import com.twd.SpringSecurityJWT.service.CommentService;

import jakarta.websocket.server.PathParam;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @GetMapping("/api/comments")
    public ResponseEntity<List<CommentDto>> getComment(
    		@RequestParam(value = "userSeq", defaultValue = "0") int userSeq,
			@RequestParam(value = "boardId", required = true) int boardId){
    	return ResponseEntity.ok(commentService.getComment(userSeq, boardId));
    }
    
    @PostMapping("/api/comments")
    public ResponseEntity<CommonRes> postComment(@RequestBody CommentDto dto){
    	System.out.println("Try post With" + dto.toString());
    	return ResponseEntity.ok(commentService.postComment(dto));
    }
    
    @PatchMapping("/api/comments/{commentId}")
    public ResponseEntity<CommonRes> patchComment(
    		@RequestBody CommentDto dto,
    		@PathVariable("commentId") int commentId){
    	return ResponseEntity.ok(commentService.patchComment(dto, commentId)); 
    }
    
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<CommonRes> deleteComment(
    		@RequestParam(value = "userSeq", required = true) int userSeq,
    		@PathVariable("commentId") int commentId){
    	return ResponseEntity.ok(commentService.deleteComment(userSeq, commentId)); 
    }
    
	@PostMapping("/api/comments/like")
	public ResponseEntity<CommonRes> createCommentLike(@RequestBody LikeReq likeReq){
		CommonRes res = commentService.createCommentLike(likeReq.getUserSeq(), likeReq.getUniqueId());
		return ResponseEntity.ok(res);
	}
    
	@DeleteMapping("/api/comments/like")
	public ResponseEntity<CommonRes> deleteBoardLike(
			@RequestParam(value = "userSeq", required = true) int userSeq,
			@RequestParam(value = "uniqueId", required = true) int uniqueId
			){
		CommonRes res = commentService.deleteCommentLike(userSeq, uniqueId);
		return ResponseEntity.ok(res);
	}
	
	

}
