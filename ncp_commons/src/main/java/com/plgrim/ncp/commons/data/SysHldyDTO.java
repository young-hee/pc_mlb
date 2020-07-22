package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


@Data
@EqualsAndHashCode(callSuper=false)
public class SysHldyDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private FormSysHldyDTO search = new FormSysHldyDTO(); // 조회조건 DTO
	
	private SysHldyDataDTO formData; // 저장용
}
