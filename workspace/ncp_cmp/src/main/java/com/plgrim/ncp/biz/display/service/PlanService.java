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
 * @since       2015. 7. 24       
 */
package com.plgrim.ncp.biz.display.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmResult;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCnrConttExtResult;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.data.DspPromtSprtrExt;
import com.plgrim.ncp.biz.display.repository.DxmDisplayRepository;
import com.plgrim.ncp.biz.display.repository.MbmDisplayRepository;
import com.plgrim.ncp.biz.display.repository.PlanRepository;
import com.plgrim.ncp.biz.display.repository.SamDisplayRepository;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.promotion.data.EventFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtSprtrExt;
import com.plgrim.ncp.biz.promotion.repository.EventRepository;
import com.plgrim.ncp.framework.page.PageParam;

@Service
public class PlanService extends AbstractService {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private DxmDisplayRepository dxmDisplayRepository;
	
	@Autowired
	private MbmDisplayRepository mbmDisplayRepository;
	
	@Autowired
	private SamDisplayRepository samDisplayRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	public DspPlanFoResult selectDxmPlan(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		DspPlanFoResult dspPlanFoResult = new DspPlanFoResult();
		dspPlanFoResult.setDspPromtExtend(planRepository.selectDxmPlan(dspPromtScFrDTO));

		List<DspPromtSprtrExt> sprtrList = planRepository.selectDxmPlanSprtrList(dspPromtScFrDTO);
		
		List<DspPromtSprtrExt> textList = new ArrayList<>();
		List<DspPromtSprtrExt> imgList = new ArrayList<>();
		for (DspPromtSprtrExt dspPromtSprtrExt : sprtrList) {

			dspPromtScFrDTO.setSprtrTurn(String.valueOf(dspPromtSprtrExt.getSprtrTurn()));
			dspPromtSprtrExt.setGodList(planRepository.selectDxmPlanSprtrGodList(dspPromtScFrDTO));
			
			if(StringUtils.isNotEmpty(dspPromtSprtrExt.getSprtrImgFileNm()) ) {
				imgList.add(dspPromtSprtrExt);
			}		
			if(StringUtils.isNotEmpty(dspPromtSprtrExt.getSprtrNm()) ) {
				textList.add(dspPromtSprtrExt);
			}

		}
		
		dspPlanFoResult.setTextList(textList);
		dspPlanFoResult.setImgList(imgList);
		dspPlanFoResult.setSprtrList(sprtrList);
		return dspPlanFoResult;
	
	}
	
	public DspPlanFoResult selectMallPlan(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		
		DspPlanFoResult dspPlanFoResult = new DspPlanFoResult();
		dspPlanFoResult.setDspPromtExtend(planRepository.selectMallPlan(dspPromtScFrDTO));

		List<DspPromtSprtrExt> sprtrList = planRepository.selectMallPlanSprtrList(dspPromtScFrDTO);

		for (DspPromtSprtrExt dspPromtSprtrExt : sprtrList) {
			
			dspPromtScFrDTO.setSprtrTurn(String.valueOf(dspPromtSprtrExt.getSprtrTurn()));
			dspPromtSprtrExt.setGodList(planRepository.selectMallPlanSprtrGodList(dspPromtScFrDTO));

		}
		
		dspPlanFoResult.setSprtrList(sprtrList);
		return dspPlanFoResult;
		
	}

	public List<DspPlanFoResult> selectPlanCornerList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
 
		List<DspPlanFoResult> list = planRepository.selectPlanCornerList(dspPromtScFrDTO);
 
		DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
		dspCtgryScFrDTO.setMallId(dspPromtScFrDTO.getMallId());
		dspCtgryScFrDTO.setDevice(dspPromtScFrDTO.getDevice());
		dspCtgryScFrDTO.setLang(dspPromtScFrDTO.getLang());
		dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());
		
		for (DspPlanFoResult dspCnrFrResult : list) {
			dspPromtScFrDTO.setCnrSn(dspCnrFrResult.getCnrSn());
			dspPromtScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = planRepository.selectPlanCornerSetList(dspPromtScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
				dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
				dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());
				dspCnrSetFrResult.setCnrConttExList(dxmDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
			}
			if (!sets.isEmpty()) {
				dspCnrFrResult.setDspCnrSetList(sets);
			}
		}
 
		return list;
	}
	
	public List<DspPlanFoResult> selectMallPlanCornerList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		 
		List<DspPlanFoResult> list = planRepository.selectMallPlanCornerList(dspPromtScFrDTO);
 
		DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
		dspCtgryScFrDTO.setMallId(dspPromtScFrDTO.getMallId());
		dspCtgryScFrDTO.setDevice(dspPromtScFrDTO.getDevice());
		dspCtgryScFrDTO.setLang(dspPromtScFrDTO.getLang());
		dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());
		
		for (DspPlanFoResult dspCnrFrResult : list) {
			dspPromtScFrDTO.setCnrSn(dspCnrFrResult.getCnrSn());
			dspPromtScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = planRepository.selectMallPlanCornerSetList(dspPromtScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
				dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
				dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());
				if("MBM".equals(dspPromtScFrDTO.getMallId())){
					dspCnrSetFrResult.setCnrConttExList(mbmDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
				} else {
					dspCnrSetFrResult.setCnrConttExList(samDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
				}
			}
			if (!sets.isEmpty()) {
				dspCnrFrResult.setDspCnrSetList(sets);
			}
		}
 
		return list;
	}
 
	public EventFoDTO selectDxmEvt(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		EventFoDTO eventFoDTO = new EventFoDTO();
		
		eventFoDTO.setEventExt(eventRepository.selectEvent(eventSearchFoDTO));
 
		List<EvtSprtrExt> sprtrList = planRepository.selectDxmEvtSprtrList(eventSearchFoDTO);

		List<EvtSprtrExt> textList = new ArrayList<>();
		List<EvtSprtrExt> imgList = new ArrayList<>();
		
		for (EvtSprtrExt evtSprtrExt : sprtrList) {
			eventSearchFoDTO.setSprtrTurn(String.valueOf(evtSprtrExt.getSprtrTurn()));
			evtSprtrExt.setGodList(planRepository.selectDxmEvtAplGodList(eventSearchFoDTO));

			if(StringUtils.isNotEmpty(evtSprtrExt.getSprtrImgFileNm()) ) {
				imgList.add(evtSprtrExt);
			}
			if(StringUtils.isNotEmpty(evtSprtrExt.getSprtrNm()) ) {
				textList.add(evtSprtrExt);
			}
 
		}
		eventFoDTO.setTextList(textList);
		eventFoDTO.setImgList(imgList);
		eventFoDTO.setSprtrExtList(sprtrList);
		return eventFoDTO;
	}
	
	public Page<DspPlanFoResult> selectDxmEventPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
		return planRepository.selectDxmEventPlanList(dspPromtScFrDTO, pageParam);
	}
	public List<DspPlanFoResult> selectDxmStyleList(DspPromtScFrDTO dspPromtScFrDTO)
			throws Exception {
		return planRepository.selectDxmStyleList(dspPromtScFrDTO );
	}
	
	/**
	 * Discoverer 기획전 조회
	 * 
	 * @param dspPromtScFrDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<DspPlanFoResult> selectDxmDiscovererPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
		RepositoryHelper.makePageEntityIndex(dspPromtScFrDTO, pageParam);
		
		// Discoverer 기획전 건수 조회
		long discovererPlanListCount = planRepository.selectDxmDiscovererPlanListCount(dspPromtScFrDTO);
		
		// Discoverer 기획전 목록 조회
		List<DspPlanFoResult> discovererPlanList = new ArrayList<DspPlanFoResult>();
		List<DspPlanFoResult> returnDiscovererPlanList = new ArrayList<DspPlanFoResult>();
		
		if(discovererPlanListCount > 0) {
			discovererPlanList = planRepository.selectDxmDiscovererPlanList(dspPromtScFrDTO);
			
			if(discovererPlanList != null && !discovererPlanList.isEmpty()) {
				int row = 1;
				String sortDiv = "ASC";
				for(DspPlanFoResult result : discovererPlanList) {
					// 그룹별 Discoverer 기획전 조회
					DspPromtScFrDTO dspPromtScFrByDTO = new DspPromtScFrDTO();
					dspPromtScFrByDTO = dspPromtScFrDTO;
//					dspPromtScFrByDTO.setPromtSn( Long.parseLong(result.getEvtNo()) );
					dspPromtScFrByDTO.setPromtGrpSn( Long.parseLong(result.getPromtGrpSn()) );
					// 짝수 이면서 PC만
					if(row % 2 == 0 && "PC".equals(dspPromtScFrDTO.getDevice())) {
						sortDiv = "DESC";
					}
					// 홀수
					else {
						sortDiv = "ASC";
					}
					row++;
					dspPromtScFrByDTO.setSortDiv(sortDiv);
					List<DspPlanFoResult> discovererPlanListByGroup = planRepository.selectDxmDiscovererPlanListByGroup(dspPromtScFrByDTO);

					/* 각 Discoverer 기획전에 그룹별 목록을 추가
					 * 구조
					 * 기획전번호 기획전그룹번호 그룹별기획전번호
					 * 1          1              1,2,3
					 * 4          2              4,5,6,7
					 * 8          3              8,9,10,11,12
					 * 
					 */
					DspPlanFoResult returnResult = result;
					returnResult.setDiscovererPlanListByGroup(discovererPlanListByGroup);
					
					returnDiscovererPlanList.add(returnResult);
				}
			}
		}
		
		return new PageImpl<DspPlanFoResult>(returnDiscovererPlanList, pageParam.getPageable(), discovererPlanListCount);
	}
	
	/**
	 * Discoverer 기획전 그룹별 조회
	 * 
	 * @param dspPromtScFrByDTO
	 * @return List<DspPlanFoResult>
	 * @throws Exception
	 */
	public List<DspPlanFoResult> selectDxmDiscovererPlanListByGroup(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		return planRepository.selectDxmDiscovererPlanListByGroup(dspPromtScFrDTO);
	}
	
	/**
	 * Discoverer 기획전 그룹별 다른글 조회
	 * 
	 * @param dspPromtScFrByDTO
	 * @return List<DspPlanFoResult>
	 * @throws Exception
	 */
	public List<DspPlanFoResult> selectDxmDiscovererPlanListByGroupWithoutSelf(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		return planRepository.selectDxmDiscovererPlanListByGroupWithoutSelf(dspPromtScFrDTO);
	}
	
	/**
	 * Discoverer 기획전 그룹 일련번호 조회
	 * 
	 * @param dspPromtScFrByDTO
	 * @return List<DspPlanFoResult>
	 * @throws Exception
	 */
	public DspPromt selectDxmDiscovererPlanGroupInfo(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		return planRepository.selectDxmDiscovererPlanGroupInfo(dspPromtScFrDTO);
	}
	
	public DspCnrFrResult selectDxmEventPlanContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrFrResult> list = dxmDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrConttList = new ArrayList<>();
		List<DspCnrConttExt> cnrBnrConttList = new ArrayList<>();
		int count = 0;
		Long CnrSn= 0L;
		boolean flag = true;
		for (DspCnrFrResult dspCnrFrResult : list) {
			//코너 단위로 리스트를 가져올수가 없어서 별도 처리함
			if(count == 0){
			
				CnrSn =	dspCnrFrResult.getCnrSn();		
			
			}else if(CnrSn != dspCnrFrResult.getCnrSn()){
				flag = false;
			}
			
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = dxmDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {

				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				if(flag) {
					cnrBnrConttList.addAll(dxmDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				}else {
					cnrConttList.addAll(dxmDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				}
			
			}
			count++;
		}
		DspCnrFrResult result = new DspCnrFrResult();

		result.setCnrBnrConttList(cnrBnrConttList);
		result.setCnrConttList(cnrConttList);
		return result;

	}
	
	/**
	 * 코너 콘텐츠 목록 조회(PAGING)
	 * cnrConttList 를 페이징 처리하여 반환한다.
	 * 
	 * @param dspCtgryScFrDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public DspCnrFrResult selectDxmEventPlanConttByPaging(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {

		List<DspCnrFrResult> list = dxmDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrConttExtResult> cnrConttListByPaging = new ArrayList<>();
		List<DspCnrConttExt> cnrBnrConttList = new ArrayList<>();
		int count = 0;
		Long CnrSn= 0L;
		long cnrConttListByPagingTotCnt = 0;
		boolean flag = true;
		for (DspCnrFrResult dspCnrFrResult : list) {
			//코너 단위로 리스트를 가져올수가 없어서 별도 처리함
			if(count == 0) {
				CnrSn =	dspCnrFrResult.getCnrSn();		
			}
			else if(CnrSn != dspCnrFrResult.getCnrSn()) {
				flag = false;
			}
			
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = dxmDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				if(flag) {
					cnrBnrConttList.addAll(dxmDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				}
				else {
					RepositoryHelper.makePageEntityIndex(dspCtgryScFrDTO, pageParam);
					
					// 코너 콘텐츠 건수 조회
					cnrConttListByPagingTotCnt += dxmDisplayRepository.selectCategoryCornerConttListByPagingTotCnt(dspCtgryScFrDTO, pageParam);
					if(cnrConttListByPagingTotCnt > 0) {
						cnrConttListByPaging.addAll(dxmDisplayRepository.selectCategoryCornerConttListByPaging(dspCtgryScFrDTO, pageParam));
					}
				}
			}
			count++;
		}
		DspCnrFrResult result = new DspCnrFrResult();

		result.setCnrBnrConttList(cnrBnrConttList);
		result.setCnrConttListByPaging(new PageImpl<DspCnrConttExtResult>(makePagingCnrConttList(cnrConttListByPaging, pageParam), pageParam.getPageable(), cnrConttListByPagingTotCnt));
		return result;

	}
	
	private List<DspCnrConttExtResult> makePagingCnrConttList(List<DspCnrConttExtResult> cnrConttListByPaging, PageParam pageParam) {
		List<DspCnrConttExtResult> returnResultList = new ArrayList<>();
		
		int begin = pageParam.getBeginIndex();
		int end = pageParam.getEndIndex();
		int size = cnrConttListByPaging.size();
		
		if(cnrConttListByPaging != null && !cnrConttListByPaging.isEmpty()) {
			for(int i=0; i<size; i++) {
				if((i+1) >= begin && (i+1) <= end) {
					DspCnrConttExtResult result = cnrConttListByPaging.get(i);
					returnResultList.add(result);
				}
			}
		}
		
		return returnResultList;
	}
	
	
	public PrmResult selectWebPointInfo(String mbrNo) {
		return planRepository.selectWebPointInfo(mbrNo);
	}
	
	public PrmResult selectWebPointInfoNew(MbrWebpntHist mbrWebpntHist) {
		return planRepository.selectWebPointInfoNew(mbrWebpntHist);
	}
	
	public EventFoDTO selectMallEvt(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		EventFoDTO eventFoDTO = new EventFoDTO();
		
		eventFoDTO.setEventExt(eventRepository.selectEvent(eventSearchFoDTO));
 
		List<EvtSprtrExt> sprtrList = planRepository.selectMallEvtSprtrList(eventSearchFoDTO);

		List<EvtSprtrExt> textList = new ArrayList<>();
		List<EvtSprtrExt> imgList = new ArrayList<>();
		
		for (EvtSprtrExt evtSprtrExt : sprtrList) {
			eventSearchFoDTO.setSprtrTurn(String.valueOf(evtSprtrExt.getSprtrTurn()));
			evtSprtrExt.setGodList(planRepository.selectMallEvtAplGodList(eventSearchFoDTO));

			if(StringUtils.isNotEmpty(evtSprtrExt.getSprtrImgFileNm()) ) {
				imgList.add(evtSprtrExt);
			}
			if(StringUtils.isNotEmpty(evtSprtrExt.getSprtrNm()) ) {
				textList.add(evtSprtrExt);
			}
 
		}
		eventFoDTO.setTextList(textList);
		eventFoDTO.setImgList(imgList);

		eventFoDTO.setSprtrExtList(sprtrList);
		return eventFoDTO;
	}
	
	public Page<DspPlanFoResult> selectMallEventPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
		return planRepository.selectMallEventPlanList(dspPromtScFrDTO, pageParam);
	}
	
	public DspCnrFrResult selectMbmEventPlanContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrFrResult> list = mbmDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrConttList = new ArrayList<>();
		List<DspCnrConttExt> cnrBnrConttList = new ArrayList<>();
		int count = 0;
		Long CnrSn= 0L;
		boolean flag = true;
		for (DspCnrFrResult dspCnrFrResult : list) {
			//코너 단위로 리스트를 가져올수가 없어서 별도 처리함
			if(count == 0){
			
				CnrSn =	dspCnrFrResult.getCnrSn();		
			
			}else if(CnrSn != dspCnrFrResult.getCnrSn()){
				flag = false;
			}
			
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = mbmDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {

				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				if(flag) {
					cnrBnrConttList.addAll(mbmDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				}else {
					cnrConttList.addAll(mbmDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				}
			
			}
			count++;
		}
		DspCnrFrResult result = new DspCnrFrResult();

		result.setCnrBnrConttList(cnrBnrConttList);
		result.setCnrConttList(cnrConttList);
		return result;

	}
	
	public DspCnrFrResult selectSamEventPlanContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrFrResult> list = samDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrConttList = new ArrayList<>();
		List<DspCnrConttExt> cnrBnrConttList = new ArrayList<>();
		int count = 0;
		Long CnrSn= 0L;
		boolean flag = true;
		for (DspCnrFrResult dspCnrFrResult : list) {
			//코너 단위로 리스트를 가져올수가 없어서 별도 처리함
			if(count == 0){
			
				CnrSn =	dspCnrFrResult.getCnrSn();		
			
			}else if(CnrSn != dspCnrFrResult.getCnrSn()){
				flag = false;
			}
			
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = samDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {

				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				if(flag) {
					cnrBnrConttList.addAll(samDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				}else {
					cnrConttList.addAll(samDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				}
			
			}
			count++;
		}
		DspCnrFrResult result = new DspCnrFrResult();

		result.setCnrBnrConttList(cnrBnrConttList);
		result.setCnrConttList(cnrConttList);
		return result;

	}
	
	public DspPlanFoResult selectMallPlanAdvance(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		
		DspPlanFoResult dspPlanFoResult = new DspPlanFoResult();
		dspPlanFoResult.setDspPromtExtend(planRepository.selectMallPlan(dspPromtScFrDTO));

		List<DspPromtSprtrExt> sprtrList = planRepository.selectMallPlanSprtrList(dspPromtScFrDTO);
 
		dspPlanFoResult.setSprtrList(sprtrList);
		return dspPlanFoResult;
		
	}

	public Page<DspCnrConttExt> searchDisplayGodPageList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception {
		RepositoryHelper.makePageEntityIndex(dspPromtScFrDTO, pageParam);
		return planRepository.searchDisplayGodPageList(dspPromtScFrDTO, pageParam);
	}
	
	public EventFoDTO selectMallEventAdvance(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		EventFoDTO eventFoDTO = new EventFoDTO();
		
		eventFoDTO.setEventExt(eventRepository.selectEvent(eventSearchFoDTO));
 
		List<EvtSprtrExt> sprtrList = planRepository.selectMallEvtSprtrList(eventSearchFoDTO);

		eventFoDTO.setSprtrExtList(sprtrList);
		return eventFoDTO;
	}
	public Page<DspCnrConttExt> searchEvtGodPageList(EventSearchFoDTO eventSearchFoDTO, PageParam pageParam) throws Exception {
		RepositoryHelper.makePageEntityIndex(eventSearchFoDTO, pageParam);

		return planRepository.searchEvtGodPageList(eventSearchFoDTO, pageParam);
	}
	
}
