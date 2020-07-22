package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.commons.data.order.KcpReturnDTO;
import com.plgrim.ncp.commons.data.order.NaverCancelReturnDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class RefundPayDTO extends AbstractDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5836627492662374731L;

	/**
	 * 주문번호
	 */
	private String ordNo;
	/**
	 * 클레임번호
	 */
	private String clmNo;
	/**
	 * 회원번호
	 */
	private String mbrNo;
	/**
	 * 결제번호
	 */
	private String payNo;
	/**
	 * 상위 결제 번호
	 * ST || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String upperPayNo;
	/**
	 * 환불 순번	 
	 */
	private java.lang.Integer rfdTurn;
	/**
	 * 신규결제번호
	 */
	private String newPayNo;
	
	
	/**
	 * 거래유형코드
	 */
	private String dealTpCd;
	/**
	 * 결제수단코드
	 */
	private String payMnCd;
	/**
	 * 결제유형코드
	 */
	private String payTpCd;
	/**
	 * PG승인번호
	 */
	private String pgAprvNo;
	/**
	 * PG구분코드
	 */
	private String pgSectCd;
	/**
	 * 에스크로여부
	 */
	private String escrYn;
	/**
	 * 에스크로상태코드
	 */
	private String escrStatCd;
	/**
	 * 주결제여부
	 */
	private String mpayMnYn;
	/**
	 * 회원쿠폰번호
	 */
	private String mbrCpnNo;
	
	/**
	 * 환불처리여부
	 */
	private String refundYn;
	/**
	 * 환불은행코드
	 */
	private String rfdBnkCd;
	/**
	 * 환불계좌번호
	 */
	private String rfdBnkAcctNo;
	/**
	 * 환불예금주명
	 */
	private String rfdAcnthldrNm;
	
	
	/**
	 * 결제환율적용시작일
	 */
	private java.util.Date exchgRtAplBegDt;
	/**
	 * 취소누적금액
	 */
	private String cnclAcmtlAmt;
	/**
	 * 기준통화결제금액
	 */
	private String stdrCrncyPayAmt;
	/**
	 * 결제통화결제금액
	 */
	private String payCrncyPayAmt;
	/**
	 * 결제통화코드
	 */
	private String payCrncyCd;
	
	
	/**
	 * 원결제 금액
	 */
	private java.math.BigDecimal oriStdrCrncySumAmt;
	/**
	 * 원결제 취소 누적금액
	 */
	private java.math.BigDecimal oriCnclAcmtlAmt;
	
	
	/**
	 * 등록자ID
	 */
	private String regtrId;
	
	/**
	 * 환불사유
	 */
	private String rfdResn;
	
	
	/**
	 * 환불 성공 여부
	 */
	private boolean rfdSuccess;
	/**
	 * 환불결과메시지
	 */
	private String rfdRsltmsg;
	/**
	 * 재 승인 거래번호 (휴대폰결제)
	 */
	private String tradeReauthNo;
	/**
	 * 클레임 사유 코드
	 * ㅁ. 클레임 사유 : CLM_RSN
	 *    >. 단순변심 : SIMPL_CHGMIND
	 *    >. 옵션변경 : OPT_CHG
	 *    >. 상품불량 : GOD_BADN
	 *    >. 상품파손 : GOD_DMG
	 *    >. 상품정보상이 : GOD_INFO_GAP
	 *    >. 오배송 : WN_DLV
	 *    >. 품절 : SLDOUT
	 *    >. 배송지연 : DLV_DELAY
	 *    >. 가격차이 : PRC_GAP
	 *    >. 상품정보오류 : GOD_INFO_ERR
	 *    >. 주문입력오류 : ORD_ENT_ERR
	 *    >. 수량변경 : QTY_MOD
	 *    >. 택배분실 : HDRY_LOST
	 *    >. 수선 : REPAIR
	 *    >. 기타 : ETC	 
	 */
	private String clmResnCd;


	/**
	 * 클레임 사유 내용	 
	 */
	private String clmResnCont;

	private String clmTpCd;
	
	private String ordStatCd;
	
	
	
	/**
	 * PG 취소 결과 - KCP
	 */
	private KcpReturnDTO kcpReturnDTO;
	
	/**
	 * PG 취소 결과 - 네이버페이
	 */
	private NaverCancelReturnDTO naverCancelReturnDTO;
}
