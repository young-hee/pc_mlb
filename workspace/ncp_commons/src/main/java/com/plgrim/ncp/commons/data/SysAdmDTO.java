package com.plgrim.ncp.commons.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdmDTO  extends AbstractDTO {

	
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
	String searchAuthGroupCd; //권한그룹코드
	String searchAuthGroupNm; //권한그룹명
	String searchUseYn;	//사용여부
	
	
	//선택된 권한그룹코드
	String selectAuthGroupCd; //선택된 권한그룹코드

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
