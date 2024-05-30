package com.twd.SpringSecurityJWT.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto {
	private int boardId;
	private String userNickname;
    private String boardTitle;
    private String boardContent;
    private String boardImageUrl;
    private LocalDateTime boardDate;
    private int boardCntWtd;
    private int boardCntLkd;
    private int boardCntCmt;
    private boolean isLiked = false;
	
}
