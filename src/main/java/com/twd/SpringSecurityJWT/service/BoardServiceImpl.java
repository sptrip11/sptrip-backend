package com.twd.SpringSecurityJWT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twd.SpringSecurityJWT.dto.BoardDto;
import com.twd.SpringSecurityJWT.dto.BoardReq;
import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.entity.Board;
import com.twd.SpringSecurityJWT.entity.BoardLike;
import com.twd.SpringSecurityJWT.entity.Image;
import com.twd.SpringSecurityJWT.entity.User;
import com.twd.SpringSecurityJWT.repository.BoardLikeRepo;
import com.twd.SpringSecurityJWT.repository.BoardRepo;
import com.twd.SpringSecurityJWT.repository.ImageRepo;
import com.twd.SpringSecurityJWT.repository.UserRepo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
    private BoardRepo boardRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BoardLikeRepo boardLikeRepo;
	@Autowired
	private ImageRepo imageRepo;
	
	public List<BoardDto> getBoards(String userId, int pageNo, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "boardDate"));
        Page<Board> boardPage = boardRepo.findAll(pageable);
        Optional<User> user = userRepo.findByUserId(userId);
        return boardPage.map(board -> {
        	BoardDto boardDto = new BoardDto();
        	 boardDto.setBoardId(board.getBoardId());
             boardDto.setBoardTitle(board.getBoardTitle());
             boardDto.setBoardContent(board.getBoardContent());
             boardDto.setBoardImageUrl(board.getBoardImageUrl());
             boardDto.setBoardDate(board.getBoardDate());
             boardDto.setBoardCntWtd(board.getBoardCntWtd());
             boardDto.setBoardCntLkd(board.getBoardLikesCount());
             boardDto.setBoardCntCmt(board.getCommentsCount());
             if(user.isPresent())  boardDto.setLiked(board.isLikedByUser(user.get()));
        	 boardDto.setUserNickname(board.getUser().getUserNickname());
        	return boardDto;
        	
        }).toList();
    }
	
	public BoardDto getBoardByBoardId(String userId, int boardId) {
		BoardDto boardDto = new BoardDto();
		Optional<Board> optionalBoard = boardRepo.findByBoardId(boardId);
		Optional<User> user = userRepo.findByUserId(userId);
		if(optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			board.increaseBoardCountWtd();
			boardRepo.save(board);
			
			boardDto.setBoardId(board.getBoardId());
            boardDto.setBoardTitle(board.getBoardTitle());
            boardDto.setBoardContent(board.getBoardContent());
            boardDto.setBoardImageUrl(board.getBoardImageUrl());
            boardDto.setBoardDate(board.getBoardDate());
            boardDto.setBoardCntWtd(board.getBoardCntWtd());
            boardDto.setBoardCntLkd(board.getBoardLikesCount());
            boardDto.setBoardCntCmt(board.getCommentsCount());
            if(user.isPresent()) boardDto.setLiked(board.isLikedByUser(user.get()));
            boardDto.setUserNickname(board.getUser().getUserNickname());
		}
		return boardDto;
	}
	
	
	public CommonRes createBoardLike(int userSeq, int uniqueId) {
		CommonRes res = new CommonRes();
		try {
			User user = userRepo.findById(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
	        Board board = boardRepo.findByBoardId(uniqueId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid boardId: " + uniqueId));
	        
	        BoardLike boardLike = new BoardLike();
	        boardLike.setUser(user);
	        boardLike.setBoard(board);
	        boardLikeRepo.save(boardLike);
	        res.claimSuccess("저장에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("저장에 실패했습니다.");
		} 
		return res;
	}
	
	public CommonRes deleteBoardLike(int userSeq, int uniqueId) {
		CommonRes res = new CommonRes();
		try {
			User user = userRepo.findById(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
	        Board board = boardRepo.findByBoardId(uniqueId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid boardId: " + uniqueId));
	        BoardLike boardLike = boardLikeRepo.findByUserAndBoard(user, board)
	                .orElseThrow(() -> new IllegalArgumentException("BoardLike not found for userSeq: " + userSeq + " and boardId: " + uniqueId));
	        boardLikeRepo.delete(boardLike);
	        res.claimSuccess("삭제에 성공했습니다.");
		}catch(Exception e) {
			res.claimError("삭제에 실패했습니다.");
		} 
        return res;
    }
	
	@Transactional
	public CommonRes createBoard(BoardReq boardReq) {
		CommonRes res = new CommonRes();
		
		try {
			int userSeq = boardReq.getUserSeq();
			List<String> boardImagePaths = boardReq.getBoardImagePaths();

			User user = userRepo.findByUserSeq(userSeq)
					.orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
			
			Board board = new Board();
			board.setBoardTitle(boardReq.getBoardTitle());
			board.setUser(user);
			board.setBoardContent(boardReq.getBoardContent());
			
			Board savedBoard = boardRepo.save(board);
			
			if(!boardImagePaths.isEmpty()) {
				savedBoard.setBoardImageUrl(boardImagePaths.get(0));
				for(String s : boardImagePaths) {
					Image image = new Image();
					image.setBoard(savedBoard);
					image.setImageSrc(s);
					imageRepo.save(image);
				}
			}
			res.claimSuccess("게시글 등록에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("게시글 등록에 실패했습니다.");
		}
		return res;
	}
	
	
}
