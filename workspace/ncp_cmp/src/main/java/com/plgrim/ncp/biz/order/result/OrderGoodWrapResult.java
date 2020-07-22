package com.plgrim.ncp.biz.order.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("orderGoodWrapResult")
public class OrderGoodWrapResult extends AbstractResult {


	/**
	 * UUID
	 */
	private static final long serialVersionUID = -7612166376584556599L;


	private String bskNo;

	private String godNo;

	private String godNm;

	private String colorNm;

	private String itmNo;

	private String itmNm;

	private String itmQty;

	private String brndId;

	private String brndGrpId;

	private java.lang.Long svcSn;

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

	private String prdlstCd;

	private String brndStart;

	private String brndEnd;

	private String imgUrl;

	private String endpBrndNm;

	private String useYn;

	List<GodSvcResult> godSvcResultList;


}
