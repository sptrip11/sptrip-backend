package com.twd.SpringSecurityJWT.service;

import java.util.List;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.WishDto;

public interface WishService {
	public List<WishDto> wishList(int userSeq);
	public CommonRes wishInsert(int userSeq, String contentId);
	public CommonRes wishDelete(int userSeq, String contentId);
	public CommonRes wishCheck(int userSeq, String contentId);
	
}
