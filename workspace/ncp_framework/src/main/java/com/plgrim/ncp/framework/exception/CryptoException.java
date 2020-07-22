package com.plgrim.ncp.framework.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CryptoException extends RuntimeException {
	private static final long serialVersionUID = 5463728564760510596L;

	public CryptoException(Exception e) {
		super(e);
	}

}
