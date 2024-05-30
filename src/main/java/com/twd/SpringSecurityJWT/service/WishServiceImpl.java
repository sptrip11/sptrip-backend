package com.twd.SpringSecurityJWT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.WishDto;
import com.twd.SpringSecurityJWT.entity.User;
import com.twd.SpringSecurityJWT.entity.Wish;
import com.twd.SpringSecurityJWT.repository.UserRepo;
import com.twd.SpringSecurityJWT.repository.WishRepo;

@Service
public class WishServiceImpl implements WishService{
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private WishRepo wishRepo;
	
	@Override
	public List<WishDto> wishList(int userSeq) {
		WishDto wishDto = new WishDto();
		Optional<User> user = userRepo.findByUserSeq(userSeq);
		if(user.isPresent()) {
			return user.get().getWishs().stream().map(
				wish -> {
					WishDto dto = new WishDto();
					dto.setUserSeq(userSeq);
					dto.setContentId(wish.getContentId());
					return dto;
				}
			).toList();
		}
		return null;
	}


	@Override
	public CommonRes wishInsert(int userSeq, String contentId) {
		CommonRes res = new CommonRes();
		try {
			Wish wish = new Wish();
			User user = userRepo.findByUserSeq(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
	        
			wish.setUser(user);
			wish.setContentId(contentId);
			wishRepo.save(wish);
	        res.claimSuccess("저장에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("저장에 실패했습니다.");
		} 
		return res;
	}

	@Override
	public CommonRes wishCheck(int userSeq, String contentId) {
		CommonRes res = new CommonRes();
		try {
			User user = userRepo.findByUserSeq(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
			Optional<Wish> wish = wishRepo.findByUserAndContentId(user, contentId);
			if(wish.isPresent()) res.claimSuccess("찜 목록에 있음");
			else res.claimFail("찜 목록에 없음");
		} catch(Error e) {
			res.claimError("일치하는 사용자가 없음");
		}
		
		return res;
	}
	
	
	@Override
	public CommonRes wishDelete(int userSeq, String contentId) {
		CommonRes res = new CommonRes();
		try {
			User user = userRepo.findByUserSeq(userSeq)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
			Wish wish = wishRepo.findByUserAndContentId(user, contentId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid"));
			wishRepo.delete(wish);
			
	        res.claimSuccess("삭제에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("삭제에 실패했습니다.");
		} 
		return res;
	}

	
	
}
