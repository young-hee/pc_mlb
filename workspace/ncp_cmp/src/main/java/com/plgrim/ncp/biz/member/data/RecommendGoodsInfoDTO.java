package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;

/**
 * Created by Jieun on 2016-09-22.
 */
@Data
public class RecommendGoodsInfoDTO  extends AbstractDTO {
	
	private String godNo;
	private String erpGodNo;
    private String dsgnGrpNo;
    private String godNm;
    private String mobileGodNm;
    private String tagNm;
    private String colorTagNm;
    private String themaTagNm;
    private String textIconCont;
    private String godAprvSectCd;
    private String godSaleSectCd;
    private String saleBegDate;
    private String saleEndDate;
    private String colorNm;
    private String colorCd;
    private String clorChipImgUrl;
    private String godTpCd;
    private String partmalSectCd;
    private String godBbrndId;
    private String godBrndGrpId;
    private String stdCtgryNo;
    private String tdfSect;
    private String sesonGrpCd;
    private String sesonCd;
    private String prdlstCd;
    private String otltYn;
    private String comId;
    private String brndId;
    private String upperBrndId;
    private String brndNm;
    private String regDt;
    private String rtlPrc;
    private String dgprcLastSalePrc;
    private String dgprcGodDcRt;
    private String dgprcCpnDcRt;
    private String bstGodSaleIdex;
    private String saleIdex;
    private String newGodIdex;
    private String glgnmGodNo;
    private String langCd;
    private String glgnmGodNm;
    private String glgnmMobileGodNm;
    private String glgnmTagNm;
    private String glgnmColorTagNm;
    private String glgnmThemaTagNm;
    private String glgnmColorNm;
    private String imgFrontUrl;
    private String imgBackUrl;
    private String colorStyleCd;
    private String sesonGrpNm;
	
}