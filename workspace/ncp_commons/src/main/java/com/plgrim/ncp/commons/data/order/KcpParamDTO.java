package com.plgrim.ncp.commons.data.order;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(value=Include.ALWAYS)
@EqualsAndHashCode(callSuper = false)
public class KcpParamDTO  extends AbstractOrderDTO implements Serializable{
	
	private static final long serialVersionUID = -1172690892444416114L;
	
	String req_tx; // 요청 종류
	String tran_cd; // 처리 종류

	String cust_ip; // 요청 IP
	String ordr_idxx; // 쇼핑몰 주문번호
	String good_name; // 상품명
	
	String res_cd; // 응답코드
	String res_msg; // 응답 메세지
	String escw_yn; // 에스크로 사용여부
	String tno; // KCP 거래 고유 번호
	String vcnt_yn; // 가상계좌 에스크로 사용 유무
	
	String buyr_name; // 주문자명
	String buyr_tel1; // 주문자 전화번호
	String buyr_tel2; // 주문자 핸드폰 번호
	String buyr_mail; // 주문자 E-mail 주소

	String use_pay_method; // 결제 방법
	String bSucc; // 업체 DB 처리 성공 여부

	String app_time; // 승인시간 (모든 결제 수단 공통)
	String amount; // KCP 실제 거래금액
	String total_amount; // 복합결제시 총 거래금액
	String coupon_mny; // 쿠폰금액

	String card_cd; // 신용카드 코드
	String card_name; // 신용카드 명
	String app_no; // 신용카드 승인번호
	String noinf; // 신용카드 무이자 여부
	String quota; // 신용카드 할부개월
	String partcanc_yn; // 부분취소 가능유무
	String card_bin_type_01; // 카드구분1
	String card_bin_type_02; // 카드구분2
	String card_mny       ; // 카드금액

	String bank_name; // 은행명
	String bank_code; // 은행코드
	String bk_mny; // 계좌이체결제금액

	String bankname; // 입금할 은행명
	String depositor; // 입금할 계좌 예금주 성명
	String account; // 입금할 계좌 번호
	String va_date; // 가상계좌 입금마감시간

	String pnt_issue; // 결제 포인트사 코드
	String pnt_amount; // 적립금액 or 사용금액
	String pnt_app_time; // 승인시간
	String pnt_app_no; // 승인번호
	String add_pnt; // 발생 포인트
	String use_pnt; // 사용가능 포인트
	String rsv_pnt; // 총 누적 포인트

	String commid; // 통신사코드
	String mobile_no; // 휴대폰번호

	String shop_user_id; // 가맹점 고객 아이디
	String tk_van_code; // 발급사코드
	String tk_app_no; // 승인번호

	String cash_yn; // 현금 영수증 등록 여부
	String cash_authno; // 현금 영수증 승인 번호
	String cash_tr_code; // 현금 영수증 발행 구분
	String cash_id_info; // 현금 영수증 등록 번호
	String cash_no; // 현금 영수증 거래 번호

	String escw_used; // 에스크로 사용 여부
	String pay_mod; // 에스크로 결제처리 모드
	String deli_term; // 배송 소요일
	String bask_cntx; // 장바구니 상품 개수
	String good_info; // 장바구니 상품 상세 정보
	String rcvr_name; // 수취인 이름
	String rcvr_tel1; // 수취인 전화번호
	String rcvr_tel2; // 수취인 휴대폰번호
	String rcvr_mail; // 수취인 E-Mail
	String rcvr_zipx; // 수취인 우편번호
	String rcvr_add1; // 수취인 주소
	String rcvr_add2; // 수취인 상세주소
	
	String site_cd;
	String site_name;

	String currency;
	String module_type;

	String enc_info;
	String enc_data;
	String ret_pay_method;



	String ordr_chk;
	String good_expr;
	
	
	// mobile only parameter
	String shop_name;
	String ipgm_date;
	//String Ret_URL;
	String tablet_size;
	String response_type;
	//String PayUrl;
	String traceNo;
	String param_opt_1;
	String param_opt_2;
	String param_opt_3;
	
	String vcnt_expire_term;

	String approval_key;
	
	String encoding_trans;
	/**
	 *    무이자 옵션
     * ※ 설정할부    (가맹점 관리자 페이지에 설정 된 무이자 설정을 따른다)                             - "" 로 설정
     * ※ 일반할부    (KCP 이벤트 이외에 설정 된 모든 무이자 설정을 무시한다)                           - "N" 로 설정
     *※ 무이자 할부 (가맹점 관리자 페이지에 설정 된 무이자 이벤트 중 원하는 무이자 설정을 세팅한다)   - "Y" 로 설정
     * <input type="hidden" name="kcp_noint"       value=""/> 
     * 무이자 설정
     *※ 주의 1 : 할부는 결제금액이 50,000 원 이상일 경우에만 가능
     *※ 주의 2 : 무이자 설정값은 무이자 옵션이 Y일 경우에만 결제 창에 적용
     *예) 전 카드 2,3,6개월 무이자(국민,비씨,엘지,삼성,신한,현대,롯데,외환) : ALL-02:03:04
     *BC 2,3,6개월, 국민 3,6개월, 삼성 6,9개월 무이자 : CCBC-02:03:06,CCKM-03:06,CCSS-03:06:04
     *<input type="hidden" name="kcp_noint_quota" value="CCBC-02:03:06,CCKM-03:06,CCSS-03:06:09"/> 
	 */
	String kcp_noint;
	String kcp_noint_quota;
	
	String disp_tax_yn;//현금영수증 등록창
	


	
	
}
