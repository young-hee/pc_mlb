package com.plgrim.ncp.commons.auth.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NaverLoginException extends RuntimeException {
	Integer code;
	String type;
	String message; 
}

