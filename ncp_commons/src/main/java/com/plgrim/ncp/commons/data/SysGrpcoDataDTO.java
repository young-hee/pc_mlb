package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpco;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysGrpcoDataDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private SysGrpco sysGrpco; // 그룹사
	private String aplTurn; // 적용순번
}
