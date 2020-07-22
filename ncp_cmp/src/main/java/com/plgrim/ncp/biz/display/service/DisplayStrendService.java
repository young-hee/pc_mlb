package com.plgrim.ncp.biz.display.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.repository.dsp.DspStrndDspTgtRepository;
import com.plgrim.ncp.base.repository.dsp.DspStrndLangRepository;
import com.plgrim.ncp.biz.display.data.DspCnrSearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspStrendResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspStrendSearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspStrndBoDTO;
import com.plgrim.ncp.biz.display.data.DspStrndDspTgtBoDTO;
import com.plgrim.ncp.biz.display.repository.DisplayStrendRepository;
import com.plgrim.ncp.biz.display.result.DspCnrConttFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFoResult;
import com.plgrim.ncp.biz.display.result.DspStrendFoResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

@Service
public class DisplayStrendService extends AbstractService{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayStrendRepository displayStrendRepository;
	
	/** The dsp strnd lang repository. */
	@Autowired
	DspStrndLangRepository dspStrndLangRepository;
	
	/** The display corner service. */
	@Autowired
	DisplayCornerService displayCornerService;
	
	/** The dsp strnd dsp tgt repository. */
	@Autowired
	DspStrndDspTgtRepository dspStrndDspTgtRepository;
		
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	

	/**
	 * Select strend corner.
	 *
	 * @param searchDTO the search dto
	 * @return the list
	 */
	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	public List<DspStrendFoResult> selectStrendCorner(
            DspStrendSearchFoDTO searchDTO) {
		
		List<DspStrendFoResult> strendCorner = displayStrendRepository.selectStrendCorner(searchDTO);
		
		for (DspStrendFoResult dspStrendFoResult : strendCorner) {
	        
			dspStrendFoResult = langStringChange(dspStrendFoResult,searchDTO);
        }

	    return strendCorner;
    }

	/**
	 * Select strend brand.
	 *
	 * @param searchDTO the search dto
	 * @return the list
	 */
	public List<DspStrendFoResult> selectStrendBrand(
            DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectStrendBrand(searchDTO);
    }
	
	/**
	 * Select strend season.
	 *
	 * @param searchDTO the search dto
	 * @return the list
	 */
	public List<DspStrendFoResult> selectStrendSeason(
			DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectStrendSeason(searchDTO);
	}

	/**
	 * Select strend count.
	 *
	 * @param searchDTO the search dto
	 * @return the long
	 */
	public long selectStrendCount(DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectStrendCount(searchDTO);
    }
	
//	public List<DspStrendFoResult> selectStrendList(
//            DspStrendSearchFoDTO searchDTO, PageParam pageParam) {
//		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
//		
//		//현재 시즌 생성 start
//		if(searchDTO.getSesonCd() == null || searchDTO.getSesonCd() == ""){
//			
//			Date nowDate = new Date();
//			
//			DateFormat plgrimormat = new SimpleDateFormat("yyyy");
//			
//			String year = plgrimormat.format(nowDate);
//			
//			int month = nowDate.getMonth()+1;
//			
//			String sesonCd = "SS";
//			
//			if((month <13 && month >9) || month < 3){
//				sesonCd = "FS";
//			}
//			
//			searchDTO.setSesonCd(year+""+sesonCd);
//		}
//		//현재 시즌 생성 end
//		
//		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
//		
//		List<DspStrendFoResult> strend = displayStrendRepository.selectStrendList(searchDTO);
//		
//		for (DspStrendFoResult dspStrendFoResult : strend) {
//	        
//			dspStrendFoResult.setRegistDt(sdFormat.format(dspStrendFoResult.getDspStrnd().getRegDt()));
//			
//			dspStrendFoResult = langStringChange(dspStrendFoResult,searchDTO);
//        }
//		
//	    return strend;
//    }

