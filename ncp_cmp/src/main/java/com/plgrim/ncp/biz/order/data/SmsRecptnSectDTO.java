package com.plgrim.ncp.biz.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class SmsRecptnSectDTO extends AbstractDTO {

	private static final long serialVersionUID = -4737894158230756578L;

	private String ordNo;
	private String smsRecptnSectCd;
	private String regtrId;
	private String udterId;

}
