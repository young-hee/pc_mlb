/**
 * @package : com.plgrim.ncp.base.entities..mbr
 * @author : ()
 * @date : 20170627
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.member.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpco;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 회원 혜택 적용 대상 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBenefitAplTgtGrpCdResult extends AbstractResult {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 혜택 일련번호	 
	 */
	private java.lang.Long bnefSn;
	/**
	 * 적용 순번	 
	 */
	private java.lang.Integer aplTurn;
	
	private SysGrpco sysGrpco; // 그룹사
	
}
