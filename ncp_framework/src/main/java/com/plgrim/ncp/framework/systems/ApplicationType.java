package com.plgrim.ncp.framework.systems;

import com.plgrim.ncp.framework.systems.apps.*;
import org.springframework.core.env.Environment;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ApplicationType {
	//@formatter:off
	NCP_FRONT_PC					(NcpPC.SYMBOL, 						NcpPC.PROFILE_NAME, 				NcpPC.MALL_ID,  			NcpPC.SITE_TYPE),
	NCP_FRONT_MOBILE				(NcpMB.SYMBOL, 						NcpMB.PROFILE_NAME, 				NcpMB.MALL_ID,  			NcpMB.SITE_TYPE),
	NCP_FRONT_MLB_PC				(NcpMLBPC.SYMBOL, 					NcpMLBPC.PROFILE_NAME, 				NcpMLBPC.MALL_ID,  			NcpMLBPC.SITE_TYPE),
	NCP_FRONT_MLB_MOBILE			(NcpMLBMB.SYMBOL, 					NcpMLBMB.PROFILE_NAME, 				NcpMLBMB.MALL_ID,  			NcpMLBMB.SITE_TYPE),
	NCP_FRONT_SA_PC					(NcpSAPC.SYMBOL, 					NcpSAPC.PROFILE_NAME, 				NcpSAPC.MALL_ID,  			NcpSAPC.SITE_TYPE),
	NCP_FRONT_SA_MOBILE				(NcpSAMB.SYMBOL, 					NcpSAMB.PROFILE_NAME, 				NcpSAMB.MALL_ID,  			NcpSAMB.SITE_TYPE),

	GF_FRONT_PC						(GfPC.SYMBOL, 						GfPC.PROFILE_NAME, 					GfPC.MALL_ID,  				GfPC.SITE_TYPE),
	GF_FRONT_MOBILE					(GfMB.SYMBOL, 						GfMB.PROFILE_NAME, 					GfMB.MALL_ID,  				GfMB.SITE_TYPE),

	BACK_OFFICE						(BackOffice.SYMBOL, 				BackOffice.PROFILE_NAME),
	INTERNAL_INTERFACES				(InternalInterfarceServer.SYMBOL, 	InternalInterfarceServer.PROFILE_NAME),
	EXTERNAL_INTERFACES				(ExternalInterfarceServer.SYMBOL, 	ExternalInterfarceServer.PROFILE_NAME),
	BATCH							(Batch.SYMBOL, 						Batch.PROFILE_NAME),
	TUTORIAL						(Tutorial.SYMBOL, 					Tutorial.PROFILE_NAME),
	UNKNOWN							("UN", 								"UNKNOWN");
	//@formatter:on

	@Getter
	private String mallId;

	@Getter
	private String symbol;

	@Getter
	private String systemId;
	
	@Getter
	private SiteType siteType;

	private ApplicationType(String twoLetterSymbol, String systemId) {
		this(twoLetterSymbol, systemId, null, SiteType.Internal);
	}

	private ApplicationType(String twoLetterSymbol, String systemId, String mallId, SiteType siteType) {
		this.symbol = twoLetterSymbol;
		this.systemId = systemId;
		this.mallId = mallId;
		this.siteType = siteType;
	}

	static ApplicationType findBySystemId(String systemId) {
		for (ApplicationType type : values()) {
			if (type.getSystemId().equals(systemId)) {
				return type;
			}

		}
		return ApplicationType.UNKNOWN;
	}

	public static ApplicationType decideApplicationType(Environment env) {
		String systemId = env.getProperty("system.id");
		ApplicationType appType = ApplicationType.findBySystemId(systemId);


		// 환경 변수에 localtype=gf 가 있으면 글로벌 사이트
		String localType = env.getProperty("localtype");
		if ("gf".equalsIgnoreCase(localType)) {
			switch (appType) {
			case NCP_FRONT_MOBILE:
				appType = GF_FRONT_MOBILE;
				break;
			case NCP_FRONT_PC:
				appType = GF_FRONT_PC;
				break;
			default:
				break;

			}
		}

		log.info("Application Type: {}", appType);
		return appType;
	}

}
