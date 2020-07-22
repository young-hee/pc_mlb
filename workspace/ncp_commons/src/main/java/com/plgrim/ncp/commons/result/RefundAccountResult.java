package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class RefundAccountResult extends AbstractResult {

	String seqNo;
	String userId;
	String orderNo;
	String userGb;
	String codeValue;
	
}
