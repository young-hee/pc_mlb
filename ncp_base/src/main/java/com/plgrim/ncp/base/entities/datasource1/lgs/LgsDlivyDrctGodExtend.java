package com.plgrim.ncp.base.entities.datasource1.lgs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class LgsDlivyDrctGodExtend extends LgsDlivyDrctGod{

/**
	 * 
	 */
    private static final long serialVersionUID = 826406263588395029L;

    private String dmstcWaybilNo ;
    private String pkupShopId ;
    private String pkupShopBrndId ;

    /*
     * 배송매장 자동배정을 위한 단품번호, ERP상품번호, SKU번호
     * 단품번호리스트, 해당주문의 미배정상품수
     */
    /**
     * 단품 번호
     IT || YYYYMMDD || 7자리 Cycle Sequence
     */
    private String itmNo;

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
     * 단품 번호 리스트
     IT || YYYYMMDD || 7자리 Cycle Sequence
     */
    private List<String> itmNoList;

    /**
     * 출고지시 상품 번호 리스트
     RO || YYYYMMDD || 7자리 Cycle Sequence
     */
    private List<String> dlivyDrctGodNoList;

    /**
     * 복품주문인 경우 상품수
     */
    private java.lang.Integer godCnt;

    /**
     * 몰 ID
     */
    private String mallId;

    /**
     * 복품주문인 경우 최대 주문수량
     */
    private java.lang.Integer maxDlivyDrctQty;

    /**
     * 동잂품번의 출고지시수량 합계
     */
    private java.lang.Integer sumDlivyDrctQty;
    
    /**
     * 배치여부(프로그램구분:배치/관리시스템)
     */
    private boolean batchYn;

    /**
     * 세트상품의 동일품번 추가구성상품에 대한 처리로 'ITM_NO'를 이용해서 중복제거된 리스트
     */
    private List<LgsDlivyDrctGodExtend> lgsDlivyDrctGodExtendList;

    /**
     * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경 기능
     *  - 변경될 픽업매장ID
     */
    private String pkupShopId4Change ;

    /**
     * #23145 [주문모듈]픽업 주문 지정 픽업 매장 변경 기능
     *  - 배송 수거지 순번
     */
    private java.lang.Integer dlvPcupspTurn;
}
