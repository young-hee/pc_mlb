/**
 * @author : Generator(Generator)
 * @date : 2018-06-22
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.lgs;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 물류 출고지시 상품 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("lgsDlivyDrctGodHist")
public class LgsDlivyDrctGodHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 출고지시 상품 번호
	 * RO || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String dlivyDrctGodNo;


	/**
	 * 이력 순번
	 * ㅁ. 출고지시 이력에 대한 순번
	 *    >. "물류 출고지시 상품 이력.이력 순번"의 MAX값 + 1로 등록 되도록 한다.
	 *    >. 숫자로 최대 5자리 " 99999" 로 관리 한다.	 
	 */
	private java.lang.Integer histTurn;


	/**
	 * 주문 번호	 
	 */
	private String ordNo;


	/**
	 * 주문 상품 순번	 
	 */
	private java.lang.Integer ordGodTurn;


	/**
	 * 패키지형상품 순번	 
	 */
	private java.lang.Integer pckageGodTurn;


	/**
	 * 사은품 여부
	 * ㅁ. Y인 경우 적용 사은품 프로모션 순번을 사용
	 * 
	 * ㅁ. N인 경우 주문 상품 순번 사용	 
	 */
	private String gftYn;


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
	 * 배송 매장 ID	 
	 */
	private String dlvShopId;


	/**
	 * 배송 매장 출고 위치 ID	 
	 */
	private String dlvShopDlivyLcId;


	/**
	 * 출고 매장 ID	 
	 */
	private String dlivyShopId;


	/**
	 * 누적 상품 부피
	 * ㅁ. ㎠ 단위
	 * ㅁ. 누적 상품 무게나 부피가 업체 배송비 정책의 무게나 부피를 넘을 경우 출고지시 상품 번호를 시퀀스로부터 채번한 후 0부터 다시 계산 한다.	 
	 */
	private java.math.BigDecimal acmtlGodVol;


	/**
	 * 누적 상품 무게
	 * ㅁ. ㎏ 단위
	 * ㅁ. 누적 상품 무게나 부피가 업체 배송비 정책의 무게나 부피를 넘을 경우 출고지시 상품 번호를 시퀀스로부터 채번한 후 0부터 다시 계산 한다	 
	 */
	private java.math.BigDecimal acmtlGodWt;


	/**
	 * 물류 단품 여부
	 * ㅁ. 상품의 단품을 지칭 하는 것이 아니며 물류에서 사용하는 단품과 복품을 구분	 
	 */
	private String lgsItmYn;


	/**
	 * 배정 구분 코드
	 * 배정 구분 : ASGN_SECT
	 *   >. 자동 배정 : AUTO_ASGN
	 *   >. 강제 배정 : ENFRC_ASGN
	 *   >. 고객센터 강제 배정 : CC_ENFRC_ASGN
	 *   >. 희망 배정 : HOPE_ASGN	 
	 */
	private String asgnSectCd;


	/**
	 * 배정 횟수	 
	 */
	private java.lang.Integer asgnCount;


	/**
	 * 거부 횟수	 
	 */
	private java.lang.Integer rejectCount;


	/**
	 * 출고지시 대상 여부	 
	 */
	private String dlivyDrctTgtYn;


	/**
	 * 출고지시 여부
	 * ㅁ. 물류 센터에 출고 지시를 했는지 여부	 
	 */
	private String dlivyDrctYn;


	/**
	 * 출고지시 그룹 차수	 
	 */
	private java.lang.Integer dlivyDrctGrpDgre;


	/**
	 * 출고지시 사용자 그룹 차수	 
	 */
	private java.lang.Integer dlivyDrctUserGrpDgre;


	/**
	 * 출고지시 횟수
	 * ㅁ. 물류센터의 작업 차수로 1회 작업 할 수있는 양을 그룹핑	 
	 */
	private java.lang.Integer dlivyDrctCount;


	/**
	 * 출고지시 유형 코드
	 * ㅁ. 출고지시 유형 : RLOR_TP
	 *    >. 주문 : ORD
	 *    >. 교환 : EXCHG
	 *    >. 매장 픽업 : SHOP_PKUP	 
	 */
	private String dlivyDrctTpCd;


	/**
	 * 최초 출고지시 일시	 
	 */
	private java.util.Date firstDlivyDrctDt;


	/**
	 * 출고지시 일시	 
	 */
	private java.util.Date dlivyDrctDt;


	/**
	 * 출고지시 수량	 
	 */
	private java.lang.Long dlivyDrctQty;


	/**
	 * 출고지시 취소 수량	 
	 */
	private java.lang.Long dlivyDrctCnclQty;


	/**
	 * 출고지시 취소 일시	 
	 */
	private java.util.Date dlivyDrctCnclDt;


	/**
	 * 출고지시 취소 상태 코드
	 * ㅁ. 출고지시 취소 상태 코드 : DLIVY_DRCT_CNCL_STAT
	 *    >. 출고지시 취소 대기 : DLIVY_DRCT_CNCL_WAIT
	 *    >. 출고지시 취소 확정 : DLIVY_DRCT_CNCL_DCSN
	 * 
	 * ㅁ. MD가 출고지시 취소를 접수하는 경우 재배정을 위한 상태 추적을 위한 컬럼	 
	 */
	private String dlivyDrctCnclStatCd;


	/**
	 * 출고 예정 일시
	 * ㅁ. 일반 상품, 주문제작 상품 : 결제.결제 
	 * 일시 + 상품.상품 출고 대기 시간 으로 계산
	 * ㅁ. 예약 상품 : 상품.예약 출고 예정 일시	 
	 */
	private java.util.Date dlivyPrearngeDt;


	/**
	 * 출고 검수 여부
	 * ㅁ. FO(고객이 FO에서 불량사유로 교환접수하는 건), CSO(고객센터에 불량으로 교환접수되는 건)에 대해서, 물류출고 검수시 검수요청 알럿생성, 배송매장 출고검수 시 검수요청 알럿생성으로 2차 불량출고를 줄이고자 추가	 
	 */
	private String dlivyAcptYn;


	/**
	 * 배송 상태 코드
	 * ㅁ. 배송 상태 : DLV_STAT
	 *    >. 배정대기 : DLV_WAIT
	 *    >. 교환입고대기: EXCHG_WRHS_WAIT
	 *    >. 출고지시대기 : DLIVY_DRCT_WAIT
	 *    >. 출고지시 : DLIVY_DRCT
	 *    >. 출고지시취소 : DLIVY_DRCT_CNCL
	 *    >. 출고완료 : DLIVY_COMPT
	 *    >. 배송완료 : DLV_COMPT
	 *    >. 매장 준비 완료 : SHOP_PRPARE_COMPT
	 *    >. 결품접수 : SHTG_RCEPT	 
	 */
	private String dlvStatCd;


	/**
	 * 결품 예상 SMS 발송 여부	 
	 */
	private String shtgExpectSmsSndYn;


	/**
	 * 결품 수량	 
	 */
	private java.lang.Long shtgQty;


	/**
	 * 결품 일시	 
	 */
	private java.util.Date shtgDt;


	/**
	 * 결품 확정 여부	 
	 */
	private String shtgDcsnYn;


	/**
	 * 결품 확정 일시	 
	 */
	private java.util.Date shtgDcsnDt;


	/**
	 * 출고 완료 수량	 
	 */
	private java.lang.Long dlivyComptQty;


	/**
	 * 출고 완료 일시	 
	 */
	private java.util.Date dlivyComptDt;


	/**
	 * 배송 완료 일시	 
	 */
	private java.util.Date dlvComptDt;


	/**
	 * 출고지시 상품 메모 내용	 
	 */
	private String dlivyDrctGodMemoCont;


	/**
	 * 해외 배송 입고 완료 여부
	 * 3PL 입고 완료 여부	 
	 */
	private String ovseaDlvWrhsComptYn;


	/**
	 * 영수증 번호	 
	 */
	private String rcptfrNo;


	/**
	 * 예약 영수증 생성 여부
	 * ㅁ. 판매 영수증 관련 컬럼 임 RFC에 예약으로  되어 있어 네이밍 된것 임	 
	 */
	private String resveRcptfrCreatYn;


	/**
	 * 예약 영수증 생성 일시
	 * ㅁ. 판매 영수증 관련 컬럼 임 RFC에 예약으로  되어 있어 네이밍 된것 임	 
	 */
	private java.util.Date resveRcptfrCreatDt;


	/**
	 * 예약 영수증 출고 여부
	 * ㅁ. 판매 영수증 관련 컬럼 임 RFC에 예약으로  되어 있어 네이밍 된것 임	 
	 */
	private String resveRcptfrDlivyYn;


	/**
	 * 예약 영수증 출고 일시
	 * ㅁ. 판매 영수증 관련 컬럼 임 RFC에 예약으로  되어 있어 네이밍 된것 임	 
	 */
	private java.util.Date resveRcptfrDlivyDt;


	/**
	 * 예약 영수증 확정 여부
	 * ㅁ. 판매 영수증 관련 컬럼 임 RFC에 예약으로  되어 있어 네이밍 된것 임	 
	 */
	private String resveRcptfrDcsnYn;


	/**
	 * 예약 영수증 확정 일시
	 * ㅁ. 판매 영수증 관련 컬럼 임 RFC에 예약으로  되어 있어 네이밍 된것 임	 
	 */
	private java.util.Date resveRcptfrDcsnDt;


	/**
	 * ERP 예약 영수증 생성 건수	 
	 */
	private java.lang.Integer erpResveRcptfrCreatCnt;


	/**
	 * ERP 예약 영수증 생성 여부	 
	 */
	private String erpResveRcptfrCreatYn;


	/**
	 * ERP 예약 영수증 생성 일시	 
	 */
	private java.util.Date erpResveRcptfrCreatDt;


	/**
	 * ERP 예약 영수증 재생성 여부	 
	 */
	private String erpResveRcptfrRecreatYn;


	/**
	 * ERP 예약 영수증 재생성 일시	 
	 */
	private java.util.Date erpResveRcptfrRecreatDt;


	/**
	 * ERP 예약 영수증 취소 여부	 
	 */
	private String erpResveRcptfrCnclYn;


	/**
	 * ERP 예약 영수증 취소 일시	 
	 */
	private java.util.Date erpResveRcptfrCnclDt;


	/**
	 * 재고 적용 여부	 
	 */
	private String invAplYn;


	/**
	 * 센터 출고 확인 여부	 
	 */
	private String cntrDlivyCnfirmYn;


	/**
	 * 센터 출고 확인 일시	 
	 */
	private java.util.Date cntrDlivyCnfirmDt;


	/**
	 * 단품 출고 검수 대상 여부
	 * 단품(복품아님)에 대한 상품 출고시 송장출력이 중복으로 되는 것을 방지하기 위한 컬럼	 
	 */
	private String itmDlivyAcptTgtYn;


	/**
	 * S STO 번호
	 * ㅁ. STOREORDER_STO번호	 
	 */
	private String sStoNo;


	/**
	 * S DOC 번호
	 * ㅁ. STOREORDER_DOC번호	 
	 */
	private String sDocNo;


	/**
	 * P STO 번호
	 * ㅁ. PLANTORDER_STO번호	 
	 */
	private String pStoNo;


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