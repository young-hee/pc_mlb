package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 퀵배송BO command result
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvCmdResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;

    /**
     * 성공건수
     */
    private int successCnt;

    /**
     * 실패건수
     */
    private int failCnt;
    
    /**
     * 실패타입 ( NOPARAM : 파라미터없음, ERROR : 오류 )
     */
    private String failType;

    /**
     * 실패데이터
     */
    private String failData;

    /**
     * 실패메시지
     */
    private String failMsg;

    /**
     * 처리결과 리스트
     */
    private List<QdlvCmdResult> qdlvCmdResultList;
}

