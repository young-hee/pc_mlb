package com.plgrim.ncp.base.entities.datasource1.lgs;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LgsRtrvlDrctGodExtend extends LgsRtrvlDrctGod {
	/**
	 * 
	 */
    private static final long serialVersionUID = -7602251570249711106L;

    /*********************************************************************
     * 클레임
     **********************************************************************/

    /**
     * 작업구분
     * 교환/맞교환 시 입고완료/사은품가입고 변경시 사용
     * 입고완료 : wrhsCompt 
     * 사은품가입고 : gftWrhs 
     */    
	private String jobGbn;
    

	/**
	 * 업체 ID
	 */
	private String comId;
	/**
	 * 수선 여부
	 */
	private String repairYn;
	/**
	 * 국내 배송비 정책 일련번호
	 */
	private java.lang.Long dmstcDlvCstPlcSn;
	/**
	 * 해외 배송 국내 배송비 정책 일련번호
	 */
	private java.lang.Long ovseaDlvDmstcDlvCstPlcSn;
	/**
	 * 해외 배송비 정책 일련번호
	 */
	private java.lang.Long ovseaDlvCstPlcSn;

    /*********************************************************************
     * 클레임
     **********************************************************************/
    
    
    
}
