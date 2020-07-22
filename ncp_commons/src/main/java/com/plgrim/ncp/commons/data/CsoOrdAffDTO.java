package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


@Data
@EqualsAndHashCode(callSuper=false)
public class CsoOrdAffDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private FormCsoOrdAffDTO search = new FormCsoOrdAffDTO(); // 조회조건 DTO
	
	private CsoOrdAffDataDTO formData; // 저장용
}
