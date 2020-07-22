package com.plgrim.ncp.biz.settlement.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEvent;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventBrnd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventCom;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventExclsGod;

@Data
@EqualsAndHashCode(callSuper=false)
public class PrmFeeEventFormDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Com> comList;
	
	private List<String> brandList;
	
	private List<God> aplGodList;
	
	private List<God> exclsGodList;
	
	private PrmFeeEventBasicFormDTO prmFeeEventBasicFormDTO;
	
	private PrmFeeEvent prmFeeEvent;
	
	private PrmFeeEventCom prmFeeEventCom;
	
	private PrmFeeEventBrnd prmFeeEventBrnd;
	
	private PrmFeeEventAplGod prmFeeEventAplGod;
	
	private PrmFeeEventExclsGod prmFeeEventExclsGod;
	
	private String udterId;
	



	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
