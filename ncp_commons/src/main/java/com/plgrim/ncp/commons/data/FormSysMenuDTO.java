package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysMenuDTO  extends AbstractDTO {
	
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	//검색조건
	String searchField; //검색 필드 ( 1:메뉴명, 2:메뉴코드)
	String searchText; // 검색 값
	List<String> searchListUseYn;	//메뉴 사용여부
	List<String> searchListBoSite;//메뉴 시스템구분( BO,CS)
	
	long selMenuSn; //수정 메뉴코드

	List<SysMenuDTO> listMenu; //메뉴 

}
