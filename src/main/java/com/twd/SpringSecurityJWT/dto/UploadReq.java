package com.twd.SpringSecurityJWT.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadReq {
	private String path;
	private MultipartFile userImageFile;
	
	public void setRequest(String path, MultipartFile userImageFile) {
		this.path = path;
		this.userImageFile = userImageFile;
	}
	
}



