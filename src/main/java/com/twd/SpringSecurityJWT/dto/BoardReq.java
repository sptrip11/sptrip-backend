package com.twd.SpringSecurityJWT.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardReq {
	private int userSeq;
	private String boardTitle;
	private String boardContent;
	private List<String> boardImagePaths;
}
