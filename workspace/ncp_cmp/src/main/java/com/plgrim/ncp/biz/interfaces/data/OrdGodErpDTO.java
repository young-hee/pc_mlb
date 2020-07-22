package com.plgrim.ncp.biz.interfaces.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * 주문erp분배 테이블에 상품 수량단위로 조절하기 위한 DTO
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdGodErpDTO extends AbstractDTO {

	private String clmNo;
	private String ordGodTurn;//주문상품 순번
	private String dlivyDrctGodNo;//출고지시 상품순번
	private String qty;//수량
	private String erpGodSn; // 시리얼번호
	private String clmResnCont; // 접수사유
	private String[] erpGodSnArry; // 시리얼번호

}
