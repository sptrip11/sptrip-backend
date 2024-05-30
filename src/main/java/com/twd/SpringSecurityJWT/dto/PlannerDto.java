package com.twd.SpringSecurityJWT.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlannerDto {
	private int plannerId;
	private int userSeq;
	private String plannerName;
	private int plannerCntDt;
}
