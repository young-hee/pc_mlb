package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;

/**
 * Created by Jieun on 2016-09-22.
 */
@Data
public class PurchaseGoodInfoDTO  extends AbstractDTO {

	private String ordNo;
	private String ordGodTurn;
	private String godTpCd;
	private String ordDt;
	private String brndNm;
	private String godNo;
	private String godNm;
	private String itmNo;
	private String itmNm;
	private String lstImgUrl;
	private String brndGrpId;
}
