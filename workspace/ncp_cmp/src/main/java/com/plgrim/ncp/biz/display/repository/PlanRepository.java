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
 * @since       2015. 8. 19       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmResult;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.data.DspPromtSprtrExt;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtSprtrExt;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PlanRepository extends AbstractRepository {

	public List<DspPlanFoResult> selectPlanCornerList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectPlanCornerList");
		DspPromtScFrDTO  copy = new DspPromtScFrDTO();
		BeanUtils.copyProperties(dspPromtScFrDTO, copy);
    	if("MOBILE_WEB".equals(copy.getDevice()) ||"MOBILE_APP".equals(copy.getDevice()) ) {
    		copy.setDevice("MOBILE");
    	};
		List<DspPlanFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectPlanCornerList",
				copy);

		return result;
	}
	
	public List<DspCnrSetFrResult> selectPlanCornerSetList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectPlanCornerSetList ");

		List<DspCnrSetFrResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectPlanCornerSetList", dspPromtScFrDTO);
		
		return result;
	}
	public DspPromtExtend selectDxmPlan(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmPlan");

		DspPromtExtend result = getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectDxmPlan", dspPromtScFrDTO);

		return result;
	}
	
	public List<DspPromtSprtrExt> selectDxmPlanSprtrList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmPlanSprtrList");

		List<DspPromtSprtrExt>  result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmPlanSprtrList", dspPromtScFrDTO);

		return result;
	}
	
	@Cacheable(value="planRepository.selectDxmPlanSprtrGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.promtSn,"
					+ "#p0.sprtrTurn,"
					+ "#p0.prcSectCd"
					+ "}")
	public List<DspCnrConttExt> selectDxmPlanSprtrGodList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmPlanSprtrGodList");

		List<DspCnrConttExt>  result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmPlanSprtrGodList", dspPromtScFrDTO);

		return result;
	}
	
 
	public List<EvtSprtrExt> selectDxmEvtSprtrList(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmPlanSprtrList");

		List<EvtSprtrExt>  result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmEvtSprtrList", eventSearchFoDTO);

		return result;
	}
	
	@Cacheable(value="planRepository.selectDxmEvtAplGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.evtNo,"
					+ "#p0.sprtrTurn,"
					+ "#p0.prcSectCd"
					+ "}")
	public List<DspCnrConttExt> selectDxmEvtAplGodList(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmEvtAplGodList");

		List<DspCnrConttExt>  result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmEvtAplGodList", eventSearchFoDTO);

		return result;
	}
	
	public Page<DspPlanFoResult> selectDxmEventPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmEventPlanList");
		RepositoryHelper.makePageEntityIndex(dspPromtScFrDTO, pageParam);

		List<DspPlanFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmEventPlanList", dspPromtScFrDTO);
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectDxmEventPlanListTotCnt", dspPromtScFrDTO);
		return new PageImpl<DspPlanFoResult>(result, pageParam.getPageable(), totalCnt);
	}

	public List<DspPlanFoResult> selectDxmStyleList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmStyleList");

		List<DspPlanFoResult>  result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmStyleList", dspPromtScFrDTO);

		return result;
	}

	/**
	 * Discoverer 기획전 건수 조회
	 * 
	 * @param dspPromtScFrDTO
	 * @return long
	 * @throws Exception
	 */
	public long selectDxmDiscovererPlanListCount(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmDiscovererPlanListCount");
		return getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectDxmDiscovererPlanListTotCnt", dspPromtScFrDTO);
	}
	
	/**
	 * Discoverer 기획전 조회
	 * 
	 * @param dspPromtScFrDTO
	 * @return List<DspPlanFoResult>
	 * @throws Exception
	 */
	public List<DspPlanFoResult> selectDxmDiscovererPlanList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmDiscovererPlanList");
		return getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmDiscovererPlanList", dspPromtScFrDTO);
	}
	
	/**
	 * Discoverer 기획전 그룹별 조회
	 * 
	 * @param dspPromtScFrDTO
	 * @return List<DspPlanFoResult>
	 * @throws Exception
	 */
	public List<DspPlanFoResult> selectDxmDiscovererPlanListByGroup(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmDiscovererPlanListByGroup");
		return getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmDiscovererPlanListByGroup", dspPromtScFrDTO);
	}
	
	/**
	 * Discoverer 기획전 그룹별 다른글 조회
	 * 
	 * @param dspPromtScFrDTO
	 * @return List<DspPlanFoResult>
	 * @throws Exception
	 */
	public List<DspPlanFoResult> selectDxmDiscovererPlanListByGroupWithoutSelf(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmDiscovererPlanListByGroupWithoutSelf");
		return getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDxmDiscovererPlanListByGroupWithoutSelf", dspPromtScFrDTO);
	}
	
	/**
	 * Discoverer 기획전 그룹 일련번호 조회
	 * 
	 * @param dspPromtScFrDTO
	 * @return DspPromt
	 * @throws Exception
	 */
	public DspPromt selectDxmDiscovererPlanGroupInfo(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDxmDiscovererPlanGroupInfo");
		return getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectDxmDiscovererPlanGroupInfo", dspPromtScFrDTO);
	}
	
	public PrmResult selectWebPointInfo(String mbrNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectWebPointInfo",mbrNo);
	}
	
	public PrmResult selectWebPointInfoNew(MbrWebpntHist mbrWebpntHist) {
		return getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectWebPointInfoNew",mbrWebpntHist);
	}
	
	public DspPromtExtend selectMallPlan(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectMallPlan");

		DspPromtExtend result = getSession1().selectOne("com.plgrim.ncp.biz.mall.display.selectMallPlan", dspPromtScFrDTO);

		return result;
	}
	
	public List<DspPromtSprtrExt> selectMallPlanSprtrList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectMallPlanSprtrList");

		List<DspPromtSprtrExt>  result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectMallPlanSprtrList", dspPromtScFrDTO);

		return result;
	}
	
	@Cacheable(value="planRepository.selectMallPlanSprtrGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.mallId,"
					+ "#p0.promtSn,"
					+ "#p0.sprtrTurn,"
					+ "#p0.prcSectCd"
					+ "}")
	public List<DspCnrConttExt> selectMallPlanSprtrGodList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectMallPlanSprtrGodList");

		List<DspCnrConttExt>  result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectMallPlanSprtrGodList", dspPromtScFrDTO);

		return result;
	}
	public Page<DspCnrConttExt> searchDisplayGodPageList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> searchDisplayGodList");

		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.searchDisplayGodPageList", dspPromtScFrDTO);
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.mall.display.searchDisplayGodPageListTotCnt", dspPromtScFrDTO);
		return new PageImpl<DspCnrConttExt>(result, pageParam.getPageable(), totalCnt);
	}
	
	public Page<DspCnrConttExt> searchEvtGodPageList(EventSearchFoDTO eventSearchFoDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> searchEvtGodPageList");

		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.searchEvtGodPageList", eventSearchFoDTO);
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.mall.display.searchEvtGodPageListTotCnt", eventSearchFoDTO);
		return new PageImpl<DspCnrConttExt>(result, pageParam.getPageable(), totalCnt);
	}
	
	public Page<DspPlanFoResult> selectMallEventPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectMallEventPlanList");
		RepositoryHelper.makePageEntityIndex(dspPromtScFrDTO, pageParam);

		List<DspPlanFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectMallEventPlanList", dspPromtScFrDTO);
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.mall.display.selectMallEventPlanListTotCnt", dspPromtScFrDTO);
		return new PageImpl<DspPlanFoResult>(result, pageParam.getPageable(), totalCnt);
	}
	
	public List<EvtSprtrExt> selectMallEvtSprtrList(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectMallEvtSprtrList");

		List<EvtSprtrExt>  result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectMallEvtSprtrList", eventSearchFoDTO);

		return result;
	}
	
	@Cacheable(value="planRepository.selectDxmEvtAplGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.evtNo,"
					+ "#p0.sprtrTurn,"
					+ "#p0.prcSectCd"
					+ "}")
	public List<DspCnrConttExt> selectMallEvtAplGodList(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectMallEvtAplGodList");

		List<DspCnrConttExt>  result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectMallEvtAplGodList", eventSearchFoDTO);

		return result;
	}
	
	public List<DspPlanFoResult> selectMallPlanCornerList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectPlanCornerList");
		DspPromtScFrDTO  copy = new DspPromtScFrDTO();
		BeanUtils.copyProperties(dspPromtScFrDTO, copy);
    	if("MOBILE_WEB".equals(copy.getDevice()) ||"MOBILE_APP".equals(copy.getDevice()) ) {
    		copy.setDevice("MOBILE");
    	};
		List<DspPlanFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectPlanCornerList",
				copy);

		return result;
	}
	
	public List<DspCnrSetFrResult> selectMallPlanCornerSetList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectPlanCornerSetList ");

		List<DspCnrSetFrResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectPlanCornerSetList", dspPromtScFrDTO);
		
		return result;
	}
	
}
