package com.plgrim.ncp.biz.order.result;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrdGodForClmResult extends AbstractResult {/**
	 * 
	 */
    private static final long serialVersionUID = -7383943264466575193L;

    private String	turn; //	순번
    
    private String	ordDt; //	주문일시
    
    private String	brndGrpId; //	브랜드그룹ID
    
    private String	brndGrpNm; //	브랜드그룹
    
    private String	ordTpCd; //	주문구분코드
    
    private String	ordTpCdNm; //	주문구분
    
    private String	godNo; //	상품번호
    
    private String	erpGodNo; //	 ERP코드
    
    private String	godNm; //	상품명
    
    private String	itmNo; //	단품번호
    
    private String	itmNm; //	옵션
    
    private String	csmrPrc; //	실소가
    
    private String	saleAmt;	//판매가
    
    private String	dcAmt; //	할인금액
    
    private String	stdrCrncyAmt; //	판매가
    
    private String	ordStatCdNm; //	주문상태
    
    private String	ordQty; //	수량
    
    private String	payAmt;	//결제금액


}
