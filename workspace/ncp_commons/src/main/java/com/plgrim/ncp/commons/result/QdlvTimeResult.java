package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.ComQdlvOper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvTimeResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    
    /**
     * 퀵배송 운영시간
     */
    private ComQdlvOper comQdlvOper;
    
    /**
     * 운영일 구분 코드명
     */
    private String operDaySectCdNm;

    /**
     * 운영 시작 시
     */
    private String operBegHh;

    /**
     * 운영 시작 분
     */
    private String operBegMm;

    /**
     * 운영 종료 시
     */
    private String operEndHh;

    /**
     * 운영 종료 분
     */
    private String operEndMm;
}

