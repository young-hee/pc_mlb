package com.plgrim.ncp.base.enums;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 반드시 enum 속성은 상수이기 때문에 대문자로 선언 한다. 
 * 예) USER_ID_INPUT_NAME enum를 String값 비교 할때는
 * if (EnumSample.USER_ID_INPUT_NAME.equals("비교값"))
 */
public enum OrderEnum {

	ORD("ord"), CLM("clm"), KOR("KOR"), KRW("KRW"),
	
	/**
	 * 주문상태 코드
	 * @author user
	 */
	ORD_STAT_ALL_CNCL("ALL_CNCL"), // >.전체 취소 :
	ORD_STAT_DEPST_WAIT("DEPST_WAIT"), // >.입금 대기 :
	ORD_STAT_DLV_COMPT("DLV_COMPT"), // >.배송 완료 :
	ORD_STAT_DLV_PROGRS("DLV_PROGRS"), // >.배송중 :
	ORD_STAT_DLV_PRPARE("DLV_PRPARE"), // >.배송준비 :
	ORD_STAT_PAY_COMPT("PAY_COMPT"), // >.결제 완료 :
	ORD_STAT_PAY_WAIT("PAY_WAIT"), // >.결제 대기 :
	ORD_STAT_RESVE_ORD_DEPST_WAIT("RESVE_ORD_DEPST_WAIT"), // >.예약 주문 입금 대기:
	ORD_STAT_RESVE_ORD_PAY_COMPT("RESVE_ORD_PAY_COMPT"), // >.예약 주문 결제 완료:
	
	
	/**매장코드*
	 * 
	 */
	ORD_SALE_SHOP_CD_WMS("X30004"),

	/**
	 * 배송 코드
	 * @author user
	 */
	DLVSP_ORD_DLVSP("ORD_DLVSP"), // >. 주문 배송지 :
	DLVSP_CLM_PCUPSPP("CLM_PCUPSP"), // >. 클레임 수거지 :
	DLVSP_CLM_DLVSP("CLM_DLVSP"), // >. 클레임 배송지 :
	
	DLV_STAT_DLIVY_COMPT("DLIVY_COMPT"), // >. 출고 완료
	DLV_STAT_DLIVY_DRCT("DLIVY_DRCT"), // >. 출고지시
	DLV_STAT_DLIVY_DRCT_CNCL("DLIVY_DRCT_CNCL"), // >. 출고지시취소
	DLV_STAT_DLV_COMPT("DLV_COMPT"), // >. 배송 완료
	DLV_STAT_DLV_WAIT("DLV_WAIT"), // >. 배송대기
	DLV_STAT_DLIVY_DRCT_WAIT("DLIVY_DRCT_WAIT"), // >. 출고지시대기
	DLV_STAT_LSSGS_RCEPT("LSSGS_RCEPT"), // >. 결품접수
	DLV_STAT_SHOP_GOD_PRPARE_COMPT("SHOP_GOD_PRPARE_COMPT"), // >. 매장 상품 준비 완료
	
	/**
	 * 주문타입 코드
	 * @author user
	 */
	ORD_TP_CD_GNRL_ORD("GNRL_ORD"), // >. 일반주문 :
	ORD_TP_CD_LAG_QTY_ORD("LAG_QTY_ORD"), // >. 대량주문 :
	ORD_TP_CD_RESVE_ORD("RESVE_ORD"), // >. 예약주문 :
	ORD_TP_CD_SHOP_PKUP_ORD("SHOP_PKUP_ORD"), // >. 매장 수령 주문 :

	/**
	 * 결제 수단 코드
	 * @author user
	 */
	// BSK_CPN("BSK_CPN"), // 장바구니쿠폰
	CREDT_CARD_PAY("CREDT_CARD_PAY"), // 신용카드결제
	// DLV_CST_CPN("DLV_CST_CPN"), // 배송비쿠폰
	// EVT_PNT_PAY("EVT_PNT_PAY"), // 이벤트포인트결제
	// GFCT_PAY("GFCT_PAY"), // 상품권결제
	// GOD_CPN("GOD_CPN"), // 상품쿠폰
	// MBSH_PNT_PAY("MBSH_PNT_PAY"), // 멤버쉽포인트결제
	MOBIL_PON_PAY("MOBIL_PON_PAY"), // 휴대폰결제
	// NON_BNKBOK_PAY("NON_BNKBOK_PAY"), // 무통장결제
	RLTM_BNK_ACCT_PAY("RLTM_BNK_ACCT_PAY"), // 실시간계좌결제
	VIRTL_BNK_ACCT_PAY("VIRTL_BNK_ACCT_PAY"), // 가상계좌결제
	WEB_PNT_PAY("WEB_PNT_PAY");// 웹포인트결제

	/**
	 * 결제수단
	 */
	public enum PAY_MN_CD {
		CREDT_CARD_PAY, MOBIL_PON_PAY, RLTM_BNK_ACCT_PAY, VIRTL_BNK_ACCT_PAY, NAVER_PAY
	}
	
	public enum kcpPayComp {
		CREDT_CARD_PAY("CREDT_CARD_PAY","100000000000"), //신용카드결제
		RLTM_BNK_ACCT_PAY("RLTM_BNK_ACCT_PAY","010000000000"), // 실시간계좌결제
		VIRTL_BNK_ACCT_PAY("VIRTL_BNK_ACCT_PAY","001000000000"), // 가상계좌결제
		MOBIL_PON_PAY("MOBIL_PON_PAY","000010000000"); // 휴대폰결제
		
		private final String localValue;
		private final String kcpValue;
		
		kcpPayComp(String localValue,String kcpValue){
            this.localValue = localValue;
            this.kcpValue = kcpValue;
        }
        
        public String getLocalValue(){
            return this.localValue;
        }
        public String getKcpValue(){
            return this.kcpValue;
        }
        
	}
	
	

	public enum AUTO_RFD_SECT {
	   CC_CNFIRM_RFD,ALL_AUTO_RFD,PART_AUTO_RFD
	}
	
	/**
	 * 결제사
	 * @author ka2
	 *
	 */
	public enum PG_SECT_CD {
		KCP_PAY, NAVER_PAY, NON_BNKBOK_PAY
	}

	/**
	 * 패키지상품
	 */
	public enum PACKAGE_GOODS_TYPE {
		SET_GOD, PCKAGE_GOD;
	}

	/**
	 * 구매이력 ERP 전송
	 */
	public enum ORD_ERP_TRNSMIS {
		ERP_OR_ERP, // 구매 이력 증가 전송
		R, // 구매 이력 전송 성공
		E // 구매 이력 전송 실패
	}
	
	/**
	 * bpSalesSave
	 */
	public enum BpSalesSave {
		M, // 포인트사용
		U, // 일모카드사용
		KRW // 원화
	}
	
	final String text;

	private OrderEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

	static <T extends Enum<T>> List<String> toStringList(Class<T> clz) {
		try {
			List<String> res = new LinkedList<String>();
			Method getDisplayValue = clz.getMethod("getDisplayValue");

			for (Object e : clz.getEnumConstants()) {
				res.add((String) getDisplayValue.invoke(e));
			}

			return res;
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private static <T extends Enum<T>> List<T> toStringList(Class<T> clazz, List<String> values) {
		List<T> list = new ArrayList<>();
		for (String level : values) {
			list.add(Enum.valueOf(clazz, level));
		}
		return list;
	}

}
