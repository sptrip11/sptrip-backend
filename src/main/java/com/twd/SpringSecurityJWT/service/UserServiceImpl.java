package com.twd.SpringSecurityJWT.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.RegisterReq;
import com.twd.SpringSecurityJWT.dto.UploadReq;
import com.twd.SpringSecurityJWT.dto.UploadRes;
import com.twd.SpringSecurityJWT.dto.UserDto;
import com.twd.SpringSecurityJWT.entity.User;
import com.twd.SpringSecurityJWT.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ImageService imageService;
    
    private static final String PROFILE_IMAGE_DIR = "C://!DATA/profile";
    
    public CommonRes checkUserIdDuplicate(String userId) {
    	CommonRes res = new CommonRes();
    	if(userId == null) res.claimError("잘못된 접근입니다.");
    	else {
    		Optional<User> user = userRepo.findByUserId(userId);
    		if(user.isPresent()) res.claimFail("중복된 아이디입니다.");
    		else res.claimSuccess("사용 가능한 아이디입니다.");
    	}
    	return res;
    }
    
    public CommonRes checkUserNicknameDuplicate(String userNickname) {
    	CommonRes res = new CommonRes();
    	if(userNickname == null) res.claimError("잘못된 접근입니다.");
    	else {
    		Optional<User> user = userRepo.findByUserNickname(userNickname);
    		if(user.isPresent()) res.claimFail("중복된 닉네임입니다.");
    		else res.claimSuccess("사용 가능한 닉네임입니다.");
    	}
    	return res;
    }

	@Override
	public CommonRes register(RegisterReq req) {
		CommonRes res = new CommonRes();
		
		try {
			User user = new User();
			user.setUserId(req.getUserId());
			user.setUserEmail(req.getUserEmail());
			user.setUserPw(passwordEncoder.encode(req.getUserPw()));
			user.setUserNickname(req.getUserNickname());
			user.setUserStateMsg(req.getUserStateMsg());
			System.out.println(req.getUserImageFile());
			// 프로필 이미지 파일 저장
	        if (req.getUserImageFile() != null) {
	        	System.out.println("테스트 업로드 이미지");
	            UploadReq uploadReq = new UploadReq();
	            uploadReq.setRequest("/profile", req.getUserImageFile());
	            UploadRes uploadRes = imageService.uploadImage(uploadReq);
	            if(uploadRes.getResult().equals("success")) {
	            	user.setUserImageUrl(uploadRes.getPath());
	            }
	        }
			
			User userResult = userRepo.save(user);
			if(userResult != null && userResult.getUserSeq() > 0) {
				res.claimSuccess("User Saved Successfully");
			}
		} catch (Exception e) {
			res.claimFail("User Save Failed");
		}
		
		return res;
	}
	
	@Override
	public UserDto login(UserDto userDto) {
		UserDto response = new UserDto();
        try {
            var user = userRepo.findByUserId(userDto.getUserId()).orElseThrow();
            
            if(user != null && passwordEncoder.matches(userDto.getUserPw(),user.getUserPw())) {
            	var jwt = jwtUtils.generateToken(user);
                response.setResult("success");
                response.setMessage("Successfully Login");
                response.setUserSeq(user.getUserSeq());
                response.setUserNickname(user.getUserNickname());
                response.setToken(jwt);
            } else {
            	response.setResult("fail");
            	response.setMessage("Failed to Login");
            }
        }catch (Exception e){
        	response.setResult("fail");
        	response.setMessage("Failed to Login");
        }
        return response;
	}
	
    @Override
    public UserDto getProfile(String userId) {
    	UserDto response = new UserDto();
    	try {
            var user = userRepo.findByUserId(userId).orElseThrow();
            response.setUserSeq(user.getUserSeq());
            response.setUserId(user.getUserId());
            response.setUserRegDate(user.getUserRegDate());
            response.setUserNickname(user.getUserNickname());
            response.setUserStateMsg(user.getUserStateMsg());
            response.setUserImageUrl(user.getUserImageUrl());
            response.setResult("success");
            response.setMessage("Successfully Get Profile");
        }catch (Exception e){
        	response.setResult("fail");
        	response.setMessage("Failed to Get Profile");
        }
        return response;	
    }
    
    @Override
    public CommonRes patchProfile(UserDto userDto) {
    	
    	return null;
    }
	
}
