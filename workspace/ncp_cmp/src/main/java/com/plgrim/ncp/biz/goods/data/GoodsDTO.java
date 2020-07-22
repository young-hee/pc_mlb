package com.plgrim.ncp.biz.goods.data;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodCnncGrpGod;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodDsgnGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodMovi;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodSaleMall;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInv;
import com.plgrim.ncp.framework.utils.Utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	상품 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsDTO")
public class GoodsDTO extends AbstractDTO {
    private static final long serialVersionUID = 8254494513506427443L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */    
    /** 로그인 아이디. */
    String loginId;

    /** 업체ID. */
    String comId;

    /** 상품 이미지 업로드 유형. */
    String imgInsertType;
    
    /** 상품 이미지 소스 경로. */
    String imgSrcPath;

    /** 상품 이미지 소스 경로(일괄). */
    String imgBulkSrcPath;

	/** 상품 엔티티. */
	private God god;
	
	/** 상품 디자인 그룹 엔티티. */
	private GodDsgnGrp godDsgnGrp;

	/** 상품 단품 엔티티. */
	private GodItm godItm;
			
    /** 상품 언어별 상품명 엔티티 */
    private GodLangbyGodNm godLangbyGodNm;

	/** 상품 고시 상세 엔티티. */
	private GodNtfcDetail godNtfcDetail;

	/** 상품 이미지 엔티티. */
	private GodImg godImg;
	
	/** 상품 동영상 엔티티. */
	private GodMovi godMovi;
	
	/** 상품 매장 단품 재고 */
	private GodShopItmInv godShopItmInv;
	    
    /** 상품 구성 상품 연결 엔티티. */
    private GodCpstGodCnnc godCpstGodCnnc;
    
    /** 상품 판매 몰 엔티티. */
    private GodSaleMall godSaleMall;
    
    /** 상품 연결 상품 엔티티. */
    private GodCnncGrpGod godCnncGrpGod;
    
    /** 상품 리스트 */
    private List<God> godList;
    
    /** 상품 단품 리스트 */
    private List<GodItmExtend> godItmExtendList;
    
    /** 전시카테고리 연결 상품 리스트 */
    private List<DspCtgryCnncGod> dspCtgryCnncGodList;
    
    /** 상품 구성 상품 연결 리스트 */
    private List<GodCpstGodCnnc> godCpstGodCnncList;
    
    /** 상품 판매 몰 리스트 */
    private List<GodSaleMall> godSaleMallList;
    
    /** 상품 이미지 리스트 */
    private List<GodImgExtend> godImgExtendList;
        
    /** 상품 언어별 상품명 리스트 */
    private List<GodLangbyGodNm> godLangbyGodNmList;
    
    /** 상품 고시정보 리스트 */
    private List<GodNtfcDetail> godNtfcDetailList;
    
    /** 연관상품 리스트 */
    private List<GodRelateExtend> godRelateExtendList;
    
    
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
    /**
     * 선언된 객체에 loginId 설정
     * 
     * @param loginId
     */
    public void setLoginId(String loginId) {    	
        
    	this.loginId = loginId;        
        
        if(!Utils.empty(godDsgnGrp)){
        	godDsgnGrp.setRegtrId(loginId);
        	godDsgnGrp.setUdterId(loginId);         	
        }
        
        if(!Utils.empty(god)){
        	god.setRegtrId(loginId);
        	god.setUdterId(loginId);
        }
        
        if(!Utils.empty(godItm)){
        	godItm.setRegtrId(loginId);
        	godItm.setUdterId(loginId);       
        }
        
        if(!Utils.empty(godNtfcDetail)){
            godNtfcDetail.setRegtrId(loginId);
            godNtfcDetail.setUdterId(loginId);  
        }
        
        if(!Utils.empty(godImg)){
            godImg.setRegtrId(loginId);
            godImg.setUdterId(loginId);    
        }
        
        if(!Utils.empty(godMovi)){
        	godMovi.setRegtrId(loginId);
        	godMovi.setUdterId(loginId);    
        }
        
        if(!Utils.empty(godShopItmInv)){
            godShopItmInv.setRegtrId(loginId);
            godShopItmInv.setUdterId(loginId);
        }
        
        if(!Utils.empty(godCpstGodCnnc)){
            godCpstGodCnnc.setRegtrId(loginId);
            godCpstGodCnnc.setUdterId(loginId);
        }
        
        if(!Utils.empty(godList)){
        	for(God god : godList) {
            	god.setRegtrId(loginId);
            	god.setUdterId(loginId);        		
        	}
        }

        if(!Utils.empty(godItmExtendList)){
        	for(GodItmExtend godItmExtend : godItmExtendList){
        		godItmExtend.setRegtrId(loginId);
        		godItmExtend.setUdterId(loginId);  

        	}
        }
        
        if(!Utils.empty(dspCtgryCnncGodList)){
        	for(DspCtgryCnncGod dspCtgryCnncGod : dspCtgryCnncGodList){
        		dspCtgryCnncGod.setRegtrId(loginId);
        		dspCtgryCnncGod.setUdterId(loginId);
        	}
        }

        if(!Utils.empty(godCpstGodCnncList)){
        	for(GodCpstGodCnnc godCpstGodCnnc : godCpstGodCnncList){
        		godCpstGodCnnc.setRegtrId(loginId);
        		godCpstGodCnnc.setUdterId(loginId);
        	}
        }
        
        if(!Utils.empty(godSaleMallList)){
        	for(GodSaleMall godSaleMall : godSaleMallList){
        		godSaleMall.setRegtrId(loginId);
        		godSaleMall.setUdterId(loginId);
        	}
        }
        
        if(!Utils.empty(godImgExtendList)){
        	for(GodImg godImg : godImgExtendList){
        		godImg.setRegtrId(loginId);
        		godImg.setUdterId(loginId);
        	}
        }
        
        if(!Utils.empty(godLangbyGodNmList)) {
        	for(GodLangbyGodNm godLangbyGodNm: godLangbyGodNmList) {
        		godLangbyGodNm.setRegtrId(loginId);
        		godLangbyGodNm.setUdterId(loginId);
        	}
        }
        
        if(!Utils.empty(godNtfcDetailList)) {
        	for(GodNtfcDetail godNtfcDetail : godNtfcDetailList) {
        		godNtfcDetail.setRegtrId(loginId);
        		godNtfcDetail.setUdterId(loginId);        	
        	}
        }
        
    }     
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
