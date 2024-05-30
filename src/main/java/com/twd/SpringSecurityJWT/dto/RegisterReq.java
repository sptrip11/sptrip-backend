package com.twd.SpringSecurityJWT.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class RegisterReq {
	
	private String userId;
    private String userPw;
    private String userEmail;
    private String userNickname;
    private String userStateMsg;
    private MultipartFile userImageFile;
}