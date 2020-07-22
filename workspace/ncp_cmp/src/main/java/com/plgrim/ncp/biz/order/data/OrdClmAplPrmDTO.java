package com.plgrim.ncp.biz.order.data;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;



@Data
@EqualsAndHashCode(callSuper = false)
public class OrdClmAplPrmDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = 5520695327272100234L;
	private String aplPrmTurn;	//적용프로모션순번
	private String ordNo;				//주문번호
	private String mbrNo;				//회원번호
	private String prmDtlTpCd;	//프로모션 세부유형코드
	private List<String> ordGodTurnList;	// 배송지별 상품 목록
	private String ordGodTurn;
	private String dlvPcupspTurn; //배송지 순번
	private String ordTp; 
	/**
	 * 국내 배송비 정책 일련번호 : 2015-12-07 추가 [AshA]
	 */
	private java.lang.Long dmstcDlvCstPlcSn;
	/**
	 * 해외 배송 국내 배송비 정책 일련번호 : 2015-12-07 추가 [AshA]
	 */
	private java.lang.Long ovseaDlvDmstcDlvCstPlcSn;
	/**
	 * 해외 배송비 정책 일련번호 : 2015-12-07 추가 [AshA]
	 */
	private java.lang.Long ovseaDlvCstPlcSn;

}
