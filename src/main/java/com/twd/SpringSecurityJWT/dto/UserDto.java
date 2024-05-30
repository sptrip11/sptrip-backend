package com.twd.SpringSecurityJWT.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	private int userSeq;
    private String userId;
    private String userPw;
    private String userEmail;
    private LocalDateTime userRegDate;
    private String userNickname;
    private String userStateMsg;
    private String userImageUrl;
    private String result;
    private String message;
    private String token;
	
}
