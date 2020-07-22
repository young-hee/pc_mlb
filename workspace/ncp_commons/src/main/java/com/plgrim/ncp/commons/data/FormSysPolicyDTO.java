package com.plgrim.ncp.commons.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysPolicyDTO extends AbstractDTO {
	/**
	 * UID 
	 */
	private static final long serialVersionUID = 1L;
	
	//검색조건
	String searchField; //조회필드
 	String searchText; //조회 조건
 	List<String> searchListUseYn;	//사용여부 
 	
 	String selMallId;	//선택된 몰ID
 	String selPlcCd;	//선택된 정책코드
 	int selPlcValTurn;
 	String selPlcVal;
 	String selAplBegDt;	//선택된 적용 시작일시
 	String selPlcValDt;	//선택된 정책 값 일시
 	String selPlcValBegDt;	//선택된 정책 값 시작일시
 	String selPlcValEndDt;	//선택된 정책 값 시작일시
 	String selUseYn;
 	String selCmpndValPsbYn;
 	
 	List<SysPolicyDTO> listSysPolicy;	//저장 정책 or 정책 값
}
