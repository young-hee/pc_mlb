package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
public class SysEmailDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	//private FormSysShopDTO search = new FormSysShopDTO(); // 조회조건 DTO
	
	private SysEmailDataDTO formData; // 저장용
	
	private List<SysEmailDataDTO> gridList; // 그리드
	
}
