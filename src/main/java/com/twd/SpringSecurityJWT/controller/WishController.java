package com.twd.SpringSecurityJWT.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.WishDto;
import com.twd.SpringSecurityJWT.service.WishService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WishController {
	
	private final WishService service;
	
	@GetMapping("api/wish/{contentId}")
	public ResponseEntity<CommonRes> wishCheck(
			@RequestParam(value = "userSeq", required = true) int userSeq,
			@PathVariable("contentId") String contentId
			){
			return ResponseEntity.ok(service.wishCheck(userSeq, contentId));
	}
	
	
	@GetMapping("api/wish")
	public List<WishDto> wishList(@RequestParam(value = "userSeq", required = true) int userSeq){
		System.out.println("called wishList =>" + userSeq);
		return service.wishList(userSeq);
	}
	
	@PostMapping("api/wish")
	public ResponseEntity<CommonRes> wishInsert(@RequestBody WishDto dto){
		return ResponseEntity.ok(service.wishInsert(dto.getUserSeq(), dto.getContentId()));
	}
	
	@DeleteMapping("api/wish")
	public ResponseEntity<CommonRes> wishDelete(
			@RequestParam(value = "userSeq", required = true) int userSeq,
			@RequestParam(value = "contentId", required = true) String contentId
			) {
		return ResponseEntity.ok(service.wishDelete(userSeq, contentId));
	}
	
}
