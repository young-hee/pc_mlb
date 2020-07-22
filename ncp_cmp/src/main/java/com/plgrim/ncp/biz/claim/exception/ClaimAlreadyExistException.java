package com.plgrim.ncp.biz.claim.exception;

import com.plgrim.ncp.framework.exception.NCPException;


public class ClaimAlreadyExistException extends NCPException {

	public ClaimAlreadyExistException(String[] params){
		init(params);
	}

	public ClaimAlreadyExistException(String param){
		directMessage = param;
	}

	
}
