package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmCouponSearchDTO extends AbstractDTO {
	
	private static final long serialVersionUID = -1469413381114320285L;
	
	Mbr mbr;

	List<ClmWrhsGodExtend> clmWrhsGodExtendList;
	
	String prmDtlTpCd;
	String allGodYn;
	String prmNo;
	String mbrCpnNo;
}
