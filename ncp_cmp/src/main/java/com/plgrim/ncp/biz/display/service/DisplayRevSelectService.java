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
 * @since       2015. 4. 7       
 */
package com.plgrim.ncp.biz.display.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfig;
import com.plgrim.ncp.base.repository.dsp.DspAbTestRepository;
import com.plgrim.ncp.base.repository.dsp.DspCtgryRepository;
import com.plgrim.ncp.base.repository.dsp.DspRevCpstRepository;
import com.plgrim.ncp.base.repository.dsp.DspRevRepository;
import com.plgrim.ncp.base.repository.dsp.DspStrndRepository;
import com.plgrim.ncp.biz.display.data.DspAbTestBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttAbTestDTO;
import com.plgrim.ncp.biz.display.repository.DisplayCategoryRepository;
import com.plgrim.ncp.biz.display.repository.DisplayRevSelectRepository;
import com.plgrim.ncp.biz.display.result.DspAbTestResult;
import com.plgrim.ncp.biz.display.result.DspAbTestRevResult;
import com.plgrim.ncp.biz.display.result.DspAbTestSetFlterResult;
import com.plgrim.ncp.biz.display.result.DspConttAbTestResult;
import com.plgrim.ncp.biz.display.result.DspRevCpstResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 전시 리비전 Select Service
 * 
 */
@Service
public class DisplayRevSelectService extends AbstractService {

	@Autowired
	DisplayRevSelectRepository displayRevSelectRepository;

	@Autowired
	DspRevRepository dspRevRepository;
	
	@Autowired
	DspRevCpstRepository dspRevCpstRepository;
	
	@Autowired
	DspAbTestRepository dspAbTestRepository;

	@Autowired
	DisplayCategoryRepository displayCategoryRepository;

	@Autowired
	DspCtgryRepository dspCtgryRepository;

	@Autowired
	DspStrndRepository dspStrndRepository;

	@Value("${ncp_web_bo.fo.site.plgrim.main}")
	private String newMainCategoryNo;
	@Value("${ncp_web_bo.fo.site.plgrim.beaker.main}")
	private String beakerThemaNo;
	@Value("${ncp_web_bo.fo.site.plgrim.8s.main}")
	private String brnd8SCtgryNo;
	@Value("${ncp_web_bo.fo.site.plgrim.kiosk.best}")
	private String plgrimKioskBestNo;
	@Value("${ncp_web_bo.fo.site.plgrim.kiosk.gs}")
	private String plgrimKioskGsNo;
	@Value("${ncp_web_bo.fo.site.plgrim.section.brnd}")
	private String plgrimSectionBrndNo;
	@Value("${ncp_web_bo.fo.site.plgrim.section.ctgry}")
	private String plgrimSectionCtgryNo;
	@Value("${ncp_web_bo.fo.site.plgrim.section.evt}")
	private String plgrimSectionEvtNo;
    @Value("${ncp_web_bo.fo.site.plgrim.goods.ctgry}")
    private String godCtgryNo;

	/**
	 * AB Test 목록 조회
	 *
	 * @param dspAbTestDTO the dsp ab test dto
	 * @param pageParam the page param
	 * @return the list
	 * @ the exception
	 */
	public List<DspAbTestResult> selectAbTestList(DspAbTestBoDTO dspAbTestDTO, PageParam pageParam)  {
		return displayRevSelectRepository.selectAbTestList(dspAbTestDTO, pageParam);
	}
	
	/**
	 * AB Test total count.
	 *
	 * @param dspAbTestDTO the dsp ab test dto
	 * @return the integer
	 * @ the exception
	 */
	public Integer selectAbTestTotalCount(DspAbTestBoDTO dspAbTestDTO)  {
		return displayRevSelectRepository.selectAbTestTotalCount(dspAbTestDTO);
	}

	public DspAbTestResult selectAbTestDetail(int abTestSn)  {
		return displayRevSelectRepository.selectAbTestDetail(abTestSn);
	}

