/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 7. 6       
 */
package com.plgrim.ncp.biz.display.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtBrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtGod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImg;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImgLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtrLang;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResve;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.repository.dsp.DspPromtBrndRepository;
import com.plgrim.ncp.base.repository.dsp.DspPromtDspTgtRepository;
import com.plgrim.ncp.base.repository.dsp.DspPromtGodRepository;
import com.plgrim.ncp.base.repository.dsp.DspPromtImgLangRepository;
import com.plgrim.ncp.base.repository.dsp.DspPromtImgRepository;
import com.plgrim.ncp.base.repository.dsp.DspPromtLangRepository;
import com.plgrim.ncp.base.repository.dsp.DspPromtSprtrRepository;
import com.plgrim.ncp.biz.display.data.DspPlanDetailResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspPlanSearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtBoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtDspTgtBoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtGodBoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtGodExt;
import com.plgrim.ncp.biz.display.data.DspPromtSprtrBoDTO;
import com.plgrim.ncp.biz.display.exception.NotDeletedPromtGodException;
import com.plgrim.ncp.biz.display.repository.DisplayCategoryRepository;
import com.plgrim.ncp.biz.display.repository.DisplayPlanRepository;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult2;
import com.plgrim.ncp.biz.display.result.DspPlanGodFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanSprtrFoResult;
import com.plgrim.ncp.biz.display.result.GodIconFoResult;
import com.plgrim.ncp.biz.display.result.GodTagResveFoResult;
import com.plgrim.ncp.commons.data.DspRevDTO;
import com.plgrim.ncp.commons.repository.DisplayRevRepository;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2015. 5. 28
 */
@Service
public class DisplayPlanService extends AbstractService{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayPlanRepository displayPlanRepository;
	
	@Autowired
	DspPromtBrndRepository dspPromtBrndRepository;
	
	@Autowired
	DspPromtDspTgtRepository dspPromtDspTgtRepository;
	
	@Autowired
	DspPromtSprtrRepository dspPromtSprtrRepository;
	
	@Autowired
	DspPromtGodRepository dspPromtGodRepository; 
	
	@Autowired
	DspPromtLangRepository dspPromtLangRepository;
	
	@Autowired
	DisplayCategoryRepository displayRepository;
	
	@Autowired
	DspPromtImgRepository dspPromtImgRepository;
	
	@Autowired
	DspPromtImgLangRepository dspPromtImgLangRepository;

	@Autowired
    DisplayRevRepository displayRevRepository;

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
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectPlanCategoryList(DspPlanSearchFoDTO searchDTO,SystemPK pk) {
	    // TODO Auto-generated method stub
		
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode("ctgry");
		
		List<DspPlanFoResult> lists = displayPlanRepository.selectPlanCategoryList(searchDTO);
		
		DateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date nowDate = new Date();
		
		for (DspPlanFoResult dspPlanFoResult : lists) {
	        
			dspPlanFoResult.setBeginDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspBegDt()));
			dspPlanFoResult.setEndDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspEndDt()));
			
			if(!("KOR".equals(pk.getLang().toString()))){
				
				String promtNm = dspPlanFoResult.getDspPromtLang().getPromtNm();
				
				if(promtNm != null && promtNm != ""){
					dspPlanFoResult.getDspPromt().setPromtNm(promtNm);
				}
			}
        }

		
	    return lists;
    }
	
	/**
	 * 카테고리별 기획전 카운트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectPlanCategoryCnt(DspPlanSearchFoDTO searchDTO,SystemPK pk) {
	    // TODO Auto-generated method stub
		
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		
	    return displayPlanRepository.selectPlanCategoryCnt(searchDTO);
    }
	
	/**
	 * 기획전 브랜드 리스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectPlanBrandList(DspPlanSearchFoDTO searchDTO,SystemPK pk) throws Exception{
	    // TODO Auto-generated method stub
		
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode("brnd");
		
	    return displayPlanRepository.selectPlanBrandList(searchDTO);
    }
	
	/**
	 * 기획전 리스트 총갯수
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public long selectPlanMainTotalCount(DspPlanSearchFoDTO searchDTO,
            SystemPK pk) {
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		
	    return displayPlanRepository.selectPlanMainTotalCount(searchDTO);
    }
	
	/**
	 * 기획전 브랜드별 카운트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectPlanBrandyCnt(DspPlanSearchFoDTO searchDTO,SystemPK pk) {
	    // TODO Auto-generated method stub
		
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		
		return displayPlanRepository.selectPlanBrandyCnt(searchDTO);
    }
	
	public long selectPlanBrandyCount(DspPlanSearchFoDTO searchDTO,
            SystemPK pk) {
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());

	    return displayPlanRepository.selectPlanBrandyCount(searchDTO);
    }

	/**
	 * 기획전 상세 및 템플릿 코너정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectPlanCorner(DspPlanSearchFoDTO searchDTO,Integer promtSn, SystemPK pk) throws Exception {
        /**
         * 개정순번 셋팅
         * - revSn이 넘어온 경우(BO 미리보기) : 넘어온 revSn으로 조회
         * - revSn이 null인 경우 : featureUtil과 procedure 호출하여 revSn 조회
         */
        if(searchDTO.getRevSn() == null || !displayRevRepository.isPsbPreviewIP()){
            DspRevDTO dspRevDTO = new DspRevDTO();
            dspRevDTO.setTmplatScKey(String.valueOf(searchDTO.getPromtSn()));

            DspRevDTO revSnResult = displayRevRepository.getRevSnResult(dspRevDTO);
            searchDTO.setRevSn(revSnResult.getDspRevCpst().getRevSn());
            searchDTO.setRevCpstTurn(revSnResult.getDspRevCpst().getRevCpstTurn());
            searchDTO.setAbTestSn(revSnResult.getAbTestSn());
        }
		
		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall()); 
