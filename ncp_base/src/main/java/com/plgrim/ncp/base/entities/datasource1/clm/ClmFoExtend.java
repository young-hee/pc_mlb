/**
 * @package : com.plgrim.ncp.base.entities..clm
 * @author : jackie(jackie)
 * @date : 20150427
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.clm;

import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import java.util.List;



/**
 * 클레임
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("clmFoExtend")
public class ClmFoExtend extends Clm{
	private static final long serialVersionUID = -338074828482249789L;
	
	private List<LgsDlvspFoExtend> lgsDlvspFoExtend;   
	
	private List<ClmWrhsGodExtend> clmWrhsGodList;
	
	private java.math.BigDecimal clmDlvCstSumAmt;   /*클레임배송비*/
		
	private java.math.BigDecimal plcDlvCst;       	/*정책배송비*/
	private java.math.BigDecimal rtgodDlvCst;       /*반품배송비*/
	private java.math.BigDecimal cnclDlvCst;        /*환불배송비*/
	private java.math.BigDecimal exchgDlvCst;       /*교환배송비*/


	private String rfdBnkAcctNo;    				/*환불계좌*/
	private String rfdBnkCd;        		    	/*환불계좌 은행코드*/
	private String rfdAcnthldrNm;   				/*환불계자 예금주명*/
	private String ordGodTurn;      				/*주문순번*/
	private String payMnCd;         				/*결제구분코드*/
	private String dealTpCd;         				/*거래유형코드*/
	private String dlvComCd;        				/*택배사코드*/
	private String cvnstorHdryAprvNo;        		/*편의점택배승인코드*/
	private String dlvMnCd;         				/*배송수단코드*/
	private String shopNm;        		        	/* 매장명 */
	private String shopBaseAddr;        			/* 매장주소1 */
	private String shopDetailAddr;        			/* 매장주소2 */
	private String shopTelTlofWthnNo;        		/* 매장전화번호 */
	private String wkdyOperBegHhmm;        			/* 매장운영시간1 */
	private String wkdyOperEndHhmm;        			/* 매장운영시간2 */
	private String shopId;          				/* 매장ID */
	private String shopBrndId;          			/* 매장브랜드ID */
	private java.math.BigDecimal ordPayAmt;     	/* 주문금액 */
	private java.math.BigDecimal payCrncyPayAmt;    /* 교환배송금액 */
	
	private java.util.Date clmReqDt;				/* 클레임신청일 */
	
	/*
	 * ncp 3
	 * 주문상세 클레임정보에 결제수단 추가
	 */
	private List<Pay> payList;                    	/* 결제정보 */

	/*
	 * ncp 3
	 * 언어코드
	 */
	String lang;

	private java.math.BigDecimal rtgodDlvCstSum;   /* 반품배송비 합계 금액 */
	private java.math.BigDecimal rtgodDlvCstSumEx;   
	
	private java.math.BigDecimal cnclDlvCstSum;    /* 환불배송비 합계 금액 */
	private String dlvComNm;						/* 반품택배사 */
	private String ovseaWaybilNo;					/* 해외반품송장번호 */

	private java.math.BigDecimal clmSaleAmtExSum;  	/*클레임상품 판매금액 환율적용 합계*/

	private java.math.BigDecimal exchgDlvCstSum;   /* 교환배송비 합계 금액 */
	
	private String dlvCstCpnNo;		/* 배송비 쿠폰 번호 */
	private String dlvCstCpnPrmNo;	/* 배송비 쿠폰 프로모션번호 */

}
