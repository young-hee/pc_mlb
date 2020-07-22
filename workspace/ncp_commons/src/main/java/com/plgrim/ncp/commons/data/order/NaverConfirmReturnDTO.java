package com.plgrim.ncp.commons.data.order;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class NaverConfirmReturnDTO extends AbstractOrderDTO{

	String code;
	String message;
	
	NaverConfirmReturnBodyDTO body;
	
}
