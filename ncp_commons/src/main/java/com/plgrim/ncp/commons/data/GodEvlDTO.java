package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


@Data
@EqualsAndHashCode(callSuper=false)
public class GodEvlDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private FormGodEvlDTO search = new FormGodEvlDTO(); // 조회조건 DTO
	
	private GodEvlDataDTO formData; // 저장용
}
