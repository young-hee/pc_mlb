/**
 * @package : com.plgrim.ncp.base.entities..inf
 * @author : ()
 * @date : 20150618
 * @version : 1.0
 * @desc :
 */

package com.plgrim.ncp.base.entities.datasource1.inf;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 인터페이스 주문 상품 ERP 분배
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infOrdGodErpDstbExtend")
public class InfOrdGodErpDstbExtend extends InfOrdGodErpDstb{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 배송지 순번
	 */
	private String dlvPcupspTurn;

	/**
	 * 수량
	 */
	private String qty;

	/**
	 * 주문 수량
	 */
	private java.lang.Long ordQty;

	/**
	 * erp 통신규격을 위한 할인 합계금액
	 */
	private String disAmt;

	/**
	 * erp 통신규격을 위한 해당 상품의 할인율
	 */
	private String disRate;

	/**
	 * erp 통신규격을 위한 해당상품의 적립율
	 */
	private String addRate;

	/**
	 * erp 통신규격을 위한 포인트 마일리지 구분코드 (to-be 에는 마일리지가 없어서 P 로 고정)
	 */
	private String gb;

	/**
	 * erp 통신 규격을 위한 상품의 기본 단위 'EA'(수량) 로 고정
	 */
	private String meins;

	/**
	 * erp 통신 규격을 위한 통화키
	 */
	private String waers;

	/**
	 * ERP 연동 대상 여부
ㅁ. 입점 업체의 상품일 경우 N
	 */
	private String erpIntrlckTgtYn;

	/**
	 * 클레임번호
	 */
	private String clmNo;

	/**
	 * 배송순번 : 2015-12-07 추가 [AshA]
	 */
	private java.lang.Integer dlvTurn;

	/**
     * Role
     * FO/BO 구분
     * FO : F, BO : B
     * 반품접수/교환접수 에서 사용
     */
	private String role;
	
	
	
    // #54953 : 구매이력개선
    private String prdlstCd;		//품목 : item_cd
    private String erpBrndGrpId;	//품목브랜드그룹: brand
    private String colorCd;			//품번색상: color
    private String optCd1;			//품번사이즈: size
}
