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
 * 고객서비스 채팅 문의
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("csoChatInq")
public class CsoChatInq extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 채팅 문의 일련번호	 
	 */
	private java.lang.Long chatInqSn;


	/**
	 * 문의 유형 코드
	 * ㅁ. 고객 상담 문의에 대한 형태를 관리함
	 * 
	 * ㅁ. 문의 유형 : INQ_TP
	 *    >. 상품문의 : GOD_INQ
	 *    >. 취소 : CNCL
	 *    >. 교환/반품 : EXCHG_RTGOD
	 *    >. 배송문의 : DLV_INQ
	 *    >. 아웃바운드 : OUTBND
	 *    >. 주문/결제 : ORD_SANCTN
	 *    >. 회원 : MBR
	 *    >. 포인트/이벤트/쿠폰/마일리지 : PNT_EVT_CPN_MILE
	 *    >. 모바일앱이용 : MOBILE_APP_USE
	 *    >. 매장구매후문의/매장문의 : SHOP_PCH_SHOP_INQ
	 *    >. A/S : AS
	 *    >. 이용불편/기타 : USE_INCONV_ETC
	 *    >. 배송 매장 문의 : DLV_SHOP_INQ
	 *    >. 픽업 매장 문의 : PKUP_SHOP_INQ	 
	 */
	private String inqTpCd;


	/**
	 * 회원 번호
	 * ㅁ. 회원 가입시 부여되는 고유한 관리 번호
	 *    >. MB || YYYYMMDD || 7자리 Cycle	 
	 */
	private String mbrNo;


	/**
	 * 문의 고객 명
	 * ㅁ. 삼담을 요청한 고객의 성명	 
	 */
	private String inqCstmrNm;


	/**
	 * 문의 제목
	 * ㅁ. 상담의 목적을 간략하게 표현 함
	 *        (정책서v0.6-반품 접수 등 고객이 증빙자료를 제출해야할 경우 게시판을 활용. 현재 개인 웹메일 계정을 이용한다고 함)	 
	 */
	private String inqSj;


	/**
	 * 처리 완료 일시
	 * ㅁ. 문의에 대해 완료한 일시를 의미 함
	 *    >. YYYYMMDD HH24:MI:SS
	 *       (ORACLE 기준 Format)	 
	 */
	private java.util.Date prcsComptDt;


	/**
	 * 시나리오 채팅 여부	 
	 */
	private String senarioChatYn;


	/**
	 * 채팅 티켓 ID	 
	 */
	private String chatTicketId;


	/**
	 * 채팅 이관 여부	 
	 */
	private String chatTransYn;


	/**
	 * 몰 ID	 
	 */
	private String mallId;


	/**
	 * 언어 코드
	 * ㅁ. 코드 테이블 규칙에 따라 대문자를 사용한 ISO 639 표준에 따른다
	 * 
	 * ㅁ. 언어 : LANG
	 *    >. 한국어 : KOR
	 *    >. 중국어 : CHI
	 *    >. 영어 : ENG
	 *    >. 일본어 : JPN	 
	 */
	private String langCd;


	/**
	 * 인입 경로 구분 코드
	 * ㅁ. 인입된 경로를 정의
	 * 
	 * ㅁ. 인입 경로 구분 : LDIN_PATH_SECT
	 *    >. PC : PC
	 *    >. 모바일 웹 : MOBILE_WEB
	 *    >. 모바일 앱 : MOBILE_APP
	 *    >. 외부 메신저 : EXTRL_CHAT
	 *    >. QR 코드 : QR_CD	 
	 */
	private String ldinPathSectCd;


	/**
	 * 인입 매체 유형 코드
	 * ㅁ. 인입 매체 유형 : LDIN_MEDIA_TP
	 *    >. 카카오톡 상담톡 : KKO_TAK_CNSLT_CHAT
	 *    >. 카카오톡 알림톡 : KKO_TAK_NTCN_CHAT
	 *    >. 라인 : LNE
	 *    >. 네이버 톡톡 : NAVER_CHAT	 
	 */
	private String ldinMediaTpCd;


	/**
	 * 인입 화면 구분 코드
	 * ㅁ. 인입 화면 구분 : LDIN_SCRIN_SECT
	 *    >. 상품 상세 : C_GOD_DETAIL
	 *    >. 기획전 상세 : C_PROMT_DETAIL
	 *    >. 이벤트 상세 : C_EVT_DETAIL
	 *    >. 마이페이지 주문/배송 조회 : C_MY_PGE_ORD_DLV
	 *    >. 마이페이지 취소/교환/반품 조회 : C_MY_PGE_CNCL_EXCHG_RTGOD
	 *    >. 마이페이지 그외 : C_MY_PGE_ETC
	 *    >. 고객 센터 : C_CSTMR_CNTR
	 *    >. A/S 현황 조회 : C_AS_STTUS
	 *    >. 멤버십 안내 : C_MBSH_GUD
	 *    >. 장바구니 : C_BSK
	 *    >. 주문/결제 : C_ORD_PAY
	 *    >. 기타 : C_ETC	 
	 */
	private String ldinScrinSectCd;


	/**
	 * 답변 평가 코드
	 * ㅁ. 답변 평가 : ANS_EVL
	 *    >. 매우 불만족 : VDSTF
	 *    >. 불만족 : DSTF
	 *    >. 보통 : NORM
	 *    >. 만족 : SATSFC
	 *    >. 매우 만족 : VSATSFC	 
	 */
	private String ansEvlCd;


	/**
	 * 답변 평가 내용	 
	 */
	private String ansEvlCont;


	/**
	 * 답변 평가 작성 일시	 
	 */
	private java.util.Date ansEvlWritngDt;


	/**
	 * 답변 평가 관리자 답변 내용	 
	 */
	private String ansEvlAdminAnsCont;


	/**
	 * 답변 평가 관리자 답변 일시	 
	 */
	private java.util.Date ansEvlAdminAnsDt;


	/**
	 * 사용자 트래킹 ID	 
	 */
	private String utid;


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
