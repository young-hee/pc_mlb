package com.plgrim.ncp.commons.auth.naver;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaverValidationResult implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String userId; 
	String email;
}

