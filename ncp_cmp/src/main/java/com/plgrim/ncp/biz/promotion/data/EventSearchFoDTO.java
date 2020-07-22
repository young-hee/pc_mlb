package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventSearchFoDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 이벤트 번호
	** || YYYYMMDD || 7자리 Cycle Sequence 기본 번호체계 *에 입력되는 값에 따라 필요 번호 구분 
	
	EV : 이벤트 EVENT
		 
		 */
	private String evtNo;// 이벤트 번호
	
	private String endYn;// 종료이벤트 조회 여부
	
	private String mbrNo;// 회원번호
	
	private String prcSectCd;// 가격 타입
	
	private String orderBy;
	
	private String sprtrTurn;
	
	private String tgtMbrTpCd;

    private String freeGiftKndCd;   //경품 종류 코드

    private String prmNo;   //경품(쿠폰) 프로모션 번호
    	
	private String begDt;		//	시작일
	
	private String endDt;		//	종료일
	
	private java.math.BigDecimal pchAmt;		//	응모 기준 금액

    private String tabIdx;       // 이동탭 구분
    
    private String boAccess;       // 이동탭 구분
    
    private int height;       
    
    private int freeGiftTurn;
    
    private int prizeRank;
    
    private int cpnLimit;
    
    private String dayCheckCpnYn;
    
    private String mbrUnlimitedYn;

    private java.math.BigDecimal stdrAmt;

    private String brndGrpId;
    
    private int rowCount;
    
	private String lang;

	private String mallId;   
	
	private String deviceId;
	
	String pageNo;

	int pageSize = 12; // 40 80 120
	
	private String evtTp;
	
	private java.lang.Integer atendDaycnt;

	private java.lang.Integer stmpCount;
	
	private java.lang.Long evtPartcptnSn;
	
	private String lastDay;
	
	private java.lang.Integer sortSeq;
	
	private String phoneMobile;	
	
	private char evtDuplctYn;	
	
}
