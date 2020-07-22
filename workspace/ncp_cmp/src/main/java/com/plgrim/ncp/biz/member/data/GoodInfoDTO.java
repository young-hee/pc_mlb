package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;

/**
 * Created by Jieun on 2016-09-22.
 */
@Data
public class GoodInfoDTO  extends AbstractDTO {

	private String itmNo;
	private String godNo;
	private String skuNo;
	private String itmStatCd;
	
}
