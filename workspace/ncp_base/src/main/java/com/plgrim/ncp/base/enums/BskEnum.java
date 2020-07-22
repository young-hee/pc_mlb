/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 20
 */
package com.plgrim.ncp.base.enums;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 20
 */
public enum BskEnum {

	// CONSTANTS
	YES("Y"), NO("N"),ReWhsgNtcnReqst("RE_WHSG_NTCN_REQST");

	public enum BskTp {
		 BSK, DIRT, REORDER
	}

	public enum MbrSect {
		MBR ,NMBR
	}

	public enum DlvSect {
		GNRL_DLV ,PKUP_DLV ,OVSEA_DLV ,QDLV, RSV
	}

	public enum PckageGodTp {
		SET_GOD ,PCKAGE_GOD ,ADIT_CPST_GOD
	}

	public enum BskGodSaleStat {
		SALE_PROGRS, SALE_PROGRS_PKUP, SALE_END, SLDOUT, TMPR_SLDOUT;
	}

	public enum BskGodItmSaleStat {
		SALE_PROGRS, SALE_PROGRS_PKUP, SALE_END, SLDOUT, TMPR_SLDOUT;
	}

	public enum BskPrmType {

		GOD_DC, ORD_DC, GFT ,MBSH_PNT ,EVT_PNT, CRCOM_AFF ,CPN , WEBPNT;
	}

	public enum BskPrmDtlType {

		GOD_CPN, BSK_CPN, DLV_CST_CPN, QDLV_CST_CPN, SUBD_GOD_DC ,SIGNL_SPSL,B2E_SPSL ,BUNDLE_DC ,CRS_DC ,GOD_GFT, ORD_GFT ,ADIT_SAV ,RQEST_DC ,NINT_INSTM;
	}

	public enum BskDcSect {
		FIXAMT	,FIXRT	,SALE_AMT
	}

	private final String value;

	private BskEnum(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}


}
