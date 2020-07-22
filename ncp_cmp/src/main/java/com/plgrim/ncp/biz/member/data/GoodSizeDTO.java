package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;

/**
 * Created by Jieun on 2016-09-22.
 */
@Data
public class GoodSizeDTO  extends AbstractDTO {

	private String brstBegVal;
	private String brstEndVal;
	private String brstMidVal;
	private String waistBegVal;
	private String waistEndVal;
	private String waistMidVal;
}
