/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 업체 제휴 대행 업체 브랜드
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comAffVrscComBrnd")
public class ComAffVrscComBrnd extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 제휴 대행 업체 ID	 
	 */
	private String affVrscComId;


	/**
	 * 제휴 업체 ID	 
	 */
	private String affComId;


	/**
	 * 브랜드 ID
	 * ㅁ. 온라인에서 사용하는 브랜드 ID	 
	 */
	private String brndId;


	/**
	 * 제휴 대행 업체 제공 여부
	 * ㅁ. 대행사 브랜드별 연동을 제공할 것인지 여부	 
	 */
	private String affVrscComOfferYn;


	/**
	 * 제휴 대행 업체 수수료 율	 
	 */
	private java.math.BigDecimal affVrscComFeeRt;


	/**
	 * 제휴 업체 수수료 율	 
	 */
	private java.math.BigDecimal affComFeeRt;


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
	 * 직접 연계 여부
	 * 주체가 되어 연계 데이터를 직접 연계 해오는 여부 값	 
	 */
	private String dirtCntcYn;


	/**
	 * 제휴 업체 재고 적용 여부	 
	 */
	private String affComInvAplYn;


	/**
	 * 제휴 업체 재고 분배 율	 
	 */
	private java.math.BigDecimal affComInvDstbRt;


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