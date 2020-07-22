package com.plgrim.ncp.biz.promotion.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SingleEventResultDto {

	private String evtNo;       //이벤트번호

	private String enterCount;       //응모 횟수

	private int enterHisCount;       //응모 한 횟수
	
	private double totalAmt;			//총구매금액
	
	private double onlinAmt;			//온라인 구매금액

	private double offlinAmt;			//오프라인 구매금액

	private String freeNm;			//참여 내용

	private String applcnBegDt;			//응모 가능 시작일
	
	private String applcnEndDt;			//응모 가능 종료일
	
	private String mbrNo;         
	
	private String evtNm;      
	
	private String upperEvtNm; 
	
	private String evtNoSub;
	
	private List<EvtAplGodExtend> aplGodList;

	private String rstMsg ;
	
	private String cpnPrmNo ;

	private String rtnMsg;			//	결과 메세지
	
	private String rtnCd;			//	결과 코드
	
	private String evtTpCdSub;	//	하위 이벤트 유형

	private String exchgbilNoArr;

}
