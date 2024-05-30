package com.twd.SpringSecurityJWT.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.PlannerContentDto;
import com.twd.SpringSecurityJWT.dto.PlannerDto;
import com.twd.SpringSecurityJWT.entity.Planner;
import com.twd.SpringSecurityJWT.entity.PlannerContent;
import com.twd.SpringSecurityJWT.entity.User;
import com.twd.SpringSecurityJWT.repository.PlannerContentRepo;
import com.twd.SpringSecurityJWT.repository.PlannerRepo;
import com.twd.SpringSecurityJWT.repository.UserRepo;

@Service
public class PlannerServiceImpl implements PlannerService{
	
	@Autowired
	private PlannerRepo plannerRepo;
	
	@Autowired
	private PlannerContentRepo plannerContentRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public List<PlannerDto> plannerList(int userSeq) {
		return plannerRepo.findByUserUserSeq(userSeq).stream()
				.map(planner -> {
					PlannerDto plannerDto = new PlannerDto();
					plannerDto.setPlannerId(planner.getPlannerId());
					plannerDto.setPlannerCntDt(planner.getPlannerCntDt());
					plannerDto.setPlannerName(planner.getPlannerName());
					plannerDto.setUserSeq(userSeq);
					return plannerDto;
				})
				.collect(Collectors.toList());
	}


	@Override
	public CommonRes plannerInsert(@RequestBody PlannerDto dto) {
		CommonRes res = new CommonRes();
		try {
			Planner planner = new Planner();
			int userSeq = dto.getUserSeq();
			User user = userRepo.findByUserSeq(userSeq)
					.orElseThrow(() -> new IllegalArgumentException("Invalid userSeq: " + userSeq));
			planner.setUser(user);
			planner.setPlannerName(dto.getPlannerName());
			planner.setPlannerCntDt(dto.getPlannerCntDt());
			plannerRepo.save(planner);
			res.claimSuccess("플래너 생성 완료" );
		} catch (Exception e) {
			res.claimFail("플래너 생성 실패");
		}
		return res;
	}

	@Override
	public CommonRes plannerDelete(int userSeq, int plannerId) {
		CommonRes res = new CommonRes();
		try {
			Planner planner = plannerRepo.findByPlannerId(plannerId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid plannerId: " + plannerId));
			if(planner.getUser().getUserSeq() != userSeq) throw new Exception("Invalid User");
			plannerRepo.delete(planner);
			res.claimSuccess("플래너 삭제에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("플래너 삭제에 실패했습니다.");
		}
		return res;
	}

	@Override
	public List<PlannerContentDto> plannerContentList(int plannerId) {
		return plannerContentRepo.findByPlannerPlannerId(plannerId).stream().map(
				content -> {
					PlannerContentDto dto = new PlannerContentDto();
					dto.setContentId(content.getContentId());
					dto.setPlannerContentId(content.getPlannerContentId());
					dto.setPlannerDt(content.getPlannerDt());
					dto.setPlannerId(plannerId);
					dto.setPlannerPq(content.getPlannerPq());
					return dto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public CommonRes plannerContentInsert(PlannerContentDto dto) {
		CommonRes res = new CommonRes();
		try {
			PlannerContent content = new PlannerContent();
			
			int plannerId = dto.getPlannerId();
			Planner planner = plannerRepo.findByPlannerId(plannerId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid plannerId: " + plannerId));
			content.setPlanner(planner);
			content.setContentId(dto.getContentId());
			content.setPlannerDt(dto.getPlannerDt());
			plannerContentRepo.save(content);
			res.claimSuccess("플래너 항목 추가 성공");
		} catch(Exception e) {
			res.claimError("플래너 항목 추가 실패");
		}
		return res;
	}

	@Override
	public CommonRes plannerContentDelete(int userSeq, int plannerContentId) {
		CommonRes res = new CommonRes();
		try {
			PlannerContent content = plannerContentRepo.findByPlannerContentId(plannerContentId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid plannerContentId: " + plannerContentId));
			if(content.getPlanner().getUser().getUserSeq() != userSeq) throw new Exception("Invalid User");
			plannerContentRepo.delete(content);
			res.claimSuccess("플래너 항목 삭제에 성공했습니다.");
		} catch(Exception e) {
			res.claimError("플래너 항목 삭제에 실패했습니다.");
		}
		return res;
	}

	
	
	

}
