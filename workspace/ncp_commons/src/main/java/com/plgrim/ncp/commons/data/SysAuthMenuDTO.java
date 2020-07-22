package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysAuthMenuDTO  extends AbstractDTO {

	
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
	String authGroupCd; //권한그룹코드
	
	
	//List<SysMenu> listAuthMenu; //권한 메뉴 목록
	
	SysMenu sysMenu;
	
	//권한 체크 [url]
	String url;	
	//권한 체크 [대상자]
	String adminId;
	//사이트ID [로그인시선택한 정보]
	String boSiteId;
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
