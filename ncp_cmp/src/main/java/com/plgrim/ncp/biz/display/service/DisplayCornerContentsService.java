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
 * @since       2015. 7. 15       
 */
package com.plgrim.ncp.biz.display.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSetLang;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.repository.dsp.DspCnrConttLangRepository;
import com.plgrim.ncp.base.repository.dsp.DspCnrSetLangRepository;
import com.plgrim.ncp.base.repository.dsp.DspCnrSetRepository;
import com.plgrim.ncp.base.repository.dsp.DspConttImgRepository;
import com.plgrim.ncp.biz.display.data.DspCnrConttDspTgtBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspImgUploadDTO;
import com.plgrim.ncp.biz.display.exception.DspConttValidException;
import com.plgrim.ncp.biz.display.exception.NotDeletedSetException;
import com.plgrim.ncp.biz.display.repository.DisplayCornerConttDspTgtRepository;
import com.plgrim.ncp.biz.display.repository.DisplayCornerConttRepository;
import com.plgrim.ncp.biz.display.repository.DisplayCornerSetRepository;
import com.plgrim.ncp.biz.display.result.DspImgUploadResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.google.common.collect.Maps;

// TODO: Auto-generated Javadoc
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
 * @since 2015. 3. 16
 */
@Service
public class DisplayCornerContentsService extends AbstractService {

	/** The display corner set repository. */
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayCornerSetRepository displayCornerSetRepository;
	
	@Autowired
	DspCnrSetRepository dspCnrSetRepository;

	@Autowired
	DspCnrSetLangRepository dspCnrSetLangRepository;
	
	@Autowired
	DisplayCornerConttDspTgtRepository displayCornerConttDspTgtRepository;
	
	@Autowired
	DisplayCornerConttRepository displayCornerConttRepository;
	
	@Autowired
	DspCnrConttLangRepository dspCnrConttLangRepository;
	
	@Autowired
	DspConttImgRepository dspConttImgRepository;
	
	@Value("${ncp_web_bo.image.htmlImage.upload.path}")
    String htmlImageUploadPath;
    
    @Value("${ncp_web_bo.image.htmlImage.http.path}")
    String htmlImageHttpPath;
    
    @Autowired
    IdGenService idGenService;
	
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
	 * 코너 세트 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public void insertCornerSet(List<DspCnrConttScBoDTO> list) throws Exception {
		DspCnrSet dspCnrSet = null;
		String loginId = BOSecurityUtil.getLoginId();
		Long cnrSetSn = null;
		if(list != null) {
			for(DspCnrConttScBoDTO dspCnrConttScBoDTO: list) {
				dspCnrSet = dspCnrConttScBoDTO.getDspCnrSet();
				dspCnrSet.setRegtrId(loginId);
				dspCnrSet.setUdterId(loginId);
				
				cnrSetSn = displayCornerSetRepository.insertCornerSetInfo(dspCnrSet);
				dspCnrSet.setCnrSetSn(cnrSetSn);

				if(StringService.isNotEmpty(dspCnrConttScBoDTO.getSetNmChi())) {
					DspCnrSetLang lang = new DspCnrSetLang();
					lang.setRevSn(dspCnrSet.getRevSn());
					lang.setCnrSn(dspCnrSet.getCnrSn());
					lang.setCnrSetSn(cnrSetSn);
					lang.setLangCd("CHI");
					
					lang.setSetNm(dspCnrConttScBoDTO.getSetNmChi());
					lang.setRegtrId(loginId);
					
					dspCnrSetLangRepository.insertDspCnrSetLang(lang);
				}
				
				if(StringService.isNotEmpty(dspCnrConttScBoDTO.getSetNmEng())){
					DspCnrSetLang lang = new DspCnrSetLang();
					lang.setRevSn(dspCnrSet.getRevSn());
					lang.setCnrSn(dspCnrSet.getCnrSn());
					lang.setCnrSetSn(cnrSetSn);
					lang.setLangCd("ENG");
					
					lang.setSetNm(dspCnrConttScBoDTO.getSetNmEng());
					lang.setRegtrId(loginId);
					
					dspCnrSetLangRepository.insertDspCnrSetLang(lang);
				}
			}
		}
	}
		
