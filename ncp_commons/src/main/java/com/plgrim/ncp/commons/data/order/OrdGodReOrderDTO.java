package com.plgrim.ncp.commons.data.order;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrdGodReOrderDTO extends AbstractOrderDTO{

	private String godNo;
	private String itmNo;
	private Long ordQty;
	private String godTpCd; 
	private Long ordGodTurn; 
	private String pckageGodTpCd;
	private Long parentOrdGodTurn;
	private String ordCpstGodTurn;
	
}
