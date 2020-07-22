package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysCldr;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysHldyDataDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private SysCldr sysCldr; // 달력
	private String prvSysDate; // 이전시스템일자
}
