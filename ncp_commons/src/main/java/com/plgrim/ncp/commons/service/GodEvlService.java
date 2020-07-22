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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.repository.god.GodGodEvlAtchFileRepository;
import com.plgrim.ncp.commons.data.GodEvlDTO;
import com.plgrim.ncp.commons.repository.GodEvlRepository;
import com.plgrim.ncp.commons.result.GodEvlResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

/**
 * 상품평관리 Service
 * @author ed
 *
 */
@Service
public class GodEvlService extends AbstractService {
	
	private final String UPLOAD_IMAGE_TYPE = "godevl";
	
	@Autowired
    GodEvlRepository godEvlRepository; // 상품평관리 DAO
	
	@Autowired
    GodGodEvlAtchFileRepository godGodEvlAtchFileRepository;
	
	/**
	 * 상품평관리 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<GodEvlResult> selectGodEvlList( GodEvlDTO paramData) throws Exception {
		return godEvlRepository.selectGodEvlList(paramData); 
	}
	
	/**
	 * 상품평관리 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectGodEvlListCount( GodEvlDTO paramData) throws Exception {
		return godEvlRepository.selectGodEvlListCount(paramData); 
	}
	
	/**
	 * 상품평관리 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectGodEvlListExcel( GodEvlDTO paramData) throws Exception {
		return godEvlRepository.selectGodEvlListExcel(paramData); 
	}
	
	/**
	 * 상품평관리 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateGodEvl( GodEvlDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		paramData.getFormData().getGodGodEvl().setUdterId(loginId); // 수정자
		
		GodGodEvl godGodEvl = paramData.getFormData().getGodGodEvl();
		
		if(GoodsEnum.YES.toString().equals(godGodEvl.getBadnGodEvlYn())){	//불량리뷰=Y 이면 게시여부=N 로 수정
			godGodEvl.setNtceYn(GoodsEnum.NO.toString());
		}
		
		//베스트상품평후보여부가 null 이면 N 으로 셋팅. 20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
		if(godGodEvl.getBstGodEvlCndcyYn() == null || "".equals(godGodEvl.getBstGodEvlCndcyYn())){
			godGodEvl.setBstGodEvlCndcyYn("N");
		}
		
		//베스트상품평여부 또는 베스트상품평정렬순서가 변경되면 수정
		//20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
		int orgBstGodEvlSortSeq = paramData.getFormData().getOrgBstGodEvlSortSeq();
		String bstGodEvlYn      = godGodEvl.getBstGodEvlYn();
		String orgBstGodEvlYn   = paramData.getFormData().getOrgBstGodEvlYn();
		
		if(godGodEvl.getBstGodEvlSortSeq() != orgBstGodEvlSortSeq || !bstGodEvlYn.equals(orgBstGodEvlYn)){
			godEvlRepository.updateGodEvlSortSeq(godGodEvl); // 수정
			
			if("N".equals(bstGodEvlYn)){
				paramData.getFormData().getGodGodEvl().setBstGodEvlSortSeq(0);
			}
		}
		
		godEvlRepository.updateGodEvl(godGodEvl); // 수정
		
		// 삭제할 파일 정보 저장
 		if(paramData.getFormData().getDelImgSn() != null && paramData.getFormData().getDelImgSn().size() > 0) {
 			for(int i = 0; i < paramData.getFormData().getDelImgSn().size(); i++){
 				GodGodEvlAtchFile godGodEvlAtchFile = new GodGodEvlAtchFile();
 				
 				// 키값 설정
 				godGodEvlAtchFile.setGodNo(paramData.getFormData().getGodGodEvl().getGodNo());
 				godGodEvlAtchFile.setGodEvlTurn(paramData.getFormData().getGodGodEvl().getGodEvlTurn());
 				godGodEvlAtchFile.setAtchFileTurn(paramData.getFormData().getDelImgSn().get(i));
 				
 				godGodEvlAtchFileRepository.deleteGodGodEvlAtchFile(godGodEvlAtchFile); // 삭제
 			}
 		}
 		
 		// 저장할 파일 정보 저장
 		if(paramData.getFormData().getImgFile() != null && paramData.getFormData().getImgFile().size() > 0) {
 			for(int i = 0; i < paramData.getFormData().getImgFile().size(); i++){
 				GodGodEvlAtchFile godGodEvlAtchFile = new GodGodEvlAtchFile();
 				
 				// 키값 설정
 				godGodEvlAtchFile.setGodNo(paramData.getFormData().getGodGodEvl().getGodNo());
 				godGodEvlAtchFile.setGodEvlTurn(paramData.getFormData().getGodGodEvl().getGodEvlTurn());
 				godGodEvlAtchFile.setAtchFileNm(paramData.getFormData().getImgSj().get(i)); // 원본파일명
 				godGodEvlAtchFile.setAtchFileUrl(getConfigService().getProperty("ncp_base.image."+UPLOAD_IMAGE_TYPE+".http.path")+"/"+paramData.getFormData().getImgFile().get(i)); // URL
 				godGodEvlAtchFile.setRegtrId(loginId);
 				godGodEvlAtchFile.setUdterId(loginId);
 				
 				godEvlRepository.insertGodGodEvlAtchFile(godGodEvlAtchFile); // 저장
 			}
 		}
	}
	
	/**
	 * 상품평관리 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public GodEvlResult selectGodEvlDetail( GodEvlDTO paramData) throws Exception {
		if(StringUtils.isEmpty(paramData.getSearch().getMaskingYn())) {
			paramData.getSearch().setMaskingYn("Y");
		}
		
		return godEvlRepository.selectGodEvlDetail(paramData); 
	}
	
	/**
	 * 상품평관리 베스트 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectBestGodEvlListCount( GodEvlDTO paramData) throws Exception {
		return godEvlRepository.selectBestGodEvlListCount(paramData); 
	}
}
