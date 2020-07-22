package com.plgrim.ncp.commons.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


@Data
@EqualsAndHashCode(callSuper=false)
public class SysLsmsDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	//private FormSysShopDTO search = new FormSysShopDTO(); // 조회조건 DTO
	
	private SysLsmsDataDTO formData; // 저장용
	
	private List<SysLsmsDataDTO> gridList; // 그리드
}
