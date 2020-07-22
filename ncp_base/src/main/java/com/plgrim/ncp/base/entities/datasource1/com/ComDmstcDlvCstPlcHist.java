/**
 * @author : Generator(Generator)
 * @date : 2018-05-29
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 업체 국내 배송비 정책 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comDmstcDlvCstPlcHist")
public class ComDmstcDlvCstPlcHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 국내 배송비 정책 일련번호	 
	 */
	private java.lang.Long dmstcDlvCstPlcSn;


	/**
	 * 이력 일시	 
	 */
	private java.util.Date histDt;


	/**
	 * 배송 정책 명	 
	 */
	private String dlvPlcNm;


	/**
	 * 업체 ID	 
	 */
	private String comId;


	/**
	 * 기본 배송비 정책 여부	 
	 */
	private String baseDlvCstPlcYn;


	/**
	 * 회수 지시 가능 여부	 
	 */
	private String rtrvlDrctPsbYn;


	/**
	 * 해외 배송 국내 배송비 정책 여부
	 * ㅁ. 해외 배송시 사용되는 국내 배송비 정책 여부를 결정하는 컬럼	 
	 */
	private String ovseaDlvDmstcDlvCstPlcYn;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


	/**
	 * 배송 수단 코드
	 * 사용코드 : DLV_MN    배송 수단
	 * HDRY    택배
	 * SHOP_PKUP    매장픽업
	 * DHL    DHL
	 * OVSEA_POST    해외우편
	 * GNRL_POST    일반우편
	 * RGIST_POST    등기우편
	 * CVNSTOR_HDRY    편의점택배	 
	 */
	private String dlvMnCd;


	/**
	 * 배송 업체 코드
	 * 사용코드 : DLV_COM    배송 업체
	 * SHOP_PKUP    매장픽업
	 * ONSLF_DLV    자체배송
	 * ACIEX    ACI Express
	 * AIRBE    AirBoyExpress
	 * CJGLS    CJ-GLS(HTH통합)
	 * CVSNT    CVSnet
	 * INLOS    GSM 국제택배
	 * KGBLS    KGB택배
	 * KTLOG    KT로지스
	 * WIZWA    WIZWA
	 * KDEXP    경동택배
	 * KLOGI    고려택배
	 * KNHTM    나이트맨
	 * NEDEX    네덱스
	 * DAZEN    다젠
	 * DAESN    대신택배
	 * DRTDR    대한통운
	 * KGLOP    대한통운(미국상사)
	 * DNGBU    동부익스프레스
	 * LOEXE    동원로엑스
	 * LOGEN    로젠택배
	 * SCLOG    사가와 익스프레스
	 * JUSOU    스피디익스프레스
	 * SEDEX    한진드림익스프레스
	 * YELLW    옐로우캡
	 * POSTOFC_EMS    우체국EMS
	 * POSTOFC_RGIST    우체국등기
	 * POSTOFC_HDRY    우체국택배
	 * INNOG    이노지스택배
	 * EZENS    이젠택배
	 * ILYNG    일양로지스택배
	 * C1001    천일택배
	 * TRANT    트라넷
	 * HANAR    하나로택배
	 * HANJN    한진택배
	 * HYDEX    현대택배
	 * FAMIL    훼미리택배	 
	 */
	private String dlvComCd;


	/**
	 * 배송 가능 일	 
	 */
	private String dlvPsbDay;


	/**
	 * 배송지 우편번호	 
	 */
	private String dlvspPostNo;


	/**
	 * 배송지 기본주소	 
	 */
	private String dlvspBaseAddr;


	/**
	 * 배송지 상세주소	 
	 */
	private String dlvspDetailAddr;


	/**
	 * 국내 배송 박스 가능 부피
	 * ㅁ. 가능 부피나 무게 둘중 하나만 넘어서도 추가 박스로 처리	 
	 */
	private java.math.BigDecimal dmstcDlvBoxPsbVol;


	/**
	 * 국내 배송 박스 가능 무게
	 * ㅁ. 가능 부피나 무게 둘중 하나만 넘어서도 추가 박스로 처리	 
	 */
	private java.math.BigDecimal dmstcDlvBoxPsbWt;


	/**
	 * 배송비 부과 구분 코드
	 * ㅁ. 배송비 부과 구분 : DLV_CST_LEVY_SECT
	 *    >. 무료 : FREE
	 *    >. 유료 : PCHRG(사용안함)
	 *    >. 조건부무료 : COND_FREE	 
	 */
	private String dlvCstLevySectCd;


	/**
	 * 국내 배송비 면제 기준 금액	 
	 */
	private java.math.BigDecimal dmstcDlvCstExmStdrAmt;


	/**
	 * 국내 배송비	 
	 */
	private java.math.BigDecimal dmstcDlvCst;


	/**
	 * 퀵배송비 부과 구분 코드
	 * ㅁ. 퀵배송비 부과 구분 : QDLV_CST_LEVY_SECT
	 *    >. 무료 : FREE
	 *    >. 유료 : PCHRG
	 *    >. 조건부무료 : COND_FREE	 
	 */
	private String qdlvCstLevySectCd;


	/**
	 * 퀵배송비 면제 기준 금액	 
	 */
	private java.math.BigDecimal qdlvCstExmStdrAmt;


	/**
	 * 퀵배송비	 
	 */
	private java.math.BigDecimal qdlvCst;


	/**
	 * 수선 배송비 부과 구분 코드
	 * ㅁ. 수선 배송비 부과 구분 : REPAIR_DLV_CST_LEVY_SECT
	 *    >. 무료 : FREE
	 *    >. 유료 : PCHRG	 
	 */
	private String repairDlvCstLevySectCd;


	/**
	 * 수선 배송비	 
	 */
	private java.math.BigDecimal repairDlvCst;


	/**
	 * 반송 수단 코드
	 * 사용코드 : DLV_MN    배송 수단
	 * HDRY    택배
	 * APPN_HDRY    지정 택배
	 * SHOP_PKUP    매장픽업
	 * DHL    DHL
	 * OVSEA_POST    해외우편
	 * GNRL_POST    일반우편
	 * RGIST_POST    등기우편
	 * CVNSTOR_HDRY    편의점택배	 
	 */
	private String retrnMnCd;


	/**
	 * 반송 업체 코드
	 * 사용코드 : DLV_COM    배송 업체
	 * SHOP_PKUP    매장픽업
	 * ONSLF_DLV    자체배송
	 * ACIEX    ACI Express
	 * AIRBE    AirBoyExpress
	 * CJGLS    CJ-GLS(HTH통합)
	 * CVSNT    CVSnet
	 * INLOS    GSM 국제택배
	 * KGBLS    KGB택배
	 * KTLOG    KT로지스
	 * WIZWA    WIZWA
	 * KDEXP    경동택배
	 * KLOGI    고려택배
	 * KNHTM    나이트맨
	 * NEDEX    네덱스
	 * DAZEN    다젠
	 * DAESN    대신택배
	 * DRTDR    대한통운
	 * KGLOP    대한통운(미국상사)
	 * DNGBU    동부익스프레스
	 * LOEXE    동원로엑스
	 * LOGEN    로젠택배
	 * SCLOG    사가와 익스프레스
	 * JUSOU    스피디익스프레스
	 * SEDEX    한진드림익스프레스
	 * YELLW    옐로우캡
	 * POSTOFC_EMS    우체국EMS
	 * POSTOFC_RGIST    우체국등기
	 * POSTOFC_HDRY    우체국택배
	 * INNOG    이노지스택배
	 * EZENS    이젠택배
	 * ILYNG    일양로지스택배
	 * C1001    천일택배
	 * TRANT    트라넷
	 * HANAR    하나로택배
	 * HANJN    한진택배
	 * HYDEX    현대택배
	 * FAMIL    훼미리택배	 
	 */
	private String retrnComCd;


	/**
	 * 반송 우편번호	 
	 */
	private String retrnPostNo;


	/**
	 * 반송 기본주소	 
	 */
	private String retrnBaseAddr;


	/**
	 * 반송 상세주소	 
	 */
	private String retrnDetailAddr;


	/**
	 * 판매자 귀책 반품 배송비	 
	 */
	private java.math.BigDecimal slrImptRtgodDlvCst;


	/**
	 * 판매자 귀책 교환 배송비	 
	 */
	private java.math.BigDecimal slrImptExchgDlvCst;


	/**
	 * 반품 출고 배송비 포함 여부
	 * ㅁ. 구매자 귀책이며 반품 진행될 경우 주문시 할인 또는 면제 받은 배송비를 포함 할지 여부	 
	 */
	private String rtgodDlivyDlvCstInclsYn;


	/**
	 * 구매자 귀책 반품 배송비	 
	 */
	private java.math.BigDecimal buyerImptRtgodDlvCst;


	/**
	 * 구매자 귀책 교환 배송비	 
	 */
	private java.math.BigDecimal buyerImptExchgDlvCst;


	/**
	 * 배송 방법 설명	 
	 */
	private String dlvMthdDscr;


	/**
	 * 모바일 배송 방법 설명	 
	 */
	private String mobileDlvMthdDscr;


	/**
	 * 상품권 설명	 
	 */
	private String gfctDscr;


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