	/**
 * Select strend list.
 *
 * @param pk the pk
 * @param searchDTO the search dto
 * @param pageParam the page param
 * @return the list
 * @throws Exception the exception
 */
public List<DspStrendFoResult> selectStrendList(SystemPK pk,
            DspStrendSearchFoDTO searchDTO, PageParam pageParam) throws Exception {
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		
		//현재 시즌 생성 start
//		if(searchDTO.getSesonCd() == null || "".equals(searchDTO.getSesonCd())){
//			
//			Date nowDate = new Date();
//			
//			DateFormat plgrimormat = new SimpleDateFormat("yyyy");
//			
//			String year = plgrimormat.format(nowDate);
//			
//			int month = nowDate.getMonth()+1;
//			
//			String sesonCd = "SS";
//			
//			if((month <13 && month >9) || month < 3){
//				sesonCd = "FS";
//			}
//			
//			searchDTO.setSesonCd(year+""+sesonCd);
//		}
		//현재 시즌 생성 end

		DateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd");
		
		List<DspStrendFoResult> strend = displayStrendRepository.selectStrendList(searchDTO);
		
		for (DspStrendFoResult dspStrendFoResult : strend) {
			
//			if("MGZ".equals(dspStrendFoResult.getDspStrnd().getStrndTpCd().toString())){
//				DspCnrSearchFoDTO cnrSearch = new DspCnrSearchFoDTO();
//				DspCnr dspCnr = new DspCnr();
//				
//				long strndSn = dspStrendFoResult.getDspStrnd().getStrndSn();
//				
//				dspCnr.setCnrSn(dspStrendFoResult.getDspCnrTmplatInfo().getCnrSn());
//				cnrSearch.setParentType("S_TRND");
//				cnrSearch.setParentIntCd((int)strndSn);
//				cnrSearch.setDspCnr(dspCnr);
//				cnrSearch.setMallId(pk.getMall());
//				cnrSearch.setDevice(pk.getDevice());
//				cnrSearch.setLang(pk.getLang());
//				cnrSearch.setAplMbrTp(searchDTO.getAplMbrTp());
//				cnrSearch.setAplMbrAtrb(searchDTO.getAplMbrAtrb());
//				cnrSearch.setGrpcoId(searchDTO.getGrpcoId());
//				
//				//코너 기본정보
//				DspCnrFoResult dspCnrFoResult = displayCornerService.selectDspCnrDefaultInfo(pk, cnrSearch);
//				
//				List<DspCnrSetFoResult> cnrsetList = null;
//				Map<String,List<DspCnrConttFoResult>> text = null;
//				
//				if (dspCnrFoResult != null) {
//					cnrsetList = dspCnrFoResult.getDspCnrSetList();
//				}
//				
//				if(cnrsetList != null && cnrsetList.get(0) != null){
//					text = cnrsetList.get(0).getDspCnrConttMap();
//				}
//					
//				if(text != null && !text.isEmpty()){
//					if(text.get("HTML") != null && !text.get("HTML").isEmpty()){
//
//						dspStrendFoResult.setTitle1(text.get("HTML").get(0).getDspCnrContt().getHtmlSj());
//						dspStrendFoResult.setTitle2(text.get("HTML").get(0).getDspCnrContt().getHtmlCont());
//					}
//				}
//			}	
			
			dspStrendFoResult.setRegistDt(sdFormat.format(dspStrendFoResult.getDspStrnd().getRegDt()));
			dspStrendFoResult = langStringChange(dspStrendFoResult,searchDTO);
        }
		
	    return strend;
    }
	
	/**
	 * Lang string change.
	 *
	 * @param dspStrendFoResult the dsp strend fo result
	 * @param searchDTO the search dto
	 * @return the dsp strend fo result
	 */
	//strend 언어 변경 메소드
	private DspStrendFoResult langStringChange(DspStrendFoResult dspStrendFoResult,DspStrendSearchFoDTO searchDTO){
		// 대체변환 안함.
		if(!"KOR".equals(searchDTO.getLang().toString())){
			
			if(dspStrendFoResult.getDspStrndLang() != null){
				dspStrendFoResult.getDspStrnd().setStrndNm(dspStrendFoResult.getDspStrndLang().getStrndNm());
				dspStrendFoResult.getDspStrnd().setRprstImgFileNm(dspStrendFoResult.getDspStrndLang().getRprstImgFileNm());
				dspStrendFoResult.getDspStrnd().setRprstImgFileUrl(dspStrendFoResult.getDspStrndLang().getRprstImgFileUrl());
				dspStrendFoResult.getDspStrnd().setRprstImgAltrtvCont(dspStrendFoResult.getDspStrndLang().getRprstImgAltrtvCont());
			}else{
				dspStrendFoResult.getDspStrnd().setStrndNm("");
				dspStrendFoResult.getDspStrnd().setRprstImgFileNm("");
				dspStrendFoResult.getDspStrnd().setRprstImgFileUrl("");
				dspStrendFoResult.getDspStrnd().setRprstImgAltrtvCont("");
			}
		}
		
		return dspStrendFoResult;
	}
	
	
	
	
	
	
	
	
	/**
	 * S-TREND 목록 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 22
	 */
	public void updateStrndList (List<DspStrndBoDTO> list) throws Exception {
		DspStrnd dspStrnd = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		for(DspStrndBoDTO dspStrndBoDTO: list) {
			dspStrnd = dspStrndBoDTO.getDspStrnd();
			dspStrnd.setRegtrId(loginId);
			dspStrnd.setUdterId(loginId);
			displayStrendRepository.updateStrndForGrid(dspStrnd);
		}
		
	}
	
