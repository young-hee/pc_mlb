package com.plgrim.ncp.biz.delivery.data;



import lombok.Data;
import lombok.EqualsAndHashCode;
import com.plgrim.ncp.base.abstracts.AbstractDTO;


/**
 * 주문번호와 배송 수거지 순번으로 
 * 일반배송이면 배송지를
 * 픽업배송이면 픽업 매장 정보를 조회하여 결과를 담는 DTO
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LgsDlvspPkupDTO extends AbstractDTO {/**
	 * 
	 */
    private static final long serialVersionUID = 3276694272377826939L;

    
    /*주문번호*/
    private String ordNo;
    
    /*배송 수거지 순번*/
    private String dlvPcupspTurn;
    
    /*배송 구분 코드
     * ㅁ. 배송 구분 : DLV_SECT
   >. 일반 배송 : GNRL_DLV
   >. 픽업 배송 : PKUP_DLV
   >. 해외 배송 : OVSEA_DLV*/
    private String dlvSectCd;
    
    /*픽업 매장 ID*/
    private String pkupShopId;
    
    /*픽업 매장 브랜드 ID*/
    //private String 
    
    /*배송지 주소 기본주소 + 상세주소*/
    private String addr;
    
    /*픽업 메장 이름*/
    private String shopNm;
     
    
    private String dlvMemo;                  /* 배송메모 */
    private String addrseNm;                 /* 수취인 명 */             
    private String addrseBaseAddr;           /* 수취인 기본주소 */       
    private String addrseDetailAddr;         /* 수취인 상세주소 */       
    private String addrseMobilAreaNo;        /*수취인 휴대번호*/         
    private String addrseMobilTlofNo;                                    
    private String addrseMobilTlofWthnNo;                                
    private String addrseTelAreaNo;          /* 수취인  전화 지역번호 */ 
    private String addrseTelTlofNo;                                      
    private String addrseTelTlofWthnNo;
    private String addrShop;  /* 배송매장 주소 */

    private java.lang.Integer clmCnt;               //수거지별 클레임 가능 수


    /*
     * ncp 3차 반품시 주소정보
     */
    private String addrseNationCd;           	/* 수취인 국가 코드 */
    private String addrseNationNm;           	/* 수취인 국가명 */
    private String addrsePostNo;           		/* 수취인 우편번호 */
    private String addrseCtyNm;           		/* 수취인 도시 명 */
    private String addrseLcltyNm;           	/* 수취인 지방 명 */
    private String addrseTelNationNo;           /* 수취인 전화 국가번호 */
    
    private String addrSectCd;                  /* 주소구분코드 */
    private String addrseMobilNationNo;			/* 휴대전화국가번호 */
}
