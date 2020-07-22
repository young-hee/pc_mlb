package com.plgrim.ncp.biz.settlement.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class PrmFeeEventSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String searchEventBegDt;
	private String searchEventEndDt;
	private String searchFeeEventNm;
	private String searchComTxt;
	private String searchComGubun;
	private String searchGodTxt;
	private String searchGodGubun;
	private String searchUpperBrndId;
	private String searchBrndId;
	private List<String> searchFeeEventTpCdList;
	private String feeEventSn;
	private List<String> searchAffAgncCom;

	private String brndList;
	private String aplGod;
	private String exclsGod;
	
	/** 다중검색 타입 */
	private String schTypeIds;
	
	/** 상품코드 */
	private String multiIds;
	
	/** 상품코드(+콤마) */
	private String multiNo;
	
	/** 상품코드(배열) */
	private List<String> multiNos;
	
	/** 행사번호 */
	private String eventIds;
	
	/** 행사번호(+콤마) */
	private String eventNo;
	
	/** 행사번호(배열) */
	private List<String> eventNos;
	
	/** 행사진행상태 */
	private String schUseYn;
	
	/** 업체ID */
	private String partmalComId;
	
	/** 업체ID(배열). */
	private List<String> partmalComIds;
	
	/** 업체명 */
	private String partmalComNm;
	
	/** 업체명(배열) */
	private List<String> partmalComNms;

}
