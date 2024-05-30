package com.twd.SpringSecurityJWT.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.PlannerContentDto;
import com.twd.SpringSecurityJWT.dto.PlannerDto;

public interface PlannerService {
	public List<PlannerDto> plannerList(int userSeq);
	public CommonRes plannerInsert(PlannerDto dto);
	public CommonRes plannerDelete(int userSeq, int plannerId);
	
	public List<PlannerContentDto> plannerContentList(int plannerId);
	public CommonRes plannerContentInsert(PlannerContentDto dto);
	public CommonRes plannerContentDelete(int userSeq, int plannerContentId);
}
