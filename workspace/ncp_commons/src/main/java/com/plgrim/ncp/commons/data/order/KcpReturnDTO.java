package com.plgrim.ncp.commons.data.order;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class KcpReturnDTO  extends AbstractOrderDTO {
	
	
	String resCd;
	String resMsg;
	
	String tno;// KCP 거래 고유 번호
	String amount;// KCP 실제 거래 금액
	String pntIssue;// 결제 포인트사 코드
	String couponMny;// 쿠폰금액
	
	
	/* ============================================================================== */
    /* =   06. 승인 결과 값 추출                                                    = */
    /* = -------------------------------------------------------------------------- = */
    
	String cardCd; // 카드사 코드
	String cardName; // 카드사 명
	String appTime; // 승인시간
	String appNo; // 승인번호
	String noinf; // 무이자 여부
	String quota; // 할부 개월 수
	String partcancYn; // 부분취소 가능유무
	String cardBinType_01; // 카드구분1
	String cardBinType_02; // 카드구분2
	String cardMny; // 카드결제금액

	/* = -------------------------------------------------------------------------- = */
    /* =   06-1. 신용카드 승인 결과 처리                                            = */
    /* = -------------------------------------------------------------------------- = */
    String pntAmount; // 적립금액 or 사용금액
    String pntAppTime; // 승인시간
    String pntAppNo; // 승인번호
    String addPnt; // 발생 포인트
    String usePnt; // 사용가능 포인트
    String rsvPnt; // 총 누적 포인트
    Long total_amount;// = amount + pnt_amount;                    // 복합결제시 총 거래금액

    /* = -------------------------------------------------------------------------- = */
    /* =   06-2. 계좌이체 승인 결과 처리                                            = */
    /* = -------------------------------------------------------------------------- = */
            
    //String appTime; // 승인시간 중복
    String bankName; // 은행명
    String bankCode; // 은행코드
    String bkMny; // 계좌이체결제금액
            

    /* = -------------------------------------------------------------------------- = */
    /* =   06-3. 가상계좌 승인 결과 처리                                            = */
    /* = -------------------------------------------------------------------------- = */
            
    String bankname; // 입금할 은행 이름
    String depositor; // 입금할 계좌 예금주
    String account; // 입금할 계좌 번호
    String vaDate; // 가상계좌 입금마감시간
            

    /* = -------------------------------------------------------------------------- = */
    /* =   06-4. 포인트 승인 결과 처리                                              = */
    /* = -------------------------------------------------------------------------- = */
//
//    pnt_amount   = c_PayPlus.mf_get_res( "pnt_amount"   ); // 적립금액 or 사용금액
//    pnt_app_time = c_PayPlus.mf_get_res( "pnt_app_time" ); // 승인시간
//    pnt_app_no   = c_PayPlus.mf_get_res( "pnt_app_no"   ); // 승인번호
//    add_pnt      = c_PayPlus.mf_get_res( "add_pnt"      ); // 발생 포인트
//    use_pnt      = c_PayPlus.mf_get_res( "use_pnt"      ); // 사용가능 포인트
//    rsv_pnt      = c_PayPlus.mf_get_res( "rsv_pnt"      ); // 총 누적 포인트
            

    /* = -------------------------------------------------------------------------- = */
    /* =   06-5. 휴대폰 승인 결과 처리                                              = */
    /* = -------------------------------------------------------------------------- = */
            
    //String appTime; // 승인 시간 중복
    String commid; // 통신사 코드
    String mobileNo; // 휴대폰 번호
            

    /* = -------------------------------------------------------------------------- = */
    /* =   06-6. 상품권 승인 결과 처리                                              = */
    /* = -------------------------------------------------------------------------- = */

//    app_time    = c_PayPlus.mf_get_res( "tk_app_time" ); // 승인 시간
//    tk_van_code = c_PayPlus.mf_get_res( "tk_van_code" ); // 발급사 코드
//    tk_app_no   = c_PayPlus.mf_get_res( "tk_app_no"   ); // 승인 번호


    /* = -------------------------------------------------------------------------- = */
    /* =   06-7. 현금영수증 승인 결과 처리                                          = */
    /* = -------------------------------------------------------------------------- = */
    String cashAuthno; // 현금영수증 승인번호
    String cashNo; // 현금영수증 거래번호            
    

    /* = -------------------------------------------------------------------------- = */
    /* =   06-8. 에스크로 여부 결과 처리                                            = */
    /* = -------------------------------------------------------------------------- = */
    String escwYn; // 에스크로 여부
    
    /* = -------------------------------------------------------------------------- = */
    /* =   06. 승인 결과 처리 END                                                   = */
    /* ============================================================================== */

	
    /** 재 승인 거래번호 (휴대폰결제) */
    String tradeReauthNo;
	

}
