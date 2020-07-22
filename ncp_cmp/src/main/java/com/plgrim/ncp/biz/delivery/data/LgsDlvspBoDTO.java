package com.plgrim.ncp.biz.delivery.data;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
@Data
@EqualsAndHashCode(callSuper = false)
public class LgsDlvspBoDTO extends AbstractDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -284814100492274407L;

	private Map<String, Integer> drctGodMap;

	private List<LgsDlvspExtend> lgsDlvspList;// 물류배송지

	//ncp2 - 계산된 물류배송 정보를 담기 위한 TmpDTO
	private List<LgsDlvspExtend> lgsDlvspTmpList;
	
	private String clmTpCd;    //클레임 유형 코드
	private String mallId;     //몰ID
	private String clmResnCd;  //사유
}
