package com.plgrim.ncp.biz.delivery.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryFinishFoDTO extends AbstractDTO {
	
	private static final long serialVersionUID = -284814100492274417L;
    
	String ordNo;
	java.lang.Integer dlvPcupspTurn;
    String dlivyDrctGodNo;
    
}
