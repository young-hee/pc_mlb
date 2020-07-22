package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvHoldy;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvHolidayResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    
    /**
     * 퀵배송 미운영일
     */
    private ComQdlvHoldy comQdlvHoldy;
    
    /**
     * 미운영 일자 날짜타입
     */
    private java.util.Date sysDateD;

    /**
     * 등록자명
     */
    private String regtrIdNm;

    /**
     * 삭제가능여부
     */
    private String delPossibleYn;

    /**
     * 엑셀업로드 실패 미운영 일자
     */
    private String excelFailSysDate;

    /**
     * 엑셀업로드 실패 미운영 사유
     */
    private String excelFailDscr;

    /**
     * 엑셀업로드 실패 메시지
     */
    private String excelFailMsg;
}

