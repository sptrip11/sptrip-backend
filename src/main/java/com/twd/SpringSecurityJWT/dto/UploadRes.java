package com.twd.SpringSecurityJWT.dto;

import lombok.Data;


@Data
public class UploadRes {
	private String result;
    private String message;
    private String path;
    
    public void setResult(String result, String message, String path) {
    	this.result = result;
    	this.message = message;
    	this.path = path;
    }

    public void claimSuccess(String msg, String path) {
    	this.setResult("success", msg, path);
    }
    
    public void claimFail(String msg, String path) {
    	this.setResult("fail", msg, path);
    }
    
    public void claimError(String msg, String path) {
    	this.setResult("error", msg, path);
    }
    
}


