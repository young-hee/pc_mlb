package com.plgrim.ncp.biz.member.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageMtmFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 *
	 */
	private static final long serialVersionUID = -7893147324413144491L;

	/**1:1 문의 리스트 Entity */
	CsoMtmInq csoMtmInq;

	/**1:1 문의 답변 Entity	 */
	CsoMtmInqAns csoMtmInqAns;
	/**1:1 문의 파일 Entity	 */
	CsoMtmInqAtchFile csoMtmInqAtchFile;
	/**1:1 문의 주문상품 Entity	 */
	CsoMtmInqOrdGod csoMtmInqOrdGod;
	/**주문 Entity	 */
	Ord ord;
	/**주문상품 Entity	 */
	OrdGodExtend ordGod;
	/**단품 Entity	 */
	GodItmHist godItmHist;

	List<MypageMtmFoResult> ordGodList;

	private String srchMtmInqSn;

	private String totalRow;

	private String inqRegDt;

	private String ansRegDt;

	private String mbrNo;

	private String mbrEmail;

	private String mobilNo;

	private String ordDt;

	private String inqDt;

	private String mbrId;

	private String mbrNm;

	private String chkDay;

	private Long mtmInqSn;

	private String mallId;

	private String dvcCd;

	private String langCd;

	private long mtmInqOrdGodTurn;

	private String deleteYn;

	private String mobilNationNo;

	private String fileNm;

	private List<String> deleteFileNm;

	private String url;

	private String nm;

	private String mbrCheck;

	private long psnlInfoModHistSn;                 //개인정보 수정이력 seq

	private long psnlInfoModHistTurn;               //개인정보 수정이력 순서

	private String colorStyleCd;				//컬러칩

	private String brndNm; //브랜드이름
	
	private String rowNum; //1:1문의순번
	
	private String inqDateTime; //문의일자시간
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
