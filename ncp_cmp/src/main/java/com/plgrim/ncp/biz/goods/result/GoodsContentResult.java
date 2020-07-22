package com.plgrim.ncp.biz.goods.result;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmLang;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGodExtend;

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
 * Description	:	상품 컨텐츠 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("goodsContentResult")
public class GoodsContentResult extends GoodsResult {
	private static final long serialVersionUID = -865857960662309234L;
	
	
    /** 상품 공지사항 정보 리스트 */
    private List<GodNotiExtend> godNotiExList;
    
	/** 상품 상품평 확장 목록. */
	private Page<GodGodEvlExtend> godGodEvlExList;
	
    /** 상품 상품평 첨부 파일 리스트. */
    private List<GodGodEvlAtchFile> godGodEvlAtchFileList;    
    
    /** 업체 국내 배송정책. */
    private ComDmstcDlvCstPlc comDmstcDlvCstPlc;

    /** 카드 이벤트. */
    private Evt cardEvent;
    
    /** 이벤트 목록. */
    private List<Evt> eventList; 

    /** 기획전 목록. */
    private List<DspPromt> promotionList;
    
    /** 사은품 프로모션 여부. */
    private String giftPromoAplYn;
    
    /** 상품 쿠폰 프로모션.*/
    private PrmCpnGodExtend prmCpnGod;
    
    /** 상품 쿠폰 프로모션 목록.*/
    private List<PrmCpnGodExtend> prmCpnList;

    /** 프로모션 언어.*/
    private PrmLang prmCpnLang;
    
    /** 프로모션 언어별 목록.*/
    private List<PrmLang> prmLangList;
    
    /** 신규가입 카테고리 쿠폰 */
    private PrmCpnGodExtend prmCpnDspCtgry;
        
}
