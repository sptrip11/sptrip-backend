package com.twd.SpringSecurityJWT.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRes {
	
    private String result;
    private String message;

    public void setResult(String result, String message) {
    	this.result = result;
    	this.message = message;
    }

    public void claimSuccess(String msg) {
    	this.setResult("success", msg);
    }
    
    public void claimFail(String msg) {
    	this.setResult("fail", msg);
    }
    
    public void claimError(String msg) {
    	this.setResult("error", msg);
    }
    
}
