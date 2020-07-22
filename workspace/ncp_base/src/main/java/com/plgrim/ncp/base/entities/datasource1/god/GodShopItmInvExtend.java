/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : jackie(jackie)
 * @date : 20150420
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 상품 매장 단품 재고
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godShopItmInvExtend")
public class GodShopItmInvExtend extends GodShopItmInv {
    private static final long serialVersionUID = 3656426167380598344L;

    /** SKU 번호 */
    private String skuNo;

    /** 브랜드 ID */
    private String brndId;
    
    /** LOCATION 번호 */
    private String stagLoc;
    
	/** 재고수량 Integer형 */
	private int invQtyInt;
	
	/** 배송수량 Integer형 */
	private int dlvQtyInt;
	
	/** 판매예정수량 Integer형 */
	private int salePrearngeQtyInt;
	
	/** 안전재고수량 Integer형 */
	private int safeInvQtyInt;
	
	/** 안전 재고 율 사용 여부*/
	private String safeInvRtUseYnEx;
	
	/** 단품 전체매장 총 재고 수량 */
	private Long totUsefulInvQty;
	
	/** 추가 매장ID */
	private String acctnShopId;
	
	/** 재고 Location 1 */
	private Long lc1InvQty;
	
	/** 재고 Location 2 */
	private Long lc2InvQty;
	
	/** 배송매장재고 */
	private Long dlvInvQty;
	
	/** 상품유형코드 */
	private String godTpCd;
	
	/** 입점 구분 코드 >. 자사 : MCOM >. 입점 : PARTMAL */
	private String partmalSectCd;

	private int saleQty;
	
	private String afterItmNo;
	
	private int resultCnt;
	
	/** 매장단품재고 정보 변경 사유 */
	private String modResnCont;
	
	/** 매장단품재고 정보 변경 상세 사유 */
	private String modResnDetailCont;
	
	/** 매장명 */
	private String shopNm;
	
	/** 처리자명 */
	private String regTrNm;
	
	/** 처리채널 */
	private String channel;
	
}
