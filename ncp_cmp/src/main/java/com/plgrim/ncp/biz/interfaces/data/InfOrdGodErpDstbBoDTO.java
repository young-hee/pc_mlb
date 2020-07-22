package com.plgrim.ncp.biz.interfaces.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;

@Data
@EqualsAndHashCode(callSuper = false)
public class InfOrdGodErpDstbBoDTO extends AbstractDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -250943542559801727L;

	private List<InfOrdGodErpDstb> infOrdGodErpDstbList;

}
