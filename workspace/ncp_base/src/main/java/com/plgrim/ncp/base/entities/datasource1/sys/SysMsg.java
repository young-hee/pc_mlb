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
 * 시스템 메시지
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysMsg")
public class SysMsg extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 메시지 번호	 
	 */
	private String msgNo;


	/**
	 * 메시지 사용 디바이스 코드
	 * ㅁ. 메시지 사용 디바이스 : MSG_USE_DVC
	 *    >. PC + 모바일 : PC_MOBILE
	 *    >. PC : PC
	 *    >. 모바일 : MOBILE	 
	 */
	private String msgUseDvcCd;


	/**
	 * 메시지 시스템 분류 코드
	 * ㅁ. 메시지 시스템 분류 : MSG_SYS_CLFC
	 *    >. 카카오톡 : KKO_TAK
	 *    >. 응답코드 : RSPNS_CD	 
	 */
	private String msgSysClfcCd;


	/**
	 * 메시지 분류 코드
	 * ㅁ. 메시지 분류 : MSG_CLFC
	 *    >. 카카오톡 : KKO_TAK
	 *     >>. 공통 : KKO_CMMN
	 *     >>. 회원 : KKO_MBR
	 *     >>. 마이페이지 : KKO_MY_PGE
	 *     >>. 상품 : KKO_GOD
	 *     >>. 주문 : KKO_ORD
	 *     >>. 장바구니 : KKO_BSK
	 *     >>. 전시 : KKO_DSP
	 *     >>. 제휴 : KKO_AFF
	 *     >>. 이벤트 : KKO_EVT
	 *     >>. HelpDesk : KKO_HELPDESK
	 *     >>. 교환 : KKO_EXCHG
	 *     >>. 반품 : KKO_RTGOD
	 *     >>. 배송 : KKO_DLV
	 *     >>. 픽업 : KKO_PKUP
	 *     >>. 기타 : KKO_ETC
	 *    >. 응답코드 : RSPNS_CD
	 *     >>. 공통 : CMMN	 
	 */
	private String msgClfcCd;


	/**
	 * 메시지 상세 분류 코드
	 * ㅁ. 메시지 분류 : MSG_CLFC
	 *    >. 카카오톡 : KKO_TAK
	 *      >>. 공통 : KKO_CMMN
	 *        >>>. 공통 : KKO_CMMN
	 *      >>. 회원 : KKO_MBR
	 *        >>>. 회원 : KKO_MBR
	 *      >>. 마이페이지 : KKO_MY_PGE
	 *        >>>. 마이페이지 : KKO_MY_PGE
	 *      >>. 상품 : KKO_GOD
	 *        >>>. 상품 : KKO_GOD
	 *      >>. 주문 : KKO_ORD
	 *        >>>. 주문 : KKO_ORD
	 *      >>. 장바구니 : KKO_BSK
	 *        >>>. 장바구니 : KKO_BSK
	 *      >>. 전시 : KKO_DSP
	 *        >>>. 전시 : KKO_DSP
	 *      >>. 제휴 : KKO_AFF
	 *        >>>. 제휴 : KKO_AFF
	 *      >>. 이벤트 : KKO_EVT
	 *        >>>. 이벤트 : KKO_EVT
	 *      >>. HelpDesk : KKO_HELPDESK
	 *        >>>. HelpDesk : KKO_HELPDESK
	 *      >>. 교환 : KKO_EXCHG
	 *        >>>. 신청 : REQST
	 *        >>>. 결제 대기 : PAY_WAIT
	 *        >>>. 접수 : RCEPT
	 *      >>. 반품 : KKO_RTGOD
	 *        >>>. 신청 : REQST
	 *        >>>. 접수 : RCEPT
	 *      >>. 배송 : KKO_DLV
	 *        >>>. 상품준비중 : GOD_PRPARE
	 *        >>>. 배송중 : DLV_PROGRS
	 *      >>. 픽업 : KKO_PKUP
	 *        >>>. 상품준비중 : GOD_PRPARE
	 *        >>>. 준비 완료 : PRPARE_COMPT
	 *      >>. 기타 : KKO_ETC
	 *        >>>. 기타 : KKO_ETC
	 *    >. 응답코드 : RSPNS_CD
	 *      >>. 공통 : CMMN
	 *        >>>. 공통 : CMMN	 
	 */
	private String msgDetailClfcCd;


	/**
	 * 메시지 등급 코드
	 * ㅁ. 메시지 등급 : MSG_GRD
	 *    >. 경고 : WARN
	 *    >. 오류 : ERR
	 *    >. 알림 : NTCN
	 *    >. 확인 : CONF	 
	 */
	private String msgGrdCd;


	/**
	 * 글로벌 사용 여부
	 * ㅁ. N인 경우 시스템 메시지 언어 필수 등록 제외	 
	 */
	private String glbUseYn;


	/**
	 * 메시지 제목	 
	 */
	private String msgSj;


	/**
	 * PC 메시지 내용	 
	 */
	private String pcMsgCont;


	/**
	 * 모바일 메시지 내용	 
	 */
	private String mobileMsgCont;


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