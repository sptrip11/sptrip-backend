package com.twd.SpringSecurityJWT.service;

import org.springframework.http.ResponseEntity;

import com.twd.SpringSecurityJWT.dto.UploadReq;
import com.twd.SpringSecurityJWT.dto.UploadRes;

public interface ImageService {

	public UploadRes uploadImage(UploadReq req);
	public ResponseEntity<byte[]> getImage(String path);

}
