package com.plgrim.ncp.interfaces.order.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(value=Include.NON_EMPTY)
public class OrderPaymentInfoResultSDO extends AbstractSDO {
	
	private static final long serialVersionUID = 1L;

    /**
     * 주문번호
     */
    @JsonProperty("ORD_NO")
    private String ordNo;
    
    /**
     * 주문상품번호
     */
    @JsonProperty("ORD_DTL_NO")
    private String ordDtlNo;
    
    /**
     * 결과코드 (00:정상, 99:실패)
     */
    @JsonProperty("RESULT_CD")
    private String resultCd;
    /**
     * 결과메시지
     */
    @JsonProperty("RESULT_MSG")
    private String resultMsg;
    
}
