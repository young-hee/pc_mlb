package com.plgrim.ncp.commons.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysMallDTO extends AbstractDTO {

	/**
	 * 
	 */
    	private static final long serialVersionUID = 2484110538248972135L;
		/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	//검색조건
		String searchField; //검색 필드
		String searchText; // 권한그룹코드 , 권한그룹명
		String searchUseYn;	//사용여부
		String searchMall; // Mall 구분. (I)ntegration, (C)irculation, (B)eaker
		String searchLimit; //검색 목록 Limit 수

		long selAuthorGrpSn; //선택된 권한그룹코드
		long selMenuSn; //선택된 메뉴코드
		
		List<SysMallDTO> listSysMall; //SysMall 목록
	    
	    String searchBoSite; //시스템구분( BO,CS)
	    List<String> listBoSite;
	    
	    List<String> searchMalls; // 몰 구분
	    List<String> searchUseYns; // 사용여부
	    
	    String shortUrl;//shortUrl
}
