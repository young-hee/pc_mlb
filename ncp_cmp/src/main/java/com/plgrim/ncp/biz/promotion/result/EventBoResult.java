package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;

/**
 * 이벤트 Result
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventBoResult extends AbstractResult {
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
	 * 이벤트
	 */
	private Evt evt;
	
	/**
	 * 이벤트 경품 정보
	 */
	private EvtFreeGiftInfo evtFreeGiftInfo;
	
	/**
	 * 회원번호
	 */
	private String mbrNo;
	
	private String mbrAtrbCd;
	
	/**
	 * 응모횟수
	 */
	private int applcnCount;
	
	/**
	 * 응모가능여부 체크
	 *   - Y : 가능
	 *   - N : 불가
	 */
	private String applcnCheck;
	

	/**
	 * 응모가능 Evt 여부 체크
	 *   - Y : 가능
	 *   - N : 불가
	 */
	private String applcnEvtCheck;

    /**
     * 응모 가능일 체크
     */
    private String applcnDateCheck;

    /**
     * 회원 유형 체크
     */
    private String memberTypeCheck;

    /**
     * 디바이스 체크
     */
    private String deviceCheck;
    
    /**
     * 디바이스 코드
     */
    private String deviceCodes;
    
    private boolean checkResult = false;
    
    /**
     * 유효성 체크 결과코드
     */
    private String resultCode;
    
    /**
     * 결과 메시지
     */
    private String resultMessage;
    
    /**
     * 메시지 property 소스
     */
    private String messageSource;
    
    /**
     * 결과 url
     */
    private String resultUrl;
    
    /**
     * 유효성 체크 부가(Addition) 결과코드
     */
    private String resultAdtnCode;
    
    /**
     * 프로모션 발급가능 체크
     */
    private String prmAplPdCheck;
    
    /**
     * 쿠폰 종류
     */
	private String cpnKndCd;	

    /**
     * 회원 쿠폰 번호
     */
	private String mbrCpnNo;	
	
    /**
     * 응모 일자
     */
	private String applcnDt;	
	
    /**
     * 응모 참여 일자
     */
	private int atendDaycnt;
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
