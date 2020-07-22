/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.cso;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 고객서비스 업무 요청
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("csoJobRequst")
public class CsoJobRequst extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 요청 일련번호	 
	 */
	private java.lang.Long requstSn;


	/**
	 * 이관 상태 코드
	 * ㅁ. 이관 상태 : TRANS_STAT
	 *    >. 이관 대기 : TRANS_WAIT
	 *    >. 이관 취소 : TRANS_CNCL
	 *    >. 이관 완료 : TRANS_COMPT	 
	 */
	private String transStatCd;


	/**
	 * 담당 업무 유형 코드
	 * ㅁ. 담당 업무 유형
	 *    >. 상담원 이관 : CNSLR_TRANS
	 *    >. 업체 이관 : COM_TRANS
	 *       >>. 물류센터 : LGIST_CNTR
	 *       >>. 수선/OFF : REPAIR_OFF
	 *       >>. 고객센터 : CC
	 *       >>. 제휴몰 : AFF_MALL
	 *       >>. 입점업체 : PARTMAL_COM
	 *    >. 업무 이관 : JOB_TRANS
	 *       >>. 상품관련(MD) : GOD_RELAT
	 *       >>. 마케팅/정산/기타 : MARKT_CAL_ETC
	 *       >>. 시스템 : SYS	 
	 */
	private String chrgJobTpCd;


	/**
	 * 이관 요청 유형 코드
	 * ㅁ. 이관 요청 유형
	 *    >. 상담원 이관 : CNSLR_TRANS
	 *    >. 업체 이관 : COM_TRANS
	 *    >. 업무 이관 : JOB_TRANS
	 *       >>. 상품관련(MD) : GOD_RELAT
	 *          >>>. 상품상세문의 : GOD_DETAIL_INQ
	 *          >>>. 재고확인요청(RT등) : INV_CNFIRM_REQUST
	 *          >>>. 품절처리요청(미전시) : SLDOUT_PRCS_REQUST
	 *          >>>. 입고일정문의 : WHSG_FCST_INQ
	 *          >>>. 이벤트(사은품)문의 : EVT_GFT_INQ
	 *          >>>. 웹정보오류확인 : WEB_INFO_ERR_CNFIRM
	 *          >>>. 코디.룩북상품문의 : CODI_MGZN_GOD_INQ
	 *          >>>. 기타 : ETC
	 *       >>. 마케팅/정산/기타 : MARKT_CAL_ETC
	 *          >>>. 이벤트문의 : EVT_INQ
	 *          >>>. 쿠폰문의 : CPN_INQ
	 *          >>>. 수동환불요청 : PASSIV_RFD_REQUST
	 *          >>>. 매장재고문의 : SHOP_INV_INQ
	 *          >>>. 기타 : ETC
	 *       >>. 시스템 : SYS
	 *          >>>. 클레임수동완료 : CLM_PASSIV_COMPT
	 *          >>>. 클레임접수오류 : CLM_RCEPT_ERR	 
	 */
	private String transRequstTpCd;


	/**
	 * 중요도 코드
	 * ㅁ. 중요도 : DOI
	 *    >. 즉시 : IMDTL
	 *    >. 긴급 : EMERG
	 *    >. 일반 : GNRL	 
	 */
	private String doiCd;


	/**
	 * 등록 관리자 ID	 
	 */
	private String regAdminId;


	/**
	 * 요청 접수 관리자 ID	 
	 */
	private String requstRceptAdminId;


	/**
	 * 요청 제목
	 * ㅁ. 상담의 목적을 간략하게 표현 함	 
	 */
	private String requstSj;


	/**
	 * 요청 내용
	 * ㅁ. 고객의 질문의 주요 내용을 기술한다.	 
	 */
	private String requstCont;


	/**
	 * 상담 일련번호	 
	 */
	private java.lang.Long cnsltSn;


	/**
	 * 상담 상세 순번
	 * ㅁ. 상담상세 이력에 대한 순번으로 상담 번호별 순번을 발급하도록 한다.
	 *    >. "고객 상담 상세 이력.상담 상세 순번"의 MAX값 + 1로 등록 되도록 한다.
	 *    >. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 *       (ORACLE 기준 Format)	 
	 */
	private java.lang.Integer cnsltDetailTurn;


	/**
	 * 이관 요청 순번	 
	 */
	private java.lang.Integer transRequstTurn;


	/**
	 * 이관 요청 일시
	 * ㅁ. 상담 이관 요청한 일자를 의미하며, 요청 시점의 초단위까지 관리 한다.
	 *    >. YYYYMMDD HH24:MI:SS
	 *       (ORACLE 기준 Format)	 
	 */
	private java.util.Date transRequstDt;


	/**
	 * 이관 취소 일시
	 * ㅁ. 상담 이관 취소한 일자	 
	 */
	private java.util.Date transCnclDt;


	/**
	 * 이관 완료 일시	 
	 */
	private java.util.Date transComptDt;


	/**
	 * 몰 ID	 
	 */
	private String mallId;


	/**
	 * 언어 코드	 
	 */
	private String langCd;


	/**
	 * 등록자 ID	 
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
