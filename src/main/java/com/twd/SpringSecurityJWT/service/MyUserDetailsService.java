package com.twd.SpringSecurityJWT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.repository.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    
    ///import org.springframework.security.core.userdetails.User;를 생성해서 구현해보자 다음에는 기필코
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUserId(username).orElseThrow();
    }
    
    
    
}