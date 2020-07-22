package com.plgrim.ncp.biz.order.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("godSvcResult")
public class GodSvcResult extends AbstractResult {



	/**
	 * UUID
	 */
	private static final long serialVersionUID = -1110357570831265341L;



	private Long svcSn;

	private String svcTpCd;

	private String svcDetailTpCd;

	private String freeSvcYn;

	private String crncyCd;

	private java.math.BigDecimal svcAmt;

	private String svcOfferYn;

	private String essntlOfferYn;

	private String svcImgFileUrl;

	private String svcImgFileNm;

	private String brndNm;


}
