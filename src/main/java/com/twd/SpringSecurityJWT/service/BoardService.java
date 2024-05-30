package com.twd.SpringSecurityJWT.service;

import java.util.List;

import com.twd.SpringSecurityJWT.dto.BoardDto;
import com.twd.SpringSecurityJWT.dto.BoardReq;
import com.twd.SpringSecurityJWT.dto.CommonRes;

public interface BoardService {
	public List<BoardDto> getBoards(String userId, int pageNo, int pageSize);
	public BoardDto getBoardByBoardId(String userId, int boardId);
	public CommonRes createBoardLike(int userSeq, int uniqueId);
	public CommonRes deleteBoardLike(int userSeq, int uniqueId);
	public CommonRes createBoard(BoardReq boardReq);
}
