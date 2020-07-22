package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenuDTO  extends AbstractDTO {

	
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
	
	java.math.BigInteger menuDepth; //메뉴 단계
    String upperMenuNm; //상위 메뉴명
    String naMenuNm; // 메뉴명 네비
    
    SysMenu sysMenu; //메뉴유형 메뉴
    
    List<SysMenuDTO> sysFileList;//메뉴 종속 FILE 유형 정보
    
    List<Long> deleteFileList; //삭제된 File URL key (menuSn)
    
    String regtrId; //로그인 아이디
    
    String  radioFirstPgeYn; //라디오 첫페이지 선택여부 1 or 0
    
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
    
    int maxSortSeq; //전시순서 자동  

}
