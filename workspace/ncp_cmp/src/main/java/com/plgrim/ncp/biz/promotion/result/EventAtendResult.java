package com.plgrim.ncp.biz.promotion.result;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventAtendResult extends EvtFreeGiftInfo {
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
     * 이벤트 번호
     */
    private String evtNo;

    /**
     * 출석체크 응모여부
     */
    private boolean atendCheckResult = false;

    /**
     * 출석횟수
     */
    private int attendCount;

    /**
     * 사은품가능여부
     * 
     * false : 가능 사은품 없음 또는, 최종 사은품 조건 충족
     * true : 가능사은품 존재
     * 
     * ex> 3회시 사은품 지급
     * <pre>
     * flase : 참여이력 없음 또는, 3회 이상 참여 - 받을 수 있는 사은품 없음.
     * true  : 2회 참여시                       - 3회차 사은품 가능
     */
    private boolean abledFreeCheck = false;

    /**
     * 이벤트 경품정보 리스트
     */
    private List<EvtFreeGiftInfo> evtFreeGiftInfoList = new LinkedList<EvtFreeGiftInfo>();

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
