package com.twd.SpringSecurityJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.LikeReq;
import com.twd.SpringSecurityJWT.dto.UploadReq;
import com.twd.SpringSecurityJWT.dto.UploadRes;
import com.twd.SpringSecurityJWT.service.ImageService;

import jakarta.websocket.server.PathParam;

@RestController
public class ImageController {

	
	@Autowired
	ImageService imageService;
	
	@PostMapping("/api/image")
	public UploadRes uploadImage(@ModelAttribute UploadReq req) {
		return imageService.uploadImage(req);
	}
	
	@GetMapping("/api/image")
	public ResponseEntity<byte[]> getImage(@RequestParam(value = "path") String path){
		return imageService.getImage(path);
	}
	
}



