/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 시스템 관리자 SMS 발송 대상
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysAdminSmsSndTgt")
public class SysAdminSmsSndTgt extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 관리자 ID	 
	 */
	private String adminId;


	/**
	 * SMS 발송 대상 코드
	 * ㅁ. SMS 발송 대상 : SMS_SND_TGT
	 *    >. 1시간 주문 부재 : 1TIME_ORD_ABSNCE
	 *    >. 2시간 주문 부재 : 2TIME_ORD_ABSNCE
	 *    >. 3시간 주문 부재 : 3TIME_ORD_ABSNCE	 
	 */
	private String smsSndTgtCd;


	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}
