package com.plgrim.ncp.biz.delivery.data;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LgsDlivyCJ {

	//************************************ 인터페이스 객체 *************************************
	String custId;					// 고객ID	CUST_ID    P.K
	String rcptYmd;					// 접수일자	RCPT_YMD    P.K
	String custUseNo;				// 고객사용번호	CUST_USE_NO    P.K
	//01: 일반, 02: 반품
	String rcptDv = "01";			// 접수구분	RCPT_DV     P.K
	//01: 일반, 02 : 교환, 03 : A/S
	String workDvCd = "01";			// 작업구분코드	WORK_DV_CD     P.K 
	//01: 요청, 02: 취소
	String reqDvCd = "01";			// 요청구분코드	REQ_DV_CD     P.K
	String mpckKey;					// 합포장키	MPCK_KEY YYYYMMDD_고객ID_고객사용번호    P.K 
	int mpckSeq;					// 합포장순번	MPCK_SEQ (합포 없는 경우 무조건 1 )    P.K
	//01: 계약 운임,  02: 자료 운임 
	String calDvCd="01";			// 정산구분코드	CAL_DV_CD    (NOT NULL)
	//01: 선불,  02: 착불 ,  03: 신용
	String frtDvCd="03";			// 운임구분코드	FRT_DV_CD    (NOT NULL)
	//01: 일반 품목
	String cntrItemCd;				// 계약품목코드	CNTR_ITEM_CD    (NOT NULL)
	//01: 극소,  02: 소,  03: 중,  04: 대,  05: 특대
	String boxTypeCd;				// 박스타입코드	BOX_TYPE_CD    (NOT NULL)
	int boxQty=1;					// 박스수량	BOX_QTY    (NOT NULL)
	int frt;						// 운임	FRT
	String custMgmtDlcmCd;			// 고객관리거래처코드	CustMgmtDlcmCd    (NOT NULL)
	String sendrNm;					// 송화인명	SendrNm    (NOT NULL)
	String sendrTelNo1;				// 송화인전화번호1	SendrTelNo1    (NOT NULL)
	String sendrTelNo2;				// 송화인전화번호2	SendrTelNo2    (NOT NULL)
	String sendrTelNo3;				// 송화인전화번호3	SendrTelNo3    (NOT NULL)
	String sendrCellNo1;			// 송화인휴대폰번호1	SendrCellNo1
	String sendrCellNo2;			// 송화인휴대폰번호2	SendrCellNo2
	String sendrCellNo3;			// 송화인휴대폰번호3	SendrCellNo3
	String sendrSafeNo1;			// 송화인안심번호1	SendrSafeNo1
	String sendrSafeNo2;			// 송화인안심번호2	SendrSafeNo2
	String sendrSafeNo3;			// 송화인안심번호3	SendrSafeNo3
	String sendrZipNo;				// 송화인우편번호	SendrZipNo    (NOT NULL)
	String sendrAddr;				// 송화인주소	SendrAddr    (NOT NULL)
	String sendrDetailAddr;			// 송화인상세주소	SendrDetailAddr    (NOT NULL)
	String rcvrNm;					// 수화인명	RcvrNm    (NOT NULL)
	String rcvrTelNo1;				// 수화인전화번호1	RcvrTelNo1    (NOT NULL)
	String rcvrTelNo2;				// 수화인전화번호2	RcvrTelNo2    (NOT NULL)
	String rcvrTelNo3;				// 수화인전화번호3	RcvrTelNo3    (NOT NULL)
	String rcvrCellNo1;				// 수화인휴대폰번호1	RcvrCellNo1
	String rcvrCellNo2;				// 수화인휴대폰번호2	RcvrCellNo2
	String rcvrCellNo3;				// 수화인휴대폰번호3	RcvrCellNo3
	String rcvrSafeNo1;				// 수화인안심번호1	RcvrSafeNo1
	String rcvrSafeNo2;				// 수화인안심번호2	RcvrSafeNo2
	String rcvrSafeNo3;				// 수화인안심번호3	RcvrSafeNo3
	String rcvrZipNo;				// 수화인우편번호	RcvrZipNo    (NOT NULL)
	String rcvrAddr;				// 수화인주소	RcvrAddr    (NOT NULL)
	String rcvrDetailAddr;			// 수화인상세주소	RcvrDetailAddr    (NOT NULL)
	String ordrrNm;					// 주문자명	OrdrrNm
	String ordrrTelNo1;				// 주문자전화번호1	OrdrrTelNo1
	String ordrrTelNo2;				// 주문자전화번호2	OrdrrTelNo2
	String ordrrTelNo3;				// 주문자전화번호3	OrdrrTelNo3
	String ordrrCellNo1;			// 주문자휴대폰번호1	OrdrrCellNo1
	String ordrrCellNo2;			// 주문자휴대폰번호2	OrdrrCellNo2
	String ordrrCellNo3;			// 주문자휴대폰번호3	OrdrrCellNo3
	String ordrrSafeNo1;			// 주문자안심번호1	OrdrrSafeNo1
	String ordrrSafeNo2;			// 주문자안심번호2	OrdrrSafeNo2
	String ordrrSafeNo3;			// 주문자안심번호3	OrdrrSafeNo3
	String ordrrZipNo;				// 주문자우편번호	OrdrrZipNo
	String ordrrAddr;				// 주문자주소	OrdrrAddr
	String ordrrDetailAddr;			// 주문자상세주소	OrdrrDetailAddr
	String invcNo;					// 운송장번호	InvcNo
	String oriInvcNo;				// 원운송장번호	OriInvcNo
	String oriOrdNo;				// 원주문번호	OriOrdNo
	String colctExpctYmd="";		// 집화예정일자	ColctExpctYmd
	String colctExpctHour="";		// 집화예정시간	ColctExpctHour
	String shipExpctYmd="";			// 배송예정일자	ShipExpctYmd
	String shipExpctHour="";		// 배송예정시간	ShipExpctHour
	//01: 미출력,  02: 선출력,  03: 선발번 (반품은 선발번이 없음)
	String prtSt="02";				// 출력상태	PRT_ST    (NOT NULL)
	BigDecimal articleAmt;			// 물품가액	ARTICLE_AMT
	//배송메세지1(비고)
	String remark1;					// 비고1	Remark1
	//배송메세지2(송화인비고)
	String remark2;					// 비고2	Remark2
	//배송메세지3(수화인비고)	
	String remark3;					// 비고3	Remark3
	//대면결제 서비스 업체의 경우 대면결제 발생시 Y로 셋팅
	String codYn;					// Cod여부	CodYn
	String gdsCd;					// 상품코드	GdsCd
	String gdsNm;					// 상품명	GdsNm    (NOT NULL)
	String gdsQty;					// 상품수량	GdsQty
	String unitCd;					// 단품코드	UnitCd
	String unitNm;					// 단품명	UNIT_NM
	BigDecimal gdsAmt;				// 상품가액	GDS_AMT
	String etc1;					// 기타1	Etc1
	String etc2;					// 기타2	Etc2
	String etc3;					// 기타3	Etc3
	String etc4;					// 기타4	Etc4
	String etc5;					// 기타5	Etc5
	//택배 : '01', 중량물(설치물류) : '02', 중량물(비설치물류) : '03'
	String dlvDv = "01";			// 택배구분	DlvDv    (NOT NULL)
	//DEFAULT : 'N'	
	String rcptErrYn = "N";			// 접수에러여부	RcptErrYn    (NOT NULL)
	String rcptErrMsg;				// 접수에러메세지	RcptErrMsg
	//DEFAULT : '01'
	String eaiPrgsSt = "01";		// Eai전송상태	EaiPrgsSt    (NOT NULL)
	String eaiErrMsg;				// 에러메세지	EaiErrMsg
	String regEmpId;				// 등록사원ID	REG_EMP_ID    (NOT NULL)
	Date regDtime;					// DATE 등록일시	REG_DTIME    (NOT NULL)
	String modiEmpId;				// 수정사원ID	MODI_EMP_ID    (NOT NULL)
	Date modiDtime;					// DATE 수정일시	MODI_DTIME    (NOT NULL)
	//************************************ 인터페이스 객체 *************************************
	
	String dlvComCd;
	String clRegtrId; 				// 클레임 접수자
	
	private String cjComId;			// CJ 코드
	private String ordNo;			// 클레임번호.
	private String clmNo;			// 클레임번호.
	private int dlvPcupspTurn;		// 순번.
	private int dlvTurn;			// 순번.
	private String rtrvlDrctGodNo;	// 회수 상품 번호.
	private String brndId;			// 브랜드ID
	private String result;			// 처리 결과 코드
	private String message;			// 처리 결과 메시지
	private String callerId;
	
}