	/**
	 *  특정 페이지 (KEY)에 존재하는 ABTEST 존재여부 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the string
	 * @ the exception
	 */
	public String selectPageAbTestCount(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectRepository.selectPageAbTestCount(dspCnrConttScBoDTO);
	}
	
	/**
	 * 특정 KEY에 존재하는 템플릿 리비전 목록 조회
	 *
	 * @param dspCnrConttScBoDTO
	 * @return
	 * @
	 */
	public List<DspRevCpstResult> selectRevCpstList(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectRepository.selectRevCpstList(dspCnrConttScBoDTO);
	}
	
//	/**
//	 * 특정 KEY에 존재하는 BASE 리비전 번호조회
//	 *
//	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
//	 * @return the dsp rev cpst result
//	 */
//	public Long getBaseRevSn(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
//		Long revSn = 1L;
//		DspRevCpstResult baseRev = displayRevSelectRepository.selectBaseRev(dspCnrConttScBoDTO);
//		if(baseRev != null && baseRev.getDspRev() != null) {
//			revSn = baseRev.getDspRev().getRevSn();
//		}
//		
//		return revSn;
//	}
	
	
	/**
	 * 특정 KEY에 존재하는 BASE 리비전 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the dsp rev cpst result
	 */
	public DspRevCpstResult selectBaseRev(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectRepository.selectBaseRev(dspCnrConttScBoDTO);
	}
	
	/**
	 * Select dsp rev cpst.
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @return the list
	 * @ the exception
	 */
	public DspRevCpst selectDspRevCpst(DspRevCpst dspRevCpst)  {
		//return dspRevCpstRepository.selectDspRevCpst(dspRevCpst);
		return null;
	}
	/**
	 * BO관리자의 리비전 존재여부 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @return the integer
	 * @ the exception
	 */
	public Long selectRevByAdmin(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectRepository.selectRevByAdmin(dspCnrConttScBoDTO);
	}
	
	/**
	 * 특정 페이지의 리비전 번호 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @return the long
	 * @ the exception
	 */
	public Long selectPageRevByAdmin(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectRepository.selectPageRevByAdmin(dspCnrConttScBoDTO);
	}

	/**
	 * 리비전 정보 조회
	 *
	 * @param dspRev
	 * @return
	 * @
	 */
	public DspRev selectDspRev(DspRev dspRev)  {
		 return dspRevRepository.selectDspRev(dspRev);
 
	}

