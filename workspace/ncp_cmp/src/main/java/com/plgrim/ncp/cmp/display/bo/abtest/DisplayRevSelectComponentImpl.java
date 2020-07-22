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
 * @since       2015. 8. 27       
 */
package com.plgrim.ncp.cmp.display.bo.abtest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import com.plgrim.ncp.biz.display.data.DspAbTestAnlSearchDTO;
import com.plgrim.ncp.biz.display.data.DspAbTestBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttAbTestDTO;
import com.plgrim.ncp.biz.display.result.DspAbTestAnlResult;
import com.plgrim.ncp.biz.display.result.DspAbTestResult;
import com.plgrim.ncp.biz.display.result.DspAbTestRevResult;
import com.plgrim.ncp.biz.display.result.DspAbTestSetModResult;
import com.plgrim.ncp.biz.display.result.DspConttAbTestResult;
import com.plgrim.ncp.biz.display.result.DspConttAbTestSetResult;
import com.plgrim.ncp.biz.display.result.DspRevCpstResult;
import com.plgrim.ncp.biz.display.service.DisplayAbTestAnlService;
import com.plgrim.ncp.biz.display.service.DisplayRevSelectService;
import com.plgrim.ncp.cmp.display.bo.DisplayRevSelectComponent;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.page.PageParam;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Component
public class DisplayRevSelectComponentImpl extends AbstractComponent implements DisplayRevSelectComponent {

	/** The display rev select service. */
	@Autowired
	DisplayRevSelectService displayRevSelectService;

	/** The display ab test anl service. */
	@Autowired
	DisplayAbTestAnlService displayAbTestAnlService;
	
	/**
	 * Select ab test list.
	 *
	 * @param dspAbTestDTO the dsp ab test DTO
	 * @param pageParam the page param
	 * @return the list
	 */
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevSelectComponent#selectAbTestList(com.plgrim.ncp.biz.display.data.DspAbTestBoDTO, com.plgrim.ncp.framework.page.PageParam)
	 */
	@Override
	public List<DspAbTestResult> selectAbTestList(DspAbTestBoDTO dspAbTestDTO, PageParam pageParam)  {
		return displayRevSelectService.selectAbTestList(dspAbTestDTO, pageParam);
	}
	
	/**
	 * Select ab test total count.
	 *
	 * @param dspAbTestDTO the dsp ab test DTO
	 * @return the integer
	 */
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevSelectComponent#selectAbTestTotalCount(com.plgrim.ncp.biz.display.data.DspAbTestBoDTO)
	 */
	@Override
	public Integer selectAbTestTotalCount(DspAbTestBoDTO dspAbTestDTO)  {
		// TODO Auto-generated method stub
		return displayRevSelectService.selectAbTestTotalCount(dspAbTestDTO);
	}

	/**
	 * Select ab test detail.
	 *
	 * @param abTestSn the ab test sn
	 * @return the dsp ab test result
	 */
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevSelectComponent#selectAbTestDetail(java.lang.Long)
	 */
	@Override
	public DspAbTestResult selectAbTestDetail(int abTestSn)  {
		return displayRevSelectService.selectAbTestDetail(abTestSn);
	}

