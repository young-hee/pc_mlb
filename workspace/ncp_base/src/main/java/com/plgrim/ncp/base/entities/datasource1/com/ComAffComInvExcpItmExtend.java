/**
 * @package : com.plgrim.ncp.base.entities..com
 * @author : jackie(jackie)
 * @date : 20150511
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 업체 제휴 대행 업체 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comAffComInvExcpItmExtend")
public class ComAffComInvExcpItmExtend extends ComAffComInvExcpItm{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = -8634515176330181536L;
	
    /**
	 * 제휴 대행 업체 ID	 
	 */
	private String affVrscComNm;
	/**
	 * 제휴 업체 ID	 
	 */
	private String affComNm;
	
	/**
	 * 상품명 
	 */
	private String godNm;
	
	/**
	 * erp 상품명
	 */
	private String erpGodNo;
	
	/**
	 * 브랜드 ID
	 */
	private String brndId;

	/**
	 * 브랜드명
	 */
	private String brndNm;

	/**
	 * 단품명
	 */
	private String itmNm;
	
	/**
	 * 가용재고
	 */
	private String totUsefulInvQty;
	
	/**
	 * 안전재고
	 */
	private String safeInvQty;
	
	/**
	 * userId
	 */
	private String userId;
	
	/**
	 * 등록 일시	 
	 */
	private String regDtStr;

	/**
	 * 수정 일시	 
	 */
	private String udtDtStr;

}
