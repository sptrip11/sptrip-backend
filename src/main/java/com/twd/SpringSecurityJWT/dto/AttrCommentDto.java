package com.twd.SpringSecurityJWT.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttrCommentDto {
	private int attrCmtId;
	private int userSeq;
	private String userNickname;
	private String contentId;
	private String attrCmtContent;
	private LocalDateTime attrCmtDate;
	private int attrCmtStar;
	private boolean isLiked = false;
	private int cmtCntLkd;
}
