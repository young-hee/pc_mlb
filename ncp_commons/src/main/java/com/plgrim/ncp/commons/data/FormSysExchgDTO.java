package com.plgrim.ncp.commons.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)

public class FormSysExchgDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;
	//검색조건
	String searchField; //검색 필드
	String searchText; // 권한그룹코드 , 권한그룹명
	String searchUseYn;	//사용여부
	String searchExchg; // 달러나 위안화 사용여부. (US)달러, (CN)위안화
	String searchLimit; //검색 목록 Limit 수
	String textField; // 비고란 필드.
	String textExchg; // 환율 입력란 feiled
	List<String> exchgs; // 적용화폐
	
	/** 조회 시작일 */
	private String searchStDt;
	
	/** 조회 종료일 */
	private String searchEdDt;
	
	String crncyCd; // 적용화폐
	
	List<SysExchgDTO> listExchg; //SysMall 목록
	

}
