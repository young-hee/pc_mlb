package com.plgrim.ncp.framework.versions;

import lombok.Getter;

public class VersionMispatchException extends RuntimeException {
	private static final long serialVersionUID = -4908470475721217419L;

	@Getter
	long expectedVersion;

	@Getter
	long givenVersion;

	public VersionMispatchException(long expectedVersion, long givenVersion) {
		super("Version mismatch expected:" + expectedVersion + " given:" + givenVersion);
		this.expectedVersion = expectedVersion;
		this.givenVersion = givenVersion;
	}

	public VersionMispatchException(long givenVersion) {
		super("Version mismatch  given:" + givenVersion);
		this.givenVersion = givenVersion;
	}

}