	/**
	 * Select page rev by admin.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the long
	 */
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevSelectComponent#selectPageRevByAdmin(com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public Long selectPageRevByAdmin(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectService.selectPageRevByAdmin(dspCnrConttScBoDTO);
	}

	/**
	 * Select page ab test count.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the string
	 */
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevSelectComponent#selectPageAbTestCount(com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	public String selectPageAbTestCount(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectService.selectPageAbTestCount(dspCnrConttScBoDTO);
	}
	
	/**
	 * 특정 KEY에 존재하는 템플릿 리비전 목록 조회.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the list
	 * @ 
	 */
	@Override
	public List<DspRevCpstResult> selectRevCpstList(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectService.selectRevCpstList(dspCnrConttScBoDTO);
	}

	/**
	 * 리비전 정보 조회.
	 *
	 * @param dspRev the dsp rev
	 * @return the dsp rev
	 * @ 
	 */
	@Override
	public DspRev selectDspRev(DspRev dspRev)  {
		return displayRevSelectService.selectDspRev(dspRev);
	}

	/**
	 * 미리보기 URL 조회.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the preview url
	 * @ 
	 */
	@Override
	public String getPreviewUrl(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		String url = null;
		String domain = null;

		String lang = dspCnrConttScBoDTO.getPrevLangCd();
		if(StringService.isEmpty(lang)) {
			lang = "";
		}
		else {
			lang += ".";
		}

		String dvc = dspCnrConttScBoDTO.getScDvcSectCd();
		if(StringService.isEmpty(dvc) || !"MOBILE".equals(dvc)) {
			dvc = "PC";
		}

		String configKey = "ncp_web_bo.fo.site." + lang + dvc + "_" + dspCnrConttScBoDTO.getMallId();
		domain = getConfigService().getProperty(configKey);

		String prevUrl = displayRevSelectService.setPreviewUrl(dspCnrConttScBoDTO);
		if(StringService.isNotEmpty(prevUrl)) {
			url = domain + prevUrl;
		}
		else {
			url = "NONE";
		}

		return url;
	}
	
	/**
	 * 컨텐츠 관리의 AB-TEST 리비전 목록.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the dsp contt ab test result
	 * @ 
	 */
	@Override
	public DspConttAbTestResult selectConttAbTestRevList(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		
		DspConttAbTestResult result = displayRevSelectService.selectConttAbTestRevList(dspCnrConttScBoDTO);
		
		//글로벌 미리보기 컨텐츠 체크 여부
		if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScGlobalConttYn()) && "Y".equals(dspCnrConttScBoDTO.getScGlobalConttYn())) {
			List<DspConttAbTestSetResult> setList = result.getDspAbTestSetList();

			for(DspConttAbTestSetResult set: setList) {
				List<DspAbTestRevResult> revLst = set.getDspAbTestRevList();
				for(DspAbTestRevResult rev: revLst) {
					DspAbTestRev dspAbTestRev = rev.getDspAbTestRev();
					DspRevCpst dspRevCpst = rev.getDspRevCpst();

					DspCnrConttScBoDTO dto = new DspCnrConttScBoDTO();
					BeanUtils.copyProperties(dspCnrConttScBoDTO, dto);
					dto.setScRevSn(dspAbTestRev.getRevSn());
					if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDvcSectCd()) && "MOBILE".equals(dspCnrConttScBoDTO.getScDvcSectCd())){
						dto.setScTmplatSn(Long.toString(dspRevCpst.getMobileTmplatSn()));
					}
					else {
						dto.setScTmplatSn(Long.toString(dspRevCpst.getPcTmplatSn()));
					}

					dto.setLang("ENG");
					int engConttCnt = displayRevSelectService.selectGlobalConttCntRev(dto);
					rev.setEngConttCnt(engConttCnt);

					dto.setLang("CHI");
					int chiConttCnt = displayRevSelectService.selectGlobalConttCntRev(dto);
					rev.setChiConttCnt(chiConttCnt);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Select ab test contt detail.
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the dsp ab test
	 */
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayRevSelectComponent#selectAbTestDetail(com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest)
	 */
	@Override
	public DspAbTest selectAbTestConttDetail(DspAbTest dspAbTest)  {
		return displayRevSelectService.selectAbTestConttDetail(dspAbTest);
	}
	
	/**
	 * Select cstmr sgmt percent.
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the int
	 */
	public int selectCstmrSgmtPercent(DspConttAbTestDTO dspConttAbTestDTO)  {
		return displayRevSelectService.selectCstmrSgmtPercent(dspConttAbTestDTO);
	}

	/**
	 * Select ab test expsr list.
	 *
	 * @param searchDTO the search DTO
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the dsp contt ab test result
	 */
	@Override
	public DspConttAbTestResult selectAbTestExpsrList(DspAbTestAnlSearchDTO searchDTO, DspCnrConttScBoDTO dspCnrConttScBoDTO) {

		// AB 테스트에 설정되어있는 세트, 변경 순번 목록 조회
		DspConttAbTestResult resultList = displayRevSelectService.selectAbTestsetModList(searchDTO.getAbTestSn());

		DspConttAbTestResult abTestSetModResult = new DspConttAbTestResult();
		List<DspConttAbTestSetResult> setResultList = new ArrayList<>();

		for(DspConttAbTestSetResult setList : resultList.getDspAbTestSetList()){
			List<DspAbTestSetModResult> modAnlResultList = new ArrayList<>();
			searchDTO.setSetTurn(setList.getSetTurn());
			DspConttAbTestSetResult setResult = new DspConttAbTestSetResult();

			// 세트 노출 방식이 고객 세그먼트일 경우 노출 필터 조회
			if("CSTMR_SGMT".equals(resultList.getDspAbTest().getSetExpsrMenmthdCd())){
//				DspAbTestSetFlter dspAbTestSetFlter = new DspAbTestSetFlter();
//				dspAbTestSetFlter.setAbTestSn(Long.parseLong(StringService.integerToString(searchDTO.getAbTestSn())));
//				dspAbTestSetFlter.setSetTurn(setList.getSetTurn());

//				setResult.setDspAbTestSetFlterList(displayRevSelectService.selectDspAbTestSetFilter(dspAbTestSetFlter));
				setResult.setDspAbTestSetFlterList(setList.getDspAbTestSetFlterList());
			}
			

			// MOD 별 분석 결과 조회
			setResult.setSetTurn(setList.getSetTurn());
			for(DspAbTestSetModResult modList : setList.getDspAbTestSetModList()){
				searchDTO.setModTurn(modList.getModTurn());
				if("CSTMR_SGMT".equals(resultList.getDspAbTest().getSetExpsrMenmthdCd())){
					searchDTO.setExclsYn("Y");
				}
				List<DspAbTestAnlResult> anlResult = displayAbTestAnlService.selectAbTestExpsrAnl(searchDTO);

				if(anlResult != null && anlResult.size() > 0){
					for(DspAbTestAnlResult anl : anlResult){
						dspCnrConttScBoDTO.setScRevSn(Long.parseLong(Integer.toString(anl.getRevSn())));
						dspCnrConttScBoDTO.setScTmplatSn(anl.getTmplatSn());

						dspCnrConttScBoDTO.setLang("CHI");
						anl.setChiConttCnt(displayRevSelectService.selectGlobalConttCntRev(dspCnrConttScBoDTO));

						dspCnrConttScBoDTO.setLang("ENG");
						anl.setEngConttCnt(displayRevSelectService.selectGlobalConttCntRev(dspCnrConttScBoDTO));
					}
				}

				DspAbTestSetModResult modResult = new DspAbTestSetModResult();
				modResult.setModTurn(modList.getModTurn());
				modResult.setDspAbTestAnlResultList(anlResult);

				modAnlResultList.add(modResult);
			}


			setResult.setDspAbTestSetModList(modAnlResultList);
			setResultList.add(setResult);
		}
		resultList.setDspAbTestSetList(setResultList);

		return resultList;
	}

	/**
	 * Select mall id.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the string
	 */
	@Override
	public String selectMallId(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		return displayRevSelectService.selectMallId(dspCnrConttScBoDTO);
	}
	
//	/**
//	 * BASE 리비전 조회
//	 *
//	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
//	 * @return the base rev sn
//	 * @ the exception
//	 */
//	@Override
//	public Long getBaseRevSn(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
//		
////		//AB-TEST 존재 엽부 체크
////    	String abTestSelectYn = displayRevSelectService.selectPageAbTestCount(dspCnrConttScBoDTO);
////    	if(StringService.isEmpty(abTestSelectYn)) {
////    		dspCnrConttScBoDTO.setAbTestSelectYn("N");
////    	}
////    	else {
////    		dspCnrConttScBoDTO.setAbTestSelectYn(abTestSelectYn);
////    	}
//    	//해당페이지에 연결된 BASE REV 조회 (AB-TEST 있을 경우 / 없을 경우)
////		dspCnrConttScBoDTO.setScRevSectCd("BASE");
//		Long baseRev = displayRevSelectService.getBaseRevSn(dspCnrConttScBoDTO);
//		
//		return baseRev;
//	}
	
	/**
	 * Select base rev.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the dsp rev cpst result
	 */
	@Override
	public DspRevCpstResult selectBaseRev(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectService.selectBaseRev(dspCnrConttScBoDTO);
	}

	/**
	 * Select ab test anl count.
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the integer
	 */
	@Override
	public Integer selectAbTestAnlCount(DspAbTest dspAbTest) {
		return displayRevSelectService.selectAbTestAnlCount(dspAbTest);
	}
}
