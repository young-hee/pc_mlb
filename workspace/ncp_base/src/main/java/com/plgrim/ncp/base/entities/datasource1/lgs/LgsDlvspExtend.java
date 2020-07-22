package com.plgrim.ncp.base.entities.datasource1.lgs;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;

@Data
@EqualsAndHashCode(callSuper = false)
public class LgsDlvspExtend extends LgsDlvsp {


    /**
     *
     */
    private static final long serialVersionUID = -4027197063254111725L;
    private List<LgsDlvFoExtend> lgsDlvList;// 물류배송

    private List<LgsDlv> lgsDlvCoList;
    private List<OrdGodExtend> ordGodList;

    private List<OrdGodExtend> ordGodGifList;

    private java.lang.Integer dlvAdbukTurn;

    private String mbrNo;

    private String mobilNo;

    private String shopId;

    private String fullAddrs;

    private java.lang.Integer realityDlvCst;

    private String realityDlvCstEx;


    private java.math.BigDecimal payExchgRtCrncyAmt;

    private boolean mbrCheck = true;
    private String telNo;
    private String shopNm;


    private List<OrdGodAplPrmExtend> gifts;

    /*********************************************************************
     * 클레임
     **********************************************************************/

    private java.lang.Integer cnclDlvCst;

    /**
     * 배송 순번
     */
    private java.lang.Integer dlvTurn;

    private java.lang.Integer dlvCstCpnDcAmt;

//    private java.lang.Long dmstcDlvCstPlcSn;
//    private java.lang.Long ovseaDlvCstPlcSn;

	/**
	 * 국내 배송비 정책 일련번호
	 */
	private java.lang.Long dmstcDlvCstPlcSn;
	/**
	 * 해외 배송 국내 배송비 정책 일련번호
	 */
	private java.lang.Long ovseaDlvDmstcDlvCstPlcSn;
	/**
	 * 해외 배송비 정책 일련번호
	 */
	private java.lang.Long ovseaDlvCstPlcSn;



    /**
     * 클레임입고상품 확장
     */
    private List<ClmWrhsGodExtend> clmWrhsGodList;

    /**
     * 반품사유 일괄적용
     * 물류 배송지별 반품사유를 일괄적용하기 위한 value
     */
    private String clmResnCdArr;

    /**
     * 회수방식
     * 물류 배송지별 회수방식을 적용하기 위한 value
     */
    private String dlvMnCdArr;

    //ncp2.khan 물류 배송지.배송 수단 코드 --> 물류 배송.배송 수단 코드
    private String dlvMnCd;

    private String maskingYn;
    
    private String dlvCstCpnInfo;
	
    /* 
	 * 배송지변경 여부
	 */
	private String dlvspChgYn;
    
    /*********************************************************************
     * 클레임
     **********************************************************************/

	private String comId; //업체 코드

    private String crncyCd;

    private String ordTpCd;
    
    private String clmTpCd;
    
    private String dlvCstCpnNo; //반품/교환 무료배송비 쿠폰

}
