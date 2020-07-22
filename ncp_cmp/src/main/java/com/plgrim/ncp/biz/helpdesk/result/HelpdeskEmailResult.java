package com.plgrim.ncp.biz.helpdesk.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskEmailResult extends AbstractResult{
	//1:1 문의
	private String inqSn;
	private String inqTpCd;
	private String inqSj;
	private String inqCont;
	private String inqDt;
	private String inqGodNo;
	private String inqErpGodNo;
	private String inqRegDt;
	private String inqOrdNo;
	private String inqMbrTpCd;
	private String inqMbrNm;
	private String inqAtchFileUrl;
	private String inqAtchFileNm;
	private String inqAnssj;
	private String inqAnscont;
	private String inqAnsDt;
	private String inqMbrNo;
	
	// 단체 제휴
	private String orgztOrdAffInqSn;
	private String orgztOrdAffInqTpCd;
	private String orgztOrdAffInqDetailTpCd;
	private String prcsComptDt;
	private String orgztNm;
	private String orgztInqerNm;
	private String orgztInqCont;
	private String orgztInqerEmail;
	private String orgztRegDt;
	private String orgztAnsCont;
	private String orgztAnsDt;
	private String orgztFileNm;
	private String orgztFileUrl;
	
	
}
