/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.lgs;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 물류 회수지시 상품 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("lgsRtrvlDrctGodHist")
public class LgsRtrvlDrctGodHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 회수지시 상품 번호
	 * RT || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String rtrvlDrctGodNo;


	/**
	 * 이력 순번	 
	 */
	private java.lang.Integer histTurn;


	/**
	 * 주문 번호	 
	 */
	private String ordNo;


	/**
	 * 클레임 번호	 
	 */
	private String clmNo;


	/**
	 * 사은품 여부
	 * ㅁ. Y인 경우 적용 사은품 프로모션 순번을 사용
	 * 
	 * ㅁ. N인 경우 주문 상품 순번 사용	 
	 */
	private String gftYn;


	/**
	 * 클레임 입고 상품 순번	 
	 */
	private java.lang.Integer clmWrhsGodTurn;


	/**
	 * 출고지시 상품 번호	 
	 */
	private String dlivyDrctGodNo;


	/**
	 * 배송 수거지 순번	 
	 */
	private java.lang.Integer dlvPcupspTurn;


	/**
	 * 배송 순번	 
	 */
	private java.lang.Integer dlvTurn;


	/**
	 * 해외 배송 순번	 
	 */
	private java.lang.Integer ovseaDlvTurn;


	/**
	 * 회수 매장 ID	 
	 */
	private String rtrvlShopId;


	/**
	 * 누적 상품 무게
	 * ㅁ. ㎏ 단위
	 * ㅁ. 누적 상품 무게나 부피가 업체 배송비 정책의 무게나 부피를 넘을 경우 출고지시 상품 번호를 시퀀스로부터 채번한 후 0부터 다시 계산 한다	 
	 */
	private java.math.BigDecimal acmtlGodWt;


	/**
	 * 누적 상품 부피
	 * ㅁ. ㎠ 단위
	 * ㅁ. 누적 상품 무게나 부피가 업체 배송비 정책의 무게나 부피를 넘을 경우 출고지시 상품 번호를 시퀀스로부터 채번한 후 0부터 다시 계산 한다.	 
	 */
	private java.math.BigDecimal acmtlGodVol;


	/**
	 * 물류 단품 여부
	 * ㅁ. 상품의 단품을 지칭 하는 것이 아니며 물류에서 사용하는 단품과 복품을 구분	 
	 */
	private String lgsItmYn;


	/**
	 * 회수지시 대상 여부	 
	 */
	private String rtrvlDrctTgtYn;


	/**
	 * 회수지시 여부
	 * ㅁ. 물류 센터에 회수 지시를 했는지 여부	 
	 */
	private String rtrvlDrctYn;


	/**
	 * 회수지시 그룹 차수	 
	 */
	private java.lang.Integer rtrvlDrctGrpDgre;


	/**
	 * 회수지시 횟수
	 * ㅁ. 물류센터의 작업 차수로 1회 작업 할 수있는 양을 그룹핑	 
	 */
	private java.lang.Integer rtrvlDrctCount;


	/**
	 * 회수지시 유형 코드
	 * ㅁ. 회수지시유형 : RTRVL_DRCT_SECT
	 *    >. 반품 : RTGOD
	 *    >. 교환 : EXCHG
	 *    >. 맞교환 : DRT_EXCHG
	 *    >. 매장 반품 : SHOP_RTGOD	 
	 */
	private String rtrvlDrctTpCd;


	/**
	 * 회수지시 일시	 
	 */
	private java.util.Date rtrvlDrctDt;


	/**
	 * 회수지시 수량	 
	 */
	private java.lang.Long rtrvlDrctQty;


	/**
	 * 회수지시 철회 수량	 
	 */
	private java.lang.Long rtrvlDrctWthdrawQty;


	/**
	 * 회수지시 철회 일시	 
	 */
	private java.util.Date rtrvlDrctWthdrawDt;


	/**
	 * 회수 상태 코드
	 * ㅁ. 회수 상태 : RTRVL_STAT
	 *    >. 회수지시 대기 : RTRVL_DRCT_WAIT
	 *    >. 회수지시 : RTRVL_DRCT
	 *    >. 입고 완료 : WRHS_COMPT
	 *    >. 회수 완료 : RTRVL_COMPT
	 *    >. 회수 철회 : RTRVL_WTHDRAW	 
	 */
	private String rtrvlStatCd;


	/**
	 * 입고 완료 일시	 
	 */
	private java.util.Date wrhsComptDt;


	/**
	 * 회수 완료 일시	 
	 */
	private java.util.Date rtrvlComptDt;


	/**
	 * 회수 상품 상태 코드
	 * ㅁ. 회수상품상태 : RTRVL_GOD_STAT
	 *    >. 정상입고 : NORM_WRHS
	 *    >. 불량판정 : BADN_JDGMNT
	 * ---삭제   >. 수선의뢰 : REPAIR_REQEST	 
	 */
	private String rtrvlGodStatCd;


	/**
	 * 회수 상품 상태 상세 코드
	 * ㅁ. 회수 상품 상태 상세
	 *    >. 정상 : NRMLT
	 *    >. 소각 : INCNR
	 *    >. 자가 소비 : OOWN_CSM	 
	 */
	private String rtrvlGodStatDetailCd;


	/**
	 * 회수 상품 처리 완료 여부	 
	 */
	private String rtrvlGodPrcsComptYn;


	/**
	 * 불량 의뢰 내용	 
	 */
	private String badnReqestCont;


	/**
	 * 택배사 전송 대상 여부	 
	 */
	private String hdryComTrnsmisTgtYn;


	/**
	 * 택배사 전송 여부	 
	 */
	private String hdryComTrnsmisYn;


	/**
	 * 택배사 전송 일시	 
	 */
	private java.util.Date hdryComTrnsmisDt;


	/**
	 * 택배사 키	 
	 */
	private String hdryComKey;


	/**
	 * 택배사 입고 여부	 
	 */
	private String hdryComWrhsYn;


	/**
	 * 택배사 입고 일시	 
	 */
	private java.util.Date hdryComWrhsDt;


	/**
	 * ERP 전송 여부
	 * ㅁ. 택배사와 정산을 위해 ERP으로 보냈는지에 대한 정보	 
	 */
	private String erpTrnsmisYn;


	/**
	 * ERP 전송 일시	 
	 */
	private java.util.Date erpTrnsmisDt;


	/**
	 * ERP 확인 여부	 
	 */
	private String erpCnfirmYn;


	/**
	 * ERP 확인 일시	 
	 */
	private java.util.Date erpCnfirmDt;


	/**
	 * ERP 재고 전송 구분 코드
	 * ㅁ. ERP 재고 전송 구분 : ERP_INV_TRANSMIS_SECT
	 *    >. 센터 재고 : CNTR_INV
	 *    >. 매장 재고 : SHOP_INV
	 * 
	 * ㅁ. 센터재고:물류센터재고
	 * ㅁ. 매장재고:배송매장재고	 
	 */
	private String erpInvTrnsmisSectCd;


	/**
	 * ERP 재고 전송 일시	 
	 */
	private java.util.Date erpInvTrnsmisDt;


	/**
	 * 반품비 정산 구분 코드
	 * ㅁ. 반품비 정산 구분 : REGODCST_CAL_SECT
	 *    >. 신용 : CREDT
	 *    >. 선불 : PRPAY
	 *    >. 업체 지불 : COM_PAY	 
	 */
	private String rtgodcstCalSectCd;


	/**
	 * 반품비 정산 금액	 
	 */
	private java.math.BigDecimal rtgodcstCalAmt;


	/**
	 * 환불 지연 코드
	 * ㅁ. 환불 지연 : RFD_DELAY
	 *    >. 상품 변형 : GOD_DAMAGE
	 *    >. 상품 훼손/분실 : GOD_DAMAGE_LOST
	 *    >. 상품 세탁 : GOD_LNDR
	 *    >. 상품택 제거 : GOD_TAG_REMOV
	 *    >. 상품 손상 : GOD_DAMG
	 *    >. 상품 착용 : GOD_USE
	 *    >. 재판매 불가 상태 : RESALE_IMPS_STAT
	 *    >. 기타 : ETC	 
	 */
	private String rfdDelayCd;


	/**
	 * 환불 지연 일시	 
	 */
	private java.util.Date rfdDelayDt;


	/**
	 * 환불 지연 사유 설명	 
	 */
	private String rfdDelayResnDscr;


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
