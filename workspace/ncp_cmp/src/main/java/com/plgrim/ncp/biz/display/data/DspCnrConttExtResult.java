package com.plgrim.ncp.biz.display.data;

import org.apache.ibatis.type.Alias;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("dspCnrConttExtResult")
public class DspCnrConttExtResult extends DspCnrConttExt {

	/** 페이지 요청 오브젝트. */
	Pageable pageable = new PageRequest(0, 30);
	
	private long totalRow;
	
	private static final long serialVersionUID = 1L;
	
}
