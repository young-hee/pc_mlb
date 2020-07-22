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
 * @since       2015. 7. 20       
 */
package com.plgrim.ncp.biz.display.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

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
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.repository.dsp.DspPromtRepository;
import com.plgrim.ncp.biz.display.data.DspPlanDetailResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspPlanSearchFoDTO;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult2;
import com.plgrim.ncp.biz.display.result.DspPlanGodFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanSprtrFoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.google.common.collect.Maps;

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
 * @since 2015. 6. 1
 */
@Repository
public class DisplayPlanRepository extends DspPromtRepository{

	

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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

	private DspPlanFoResult langPromtChange(DspPlanFoResult dspPlanFoResult,DspPlanSearchFoDTO searchDTO){

		if(!"KOR".equals(searchDTO.getLang().toString())){

			String promtNm = "";
			String PromtDscr ="";
			String pcImgFileNm = "";
			String pcImgFileUrl = "";
			String pcImgCont = "";
			String mbImgFileNm = "";
			String mbImgFileUrl = "";
			String mbImgCont = "";
			
			if(dspPlanFoResult.getDspPromtLang() != null) {
				promtNm = dspPlanFoResult.getDspPromtLang().getPromtNm() != null ? dspPlanFoResult.getDspPromtLang().getPromtNm().toString() : "";
				PromtDscr = dspPlanFoResult.getDspPromtLang().getPromtDscr() != null ? dspPlanFoResult.getDspPromtLang().getPromtDscr().toString() : "";
			}
			if(dspPlanFoResult.getDspPromtImgLang() != null){
				pcImgFileNm = dspPlanFoResult.getDspPromtImgLang().getPcImgFileNm() != null ? dspPlanFoResult.getDspPromtImgLang().getPcImgFileNm().toString() : "";
				pcImgFileUrl = dspPlanFoResult.getDspPromtImgLang().getPcImgFileUrl() != null ? dspPlanFoResult.getDspPromtImgLang().getPcImgFileUrl().toString() : "";
				pcImgCont = dspPlanFoResult.getDspPromtImgLang().getPcImgAltrtvCont() != null ? dspPlanFoResult.getDspPromtImgLang().getPcImgAltrtvCont().toString() : "";
				mbImgFileNm = dspPlanFoResult.getDspPromtImgLang().getMobileImgFileNm() != null ? dspPlanFoResult.getDspPromtImgLang().getMobileImgFileNm().toString() : "";
				mbImgFileUrl = dspPlanFoResult.getDspPromtImgLang().getMobileImgFileUrl() != null ? dspPlanFoResult.getDspPromtImgLang().getMobileImgFileUrl().toString() : "";
				mbImgCont = dspPlanFoResult.getDspPromtImgLang().getMobileImgAltrtvCont() != null ? dspPlanFoResult.getDspPromtImgLang().getMobileImgAltrtvCont().toString() : "";
			}

			dspPlanFoResult.getDspPromt().setPromtNm(promtNm);
			dspPlanFoResult.getDspPromt().setPromtDscr(PromtDscr);
			dspPlanFoResult.getDspPromtImg().setPcImgFileNm(pcImgFileNm);
			dspPlanFoResult.getDspPromtImg().setPcImgFileUrl(pcImgFileUrl);
			dspPlanFoResult.getDspPromtImg().setPcImgAltrtvCont(pcImgCont);
			dspPlanFoResult.getDspPromtImg().setMobileImgFileNm(mbImgFileNm);
			dspPlanFoResult.getDspPromtImg().setMobileImgFileUrl(mbImgFileUrl);
			dspPlanFoResult.getDspPromtImg().setMobileImgAltrtvCont(mbImgCont);
		}


		return dspPlanFoResult;
	}

