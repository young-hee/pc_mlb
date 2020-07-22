/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 6. 10       
 */
package com.plgrim.ncp.commons.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldy;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldyDow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopPhoto;
import com.plgrim.ncp.commons.data.FormSysShopDTO;
import com.plgrim.ncp.commons.data.SysShopDTO;
import com.plgrim.ncp.commons.data.SysShopDataDTO;
import com.plgrim.ncp.commons.data.SysShopHoldyDTO;
import com.plgrim.ncp.commons.repository.SysShopMgrRepository;
import com.plgrim.ncp.commons.result.SysShopEvtResult;
import com.plgrim.ncp.commons.result.SysShopHoldyResult;
import com.plgrim.ncp.commons.result.SysShopResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * 매장관리 Service
 * @author ed
 *
 */
@Service
@Slf4j
public class SysShopMgrService extends AbstractService {

	@Autowired
    SysShopMgrRepository sysShopMgrRepository; // 매장 DAO
	
	/**
	 * 매장 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysShopResult> selectSysShopList( SysShopDTO paramData) throws Exception {
		return sysShopMgrRepository.selectSysShopList(paramData); 
	}
	
	/**
	 * 매장 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysShopListCount( SysShopDTO paramData) throws Exception {
		return sysShopMgrRepository.selectSysShopListCount(paramData); 
	}
	
	/**
	 * 매장 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysShopListExcel( SysShopDTO paramData) throws Exception {
		return sysShopMgrRepository.selectSysShopListExcel(paramData); 
	}
	
	/**
	 * 매장 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysShop( SysShopDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		paramData.getFormData().getSysShop().setRegtrId(loginId); // 등록자
		paramData.getFormData().getSysShop().setUdterId(loginId); // 수정자

		sysShopMgrRepository.insertSysShop(paramData.getFormData().getSysShop()); // 추가
		
		try {
			// 최초등록시에 변경 플래그 저장. 재고연동대상 처리를 위해
			sysShopMgrRepository.mergeSysShopModifyFlag(paramData.getFormData().getSysShop());
		} catch (Exception e) {
			// 저장에 실패하면 오류로그만 남기고 처리하도록
			log.error(e.getMessage(), e);
		}
		
	}
	
	/**
	 * 매장 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysShopResult selectSysShopDetail( SysShopDTO paramData) throws Exception {
		SysShopResult sysShopResult = new SysShopResult();
		sysShopResult = sysShopMgrRepository.selectSysShopDetail(paramData);
		
		if(sysShopResult != null){
			SysShopPhoto sysShopPhoto = new SysShopPhoto();
			sysShopPhoto.setShopId(sysShopResult.getSysShop().getShopId());
			sysShopPhoto.setShopImgSectCd("SHOP_IMG");
			sysShopResult.setSysShopPhotoList(sysShopMgrRepository.selectSysShopPhotoDetail(sysShopPhoto));
		}
		
		return sysShopResult;
	}
	
	/**
	 * 매장 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysShop( SysShopDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		paramData.getFormData().getSysShop().setUdterId(loginId); // 수정자
		
		FormSysShopDTO search = new FormSysShopDTO();
		search.setShopId(paramData.getFormData().getSysShop().getShopId());
		paramData.setSearch(search);
		
		SysShopResult result = sysShopMgrRepository.selectSysShopDetail(paramData);
		SysShop sysShopOld = null;
		if (result != null && result.getSysShop() != null) {	// 수정 전 현재 정보 null 체크
			sysShopOld = result.getSysShop();
		}

		sysShopMgrRepository.updateSysShop(paramData.getFormData().getSysShop()); // 수정
		
		try {
			if (StringUtils.isNotEmpty(sysShopOld.getUseYn())) {	// 수정 전 현재 사용여부 null 체크
				if (!StringUtils.equals(sysShopOld.getUseYn(), paramData.getFormData().getSysShop().getUseYn())) {
					// 수정 전 현재 값과 수정 할 값을 비교하여 다르면 플래그 처리, 재고연동대상 처리를 위해
					
					sysShopMgrRepository.mergeSysShopModifyFlag(paramData.getFormData().getSysShop());
				}
			}
			
		} catch (Exception e) {
			// 저장에 실패하면 오류로그만 남기고 처리하도록
			log.error(e.getMessage(), e);
		}
		
	}
	
	/**
	 * 매장 이미지 페이지 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysShopImgPage( SysShopDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		paramData.getFormData().getSysShop().setUdterId(loginId); // 수정자
		
		sysShopMgrRepository.updateSysShopImgPage(paramData.getFormData().getSysShop()); // 수정
	}
	
	/**
	 * 매장 그리드 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateSysShopGrid( List<SysShopDataDTO> paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramData != null){
			for(int i=0; i < paramData.size(); i++){
				paramData.get(i).getSysShop().setUdterId(loginId); // 수정자
	
				SysShopDTO dto = new SysShopDTO();
				FormSysShopDTO search = new FormSysShopDTO(); 
				search.setShopId(paramData.get(i).getSysShop().getShopId());
				dto.setSearch(search);
				
				SysShopResult result = sysShopMgrRepository.selectSysShopDetail(dto);
				SysShop sysShopOld = null;
				if (result != null && result.getSysShop() != null) {	// 수정 전 현재 정보 null 체크
					sysShopOld = result.getSysShop();
				}
				
				sysShopMgrRepository.updateSysShopGrid(paramData.get(i).getSysShop());
				
				try {
					if (StringUtils.isNotEmpty(sysShopOld.getUseYn())) {	// 수정 전 현재 사용여부 null 체크
						if (!StringUtils.equals(sysShopOld.getUseYn(), paramData.get(i).getSysShop().getUseYn())) {
							// 수정 전 현재 값과 수정 할 값을 비교하여 다르면 플래그 처리, 재고연동대상 처리를 위해
							
							sysShopMgrRepository.mergeSysShopModifyFlag(paramData.get(i).getSysShop());
						}
					}
					
				} catch (Exception e) {
					// 저장에 실패하면 오류로그만 남기고 처리하도록
					log.error(e.getMessage(), e);
				}
				
			}
		}
	}
	
	/**
	 * 브랜드 매장 이미지 등록/수정
	 * @param vendorBrndRegDTO
	 * @throws Exception
	 */
	public void updateSysShopImg(SysShopDTO sysShopDTO)throws Exception{
		if(sysShopDTO.getSysShopPhotoList() != null){
			for(int i=0; i<sysShopDTO.getSysShopPhotoList().size(); i++){
				sysShopMgrRepository.insertSysShopPhoto((SysShopPhoto)sysShopDTO.getSysShopPhotoList().get(i));
			}
		}
		
		if(sysShopDTO.getSysShopPhotoDeleteList() != null){
			for(int i=0; i<sysShopDTO.getSysShopPhotoDeleteList().size(); i++){
				sysShopMgrRepository.updateSysShopPhoto((SysShopPhoto)sysShopDTO.getSysShopPhotoDeleteList().get(i));
			}
		}
	}
	
