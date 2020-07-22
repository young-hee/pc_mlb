package com.plgrim.ncp.biz.helpdesk.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoNmbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAns;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskNmbrStplatAgrFoDTO extends AbstractDTO {
	
	Mbr mbr;
	
	/**1:1 문의 리스트 Entity */
	CsoMtmInq csoMtmInq;
	
	/**단체 제휴 Entity */
	CsoOrgztOrdAffInq csoOrgztOrdAffInq;
	
	/**고객서비스 비회원 약관 동의 Entity	 */
	CsoNmbrStplatAgr csoNmbrStplatAgr;
	
	

}
