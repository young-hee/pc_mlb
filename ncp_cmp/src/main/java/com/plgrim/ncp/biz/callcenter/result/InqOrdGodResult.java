package com.plgrim.ncp.biz.callcenter.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class InqOrdGodResult  extends AbstractResult {/**
	 * 
	 */
    private static final long serialVersionUID = -3921755290909773112L;

    private String ordGodTurn;				//주문상품 순번
    private String erpGodNo;				//erp 상품번호
    private String godNm;					//상품명 (주문의 대표상품 하나)
    private String godNo;					//상품번호 품번 (주문의 대표상품 하나)

}
