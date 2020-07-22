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
 * @since       2015. 8. 4       
 */
package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndImg;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndLang;
import com.plgrim.ncp.base.repository.sys.SysBrndImgRepository;
import com.plgrim.ncp.commons.data.FormSysBrndDTO;
import com.plgrim.ncp.commons.data.SysBrandDTO;
import com.plgrim.ncp.commons.repository.SysBrandRepository;
import com.plgrim.ncp.commons.result.BrndTagCdResult;
import com.plgrim.ncp.commons.result.ErpBrndIdResult;
import com.plgrim.ncp.commons.result.SysBrandResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SysBrandService extends AbstractService { 

	@Autowired
	SysBrandRepository sysBrandRepository;

	@Autowired
	SysBrndImgRepository sysBrndImgRepository;
	
	/**
	 * 인터페이스 API 어댑터 
	 */
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
	/**
	 * 시스템 브랜드의 임직원할인율 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 4
	 */
	public void updateSysBrndEmpDcRt(SysBrnd sysBrnd) throws Exception {
		sysBrandRepository.updateSysBrndEmpDcRt(sysBrnd);
	}
	
	/**
	 * 시스템 브랜드의 임직원할인율 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @return Big decimal [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 4
	 */
	public BigDecimal selectEmpPrcDcRt(SysBrnd sysBrnd) throws Exception {
		return sysBrandRepository.selectEmpPrcDcRt(sysBrnd);
	}

	/**
	 * 브랜드별 포인트적립률 목록 조회
	 * 
	 * @param sysBrandDTO
	 * @return
	 * @throws Exception
	 */
	public List<SysBrandResult> selectSysBrandPntAccmlList(SysBrandDTO sysBrandDTO) throws Exception {
		return sysBrandRepository.selectSysBrandPntAccmlList(sysBrandDTO);
	}

	/**
	 * 브랜드별 포인트적립률 목록 TotalCount
	 * 
	 * @param sysBrandDTO
	 * @return
	 * @throws Exception
	 */
	public int selectSysBrandPntAccmlTotalCount(SysBrandDTO sysBrandDTO) throws Exception {
		return sysBrandRepository.selectSysBrandPntAccmlTotalCount(sysBrandDTO);
	}
	
	/**
	 * 브랜드별 포인트적립률 목록 엑셀다운로드
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrandDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 8
	 */
	public List<Map<String, Object>> selectSysBrandPntAccmlListByAll(SysBrandDTO sysBrandDTO) throws Exception {
		return sysBrandRepository.selectSysBrandPntAccmlListByAll(sysBrandDTO);
	}
	
	/**
	 * 포인트적립률 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 8
	 */
	public void updateSysBrndPntAccml(SysBrnd sysBrnd) throws Exception {
		sysBrandRepository.updateSysBrndPntAccml(sysBrnd);
	}
	
	/**
	 * 포인트 적립률 수정 시 상품정보 update..
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 24
	 */
	public void updatePntAccmlGod(SysBrnd sysBrnd) throws Exception {
		sysBrandRepository.updatePntAccmlGod(sysBrnd);
	}
	/**
	 * 브랜드코드관리 > 브랜드목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysBrandResult> selectListSysBrnd(FormSysBrndDTO paramData) throws Exception {
		return sysBrandRepository.selectListSysBrnd(paramData);
	}
	
	/**
	 * 브랜드코드관리 > 자식 브랜드 목록 조회
	 * @param paramData (selBrndId 필수)
	 * @return
	 * @throws Exception
	 */
	public List<SysBrandResult> selectListChildSysBrnd(FormSysBrndDTO paramData) throws Exception {
		return sysBrandRepository.selectListChildSysBrnd(paramData);
	}
	
	/**
	 * 브랜드코드관리 > 자식 브랜드 정보 조회
	 * @param paramData (selBrndId 필수)
	 * @return
	 * @throws Exception
	 */
	public SysBrandResult selectRowSysBrnd(FormSysBrndDTO paramData) throws Exception {
		SysBrandResult sysBrandResult = sysBrandRepository.selectRowSysBrnd(paramData);

		List<SysBrndImg> sysBrndImgList = sysBrandRepository.selectRowSysBrndImg(paramData.getSelBrndId());

		if(sysBrandResult != null) {
			sysBrandResult.setSysBrndImgList(sysBrndImgList);
		}

		return sysBrandResult;
	}
	
	
	/**
	 * 브랜드코드관리 > 브랜드코드 사용 횟수 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public int checkUseCntFromBrndId(FormSysBrndDTO paramData) throws Exception {
		return sysBrandRepository.checkUseCntFromBrndId(paramData);
	}
	
	/**
	 * 인터페이스 브랜드ID 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<ErpBrndIdResult> selectListErpBrndId(FormSysBrndDTO paramData) throws Exception {
		return sysBrandRepository.selectListErpBrndId(paramData);
	}
	
	/**
	 * 인터페이스 브랜드 tag 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<BrndTagCdResult> selectListBrndTagCd(FormSysBrndDTO paramData, SystemPK systemPk) throws Exception {
//		BrndTagSDO bt = new BrndTagSDO();
//
//		// Parameter setting
//		bt.setSelErpBrndId(paramData.getSelErpBrndId());
//		bt.setSearchField(paramData.getSearchField());
//		bt.setSearchText(paramData.getSearchText());
//
//		BrndTagSDO rsltBt = brndTagInfoSplitAdapter.getBrndTagList(bt, this.setHeader(systemPk)); // 브랜드 태그 조회
//
		List<BrndTagCdResult> list = new ArrayList<>();
//
//		// 조회된 태그 리스트가 존재하면 리스트에 재배열
//		if(rsltBt.getList().size() > 0){
//			BrndTagCdResult btcr;
//
//			for(int i=0 ; i < rsltBt.getList().size() ; i++){
//				btcr = new BrndTagCdResult();
//
//				btcr.setBrndCd(rsltBt.getList().get(i).getErpBrndId());
//				btcr.setZztagInfo(rsltBt.getList().get(i).getBrndTagCd());
//				btcr.setZztagInfoNm(rsltBt.getList().get(i).getBrndTagNm());
//
//				list.add(btcr);
//
//				btcr = null;
//			}
//		}
//
//		//return sysBrandRepository.selectListBrndTagCd(paramData);
		return list;
	}
	
	/**
	 * beaker brand 체크 ( 1이면 beaker 브랜드 그룹 브랜드)
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public int checkBeakerBrndCount(FormSysBrndDTO paramData) throws Exception {
		return sysBrandRepository.checkBeakerBrndCount(paramData);
	}
	
	/*################################################################################################
	 * Command
	##################################################################################################*/
	
	/**
	 * 시스템 브랜드 등록
	 * @param paramData
	 * @throws Exception
	 */
	public void insertSysBrnd( SysBrndExtend paramData ) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());

		//Beaker brand 체크하여 erp brnd id를 구성한다.
		paramData.setErpBrndId( this.checkBeakerBrnd( paramData.getUpperBrndId(), paramData));
		
		//브랜드 등록
		sysBrandRepository.insertSysBrnd(paramData);

		//영어 다국어 코드명 등록
		if( paramData.getEngBrndNm() != null && !"".equals(paramData.getEngBrndNm())){
			this.mergeBlangBrndNm("ENG", paramData);
		}
		
		//중국어 다국어 코드명 등록
		if( paramData.getChiBrndNm() != null && !"".equals(paramData.getChiBrndNm())){
			this.mergeBlangBrndNm("CHI", paramData);
		}
		
		try {
			// 재고연동대상 처리를 위해 별도 테이블에 삽입
			sysBrandRepository.mergeSysBrndModifyFlag(paramData);
		} catch (Exception e) {
			// 저장에 실패하면 오류로그만 남기고 처리하도록
			log.error(e.getMessage(), e);
		}
		
	}

	/**
     * 시스템 브랜드 이미지 등록
     * */
	public void insertSysBrndImg(List<SysBrndImg> sysBrndImgList) throws Exception{
		String loginId = BOSecurityUtil.getLoginId();

		if(sysBrndImgList != null) {
			for(SysBrndImg sysBrndImg : sysBrndImgList){
				sysBrndImg.setRegtrId(loginId);
				sysBrndImg.setUdterId(loginId);

				sysBrndImgRepository.insertSysBrndImg(sysBrndImg);
			}
		}
	}

	/**
	 * 시스템 브랜드 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateSysBrnd ( SysBrndExtend paramData) throws Exception {
	    String loginId = BOSecurityUtil.getLoginId();
	    
	    //FormSysBrndDTO formParam = new FormSysBrndDTO();
	    //formParam.setSelBrndId(paramData.getBrndId());
	    
	    //수정히스트로 생성
	    /*
			Leo 트리거 생성 요청으로(2015.07.07)
			tg_sys_brnd_hist_insert 트리거가 히스토리 생성 대체
			//sysBrandRepository.insertSysBrndHist(paramData);
	     */
	    
	    //Beaker brand 체크하여 erp brnd id를 구성한다.  2015.7.30
	    paramData.setErpBrndId( this.checkBeakerBrnd( paramData.getBrndId(), paramData));
	    paramData.setUdterId(loginId);

	    SysBrnd sysBrndOld = sysBrandRepository.selectSysBrnd(paramData);
	    
	    //브랜드 기본정보 수정
	    sysBrandRepository.updateSysBrnd(paramData);
	    
	    //영문다국어 처리
	    if(StringService.isNotEmpty(paramData.getEngBrndNm())){
	        this.mergeBlangBrndNm("ENG", paramData);
	    }
	    
	    //중국어 다국어 처리		
	    if(StringService.isNotEmpty(paramData.getChiBrndNm())){
	        this.mergeBlangBrndNm("CHI", paramData);
	    }		

	    try {
	    	if (sysBrndOld != null && StringUtils.isNotEmpty(sysBrndOld.getUseYn())) {
	    		if (!StringUtils.equals(sysBrndOld.getUseYn(), paramData.getUseYn())) {	// 수정 전 현재 브랜드 사용여부와 수정 예정의 브랜드 사용여부를 비교
	    			// 값이 다르면 별도 플래그 처리, 재고연동대상 처리를 위해
	    			sysBrandRepository.mergeSysBrndModifyFlag(paramData);
	    		}
	    	}
	    } catch (Exception e) {
	    	// 저장에 실패하면 오류로그만 남기고 처리하도록
			log.error(e.getMessage(), e);
		}
	    
	}

	/**
	 * 시스템 브랜드 이미지 수정
	 * @param sysBrndImgList
	 * @throws Exception
	 */
	public void updateSysBrndImg(List<SysBrndImg> sysBrndImgList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();

		if(sysBrndImgList != null) {
			for(SysBrndImg sysBrndImg : sysBrndImgList){
				sysBrndImg.setRegtrId(loginId);
				sysBrndImg.setUdterId(loginId);

				sysBrandRepository.mergeSysBrndImg(sysBrndImg);
			}
		}
	}


	public void deleteSysBrndImg(List<SysBrndImg> paramList) throws Exception{
		if(paramList != null) {
			for(SysBrndImg sysBrndImg : paramList){
				sysBrndImgRepository.deleteSysBrndImg(sysBrndImg);
			}
		}
	}

	/*################################################################################
	 * Private Function 
	 ###################################################################################*/
	/**
	 * 시스템 브랜드 다국어 등록/수정
	 * @param paramData
	 * @throws Exception
	 */
	private void mergeBlangBrndNm ( String langCd ,  SysBrndExtend paramData) throws Exception {
		
		if( "ENG".equals(langCd) ){
			//영어 다국어 코드명 수정
			if( paramData.getEngBrndNm() != null && !"".equals(paramData.getEngBrndNm()) ){
				SysBrndLang sysBrndLang = new SysBrndLang();
				sysBrndLang.setBrndId(paramData.getBrndId());
				sysBrndLang.setLangCd(langCd);
				sysBrndLang.setBrndTagNm(paramData.getBrndTagNm());
				sysBrndLang.setBrndNm(paramData.getEngBrndNm());
				sysBrndLang.setBrndDscr(paramData.getBrndDscr());
				sysBrndLang.setBrndImgUrl(paramData.getBrndImgUrl());
				sysBrndLang.setBrndStoryIntrcnCont(paramData.getEngBrndStoryIntrcnCont());

				sysBrandRepository.mergeBlangBrndNm(sysBrndLang);

//				SysMlang engMlang = new SysMlang();
//				engMlang.setMlangSn(paramData.getEngMlangSn()); //등록이면 null 값이 설정됨
//				engMlang.setMlangIdxId( "SYS_BRND.BRND_NM.ENG" );
//				engMlang.setMlangPkNo(paramData.getBrndId());
//				engMlang.setMlangShrtDataNm(paramData.getEngBrndNm());
//				sysBrandRepository.mergeMlangBrndNm(chiMlang);

			}
		}
		else if( "CHI".equals(langCd) ){
			//중국어 다국어 코드명 등록
			if( paramData.getChiBrndNm() != null && !"".equals(paramData.getChiBrndNm())){
				SysBrndLang sysBrndLang = new SysBrndLang();
				sysBrndLang.setBrndId(paramData.getBrndId());
				sysBrndLang.setLangCd(langCd);
				sysBrndLang.setBrndTagNm(paramData.getBrndTagNm());
				sysBrndLang.setBrndNm(paramData.getChiBrndNm());
				sysBrndLang.setBrndDscr(paramData.getBrndDscr());
				sysBrndLang.setBrndImgUrl(paramData.getBrndImgUrl());
				sysBrndLang.setBrndStoryIntrcnCont(paramData.getChiBrndStoryIntrcnCont());
				
				sysBrandRepository.mergeBlangBrndNm(sysBrndLang);

//				SysMlang chiMlang = new SysMlang();
//				chiMlang.setMlangSn(paramData.getChiMlangSn());  //등록이면 null 값이 설정됨
//				chiMlang.setMlangIdxId("SYS_BRND.BRND_NM.CHI" );
//				chiMlang.setMlangPkNo(paramData.getBrndId());
//				chiMlang.setMlangShrtDataNm(paramData.getChiBrndNm());
//				sysBrandRepository.mergeMlangBrndNm(chiMlang);
			}
		}
	}
	
	/**
	 * BEAKER Brand group 인지 체크한다.
	 * @param paramData
	 * @return beaker brand group 이면 재구성된 erp brand id 넘겨준다. 아니면 SysBrnd.erpBrndId를 넘겨줌.
	 * @throws Exception
	 */
	private String checkBeakerBrnd ( String checkBrndId, SysBrndExtend paramData ) throws Exception {
		
		FormSysBrndDTO formParam = new FormSysBrndDTO();
		formParam.setSelBrndId( checkBrndId);
		
		//beaker 브랜드수정인지 체크 2015.7.30
		if( paramData.getErpBrndId() != null && paramData.getBrndTagCd() != null && sysBrandRepository.checkBeakerBrndCount(formParam) > 0 ){
			/*
			 재구성 
			 BEAKER (MCBR) 그룹 브랜드 일경우 erp brand id 를 재구성한다.
			 브랜드코드가 자사(2자리, 입점사는 무시)이면 인터페이스 태그인포정보가 필수항목이면 저장시 erp brand id는 아래와 같이 변경하여 저장한다.
			  erp_brand_id  = 인터페이스 브랜드코드(2자리) + 인터페이스 태그인포정보(맨뒤2자리)   구성한다.
			 */
			if( paramData.getErpBrndId().length() == 2 && paramData.getBrndTagCd().length() > 2  ){
				//자사 beaker 브랜드 이다.
				//log.debug("=============================================================");
				paramData.setErpBrndId(  paramData.getErpBrndId() +  paramData.getBrndTagCd().substring( paramData.getBrndTagCd().length()-2) );
			}
					
		}
		
		return paramData.getErpBrndId();
	}
	
	/**
	 * 포인트종류별 브랜드 적립률 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2016. 2. 1
	 */
	public void updateSysBrndPntAccmlByType(SysBrnd sysBrnd) throws Exception {
		sysBrandRepository.updateSysBrndPntAccmlByType(sysBrnd);
	}

	/**
	 * 포인트종류별 적립률 수정 시 상품정보 update..
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param sysBrnd [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 24
	 */
	public void updatePntAccmlGodByType(SysBrnd sysBrnd) throws Exception {
		sysBrandRepository.updatePntAccmlGodByType(sysBrnd);
	}
	
	/**
	 * SMS 발송용 브랜드명 구하기 (BEAKER 최상위, 이외 2레벨)
	 *  
	 * @param brndId
	 * @return
	 * @throws Exception
	 */
	public String selectBrndNm4Sms(String brndId) throws Exception {
		// SMS개선 by cannon : 2016.07.17
		return sysBrandRepository.selectBrndNm4Sms(brndId);
	}

	public String selectBrndNm4SmsByOrdNo(String ordNo) throws Exception {
		return sysBrandRepository.selectBrndNm4SmsByOrdNo(ordNo);
	}

	public String selectBrndNm4SmsByClmNo(String clmNo) throws Exception {
		return sysBrandRepository.selectBrndNm4SmsByClmNo(clmNo);
	}
	
	/**
	 * 인터페이스 헤더셋팅
	 * @param systemPk
	 * @return
	 */
	private AdapterHeader setHeader(SystemPK systemPk){
		// TODO 인터페이스 헤더설정 확인필요!!
		// 인터페이스 헤더설정
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");
		
		return adapterHeader;
	}

}