	/**
	 * 미리보기 URL 생성
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @return the dsp rev cpst
	 * @throws Exception
	 * @ the exception
	 */
	public String setPreviewUrl(DspCnrConttScBoDTO dspCnrConttScBoDTO){
		String previewUrl = "";

		if("DXM".equals(dspCnrConttScBoDTO.getMallId())){
			if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspBrndId())){		//브랜드몰 전시카테고리 URL
				String brandShopNo = displayCategoryRepository.selectBrandParam(dspCnrConttScBoDTO.getScDspBrndId());

				DspCtgry dspCtgry = new DspCtgry();
				dspCtgry.setDspCtgryNo(dspCnrConttScBoDTO.getScDspCtgryNo());
				try{
					dspCtgry = dspCtgryRepository.selectDspCtgry(dspCtgry);
				}catch(Exception e){

				}

				if("STRTGY_CTGRY".equals(dspCtgry.getCtgrySectCd()) || "OTLT_CTGRY".equals(dspCtgry.getCtgrySectCd())){
					previewUrl = "/preview/preview/list?dspCtgryNo=&etcCtgryNo="+dspCnrConttScBoDTO.getScDspCtgryNo()+"&brndShopId="+dspCnrConttScBoDTO.getScDspBrndId()+"&brandShopNo="+brandShopNo;
				}else{
					previewUrl = "/preview/preview/list?dspCtgryNo="+dspCnrConttScBoDTO.getScDspCtgryNo()+"&brndShopId="+dspCnrConttScBoDTO.getScDspBrndId()+"&brandShopNo="+brandShopNo;
				}

			}else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScDspCtgryNo())){
				String dspCtgryNo = dspCnrConttScBoDTO.getScDspCtgryNo();

				if(dspCtgryNo.indexOf("BDM") > -1){

					if(dspCtgryNo.indexOf("BDMA09") >-1 && dspCtgryNo.length() >= 9){
						previewUrl = "/beaker/preview/main?brndShopId=MCBR&brandShopNo="+dspCtgryNo;
					}else{
						String brndShopId = displayCategoryRepository.selectBrandId(dspCtgryNo);
						previewUrl = "/preview/main?brndShopId="+brndShopId+"&brandShopNo="+dspCtgryNo;
					}
				}else if(dspCtgryNo.indexOf("THM") > -1){
					if(brnd8SCtgryNo.equals(dspCtgryNo)){
						previewUrl = "/preview/main?brndShopId=8SBSS&brandShopNo=BDMA07A01";
					}else if(beakerThemaNo.equals(dspCtgryNo)){
						previewUrl = "/preview/main?brndShopId=MCBR&brandShopNo=BDMA09";
					}else if(newMainCategoryNo.equals(dspCtgryNo)){
						previewUrl = "/main";
					}else if(plgrimKioskBestNo.equals(dspCtgryNo)){
						previewUrl = "/kioskPage?id="+plgrimKioskBestNo;
					}else if(plgrimKioskGsNo.equals(dspCtgryNo)){
						previewUrl = "/kioskPage?id="+plgrimKioskGsNo;
					}else if(plgrimSectionBrndNo.equals(dspCtgryNo)){
						previewUrl = "/brandSection?section="+plgrimSectionBrndNo;
					}else if(plgrimSectionCtgryNo.equals(dspCtgryNo)){
						previewUrl = "/categorySection?section="+plgrimSectionCtgryNo;
					}else if(plgrimSectionEvtNo.equals(dspCtgryNo)){
						previewUrl = "/main/"+plgrimSectionEvtNo+"?section="+plgrimSectionEvtNo;
					}else if(godCtgryNo.equals(dspCtgryNo)){
					    //상품상세 미리보기 URL의 경우 품번이 필요하기 때문에 임의의 빈폴레이디스 상품 조회
					    /*
					    // TODO	상품재작업필요 : 
						String godNo = goodsInfoSelectRepository.selectGodNoForPreview();
					    previewUrl = "/Beanpole-Ladies/"+godNo+"/good?tmplatYn=Y";
					    */
                    }
				}else{
					DspCtgry dspCtgry = new DspCtgry();
					dspCtgry.setDspCtgryNo(dspCnrConttScBoDTO.getScDspCtgryNo());
					try {
						dspCtgry = dspCtgryRepository.selectDspCtgry(dspCtgry);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if("STRTGY_CTGRY".equals(dspCtgry.getCtgrySectCd()) || "OTLT_CTGRY".equals(dspCtgry.getCtgrySectCd())){
						previewUrl = "/preview/list?etcCtgryNo="+dspCtgry.getDspCtgryNo();
					}else{
						previewUrl = "/preview/list?dspCtgryNo="+dspCnrConttScBoDTO.getScDspCtgryNo();
					}
				}

			}else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScPromtSn())){
				previewUrl = "/special/"+dspCnrConttScBoDTO.getScPromtSn()+"/view";
			}else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScEvtNo())){
				previewUrl = "/event/"+dspCnrConttScBoDTO.getScEvtNo()+"/view";
			}else if(StringService.isNotEmpty(dspCnrConttScBoDTO.getScStrndSn())){
				DspStrnd dspStrnd = new DspStrnd();
				dspStrnd.setStrndSn(Long.parseLong(dspCnrConttScBoDTO.getScStrndSn()));
				try{
					dspStrnd = dspStrndRepository.selectDspStrnd(dspStrnd);
				}catch(Exception e){

				}

				if("GTS".equals(dspStrnd.getStrndTpCd())){
					previewUrl = "/strend/getTheStyle/"+dspStrnd.getStrndSn()+"/view";
				}else if("LKBUK".equals(dspStrnd.getStrndTpCd())){
					previewUrl = "/strend/lookbook/"+dspStrnd.getStrndSn()+"/view";
				}
			}
		}

		return previewUrl;
	}

	/**
	 * 컨텐츠 관리의 AB-TEST 리비전 목록
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the list
	 * @ the exception
	 */
	public DspConttAbTestResult selectConttAbTestRevList(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectRepository.selectConttAbTestRevList(dspCnrConttScBoDTO);
	}
	
	/**
	 *  A/B테스트 분석결과 데이터 존재여부 체크
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the integer
	 */
	public Integer selectAbTestAnlCount(DspAbTest dspAbTest)  {
		return displayRevSelectRepository.selectAbTestAnlCount(dspAbTest);
	}
	/**
	 * AB-TEST 조회
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the dsp ab test
	 * @ the exception
	 */
	public DspAbTest selectAbTestConttDetail(DspAbTest dspAbTest)  {
		//return dspAbTestRepository.selectDspAbTest(dspAbTest);
		return null;
	}
	
	/**
	 * AB테스트 세트존재여부 확인
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @return the integer
	 * @ the exception
	 */
	public Integer selectAbTestSetCount(DspAbTestSet dspAbTestSet)  {
		return displayRevSelectRepository.selectAbTestSetCount(dspAbTestSet);
	}
	
	/**
	 * Select cstmr sgmt percent.
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the int
	 * @ the exception
	 */
	public int selectCstmrSgmtPercent(DspConttAbTestDTO dspConttAbTestDTO)  {
		return displayRevSelectRepository.selectCstmrSgmtPercent(dspConttAbTestDTO);
	}
	
	/**
	 * 현재 mod turn에 해당하는 abtest 개정 목록 조회.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the integer
	 * @ the exception
	 */
	public List<DspAbTestRevResult> selectDspAbTestRevListByMod(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectRepository.selectDspAbTestRevListByMod(dspCnrConttScBoDTO);
	}
	
	/**
	 * Select cstmr sgmt percent.
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the int
	 * @ the exception
	 */
	public int selectDspAbTestRevCount(DspAbTestRev dspAbTestRev)  {
		return displayRevSelectRepository.selectDspAbTestRevCount(dspAbTestRev);
	}
	
	/**
	 * A/B테스트 시작일, 종료일 변경 시 Dynamic Config 수정을 위해 목록 조회 
	 *
	 * @param sysDynmcConfig the sys dynmc config
	 * @return the list
	 * @ the exception
	 */
	public List<SysDynmcConfig> selectAbTestSysConfigList(SysDynmcConfig sysDynmcConfig)  {
		return displayRevSelectRepository.selectAbTestSysConfigList(sysDynmcConfig);
	}


	/**
	 * A/B테스트 리비전별 글로벌 컨텐츠 존재여부 체크
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the integer
	 * @ the exception
	 */
	public Integer selectGlobalConttCntRev(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return displayRevSelectRepository.selectGlobalConttCntRev(dspCnrConttScBoDTO);
	}
	public DspConttAbTestResult selectAbTestsetModList(int abTestSn)  {
		return displayRevSelectRepository.selectAbTestSetModList(abTestSn);
	}

	public String selectMallId(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		return displayRevSelectRepository.selectMallId(dspCnrConttScBoDTO);
	}

	public List<DspAbTestSetFlterResult> selectDspAbTestSetFilter(DspAbTestSetFlter dspAbTestSetFlter) {
		return displayRevSelectRepository.selectDspAbTestSetFilter(dspAbTestSetFlter);
	}

	public String selectBaseRevTmplatSn(DspCnrConttScBoDTO dspCnrConttScBoDTO){
		return displayRevSelectRepository.selectBaseRevTmplatSn(dspCnrConttScBoDTO);
	}
}
