package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysCmmnNotiDataDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private SysNoti sysNoti; // 매장
}
