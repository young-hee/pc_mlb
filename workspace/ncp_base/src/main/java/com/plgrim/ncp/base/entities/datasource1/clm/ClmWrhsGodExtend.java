package com.plgrim.ncp.base.entities.datasource1.clm;

import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodFoExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodSvcDetailCnncExtend;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("clmWrhsGodExtend")
public class ClmWrhsGodExtend extends ClmWrhsGod {

	/**
	 * 
	 */
    private static final long serialVersionUID = 53181491322556035L;
    
    /**
     * 클레임입고상품 확장 
     */
    private List<ClmWrhsGodExtend> clmWrhsGodList;
        
    /**
     * 클레임가능 수량
     */
    private java.lang.Integer wrkQty;

    /**
     * 주문상품순번
     * 클레임입고상품 저장시 주문상품 조회조건용
     */
    private int ordGodTurn;

    /**
     * 주문클레임상품연결 유형
     * 클레임입고상품 저장시 주문상품 조회조건용
     */    
    private String godCnncTpCd;
    
    /**
     * 출고지시상품번호
     * 물류회수지시상품 저장시 사용
     * 주문 - 교환접수시 사용
     */    
    private String dlivyDrctGodNo;
        
    /**
     * 주문클레임수량
     * 주문클레임상품연결 저장시 출고지시상품별 수량을 클레임상품 단위로 Merge 하기 위해 update 용으로 생성
     */    
    private java.lang.Long ordClmQty;

    /**
     * 물류회수지시상품 회수지시수량
     * 물류회수지시상품의 수량은 화면에서 출고지시수량 별로 입력받은 수량을 저장
     */    
    private java.lang.Long rtrvlDrctQty;
    
    
	/**
	 * 배송 순번	 
     * 물류회수지시상품 저장시
	 */
	private java.lang.Integer dlvTurn;
	
	
	/**
	 * 브랜드 명
	 */
	private String brndNm;
    
    /**
     * 품목명
     * 편의점택배 interface
     */    
    private String prdlstNm;
        
    /**
	 * 주결제금액	 
     * 편의점택배 interface
	 */
	private java.lang.Double stdrCrncySumAmt;
	
	/*
	 * 세트,패키지 상품 옵션 리스트
	 */
	private List<OrdGodFoExtend> godOptList; 
	
    
	/**
	 * 주문 상품 순번 - original
	 * 주문 - 교환접수시 사용	 
	 */
	private java.lang.Integer newOrdGodTurn;
			
	/**
	 * 패키지 상품 순번 - original
	 * 주문 - 교환접수시 사용	 
	 */
	private java.lang.Integer pckageGodTurn;
	
	
	/**
	 * 주문 수량	 
	 * 주문 - 교환접수시 사용	 
	 */
	private java.lang.Long ordQty;
	
	/**
	 * 단품 명	 
	 */
	private String itmNm;

	/**
	 * ERP 상품 번호
	 */
	private String erpGodNo;
	
	/**
	 * SKU 번호
	ㅁ. Stock Keeping Unit
   	>. 자사의 경우 ERP 상품 번호에 사이즈 옵션 값 코드를 붙인것을 SKU로 정의
   	>. 입점사는 입력된 ERP 상품 번호에 모든 옵션 값 코드를 붙여 사용
	 */
	private String skuNo;
	
	
	/**
	 * 배송매장 ID	 
	 */
	private String dlvShopId;
	
	
	/**
	 * 물류단품여부	 
	 */
	private String lgsItmYn;
	
	
	/**
	 * 주문 구성 상품 연결 상품순번
	 * 주문 - 반품접수시 사용	 
	 */
	private java.lang.Integer ordGodTurnAnce;

	/**
	 * 주문 구성 상품 순번
	 * 주문 - 반품접수시 사용	 
	 */
	private java.lang.Integer ordCpstGodTurn;

	/**
	 * 주문 구성 상품 유형	 
	 * 주문 - 반품접수시 사용	 
	 */
	private String pckageGodTpCd;

	/**
	 * 주문 정렬 순서
	 * 주문 - 반품접수시 사용	 
	 */
	private java.lang.Integer sortSeq;

	
    /**
     * 물류출고지시상품 출고지시수량
     * 교환철회 시 사용
     */    
    private java.lang.Long dlivyQty;	
	private java.lang.Integer dlvPcupspTurnDlivy;    
	
    /**
     * 
    private String comNm;// 업체명
    
    private String dmstcWaybilNo;// 운송장번호
    
    private String dlvStatNm;
    
    
    private String shtgDcsnYn;
    
    
    private String shopNm;
    
    private String url;
    
    private java.lang.Integer pkGodTurn;
    
    private java.lang.Long totOrdQty;
    
    private java.lang.Integer dlvPcupspTurn;
    
    private java.math.BigDecimal stdrCrncySumAmt;
    
    private java.math.BigDecimal unityPntUseSumAmt;
    
    private java.math.BigDecimal evtPntUseSumAmt;
    
    private java.math.BigDecimal godDcSumAmt;
    
    private java.math.BigDecimal godCpnDcSumAmt;
    
    
    private java.lang.Integer dlivyDrctCnclQty;
    
    
    private java.math.BigDecimal godEtcDcSumAmt;
     */
   
    
	private String colorCd;         // 컬러코드CSS
	private String clorChipImgUrl;  // 컬러칩 이미지 경로
     
	private String exchgItmNm;

	//2차 물류배송 관련 추가
	private String ordGiftYn;


    /**
     * 원 주문 상품 순번 - 교환 후 반품 상품일 경우 사용
     */
    private int oriOrdGodTurn;


    /**
     * ncp 3차 클레임정보의 판매금액 환율적용을 하기위해 분배테이블의 수량 1개의 판매금액을 구한다.
     */
    private java.math.BigDecimal saleUntPrc;
    
    /** 
     * K2 반품전환 클레임 번호
     */
    
    private String wthDrawClmNo;
    
    /** 
     * 시리얼번호
     */
    private String[] erpGodSnArry;
    
    /**
     * 인터페이스 주문 상품 분배 테이블 QTY_TURN
     * */
	private int qtyTurn;

	/**
	 * 수선 ERP 취소 내용
	 */
	private String repairErpCancleCont;
	
	/**
	 * 상위상품번호
	 */
	private String upGodNo;

	/**
	 * 주문 상품 서비스 상세(선물포장)
	 */
	private List<OrdGodSvcDetailCnncExtend> ordGodSvcDetailCnncList;
	
	/**
	 * 추천 성별코드
	 */
	private String recomendSexCd;
	
	/**
	 * 배송상태코드
	 */
	private String dlvStatCd;
	
	/**
	 * 구매확정여부
	 */
	private String cstmrPchDcsnYn;
	
	/**
	 * 상품리뷰개수
	 */
	private String godEvlCnt;
	
	/**
	 * 리뷰가능기간여부
	 */
	private String reviewApplyTermYn;
}
