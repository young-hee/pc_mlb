package com.plgrim.ncp.biz.interfaces.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InfOrdGodErpDstbUpdateDTO {

	private String ordNo;
	private String clmNo;
	private List<OrdGodErpDTO> ordGodTurnList;	//여기에 데이터 set 후 for 문으로 쿼리 실행
	private String qty;	//쿼리날리기위해 리스트에서 빼서 set 하는 수량
	private String ordGodTurn;	//쿼리날리기위해 리스트에서 빼서 set 하는 상품순번
	private String dlivyDrctGodNo;	//출고지시상품순번
	private String erpGodSn; // 시리얼 번호
	private String clmResnCont; // 접수사유
	private String[] erpGodSnArry; // 시리얼 번호
	private String repairNo; //수선번호
}
