package com.twd.SpringSecurityJWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.dto.BoardDto;
import com.twd.SpringSecurityJWT.dto.BoardReq;
import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.LikeReq;
import com.twd.SpringSecurityJWT.service.BoardService;

@RestController
public class BoardController {

	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/api/boards")
	public ResponseEntity<List<BoardDto>> getBoards(
			@RequestParam(value = "userId", defaultValue = "") String userId,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "6") int pageSize){
		List<BoardDto> boards = boardService.getBoards(userId, pageNo, pageSize);
	    return ResponseEntity.ok(boards);
	}
	
	@GetMapping("/api/boards/{boardId}")
	public ResponseEntity<BoardDto> getBoardByBoardId(
			@RequestParam(value = "userId", defaultValue = "") String userId,
			@PathVariable("boardId") int boardId){
			
		BoardDto board = boardService.getBoardByBoardId(userId, boardId);
		return ResponseEntity.ok(board);
	}
	
	@PostMapping("/api/boards")
	public ResponseEntity<CommonRes> createBoard(@RequestBody BoardReq boardReq){
		CommonRes res = boardService.createBoard(boardReq);
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/api/board/like")
	public ResponseEntity<CommonRes> createBoardLike(@RequestBody LikeReq likeReq){
		CommonRes res = boardService.createBoardLike(likeReq.getUserSeq(), likeReq.getUniqueId());
		return ResponseEntity.ok(res);
	}
	
	@DeleteMapping("/api/board/like")
	public ResponseEntity<CommonRes> deleteBoardLike(
			@RequestParam(value = "userSeq", required = true) int userSeq,
			@RequestParam(value = "uniqueId", required = true) int uniqueId
			){
		CommonRes res = boardService.deleteBoardLike(userSeq, uniqueId);
		return ResponseEntity.ok(res);
	}
	
	

}