	/**
	 * 코너 세트 등록. - 리비전 변경 저장
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public void insertCornerSetRev(List<DspCnrConttScBoDTO> list) throws Exception {
		DspCnrSet dspCnrSet = null;
		String loginId = BOSecurityUtil.getLoginId();
		Long cnrSetSn = null;
		if(list != null) {
			for(DspCnrConttScBoDTO dspCnrConttScBoDTO: list) {
				dspCnrSet = dspCnrConttScBoDTO.getDspCnrSet();
				dspCnrSet.setRegtrId(loginId);
				dspCnrSet.setUdterId(loginId);

				dspCnrSetRepository.insertDspCnrSet(dspCnrSet);

				if(StringService.isNotEmpty(dspCnrConttScBoDTO.getSetNmChi())) {
					DspCnrSetLang lang = new DspCnrSetLang();
					lang.setRevSn(dspCnrSet.getRevSn());
					lang.setCnrSn(dspCnrSet.getCnrSn());
					lang.setCnrSetSn(dspCnrSet.getCnrSetSn());
					lang.setLangCd("CHI");

					lang.setSetNm(dspCnrConttScBoDTO.getSetNmChi());
					lang.setRegtrId(loginId);

					dspCnrSetLangRepository.insertDspCnrSetLang(lang);
				}

				if(StringService.isNotEmpty(dspCnrConttScBoDTO.getSetNmEng())){
					DspCnrSetLang lang = new DspCnrSetLang();
					lang.setRevSn(dspCnrSet.getRevSn());
					lang.setCnrSn(dspCnrSet.getCnrSn());
					lang.setCnrSetSn(dspCnrSet.getCnrSetSn());
					lang.setLangCd("ENG");

					lang.setSetNm(dspCnrConttScBoDTO.getSetNmEng());
					lang.setRegtrId(loginId);

					dspCnrSetLangRepository.insertDspCnrSetLang(lang);
				}
			}
		}
	}


	/**
	 * 코너 세트 정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public void updateCornerSet(List<DspCnrConttScBoDTO> list) throws Exception {
		DspCnrSet dspCnrSet = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		if(list != null) {
			for(DspCnrConttScBoDTO dspCnrConttScBoDTO: list) {
				dspCnrSet = dspCnrConttScBoDTO.getDspCnrSet();
				dspCnrSet.setUdterId(loginId);
				
				displayCornerSetRepository.updateDspCnrSetInfo(dspCnrSet);
				
				if(StringService.isNotEmpty(dspCnrConttScBoDTO.getSetNmChi())) {
					DspCnrSetLang lang = new DspCnrSetLang();
					lang.setRevSn(dspCnrSet.getRevSn());
					lang.setCnrSn(dspCnrSet.getCnrSn());
					lang.setCnrSetSn(dspCnrSet.getCnrSetSn());
					lang.setLangCd("CHI");
					
					lang.setSetNm(dspCnrConttScBoDTO.getSetNmChi());
					lang.setUdterId(loginId);
					
					//displayCornerSetRepository.updateDspCnrSetLangNm(lang);
					displayCornerSetRepository.saveDspCnrSetLangInfo(lang);
				}
				
				if(StringService.isNotEmpty(dspCnrConttScBoDTO.getSetNmEng())){
					DspCnrSetLang lang = new DspCnrSetLang();
					lang.setRevSn(dspCnrSet.getRevSn());
					lang.setCnrSn(dspCnrSet.getCnrSn());
					lang.setCnrSetSn(dspCnrSet.getCnrSetSn());
					lang.setLangCd("ENG");
					
					lang.setSetNm(dspCnrConttScBoDTO.getSetNmEng());
					lang.setUdterId(loginId);
					
					//displayCornerSetRepository.updateDspCnrSetLangNm(lang);
					displayCornerSetRepository.saveDspCnrSetLangInfo(lang);
				}
				
				
			}
		}
		
	}
	
	
	/**
	 * 코너 세트 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public void deleteCornerSet(List<DspCnrConttScBoDTO> list) {
		DspCnrSet dspCnrSet = null;
		
		if(list != null) {
			for(DspCnrConttScBoDTO dspCnrConttScBoDTO: list) {
				dspCnrSet = dspCnrConttScBoDTO.getDspCnrSet();
				DspCnrSetLang lang = new DspCnrSetLang();
				lang.setRevSn(dspCnrSet.getRevSn());
				lang.setCnrSn(dspCnrSet.getCnrSn());
				lang.setCnrSetSn(dspCnrSet.getCnrSetSn());
				
				int cnt = displayCornerSetRepository.selectDspCnrConttCnt(dspCnrSet);
				
				if(cnt>0) {
					throw new NotDeletedSetException(null);
				}
				
				displayCornerSetRepository.deleteDspCnrSetLangInfo(lang);
				
				//displayCornerSetRepository.deleteDspCnrSet(dspCnrSet);
			}
		}
	}
	
	/**
	 * 신규 컨텐츠 순번 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 19
	 */
	public Integer getConttTurn(DspCnrContt dspCnrContt) throws Exception {
		return displayCornerConttRepository.getConttTurn(dspCnrContt);
	}
	
	/**
	 * 코너 컨텐츠 정보 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public void insertCornerContent(DspCnrContt dspCnrContt) throws Exception {
		
		DspCnrContt resDspCnrContt = this.setDefaultDspCnrContt(dspCnrContt);
		
		// 20160517_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 스티커 상품 처리]
		if(resDspCnrContt.getStkExpsrYn() == null){
			// Null 처리
			resDspCnrContt.setStkExpsrYn("N");
		}
		
		displayCornerConttRepository.insertDspCnrContt(resDspCnrContt);
		
	}
	
	/**
	 * 코너컨텐츠 언어 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 19
	 */
	public void insertCornerContentLang(DspCnrConttLang dspCnrConttLang) throws Exception {
		
		dspCnrConttLangRepository.insertDspCnrConttLang(dspCnrConttLang);
		
	}
		
	
	/**
	 * 코너 컨텐츠 정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public void updateCornerContent(DspCnrContt dspCnrContt) throws Exception {
			
		displayCornerConttRepository.updateCornerContent(dspCnrContt);
		
	}
	
	/**
	 * 코너 컨텐츠 언어 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 19
	 */
	public void updateCornerContentLang(DspCnrConttLang dspCnrConttLang) throws Exception {
		
		displayCornerConttRepository.updateDspCnrConttLangInfo(dspCnrConttLang);
		
	}
	
	/**
	 * 코너 컨텐츠 언어 수정(merge).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 19
	 */
	public void saveDspCnrConttLangInfo(DspCnrConttLang dspCnrConttLang) throws Exception {		
		displayCornerConttRepository.saveDspCnrConttLangInfo(dspCnrConttLang);		
	}
	
	/**
	 * 코너 컨텐츠 언어 수정- 이미지.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 15
	 */
	public void saveConttLangImg(DspCnrConttLang dspCnrConttLang) throws Exception {		
		displayCornerConttRepository.saveConttLangImg(dspCnrConttLang);		
	}
	
	/**
	 * 코너 컨텐츠 언어 수정 - 오버이미지.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 15
	 */
	public void saveConttLangOvImg(DspCnrConttLang dspCnrConttLang) throws Exception {		
		displayCornerConttRepository.saveConttLangOvImg(dspCnrConttLang);		
	}
	
	/**
	 * 컨텐츠 언어 저장 - 기타 텍스트형 다국어 컨텐츠
	 * 김병철 sr#1945
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @throws Exception the exception
	 * @since 2016. 05. 13
	 */
	public void saveConttLangContents(DspCnrConttLang dspCnrConttLang) throws Exception {		
		displayCornerConttRepository.saveConttLangContents(dspCnrConttLang);		
	}
	
	/**
	 * 코너컨텐츠 언어 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttLang [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 20
	 */
	public void deleteCornerContentLang(DspCnrConttLang dspCnrConttLang) throws Exception {
		
		dspCnrConttLangRepository.deleteDspCnrConttLang(dspCnrConttLang);
	}
	
