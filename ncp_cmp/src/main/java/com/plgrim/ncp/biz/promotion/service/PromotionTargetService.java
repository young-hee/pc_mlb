/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jw.vito.kim
 * @since       2015. 5. 6
 */
package com.plgrim.ncp.biz.promotion.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCrsDcAplGod;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplGoods;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplTarget;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionType;
import com.plgrim.ncp.biz.promotion.data.CouponPromotionDTO;
import com.plgrim.ncp.biz.promotion.data.PrmAplGodExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.exception.PromotionAlreadyExistApplyTargetException;
import com.plgrim.ncp.biz.promotion.exception.PromotionNotExistGoodException;
import com.plgrim.ncp.biz.promotion.repository.PromotionRepository;
import com.plgrim.ncp.biz.promotion.repository.PromotionTargetRepository;
import com.plgrim.ncp.biz.promotion.result.CouponPromotionResult;
import com.plgrim.ncp.biz.promotion.result.PromotionApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueApplyGoodsSDO;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueResultDataSDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PromotionTargetService {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /** The promotion target repository. */
    @Autowired
    private PromotionTargetRepository promotionTargetRepository;
    
    @Autowired
    private PromotionRepository promotionRepository;
    
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
     * 주문할인 프로모션의 기 등록된 적용상품이 있는지 체크.
     *
     * @param prmAplGodEx - prmNo, godNo
     * @return Promotion apply good bo result - ordDcGodCount, aplTgtGodGrpCd,
     *         godAplYn
     * @throws Exception the exception
     * @since 2015. 7. 7
     */
    public PromotionApplyGoodBoResult selectValidOrderPromotionApplyGoodInfo(PrmAplGodExtend prmAplGodEx) throws Exception {
        PromotionApplyGoodBoResult result = promotionTargetRepository.selectValidOrderPromotionApplyGoodInfo(prmAplGodEx);
        return result;
    }

    /**
     * 프로모션 적용대상.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param sqlSession [설명]
     * @param promotionBoDTO [설명]
     * @throws Exception the exception
     * @since 2015. 5. 6
     */
    public void insertPromotionApplyTarget(SqlSession sqlSession, PromotionBoDTO promotionBoDTO) throws Exception {

        String lang = "KOR";

        PrmAplTgt prmAplTgt = promotionBoDTO.getPrmAplTgt();
        String prmNo = promotionBoDTO.getPrm().getPrmNo();
        String prmTpCd = promotionBoDTO.getPrm().getPrmTpCd();

        //몰 ID
        List<String> mallList = new ArrayList<String>();

        if (StringService.isNotEmpty(prmAplTgt.getMallId())) {
            mallList = Arrays.asList(prmAplTgt.getMallId().split(PromotionSeparator.DELIMITER.toString()));
        }
        else {

            /* 몰 코드 임시 설정 */
            mallList.add("DXM");
            //            for (Code cd : CodeUtil.getCodes(PromotionAplTarget.MALL_ID.toString(), lang)) {
            //                mallList.add(cd.getCd());
            //            }
        }
        for (String mallId : mallList) {
            PrmAplTgt pat = new PrmAplTgt();

            pat.setMallId(mallId);
            pat.setPrmTgtTpCd(PromotionAplTarget.MALL_ID.toString());

            pat.setPrmNo(prmNo);
            pat.setRegtrId(prmAplTgt.getRegtrId());
            pat.setUdterId(prmAplTgt.getUdterId());

            promotionTargetRepository.insertPromotionApplyTarget(sqlSession, pat);
        }

        //언어 코드
        List<String> langList = new ArrayList<String>();

        if (StringService.isNotEmpty(prmAplTgt.getLangCd())) {
            langList = Arrays.asList(prmAplTgt.getLangCd().split(PromotionSeparator.DELIMITER.toString()));
        }
        else {
            for (Code cd : CodeUtil.getCodes(PromotionAplTarget.LANG.toString(), lang)) {
                langList.add(cd.getCd());
            }
        }
        for (String langCd : langList) {
            PrmAplTgt pat = new PrmAplTgt();

            pat.setLangCd(langCd);
            pat.setPrmTgtTpCd(PromotionAplTarget.LANG.toString());

            pat.setPrmNo(prmNo);
            pat.setRegtrId(prmAplTgt.getRegtrId());
            pat.setUdterId(prmAplTgt.getUdterId());

            promotionTargetRepository.insertPromotionApplyTarget(sqlSession, pat);
        }

        //디바이스 코드
        List<String> dvcList = new ArrayList<String>();

        if (StringService.isNotEmpty(prmAplTgt.getDvcCd())) {
            dvcList = Arrays.asList(prmAplTgt.getDvcCd().split(PromotionSeparator.DELIMITER.toString()));
        }
        else {
            for (Code cd : CodeUtil.getCodes(PromotionAplTarget.DVC.toString(), lang)) {
                dvcList.add(cd.getCd());
            }
        }
        for (String dvc : dvcList) {
            PrmAplTgt pat = new PrmAplTgt();

            pat.setDvcCd(dvc);
            pat.setPrmTgtTpCd(PromotionAplTarget.DVC.toString());

            pat.setPrmNo(prmNo);
            pat.setRegtrId(prmAplTgt.getRegtrId());
            pat.setUdterId(prmAplTgt.getUdterId());

            promotionTargetRepository.insertPromotionApplyTarget(sqlSession, pat);
        }

        //적용 회원 유형
        List<String> tgtMbrTpList = new ArrayList<String>();

        if (StringService.isNotEmpty(prmAplTgt.getTgtMbrTpCd())) {
            tgtMbrTpList = Arrays.asList(prmAplTgt.getTgtMbrTpCd().split(PromotionSeparator.DELIMITER.toString()));
        }
        else {
            for (Code cd : CodeUtil.getCodes(PromotionAplTarget.TGT_MBR_TP.toString(), lang)) {
                if (PromotionType.CPN.toString().equals(prmTpCd) && !"DFLT".equals(cd.getAsstnCd1())) {
                    continue;
                }
                tgtMbrTpList.add(cd.getCd());
            }
        }
        for (String tgtMbrTp : tgtMbrTpList) {
            PrmAplTgt pat = new PrmAplTgt();

            pat.setTgtMbrTpCd(tgtMbrTp);
            pat.setPrmTgtTpCd(PromotionAplTarget.TGT_MBR_TP.toString());

            pat.setPrmNo(prmNo);
            pat.setRegtrId(prmAplTgt.getRegtrId());
            pat.setUdterId(prmAplTgt.getUdterId());

            promotionTargetRepository.insertPromotionApplyTarget(sqlSession, pat);
        }

        //적용 회원 속성
        List<String> tgtMbrAtrbList = new ArrayList<String>();

        if (StringService.isNotEmpty(prmAplTgt.getTgtMbrAtrbCd())) {
            tgtMbrAtrbList = Arrays.asList(prmAplTgt.getTgtMbrAtrbCd().split(PromotionSeparator.DELIMITER.toString()));
        }
        else {
            for (Code cd : CodeUtil.getCodes(PromotionAplTarget.TGT_MBR_ATRB_DATA.toString(), lang)) {
                tgtMbrAtrbList.add(cd.getCd());
            }
        }
        for (String tgtMbrAtrb : tgtMbrAtrbList) {
            PrmAplTgt pat = new PrmAplTgt();

            pat.setTgtMbrAtrbCd(tgtMbrAtrb);
            pat.setPrmTgtTpCd(PromotionAplTarget.TGT_MBR_ATRB.toString());

            pat.setPrmNo(prmNo);
            pat.setRegtrId(prmAplTgt.getRegtrId());
            pat.setUdterId(prmAplTgt.getUdterId());

            promotionTargetRepository.insertPromotionApplyTarget(sqlSession, pat);
        }

        //그룹사 ID
        if (StringService.isNotEmpty(prmAplTgt.getGrpcoId())) {
            List<String> grpcoIdList = Arrays.asList(prmAplTgt.getGrpcoId().split(PromotionSeparator.DELIMITER.toString()));

            for (String grpcoId : grpcoIdList) {
                PrmAplTgt pat = new PrmAplTgt();

                pat.setGrpcoId(grpcoId);
                pat.setPrmTgtTpCd(PromotionAplTarget.GRPCO_ID.toString());

                pat.setPrmNo(prmNo);
                pat.setRegtrId(prmAplTgt.getRegtrId());
                pat.setUdterId(prmAplTgt.getUdterId());

                promotionTargetRepository.insertPromotionApplyTarget(sqlSession, pat);
            }
        }

        //제휴업체 ID
        if (StringService.isNotEmpty(prmAplTgt.getAffComId())) {
            List<String> affComIdList = Arrays.asList(prmAplTgt.getAffComId().split(PromotionSeparator.DELIMITER.toString()));

            for (String affComId : affComIdList) {
                PrmAplTgt pat = new PrmAplTgt();

                pat.setAffComId(affComId);
                pat.setPrmTgtTpCd(PromotionAplTarget.AFF_COM_ID.toString());

                pat.setPrmNo(prmNo);
                pat.setRegtrId(prmAplTgt.getRegtrId());
                pat.setUdterId(prmAplTgt.getUdterId());

                promotionTargetRepository.insertPromotionApplyTarget(sqlSession, pat);
            }
        }
    }

    public int deletePromotionApplyTarget(PromotionBoDTO promotionBoDTO) throws Exception {
        int result = promotionTargetRepository.deletePromotionApplyTarget(promotionBoDTO.getPrmAplTgt());
        return result;
    }

    /**
     * 프로모션 적용상품.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param sqlSession [설명]
     * @param promotionBoDTO [설명]
     * @throws Exception the exception
     * @since 2015. 5. 6
     */
    public int insertPromotionApplyGoods(SqlSession sqlSession, PrmAplGod prmAplGod) throws Exception {

        String lang = "KOR";

        PromotionApplyGoodBoResult validResult = promotionTargetRepository.selectValidPrmAplGod(prmAplGod);

        if (validResult.getPrmAplGodCount() > 0) {
            String[] exceptionParam = { CodeUtil.getCode(lang, prmAplGod.getAplGodSectCd()).getCdNm() };
            throw new PromotionAlreadyExistApplyTargetException(exceptionParam);
        }

        if (PromotionAplGoods.GOD.toString().equals(prmAplGod.getAplGodSectCd()) && (StringService.isEmpty(validResult.getGodNo()))) {
            throw new PromotionNotExistGoodException(null);
        }

        int result = promotionTargetRepository.insertPromotionApplyGoods(sqlSession, prmAplGod);
        return result;
    }
    
    /**
     * 온오프라인 쿠폰 적용 상품 ERP 연동하여 insert
     * @param sqlSession
     * @param prm
     * @return
     * @throws Exception
     */
    public int insertOnOffPromotionApplyGoods(SqlSession sqlSession, Prm prm) throws Exception{
    	int rstVal = 0;
    	// ERP 인터페이스 헤더설정
//		AdapterHeader adapterHeader = new AdapterHeader();
//		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
//		adapterHeader.setMallId("DXM");
//		adapterHeader.setLangCd("KOR");
//		adapterHeader.setDvcCd("PC");
//		
//		CombinedOnOffCoupon parameter = new CombinedOnOffCoupon();
//		parameter.setCampaignID(prm.getErpCmpgId());
//		parameter.setR3nameR("ERP");
//		parameter.setCallerId(InterfaceAdapterEnum.RFC_NM_ZD5MK_FGIF_EVENT_CAMP_INFO.toString());
//
//    	CouponOnOffInfoSDO eventOfferInfo = promotionAdapter.getFgifEventCampInfo(parameter, adapterHeader);
//
//    	if(eventOfferInfo != null && "S".equals(eventOfferInfo.getRetCd())){
//    		/** ******************************************
//        	 *  프로모션 적용 상품(상품) 저장 - PRM_APL_GOD
//        	 *  - 적용 상품 구분 : 브랜드(BRND)
//        	 *  ******************************************/
//    		if(eventOfferInfo.getInfEtOfferBrnList() != null && eventOfferInfo.getInfEtOfferBrnList().size() > 0){
//                for(InfEtOfferBrn obj: eventOfferInfo.getInfEtOfferBrnList()){
//                    CouponPromotionDTO dto = new CouponPromotionDTO();
//                    dto.setBrndId(obj.getBrandClass());
//
//                    CouponPromotionResult result = promotionRepository.selectOnOffCpnTargetBrnd(dto);
//
//                    if(result != null && result.getPrmAplGodEx() != null){
//                        PrmAplGod aplGod = new PrmAplGod();
//                        aplGod.setPrmNo(prm.getPrmNo());
//                        aplGod.setAplGodSectCd(PromotionAplGoods.BRND.toString());
//                        aplGod.setBrndId(result.getPrmAplGodEx().getBrndId());
//
//                        if("X".equals(obj.getEctX())){
//                            aplGod.setGodAplYn(PromotionEnum.NO.toString());
//                        }else{
//                            aplGod.setGodAplYn(PromotionEnum.YES.toString());
//                        }
//
//                        aplGod.setRegtrId(prm.getRegtrId());
//                        aplGod.setUdterId(prm.getRegtrId());
//                        rstVal += insertPromotionApplyGoods(sqlSession, aplGod);
//                    }
//                }
//            }
//			/** ******************************************
//        	 *  프로모션 적용 상품(상품) 저장 - PRM_APL_GOD
//        	 *  - 적용 상품 구분 : 상품(GOD)
//        	 *  ******************************************/
//			if(eventOfferInfo.getInfEtOfferMatList() != null && eventOfferInfo.getInfEtOfferMatList().size() > 0){
//                for(InfEtOfferMat obj: eventOfferInfo.getInfEtOfferMatList()){
//                    CouponPromotionDTO erpGod = new CouponPromotionDTO();
//                    erpGod.setErpGodNo(obj.getMatnr());
//
//                    CouponPromotionResult result = promotionRepository.selectOnOffCpnTargetGod(erpGod);
//
//                    if(result != null && result.getPrmAplGodEx() != null){
//                        PrmAplGod aplGod = new PrmAplGod();
//                        aplGod.setPrmNo(prm.getPrmNo());
//                        aplGod.setAplGodSectCd(PromotionAplGoods.GOD.toString());
//                        aplGod.setGodNo(result.getPrmAplGodEx().getGodNo());
//
//                        if("X".equals(obj.getEctX())){
//                            aplGod.setGodAplYn(PromotionEnum.NO.toString());
//                        }else{
//                            aplGod.setGodAplYn(PromotionEnum.YES.toString());
//                        }
//
//                        aplGod.setRegtrId(prm.getRegtrId());
//                        aplGod.setUdterId(prm.getRegtrId());
//
//                        rstVal += insertPromotionApplyGoods(sqlSession, aplGod);
//                    }
//                }
//    	    }
//    	}

    	/** ******************************************
    	 *  프로모션 적용 상품(브랜드,상품) 지정 데이터가 없을 때 - PRM_APL_GOD
    	 *  - 적용 상품 구분 : 전체 'ALL'
    	 *  ******************************************/
		if(rstVal == 0){
			PrmAplGod aplGod = new PrmAplGod();
			aplGod.setPrmNo(prm.getPrmNo());
			aplGod.setAplGodSectCd(PromotionAplGoods.ALL.toString());
			aplGod.setGodAplYn(PromotionEnum.YES.toString());
			aplGod.setRegtrId(prm.getRegtrId());
			aplGod.setUdterId(prm.getRegtrId());
			
			rstVal += insertPromotionApplyGoods(sqlSession, aplGod);	
		}
    	
    	return rstVal;
    }

    public void insertOnOffPromotionApplyGoods(SqlSession sqlSession, Prm prm, OnOffCouponIssueResultDataSDO sdo) throws Exception{
    	if(sdo.getAplGodYn() != null && "Y".equals(sdo.getAplGodYn())) {
    		if(sdo.getAplGodList() != null && !sdo.getAplGodList().isEmpty()) {
	    		for(OnOffCouponIssueApplyGoodsSDO applyGoodsSDO : sdo.getAplGodList()) {

					/** ******************************************
		        	 *  프로모션 적용 상품(상품) 저장 - PRM_APL_GOD
		        	 *  - 적용 상품 구분 : 상품(GOD)
		        	 *  ******************************************/
					if(applyGoodsSDO.getPartcode() != null && !"".equals(applyGoodsSDO.getPartcode())) {
	                    CouponPromotionDTO erpGod = new CouponPromotionDTO();
	                    erpGod.setDsgnGrpNo(applyGoodsSDO.getPartcode());
	
	                    List<CouponPromotionResult> result = promotionRepository.selectOnOffCpnTargetGod(erpGod);
	
	                    if(result != null && !result.isEmpty()){
	                    	for(CouponPromotionResult r : result) {
		                        PrmAplGod aplGod = new PrmAplGod();
		                        aplGod.setPrmNo(prm.getPrmNo());
		                        aplGod.setAplGodSectCd(PromotionAplGoods.GOD.toString());
		                        aplGod.setGodNo(r.getPrmAplGodEx().getGodNo());
		                        aplGod.setGodAplYn(PromotionEnum.YES.toString());
		                        aplGod.setRegtrId(prm.getRegtrId());
		                        aplGod.setUdterId(prm.getRegtrId());
		                        try {
		                        	insertPromotionApplyGoods(sqlSession, aplGod);
		                        }
		                        catch(PromotionAlreadyExistApplyTargetException te) {
		                        	// 이미 존재하는 상품번호는 insert하지 않고 넘어가도록 처리.
		                            StringWriter error = new StringWriter();
		                            te.printStackTrace(new PrintWriter(error));
		                            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
		                        }
	                    	}
	                    }
		    	    }
				}
    		}
    	}
    	/** ******************************************
    	 *  프로모션 적용 상품(브랜드,상품) 지정 데이터가 없을 때 - PRM_APL_GOD
    	 *  - 적용 상품 구분 : 전체 'ALL'
    	 *  ******************************************/
    	else {
			PrmAplGod aplGod = new PrmAplGod();
			aplGod.setPrmNo(prm.getPrmNo());
			aplGod.setAplGodSectCd(PromotionAplGoods.ALL.toString());
			aplGod.setGodAplYn(PromotionEnum.YES.toString());
			aplGod.setRegtrId(prm.getRegtrId());
			aplGod.setUdterId(prm.getRegtrId());
			
			this.insertPromotionApplyGoods(sqlSession, aplGod);	
		}
    	
    }
    
    public int deletePromotionTargetGoods(PrmAplGod prmAplGod) throws Exception {
        int result = promotionTargetRepository.deletePromotionTargetGoods(prmAplGod);
        return result;
    }

    public int insertPrmCrsDcAplGod(PrmCrsDcAplGod prmCrsDcAplGod) throws Exception {
        int cnt = promotionTargetRepository.insertPrmCrsDcAplGod(prmCrsDcAplGod);
        return cnt;
    }

    public int deletePrmCrsDcAplGod(PrmCrsDcAplGod prmCrsDcAplGod) throws Exception {
        int cnt = promotionTargetRepository.deletePrmCrsDcAplGod(prmCrsDcAplGod);
        return cnt;
    }

    public Page<PromotionApplyGoodBoResult> selectPromotionApplyGoodList(PromotionApplyGoodBoDTO searchDTO, PageParam pageParam)
            throws Exception {
        Page<PromotionApplyGoodBoResult> resultList = promotionTargetRepository.selectPromotionApplyGoodList(searchDTO, pageParam);
        return resultList;
    }

    public Page<PromotionApplyGoodBoResult> selectPrmCrsDcAplGodList(PromotionApplyGoodBoDTO searchDTO, PageParam pageParam)
            throws Exception {
        Page<PromotionApplyGoodBoResult> resultList = promotionTargetRepository.selectPrmCrsDcAplGodList(searchDTO, pageParam);
        return resultList;
    }

    public Page<PromotionBoResult> selectPrmAplTgtGrpcdList(PromotionBoDTO searchDTO, PageParam pageParam) throws Exception {
        Page<PromotionBoResult> resultList = promotionTargetRepository.selectPrmAplTgtGrpcdList(searchDTO, pageParam);
        return resultList;
    }
    
    public PromotionApplyGoodBoResult selectValidPrmAplGod(PrmAplGod prmAplGod) throws Exception {
        PromotionApplyGoodBoResult validResult = promotionTargetRepository.selectValidPrmAplGod(prmAplGod);
        return validResult;
    };
    
    public int selectValidPrmAplGodTargetAll(PrmAplGod prmAplGod) throws Exception {
        int result = promotionTargetRepository.selectValidPrmAplGodTargetAll(prmAplGod);
        return result;
    }
    
    /**
     * 쿠폰 적용대상브랜드 - 상위브랜드 코드의 하위브랜드 코드 일괄등록
     */
    public int insertPrmAplGodByBrandCode(SqlSession sqlSession, PrmAplGod prmAplGod) throws Exception {
        int result = promotionTargetRepository.insertPrmAplGodByBrandCode(sqlSession, prmAplGod);
        return result;
    }

    /**
     * 적용 대상 상품 검색
     * 
     * @param searchDTO
     * @return
     * @throws Exception
     */
    public int selectPrmAplGodInfoForSearch(PromotionApplyGoodBoDTO searchDTO) throws Exception {
        return promotionTargetRepository.selectPrmAplGodInfoForSearch(searchDTO);
    }
    
    
    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
