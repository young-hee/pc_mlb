package com.plgrim.ncp.biz.pay.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmRefundSearchDTO extends AbstractDTO {

	private String ordNo;
	
	private String returnDlvAmt;
	
	private String returnDlvAmtEx;	//ncp 3 return claim

	private String clmTpCd;
	
	private String adminTpCd;//관리자 유형
	
	private String clmResnCd;
	
	private List<OrdGodExtend> ordGodList;	//주문상품리스트

    /**
     * ncp2 - 업체별 반품/교환 배송비 위해 추가
     * 물류배송지
     * 배송지별 상품목록을 가져오기 위함
     * 패키지, 세트 상품인 경우 아래와 같은 구조임.
     * OrdGodExtend 	<패키지/일반상품>
     * 	   OrdGodExtend <패키지구성품>
     *     OrdGodExtend <패키지구성품>
     *     ...
     */
    private List<LgsDlvspExtend> lgsDlvspList;

	/**
	 * 클레임번호
	 */
	private String clmNo;

    /**
     * Role
     * FO/BO 구분
     * FO : F, BO : B
     * 반품접수/교환접수 에서 사용
     */
	private String role;


}
