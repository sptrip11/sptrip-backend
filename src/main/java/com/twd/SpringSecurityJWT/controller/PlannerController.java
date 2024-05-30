package com.twd.SpringSecurityJWT.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.dto.CommonRes;
import com.twd.SpringSecurityJWT.dto.PlannerContentDto;
import com.twd.SpringSecurityJWT.dto.PlannerDto;
import com.twd.SpringSecurityJWT.service.PlannerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlannerController {
	
	private final PlannerService service;
	
	@GetMapping("api/planner")
	public List<PlannerDto> plannerList(
			@RequestParam(value = "userSeq") int userSeq){
		System.out.println("plannerList => " + userSeq);
		return service.plannerList(userSeq);
	}
	
	@PostMapping("api/planner")
	public ResponseEntity<CommonRes> plannerInsert(@RequestBody PlannerDto dto) {
		System.out.println("plannerInsert => " + dto.toString());
		return ResponseEntity.ok(service.plannerInsert(dto));
	}
	
	@DeleteMapping("api/planner")
	public ResponseEntity<CommonRes> plannerDelete(
			@RequestParam(value = "userSeq") int userSeq,
			@RequestParam(value = "plannerId") int plannerId) {
		System.out.println("plannerDelete => " + userSeq + ", " + plannerId);
		return ResponseEntity.ok(service.plannerDelete(userSeq, plannerId));
	}
	
	@GetMapping("api/planner/{plannerId}")
	public List<PlannerContentDto> plannerContentList(
			@PathVariable(value = "plannerId")int plannerId) {
		System.out.println("plannerContentList=> " + plannerId);
		return service.plannerContentList(plannerId);
		
	}
	
	@PostMapping("api/planner/content")
	public ResponseEntity<CommonRes> plannerContentInsert(@RequestBody PlannerContentDto dto) {
		System.out.println("plannerContentInsert=> " + dto.toString());
		return ResponseEntity.ok(service.plannerContentInsert(dto));
	}
	
	
	@DeleteMapping("api/planner/content")
	public ResponseEntity<CommonRes> plannerContentDelete(
			@RequestParam(value = "userSeq") int userSeq,
			@RequestParam(value = "plannerContentId") int plannerContentId) {
		System.out.println("plannerContentDelete=> " + userSeq + " "+ plannerContentId);
		return ResponseEntity.ok(service.plannerContentDelete(userSeq, plannerContentId));
	}
}
