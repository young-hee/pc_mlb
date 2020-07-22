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
 * 시스템 브랜드 품목 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysBrndPrdlstHist")
public class SysBrndPrdlstHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 브랜드 ID
	 * ㅁ. 온라인에서 사용하는 브랜드 ID	 
	 */
	private String brndId;


	/**
	 * 품목 코드
	 * ㅁ. ERP과 연동하여 처리 된건은 2자리	 
	 */
	private String prdlstCd;


	/**
	 * 이력 일시	 
	 */
	private java.util.Date histDt;


	/**
	 * 품목 명	 
	 */
	private String prdlstNm;


	/**
	 * 품목 그룹 코드
	 * ㅁ. 품목 그룹 : PRDLST_GRP
	 *    >. 의류 : PDL_CLTHS
	 *    >. 구두/신발 : PDL_SHOE
	 *    >. 가방 : PDL_BAG
	 *    >. 패션잡화(모자/벨트/액세서리) : PDL_ACSR
	 *    >. 침구류/커튼 : PDL_BEDD
	 *    >. 가구(침대/소파/싱크대/DIY제품) : PDL_FNTR
	 *    >. 주방용품 : PDL_KTCH_SUPLI
	 *    >. 화장품 : PDL_CSMT
	 *    >. 귀금속/보석시계류 : PDL_JWL
	 *    >. 서적 : PDL_BUK
	 *    >. 디지털콘텐츠(음원,게임,인터넷강의등) : PDL_DGT_CONTT
	 *    >. 영유아용품 : PDL_INFNT
	 *    >. 악기 : PDL_ISTM
	 *    >. 스포츠용품 : PDL_SPORTS_SUPLI
	 *    >. 기타 : PDL_ETC	 
	 */
	private String prdlstGrpCd;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


	/**
	 * 수선 가능 여부	 
	 */
	private String repairPsbYn;


	/**
	 * 매장 수선 수수료 구분 코드
	 * ㅁ. 매장 수선 수수료 구분 : SHOP_REPAIR_FEE_SECT
	 *    >. 정액 : FIXAMT
	 *    >. 정률 : FIXRT	 
	 */
	private String shopRepairFeeSectCd;


	/**
	 * 매장 수선 수수료 금액
	 * 할인 유형별 차감되는 할인 금액	 
	 */
	private java.math.BigDecimal shopRepairFeeAmt;


	/**
	 * 매장 수선 수수료 율
	 * 할인 유형별 차감되는 할인 율	 
	 */
	private java.math.BigDecimal shopRepairFeeRt;


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
