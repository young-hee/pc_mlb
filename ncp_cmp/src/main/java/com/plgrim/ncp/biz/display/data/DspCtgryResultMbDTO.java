package com.plgrim.ncp.biz.display.data;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspCtgryResultMbDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = -6240523548959887928L;

	/** 모바일 전시카테고리 정보 */
	String ctgNm;
	String ctgNo;
	String sort;
	String cateType;
	String brndCtgId;
	String ctgDepthCd;
	String godNos; 
	List<String> colorTxts;
	List<List<String>> sizes;
	List<String> materials;
	List<DspCtgryResultMbExt> styles;
	List<SysBrnd> brands;
	String brndId[];
	String dspCtgryNm;
	String newBestType;
    String colorStyleCd;

    String[] colorStyleCds;
    
    String scriptCtgNm;	// 카테고리명에 ' <= 이 있는 카테고리명 때문에 스크립트 오류가 발생하여 치환한 카테고리명을 저장할 변수.
    
	public String[] getColorStyleCds() {
		
		if(getColorStyleCd() != null){
			
			return getColorStyleCd().split(",");
			
		}else{
		
			return colorStyleCds;
		}			
	}

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
