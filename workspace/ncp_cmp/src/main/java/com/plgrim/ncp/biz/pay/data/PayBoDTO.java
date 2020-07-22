package com.plgrim.ncp.biz.pay.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;

@Data
@EqualsAndHashCode(callSuper = false)
public class PayBoDTO extends AbstractDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6233713899703298612L;
	private List<Pay> payList;// 결제
	private Mbr mbr ;
	private String ordNo;
}