	/**
	 * 코너 컨텐츠 그리드 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param instList [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 20
	 */
	public void insertCornerContentGridList(List<DspCnrConttScBoDTO> instList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); 
		
		
		if(instList != null && instList.size() > 0) {
			DspCnrConttScBoDTO dto = instList.get(0);
			String scPrvwSn = dto.getScPrvwSn();
			Long revSn = null;

			for(DspCnrConttScBoDTO dspCnrConttScBoDTO: instList) {
				DspCnrContt dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();
				//컨텐트 순번
				Integer conttTurn = displayCornerConttRepository.getConttTurn(dspCnrContt);
				dspCnrContt.setConttTurn(conttTurn);
				
				dspCnrContt.setRegtrId(loginId);
				dspCnrContt.setUdterId(loginId);
				
				//임시저장일 경우 미리보기 리비전으로 set
				if(StringService.isNotEmpty(scPrvwSn)) {
					revSn = Long.parseLong(scPrvwSn);
				}
				else {
					revSn = dspCnrContt.getRevSn();
				}
				dspCnrContt.setRevSn(revSn);

				DspCnrContt resDspCnrContt = this.setDefaultDspCnrContt(dspCnrContt);
				displayCornerConttRepository.insertCornerContent(resDspCnrContt);
				
				//언어 수정 - TEXT일 경우
				String conttTpCd = dspCnrContt.getConttTpCd();
				if(StringService.isNotEmpty(conttTpCd) && conttTpCd.equals(DisplayEnum.CONTT_TP.TEXT.toString()) ) {
					String conttNmChi = dspCnrConttScBoDTO.getConttNmChi();
					
					DspCnrConttLang dspCnrConttLang = new DspCnrConttLang();
					dspCnrConttLang.setRevSn(dspCnrContt.getRevSn());
					dspCnrConttLang.setCnrSn(dspCnrContt.getCnrSn());
					dspCnrConttLang.setCnrSetSn(dspCnrContt.getCnrSetSn());
					dspCnrConttLang.setConttTurn(dspCnrContt.getConttTurn());
					dspCnrConttLang.setUdterId(loginId);
					
					if(StringService.isNotEmpty(conttNmChi)) {
						dspCnrConttLang.setLangCd("CHI");
						dspCnrConttLang.setConttNm(conttNmChi);
						
						dspCnrConttLangRepository.insertDspCnrConttLang(dspCnrConttLang);
					} 
					
					String conttNmEng = dspCnrConttScBoDTO.getConttNmEng();
					if(StringService.isNotEmpty(conttNmEng)) {
						dspCnrConttLang.setLangCd("ENG");
						dspCnrConttLang.setConttNm(conttNmEng);
						
						dspCnrConttLangRepository.insertDspCnrConttLang(dspCnrConttLang);
					}
				}
				
				//전시대상 설정 디폴트 insert
				DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO = new DspCnrConttDspTgtBoDTO();
				dspCnrConttDspTgtBoDTO.setScRevSn(dspCnrContt.getRevSn());
				dspCnrConttDspTgtBoDTO.setScCnrSn(dspCnrContt.getCnrSn());
				dspCnrConttDspTgtBoDTO.setScCnrSetSn(dspCnrContt.getCnrSetSn());
				List<Integer> conttTurnList = new ArrayList<Integer> ();
				conttTurnList.add(dspCnrContt.getConttTurn());
				dspCnrConttDspTgtBoDTO.setScConttTurn(conttTurnList);
				dspCnrConttDspTgtBoDTO.setLang(dspCnrConttScBoDTO.getLang());
				dspCnrConttDspTgtBoDTO.setMallId(dspCnrConttScBoDTO.getMallId());
				
				saveContentDspTgt(dspCnrConttDspTgtBoDTO);
				
			}
			
		}
	}
	
	/**
	 * 코너 컨텐츠 그리드 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public void updateCornerContentList(List<DspCnrConttScBoDTO> list) throws Exception {
		DspCnrContt dspCnrContt = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		if(list != null && list.size() > 0) {
			DspCnrConttScBoDTO dto = list.get(0);
			String scPrvwSn = dto.getScPrvwSn();
			Long revSn = null;

			for(DspCnrConttScBoDTO dspCnrConttScBoDTO: list) {
				dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();
				dspCnrContt.setUdterId(loginId);
				
				//임시저장일 경우 미리보기 리비전으로 set
				if(StringService.isNotEmpty(scPrvwSn)) {
					revSn = Long.parseLong(scPrvwSn);
				}
				else {
					revSn = dspCnrContt.getRevSn();
				}
				dspCnrContt.setRevSn(revSn);

				// 20160517_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 스티커 상품 처리]
				if(dspCnrContt.getStkExpsrYn() == null){
					// Null 처리
					dspCnrContt.setStkExpsrYn("N");
				}
				
				//컨텐츠 수정
				displayCornerConttRepository.updateCornerContentForGrid(dspCnrContt);
				
				//언어 수정 - TEXT일 경우
				String conttTpCd = dspCnrContt.getConttTpCd();
				if(StringService.isNotEmpty(conttTpCd) && conttTpCd.equals(DisplayEnum.CONTT_TP.TEXT.toString()) ) {
					String conttNmChi = dspCnrConttScBoDTO.getConttNmChi();
					
					DspCnrConttLang dspCnrConttLang = new DspCnrConttLang();
					dspCnrConttLang.setRevSn(dspCnrContt.getRevSn());
					dspCnrConttLang.setCnrSn(dspCnrContt.getCnrSn());
					dspCnrConttLang.setCnrSetSn(dspCnrContt.getCnrSetSn());
					dspCnrConttLang.setConttTurn(dspCnrContt.getConttTurn());
					dspCnrConttLang.setUdterId(loginId);
					
					dspCnrConttLang.setLangCd("CHI");
					if(StringService.isNotEmpty(conttNmChi)) {
						dspCnrConttLang.setConttNm(conttNmChi);
						//merge
						displayCornerConttRepository.saveDspCnrConttLangInfo(dspCnrConttLang);
					} else {
						//delete
						dspCnrConttLangRepository.deleteDspCnrConttLang(dspCnrConttLang);
					}
					String conttNmEng = dspCnrConttScBoDTO.getConttNmEng();
					dspCnrConttLang.setLangCd("ENG");
					if(StringService.isNotEmpty(conttNmEng)) {
						dspCnrConttLang.setConttNm(conttNmEng);
						//merge
						displayCornerConttRepository.saveDspCnrConttLangInfo(dspCnrConttLang);
					} else {
						//delete
						dspCnrConttLangRepository.deleteDspCnrConttLang(dspCnrConttLang);
					}
				}
			}
		}
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @return String[] [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 21
	 */
	public String[] insertCornerContentList(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrConttExt> list) throws Exception {
		String[] result =  new String[2];
		 
		String loginId = BOSecurityUtil.getLoginId(); 
		
		String scNo = null;
		String dupNo = "";
		String nonNo = "";
		int insCnt = 0;
		
		if(list != null && list.size() > 0) {
			DspCnrConttExt scDspCnrContt = list.get(0);
			
			List<Map<String,Object>> cnncedList = displayCornerConttRepository.selectCnrConttCnncedList(scDspCnrContt);
			String conttTpCd = scDspCnrContt.getConttTpCd();
			
			for(DspCnrContt dspCnrContt: list) {
				
				boolean dup = false;
				boolean checkGod = true;
				String key = "";
				if(StringService.isNotEmpty(conttTpCd)&&conttTpCd.equals(DisplayEnum.CONTT_TP.GOD.toString())) {
					scNo = dspCnrContt.getGodNo();
					key = "GOD_NO";
					
					//전시카테고리에 연결된 상품인지 체크
					int checkCnt = displayCornerConttRepository.selectCtgryCnncGodCnt(scNo);
					if(checkCnt == 0) {						
						checkGod = false;
						nonNo += scNo + ",";
					}
				// 20160516_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 스티커 상품 처리]
				}else if(StringService.isNotEmpty(conttTpCd)&&conttTpCd.equals(DisplayEnum.CONTT_TP.STK_GOD.toString())) {
					scNo = dspCnrContt.getGodNo();
					key = "GOD_NO";
					
					//전시카테고리에 연결된 상품인지 체크
					int checkCnt = displayCornerConttRepository.selectCtgryCnncGodCnt(scNo);
					if(checkCnt == 0) {						
						checkGod = false;
						nonNo += scNo + ",";
					}
				}
				else if(StringService.isNotEmpty(conttTpCd)&&conttTpCd.equals(DisplayEnum.CONTT_TP.PROMT.toString())) {
					scNo = dspCnrContt.getPromtSn() + "";
					key = "PROMT_SN";
				}
				else if(StringService.isNotEmpty(conttTpCd)&&conttTpCd.equals(DisplayEnum.CONTT_TP.STRND.toString())) {
					scNo = dspCnrContt.getStrndSn()  + "";
					key = "STRND_SN";
				}
				
				
				
				for(Map<String,Object> map: cnncedList) {
					if((map.get(key).toString()).equals(scNo)) {
						dup = true;
						break;
					}
				}
				
				if(StringService.isNotEmpty(scNo) && dup) {
					dupNo += scNo + ",";
				} else {
					if(checkGod) {
						//컨텐트 순번
						Integer conttTurn = displayCornerConttRepository.getConttTurn(dspCnrContt);
						dspCnrContt.setConttTurn(conttTurn);
						
						dspCnrContt.setRegtrId(loginId);
						dspCnrContt.setUdterId(loginId);
						
						DspCnrContt resDspCnrContt = this.setDefaultDspCnrContt(dspCnrContt);
						displayCornerConttRepository.insertCornerContent(resDspCnrContt);
						
						
						//전시대상 설정 디폴트 insert
						DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO = new DspCnrConttDspTgtBoDTO();
						dspCnrConttDspTgtBoDTO.setScRevSn(dspCnrContt.getRevSn());
						dspCnrConttDspTgtBoDTO.setScCnrSn(dspCnrContt.getCnrSn());
						dspCnrConttDspTgtBoDTO.setScCnrSetSn(dspCnrContt.getCnrSetSn());
						List<Integer> conttTurnList = new ArrayList<Integer> ();
						conttTurnList.add(dspCnrContt.getConttTurn());
						dspCnrConttDspTgtBoDTO.setScConttTurn(conttTurnList);
						dspCnrConttDspTgtBoDTO.setLang(dspCnrConttScBoDTO.getLang());
						dspCnrConttDspTgtBoDTO.setMallId(dspCnrConttScBoDTO.getMallId());
						
						saveContentDspTgt(dspCnrConttDspTgtBoDTO);
						
						insCnt++;
					}
				}
			}
			
			result[0] = dupNo ; //중복 
			if(!nonNo.equals("")) result[0] += "##" + nonNo;  //전시카테고리에 연결되지 않은 상품
			result[1] = ""+insCnt ;  // 등록 건수
		}
		 
		return result;
	}
	
	
	/**
	 * 컨텐츠 전시대상 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttScBoDTO [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public Integer insertCnrConttDspTgtInfo (DspCnrConttScBoDTO dspCnrConttScBoDTO) throws Exception {
		
		String loginId = BOSecurityUtil.getLoginId();
		Integer dspTgrTurn = null;
		DspCnrConttDspTgt dspCnrConttDspTgt;
		
		if(dspCnrConttScBoDTO != null) {
			dspCnrConttDspTgt = dspCnrConttScBoDTO.getDspCnrConttDspTgt();
			dspCnrConttDspTgt.setRegtrId(loginId);
			dspCnrConttDspTgt.setUdterId(loginId);
				
			dspTgrTurn = displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
			
		}
		
		return dspTgrTurn;
	}
	
	/**
	 * 컨텐츠 전시대상 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttScBoDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 15
	 */
	public void updateCnrConttDspTgtInfo (DspCnrConttScBoDTO dspCnrConttScBoDTO) throws Exception {
		
		String loginId = BOSecurityUtil.getLoginId();
		DspCnrConttDspTgt dspCnrConttDspTgt;
		
		if(dspCnrConttScBoDTO != null) {
			dspCnrConttDspTgt = dspCnrConttScBoDTO.getDspCnrConttDspTgt();
			dspCnrConttDspTgt.setUdterId(loginId);
				
			displayCornerConttDspTgtRepository.updateDspCnrConttDspTgrInfo(dspCnrConttDspTgt);
			
		}
	}
	
	/**
	 * 코너 컨텐츠 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param list [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 13
	 */
	public void deleteCornerContent(List<DspCnrConttScBoDTO> list) throws Exception {
		DspCnrContt dspCnrContt = null;
		
		if(list != null) {
			DspCnrConttScBoDTO dto = list.get(0);
			String scPrvwSn = dto.getScPrvwSn();
			Long revSn = null;

			for(DspCnrConttScBoDTO dspCnrConttScBoDTO: list) {
				dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();
				
				//임시저장일 경우 미리보기 리비전으로 set
				if(StringService.isNotEmpty(scPrvwSn)) {
					revSn = Long.parseLong(scPrvwSn);
				}
				else {
					revSn = dspCnrContt.getRevSn();
				}
				dspCnrContt.setRevSn(revSn);

				//컨텐츠 언어 삭제
				DspCnrConttLang lang = new DspCnrConttLang();
				lang.setRevSn(dspCnrContt.getRevSn());
				lang.setCnrSn(dspCnrContt.getCnrSn());
				lang.setCnrSetSn(dspCnrContt.getCnrSetSn());
				lang.setConttTurn(dspCnrContt.getConttTurn());
				
				displayCornerConttRepository.deleteDspCnrConttLangInfo(lang);
				
				//컨텐츠 전시대상 삭제
				DspCnrConttDspTgt tgt = new DspCnrConttDspTgt();
				tgt.setCnrSetSn(dspCnrContt.getCnrSetSn());
				tgt.setCnrSn(dspCnrContt.getCnrSn());
				tgt.setRevSn(dspCnrContt.getRevSn());
				tgt.setConttTurn(dspCnrContt.getConttTurn());
				
				displayCornerConttDspTgtRepository.deleteDspCnrConttDspTgtInfo(tgt);
				
				//컨텐츠 삭제
				displayCornerConttRepository.deleteDspCnrContt(dspCnrContt);
			}
		}
	}
	
	
	/**
	 * 코너 세트에 연결되어있는 상품번호 목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 20
	 */
	public List<Map<String,Object>> selectCnrConttCnncedList(DspCnrConttExt dspCnrContt) throws Exception {
		return displayCornerConttRepository.selectCnrConttCnncedList(dspCnrContt);
	}
	
	
	/**
	 * 코너 컨텐츠 전시대상 설정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrConttDspTgtBoDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 21
	 */
	public void saveContentDspTgt (DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO) throws Exception {
		
		Long revSn = dspCnrConttDspTgtBoDTO.getScRevSn();
		Long cnrSn = dspCnrConttDspTgtBoDTO.getScCnrSn();
		Long cnrSetSn = dspCnrConttDspTgtBoDTO.getScCnrSetSn();
		List<Integer> conttTurnList = dspCnrConttDspTgtBoDTO.getScConttTurn();
		String loginId = BOSecurityUtil.getLoginId();
		String lang = dspCnrConttDspTgtBoDTO.getLang();
		String mallId = dspCnrConttDspTgtBoDTO.getMallId();
		
		if(conttTurnList != null) {
			for(Integer conttTurn: conttTurnList) {
				DspCnrConttDspTgt dspCnrConttDspTgt = new DspCnrConttDspTgt();
				dspCnrConttDspTgt.setRevSn(revSn);
				dspCnrConttDspTgt.setCnrSn(cnrSn);
				dspCnrConttDspTgt.setCnrSetSn(cnrSetSn);
				dspCnrConttDspTgt.setConttTurn(conttTurn);
				dspCnrConttDspTgt.setRegtrId(loginId);
				dspCnrConttDspTgt.setUdterId(loginId);
				
				//해당 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 설정되어 있는 전시대상 삭제
				displayCornerConttDspTgtRepository.deleteDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
				
				//MALL_ID
				dspCnrConttDspTgt.setDspTgtTpCd(DisplayEnum.DSP_TGT_TP.MALL_ID.toString());
				if(StringService.isNotEmpty(mallId)) {
					dspCnrConttDspTgt.setMallId(mallId);
				} else {
					dspCnrConttDspTgt.setMallId(DisplayEnum.MALL.toString());
				}				
				displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
				dspCnrConttDspTgt.setMallId("");
				
				//LANG_CD
				List<String> langList = dspCnrConttDspTgtBoDTO.getArLangCd();
				dspCnrConttDspTgt.setDspTgtTpCd(DisplayEnum.DSP_TGT_TP.LANG.toString());
				
				if(langList!=null && langList.size() > 0) {
					for(String langCd: langList) {
						dspCnrConttDspTgt.setLangCd(langCd);
						//해단 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 신규 insert
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
					}
				} else {
					for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.LANG.toString(), lang)) {
						dspCnrConttDspTgt.setLangCd(cd.getCd());
						//해단 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 신규 insert
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
		            }
					
				}
				dspCnrConttDspTgt.setLangCd("");
				
				//DVC_CD
				List<String> dvc = dspCnrConttDspTgtBoDTO.getArDvcCd();
				dspCnrConttDspTgt.setDspTgtTpCd(DisplayEnum.DSP_TGT_TP.DVC.toString());
				if(dvc!=null && dvc.size() > 0) {
					for(String dvcCd: dvc) {
						dspCnrConttDspTgt.setDvcCd(dvcCd);
						//해단 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 신규 insert
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
					}
				} else {
					for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.DVC.toString(), lang)) {
						dspCnrConttDspTgt.setDvcCd(cd.getCd());
						//해단 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 신규 insert
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
		            }
					
				}
				dspCnrConttDspTgt.setDvcCd("");
				
				//TGT_MBR_ATRB_CD
				List<String> tgtMbrAtrb = dspCnrConttDspTgtBoDTO.getArTgtMbrAtrbCd();
				dspCnrConttDspTgt.setDspTgtTpCd(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString());
				
				String tgtGrpco = dspCnrConttDspTgtBoDTO.getTgtGrpco();
				if(tgtMbrAtrb!=null && tgtMbrAtrb.size() > 0) {
					if(tgtMbrAtrb!=null&&tgtMbrAtrb.contains("GRPCO")) {
						tgtMbrAtrb.remove("GRPCO");
						tgtMbrAtrb.add(tgtGrpco);
					}
					
					for(String tgtMbrAtrbCd: tgtMbrAtrb) {
						dspCnrConttDspTgt.setTgtMbrAtrbCd(tgtMbrAtrbCd);
						
						if(tgtMbrAtrbCd.equals("GRPCO_IND")) {
							List<DspCnrConttDspTgtBoDTO> grpCoList = dspCnrConttDspTgtBoDTO.getGrpCoList();
							
							if(grpCoList!=null) {
								for(DspCnrConttDspTgtBoDTO tgtDTO: grpCoList) {
									DspCnrConttDspTgt tgt = tgtDTO.getDspCnrConttDspTgt();
									//String cud = tgtDTO.getGridCudTag();
									//if(StringService.isNotEmpty(cud)&&!"D".equals(cud)) {
										dspCnrConttDspTgt.setGrpcoId(tgt.getGrpcoId());
										
										displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
									//}
								}
							}
						} else {
							//해단 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 신규 insert
							displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
						}
					}
				}
				else {
					for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB_DATA.toString(), lang)) {
						dspCnrConttDspTgt.setTgtMbrAtrbCd(cd.getCd());
						//해단 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 신규 insert
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
		            }
					
				}
				dspCnrConttDspTgt.setTgtMbrAtrbCd("");
				dspCnrConttDspTgt.setGrpcoId("");
				
				//TGT_MBR_TP_CD
				List<String> tgtMbrTp = dspCnrConttDspTgtBoDTO.getArTgtMbrTpCd();
				dspCnrConttDspTgt.setDspTgtTpCd(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString());
				if(tgtMbrTp!=null && tgtMbrTp.size() > 0) {
					for(String tgtMbrTpCd: tgtMbrTp) {
						dspCnrConttDspTgt.setTgtMbrTpCd(tgtMbrTpCd);
						//해단 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 신규 insert
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
					}
				}
				else {
					for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(), lang)) {
						dspCnrConttDspTgt.setTgtMbrTpCd(cd.getCd());
						//해단 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 신규 insert
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
		            }
				}
				dspCnrConttDspTgt.setTgtMbrTpCd("");
			}
		}
	}
	
	/**
	 * Valid content lang dsp tgt.
	 *
	 * @param dspCnrConttDspTgtBoDTO the dsp cnr contt dsp tgt bo dto
	 * @throws Exception the exception
	 */
	public void validContentLangDspTgt (DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO)  {
		
		Long revSn = dspCnrConttDspTgtBoDTO.getScRevSn();
		Long cnrSn = dspCnrConttDspTgtBoDTO.getScCnrSn();
		Long cnrSetSn = dspCnrConttDspTgtBoDTO.getScCnrSetSn();
		List<Integer> conttTurnList = dspCnrConttDspTgtBoDTO.getScConttTurn();
		String lang = dspCnrConttDspTgtBoDTO.getLang();
		
		if(conttTurnList != null) {
			for(Integer conttTurn: conttTurnList) {
				DspCnrConttDspTgt dspCnrConttDspTgt = new DspCnrConttDspTgt();
				dspCnrConttDspTgt.setRevSn(revSn);
				dspCnrConttDspTgt.setCnrSn(cnrSn);
				dspCnrConttDspTgt.setCnrSetSn(cnrSetSn);
				dspCnrConttDspTgt.setConttTurn(conttTurn);
				
				//LANG_CD -- checked list
				List<String> langList = dspCnrConttDspTgtBoDTO.getArLangCd();
				
				String scConttTpCd = dspCnrConttDspTgtBoDTO.getScConttTpCd();
				if(StringService.isNotEmpty(scConttTpCd) && 
						( scConttTpCd.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())
						||scConttTpCd.equals(DisplayEnum.CONTT_TP.HTML.toString()))) {					
					List<DspCnrConttLang> langContt = displayCornerConttDspTgtRepository.selectValidConttForLang(dspCnrConttDspTgt);
					String contents = "";
					boolean valid = true;
					for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.LANG.toString(), lang)) {
						if(langList.contains(cd.getCd())) { //checked : 컨텐츠가 없으면 false
							boolean bContain = false;
							for(DspCnrConttLang contt : langContt) {
								if((cd.getCd()).equals(contt.getLangCd())) {
									bContain = true;
									break;
								}								
							}	
							for(DspCnrConttLang contt : langContt) {
								if(scConttTpCd.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
									contents = contt.getImgFileNm();
								}
								else if(scConttTpCd.equals(DisplayEnum.CONTT_TP.HTML.toString())) {
									contents = contt.getHtmlCont();
								}	
								if(!bContain || ((cd.getCd()).equals(contt.getLangCd()) && StringService.isEmpty(contents))) {
									valid = false;
								}								
							}
						}
						else { //unchecked : 컨텐츠가 있으면 false
							for(DspCnrConttLang contt : langContt) {
								if(scConttTpCd.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
									contents = contt.getImgFileNm();
								}
								else if(scConttTpCd.equals(DisplayEnum.CONTT_TP.HTML.toString())) {
									contents = contt.getHtmlCont();
								}
								if((cd.getCd()).equals(contt.getLangCd()) && StringService.isNotEmpty(contents)) {
									valid = false;
								}
							}
						}
						
						if(!valid) {
							break;
						}
					}
					
					if(!valid) {
						throw new DspConttValidException(null);
					}
				}
			}
		}
	}
	
	/**
	 * Adds the content dsp tgt.
	 *
	 * @param dspCnrConttDspTgtBoDTO the dsp cnr contt dsp tgt bo dto
	 * @throws Exception the exception
	 */
	public void addContentDspTgt (DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO) throws Exception {
		
		String loginId = BOSecurityUtil.getLoginId();
		
		DspCnrConttDspTgt dspCnrConttDspTgt = dspCnrConttDspTgtBoDTO.getDspCnrConttDspTgt();
		
		if(dspCnrConttDspTgt != null) {
			dspCnrConttDspTgt.setRegtrId(loginId);
			dspCnrConttDspTgt.setUdterId(loginId);

			//해당 컨텐츠(cnrSn, cnrSetSn, conttTurn)에 설정되어 있는 전시대상 삭제
			displayCornerConttDspTgtRepository.deleteDspCnrConttDspTgtTp(dspCnrConttDspTgt);
			
			if(DisplayEnum.DSP_TGT_TP.MALL_ID.toString().equals(dspCnrConttDspTgt.getDspTgtTpCd())) {
				//MALL_ID
				String mallId = dspCnrConttDspTgtBoDTO.getMallId();
				if(StringService.isNotEmpty(mallId)) {
					dspCnrConttDspTgt.setMallId(mallId);
				} else {
					dspCnrConttDspTgt.setMallId(DisplayEnum.MALL.toString());
				}				
				displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
			}
			else if(DisplayEnum.DSP_TGT_TP.LANG.toString().equals(dspCnrConttDspTgt.getDspTgtTpCd())) {
				//LANG_CD
				List<String> arTgtLangCd = dspCnrConttDspTgtBoDTO.getArLangCd();
				
				if(arTgtLangCd!=null && arTgtLangCd.size() > 0) {
					for(String langCd: arTgtLangCd) {
						dspCnrConttDspTgt.setLangCd(langCd);
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
					}
				} 
			}
			else if(DisplayEnum.DSP_TGT_TP.DVC.toString().equals(dspCnrConttDspTgt.getDspTgtTpCd())) {
				//DVC_CD
				List<String> dvc = dspCnrConttDspTgtBoDTO.getArDvcCd();
				dspCnrConttDspTgt.setDspTgtTpCd(DisplayEnum.DSP_TGT_TP.DVC.toString());
				if(dvc!=null && dvc.size() > 0) {
					for(String dvcCd: dvc) {
						dspCnrConttDspTgt.setDvcCd(dvcCd);
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
					}
				} 
			}
			else if(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString().equals(dspCnrConttDspTgt.getDspTgtTpCd())) {
				//TGT_MBR_ATRB_CD
				List<String> tgtMbrAtrb = dspCnrConttDspTgtBoDTO.getArTgtMbrAtrbCd();
				
				String tgtGrpco = dspCnrConttDspTgtBoDTO.getTgtGrpco();
				if(tgtMbrAtrb!=null && tgtMbrAtrb.size() > 0) {
					if(tgtMbrAtrb!=null&&tgtMbrAtrb.contains("GRPCO")) {
						tgtMbrAtrb.remove("GRPCO");
						tgtMbrAtrb.add(tgtGrpco);
					}
					
					for(String tgtMbrAtrbCd: tgtMbrAtrb) {
						dspCnrConttDspTgt.setTgtMbrAtrbCd(tgtMbrAtrbCd);
						
						if(tgtMbrAtrbCd.equals("GRPCO_IND")) {
							List<DspCnrConttDspTgtBoDTO> grpCoList = dspCnrConttDspTgtBoDTO.getGrpCoList();
							
							if(grpCoList!=null) {
								for(DspCnrConttDspTgtBoDTO tgtDTO: grpCoList) {
									DspCnrConttDspTgt tgt = tgtDTO.getDspCnrConttDspTgt();
									dspCnrConttDspTgt.setGrpcoId(tgt.getGrpcoId());
										
									displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
								}
							}
						} else {
							displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
						}
					}
				}
			}
			else if(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString().equals(dspCnrConttDspTgt.getDspTgtTpCd())) {
				//TGT_MBR_TP_CD
				List<String> tgtMbrTp = dspCnrConttDspTgtBoDTO.getArTgtMbrTpCd();
				if(tgtMbrTp!=null && tgtMbrTp.size() > 0) {
					for(String tgtMbrTpCd: tgtMbrTp) {
						dspCnrConttDspTgt.setTgtMbrTpCd(tgtMbrTpCd);
						displayCornerConttDspTgtRepository.insertDspCnrConttDspTgtInfo(dspCnrConttDspTgt);
					}
				}
			}
		}		
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrContt [설명]
	 * @return Dsp cnr contt [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 24
	 */
	 private DspCnrContt setDefaultDspCnrContt (DspCnrContt dspCnrContt) throws Exception {
		DspCnrContt resDspCnrContt = dspCnrContt;
		
		//전시순서 - 999
		if(dspCnrContt.getSortSeq()==null || dspCnrContt.getSortSeq() == 0) {
			resDspCnrContt.setSortSeq(Integer.parseInt(DisplayEnum.DF_SORT_SEQ.toString()));
		}
		//전시여부 - 미전시
		if(StringService.isEmpty(dspCnrContt.getDspYn())) {
			resDspCnrContt.setDspYn(DisplayEnum.NO.toString());
		}
		//전시기간 - 전시시작일시: 오늘날짜+00:00:00
		//전시기간 - 전시종료일시: 2999.12.31 23:59:59
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String currentDate = simpleDateFormat.format(date)+"000000";
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
		
		if(dspCnrContt.getDspBegDt() == null) {
			resDspCnrContt.setDspBegDt(simpleDateFormat2.parse(currentDate));
		}
		if(dspCnrContt.getDspEndDt() == null) {
			resDspCnrContt.setDspEndDt(simpleDateFormat2.parse(DisplayEnum.DSP_END_DATE.toString()));
		}
		
		return resDspCnrContt;
	}
	 
	/**
	 * 전시컨텐츠 이미지 목록 조회
	 * @param dspImgUploadDTO [설명]
	 * @param pageParam [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 7
	 */
	public Page<DspImgUploadResult> getImageUploadList(DspImgUploadDTO dspImgUploadDTO, PageParam pageParam) throws Exception {
	    return displayCornerConttRepository.selectImageUploadList(dspImgUploadDTO, pageParam);
	}
	
	/**
	 * 전시컨텐츠 상세 조회
	 * @param dspImgUploadDTO [설명]
	 * @return Dsp img upload result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 7
	 */
	public DspImgUploadResult detailImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception {
	    return displayCornerConttRepository.detailImageUpload(dspImgUploadDTO);
	}
	
	/**
	 * 전시컨텐츠 이미지 목록 조회
	 * @param dspImgUploadDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 7
	 */
	public List<DspImgUploadResult> selectImageList(DspImgUploadDTO dspImgUploadDTO) throws Exception {
	    return displayCornerConttRepository.selectImageList(dspImgUploadDTO);
	}
	
	/**
	 * 전시컨텐츠 이미지 등록
	 * @param session1 [설명]
	 * @param dspImgUploadDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 7
	 */
	public List<String> insertImageUpload(SqlSession session1, DspImgUploadDTO dspImgUploadDTO) throws Exception {
	    
	    // 컨텐츠 번호 설정
	    dspImgUploadDTO.getDspConttImg().setConttSn(Long.parseLong(idGenService.generateDBSequence(session1, "SQ_DSP_CONTT_IMG", DatabaseType.ORACLE) + ""));
	    
        // 컨텐츠 이미지 저장
        if (dspImgUploadDTO.getUploadImage() != null && dspImgUploadDTO.getUploadImage().size() > 0) {
            return this.saveFile(session1, dspImgUploadDTO);
        }
		return null;
	}
	
	
	/**
	 * 전시컨텐츠 이미지 업로드 목록 수정(추가)
	 * @param session1 [설명]
	 * @param dspImgUploadDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<String> updateImageUpload(SqlSession session1, DspImgUploadDTO dspImgUploadDTO) throws Exception {
	    if(dspImgUploadDTO.getUploadImage() != null && dspImgUploadDTO.getUploadImage().size() > 0) {
	        return this.saveFile(session1, dspImgUploadDTO);
	    }
	    return null;
	}
	
	/**
	 * 전시컨텐츠 이미지 업로드 파일명 존재 여부 확인
	 * @param dspImgUploadDTO
	 * @return
	 * @throws Exception
	 * @since 2017. 05. 18
	 */
	public Boolean isExistFile(DspImgUploadDTO dspImgUploadDTO) throws Exception {
		String evtNo = dspImgUploadDTO.getEvtNo() == null ? "": dspImgUploadDTO.getEvtNo();

		String imageUploadMiddlePath = "";
		if(StringService.isNotEmpty(evtNo)){
			imageUploadMiddlePath = imageUploadMiddlePathForErpEvent("/", dspImgUploadDTO.getDspConttImg().getTmplatTpCd(), evtNo);
		}else{
			imageUploadMiddlePath = imageUploadMiddlePath("/", dspImgUploadDTO.getDspConttImg().getTmplatTpCd());
		}
		dspImgUploadDTO.getDspConttImg().setImgFileUrl(htmlImageHttpPath + imageUploadMiddlePath);   
		
		return displayCornerConttRepository.selectImageCnt(dspImgUploadDTO) > 0 ? true : false;
	}
	
	/**
	 * 첨부파일 업로드.
	 *
	 * @param files [설명]
	 * @return File upload result [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 7
	 */
	public FileUploadResult uploadFiles(List<MultipartFile> files, String saveFilePath) throws Exception {
		// 전체 업로드 확장자 체크를 한다.
       // excludeExtensions 포함된 데이터는 업로드 할 수 없다.
       String[] excludeExtensions = { "jsp", "php", "exe", "bat" };
       // 시스템 파일명을 자동으로 부여하여 업로드한다.
       	FileUploadResult fileUploadResult = getIoService().fileUploadOverWrite(files, saveFilePath, excludeExtensions);
		return fileUploadResult;
		
   }
	
	public String imageUploadMiddlePath(String separator, String tmplatTpCd) throws Exception {
	    String imageUploadMiddlePath = separator + tmplatTpCd + separator +
                DateService.getStringCurrentYear() +  DateService.getStringCurrentMonth() + DateService.getStringCurrentDay() + separator;
	    return imageUploadMiddlePath;
	}

	/**
	 * ERP이벤트용 이미지 경로
	 * @param separator
	 * @param tmplatTpCd
	 * @return
	 * @throws Exception
	 */
	public String imageUploadMiddlePathForErpEvent(String separator, String tmplatTpCd, String evtNo) throws Exception {
		String imageUploadMiddlePath = separator + tmplatTpCd + separator + StringService.deleteWhitespace(evtNo) + separator;
		return imageUploadMiddlePath;
	}
	
	/**
	 * 첨부파일 DB 저장.
	 *
	 * @param session1 [설명]
	 * @param dspImgUploadDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 31
	 */
    private List<String> saveFile(SqlSession session1, DspImgUploadDTO dspImgUploadDTO) throws Exception {
    	List<String> alreadySavedFileNames = new ArrayList<String>();
		String evtNo = dspImgUploadDTO.getEvtNo() == null ? "": dspImgUploadDTO.getEvtNo();

		String imageUploadMiddlePath = "";
		if(StringService.isNotEmpty(dspImgUploadDTO.getEvtNo())){
			imageUploadMiddlePath = imageUploadMiddlePathForErpEvent("/", dspImgUploadDTO.getDspConttImg().getTmplatTpCd(), dspImgUploadDTO.getEvtNo());
		}else{
			imageUploadMiddlePath = imageUploadMiddlePath("/", dspImgUploadDTO.getDspConttImg().getTmplatTpCd());
		}
		
		//전체 파일 존재 유무 체크
		List<MultipartFile> orgFiles = dspImgUploadDTO.getUploadImage();
		DspImgUploadDTO checkExistFileDTO = new DspImgUploadDTO();
		checkExistFileDTO.getDspConttImg().setImgFileUrl(htmlImageHttpPath + imageUploadMiddlePath);
		checkExistFileDTO.getDspConttImg().setImgSjNm(dspImgUploadDTO.getDspConttImg().getImgSjNm());
		for (MultipartFile item : orgFiles) {
			checkExistFileDTO.getDspConttImg().setImgFileNm(item.getOriginalFilename());
			
			if(displayCornerConttRepository.selectImageCnt(checkExistFileDTO) > 0){
				alreadySavedFileNames.add(item.getOriginalFilename());
			}
		}
		
		if (alreadySavedFileNames.size() > 0) {
			return alreadySavedFileNames;
		}
		
		//파일업로드
		FileUploadResult fileUploadResult = uploadFiles(dspImgUploadDTO.getUploadImage(), htmlImageUploadPath + imageUploadMiddlePath);

        if (fileUploadResult != null && fileUploadResult.getFileCnt() > 0) {
            for (FileUploadInfo item : fileUploadResult.getRows()) {
                Map<String, Object> conditions = Maps.newHashMap();
                conditions.put("contt_sn", dspImgUploadDTO.getDspConttImg().getConttSn());
                int maxTurn = Integer.parseInt(idGenService.generateDBOrder(session1, "DSP_CONTT_IMG", "CONTT_IMG_TURN", conditions, DatabaseType.ORACLE) + "");
                dspImgUploadDTO.getDspConttImg().setConttImgTurn(maxTurn);
                
                dspImgUploadDTO.getDspConttImg().setImgFileUrl(htmlImageHttpPath + imageUploadMiddlePath);
                dspImgUploadDTO.getDspConttImg().setImgFileNm(item.getOrgFileName() + "." + item.getExtension());
                
                dspConttImgRepository.insertDspConttImg(dspImgUploadDTO.getDspConttImg());   
               
            }
        }
        
        return alreadySavedFileNames;
    }
    
    public List<DspCnrConttExt> updateSortSeqCnrConttGod(List<DspCnrContt> list, Long cnrSn, Long cnrSetSn, Long revSn) throws Exception {
	    List<DspCnrConttExt> errList = null;
	    if (list != null) {
	        String loginId = BOSecurityUtil.getLoginId();
	        
	        int res = 0;
	        errList = new ArrayList<DspCnrConttExt>();
	        for(DspCnrContt dspCnrContt: list) {
	            dspCnrContt.setUdterId(loginId);
	            dspCnrContt.setCnrSn(cnrSn);
	            dspCnrContt.setCnrSetSn(cnrSetSn);
	            dspCnrContt.setRevSn(revSn);

	            res = displayCornerConttRepository.updateSortSeqCnrConttGod(dspCnrContt);
	            if(res < 1) {
	                DspCnrConttExt dspCnrConttExt = new DspCnrConttExt();
	                dspCnrConttExt.setGodNo(dspCnrContt.getGodNo());
	                dspCnrConttExt.setSortSeq(dspCnrContt.getSortSeq());
	                dspCnrConttExt.setErrMsg("등록되지 않은 온라인품번입니다.");
	                errList.add(dspCnrConttExt);
	            }
	            
	        }
	    }
	    return errList;
	}

	/**
	 * 코너 컨텐츠 언어정보 조회
	 * @param dspCnrConttLang
	 * @return
	 * @throws Exception
	 */
	public DspCnrConttLang selectDspCnrConttLang(DspCnrConttLang dspCnrConttLang) throws Exception{
		return dspCnrConttLangRepository.selectDspCnrConttLang(dspCnrConttLang);
	}

}
