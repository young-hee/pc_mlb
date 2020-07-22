package com.plgrim.ncp.biz.callcenter.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2015-05-29.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CallbackGridDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    private String clbcId;
	private String clbcRegDt;
	private String clbcDstbDt;
	private String clbcPhon;
	private String clbcRecptnPhon;
	private String clbcPrcsStatCd;
	private String clbcPrcsStatNm;
	private String clbcComptDt;
	private String cnsltAdminId;
	private String regtrId;
	private String regDt;
	private String udterId;
	private String udtDt;
	private String adminId;
	private String loginId;
}