	/**
	 * 브랜드 매장 이벤트 상세 조회
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public SysShopEvtResult selectSysShopEvt(SysShopEvt sysShopEvt)throws Exception{
		return sysShopMgrRepository.selectSysShopEvt(sysShopEvt);
	}
	
	/**
	 * 브랜드 매장 이벤트 등록
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public String insertSysShopEvt(SysShopEvt sysShopEvt)throws Exception {
		String rtnCode = "00";
		if("Y".equals(sysShopEvt.getUseYn())){
			int cnt = sysShopMgrRepository.selectSysShopEvtCheck(sysShopEvt);
			if(cnt > 0){//진행중인 이벤트
				rtnCode = "01";
			}
		}
		if("00".equals(rtnCode)){
			rtnCode = sysShopMgrRepository.insertSysShopEvt(sysShopEvt);
		}
		
		return rtnCode;
	}
	
	/**
	 * 브랜드 매장 이벤트 수정
	 * @param sysShopEvt
	 * @return
	 * @throws Exception
	 */
	public String updateSysShopEvt(SysShopEvt sysShopEvt)throws Exception {
		String rtnCode = "00";
		if("Y".equals(sysShopEvt.getUseYn())){
			int cnt = sysShopMgrRepository.selectSysShopEvtCheck(sysShopEvt);
			if(cnt > 0){//진행중인 이벤트
				rtnCode = "01";
			}
		}
		if("00".equals(rtnCode)){
			sysShopMgrRepository.updateSysShopEvt(sysShopEvt);
			rtnCode = Long.toString(sysShopEvt.getShopEvtSn());
		}
		
		return rtnCode;
	}

