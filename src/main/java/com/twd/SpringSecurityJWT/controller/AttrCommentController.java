package com.twd.SpringSecurityJWT.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.dto.AttrCommentDto;
import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.LikeReq;
import com.twd.SpringSecurityJWT.service.AttrCommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AttrCommentController {
	
	private final AttrCommentService service;
	
	@GetMapping("api/attraction/{contentId}")
	public List<AttrCommentDto> attrCommentList(
			@RequestParam(value = "userSeq", defaultValue = "0") int userSeq,
			@PathVariable(value = "contentId") String contentId
			){
		System.out.println("called attrCommentList => " + userSeq + " " + contentId);
		return service.attrCommentList(userSeq, contentId);
	}
	
	@PostMapping("api/attraction")
	public ResponseEntity<CommonRes> attrCommentInsert(@RequestBody AttrCommentDto dto) {
		System.out.println("called attrCommentInsert => " + dto.toString());
    	return ResponseEntity.ok(service.postAttrComment(dto));
	}
	
	@PutMapping("api/attraction/{attrCmtId}")
	public int attrCommentUpdate(@PathVariable(value = "attrCmtId") int attrCmtId, AttrCommentDto dto) {
		return service.attrCommentUpdate(dto);
	}
	
	@DeleteMapping("api/attraction/{attrCmtId}")
	public ResponseEntity<CommonRes> attrCommentDelete(
			@RequestParam(value = "userSeq", defaultValue = "0") int userSeq,
    		@PathVariable("attrCmtId") int attrCmtId){
		System.out.println("called attrCommentDelete => " + userSeq + " " + attrCmtId);
    	return ResponseEntity.ok(service.attrCommentDelete(userSeq, attrCmtId)); 
	}
	
	@PostMapping("/api/attraction/like")
	public ResponseEntity<CommonRes> createAttrCommentLike(@RequestBody LikeReq likeReq){
		System.out.println("createAttrCommentLike => " + likeReq.toString());
		CommonRes res = service.createAttrCommentLike(likeReq.getUserSeq(), likeReq.getUniqueId());
		return ResponseEntity.ok(res);
	}
	
	@DeleteMapping("/api/attraction/like")
	public ResponseEntity<CommonRes> deleteAttrCommentLike(
			@RequestParam(value = "userSeq", required = true) int userSeq,
			@RequestParam(value = "uniqueId", required = true) int uniqueId
			){
		System.out.println("deleteAttrCommentLike => " + userSeq + " " + uniqueId);
		CommonRes res = service.deleteAttrCommentLike(userSeq, uniqueId);
		return ResponseEntity.ok(res);
	}
	
	
}
