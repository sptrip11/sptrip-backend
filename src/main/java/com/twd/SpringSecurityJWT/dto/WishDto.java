package com.twd.SpringSecurityJWT.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WishDto {
	private int wishId;
	private int userSeq;
	private String contentId;
}