	/**
	 * S-TREND 목록 삭제
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 4
	 */
	public void deleteStrndList (List<DspStrndBoDTO> list) throws Exception {
		DspStrnd dspStrnd = null;
		
		for(DspStrndBoDTO dspStrndBoDTO: list) {
			dspStrnd = dspStrndBoDTO.getDspStrnd();
			
			DspStrndLang dspStrndLang = new DspStrndLang();
			dspStrndLang.setStrndSn(dspStrnd.getStrndSn());
			
			displayStrendRepository.deleteDspStrndLangInfo(dspStrndLang);
			
			displayStrendRepository.deleteDspStrnd(dspStrnd);
		}
		
	}
	
	/**
	 * S-TREND 번호 생성
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public long getStrndSn() throws Exception {
		return displayStrendRepository.getStrndSn();
	}
	
	
	/**
	 * 기본정보 등록-dsp_s_trnd
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void insertDspStrnd(DspStrnd dspStrnd) throws Exception {
		displayStrendRepository.insertDspStrnd(dspStrnd);
	}
	
	/**
	 * S-TREND 기본정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void updateDspStrnd(DspStrnd dspStrnd) throws Exception {
		displayStrendRepository.updateStrndInfo(dspStrnd);
	}
	

	/**
	 * S-TREND명 언어 등록 (merge)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void saveDspStrndLangInfo(DspStrndLang dspStrndLang) throws Exception {
		displayStrendRepository.saveDspStrndLangInfo(dspStrndLang);
	}

	/**
	 * S-TREND 언어 등록여부 체크.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang the dsp strnd lang
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int selectDspStrndLangCnt(DspStrndLang dspStrndLang) throws Exception {
		return displayStrendRepository.selectDspStrndLangCnt(dspStrndLang);
	}
	
	/**
	 * S-TREND 언어 - S-TREND명 수정 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang the dsp strnd lang
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int updateDspStrndLangNm(DspStrndLang dspStrndLang) throws Exception {
		int checkCnt = displayStrendRepository.selectDspStrndLangCnt(dspStrndLang);
		int res = 0;
		if(checkCnt == 0) {
			dspStrndLangRepository.insertDspStrndLang(dspStrndLang);
		}
		else {
			res = displayStrendRepository.updateDspStrndLangNm(dspStrndLang);
		}
		return res;
	}
	
	/**
	 *  S-TREND 언어 - image 수정 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang the dsp strnd lang
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int updateDspStrndLangImg(DspStrndLang dspStrndLang) throws Exception {
		int checkCnt = displayStrendRepository.selectDspStrndLangCnt(dspStrndLang);
		int res = 0;
		if(checkCnt == 0) {
			dspStrndLangRepository.insertDspStrndLang(dspStrndLang);
		}
		else {
			res = displayStrendRepository.updateDspStrndLangImg(dspStrndLang);
		}
		return res;
	}
	
	/**
	 *  S-TREND 언어 - image2 수정 .
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang the dsp strnd lang
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 6
	 */
	public int updateDspStrndLangImg2(DspStrndLang dspStrndLang) throws Exception {
		int checkCnt = displayStrendRepository.selectDspStrndLangCnt(dspStrndLang);
		int res = 0;
		if(checkCnt == 0) {
			dspStrndLangRepository.insertDspStrndLang(dspStrndLang);
		}
		else {
			res = displayStrendRepository.updateDspStrndLangImg2(dspStrndLang);
		}
		return res;
	}
	
	

	/**
	 * S-TREND 언어 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 2
	 */
	public void deleteDspStrndLang(DspStrndLang dspStrndLang) throws Exception {
		dspStrndLangRepository.deleteDspStrndLang(dspStrndLang);
	}	
	
	/**
	 * Select next strend.
	 *
	 * @param searchDTO the search dto
	 * @return the int
	 */
	public int selectNextStrend(DspStrendSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		return displayStrendRepository.selectNextStrend(searchDTO);
    }
	
