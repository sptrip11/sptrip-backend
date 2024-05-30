package com.twd.SpringSecurityJWT.service;

import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.RegisterReq;
import com.twd.SpringSecurityJWT.dto.UserDto;

@Service
public interface UserService {
	public CommonRes checkUserIdDuplicate(String userId);
	public CommonRes checkUserNicknameDuplicate(String userNickname);
	public CommonRes register(RegisterReq req);
	public UserDto login(UserDto userDto);
	public UserDto getProfile(String userId);
	public CommonRes patchProfile(UserDto userDto);
}
