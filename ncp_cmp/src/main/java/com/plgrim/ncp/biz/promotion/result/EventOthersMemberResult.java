package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventOthersMemberResult extends AbstractResult {
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    private Evt evt;					// 이벤트 Entity
    private EvtApplcn evtApplcn;		// 이벤트 응모내역 Entity
    private EvtPrize evtPrize;			// 이벤트 당첨자 Entity
    private Ord ord;							//	이벤트 응모자 주문 Entity
    
    private String evtDt;				// 이벤트 기간
    private String evtStatNm;			// 이벤트 상태 코드 명
    private String prizeInfo;			// 당첨정보   
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
