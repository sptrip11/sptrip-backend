package com.twd.SpringSecurityJWT.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {
	private int userSeq;
	private String userNickname;
	private int boardId;
	private int cmtId;
	private LocalDateTime cmtDate;
	private String cmtContent;
	private boolean isLiked = false;
	private int cmtCntLkd;
}
