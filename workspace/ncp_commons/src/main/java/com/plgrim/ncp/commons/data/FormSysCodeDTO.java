package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysCodeDTO extends AbstractDTO {

     private static final long serialVersionUID = 1L;
		/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	//검색조건
	
 	//검색조건
 	String searchField; // 조회필드
 	String searchText; //조회 조건
 	
 	String selGrpCd; //선택된 그룹코드
 	
 	List<String> searchListGrpCd; //searchField 3 or 4 일경우 service단에서 사용하는 필드
 	List<String> searchListUseYn;
 	
 	List<SysCdDTO> listSysCd; //저장 그룹코드 or 그룹상세코드
 	
 	String selCd; //선택된 코드
 	
 	//아래코드는 사용하는 코드인지 확인이 필요함 TAM  
 	String upperCd; // 선택된 상위코드
 	String langCd;  // 선택된 국가코드

}
