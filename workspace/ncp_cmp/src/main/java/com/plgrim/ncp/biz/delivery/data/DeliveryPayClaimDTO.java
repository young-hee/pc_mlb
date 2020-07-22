package com.plgrim.ncp.biz.delivery.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;


/**
 * 클레임 주문 전체취소, 부분취소시 배송정보 수정을 위한 DTO
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryPayClaimDTO  extends AbstractDTO {

    private static final long serialVersionUID = 6378632372483113155L;

	//주문번호
	private String ordNo;
	
	//클레임번호
	private String clmNo;
	
	//접수자id session
	private String adminId;
	
	//배송지 순번
	private String dlvPcupspTurn;
	
	//출고지시 상품번호
	private String dlivyDrctGodNo;
	
	//물류 출고 지시상품 엔티티 리스트
	private List<LgsDlivyDrctGod> dlvGodList;
	
	//출고지시 유형코드
	private String dlivyDrctTpCd;
	
	
	
}

