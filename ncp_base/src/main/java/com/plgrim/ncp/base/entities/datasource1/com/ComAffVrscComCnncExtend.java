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
@Alias("comAffVrscComCnncExtend")
public class ComAffVrscComCnncExtend extends ComAffVrscComCnnc{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = -8634515176330181536L;
	
    /**
	 * 제휴 대행 업체명	 
	 */
	private String affVrscComNm;

	/**
	 * 제휴 업체명	 
	 */
	private String affComNm;
	
	/**
	 * 브랜드 ID
	 */
	private String brndId;

	/**
	 * 브랜드명
	 */
	private String brndNm;

	/**
	 * 판매가능수량 사용여부
	 */
	private String affComInvAplYn;
	
	/**
	 * 판매가능수량 정률
	 */
	private int affComInvDstbRt;

	/**
	 * 판매가능수량 사용여부 BEFORE
	 */
	private String affComInvAplYnBf;
	
	/**
	 * 판매가능수량 정률 BEFORE
	 */
	private int affComInvDstbRtBf;
	
	/**
	 * 등록 일시	 
	 */
	private String regDtStr;

	/**
	 * 수정 일시	 
	 */
	private String udtDtStr;

}
