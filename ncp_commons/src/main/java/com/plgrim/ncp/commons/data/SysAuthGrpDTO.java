package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrp;

/*@NoArgsConstructor
@ToString*/

// @Data
// @EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
@ToString
public class SysAuthGrpDTO  extends AbstractDTO {
	
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	
	long newAuthorGrpSn; //새로운 그룹코드
	
	String regtrId; //로그인 아이디
	
	long existCount;// update항목의 존재여부

	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
	private SysAuthorGrp sysAuthorGrp = new SysAuthorGrp();
	
}
