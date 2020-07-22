package com.plgrim.ncp.commons.data;


import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMallCnvrsUrl;

@Data
@EqualsAndHashCode(callSuper=false)
public class SysMallDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	long newAuthorGrpSn; //새로운 그룹코드
	String regtrId; //로그인 아이디
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
	private SysMallCnvrsUrl sysMallCnvrsUrl = new SysMallCnvrsUrl();
	String userMallId; // 몰 ID


}
