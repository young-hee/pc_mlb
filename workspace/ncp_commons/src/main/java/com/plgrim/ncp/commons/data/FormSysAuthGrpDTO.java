package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysAuthGrpDTO  extends AbstractDTO {
	
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	
	
	
	//검색조건
	String searchField; //검색 필드
	String searchText; // 권한그룹코드 , 권한그룹명
	//String searchUseYn;	//사용여부
	String searchLimit; //검색 목록 Limit 수

	long selAuthorGrpSn; //선택된 권한그룹코드
	long selMenuSn; //선택된 메뉴코드
	
	List<SysAuthGrpDTO> listAuthGrp; //권한그룹목록
	
	List<String> searchListUseYn;

    List<SysAuthDTO> listAuth;
    
    String searchBoSite; //시스템구분( BO,CS)

    List<String> listBoSite;
    
    List<SysMenuDTO> listMenu; //메뉴 
    
    
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
