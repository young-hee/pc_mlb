package com.plgrim.ncp.commons.data.order;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class NaverReturnDetailDTO  extends AbstractOrderDTO{
	
	
	String payHistId;
	Long npointPayAmount;
	Long primaryPayAmount;
	String productName;
	String paymentId;
	Long totalPayAmount;
	String merchantPayKey;
	String merchantUserKey;
	String cardCorpCode;
	String merchantName;
	String primaryPayMeans;
	String merchantId;
	String admissionYmdt;
	String admissionTypeCode;
	Long settleExpectAmount;
	Long payCommissionAmount;
	String tradeConfirmYmdt;
	String admissionState;
	String cardNo;
	String cardAuthNo;
	Long cardInstCount;
	String bankCorpCode;
	String bankAccountNo;
	boolean settleExpected;


}
