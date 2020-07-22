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
 * 시스템 ERP 판매 매장
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysErpSaleShop")
public class SysErpSaleShop extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 판매 매장 코드
	 * ㅁ. ERP와 연동하는 판매 매장 코드
	 * 
	 * ㅁ. 판매 매장 : SALE_SHOP
	 *    >. Online패션피아 : B04A
	 *    >. Online8s : B04B
	 *    >. Beaker자사 : B04C
	 *    >. Beaker위탁 : B04D
	 *    >. Online제휴몰 : B04E
	 *    >. Beaker제휴몰위탁 : B04F	 
	 */
	private String saleShopCd;


	/**
	 * 몰 ID	 
	 */
	private String mallId;


	/**
	 * 지역 구분 코드
	 * ㅁ. 지역 구분 : AREA_SECT
	 *    >. 국내 : DMSTC
	 *    >. 해외 : OVSEA
	 * 
	 * ㅁ. 시스템 몰 언어.기준 언어 여부가 Y인 경우 국내로 처리	 
	 */
	private String areaSectCd;


	/**
	 * 입점 구분 코드
	 * ㅁ. 입점 구분 : PARTMAL_SECT
	 *    >. 자사 : MCOM
	 *    >. 입점 : PARTMAL
	 * 
	 * ㅁ. 상품.입점 구분 코드와 동일	 
	 */
	private String partmalSectCd;


	/**
	 * 판매 주체 코드
	 * ㅁ. 판매 주체 : SALE_MBD
	 *    >. 자사 : MCOM
	 *    >. 제휴사 : AFF_COM	 
	 */
	private String saleMbdCd;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


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