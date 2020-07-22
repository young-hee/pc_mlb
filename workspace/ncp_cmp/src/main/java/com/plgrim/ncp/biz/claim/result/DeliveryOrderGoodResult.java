package com.plgrim.ncp.biz.claim.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryOrderGoodResult extends AbstractResult {

    private static final long serialVersionUID = 78696418699829320L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 주문 엔터티. */
    private Ord ord;
	/** 주문상품 엔터티. */
    private OrdGod ordGod;
	/** 물류배송지 엔터티. */
    private LgsDlvsp lgsDsp;
	/** 물류배솓 상품 엔터티. */
    private LgsDlv lgsDlv;
	/** 물류출고지시상품 엔터티. */
    private LgsDlivyDrctGod lgsDdg;
    /** 상품정보 */
    private GodItm godItm;
	
    
    /** 클레임번호. */
    private String clmNo;
    /** 클레임입고상품순번. */
    private String clmWrhsGodTurn;
    /** 회수지시상품번호. */
    private String rtrvlDrctGodNo;
	/** 지연일수. */
	private int delayTerm;
	/** 해외배송여부. */
	private String ovseaDlvYn;
	/** 회수지시수량. */
	private int rtrvlDrctQty;
	/** 브랜드명 */
	private String brndNm;
	/** 업체 업무담당자 전화번호. */
	private String chrgerMobilNo;
	/** 유효 전화번호여부 */
	private String mobilNoCheck;
	/** today */
	private String today;
	/** 주문상품 수량 */
	private int ordGodCnt;
	/** 묶음상품순번  */
	private String pckageGodTurn;
	/** 주문유형  */
	private String ordTpNm;
	/** 판매제휴사명  */
	private String saleAffComNm;
	/** 상품유형  */
	private String godTpNm;
	/** 수취인주소  */
	private String addrseAddr;
	/** 수취인휴대전화번호  */
	private String addrseMobilNo;
	/** 수취인휴대전화번호  */
	private String addrseMobilNoOrg;
	/** 수취인전화번호  */
	private String addrseTelNo;
	/** 배송매장명  */
	private String dlvShopNm;
	/** 단/복품여부  */
	private String lgsItmTp;
	/** 출고지시유형  */
	private String dlivyDrctTpNm;
	/** 배송상태  */
	private String dlvStatNm;
	/** 해외/국내 구분  */
	private String ovseaDlvTp;
	/** 남은시간  */
	private String remainTime;
	/** 경과시간  */
	private String elapsedTime;
	/** 배송매장번호  */
	private String orgDlvShopId;
	/** 배송매장번호  */
	private String newDlvShopId;
	/** S STO 번호(STOREORDER_STO번호)  */
	private String sStoNo;
	/** S DOC 번호(STOREORDER_DOC번호)  */
	private String sDocNo;
	/** ST 오류 내용()STORE_ORDER에러내용)  */
	private String stErrCont;
	/** 센터출고확인여부  */
	private String cntrDlivyCnfirmYn;
	/** 예약영수증생성여부  */
	private String resveRcptfrCreatYn;
	/** 예약영수증출고여부  */
	private String resveRcptfrDlivyYn;
	/** 예약영수증확정여부  */
	private String resveRcptfrDcsnYn;
	/** 예약판매여부  */
	private String reservationYn;
	/** 몰명  */
	private String mallNm;
	/** 배송방법  */
	private String dlvMnNm;
	/** 회수상태  */
	private String rtrvlStatNm;
	/** 클레임사유  */
	private String clmResnNm;
	/**  상품옵션  */
	private String options;
	/**  예약 주문 출고 예정 일자*/
	private String resveOrdDlivyPrearngeDate;
	/**  수량순번  */
	private int qtyTurn;
	/**  출고검수여부  */
	private String dlivyAcptYn;
	/**  출고지시수량  */
	private int newDlivyDrctQty;
	/**  원본송장번호  */
	private String dmstcWaybilNoOrg;
	/**  원본송장번호  */
	private String dmstcWaybilNo;
	/**  이미지보기  */
	private String imageView;
	/**  송장보기  */
	private String dmstcWaybilNoView;
	/**  출고지시 상품 메모 내용  */
	private String dlivyDrctGodMemoCont;
	/**  택배사  */
	private String dlvComNm;
	/** 판매제휴사명  */
	private String affComNm;
	/** 옵션  */
	private String optValCd;
	/** 사은품 원상품 주문상품순번  */
	private String orgOrdGodTurn;
	private String gftViewYn;		//사은품 출고관리 노출여부
	private String remainQty;		//잔여수량
	private String viewTxt;
	private String gftGubunNm;		//사은품구분명
	private String comNm;			//업체명
	private String gftGubun;		//사은품구분(S:자체배송,H:합배송,N:사은품아님)
	private String erpGodSn;		//시리얼번호
	private String lmttInvYn;		//한정재고여부
	private String shopStckChckYn;	//매장재고체크여부
	private String aplUnitCd;		//사은품적용단위
	private long invQty;			//가용재고수량
	private int newTargetCnt;		//신규채번 수량
	private String transYn;			//전송여부
	private String params;
	private String erpGodSnView;
	private String skuNo;
	private int dlvPcupspTurn;
	private int dlvTurn;
	private String ordNo;
	private String usefulInvQty;	//가용재고수량
	private String itmStatNm;		//상품상태
	private String langCd;			//언어
	private String smllGodYn;		//소액상품여부
	private String cdcInvOnlyYn;	//CDC한정판매여부

	private String cstmrMobilNo;	//주문자 휴대폰 번호
	
	private String affGodOrdNo;
	private String affItmOrdNo;

	private String partmalSectCd;	//자사 입점 구분
	private String partmalSectNm;	//자사 입점 구분명

	/**  회원 등급. */
	private String onlneGrdNm;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