//		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		return displayPlanRepository.selectPlanCorner(searchDTO);
    }
	
	/**
	 * 기획전 상세 및 템플릿 코너정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public DspPlanFoResult2 selectPlanCornerJu(DspPlanSearchFoDTO searchDTO, Integer promtSn, SystemPK pk) {
		
		searchDTO.setPromtSn((long)promtSn);
		DspPlanFoResult2 result = displayPlanRepository.selectPlanCornerJu(pk, searchDTO);
		
		
		String lang = pk.getLang();
		if(!lang.equals("KOR")){
			DspPromt dspPromt = result.getDspPromt();
			DspPromtLang dspPromtLang = result.getDspPromtLang();			
			changeDspPromtLnag(dspPromt, dspPromtLang);
		}
		
		return result;
    }
	
	/**
	 *기획전 상세정보 미리보기
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectPrevPlanCorner(DspPlanSearchFoDTO searchDTO,Integer promtSn, SystemPK pk) {
		
		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall()); 
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		return displayPlanRepository.selectPrevPlanCorner(searchDTO);
    }
	
	/**
	 * 기획전 구분자 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanSprtrFoResult> selectPlanSprtr(DspPlanSearchFoDTO searchDTO) {
		
		return displayPlanRepository.selectPlanSprtr(searchDTO);
    }
	
	/**
	 * 기획전 구분자 조회 미리보기
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanSprtrFoResult> selectPrevPlanSprtr(DspPlanSearchFoDTO searchDTO) {
		
		return displayPlanRepository.selectPrevPlanSprtr(searchDTO);
    }
	
	/**
	 * 구분자별 상품 리스트 삽입
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param sortValue [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanSprtrFoResult> selectPlanGodList( SystemPK pk,
            DspPlanSearchFoDTO searchDTO) throws Exception{
	
		List<DspPlanSprtrFoResult> sprtrList = displayPlanRepository.selectPlanSprtr(searchDTO);
		
		List<DspPlanGodFoResult> godList = displayPlanRepository.selectPlanGodList(searchDTO);
		
		List<String> godNoList = new ArrayList<String>();
		
		for (DspPlanGodFoResult dspPlanGodFoResult : godList) {
			
			// GRP_SESON_CD 체크 해당년도 제품의 SESON GRP 코드만 사용
//			String mDate = dspPlanGodFoResult.getMnfcturDate();
//			if(mDate != null){
//				String dd = mDate.substring(3,4);
//				String erpNo = dspPlanGodFoResult.getGod().getErpGodNo();
//				String sNo = erpNo.substring(2,3);
//				if(!dd.equals(sNo)){
//					dspPlanGodFoResult.getGod().setSesonGrpCd(null);
//				}
//			}

			godNoList.add(dspPlanGodFoResult.getGod().getGodNo());
		}
		List<GodTagResveFoResult> godTagResveList = null;
		List<GodIconFoResult> godIconList = null;
		if(godNoList != null && godNoList.size() > 0){
			godTagResveList = displayRepository.selectGodResve2(pk, godNoList);
			godIconList = displayRepository.selectGodIconList2(godNoList);
		}
		
		
		for (DspPlanSprtrFoResult dspPlanSprtrFoResult : sprtrList) {
			
			ArrayList<DspPlanGodFoResult> gList = new ArrayList<DspPlanGodFoResult>();
			
			for (DspPlanGodFoResult dspPlanGodFoResult : godList) {
				
				if(dspPlanSprtrFoResult.getDspPromt().getPromtSn().equals(
					dspPlanGodFoResult.getDspPromt().getPromtSn())){
					String sTurn = dspPlanSprtrFoResult.getDspPromtSprtr().getSprtrTurn().toString();
					String gTurn = dspPlanGodFoResult.getDspPromtSprtr().getSprtrTurn().toString();
					String lang = pk.getLang();
					if(sTurn.equals(gTurn)){
						if(godTagResveList != null){
							for(GodTagResveFoResult tag:godTagResveList){
								String godNo = tag.getGodNo();
								if(dspPlanGodFoResult.getGod().getGodNo().equals(godNo)){
									GodTagResve godTagResve = tag.getGodTagResve();
									GodLangbyTagResve godLangbyTagResve = tag.getGodLangbyTagResve();									
									if(!lang.equals("KOR")){
										changeDspCtgryCnncGodLang(dspPlanGodFoResult.getGod(), godTagResve, godLangbyTagResve);
									}
									else if(godTagResve != null && godTagResve.getTagNm() != null){
										dspPlanGodFoResult.getGod().setTagNm(godTagResve.getTagNm());
									}
									break;
								}
							}
							
							for(GodIconFoResult godIcon:godIconList){
								String godNo = godIcon.getGodIconCnnc().getGodNo();
								if(dspPlanGodFoResult.getGod().getGodNo().equals(godNo)){
									List<GodIconFoResult> iconList = dspPlanGodFoResult.getGodIconList();
									if(iconList == null){
										iconList = new ArrayList<GodIconFoResult>();
										iconList.add(godIcon);
										dspPlanGodFoResult.setGodIconList(iconList);
									}else{
										iconList.add(godIcon);
									}
								}
							}
						}
						
						if(!lang.equals("KOR")){
							changeDspCtgryCnncGodLang2(dspPlanGodFoResult.getGod(), dspPlanGodFoResult.getGodLangbyGodNm());
						}
						
						gList.add(dspPlanGodFoResult);
					}
				}

	        }
			dspPlanSprtrFoResult.setGodList(gList);
        }
		

		
		return sprtrList;
    }
	
	/**
	 * 기획전 상세 상품 리스트 조회 미리보기
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param sortValue [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanSprtrFoResult> selectPrevPlanGodList(
            DspPlanSearchFoDTO searchDTO) {
	
		List<DspPlanSprtrFoResult> sprtrList = displayPlanRepository.selectPrevPlanSprtr(searchDTO);
		
		List<DspPlanGodFoResult> godList = displayPlanRepository.selectPrevPlanGodList(searchDTO);
		
		for (DspPlanSprtrFoResult dspPlanSprtrFoResult : sprtrList) {
			
			ArrayList<DspPlanGodFoResult> gList = new ArrayList<DspPlanGodFoResult>();
			
			for (DspPlanGodFoResult dspPlanGodFoResult : godList) {
				if(dspPlanSprtrFoResult.getDspPromt().getPromtSn().equals(
					dspPlanGodFoResult.getDspPromt().getPromtSn())){
					String sTurn = dspPlanSprtrFoResult.getDspPromtSprtr().getSprtrTurn().toString();
					String gTurn = dspPlanGodFoResult.getDspPromtSprtr().getSprtrTurn().toString();
					if(sTurn.equals(gTurn)){
						searchDTO.setGodNo(dspPlanGodFoResult.getDspPromtGod().getGodNo().toString());
						List<GodImg> godImgList = displayPlanRepository.selectGodImgList(searchDTO);
						dspPlanGodFoResult.setGodImgList(godImgList);
						gList.add(dspPlanGodFoResult);
					}
				}
				
	        }
			dspPlanSprtrFoResult.setGodList(gList);
        }
		
		
		return sprtrList;
    }
	
	
	/**
	 * 기획전 모바일 상품리스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param sortValue [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<List<DspPlanGodFoResult>> selectPlanMbGodList(DspPlanSearchFoDTO searchDTO) throws Exception {
		int listExCntCd = 2;
		int pageRow = 1000;

		if ("3PCE".equals(searchDTO.getListExCnt())) {
			listExCntCd = 3;
			pageRow = 1800;
		}

		PageParam pageParam = getPageService().buildPageParam(null, pageRow);

		if (searchDTO.getPageNo() != null && searchDTO.getPageNo() != "") {
			pageParam = getPageService().buildPageParam(searchDTO.getPageNo(), pageRow);
		}

		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		List<DspPlanGodFoResult> godList = displayPlanRepository.selectPlanGodPageList(searchDTO);
		ArrayList<List<DspPlanGodFoResult>> godSetList = new ArrayList<List<DspPlanGodFoResult>>();
		ArrayList<DspPlanGodFoResult> inList = new ArrayList<DspPlanGodFoResult>();
		int cnt = 1;
		int total = godList.size();

		for (DspPlanGodFoResult dspPlanGodFoResult : godList) {
			//searchDTO.setGodNo(dspPlanGodFoResult.getDspPromtGod().getGodNo().toString());
			//List<GodImg> godImgList = displayPlanRepository.selectGodImgList(searchDTO);
			//dspPlanGodFoResult.setGodImgList(godImgList);

			inList.add(dspPlanGodFoResult);

			if (cnt % listExCntCd == 0) {
				godSetList.add(inList);
				inList = new ArrayList<DspPlanGodFoResult>();
			}
			else if (cnt % listExCntCd != 0 && cnt == total) {
				godSetList.add(inList);
			}
			cnt++;
		}
		
		return godSetList;
    }
	
	/**
	 * 기획전 모바일 구분자별 상품 리스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param sortValue [설명]
	 * @param pk [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanSprtrFoResult> selectPlanMbSprtrGodList(DspPlanSearchFoDTO searchDTO) throws Exception{
		int listExCntCd = 2;
		int pageRow = 1000;

		if ("3PCE".equals(searchDTO.getListExCnt())) {
			listExCntCd = 3;
			pageRow = 1800;
		}

		PageParam pageParam = getPageService().buildPageParam(null, pageRow);

		if (searchDTO.getPageNo() != null && searchDTO.getPageNo() != "") {
			pageParam = getPageService().buildPageParam(searchDTO.getPageNo(), pageRow);
		}

		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		List<DspPlanSprtrFoResult> sprtrList = displayPlanRepository.selectPlanSprtr(searchDTO);

		for (DspPlanSprtrFoResult dspPlanSprtrFoResult : sprtrList) {
			//ArrayList<DspPlanGodFoResult> gList = new ArrayList<DspPlanGodFoResult>();
			ArrayList<DspPlanGodFoResult> gList1 = new ArrayList<DspPlanGodFoResult>();
			ArrayList<List<DspPlanGodFoResult>> godSetList = new ArrayList<List<DspPlanGodFoResult>>();
			searchDTO.setSprtr(dspPlanSprtrFoResult.getDspPromtSprtr().getSprtrTurn().toString());
			List<DspPlanGodFoResult> godList = displayPlanRepository.selectPlanGodPageList(searchDTO);
			long totalRow = displayPlanRepository.selectPlanGodPageCount(searchDTO);
			int cnt = 1;

			// 상품그룹별 상품 리스트 생성
			/*for (DspPlanGodFoResult dspPlanGodFoResult : godList) {
				searchDTO.setGodNo(dspPlanGodFoResult.getDspPromtGod().getGodNo().toString());
				List<GodImg> godImgList = displayPlanRepository.selectGodImgList(searchDTO);
				dspPlanGodFoResult.setGodImgList(godImgList);
				gList.add(dspPlanGodFoResult);
			}*/
			//int total = gList.size();
			int total = godList != null ? godList.size() : 0;
			// 모바일 2단 또는 3단 상품 리스트 묶음
			//for (DspPlanGodFoResult dspPlanGodFoResult : gList) {
			for (DspPlanGodFoResult dspPlanGodFoResult : godList) {
				gList1.add(dspPlanGodFoResult);

				if (cnt % listExCntCd == 0) {
					godSetList.add(gList1);
					gList1 = new ArrayList<DspPlanGodFoResult>();
				}
				else if (cnt % listExCntCd != 0 && cnt == total) {
					godSetList.add(gList1);
				}

				cnt++;
			}
			dspPlanSprtrFoResult.setGodSetList(godSetList);
			dspPlanSprtrFoResult.setTotalRow(totalRow);
		}
		
		return sprtrList;
    }
	
	/**
	 * 기획전 모바일 상품 리스트 카운트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public long selectPlanGodPageCount(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		return displayPlanRepository.selectPlanGodPageCount(searchDTO);
    }
	
	
//	public List<DspPlanSprtrFoResult> selectPlanGodList(Integer promtSn,
//            String sortValue, SystemPK pk) {
//	    // TODO Auto-generated method stub
//		DspPlanSearchFoDTO searchDTO = new DspPlanSearchFoDTO();
//		
//		searchDTO.setPromtSn((long)promtSn);
//		searchDTO.setSortValue(sortValue);
//		searchDTO.setMallId(pk.getMall());
//		searchDTO.setDevice(pk.getDevice());
//		searchDTO.setLang(pk.getLang());
//		
//		List<DspPlanSprtrFoResult> sprtrList = displayPlanRepository.selectPlanSprtr(searchDTO);
//		
//		List<DspPlanGodFoResult> godList = displayPlanRepository.selectPlanGodList(searchDTO);
//		
//		for (DspPlanSprtrFoResult dspPlanSprtrFoResult : sprtrList) {
//			
//			ArrayList<DspPlanGodFoResult> gList = new ArrayList<DspPlanGodFoResult>();
//
//			for (DspPlanGodFoResult dspPlanGodFoResult : godList) {
//				if(dspPlanSprtrFoResult.getDspPromt().getPromtSn().equals(
//					dspPlanGodFoResult.getDspPromt().getPromtSn())){
//					String sTurn = dspPlanSprtrFoResult.getDspPromtSprtr().getSprtrTurn().toString();
//					String gTurn = dspPlanGodFoResult.getDspPromtSprtr().getSprtrTurn().toString();
//					if(sTurn.equals(gTurn)){
//						gList.add(dspPlanGodFoResult);
//					}
//				}
//	        }
//			dspPlanSprtrFoResult.setGodList(gList);
//        }
//		
//		return sprtrList;
//    }
	
	
	
	/**
	 * 기획전 브랜드 selectbox
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectbrndList(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
	    return displayPlanRepository.selectbrndList(searchDTO);
    }
	
	/**
	 * 기획전 카테고리 selectbox
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectCtgryList(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		return displayPlanRepository.selectCtgryList(searchDTO);
    }

	/**
	 * 기획전 기획전리스트 selectbox
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public List<DspPlanFoResult> selectPlanList(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
	    return displayPlanRepository.selectPlanList(searchDTO);
    }

	
	
	
	
	/**
	 * 기획전 목록 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 22
	 */
	public void updatePlanList (List<DspPromtBoDTO> list) throws Exception {
		DspPromt dspPromt = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		for(DspPromtBoDTO dspPromtBoDTO: list) {
			dspPromt = dspPromtBoDTO.getDspPromt();
			dspPromt.setRegtrId(loginId);
			dspPromt.setUdterId(loginId);
			displayPlanRepository.updatePromtForGrid(dspPromt);
		}
		
	}
	
	/**
	 * 기획전 승인상태 update.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 26
	 */
	public void updatePromtAprvStat (List<DspPromtBoDTO> list) throws Exception {
		DspPromt dspPromt = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		for(DspPromtBoDTO dspPromtBoDTO: list) {
			dspPromt = dspPromtBoDTO.getDspPromt();
			dspPromt.setRegtrId(loginId);
			dspPromt.setUdterId(loginId);
			displayPlanRepository.updatePromtAprvStat(dspPromt);
		}
		
	}
	
	/**
	 * 기획전 번호 생성
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public long getPromtSn() throws Exception {
		return displayPlanRepository.getPromtSn();
	}
	
	
	/**
	 * 기본정보 등록-dsp_promt
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromt [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void insertDspPromt(DspPromt dspPromt) throws Exception {
		displayPlanRepository.insertDspPromt(dspPromt);
	}
	
	/**
	 * 기획전 기본정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromt [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void updateDspPromt(DspPromt dspPromt) throws Exception {
		displayPlanRepository.updatePlanInfo(dspPromt);
	}
	

	/**
	 * 기획전명 언어 등록 (merge)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void saveDspPromtLangInfo(DspPromtLang dspPromtLang) throws Exception {
		displayPlanRepository.saveDspPromtLangInfo(dspPromtLang);
	}
	
	/**
	 * 기획전명 언어 저장
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public void updateDspPromtLangNm(DspPromtLang dspPromtLang) throws Exception {
		int checkCnt = displayPlanRepository.selectDspPromtLangCnt(dspPromtLang);
		if(checkCnt == 0) {
			dspPromtLangRepository.insertDspPromtLang(dspPromtLang);
		}
		else {
			displayPlanRepository.updateDspPromtLangNm(dspPromtLang);
		}
	}
	
	/**
	 * 기획전 PC Image 언어 저장
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public void updateDspPromtLangPcImg(DspPromtLang dspPromtLang) throws Exception {
		int checkCnt = displayPlanRepository.selectDspPromtLangCnt(dspPromtLang);
		if(checkCnt == 0) {
			dspPromtLangRepository.insertDspPromtLang(dspPromtLang);
		}
		else {
			displayPlanRepository.updateDspPromtLangPcImg(dspPromtLang);
		}
	}
	
	/**
	 * 기획전 Mobile Image 언어 저장
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public void updateDspPromtLangMobileImg(DspPromtLang dspPromtLang) throws Exception {
		int checkCnt = displayPlanRepository.selectDspPromtLangCnt(dspPromtLang);
		if(checkCnt == 0) {
			dspPromtLangRepository.insertDspPromtLang(dspPromtLang);
		}
		else {
			displayPlanRepository.updateDspPromtLangMobileImg(dspPromtLang);
		}
	}
	
	/**
	 * 기획전 상품상세 PC Image 언어 저장
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public void updateDspPromtLangPcGodImg(DspPromtLang dspPromtLang) throws Exception {
		int checkCnt = displayPlanRepository.selectDspPromtLangCnt(dspPromtLang);
		if(checkCnt == 0) {
			dspPromtLangRepository.insertDspPromtLang(dspPromtLang);
		}
		else {
			displayPlanRepository.updateDspPromtLangPcGodImg(dspPromtLang);
		}
	}
	
	/**
	 * 기획전 상품상세 Mobile Image 언어 저장
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public void updateDspPromtLangMobileGodImg(DspPromtLang dspPromtLang) throws Exception {
		int checkCnt = displayPlanRepository.selectDspPromtLangCnt(dspPromtLang);
		if(checkCnt == 0) {
			dspPromtLangRepository.insertDspPromtLang(dspPromtLang);
		}
		else {
			displayPlanRepository.updateDspPromtLangMobileGodImg(dspPromtLang);
		}
	}
	
	//4. 기획전 브랜드 등록 (delete -> insert)
	/**
	 * 기획전 브랜드 삭제 (특정 기획전의 브랜드삭제)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void deleteDspPromtBrndInfo(DspPromtBrnd dspPromtBrnd) throws Exception {
		displayPlanRepository.deleteDspPromtBrndInfo(dspPromtBrnd);
	}
	
	/**
	 * 기획전 브랜드 목록 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void insertDspPromtBrnd(List<String> arMdBrndCd, long promtSn) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		
		for(String mdBrndCd: arMdBrndCd) {
			DspPromtBrnd dspPromtBrnd = new DspPromtBrnd();
			dspPromtBrnd.setPromtSn(promtSn);
			dspPromtBrnd.setMdBrndCd(mdBrndCd);
			dspPromtBrnd.setRegtrId(loginId);
			dspPromtBrnd.setUdterId(loginId);
			
			dspPromtBrndRepository.insertDspPromtBrnd(dspPromtBrnd);
		}
	}
	
	//5. 기획전 전시대상 등록 (delete -> insert)
	/**
	 * 특정 기획전의 전시대상 삭제
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtDspTgt [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void deleteDspPromtDspTgtInfo(DspPromtDspTgt dspPromtDspTgt) throws Exception {
		displayPlanRepository.deleteDspPromtDspTgtInfo(dspPromtDspTgt);
	}
	
	/**
	 * 기획전 리뷰노출 여부  N
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param tgtCdList [설명]
	 * @param tpCd [설명]
	 * @param promtSn [설명]
	 * @param grpCoList [설명]
	 * @throws Exception the exception
	 * @since 2016. 6. 15
	 */
	//updateGodEvlExpserN
	public void updateGodEvlExpsrN(List<String> arGodEvlExpsrYn, String tpCd, long promtSn, List<DspPromtDspTgtBoDTO> grpCoList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		DspPromtDspTgt dspPromtDspTgt = new DspPromtDspTgt();
		dspPromtDspTgt.setPromtSn(promtSn);
		dspPromtDspTgt.setRegtrId(loginId);
		dspPromtDspTgt.setUdterId(loginId);
		//dspPromtDspTgt.setGodEvlExpsr((ArrayList<String>) arGodEvlExpsrYn);

		//dspPromtDspTgtRepository.updateGodEvlExpsrN(dspPromtDspTgt);
	}

	/**
	 * 기획전 리뷰노출 여부  Y
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param tgtCdList [설명]
	 * @param tpCd [설명]
	 * @param promtSn [설명]
	 * @param grpCoList [설명]
	 * @throws Exception the exception
	 * @since 2016. 6. 15
	 */
	public void updateGodEvlExpsrY(List<String> arGodEvlExpsrYn, String tpCd, long promtSn, List<DspPromtDspTgtBoDTO> grpCoList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		DspPromtDspTgt dspPromtDspTgt = new DspPromtDspTgt();
		dspPromtDspTgt.setPromtSn(promtSn);
		dspPromtDspTgt.setRegtrId(loginId);
		dspPromtDspTgt.setUdterId(loginId);
		//dspPromtDspTgt.setGodEvlExpsr((ArrayList<String>) arGodEvlExpsrYn);

		//dspPromtDspTgtRepository.updateGodEvlExpsrY(dspPromtDspTgt);
	}
	/**
	 * 기획전 전시대상 목록 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param tgtCdList [설명]
	 * @param tpCd [설명]
	 * @param promtSn [설명]
	 * @param grpCoList [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void insertDspPromtDspTgt(List<String> tgtCdList, String tpCd, long promtSn, List<DspPromtDspTgtBoDTO> grpCoList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		
		for(String tgtCd: tgtCdList) {
			DspPromtDspTgt dspPromtDspTgt = new DspPromtDspTgt();
			dspPromtDspTgt.setPromtSn(promtSn);
			dspPromtDspTgt.setRegtrId(loginId);
			dspPromtDspTgt.setUdterId(loginId);
			
			dspPromtDspTgt.setDspTgtTpCd(tpCd);
			
			// 몰 ID : MALL_ID
			// 언어 코드 : LANG_CD
			// 디바이스 코드 : DVC_CD
			// 전시 회원 유형 : DSP_MBR_TP
			// 전시 회원 속성 : DSP_MBR_ATRB
			if(StringUtils.isEmpty(dspPromtDspTgt.getGodEvlExpsrYn())) {
				dspPromtDspTgt.setGodEvlExpsrYn("N");
			}
			if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.MALL_ID.toString())) {
				dspPromtDspTgt.setMallId(tgtCd);
				
				Integer dspTgtTurn = displayPlanRepository.getDspPromtDspTgtTurn(dspPromtDspTgt);
				dspPromtDspTgt.setDspTgtTurn(dspTgtTurn);
				
				dspPromtDspTgtRepository.insertDspPromtDspTgt(dspPromtDspTgt);
			}
			else if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.LANG.toString())) {
				dspPromtDspTgt.setLangCd(tgtCd);
				
				Integer dspTgtTurn = displayPlanRepository.getDspPromtDspTgtTurn(dspPromtDspTgt);
				dspPromtDspTgt.setDspTgtTurn(dspTgtTurn);
				dspPromtDspTgtRepository.insertDspPromtDspTgt(dspPromtDspTgt);
			}
			else if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.DVC.toString())) {
				dspPromtDspTgt.setDvcCd(tgtCd);
				
				Integer dspTgtTurn = displayPlanRepository.getDspPromtDspTgtTurn(dspPromtDspTgt);
				dspPromtDspTgt.setDspTgtTurn(dspTgtTurn);
				dspPromtDspTgtRepository.insertDspPromtDspTgt(dspPromtDspTgt);
			}
			else if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString())) {
				dspPromtDspTgt.setTgtMbrTpCd(tgtCd);
				
				Integer dspTgtTurn = displayPlanRepository.getDspPromtDspTgtTurn(dspPromtDspTgt);
				dspPromtDspTgt.setDspTgtTurn(dspTgtTurn);
				dspPromtDspTgtRepository.insertDspPromtDspTgt(dspPromtDspTgt);
			}
			else if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString())) {
				dspPromtDspTgt.setTgtMbrAtrbCd(tgtCd);
				
				if(tgtCd.equals("GRPCO_IND")) {
					for(DspPromtDspTgtBoDTO tgtDTO: grpCoList) {
						DspPromtDspTgt tgt = tgtDTO.getDspPromtDspTgt();
						//String cud = tgtDTO.getGCudTag();
						//if(StringService.isNotEmpty(cud)&&!"D".equals(cud)) {
							dspPromtDspTgt.setGrpcoId(tgt.getGrpcoId());
							
							Integer dspTgtTurn = displayPlanRepository.getDspPromtDspTgtTurn(dspPromtDspTgt);
							dspPromtDspTgt.setDspTgtTurn(dspTgtTurn);
							dspPromtDspTgtRepository.insertDspPromtDspTgt(dspPromtDspTgt);
						//}
					}
				} else {
					
					Integer dspTgtTurn = displayPlanRepository.getDspPromtDspTgtTurn(dspPromtDspTgt);
					dspPromtDspTgt.setDspTgtTurn(dspTgtTurn);
					dspPromtDspTgtRepository.insertDspPromtDspTgt(dspPromtDspTgt);
				}
				
			}
			
		}
	}
	
	//기획전 구분 타이틀
	//구분타이틀 등록(텍스트, 이미지)
	/**
	 * 구분타이틀 순번 생성.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public Integer getSprtrTurn(Long promtSn) throws Exception {
		return displayPlanRepository.getSprtrTurn(promtSn);
	}
	
	/**
	 * 기획전 구분타이틀 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtSprtr [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void insertDspPromtSprtr(DspPromtSprtr dspPromtSprtr) throws Exception {
		dspPromtSprtrRepository.insertDspPromtSprtr(dspPromtSprtr);
	}
	
	//구분타이틀 수정(텍스트, 이미지)
	/**
	 * 구분타이틀 수정(텍스트, 이미지).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtSprtr [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void updateDspPromtSprtr(DspPromtSprtr dspPromtSprtr) throws Exception {
		displayPlanRepository.updateDspPromtSprtrInfo(dspPromtSprtr);
	}
	
	/**
	 * 구분타이틀 언어 merge.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void saveDspPromtSprtrLangInfo(DspPromtSprtrLang dspPromtSprtrLang) throws Exception {
		displayPlanRepository.saveDspPromtSprtrLangInfo(dspPromtSprtrLang);
	}
	
	
	/**
	 * 구분타이틀 그리드 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void updatePromtSprtrList (List<DspPromtSprtrBoDTO> list) throws Exception {
		DspPromtSprtr dspPromtSprtr = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		for(DspPromtSprtrBoDTO dspPromtSprtrBoDTO: list) {
			dspPromtSprtr = dspPromtSprtrBoDTO.getDspPromtSprtr();
			dspPromtSprtr.setRegtrId(loginId);
			dspPromtSprtr.setUdterId(loginId);
			displayPlanRepository.updateDspPromtSprtrForGrid(dspPromtSprtr);
		}
		
	}
	
	
	/**
	 * 구분타이틀 그리드 삭제 -- 언어 삭제-> 타이틀 삭제
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void deletePromtSprtrList (List<DspPromtSprtrBoDTO> list)  {
		DspPromtSprtr dspPromtSprtr = null;
		
		for(DspPromtSprtrBoDTO dspPromtSprtrBoDTO: list) {
			dspPromtSprtr = dspPromtSprtrBoDTO.getDspPromtSprtr();
			
			DspPromtSprtrLang dspPromtSprtrLang = new DspPromtSprtrLang();
			dspPromtSprtrLang.setPromtSn(dspPromtSprtr.getPromtSn());
			dspPromtSprtrLang.setSprtrTurn(dspPromtSprtr.getSprtrTurn());
			
			//연결된 상품이 있는지 체크
			DspPromtGod dspPromtGod = new DspPromtGod();
			dspPromtGod.setPromtSn(dspPromtSprtr.getPromtSn());
			dspPromtGod.setSprtrTurn(dspPromtSprtr.getSprtrTurn());
			int cnt = displayPlanRepository.selectPromtGodCnt(dspPromtGod);
			if(cnt > 0) {
				throw new NotDeletedPromtGodException(null);
			}
			
			displayPlanRepository.deleteDspPromtSprtrLangInfo(dspPromtSprtrLang);
			
			//dspPromtSprtrRepository.deleteDspPromtSprtr(dspPromtSprtr);
		}
		
	}
	
	
	/**
	 * 기획전 상품 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @return String[] [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public String[] insertPromtGodList(List<DspPromtGod> list) throws Exception {
		String[] result = new String[2];
		
		if(list != null&&list.size()>0) {
			DspPromtGod targetDspPromtGod = list.get(0);
			
			String loginId = BOSecurityUtil.getLoginId();
//			int dfSortSeq = Integer.parseInt(DisplayEnum.DF_SORT_SEQ.toString());
			int dfSortSeq = 0;
			
			//기존 등록상품 중복 체크
			List<String> godNoList = displayPlanRepository.selectPromtGodCnncedList(targetDspPromtGod); 
			String godNo = null;
			String dupGod = "";
			int insCnt = 0;
			
			// 저장시 전시 순서가 자동으로 들어가도록 개선.
			if(godNoList != null) {
				dfSortSeq = godNoList.size();
			}
			
			for(DspPromtGod dspPromtGod: list) {
				godNo = dspPromtGod.getGodNo();
				if(StringService.isNotEmpty(godNo)&&godNoList.contains(godNo)) {
					dupGod += godNo + ",";
				} else {
					dfSortSeq++;
					dspPromtGod.setRegtrId(loginId);
					dspPromtGod.setUdterId(loginId);
					dspPromtGod.setSortSeq(dfSortSeq);
					dspPromtGod.setGodEvlExpsrYn("N");
					dspPromtGodRepository.insertDspPromtGod(dspPromtGod);
					insCnt++;
				}
			}
			
			result[0] = dupGod ;
			result[1] = ""+insCnt ; 
		}
		return result;
	}
	
	
	
	
	/**
	 * 기획전 상품 그리드 수정
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 수정대상 연결상품 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 23
	 */
	public void updatePromtGodList(List<DspPromtGodBoDTO> list) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		DspPromtGod dspPromtGod = null;
		if (list != null) {
			for(DspPromtGodBoDTO dspPromtGodBoDTO: list) {
				dspPromtGod = dspPromtGodBoDTO.getDspPromtGod();
				dspPromtGod.setUdterId(loginId);
				
				displayPlanRepository.updateDspPromtGodInfo(dspPromtGod);
			}
		}
	}
	/**
	 * 기획전 상품 리뷰정렬 기준 수정
	 *
	 * <p/>
	 *
	 *
	 *
	 * @param dspPromtBoDTO 기획전 상품 리뷰정렬 기준 정보
	 * @throws Exception the exception
	 * @since 2016. 6. 16
	 */
	public void updatePromtGodReviewSortCd(DspPromtBoDTO dspPromtBoDTO) throws Exception {
 		String loginId = BOSecurityUtil.getLoginId();
		DspPromtGod dspPromtGod = new DspPromtGod();
		dspPromtGod.setUdterId(loginId);
		//dspPromtGod.setGodEvlExpsrSectCd(dspPromtBoDTO.getGodEvlExpsrSectCd());
		dspPromtGod.setPromtSn(Long.parseLong(dspPromtBoDTO.getScPromtSn()));
		//dspPromtGod.setPcGodEvlListQty(dspPromtBoDTO.getDspPromt().getPcGodEvlListQty());
		//dspPromtGod.setImgGodEvlPrioExpsrYn(dspPromtBoDTO.getImgGodEvlPrioExpsrYn());
		
		displayPlanRepository.updatePromtGodReviewSortCd(dspPromtGod);
	}

	/**
	 * 기획전 상품 그리드 삭제
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 삭제 대상 연결 상품 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 23
	 */
	public void deletePromtGodList(List<DspPromtGodBoDTO> list) throws Exception {
		DspPromtGod dspPromtGod = null;
		if (list != null) {
			for(DspPromtGodBoDTO dspPromtGodBoDTO: list) {
				dspPromtGod = dspPromtGodBoDTO.getDspPromtGod();
				dspPromtGodRepository.deleteDspPromtGod(dspPromtGod);
			}
		}
	}
	
	public List<DspPromtGodExt> updateSortSeqPromtGod(List<DspPromtGod> list, Long promtSn, Integer sprtrTurn) throws Exception {
		List<DspPromtGodExt> errList = null;
		if (list != null) {
			String loginId = BOSecurityUtil.getLoginId();
			
			int res = 0;
			errList = new ArrayList<DspPromtGodExt>();
			for(DspPromtGod dspPromtGod: list) {
				dspPromtGod.setUdterId(loginId);
				dspPromtGod.setPromtSn(promtSn);
				dspPromtGod.setSprtrTurn(sprtrTurn);
				
				res = displayPlanRepository.updateSortSeqPromtGod(dspPromtGod);
				if(res < 1) {
					DspPromtGodExt dspPromtGodExt = new DspPromtGodExt();
					dspPromtGodExt.setGodNo(dspPromtGod.getGodNo());
					dspPromtGodExt.setSortSeq(dspPromtGod.getSortSeq());
					dspPromtGodExt.setErrMsg("등록되지 않은 온라인품번입니다.");
					errList.add(dspPromtGodExt);
				}
				
			}
		}
		return errList;
	}
	 
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 기획전 b2e코드 조회
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 수정대상 연결상품 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 23
	 */
	public String selectB2eCode(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
	    return displayPlanRepository.selectB2eCode(searchDTO);
    }
	
	/**
	 * 기획전 b2e코드 프로모션 테이블 b2e코드 비교
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param list 수정대상 연결상품 목록
	 * @throws Exception the exception
	 * @since 2015. 4. 23
	 */
	public String selectPromotionB2eCode(String promtB2eCode) {
	    // TODO Auto-generated method stub
		return displayPlanRepository.selectPromotionB2eCode(promtB2eCode);
    }

	
	public List<GodIconFoResult> getGodIconList(List<String> godNoList) throws Exception {
		
		//List<GodTagResveFoResult> godTagResveList = null;
		List<GodIconFoResult> godIconList = null;
		if(godNoList != null && godNoList.size() > 0){
			//godTagResveList = displayRepository.selectGodResve2(pk, godNoList);
			godIconList = displayRepository.selectGodIconList2(godNoList);
		}
		return godIconList;
	}
	
	/**
	 * 딜기획전 전시대상에 해당하는 기획전번호 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param listPromtSn [설명]
	 * @return Integer [설명]
	 * @since 2015. 9. 3
	 */
	public Integer selectPickPromtSn(List<Integer> listPromtSn) throws Exception {
		return displayPlanRepository.selectPickPromtSn(listPromtSn);
	}
	
	/**
	 * 기획전의 노출브랜드 정보 조회 (Beanpole,8Seconds,Beaker,Homme,Other 순으로 최상위 1건).
	 *
	 * @param promtSn the promt sn
	 * @param pk the pk
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectPromtMdBrnd(Integer promtSn,SystemPK pk) throws Exception {
		return displayPlanRepository.selectPromtMdBrnd(promtSn, pk);
	}
	

	private void changeDspPromtLnag(DspPromt dspPromt, DspPromtLang dspPromtLang){
		// 대체변환 안함.
		if(dspPromtLang != null){
			dspPromt.setPromtNm(dspPromtLang.getPromtNm());
//			dspPromt.setPcImgFileUrl(dspPromtLang.getPcImgFileUrl());
//			dspPromt.setPcImgAltrtvCont(dspPromtLang.getPcImgAltrtvCont());
//			dspPromt.setPcImgFileNm(dspPromtLang.getPcImgFileNm());
		}else{
			dspPromt.setPromtNm("");
//			dspPromt.setPcImgFileUrl("");
//			dspPromt.setPcImgAltrtvCont("");
//			dspPromt.setPcImgFileNm("");
		}
	}
	

	private void changeDspCtgryCnncGodLang(God god,  GodTagResve godTagResve,GodLangbyTagResve godLangbyTagResve){
		// 대체변환 안함.
		if(godTagResve != null){
			if(godLangbyTagResve != null && godLangbyTagResve.getTagNm() != null){
				god.setTagNm(godLangbyTagResve.getTagNm());
			}else if(godTagResve != null && godTagResve.getTagNm() != null){
				god.setTagNm(godTagResve.getTagNm());
			}else{
				god.setTagNm("");
			}
		}
	}


	private void changeDspCtgryCnncGodLang2(God god, GodLangbyGodNm godLang){
		// 대체변환 안함.
		if(godLang != null){
			god.setGodNm(godLang.getGodNm());
			god.setMobileGodNm(godLang.getMobileGodNm());
			god.setTagNm(godLang.getTagNm());
			god.setColorTagNm(godLang.getColorTagNm());
			god.setThemaTagNm(godLang.getThemaTagNm());
			god.setColorNm(godLang.getColorNm());
		}else{
			god.setGodNm("");
			god.setMobileGodNm("");
			god.setTagNm("");
			god.setColorTagNm("");
			god.setThemaTagNm("");
			god.setColorNm("");
		}
	}

	/**
	 * 2016.03.30
	 * SR : #15522 [기획전 타이틀/상품 일괄 업로드 기능 개선]
	 * 담당자 : 주동민
	 * 요청자 : 이원희
	 * 
	 * @param promtSn
	 * @param sprtrTurn
	 * @param godNos
	 * @throws Exception
	 */
	public void insertPromtGodList(Long promtSn,Integer sprtrTurn, String[] godNos) throws Exception {
		
		if(godNos != null && godNos.length > 0) {
			DspPromtGod targetDspPromtGod = new DspPromtGod();
			targetDspPromtGod.setPromtSn(promtSn);
			targetDspPromtGod.setSprtrTurn(sprtrTurn);
			
			String loginId = BOSecurityUtil.getLoginId();
			int dfSortSeq = Integer.parseInt(DisplayEnum.DF_SORT_SEQ.toString());
			
			//기존 등록상품 중복 체크
			List<String> godNoList = displayPlanRepository.selectPromtGodCnncedList(targetDspPromtGod); 
			String godNo = null;
			String dupGod = "";
			int insCnt = 0;
			
			for(int i = 0 ; i < godNos.length ; i++) {
				godNo = godNos[i];
				if(StringService.isNotEmpty(godNo) && godNoList.contains(godNo)) {
					dupGod += godNo + ",";
				} else {
					targetDspPromtGod.setGodNo(godNo);
					targetDspPromtGod.setRegtrId(loginId);
					targetDspPromtGod.setUdterId(loginId);
					targetDspPromtGod.setSortSeq(dfSortSeq);
					targetDspPromtGod.setDspYn("Y");
					
					dspPromtGodRepository.insertDspPromtGod(targetDspPromtGod);
					insCnt++;
				}
			}
		}
	}
	
	/**
	 * 2016.03.30
	 * SR : #15522 [기획전 타이틀/상품 일괄 업로드 기능 개선]
	 * 담당자 : 주동민
	 * 요청자 : 이원희
	 * 
	 * @param promtSn
	 * @return
	 * @throws Exception
	 */
	public int selectDspPromtSnCnt(Long promtSn) throws Exception {
		DspPromt dspPromt = new DspPromt();
		dspPromt.setPromtSn(promtSn);
		
	    return displayPlanRepository.selectDspPromtSnCnt(dspPromt);
    }
	
	
	/**
	 * Save dsp promt img.
	 *
	 * @param dspPromtImg the dsp promt img
	 * @throws Exception the exception
	 */
	public void saveDspPromtImg(DspPromtImg dspPromtImg, String dvc) throws Exception {
		DspPromtImg obj = dspPromtImgRepository.selectDspPromtImg(dspPromtImg);
		String loginId = BOSecurityUtil.getLoginId();
		dspPromtImg.setRegtrId(loginId);
		dspPromtImg.setUdterId(loginId);
		
		if(obj!=null && obj.getPromtSn()!=null && obj.getPromtSn() != 0) {
			if(dvc.equals("PC")) {
				displayPlanRepository.updateDspPromtImgPc(dspPromtImg);
			} 
			else if (dvc.equals("MOBILE")) {
				displayPlanRepository.updateDspPromtImgMobile(dspPromtImg);
			}
			else {
				dspPromtImgRepository.updateDspPromtImg(dspPromtImg);
			}
		}
		else {
			//if(StringService.isNotEmpty(dspPromtImg.getPcImgFileNm()) || StringService.isNotEmpty(dspPromtImg.getPcImgFileUrl()) || StringService.isNotEmpty(dspPromtImg.getPcImgAltrtvCont()) 
			//	|| StringService.isNotEmpty(dspPromtImg.getPcImgExpsrTxt1Cont()) || StringService.isNotEmpty(dspPromtImg.getPcImgExpsrTxt2Cont())) {
				dspPromtImgRepository.insertDspPromtImg(dspPromtImg);
			//}
		}
	}
	
	/**
	 * Save dsp promt img lang.
	 *
	 * @param dspPromtImgLang the dsp promt img lang
	 * @throws Exception the exception
	 */
	public void saveDspPromtImgLang(DspPromtImgLang dspPromtImgLang, String dvc) throws Exception {
		DspPromtImgLang obj = dspPromtImgLangRepository.selectDspPromtImgLang(dspPromtImgLang);
		String loginId = BOSecurityUtil.getLoginId();
		dspPromtImgLang.setRegtrId(loginId);
		dspPromtImgLang.setUdterId(loginId);
		
		if(obj!=null && obj.getPromtSn()!=null && obj.getPromtSn() != 0) {
			if(dvc.equals("PC")) {
				displayPlanRepository.updateDspPromtImgLangPc(dspPromtImgLang);
			} 
			else if (dvc.equals("MOBILE")) {
				displayPlanRepository.updateDspPromtImgLangMobile(dspPromtImgLang);
			}
			else {
				dspPromtImgLangRepository.updateDspPromtImgLang(dspPromtImgLang);
			}
		}
		else {
			if(StringService.isNotEmpty(dspPromtImgLang.getPcImgFileNm()) || StringService.isNotEmpty(dspPromtImgLang.getPcImgFileUrl()) || StringService.isNotEmpty(dspPromtImgLang.getPcImgAltrtvCont()) 
					|| StringService.isNotEmpty(dspPromtImgLang.getPcImgExpsrTxt1Cont()) || StringService.isNotEmpty(dspPromtImgLang.getPcImgExpsrTxt2Cont())) {
				dspPromtImgLangRepository.insertDspPromtImgLang(dspPromtImgLang);
			}
		}
	}
	
	/**
	 * 기획전 이미지 조회.
	 *
	 * @param dspPromtImg the dsp promt img
	 * @return the dsp promt img
	 * @throws Exception the exception
	 */
	public DspPromtImg selectDspPromtImg (DspPromtImg dspPromtImg) throws Exception {
		DspPromtImg obj = dspPromtImgRepository.selectDspPromtImg(dspPromtImg);
		
		return obj;
	}
	
	/**
	 * 기획전 이미지 언어 목록조회
	 *
	 * @param dspPromtImg the dsp promt img
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspPromtImgLang> selectDspPromtImgLangList (DspPromtImg dspPromtImg) throws Exception {
		List<DspPromtImgLang> list = displayPlanRepository.selectDspPromtImgLangList(dspPromtImg);
		
		return list;
	}
	
	
	/**
	 * 기획전 전시 대상 체크
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].대상 기획전이 없으면 0, 있으면 1을 반환
	 *
	 * @param DspPlanSearchFoDTO
	 * @return int
	 * @since 2017. 8. 4
	 */
	public int selectPlanTgtChkCnt(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		return displayPlanRepository.selectPlanTgtChkCnt(searchDTO);
    }
	
	/**
	 * 기획전 전시 대상 정보 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].대상기획전의 전시 대상 정보를 제공 - 회원타입코드,회원속성코드,그룹사코드,회원타입코드명,회원속성코드명,그룹사코드명등 
	 *
	 * @param DspPlanSearchFoDTO
	 * @return dspPlanDetailResultFoDTO
	 * @since 2017. 8. 4
	 */
	public DspPlanDetailResultFoDTO selectPlanDspTgtInfo (DspPlanSearchFoDTO searchDTO){
		return displayPlanRepository.selectPlanDspTgtInfo(searchDTO);
	}
	
}
