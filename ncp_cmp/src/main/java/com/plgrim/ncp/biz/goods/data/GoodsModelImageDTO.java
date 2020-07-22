package com.plgrim.ncp.biz.goods.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.god.GodModel;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelSize;

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
 * Description	:	상품 모델 정보 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsModelImageDTO")
public class GoodsModelImageDTO extends AbstractDTO{

	private static final long serialVersionUID = -6028356236828930491L;

	/*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    /** 로그인 아이디. */
    String loginId;
    
    /** 결과 메세지. */
    String rtnMsg;

    /** 상품 모델 엔티티. */
    private GodModel godModel = new GodModel();
    
    /** 상품 모델 사이즈 엔티티. */
    private GodModelSize godModelSize = new GodModelSize();
    
    /** 상품 모델 이미지 엔티티. */
    private GodModelImg godModelImg = new GodModelImg();
    
    /** 상품 모델 이미지 확장 엔티티. */
    private GodModelImgExtend godModelImgEx = new GodModelImgExtend();
    
    /** 상품 모델 이미지 연결 엔티티. */
    private GodModelImgCnnc godModelImgCnnc = new GodModelImgCnnc(); 

    /** 상품 모델 사이즈 리스트. */
    private List<GodModelSize> modelSizeList = new ArrayList<GodModelSize>();
    
    /** 상품 모델 이미지 리스트. */
    private List<GodModelImg> modelImgList = new ArrayList<GodModelImg>();

    /** 상품 모델 이미지 확장 리스트. */
    private List<GodModelImgExtend> modelImgExList = new ArrayList<GodModelImgExtend>();
    
    /** 상품 모델 이미지 연결 리스트. */
    private List<GodModelImgCnnc> modelImgCnncList = new ArrayList<GodModelImgCnnc>();
    
    

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
    /**
     * @param loginId
     */
    public void setLoginId(String loginId) {
        
        this.loginId = loginId;        
        
        if(godModel != null){
            godModel.setRegtrId(loginId);
            godModel.setUdterId(loginId);                     
        }
        
        if(godModelSize != null){
            godModelSize.setRegtrId(loginId);
            godModelSize.setUdterId(loginId);            
        }
        
        if(godModelImg != null){
            godModelImg.setRegtrId(loginId);
            godModelImg.setUdterId(loginId);         
        }

        if(godModelImgCnnc != null){
            godModelImgCnnc.setRegtrId(loginId);
            godModelImgCnnc.setUdterId(loginId);
        }
                
        if(!(modelSizeList.isEmpty())){
            for(GodModelSize modelSize : modelSizeList){
                modelSize.setRegtrId(loginId);
                modelSize.setUdterId(loginId);               
            }
        }

        if(!(modelImgList.isEmpty())){
            for(GodModelImg modelImg : modelImgList){
                modelImg.setRegtrId(loginId);
                modelImg.setUdterId(loginId);               
            }
        }        

        if(!(modelImgExList.isEmpty())){
            for(GodModelImgExtend modelImgEx : modelImgExList){
                modelImgEx.setRegtrId(loginId);
                modelImgEx.setUdterId(loginId);               
            }
        }        
        
        if(!(modelImgCnncList.isEmpty())){
            for(GodModelImgCnnc godModelImgCnnc : modelImgCnncList){
                godModelImgCnnc.setRegtrId(loginId);
                godModelImgCnnc.setUdterId(loginId);             
            }
        }        

    }    

}
