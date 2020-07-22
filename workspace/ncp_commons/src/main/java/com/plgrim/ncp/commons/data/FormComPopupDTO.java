package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class FormComPopupDTO  extends AbstractDTO {

	
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
	 * 목록 검색 변수
	 */
	
	String searchField;
	String searchText;
	
	//선택된 브랜드 ID
	String selBrndId;
	
	//검색 부모 브랜드 ID
	String searchBrndId;
	
	String searchComTpCd;
	//브랜드 그룹여부
	String searchBrndGrpYn;
	//업체 아이디
	String searchComId;
	
	String affComId;
	
	//브랜드 최상위그룹여부
	String searchFirstBrndGrpYn;	
}
