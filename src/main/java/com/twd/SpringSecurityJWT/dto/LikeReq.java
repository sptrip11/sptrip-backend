package com.twd.SpringSecurityJWT.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class LikeReq {
	private int userSeq;
	private int uniqueId;
}
