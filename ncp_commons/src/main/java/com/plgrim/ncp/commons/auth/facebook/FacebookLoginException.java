package com.plgrim.ncp.commons.auth.facebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacebookLoginException extends RuntimeException {
	Integer code;
	String type;
	String message; 
}