	/**
	 * 브랜드 매장 이벤트 내역 조회
	 * @param vendorBrndSearchDTO
	 * @return Page<VendorBrndListResult>
	 * @throws Exception
	 */
	public Page<SysShopResult> selectSysShopEvtList(SysShopDTO sysShopDTO) throws Exception {

		RepositoryHelper.makePageEntityIndex(sysShopDTO, sysShopDTO.getPageParam());	// 페이지 설정
		
		// 목록 건수 조회
		long totalRow = sysShopMgrRepository.selectSysShopEvtListCount(sysShopDTO);
		
		// 목록 조회
		List<SysShopResult> results = new ArrayList<SysShopResult>();
		if(totalRow > 0){
			results = sysShopMgrRepository.selectSysShopEvtList(sysShopDTO);
		}
		
		return new PageImpl<SysShopResult>(results, sysShopDTO.getPageParam().getPageable(), totalRow);
	}
	
	/**
	 * 브랜드 매장 이벤트 내역 엑셀
	 * @param vendorBrndSearchDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSysShopEvtListExcel(SysShopDTO sysShopDTO) throws Exception{
		return sysShopMgrRepository.selectSysShopEvtListExcel(sysShopDTO);
	}
	
	// TODO
	/**
	 * 브랜드 매장 휴일 내역 조회
	 * @param vendorBrndSearchDTO
	 * @return Page<VendorBrndListResult>
	 * @throws Exception
	 */
	public Page<SysShopHoldyResult> selectSysShopHoldyList(SysShopDTO sysShopDTO) throws Exception {

		RepositoryHelper.makePageEntityIndex(sysShopDTO, sysShopDTO.getPageParam());	// 페이지 설정
		
		// 목록 건수 조회
		long totalRow = sysShopMgrRepository.selectSysShopHoldyListCount(sysShopDTO);
		
		// 목록 조회
		List<SysShopHoldyResult> results = new ArrayList<SysShopHoldyResult>();
		if(totalRow > 0){
			results = sysShopMgrRepository.selectSysShopHoldyList(sysShopDTO);
		}
		
		return new PageImpl<SysShopHoldyResult>(results, sysShopDTO.getPageParam().getPageable(), totalRow);
		
	}
	
	/**
	 * 브랜드 매장 휴일 내역 엑셀
	 * @param vendorBrndSearchDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSysShopHoldyListExcel(SysShopDTO sysShopDTO) throws Exception{
		return sysShopMgrRepository.selectSysShopHoldyListExcel(sysShopDTO);
	}
	
	/**
	 * 상담 휴일 내역 조회
	 * @param sysShopDTO
	 * @return Page<SysShopHoldyResult>
	 * @throws Exception
	 */
	public Page<SysShopHoldyResult> selectCounselHoldyList(SysShopDTO sysShopDTO) throws Exception {
		
		RepositoryHelper.makePageEntityIndex(sysShopDTO, sysShopDTO.getPageParam());	// 페이지 설정
		
		// 목록 건수 조회
		long totalRow = sysShopMgrRepository.selectCounselHoldyListCount(sysShopDTO);
		
		// 목록 조회
		List<SysShopHoldyResult> results = new ArrayList<SysShopHoldyResult>();
		if(totalRow > 0){
			results = sysShopMgrRepository.selectCounselHoldyList(sysShopDTO);
		}
		
		return new PageImpl<SysShopHoldyResult>(results, sysShopDTO.getPageParam().getPageable(), totalRow);
		
	}
	
