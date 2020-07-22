package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthor;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysAuthDTO  extends AbstractDTO {

	
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
	
	long selMenuSn; //선택된 메뉴코드
	
	long selAuthorGrpSn; //선택된 권한그룹코드
	java.math.BigInteger newAuthorGrpSn; //새로운 권한그룹코드
	
	
	String regtrId; //로그인된 ID
	
	SysAuthor sysAuthor;//권한그룹목록
	
	SysMenu sysMenu;
	
	int maxAuthorTurn;
	
	
	String useYn; //권한 사용 유/무

	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
}