	private DspPlanSprtrFoResult langSprtrChange(DspPlanSprtrFoResult dspPlanSprtrFoResult, DspPlanSearchFoDTO searchDTO){

		if(!"KOR".equals(searchDTO.getLang().toString())){

			String sprtrNm = "";
			String sprtrImgFileNm = "";
			String sprtrImgFileUrl = "";
			String sprtrImgCont = "";

			if(dspPlanSprtrFoResult.getDspPromtSprtrLang() != null){
				sprtrNm = dspPlanSprtrFoResult.getDspPromtSprtrLang().getSprtrNm() != null ? dspPlanSprtrFoResult.getDspPromtSprtrLang().getSprtrNm().toString() : "";
				sprtrImgFileNm = dspPlanSprtrFoResult.getDspPromtSprtrLang().getSprtrImgFileNm() != null ? dspPlanSprtrFoResult.getDspPromtSprtrLang().getSprtrImgFileNm().toString() : "";
				sprtrImgFileUrl = dspPlanSprtrFoResult.getDspPromtSprtrLang().getSprtrImgFileUrl() != null ? dspPlanSprtrFoResult.getDspPromtSprtrLang().getSprtrImgFileUrl().toString() : "";
				sprtrImgCont = dspPlanSprtrFoResult.getDspPromtSprtrLang().getSprtrImgAltrtvCont() != null ? dspPlanSprtrFoResult.getDspPromtSprtrLang().getSprtrImgAltrtvCont().toString() : "";
			}

			dspPlanSprtrFoResult.getDspPromtSprtr().setSprtrNm(sprtrNm);
			dspPlanSprtrFoResult.getDspPromtSprtr().setSprtrImgFileNm(sprtrImgFileNm);
			dspPlanSprtrFoResult.getDspPromtSprtr().setSprtrImgFileUrl(sprtrImgFileUrl);
			dspPlanSprtrFoResult.getDspPromtSprtr().setSprtrImgAltrtvCont(sprtrImgCont);
		}

		return dspPlanSprtrFoResult;
	}
	/**
	 * 카테고리 기준 리스트 카운트.
	 *
	 * @param searchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 6. 1
	 */
	public long selectPlanCategoryCount(DspPlanSearchFoDTO searchDTO) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPlanMainCount",searchDTO);
    }
	
	/**
	 * 카테고리 기준 리스트 페이징용.
	 *
	 * @param searchDTO [설명]
	 * @param pageParam [설명]
	 * @param pk [설명]
	 * @return Page [설명]
	 * @since 2015. 6. 1
	 */
	public Page<DspPlanFoResult> selectPlanCategoryList(
			DspPlanSearchFoDTO searchDTO, PageParam pageParam, SystemPK pk) {
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode("ctgry");
		
		
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		
		List<DspPlanFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanMainList",searchDTO);
		DateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date nowDate = new Date();
		
		for (DspPlanFoResult dspPlanFoResult : results) {
			
			dspPlanFoResult.setBeginDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspBegDt()));
			dspPlanFoResult.setEndDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspEndDt()));
			
			dspPlanFoResult = this.langPromtChange(dspPlanFoResult, searchDTO);
        }
		
		long total = selectPlanCategoryCount(searchDTO);
		return new PageImpl<DspPlanFoResult>(results, pageParam.getPageable(), total);
   
    }
	
	/**
	 * 카테고리 기준 리스트.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectPlanCategoryList(
			DspPlanSearchFoDTO searchDTO) {
		
		List<DspPlanFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanMainList",searchDTO);
		
		DateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date nowDate = new Date();
		
		for (DspPlanFoResult dspPlanFoResult : results) {
	        
			dspPlanFoResult.setBeginDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspBegDt()));
			dspPlanFoResult.setEndDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspEndDt()));
			dspPlanFoResult = this.langPromtChange(dspPlanFoResult, searchDTO);
        }
		
		return results;
    }
	
	/**
	 * 카테고리별 카운트.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectPlanCategoryCnt(DspPlanSearchFoDTO searchDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanCategoryCnt",searchDTO);
    }
	
	/**
	 * 브랜드 기준 리스트.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @throws Exception 
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectPlanBrandList(
            DspPlanSearchFoDTO searchDTO) throws Exception {
		
		//페이징 구현 start
		PageParam pageParam = getPageService().buildPageParam(searchDTO.getPageNo(),searchDTO.getPageRow());
		
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		
		List<DspPlanFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanMainList",searchDTO);
		
		DateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date nowDate = new Date();
		
		for (DspPlanFoResult dspPlanFoResult : results) {
	        
			dspPlanFoResult.setBeginDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspBegDt()));
			dspPlanFoResult.setEndDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspEndDt()));
			dspPlanFoResult = this.langPromtChange(dspPlanFoResult, searchDTO);
        }
		
	    return results;
    }
	
	/**
	 * 브랜드 기준 리스트 카운트.
	 *
	 * @param searchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 6. 1
	 */
	public long selectPlanBrandyCount(DspPlanSearchFoDTO searchDTO) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPlanMainCount",searchDTO);
    }
	
	/**
	 * 브랜드 기준 리스트 페이징.
	 *
	 * @param searchDTO [설명]
	 * @param pageParam [설명]
	 * @param pk [설명]
	 * @return Page [설명]
	 * @since 2015. 6. 1
	 */
	public Page<DspPlanFoResult> selectPlanBrandyList(DspPlanSearchFoDTO searchDTO, PageParam pageParam,SystemPK pk) {
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode("brnd");
		
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		
		List<DspPlanFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanMainList",searchDTO);
		
		DateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date nowDate = new Date();

		for (DspPlanFoResult dspPlanFoResult : results) {

			dspPlanFoResult.setBeginDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspBegDt()));
			dspPlanFoResult.setEndDate(
					sdFormat.format(dspPlanFoResult.getDspPromt().getDspEndDt()));

			dspPlanFoResult = this.langPromtChange(dspPlanFoResult, searchDTO);
        }
		
		long total = selectPlanBrandyCount(searchDTO);
		return new PageImpl<DspPlanFoResult>(results, pageParam.getPageable(), total);

    }
	
	/**
	 * 기획전 토탈카운트
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public long selectPlanMainTotalCount(DspPlanSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPlanMainTotalCount", searchDTO);
    }
	
	/**
	 * 브랜드별 카운트.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectPlanBrandyCnt(DspPlanSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanBrandyCnt",searchDTO);
    }
	/**
	 * 기획전 b2e정보
	 * */
	public String selectB2eCode(DspPlanSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.planSelectB2eCode", searchDTO);
    }
	/**
	 * 프로모션 b2e정보
	 * */
	public String selectPromotionB2eCode(String promtB2eCode) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPromotionB2eCode", promtB2eCode);
    }
	
	/**
	 * 기획전 상세정보.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public DspPlanFoResult2 selectPlanCornerJu(SystemPK pk, DspPlanSearchFoDTO searchDTO) {
		
		searchDTO.setMallId(pk.getMall());
		searchDTO.setLang(pk.getLang());
		searchDTO.setDevice(pk.getDevice());
		
		DspPlanFoResult2 result = getSession1().selectOne("com.plgrim.ncp.biz.display.selectPlanDetail2",searchDTO);
		
		return result;
    }
	
	/**
	 * 기획전 상세정보.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectPlanCorner(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		
		List<DspPlanFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanDetail",searchDTO);
		
		for (DspPlanFoResult dspPlanFoResult : result) {
			dspPlanFoResult = this.langPromtChange(dspPlanFoResult, searchDTO);
        }
		
		return result;
    }
	
	/**
	 * 기획전 상세정보 미리보기.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectPrevPlanCorner(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		
		List<DspPlanFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectPrevPlanDetail",searchDTO);
		
		for (DspPlanFoResult dspPlanFoResult : result) {
			dspPlanFoResult = this.langPromtChange(dspPlanFoResult, searchDTO);
        }
		
		return result;
    }
	
	/**
	 * 구분자정보.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanSprtrFoResult> selectPlanSprtr(
            DspPlanSearchFoDTO searchDTO) {
		
		List<DspPlanSprtrFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanSprtrList",searchDTO);
		
		for (DspPlanSprtrFoResult dspPlanSprtrFoResult : result) {
			dspPlanSprtrFoResult = langSprtrChange(dspPlanSprtrFoResult,searchDTO);
        }
		
		return result;
    }
	
	/**
	 * 구분자정보 미리보기.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanSprtrFoResult> selectPrevPlanSprtr(
            DspPlanSearchFoDTO searchDTO) {
		
		List<DspPlanSprtrFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.display.selectPrevPlanSprtrList",searchDTO);
		
		for (DspPlanSprtrFoResult dspPlanSprtrFoResult : result) {
			dspPlanSprtrFoResult = langSprtrChange(dspPlanSprtrFoResult,searchDTO);
        }
		
		return result;
    }
	
	/**
	 * 상품이미지정보 리스트.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<GodImg> selectGodImgList(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanGodImgList",searchDTO);
    }
	
	/**
	 * 상품정보 리스트.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanGodFoResult> selectPlanGodList(
            DspPlanSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanGodList",searchDTO);
    }
	
	/**
	 * 상품정보 리스트 미리보기.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanGodFoResult> selectPrevPlanGodList(
            DspPlanSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectPrevPlanGodList",searchDTO);
    }
	
	/**
	 * 상품정보 페이징 리스트.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanGodFoResult> selectPlanGodPageList(
            DspPlanSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanGodPageList",searchDTO);
    }
	
	public long selectPlanGodPageCount(
            DspPlanSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPlanGodPageCount",searchDTO);
		
	}	
	
	/**
	 * 브랜드 selectbox.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectbrndList(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
	    return getSession1().selectList("com.plgrim.ncp.biz.display.selectbrndList",searchDTO);
    }
	
	/**
	 * 기획전 selectbox.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectPlanList(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
	    return getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanList",searchDTO);
    }
	
	/**
	 * 카테고리 selectbox.
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 6. 1
	 */
	public List<DspPlanFoResult> selectCtgryList(DspPlanSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
	    return getSession1().selectList("com.plgrim.ncp.biz.display.selectPlanCtgryList",searchDTO);
    }

	

	/**
	 * 기획전 목록 그리드 일괄수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromt [설명]
	 * @since 2015. 5. 22
	 */
	public void updatePlanInfo(DspPromt dspPromt) {
		getSession1().update("com.plgrim.ncp.biz.display.updatePlanInfo", dspPromt);
	}
	
	/**
	 * 그리드에서 기획전 정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromt [설명]
	 * @since 2015. 5. 29
	 */
	public void updatePromtForGrid(DspPromt dspPromt) {
		getSession1().update("com.plgrim.ncp.biz.display.updatePromtForGrid", dspPromt);
	}
	
	/**
	 * 기획전 승인상태 update.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromt [설명]
	 * @since 2015. 5. 26
	 */
	public void updatePromtAprvStat(DspPromt dspPromt) {
		getSession1().update("com.plgrim.ncp.biz.display.updatePromtAprvStat", dspPromt);
	}

	

	/**
	 * 기획전 번호
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
		long promtSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_PROMT",
                DatabaseType.ORACLE).longValue();
		
		return promtSn;
	}
	
	/**
	 * 기획전 전시대상 순번생성.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtDspTgt [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public Integer getDspPromtDspTgtTurn(DspPromtDspTgt dspPromtDspTgt) throws Exception {
		
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("PROMT_SN", dspPromtDspTgt.getPromtSn());
		
		Integer dspTgtTurn = getIdGenService().generateDBOrder(getSession1(), "DSP_PROMT_DSP_TGT", "DSP_TGT_TURN", conditions, DatabaseType.ORACLE);
		
		return dspTgtTurn;
	}
	
	/**
	 * 기획전 언어 mearge
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int saveDspPromtLangInfo(DspPromtLang dspPromtLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.saveDspPromtLangInfo", dspPromtLang);
	}
	
	
	/**
	 * 기획전(언어) 등록여부 체크.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int selectDspPromtLangCnt(DspPromtLang dspPromtLang) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspPromtLangCnt", dspPromtLang);
	}
	
	/**
	 *  기획전(언어) - 기획전명 수정 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int updateDspPromtLangNm(DspPromtLang dspPromtLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtLangNm", dspPromtLang);
	}
	
	/**
	 *  기획전(언어) - 기획전 PC image 수정 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int updateDspPromtLangPcImg(DspPromtLang dspPromtLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtLangPcImg", dspPromtLang);
	}
	
	/**
	 *  기획전(언어) - 기획전 Mobile image 수정 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int updateDspPromtLangMobileImg(DspPromtLang dspPromtLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtLangMobileImg", dspPromtLang);
	}
	
	/**
	 *  기획전(언어) - 기획전 상품상세 PC image 수정 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int updateDspPromtLangPcGodImg(DspPromtLang dspPromtLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtLangPcGodImg", dspPromtLang);
	}
	
	/**
	 *  기획전(언어) - 기획전 상품상세 Mobile image 수정 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int updateDspPromtLangMobileGodImg(DspPromtLang dspPromtLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtLangMobileGodImg", dspPromtLang);
	}

	/**
	 * 기획전 언어 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int deleteDspPromtLangInfo(DspPromtLang dspPromtLang) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspPromtLangInfo", dspPromtLang);
	}
	
	/**
	 * 기획전 브랜드 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtBrnd [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int deleteDspPromtBrndInfo(DspPromtBrnd dspPromtBrnd) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspPromtBrndInfo", dspPromtBrnd);
	}
	
	/**
	 * 기획전 전시대상 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtDspTgt [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int deleteDspPromtDspTgtInfo(DspPromtDspTgt dspPromtDspTgt) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspPromtDspTgtInfo", dspPromtDspTgt);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	

	/**
	 * 기획전 구분타이틀 순번.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public Integer getSprtrTurn(Long promtSn) throws Exception {
		
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("PROMT_SN", promtSn);
		
		Integer sprtrTurn = getIdGenService().generateDBOrder(getSession1(), "DSP_PROMT_SPRTR", "SPRTR_TURN", conditions, DatabaseType.ORACLE);
		
		return sprtrTurn;
	}

	/**
	 * 기획전 구분타이틀 정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtSprtr [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void updateDspPromtSprtrInfo(DspPromtSprtr dspPromtSprtr) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtSprtrInfo", dspPromtSprtr);
	}
	

	
	/**
	 * 기획전 구분타이틀 언어 저장 - merge.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtSprtrLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void saveDspPromtSprtrLangInfo(DspPromtSprtrLang dspPromtSprtrLang) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.display.saveDspPromtSprtrLangInfo", dspPromtSprtrLang);
	}
	

	/**
	 * 기획전 구분타이틀 그리드 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtSprtr [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void updateDspPromtSprtrForGrid(DspPromtSprtr dspPromtSprtr) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtSprtrForGrid", dspPromtSprtr);
	}
	
	

	/**
	 * 기획전 구분타이틀 그리드 삭제
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtSprtrLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void deleteDspPromtSprtrLangInfo(DspPromtSprtrLang dspPromtSprtrLang)  {
		getSession1().delete("com.plgrim.ncp.biz.display.deleteDspPromtSprtrLangInfo", dspPromtSprtrLang);
	}
	
	/**
	 * 기획전 연결 상품 수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtGod [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void updateDspPromtGodInfo(DspPromtGod dspPromtGod) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtGodInfo", dspPromtGod);
	}
	/**
	 * 기획전 상품 리뷰정렬 기준 수정
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dspPromtGod [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public void updatePromtGodReviewSortCd(DspPromtGod dspPromtGod) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.display.updatePromtGodReviewSortCd", dspPromtGod);
	}

	/**
	 * 기획전연결상품 전시순서 update.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtGod [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 20
	 */
	public int updateSortSeqPromtGod(DspPromtGod dspPromtGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateSortSeqPromtGod", dspPromtGod);
	}
	
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtGod [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 1
	 */
	public List<String> selectPromtGodCnncedList(DspPromtGod dspPromtGod)  throws Exception {
		List<String> list = getSession1().selectList("com.plgrim.ncp.biz.display.selectPromtGodCnncedList", dspPromtGod);
		
		return list;
	}
	
	/**
	 * 기획전 연결 상품수 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 15
	 */
	public int selectPromtGodCnt(DspPromtGod dspPromtGod)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPromtGodCnt",dspPromtGod);
	}

	/**
	 * 딜기획전 전시대상에 해당하는 기획전번호 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param map [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 9. 3
	 */
	public Integer selectPickPromtSn(List<Integer> listPromtSn) throws Exception {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		map.put("listPromtSn", listPromtSn);
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPickPromtSn",map);
	}
	
	/**
	 * 기획전의 노출브랜드 정보 조회 (Beanpole,8Seconds,Beaker,Homme,Other 순으로 최상위 1건).
	 *
	 * @param promtSn the promt sn
	 * @param pk the pk
	 * @return the integer
	 * @throws Exception the exception
	 */
	public String selectPromtMdBrnd(Integer promtSn, SystemPK pk) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lang", pk.getLang());
		map.put("promtSn", promtSn);
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPromtMdBrnd",map);
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
	public int selectDspPromtSnCnt(DspPromt dspPromt) throws Exception {
		
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspPromtSnCnt", dspPromt);
	}

	/**
	 * 기획전 PC이미지 수정
	 *
	 * @param dspPromtImg the dsp promt img
	 */
	public void updateDspPromtImgPc(DspPromtImg dspPromtImg) {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtImgPc", dspPromtImg);
	}
	
	/**
	 * 기획전 MOBILE이미지 수정
	 *
	 * @param dspPromtImg the dsp promt img
	 */
	public void updateDspPromtImgMobile(DspPromtImg dspPromtImg) {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtImgMobile", dspPromtImg);
	}
	
	/**
	 *기획전 PC이미지 언어수정.
	 *
	 * @param dspPromtImgLang the dsp promt img lang
	 */
	public void updateDspPromtImgLangPc(DspPromtImgLang dspPromtImgLang) {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtImgLangPc", dspPromtImgLang);
	}
	
	/**
	 * 기획전 MOBILE이미지 언어수정.
	 *
	 * @param dspPromtImgLang the dsp promt img lang
	 */
	public void updateDspPromtImgLangMobile(DspPromtImgLang dspPromtImgLang) {
		getSession1().update("com.plgrim.ncp.biz.display.updateDspPromtImgLangMobile", dspPromtImgLang);
	}
	
	/**
	 * 기획전 이미지 언어 목록.
	 *
	 * @param dspPromtImg the dsp promt img
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspPromtImgLang> selectDspPromtImgLangList(DspPromtImg dspPromtImg) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspPromtImgLangList", dspPromtImg);
	}
	
	/**
	 * 기획전 전시 대상 체크
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return int [설명]
	 * @since 2017. 8. 4
	 */
	public int selectPlanTgtChkCnt(DspPlanSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPlanTgtChkCnt", searchDTO);
    }
	
	/**
	 * 기획전 전시 대상 정보 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].대상기획전의 전시 대상 정보를 제공 - 회원타입코드,회원속성코드,그룹사코드,회원타입코드명,회원속성코드명,그룹사코드명등 
	 *
	 * @param searchDTO
	 * @return dspPlanDetailResultFoDTO
	 * @since 2017. 8. 4
	 */
	public DspPlanDetailResultFoDTO selectPlanDspTgtInfo (DspPlanSearchFoDTO searchDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPlanDspTgtInfo", searchDTO);
	}
}
