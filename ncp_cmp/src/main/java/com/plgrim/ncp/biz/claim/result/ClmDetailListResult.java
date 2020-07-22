/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 17
 */
package com.plgrim.ncp.biz.claim.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

/**
 * 클레임상세 반품 result
 *
 * @author shhenry.choi
 * @since 2015. 3. 18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClmDetailListResult extends AbstractResult {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 1768813687941885564L;

	private String godNo;						// 상품번호                 
	private String godNm;                      	// 상품명                   
	private String itmNo;                      	// 단품번호                 
	private String itmNm;                      	// 단품명       
	private String itmNmDlivy;                      	// 단품명 
	private String clmResnCd;                  // 클레임사유코드                   
	private String clmResnNm;                  	// 클레임사유명             
	private String dcDtl;                     	// 할인상세                   
	private String rtlPrc;                     	// 정소가                   
	private String saleAmt;                    	// 판매금액                 
	private String webDcAmt;                   	// 웹할인금액               
	private String empDcAmt;                   	// 임직원 할인 금액                    
	private String allDcAmt;                   	// 전체 할인 금액           
	private String imdtlDcAmt;                 	// 즉시 할인 금액           
	private String godDcAmt;                   	// 상품 할인 금액           
	private String signlDcAmt;                 	// 싱글 할인 금액           
	private String b2eSpslDcAmt;               	// B2E 특판 할인 금액       
	private String bundleDcAmt;                	// 묶음 할인 금액           
	private String crsDcAmt;                   	// 교차 할인 금액           
	private String godCpnDcAmt;                	// 상품 쿠폰 할인 금액      
	private String bskCpnDcAmt;                	// 장바구니 쿠폰 할인 금액  
	private String unityPntUseAmt;             	// 통합 포인트 사용 금액    
	private String evtPntUseAmt;               	// 이벤트 포인트 사용 금액  
	private String webPntUseAmt;               	// 웹포인트 사용 금액              
	private String unityPntAccmlamt;           	// 통합 포인트 적립 금액    
	private String evtPntAccmlamt;             	// 이벤트 포인트 적립 금액  
	private String webPntAccmlamt;             	// 웹포인트 적립 금액              
	private String godDcSumAmt;                	// 할인금액                 
	private String godCpnDcSumAmt;             	// 쿠폰할인금액             
	private String godEtcDcSumAmt;             	// 기타할인금액             
	private String stdrCrncySumAmt;            	// 주결제금액               
	private String addrseBaseAddr;             	// 수취인 기본주소          
	private String addrseDetailAddr;           	// 수취인 상세주소          
	private String addrseMobilAreaNo;          	// 수취인 휴대전화 국가번호 
	private String addrseMobilNationNo;        	// 수취인 휴대전화 지역번호 
	private String addrseMobilTlofNo;          	// 수취인 휴대전화 국번호   
	private String addrseMobilTlofWthnNo;      	// 수취인 휴대전화 국내번호 
	private String dlvMnCd;                    	// 회수방식코드             
	private String exchgDlvCst;                	// 교환배송비               
	private String waybilNoErpTrnsmisCd;       	// 운송장 번호 ERP 전송 코드
	private String waybilNo;       
	private String waybilNoDlivy;      
	private String shtgDcsnYn;      
	private String dlivyStatNm;   
	private String rtrvlDrctGodNo;             	// 회수지시 상품 번호       
	private String ordNo;                      	// 주문 번호                
	private String clmNo;                      	// 클레임 번호              
	private String gftYn;                      	// 사은품 여부              
	private String clmWrhsGodTurn;             	// 클레임 입고 상품 순번    
	private String dlivyDrctGodNo;             	// 출고지시 상품 번호      
	private String dlivyDrctDt;             	// 출고지시 일시
	private String dlivyComptDt;             	// 출고완료 일시
	private String dlvTurn;                    	// 배송 순번                
	private String rtrvlShopId;                	// 회수 매장 ID             
	private String shopNm;                     	// 회수 매장 명             
	private String acmtlGodWt;                 	// 누적 상품 무게           
	private String acmtlGodVol;                	// 누적 상품 부피           
	private String lgsItmYn;                   	// 물류 단품 여부           
	private String rtrvlDrctTgtYn;             	// 회수지시 대상 여부       
	private String rtrvlDrctYn;                	// 회수지시 여부            
	private String rtrvlDrctGrpDgre;           	// 회수지시 그룹 차수       
	private String rtrvlDrctCount;             	// 회수지시 횟수            
	private String rtrvlDrctTpCd;              	// 회수지시 유형 코드       
	private String rtrvlDrctDt;                	// 회수지시 일시            
	private String rtrvlDrctQty;               	// 회수지시 수량            
	private String rtrvlDrctWthdrawQty;        	// 회수지시 철회 수량       
	private String rtrvlDrctWthdrawDt;         	// 회수지시 철회 일시       
	private String rtrvlStatCd;                	// 회수 상태 코드           
	private String rtrvlStatNm;                	// 회수 상태 명             
	private String wrhsComptDt;                	// 입고 완료 일시           
	private String rtrvlComptDt;               	// 회수 완료 일시          
	private String udterId;
	private String rtrvlGodStatCd;             	// 회수 상품 상태 코드      
	private String rtrvlGodPrcsComptYn;        	// 회수 상품 처리 완료 여부 
	private String badnReqestCont;             	// 불량 의뢰 내용           
	private String hdryComTrnsmisTgtYn;        	// 택배사 전송 대상 여부    
	private String hdryComTrnsmisYn;           	// 택배사 전송 여부         
	private String hdryComTrnsmisDt;           	// 택배사 전송 일시         
	private String hdryComKey;                 	// 택배사 키                
	private String erpTrnsmisYn;               	// ERP 전송 여부
	private String erpTrnsmisDt;               	// ERP 전송 일시
	private String erpCnfirmYn;                	// ERP 확인 여부
	private String erpCnfirmDt;                	// ERP 확인 일시
	private String erpInvTrnsmisSectCd;        	// ERP 재고 전송 구분 코드
	private String erpInvTrnsmisDt;            	// ERP 재고 전송 일시
	private String rtgodcstCalSectCd;          	// 반품비 정산 구분 코드    
	private String rtgodcstCalAmt;             	// 반품비 정산 금액         
	private String pckageGodTurn;              	// 패키지상품순번           
	private String dlvStatCd;                  	// 출고배송상태코드             
	private String dlvStatNm;                  	// 출고배송상태명             
	private String dlivyDrctQty;               	// 출고지시 수량            
	private String dlivyDrctCnclQty;           	// 출고지시 취소 수량       
	private String dlivyQty;           			// 출고수량       
	private String mbrNo;                      	// 회원번호                 
	private String mbrTpCd;                    	// 회원유형코드             
	private String mbrTp;                      	// 회원유형                 
	private String ordCstGbn;                  	// 주문배송비여부           
	private String ordGodTurn;                 	// 주문상품순번             
	private String godTpCd;                    	// 상품유형코드            
	private String payExchgRtCrncyAmt;         	// 결제환율통화금액            
	private String clmWthdrawSectCd;         	// 클레임 철회 구분 코드      
	
    private String dlvPcupspTurnWthdraw;			// 클레임 교환철회용 회수지
    private String dlvPcupspTurnDlivyWthdraw;		// 클레임 교환철회용 수거지

    private String partmalSectCd;		// 입점 구분 코드
    
    private String dlvCstCpnYn;		// 무료 반품 교환 쿠폰
    
    private String gdgpStatCd;  	// 수거상태

//	private String godNo;								// 상품번호
//	private String godNm;                      			// 상품명
//	private String itmNo;                      			// 단품번호
//	private String itmNm;                      			// 단품명
//	private java.math.BigDecimal rtlPrc;                // 정소가
//	private java.math.BigDecimal saleAmt;               // 판매금액
//	private java.math.BigDecimal webDcAmt;              // 웹할인금액
//	private java.math.BigDecimal empDcAmt;              // 임직원 할인 금액
//	private java.math.BigDecimal allDcAmt;              // 전체 할인 금액
//	private java.math.BigDecimal imdtlDcAmt;            // 즉시 할인 금액
//	private java.math.BigDecimal godDcAmt;              // 상품 할인 금액
//	private java.math.BigDecimal signlDcAmt;            // 싱글 할인 금액
//	private java.math.BigDecimal b2eSpslDcAmt;          // B2E 특판 할인 금액
//	private java.math.BigDecimal bundleDcAmt;           // 묶음 할인 금액
//	private java.math.BigDecimal crsDcAmt;              // 교차 할인 금액
//	private java.math.BigDecimal godCpnDcAmt;           // 상품 쿠폰 할인 금액
//	private java.math.BigDecimal bskCpnDcAmt;           // 장바구니 쿠폰 할인 금액
//	private java.math.BigDecimal unityPntUseAmt;        // 통합 포인트 사용 금액
//	private java.math.BigDecimal evtPntUseAmt;          // 이벤트 포인트 사용 금액
//	private java.math.BigDecimal webPntUseAmt;          // 웹포인트 사용 금액
//	private java.math.BigDecimal unityPntAccmlamt;      // 통합 포인트 적립 금액
//	private java.math.BigDecimal evtPntAccmlamt;        // 이벤트 포인트 적립 금액
//	private java.math.BigDecimal webPntAccmlamt;        // 웹포인트 적립 금액
//	private java.math.BigDecimal godDcSumAmt;           // 할인금액
//	private java.math.BigDecimal godCpnDcSumAmt;        // 쿠폰할인금액
//	private java.math.BigDecimal godEtcDcSumAmt;        // 기타할인금액
//	private java.math.BigDecimal stdrCrncySumAmt;       // 주결제금액
//	private String addrseBaseAddr;             			// 수취인 기본주소
//	private String addrseDetailAddr;           			// 수취인 상세주소
//	private String addrseMobilAreaNo;          			// 수취인 휴대전화 국가번호
//	private String addrseMobilNationNo;        			// 수취인 휴대전화 지역번호
//	private String addrseMobilTlofNo;          			// 수취인 휴대전화 국번호
//	private String addrseMobilTlofWthnNo;      			// 수취인 휴대전화 국내번호
//	private String dlvMnCd;                    			// 회수방식코드
//	private String exchgDlvCst;                			// 교환배송비
//	private String waybilNoErpTrnsmisCd;       			// 운송장 번호 ERP 전송 코드
//	private String rtrvlDrctGodNo;             			// 회수지시 상품 번호
//	private String ordNo;                      			// 주문 번호
//	private String clmNo;                      			// 클레임 번호
//	private String gftYn;                      			// 사은품 여부
//	private java.lang.Integer clmWrhsGodTurn; 			// 클레임 입고 상품 순번
//	private String dlivyDrctGodNo;             			// 출고지시 상품 번호
//	private java.lang.Integer dlvTurn;         			// 배송 순번
//	private String rtrvlShopId;                			// 회수 매장 ID
//	private String shopNm;                     			// 회수 매장 명
//	private String acmtlGodWt;                 			// 누적 상품 무게
//	private String acmtlGodVol;                			// 누적 상품 부피
//	private String lgsItmYn;                   			// 물류 단품 여부
//	private String rtrvlDrctTgtYn;             			// 회수지시 대상 여부
//	private String rtrvlDrctYn;                			// 회수지시 여부
//	private String rtrvlDrctGrpDgre;           			// 회수지시 그룹 차수
//	private String rtrvlDrctCount;             			// 회수지시 횟수
//	private String rtrvlDrctTpCd;              			// 회수지시 유형 코드
//	private String rtrvlDrctDt;                			// 회수지시 일시
//	private java.lang.Long rtrvlDrctQty;        		// 회수지시 수량
//	private java.lang.Long rtrvlDrctWthdrawQty; 		// 회수지시 철회 수량
//	private String rtrvlDrctWthdrawDt;         			// 회수지시 철회 일시
//	private String rtrvlStatCd;                			// 회수 상태 코드
//	private String rtrvlStatNm;                			// 회수 상태 명
//	private String wrhsComptDt;                			// 입고 완료 일시
//	private String rtrvlComptDt;               			// 회수 완료 일시
//	private String rtrvlGodStatCd;             			// 회수 상품 상태 코드
//	private String rtrvlGodPrcsComptYn;        			// 회수 상품 처리 완료 여부
//	private String badnReqestCont;             			// 불량 의뢰 내용
//	private String hdryComTrnsmisTgtYn;        			// 택배사 전송 대상 여부
//	private String hdryComTrnsmisYn;           			// 택배사 전송 여부
//	private String hdryComTrnsmisDt;           			// 택배사 전송 일시
//	private String hdryComKey;                 			// 택배사 키
//	private String erpTrnsmisYn;               			// ERP 전송 여부
//	private String erpTrnsmisDt;               			// ERP 전송 일시
//	private String erpCnfirmYn;                			// ERP 확인 여부
//	private String erpCnfirmDt;                			// ERP 확인 일시
//	private String erpInvTrnsmisSectCd;        			// ERP 재고 전송 구분 코드
//	private String erpInvTrnsmisDt;            			// ERP 재고 전송 일시
//	private String rtgodcstCalSectCd;          			// 반품비 정산 구분 코드
//	private java.math.BigDecimal rtgodcstCalAmt;		// 반품비 정산 금액
//	private java.lang.Integer pckageGodTurn;  			// 패키지상품순번
//	private String dlvStatCd;                  			// 출고배송상태코드             
//	private String dlvStatNm;                  			// 출고배송상태명             
//	private java.lang.Long dlivyDrctQty;        		// 출고지시 수량
//	private java.lang.Long dlivyDrctCnclQty;     		// 출고지시 취소 수량
//	private java.lang.Long dlivyQty;     				// 출고수량
//	private String mbrNo;                      			// 회원번호
//	private String mbrTpCd;                    			// 회원유형코드
//	private String mbrTp;                      			// 회원유형
//	private String ordCstGbn;                  			// 주문배송비여부
//	private String ordGodTurn;                 			// 주문상품순번             
//	private String godTpCd;                    			// 상품유형코드            
//	private java.math.BigDecimal payExchgRtCrncyAmt;    // 결제환율통화금액            
	
	

}