	/**
	 * 상담 휴일 내역 엑셀
	 * @param sysShopDTO
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCounselHoldyListExcel(SysShopDTO sysShopDTO) throws Exception{
		return sysShopMgrRepository.selectCounselHoldyListExcel(sysShopDTO);
	}
	
	/**
	 * 브랜드 매장 휴일 상세조회
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public SysShopHoldyResult selectSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception {
		return sysShopMgrRepository.selectSysShopHoldyDay(sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 등록
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public String insertSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception {
		String resultCode = "00";
		if(sysShopMgrRepository.selectSysShopHoldyDayCheck(sysShopHoldy) > 0){
			resultCode = "01";
		}else{
			sysShopMgrRepository.insertSysShopHoldyDay(sysShopHoldy);
		}
		return resultCode;
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 수정
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public String updateSysShopHoldyDay(SysShopHoldyDTO sysShopHoldyDTO) throws Exception {
		String resultCode = "00";		
		if("Y".equals(sysShopHoldyDTO.getSysDateModifyYn())){
			SysShopHoldy sysShopHoldy = new SysShopHoldy();
			sysShopHoldy.setShopId(sysShopHoldyDTO.getShopId());
			sysShopHoldy.setSysDate(sysShopHoldyDTO.getSysDate());
			if(sysShopMgrRepository.selectSysShopHoldyDayCheck(sysShopHoldy) > 0){
				resultCode = "01";
			}
		}
		if("00".equals(resultCode)){
			sysShopMgrRepository.updateSysShopHoldyDay(sysShopHoldyDTO);
		}
		return resultCode;
	}
	
	/**
	 * 브랜드 매장 휴일 일자별 삭제
	 * @param sysShopHoldy
	 * @return
	 * @throws Exception
	 */
	public int deleteSysShopHoldyDay(SysShopHoldy sysShopHoldy) throws Exception {
		return sysShopMgrRepository.deleteSysShopHoldyDay(sysShopHoldy);
	}
	
	/**
	 * 브랜드 매장 휴일 요일별 상세조회
	 * @param sysShopHoldyDow
	 * @return
	 * @throws Exception
	 */
	public List<SysShopHoldyResult> selectSysShopHoldyWeek(SysShopHoldyDow sysShopHoldyDow) throws Exception {
		return sysShopMgrRepository.selectSysShopHoldyWeek(sysShopHoldyDow);
	}
	
	/**
	 * 브랜드 매장 휴일 요일별 등록
	 * @param sysShopHoldyDow
	 * @return
	 * @throws Exception
	 */
	public void insertSysShopHoldyWeek(SysShopHoldyDTO vendorBrndHoldyDTO) throws Exception {
		SysShopHoldyDow sysShopHoldyDowDel = new SysShopHoldyDow();
		sysShopHoldyDowDel.setShopId(vendorBrndHoldyDTO.getShopId());
		
		sysShopMgrRepository.deleteSysShopHoldyWeek(sysShopHoldyDowDel);//전체삭제
		
		String loginId = BOSecurityUtil.getLoginId();
	    if(vendorBrndHoldyDTO.getDowCd() != null){
	    	for(int i=0; i<vendorBrndHoldyDTO.getDowCd().size(); i++){
				SysShopHoldyDow sysShopHoldyDow = new SysShopHoldyDow();
				sysShopHoldyDow.setShopId(vendorBrndHoldyDTO.getShopId());
				sysShopHoldyDow.setDowCd(vendorBrndHoldyDTO.getDowCd().get(i));
				sysShopHoldyDow.setRegtrId(loginId);
				sysShopHoldyDow.setUdterId(loginId);
				sysShopMgrRepository.insertSysShopHoldyWeek(sysShopHoldyDow);
	    	}
	    }
	}
	
	/**
	 * 매장 ERP 브랜드 ID
	 *
	 * @param shopId [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2018. 7. 27
	 */
	public String selectSysShopErpBrandId(String shopId) throws Exception {
		return sysShopMgrRepository.selectSysShopErpBrandId(shopId); 
	}	
	
}
