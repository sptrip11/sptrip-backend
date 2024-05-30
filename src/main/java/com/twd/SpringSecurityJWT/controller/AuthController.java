package com.twd.SpringSecurityJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.RegisterReq;
import com.twd.SpringSecurityJWT.dto.UserDto;
import com.twd.SpringSecurityJWT.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/api/register")
    public ResponseEntity<CommonRes> register(@ModelAttribute RegisterReq req){
    	return ResponseEntity.ok(userService.register(req));
    }
    
    @GetMapping("/api/check/id")
    public ResponseEntity<CommonRes> checkUserIdDuplicate(@RequestParam(value = "userId", required = false) String userId){
    	return ResponseEntity.ok(userService.checkUserIdDuplicate(userId));
    }
    
    @GetMapping("/api/check/nickname")
    public ResponseEntity<CommonRes> checkUserNicknameDuplicate(@RequestParam(value = "userNickname", required = false) String userNickname){
    	return ResponseEntity.ok(userService.checkUserNicknameDuplicate(userNickname));
    }
    
    @PostMapping("/api/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto){
    	return ResponseEntity.ok(userService.login(userDto));
    }
    
    @GetMapping("/api/profile/{userId}")
    public ResponseEntity<UserDto> getProfile(@PathVariable("userId") String userId){
    	return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PatchMapping("/api/profile")
    public ResponseEntity<CommonRes> patchProfile(UserDto userDto){
    	return ResponseEntity.ok(userService.patchProfile(userDto));
    }

}
