package com.plgrim.ncp.biz.promotion.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtDspTgt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtImg;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStmp;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 이벤트 Bo DTO
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventBoDTO extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 로그인 아이디
	 */
	private String loginId;
	
	/**
	 * 이벤트유형코드
	 */
	private String evtTpCd;
	
	/**
	 * 이벤트
	 */
	Evt evt;
	
	/**
	 * 이벤트 경품
	 */
	EvtFreeGiftInfo freeGiftInfo;
	
	/**
	 * 이벤트 이미지
	 */
	EvtImg evtImg;
	
	/**
	 * 이벤트 적용대상
	 */
	EvtPartcptnTgt evtPartcptnTgt;

	/**
	 * 이벤트 전시대상
	 */
	EvtDspTgt evtDspTgt;

	/**
	 * 이벤트 이미지 리스트
	 */
	List<EvtImg> evtImgList;
	
	/**
	 * 이벤트 번호 배열
	 */
	private String[] rowEvtNo;
	
	/**
	 * 프로모션 쿠폰 번호
	 */
	private String cpnPrmNm;
	
	/**
     * 이전 적용상품구분코드
     */
    String beforeAplGodSectCd;
    
    /**
     * 이전 제외상품구분코드
     */
    String beforeExcGodSectCd;
    
    /**
     * 선택된 적용상품구분코드
     */
    String checkedAplGodSectCd;
    
    /**
     * 선택된 제외상품구분코드
     */
    String checkedExcGodSectCd;
    
    /**
     * 적용순번(복수)
     */
    private String aplTurns;
    
    /**
     * 프로모션 적용상품
     */
    private EvtAplGod evtAplGod;
    
    /**
     * 이벤트 번호
     */
    private String evtNo;

	private String targetEvtNo;

	/**
	 * 경품 순번
	 */
	private int freeGiftTurn;

	private int prizeRank;
    
    
    /**
     * 회원 번호
     */
    private String mbrNo;
    
    /**
     * 이벤트 응모
     */
    private EvtApplcn evtApplcn;
    
    /**
     * 이벤트 스탬프
     */
    private EvtStmp evtStmp;
    
    private EventBoResult eventBoResult;
    
	/* B2E, SIGNL */
	private String spcPrmTp;
	/* GNRL, EMP */
	private String prcSectCd;

	private String lang;

	private String mallId;    
	
	private String relateEvtNo;
	
	private Mbr mbr;

	private String randomStmpYn;	// 무작위 스탬프 여부
	
	private String multiDownloadYn;	// 한번에 다운로드 여부
	
	private String headerReferer; //header REFERER 
	
	 /**
     * 이벤트 회원 등급
     */
	 private String evtMbrGrd;
	 
	 
	private String begDt;		//	시작일
		
	private String endDt;		//	종료일
	
    /**
     * 응모 참여 일자
     */
	private int atendDaycnt;
	
	
	 /**
     * 휴대폰번호
     */
	private String phoneMobile;
	
	 /**
     * 이벤트 응모 중복 가능 여부
     */
	private char evtDuplctYn;
    
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
