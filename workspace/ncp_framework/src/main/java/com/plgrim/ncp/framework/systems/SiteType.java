package com.plgrim.ncp.framework.systems;

public enum SiteType {
	PcSite(true), MobileSite(false), Internal(true), Unknown(true);

	private boolean isPcSite;

	private SiteType(boolean isPcSite) {
		this.isPcSite = isPcSite;
	}

	public boolean isPcSite() {
		return isPcSite;
	}

	public boolean isMobileSite() {
		return !isPcSite;
	}
}