	/**
	 * Insert dsp strnd dsp tgt.
	 *
	 * @param tgtCdList the tgt cd list
	 * @param tpCd the tp cd
	 * @param strndSn the strnd sn
	 * @param grpCoList the grp co list
	 * @throws Exception the exception
	 */
	public void insertDspStrndDspTgt(List<String> tgtCdList, String tpCd, long strndSn, List<DspStrndDspTgtBoDTO> grpCoList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		
		for(String tgtCd: tgtCdList) {
			DspStrndDspTgt dspStrndDspTgt = new DspStrndDspTgt();
			dspStrndDspTgt.setStrndSn(strndSn);
			dspStrndDspTgt.setRegtrId(loginId);
			dspStrndDspTgt.setUdterId(loginId);
			
			dspStrndDspTgt.setDspTgtTpCd(tpCd);
			
			// 몰 ID : MALL_ID
			// 언어 코드 : LANG_CD
			// 디바이스 코드 : DVC_CD
			// 전시 회원 유형 : DSP_MBR_TP
			// 전시 회원 속성 : DSP_MBR_ATRB
			if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.MALL_ID.toString())) {
				dspStrndDspTgt.setMallId(tgtCd);
				
				Integer dspTgtTurn = displayStrendRepository.getDspStrndDspTgtTurn(dspStrndDspTgt);
				dspStrndDspTgt.setDspTgtTurn(dspTgtTurn);
				dspStrndDspTgtRepository.insertDspStrndDspTgt(dspStrndDspTgt);
			}
			else if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.LANG.toString())) {
				dspStrndDspTgt.setLangCd(tgtCd);
				
				Integer dspTgtTurn = displayStrendRepository.getDspStrndDspTgtTurn(dspStrndDspTgt);
				dspStrndDspTgt.setDspTgtTurn(dspTgtTurn);
				dspStrndDspTgtRepository.insertDspStrndDspTgt(dspStrndDspTgt);
			}
			else if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.DVC.toString())) {
				dspStrndDspTgt.setDvcCd(tgtCd);
				
				Integer dspTgtTurn = displayStrendRepository.getDspStrndDspTgtTurn(dspStrndDspTgt);
				dspStrndDspTgt.setDspTgtTurn(dspTgtTurn);
				dspStrndDspTgtRepository.insertDspStrndDspTgt(dspStrndDspTgt);
			}
			else if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString())) {
				dspStrndDspTgt.setTgtMbrTpCd(tgtCd);
				
				Integer dspTgtTurn = displayStrendRepository.getDspStrndDspTgtTurn(dspStrndDspTgt);
				dspStrndDspTgt.setDspTgtTurn(dspTgtTurn);
				dspStrndDspTgtRepository.insertDspStrndDspTgt(dspStrndDspTgt);
			}
			else if(StringService.isNotEmpty(tpCd)&&tpCd.equals(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString())) {
				dspStrndDspTgt.setTgtMbrAtrbCd(tgtCd);
				
				if(tgtCd.equals("GRPCO_IND")) {
					for(DspStrndDspTgtBoDTO tgtDTO: grpCoList) {
						DspStrndDspTgt tgt = tgtDTO.getDspStrndDspTgt();
						//String cud = tgtDTO.getGCudTag();
						//if(StringService.isNotEmpty(cud)&&!"D".equals(cud)) {
							dspStrndDspTgt.setGrpcoId(tgt.getGrpcoId());
							
							Integer dspTgtTurn = displayStrendRepository.getDspStrndDspTgtTurn(dspStrndDspTgt);
							dspStrndDspTgt.setDspTgtTurn(dspTgtTurn);
							dspStrndDspTgtRepository.insertDspStrndDspTgt(dspStrndDspTgt);
						//}
					}
				} else {
					
					Integer dspTgtTurn = displayStrendRepository.getDspStrndDspTgtTurn(dspStrndDspTgt);
					dspStrndDspTgt.setDspTgtTurn(dspTgtTurn);
					dspStrndDspTgtRepository.insertDspStrndDspTgt(dspStrndDspTgt);
				}
				
			}
			
		}
	}
	
	/**
	 * S-TREND 전시대상 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndDspTgt [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public void deleteDspStrndDspTgtInfo(DspStrndDspTgt dspStrndDspTgt) throws Exception {
		displayStrendRepository.deleteDspStrndDspTgtInfo(dspStrndDspTgt);
	}
	public List<DspStrendFoResult> selectGetTheStylePreNextList(DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectGetTheStylePreNextList(searchDTO);
	}
	public List<DspStrendFoResult> selectGetTheStyleLNBList(DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectGetTheStyleLNBList(searchDTO);
	}
	public DspStrendFoResult selectGetTheStyleDetail(DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectGetTheStyleDetail(searchDTO);
	}
	public List<DspStrendFoResult> selectGetTheStyleList(DspStrendSearchFoDTO searchDTO,PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		return displayStrendRepository.selectGetTheStyleList(searchDTO);
	}
	public int selectGetTheStyleCount(DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectGetTheStyleCount(searchDTO);
	}
	public int selectLatestStrndSnOfGetTheStyle(DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectLatestStrndSnOfGetTheStyle(searchDTO);
	}
	public List<DspStrendFoResult> selectRecommendGodMoreOfGTS(DspStrendSearchFoDTO searchDTO) {
		return displayStrendRepository.selectRecommendGodMoreOfGTS(searchDTO);
	}

}
