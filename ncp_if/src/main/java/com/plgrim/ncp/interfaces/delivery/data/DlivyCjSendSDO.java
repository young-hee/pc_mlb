package com.plgrim.ncp.interfaces.delivery.data;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value=Include.NON_EMPTY)
public class DlivyCjSendSDO {

	//************************************ 인터페이스 객체 *************************************
	@JsonProperty("CUST_ID")
	private String custId;					// 고객ID	CUST_ID    P.K
	@JsonProperty("RCPT_YMD")
	private String rcptYmd;					// 접수일자	RCPT_YMD    P.K
	@JsonProperty("CUST_USE_NO")
	private String custUseNo;				// 고객사용번호	CUST_USE_NO    P.K
	//01: 일반, 02: 반품
	@JsonProperty("RCPT_DV")
	private String rcptDv;			// 접수구분	RCPT_DV     P.K
	//01: 일반, 02 : 교환, 03 : A/S
	@JsonProperty("WORK_DV_CD")
	private String workDvCd;			// 작업구분코드	WORK_DV_CD     P.K 
	//01: 요청, 02: 취소
	@JsonProperty("REQ_DV_CD")
	private String reqDvCd;			// 요청구분코드	REQ_DV_CD     P.K
	@JsonProperty("MPCK_KEY")
	private String mpckKey;					// 합포장키	MPCK_KEY YYYYMMDD_고객ID_고객사용번호    P.K 
	@JsonProperty("MPCK_SEQ")
	private int mpckSeq;					// 합포장순번	MPCK_SEQ (합포 없는 경우 무조건 1 )    P.K
	//01: 계약 운임,  02: 자료 운임 
	@JsonProperty("CAL_DV_CD")
	private String calDvCd;			// 정산구분코드	CAL_DV_CD    (NOT NULL)
	//01: 선불,  02: 착불 ,  03: 신용
	@JsonProperty("FRT_DV_CD")
	private String frtDvCd;			// 운임구분코드	FRT_DV_CD    (NOT NULL)
	//01: 일반 품목
	@JsonProperty("CNTR_ITEM_CD")
	private String cntrItemCd;				// 계약품목코드	CNTR_ITEM_CD    (NOT NULL)
	//01: 극소,  02: 소,  03: 중,  04: 대,  05: 특대
	@JsonProperty("BOX_TYPE_CD")
	private String boxTypeCd;				// 박스타입코드	BOX_TYPE_CD    (NOT NULL)
	@JsonProperty("BOX_QTY")
	private int boxQty;					// 박스수량	BOX_QTY    (NOT NULL)
	@JsonProperty("FRT")
	private int frt;						// 운임	FRT
	@JsonProperty("CUST_MGMT_DLCM_CD")
	private String custMgmtDlcmCd;			// 고객관리거래처코드	CustMgmtDlcmCd    (NOT NULL)
	@JsonProperty("SENDR_NM")
	private String sendrNm;					// 송화인명	SendrNm    (NOT NULL)
	@JsonProperty("SENDR_TEL_NO1")
	private String sendrTelNo1;				// 송화인전화번호1	SendrTelNo1    (NOT NULL)
	@JsonProperty("SENDR_TEL_NO2")
	private String sendrTelNo2;				// 송화인전화번호2	SendrTelNo2    (NOT NULL)
	@JsonProperty("SENDR_TEL_NO3")
	private String sendrTelNo3;				// 송화인전화번호3	SendrTelNo3    (NOT NULL)
	@JsonProperty("SENDR_CELL_NO1")
	private String sendrCellNo1;			// 송화인휴대폰번호1	SendrCellNo1
	@JsonProperty("SENDR_CELL_NO2")
	private String sendrCellNo2;			// 송화인휴대폰번호2	SendrCellNo2
	@JsonProperty("SENDR_CELL_NO3")
	private String sendrCellNo3;			// 송화인휴대폰번호3	SendrCellNo3
	@JsonProperty("SENDR_SAFE_NO1")
	private String sendrSafeNo1;			// 송화인안심번호1	SendrSafeNo1
	@JsonProperty("SENDR_SAFE_NO2")
	private String sendrSafeNo2;			// 송화인안심번호2	SendrSafeNo2
	@JsonProperty("SENDR_SAFE_NO3")
	private String sendrSafeNo3;			// 송화인안심번호3	SendrSafeNo3
	@JsonProperty("SENDR_ZIP_NO")
	private String sendrZipNo;				// 송화인우편번호	SendrZipNo    (NOT NULL)
	@JsonProperty("SENDR_ADDR")
	private String sendrAddr;				// 송화인주소	SendrAddr    (NOT NULL)
	@JsonProperty("SENDR_DETAIL_ADDR")
	private String sendrDetailAddr;			// 송화인상세주소	SendrDetailAddr    (NOT NULL)
	@JsonProperty("RCVR_NM")
	private String rcvrNm;					// 수화인명	RcvrNm    (NOT NULL)
	@JsonProperty("RCVR_TEL_NO1")
	private String rcvrTelNo1;				// 수화인전화번호1	RcvrTelNo1    (NOT NULL)
	@JsonProperty("RCVR_TEL_NO2")
	private String rcvrTelNo2;				// 수화인전화번호2	RcvrTelNo2    (NOT NULL)
	@JsonProperty("RCVR_TEL_NO3")
	private String rcvrTelNo3;				// 수화인전화번호3	RcvrTelNo3    (NOT NULL)
	@JsonProperty("RCVR_CELL_NO1")
	private String rcvrCellNo1;				// 수화인휴대폰번호1	RcvrCellNo1
	@JsonProperty("RCVR_CELL_NO2")
	private String rcvrCellNo2;				// 수화인휴대폰번호2	RcvrCellNo2
	@JsonProperty("RCVR_CELL_NO3")
	private String rcvrCellNo3;				// 수화인휴대폰번호3	RcvrCellNo3
	@JsonProperty("RCVR_SAFE_NO1")
	private String rcvrSafeNo1;				// 수화인안심번호1	RcvrSafeNo1
	@JsonProperty("RCVR_SAFE_NO2")
	private String rcvrSafeNo2;				// 수화인안심번호2	RcvrSafeNo2
	@JsonProperty("RCVR_SAFE_NO3")
	private String rcvrSafeNo3;				// 수화인안심번호3	RcvrSafeNo3
	@JsonProperty("RCVR_ZIP_NO")
	private String rcvrZipNo;				// 수화인우편번호	RcvrZipNo    (NOT NULL)
	@JsonProperty("RCVR_ADDR")
	private String rcvrAddr;				// 수화인주소	RcvrAddr    (NOT NULL)
	@JsonProperty("RCVR_DETAIL_ADDR")
	private String rcvrDetailAddr;			// 수화인상세주소	RcvrDetailAddr    (NOT NULL)
	@JsonProperty("ORDRR_NM")
	private String ordrrNm;					// 주문자명	OrdrrNm
	@JsonProperty("ORDRR_TEL_NO1")
	private String ordrrTelNo1;				// 주문자전화번호1	OrdrrTelNo1
	@JsonProperty("ORDRR_TEL_NO2")
	private String ordrrTelNo2;				// 주문자전화번호2	OrdrrTelNo2
	@JsonProperty("ORDRR_TEL_NO3")
	private String ordrrTelNo3;				// 주문자전화번호3	OrdrrTelNo3
	@JsonProperty("ORDRR_CELL_NO1")
	private String ordrrCellNo1;			// 주문자휴대폰번호1	OrdrrCellNo1
	@JsonProperty("ORDRR_CELL_NO2")
	private String ordrrCellNo2;			// 주문자휴대폰번호2	OrdrrCellNo2
	@JsonProperty("ORDRR_CELL_NO3")
	private String ordrrCellNo3;			// 주문자휴대폰번호3	OrdrrCellNo3
	@JsonProperty("ORDRR_SAFE_NO1")
	private String ordrrSafeNo1;			// 주문자안심번호1	OrdrrSafeNo1
	@JsonProperty("ORDRR_SAFE_NO2")
	private String ordrrSafeNo2;			// 주문자안심번호2	OrdrrSafeNo2
	@JsonProperty("ORDRR_SAFE_NO3")
	private String ordrrSafeNo3;			// 주문자안심번호3	OrdrrSafeNo3
	@JsonProperty("ORDRR_ZIP_NO")
	private String ordrrZipNo;				// 주문자우편번호	OrdrrZipNo
	@JsonProperty("ORDRR_ADDR")
	private String ordrrAddr;				// 주문자주소	OrdrrAddr
	@JsonProperty("ORDRR_DETAIL_ADDR")
	private String ordrrDetailAddr;			// 주문자상세주소	OrdrrDetailAddr
	@JsonProperty("INVC_NO")
	private String invcNo;					// 운송장번호	InvcNo
	@JsonProperty("ORI_INVC_NO")
	private String oriInvcNo;				// 원운송장번호	OriInvcNo
	@JsonProperty("ORI_ORD_NO")
	private String oriOrdNo;				// 원주문번호	OriOrdNo
	@JsonProperty("COLCT_EXPCT_YMD")
	private String colctExpctYmd;		// 집화예정일자	ColctExpctYmd
	@JsonProperty("COLCT_EXPCT_HOUR")
	private String colctExpctHour;		// 집화예정시간	ColctExpctHour
	@JsonProperty("SHIP_EXPCT_YMD")
	private String shipExpctYmd;			// 배송예정일자	ShipExpctYmd
	@JsonProperty("SHIP_EXPCT_HOUR")
	private String shipExpctHour;		// 배송예정시간	ShipExpctHour
	//01: 미출력,  02: 선출력,  03: 선발번 (반품은 선발번이 없음)
	@JsonProperty("PRT_ST")
	private String prtSt;				// 출력상태	PRT_ST    (NOT NULL)
	@JsonProperty("ARTICLE_AMT")
	private BigDecimal articleAmt;			// 물품가액	ARTICLE_AMT
	//배송메세지1(비고)
	@JsonProperty("REMARK1")
	private String remark1;					// 비고1	Remark1
	//배송메세지2(송화인비고)
	@JsonProperty("REMARK2")
	private String remark2;					// 비고2	Remark2
	//배송메세지3(수화인비고)	
	@JsonProperty("REMARK3")
	private String remark3;					// 비고3	Remark3
	//대면결제 서비스 업체의 경우 대면결제 발생시 Y로 셋팅
	@JsonProperty("COD_YN")
	private String codYn;					// Cod여부	CodYn
	@JsonProperty("GDS_CD")
	private String gdsCd;					// 상품코드	GdsCd
	@JsonProperty("GDS_NM")
	private String gdsNm;					// 상품명	GdsNm    (NOT NULL)
	@JsonProperty("GDS_QTY")
	private String gdsQty;					// 상품수량	GdsQty
	@JsonProperty("UNIT_CD")
	private String unitCd;					// 단품코드	UnitCd
	@JsonProperty("UNIT_NM")
	private String unitNm;					// 단품명	UNIT_NM
	@JsonProperty("GDS_AMT")
	private BigDecimal gdsAmt;				// 상품가액	GDS_AMT
	@JsonProperty("ETC1")
	private String etc1;					// 기타1	Etc1
	@JsonProperty("ETC2")
	private String etc2;					// 기타2	Etc2
	@JsonProperty("ETC3")
	private String etc3;					// 기타3	Etc3
	@JsonProperty("ETC4")
	private String etc4;					// 기타4	Etc4
	@JsonProperty("ETC5")
	private String etc5;					// 기타5	Etc5
	//택배 : '01', 중량물(설치물류) : '02', 중량물(비설치물류) : '03'
	@JsonProperty("DLV_DV")
	private String dlvDv;			// 택배구분	DlvDv    (NOT NULL)
	//DEFAULT : 'N'	
	@JsonProperty("RCPT_ERR_YN")
	private String rcptErrYn;			// 접수에러여부	RcptErrYn    (NOT NULL)
	@JsonProperty("RCPT_ERR_MSG")
	private String rcptErrMsg;				// 접수에러메세지	RcptErrMsg
	//DEFAULT : '01'
	@JsonProperty("EAI_PRGS_ST")
	private String eaiPrgsSt;		// Eai전송상태	EaiPrgsSt    (NOT NULL)
	@JsonProperty("EAI_ERR_MSG")
	private String eaiErrMsg;				// 에러메세지	EaiErrMsg
	@JsonProperty("REG_EMP_ID")
	private String regEmpId;				// 등록사원ID	REG_EMP_ID    (NOT NULL)
	@JsonProperty("REG_DTIME")
	private String regDtime;					// DATE 등록일시	REG_DTIME    (NOT NULL)
	@JsonProperty("MODI_EMP_ID")
	private String modiEmpId;				// 수정사원ID	MODI_EMP_ID    (NOT NULL)
	@JsonProperty("MODI_DTIME")
	private String modiDtime;					// DATE 수정일시	MODI_DTIME    (NOT NULL)
	//************************************ 인터페이스 객체 *************************************
	@JsonProperty("CRG_ST")
	private String crgSt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String cjComId;			// CJ 코드
	@JsonProperty("ORD_NO")
	private String ordNo;			// 클레임번호.
	@JsonProperty("CLM_NO")
	private String clmNo;			// 클레임번호.
	@JsonProperty("DLV_PCUPSP_TURN")
	private int dlvPcupspTurn;		// 순번.
	@JsonProperty("DLV_TURN")
	private int dlvTurn;			// 순번.
	@JsonProperty("RTRVL_DRCT_GOD_NO")
	private String rtrvlDrctGodNo;	// 회수 상품 번호.
	
    /**
     * 처리결과코드
     */
    @JsonProperty("RESULT_CD")
    private String resultCd;
    /**
     * 처리결과메시지
     */
    @JsonProperty("RESULT_MSG")
    private String resultMsg;
    
	private String result;			// 처리 결과 코드
	private String message;			// 처리 결과 메시지
	private String callerId;
	
	@JsonProperty("DMSTC_WAYBIL_NO")
	private String dmstcWaybilNo;
}
