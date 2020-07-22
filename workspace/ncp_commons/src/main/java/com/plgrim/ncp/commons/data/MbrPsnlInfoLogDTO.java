package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


@Data
@EqualsAndHashCode(callSuper=false)
public class MbrPsnlInfoLogDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private FormMbrPsnlInfoLogDTO search = new FormMbrPsnlInfoLogDTO(); // 조회조건 DTO
}
