package com.plgrim.ncp.framework.config.profiledcsv;

import lombok.Getter;

/**
 *  중복된프로퍼리를 발견하면 던저진다
 */
@Getter
public class DuplicatedPropertyException extends RuntimeException {
	private static final long serialVersionUID = -1617144047787829710L;
	String key;

	public DuplicatedPropertyException() {
		super("Duplicated key");
	}

	public DuplicatedPropertyException(String key) {
		super("Duplicated key: " + key);
		this.key = key;
	} 

}
