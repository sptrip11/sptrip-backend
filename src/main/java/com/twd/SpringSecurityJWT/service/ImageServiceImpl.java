package com.twd.SpringSecurityJWT.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.twd.SpringSecurityJWT.dto.UploadReq;
import com.twd.SpringSecurityJWT.dto.UploadRes;

@Service
public class ImageServiceImpl implements ImageService {

	
	private static final String IMAGE_DIR = "C://!DATA";
	
	@Override
	public UploadRes uploadImage(UploadReq req) {
		UploadRes res = new UploadRes();
		try {
			MultipartFile file = req.getUserImageFile();
			String originalFileName = file.getOriginalFilename(); 
			String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	        String fileName = UUID.randomUUID().toString() + fileExtension;
	        
	        String path = req.getPath();
	        if(!path.equals("/profile") && !path.equals("/gallery")) throw new Exception("Path is InCorrect");
	        String targetPath = path + "/" + fileName;
	        Path filePath = Paths.get(IMAGE_DIR, targetPath);
	        System.out.println("filePath => " + filePath.toString());
	        
	        Files.write(filePath, file.getBytes());
	        res.claimSuccess("파일 업로드 완료", targetPath);
		}
		catch (Exception e) {
			res.claimError("파일 업로드 도중 문제가 발생했습니다", "");
		}
		
		return res;
	}

	@Override
	public ResponseEntity<byte[]> getImage(String path) {
		ResponseEntity<byte[]> result = null;
		if(path == "") return result;
		try {
			String filePath = IMAGE_DIR + "/" + path;
			File file = new File(filePath);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
