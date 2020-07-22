/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author jhkhan.cha
 * @since  2015. 5. 20
 */
package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmExtend;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.biz.claim.result.ClaimListResult;
import com.plgrim.ncp.biz.order.data.LgsDlvspDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 클레임 DTO.
 *
 * @author jhkhan.cha
 * @since 2015. 5. 20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimBoDTO extends AbstractDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1282597164287160127L;
    	
	/**
	 * 클레임
	 */
	private ClmExtend clmExtend;

    /**
     * 물류배송지
     * 배송지별 상품목록을 가져오기 위함
     * 패키지, 세트 상품인 경우 아래와 같은 구조임.
     * OrdGodExtend 	<패키지/일반상품>
     * 	   OrdGodExtend <패키지구성품>
     *     OrdGodExtend <패키지구성품>
     *     ...
     */
    private List<LgsDlvspExtend> lgsDlvspList; 

    //ncp2 - 계산된 물류배송 정보를 담기 위한 TmpDTO
    private List<LgsDlvspExtend> lgsDlvspTmpList;


    /**
     * 주문번호
     */
    private String ordNo;

    /**
     * 회원번호
     *클레임접수시 회원정보에 등록된 배송지 정보를 조회하기 위한 조건값
     */
	private String mbrNo;
	
    /**
     * 회원유형
     * 클레임접수시 회원유형을 구분해야 함.
     * 회원 - 회원정보의 등록된 배송지 조회
     * 비회원 - 주문시 배송지 정보 조회
     */	
	private String mbrTp;
	
	/* 주문유형 */
	private String ordTp;
	/* 로그인 유형 */
	private String adminTpCd;
	/* 로그인된 매장ID */
	private String regtrShopId;
	

    /**
     * 주문상품순번
     * 클레임접수시 주문상세에서 선택한 주문상품순번
     */	
    private String ordGodTurn;

	private List<String> ordGodTurnArr;
	

    /**
     * 클레임번호 
     */
    private List<String> clmNoList;

    /**
     * 클레임번호
     */
    private String clmNo;

    /**
     * 클레임유형
     */
    private String clmTpCd;
    
    /**
     * 클레임상태 
     */
    private String clmStatCd;

	/**
	 * 클레임 사유 코드
	ㅁ. 클레임 사유 : CLM_RSN
	   >. 고객변심취소 : CSTMR_CHGMIND_CNCL
	   >. 사이즈교환 : SIZE_EXCHG
	   >. 품절취소 : SLDOUT_CNCL
	   >. 배송지연취소 : DLV_DELAY_CNCL
	   >. 이중주문취소 : DPLC_ORD_CNCL
	   >. 시스템오류취소 : SYS_ERR_CNCL
	   >. 누락배송 : OVSITE_DLV
	   >. 택배분실 : HDRY_LOST
	   >. 옵션변경 : OPT_CHG
	   >. 상품불량 : GOD_BADN
	   >. 상품파손 : GOD_DMG
	   >. 상품 오염 : GOD_STAN
	   >. 기타 상품정보상이 : ETC_GOD_INFO_GAP
	   >. 사이즈 정보 상이 : SIZE_INFO_DFFRNC
	   >. 색상 정보 상이 : COLOR_INFO_DFFRNC
	   >. 오배송 : WN_DLV
	   >. 기타 : ETC
	 */
	private String clmResnCd;    
    
    /**
     * 상품 연결 유형 : GOD_CNNC_TP  - 주문클레임상품연결
     * 출고 상품 연결 : DLIVY_GOD_CNNC
     * 입고 상품 연결 : WRHS_GOD_CNNC
     */
    private String godCnncTpCd;

    /**
     * 배송 업체 전송 대상 여부
     * ㅁ. 매장 배송, 사용자 직접 반품등 배송이  필요 없는 경우 N
     * ㅁ. 배송 업체 배송이 필요 한 경우 Y';
     * 택배사수거지시 선택시 'Y' 로 세팅.  
     */
    private String dlvComTrnsmisTgtYn;
    
    /**
	 * PG연동제외 
	 */
	private String pgIfYn;
		
    /**
	 * 출고지시유형코드 
	 * 주문 - 교환접수시
	 */
	private String dlivyDrctTpCd;
   
    /**
     * 수선반품 여부
     * 이미 수선실에 불량반품이 되어 있는 경우 수선실에서 사용할 용도.
     * 수선반품인 경우 반품접수시 'Y' 체크하고 고객명, 전화번호, 차수를 입력받아 저장한다.
     */
    private String repairRtgodYn;
    
    //고객명
    private String cstmrNm;
    
    //전화번호
    private String cstmrMobilNo;
    
    //차수
    private String rtrvlDrctGrpDgre;
    
	// 의뢰내용 
	private String badnReqestCont;
	
    /**
     * 로그인ID 
     */
	
	private String regtrId;
    
	private String udterId;

	private String logInId;
	
	/**
	 * 배송 수거지 순번
	 * 반품/교환에서 주문배송지 조회시 사용	 
	 */
	private java.lang.Integer dlvPcupspTurn;

	
    /**
     * Role
     * FO/BO 구분
     * FO : F, BO : B
     * 반품접수/교환접수 에서 사용
     */	
	private String role;
  
    /**
     * 주문비용구분
     * 반품 접수시 취소배송비, 반품배송비 산정시 사용
     * 무로주문 : Y 
     * 유료주문 : N
     */    
	private String ordCstGbn;
	
	
    /**
     * 인터페이스주문상품ERP분배
     * 교환/맞교환 시 출고지시인 경우 사용
     */
    private List<InfOrdGodErpDstb> infOrdGodErpDstbList;

    /**
     * 결제
     * 교환 시 사용
     */
    private List<Pay> payList;
    
	/**
	 * 총 결제환불금액
	 * 주문 - 반품접수시 발생한 배송비
	 */
    private java.math.BigDecimal refundPrdDlvFee;
    
	/**
	 * 결제 통화 결제 금액	 
	 * 주문 - 교환접수시 발생한 배송비
	 */
	private java.math.BigDecimal payCrncyPayAmt;
    
	/**
	 * 배송 매장 ID	 
	 */
	private String dlvShopId;
	
	/**
	 * 주문상품순번
	 * 클레임접수시 주문상세에서 선택한 주문상품순번
	 */
	private String ordGodTurnStr;
	
	/***************************************************************************
	* 클레임관리 - 입고완료
	****************************************************************************/

    /**
     * 물류회수지시상품
     * 교환/맞교환 시 입고완료/사은품가입고 변경시 사용
     */
    private List<LgsRtrvlDrctGodExtend> lgsRtrvlDrctGodExtendList;

    /**
     * 작업구분
     * 교환/맞교환 시 입고완료/사은품가입고 저장시 사용
     * 입고완료 : wrhsCompt 
     * 사은품가입고 : gftWrhs 
     */    
	private String jobGbn;

	/* 호출구분 */
	private String callTp;

    
	/***************************************************************************
	* [고객서비스 상담 메모]
	****************************************************************************/
    /**
     * 메모 일련번호
    ㅁ. 메모 등록을 관리하는 일련번호
    >. SEQUENCE를 이용하여 자동 발번 하도록 한다.
    >. 실직 식별자 로써 관리 함
     */
    private java.lang.Long memoSn;
    /**
     * 메모 등록자 ID
     */
    private String memoRegtrId;
    /**
     * 메모 유형 코드
    ㅁ. 메모가 작성된 관계 업무에 대한 연결 여부를 표현 한다.
	ㅁ. 메모 유형 : MEMO_TP
    >. 주문 : ORD
    >. 클레임 : CLM
    >. 회원 : MBR
     */
    private String memoTpCd;
    /**
     * 고객 유형 코드
    ㅁ. 고객 유형 : CSTMR_TP
    >. 악성 민원 : MLGN_CVPL
     */
    private String cstmrTpCd;
    /**
     * 노출 여부
    ㅁ. Y 인 경우, 고객상담>회원정보 출력 영역 고객성향에 출력됨
    ㅁ. N인 경우 고객 성향 관리에서만 관리 됨
     */
    private String expsrYn;
    
    /**
     * 메모 내용
    ㅁ. 상담자가 대상 건에 대한 업무적인 내용을 기입하는 항목으로 정의 함.
    ㅁ. 상담자의 메모 내역을 검색 할 경우 메모 내용에 대한 검색을 단어를 기준으로 검색이 가능 하도록 Domain Index (CTX-CAT)등을 고려 한다.
     */
    private String memoCont;
    
    
    /**
     * 반품 접수화면에서 반품 사유에 따라 환불금에서 차감될 반품배송비
     * 반품 접수화면 환불금 계산버튼 누를때 사용.
     */
    private String returnDlvAmt;


    /**
     * ncp 3
     * 반품 접수화면에서 반품 사유에 따라 환불금에서 차감될 반품배송비
     * 반품 접수화면 환불금 계산버튼 누를때 사용.
     */
    private String returnDlvAmtEx;


    /**
     * 클레임입고상품 확장
     */
    private List<ClmWrhsGodExtend> clmWrhsGodList;
    
    /**
     * 환불은행
     */
    private String rfdBnkCd;
    
    /**
     * 환불예금주
     */
    private String rfdAcnthldrNm;
    
    /**
     * 환불계좌
     */
    private String rfdBnkAcctNo;
    

	/***************************************************************************
	* 클레임관리 - 부분철회/전체철회
	****************************************************************************/

	/* 주문유형 */
	private String ordTpCd;

	private String group;
	
	private String rfdCheckYn;


    /**
     * ncp 3차
     * 반품택배사 - 해외반품신청 시
     */
	private String dlvComNm;

    /**
     * ncp 3차
     * 반품 송장번호 - 해외반품신청 시
     */
	private String ovseaWaybilNo;

	/**
	 * ERP 상품 일련번호
	 */
	private String[] erpGodSnArry;

	/**
     * 수선번호
     */
    private String repairNo;

    /**
     * PG구분코드
     */
    private String pgSectCd;



    //배송지 변경시 체크박스 체크여부
    /**
     * 기본배송지 지정 체크여부
     */

    private String defaultDelv;

    /**
     * 배송지 목록에 추가 체크여부
     */


    private String addMbrDlvspCheck;
	/**
	 * 수선 ERP 취소 FLAG
	 */
	private boolean repairErpCancleFlag;

    // 3. 결제
 	private Pay pay;

 	// 2. 배송
 	private List<LgsDlvspDTO> lgsDlvspDTOList;

 	// 4. 주문
 	private Ord ord;
 	
 	/**
 	 * [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청
 	 * 선진행 구분 코드
 	 */
 	private String clmPreprogrsSectCd;
  
 	private String userTrackingId;    /*트레킹아이디*/ 	
 	
 	private List<ClaimListResult> errList;
}