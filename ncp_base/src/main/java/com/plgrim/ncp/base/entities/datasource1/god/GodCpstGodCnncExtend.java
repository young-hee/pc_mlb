/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : jackie(jackie)
 * @date : 20150610
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 상품 구성 상품 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godCpstGodCnncExtend")
public class GodCpstGodCnncExtend extends GodCpstGodCnnc{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 구성 상품 명	 
	 */
	private String cpstMobileGodNm;	
	
	/**
	 * 구성상품 ERP 상품 번호
	 */
	private String erpGodNo;

	/**
	 * 구성상품 KC 인증 번호
	 */
	private String kcCrtfcNo;	
	
	/**
	 * 예약 판매 상품 여부	 
	 */
	private String resveSaleGodYn;

	/**
	 * 정소가
	 */
	private java.math.BigDecimal rtlPrc;


	/**
	 * 실소가
	 * ㅁ. 입력시
	 *    >. <상품 통화별 가격.기준 통화 금액> 입력
	 *
	 * ㅁ. 수정시
	 *    >. <상품 통화별 가격.기준 통화 금액>과 <상품 통화별 가격.환율 통화 금액>을 사용하여 환율을 구하여
	 *    >. <상품 통화별 가격.통화별 실소가>가 수정 되어야 한다.
	 */
	private java.math.BigDecimal csmrPrc;
	
	/**
	 * 구성 상품 사이즈 조견표
	 */
	private String cpstSizeLktbHtml;
	
	/**
	 * 구성 상품 모바일 사이즈 조견표
	 */
	private String cpstMobileSizeLktbHtml;
	
	/**
	 * 구성 상품 세탁 이미지 목록
	 */
	private List<GodLndrImg> cpstLndrImgList;

}
