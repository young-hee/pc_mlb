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
package com.plgrim.ncp.cmp.display.bo;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtGod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtr;
import com.plgrim.ncp.biz.display.data.DspCnrBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttDspTgtBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCnrConttExtDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrTmplatInfoBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrTpGrpBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttRevResultDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodExt;
import com.plgrim.ncp.biz.display.data.DspCtgryRelateBoDTO;
import com.plgrim.ncp.biz.display.data.DspImgUploadDTO;
import com.plgrim.ncp.biz.display.data.DspPromtBoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtGodBoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtGodExt;
import com.plgrim.ncp.biz.display.data.DspPromtSprtrBoDTO;
import com.plgrim.ncp.biz.display.data.DspStrndBoDTO;
import com.plgrim.ncp.biz.display.data.DspTmplatBoDTO;

/**
 * 전시 관리 Command Component
 * 
 * <p>
 * 
 * <ul>
 *   <li> 템플릿 정보 등록/수정/삭제 기능
 *   <li> 템플릿 연결 코너 등록/수정/삭제 기능
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2015. 4. 7
 */
public interface DisplayCommandComponent {
	
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

	/**
	 * 템플릿 기본정보 등록
	 * 
	 * @param dspTmplatDTO
	 * @return
	 * @throws Exception
	 */
	public long addTemplate(DspTmplatBoDTO dspTmplatDTO) throws Exception;
	
	/**
	 * 템플릿 기본정보 수정
	 * 
	 * @param dspTmplatDTO
	 * @return
	 * @throws Exception
	 */
	public int modifyTemplate(DspTmplatBoDTO dspTmplatDTO) throws Exception;
	
	/**
	 * 템플릿 기본정보 삭제
	 * 
	 * @param dspTmplatDTO
	 * @return
	 * @throws Exception
	 */
	public int removeTemplate(DspTmplatBoDTO dspTmplatDTO) throws Exception;
	
	/**
	 * 템플릿 기본정보 목록 삭제
	 * 
	 * @param list
	 * @return
	 */
	public int removeTemplateList(List<DspTmplatBoDTO> list);
	
	/**
	 * 템플릿 연결 코너 등록
	 * 
	 * @param tmplatSn
	 * @param list
	 * @return
	 */
	public int addTemplateCorner(Long tmplatSn, List<DspCnrTmplatInfoBoDTO> list);
	
	/**
	 * 템플릿 연결 코너목록 수정
	 * 
	 * @param tmplatSn
	 * @param list
	 * @return
	 */
	public int modifyTemplateCorner(Long tmplatSn, List<DspCnrTmplatInfoBoDTO> list);
	
	/**
	 * 템플릿 연결 코너 삭제
	 * 
	 * @param tmplatSn
	 * @param list
	 * @return
	 */
	public int removeTmplateCorner(Long tmplatSn, List<DspCnrTmplatInfoBoDTO> list);
	
	/**
	 * 템플릿 연결 코너 목록 저장
	 * 
	 * @param insList
	 * @param updList
	 * @return
	 */
	public int saveTmplateCorner(List<DspCnrTmplatInfoBoDTO> insList, List<DspCnrTmplatInfoBoDTO> updList);
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//코너
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 코너 등록
	 * 
	 * @param dspCnrDTO
	 * @return
	 * @throws Exception
	 */
	public long addCorner(DspCnrBoDTO dspCnrDTO) throws Exception;
	
	/**
	 * 코너수정
	 * 
	 * @param dspCnrDTO
	 * @return
	 * @throws Exception
	 */
	public int modifyCorner(DspCnrBoDTO dspCnrDTO) throws Exception;
	
	/**
	 * 코너 삭제
	 * 
	 * @param dspCnrDTO
	 * @return
	 * @throws Exception
	 */
	public int removeCorner(DspCnrBoDTO dspCnrDTO) throws Exception ;
	
	/**
	 * 코너 목록 수정
	 * 
	 * @param list
	 */
	public void modifyCornerList(List<DspCnrBoDTO> list) ;
	
	/**
	 * 코너 목록 삭제
	 * 
	 * @param list
	 */
	public void removeCornerList(List<DspCnrBoDTO> list) ;
	
	/**
	 * 코너컨텐츠 유형 그룹 등록
	 * 
	 * @param cnrSn
	 * @param upperCnrTpGrpSn
	 * @param list
	 */
	public void addCornerType(Long cnrSn, Long upperCnrTpGrpSn, List<DspCnrTpGrpBoDTO> list);
	
	/**
	 * 코너컨텐츠 유형 그룹 수정
	 * 
	 * @param cnrSn
	 * @param upperCnrTpGrpSn
	 * @param list
	 */
	public void modifyCornerType(Long cnrSn, Long upperCnrTpGrpSn, List<DspCnrTpGrpBoDTO> list);
	
	/**
	 * 코너컨텐츠 유형 그룹 삭제
	 * 
	 * @param cnrSn
	 * @param upperCnrTpGrpSn
	 * @param list
	 */
	public void removeCornerType(Long cnrSn, Long upperCnrTpGrpSn, List<DspCnrTpGrpBoDTO> list);
	
	/**
	 * 코너컨텐츠 유형 그룹 저장(등록/수정)
	 * 
	 * @param insList
	 * @param updList
	 */
	public void saveCornerType(List<DspCnrTpGrpBoDTO> insList, List<DspCnrTpGrpBoDTO> updList);
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//카테고리
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 카테고리 등록
	 * 
	 * @param dspCtgryBoDTO
	 * @param rprstImgFile
	 * @return
	 */
	public String insertCategoryInfo (DspCtgryBoDTO dspCtgryBoDTO, List<String> rprstImgFile);
	
	/**
	 * 카테고리 정보 수정
	 * 
	 * @param dspCtgryBoDTO
	 * @param rprstImgFile
	 * @return
	 */
	public int updateCategoryInfo (DspCtgryBoDTO dspCtgryBoDTO, List<String> rprstImgFile);
	
	/**
	 * 카테고리 삭제
	 * 
	 * @param dspCtgryBoDTO
	 * @return
	 */
	public int deleteCategoryInfo (DspCtgryBoDTO dspCtgryBoDTO);
	
	/**
	 * 하위카테고리 목록 수정
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void updateCtgryList(List<DspCtgryBoDTO> list) throws Exception;
	
	/**
	 * 하위카테고리 목록 삭제
	 * 
	 * @param list
	 */
	public void deleteCtgryList(List<DspCtgryBoDTO> list);
	
	/**
	 * 카테고리 연결 상품 목록 저장(수정)
	 * 
	 * @param updList
	 */
	public void saveCtgryCnncGodList(List<DspCtgryCnncGodBoDTO> updList);
	
	/**
	 * 카테고리 연결 상품등록
	 * 
	 * @param list
	 * @return
	 */
	public String[] addCtgryCnncGodList(List<DspCtgryCnncGod> list);
	
	/**
	 * 카테고리 연결 상품 목록 삭제
	 * 
	 * @param list
	 * @return
	 */
	public int deleteCtgryCnncGodList(List<DspCtgryCnncGodBoDTO> list);
	
	/**
	 * 카테고리 연결 상품 복사
	 * 
	 * @param dspCtgryCnncGodBoDTO
	 * @return
	 */
	public int copyCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO);

	/**
	 * 전시 순서 일괄등록
	 * 
	 * @param list
	 * @param dspCtgryNo
	 * @return
	 */
	public List<DspCtgryCnncGodExt> updateSortSeqGod(List<DspCtgryCnncGod> list, String dspCtgryNo);

	/**
	 * 연관카테고리 등록
	 * 
	 * @param dspCtgryRelateBoDTO
	 * @return
	 */
	public int addCtgryRelate (DspCtgryRelateBoDTO dspCtgryRelateBoDTO);

	/**
	 * 연관카테고리 삭제
	 * 
	 * @param list
	 */
	public void deleteCtgryRelate (List<DspCtgryRelateBoDTO> list);
	
	////////////////////////////////////////////////////////////////////////////////////////////
	//코너 컨텐츠 관리
	////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 코너 세트 정보 저장
	 * 
	 * @param insList
	 * @param updList
	 * @return
	 */
	public int saveCornerSetList(List<DspCnrConttScBoDTO> insList, List<DspCnrConttScBoDTO> updList);

	/**
	 * 코너 세트 정보 저장 - 리비전 변경하여 저장
	 *
	 * @param insList
	 * @param updList
	 * @return
	 */
	public int saveCornerSetListRev(List<DspCnrConttScBoDTO> insList, List<DspCnrConttScBoDTO> updList);
	
	/**
	 * 코너 세트 삭제
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void deleteCornerSetList(List<DspCnrConttScBoDTO> list) throws Exception;
	
	/**
	 * 코너 세트 삭제 - 리비전 변경하여 저장.
	 *
	 * @param list
	 */
	public void deleteCornerSetListRev (List<DspCnrConttScBoDTO> list);

	/**
	 * 코너 컨텐츠 정보 등록. (팝업에서 한건씩 등록할 때 사용)
	 * 
	 * @param dspCnrConttScBoDTO
	 * @param rprstImgFile
	 * @return
	 */
	public int insertCornerContent(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<String> rprstImgFile);
	
	/**
	 * 코너 컨텐츠 목록 등록. (상품,기획전,S-Trnd 목록 등록)
	 * 
	 * @param dspCnrConttScBoDTO
	 * @param list
	 * @return
	 */
	public String[] insertCornerContentList(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrConttExt> list);
	
	/**
	 * 코너 컨텐츠 정보 수정. (팝업에서 한건씩 수정)
	 * 
	 * @param dspCnrConttScBoDTO
	 * @param rprstImgFile
	 */
	public void updateCornerContent(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<String> rprstImgFile);
	
	/**
	 * 코너 컨텐츠 그리드 수정. (코너 컨텐츠 공통 그리드 수정)
	 * 
	 * @param list
	 */
	public void updateCornerContentsList(List<DspCnrConttScBoDTO> list);
	
	/**
	 * 코너 컨텐츠 그리드 저장. - 등록/수정
	 * 
	 * @param insList
	 * @param updList
	 */
	public void saveCornerContentsList(List<DspCnrConttScBoDTO> insList, List<DspCnrConttScBoDTO> updList);
	
	/**
	 * 코너 컨텐츠 그리드 삭제.
	 * 
	 * @param
	 */
	public void deleteCornerContentsList(List<DspCnrConttScBoDTO> list);

	/**
	 * 코너 컨텐츠 전시대상 설정.
	 * 
	 * @param dspCnrConttDspTgtBoDTO
	 */
	public void saveContentDspTgt(DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO);
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//기획전
	//////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 기획전 목록 수정
	 * 
	 * @param list 
	 */
	public void updatePlanList(List<DspPromtBoDTO> list);
	
	/**
	 * 기획전 승인상태 update.
	 * 
	 * @param list
	 */
	public void updatePromtAprvStat(List<DspPromtBoDTO> list);
	
	/**
	 * 기획전 기본정보 등록.
	 * 
	 * @param dspPromtBoDTO
	 * @return
	 */
	public long insertPromtInfo(DspPromtBoDTO dspPromtBoDTO);
	
	/**
	 * 기획전 기본정보 수정
	 * 
	 * @param dspPromtBoDTO
	 */
	public void updatePromtInfo(DspPromtBoDTO dspPromtBoDTO);
	
	/**
	 * 기획전 구분타이틀 등록
	 * 
	 * @param dpsPromtSprtrBoDTO
	 */
	public void insertPromtSprtrInfo(DspPromtSprtrBoDTO dpsPromtSprtrBoDTO);
	
	/**
	 * 기획전 구분타이틀 수정
	 * 
	 * @param dspPromtSprtrBoDTO
	 */
	public void updatePromtSprtrInfo(DspPromtSprtrBoDTO dspPromtSprtrBoDTO);
	
	/**
	 * 기획전 구분타이틀 그리드 수정
	 * 
	 * @param list
	 */
	public void updatePromtSprtrList(List<DspPromtSprtrBoDTO> list);
	
	/**
	 * 기획전 구분타이틀 그리드 수정
	 * 
	 * @param list
	 */
	public void deletePromtSprtrList(List<DspPromtSprtrBoDTO> list);
	
	/**
	 * 기획전 상품 연결 등록
	 * 
	 * @param list
	 * @return
	 */
	public String[] insertPromtGodList(List<DspPromtGod> list);
	
	/**
	 * 기획전 상품 연결 수정
	 * 
	 * @param list
	 */
	public void updatePromtGodList(List<DspPromtGodBoDTO> list);

	/**
	 * 기획전 상품리뷰 정렬 코드 수정
	 * 
	 * @param dspPromtBoDTO
	 * @throws Exception
	 */
	public void updatePromtGodReviewSortCd(DspPromtBoDTO dspPromtBoDTO) throws Exception;

	/**
	 * 기획전 상품 연결 삭제
	 * 
	 * @param list
	 */
	public void deletePromtGodList(List<DspPromtGodBoDTO> list);
	
	/**
	 * 기획전 상품 전시순서 일괄등록
	 * 
	 * @param list
	 * @param promtSn
	 * @param sprtrTurn
	 * @return
	 */
	public List<DspPromtGodExt> updateSortSeqPromtGod(List<DspPromtGod> list, Long promtSn, Integer sprtrTurn);
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//S-TREND
	//////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * S-TREND 목록 수정
	 * 
	 * @param list
	 */
	public void updateStrndList(List<DspStrndBoDTO> list);
	
	/**
	 * S-TREND 목록 삭제
	 * 
	 * @param list
	 */
	public void deleteStrndList(List<DspStrndBoDTO> list);
	
	/**
	 * S-TREND 기본정보 등록
	 * 
	 * @param dspStrndBoDTO
	 * @return
	 */
	public long insertStrndInfo(DspStrndBoDTO dspStrndBoDTO);
	
	/**
	 * S-TREND 기본정보 수정
	 * 
	 * @param dspStrndBoDTO
	 */
	public void updateStrndInfo(DspStrndBoDTO dspStrndBoDTO);
	
	/**
	 * 전시 컨텐츠 이미지 등록
	 * 
	 * @param dspImgUploadDTO
	 * @return
	 * @throws Exception
	 */
	public List<String> insertImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception;
	
	/**
	 * 전시 컨텐츠 이미지 수정(추가)
	 * 
	 * @param dspImgUploadDTO
	 * @return
	 * @throws Exception
	 */
	public List<String> updateImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception;
	
	/**
	 * 전시컨텐츠 이미지 업로드 파일명 존재 여부 확인
	 * 
	 * @param dspImgUploadDTO
	 * @return
	 * @throws Exception
	 */
	public Boolean isExistFile(DspImgUploadDTO dspImgUploadDTO) throws Exception;
	
	/**
	 * 전시코너 연결 상품 전시순서 업로드
	 * 
	 * @param list
	 * @param cnrSn
	 * @param cnrSetSn
	 * @param revSn
	 * @return
	 */
	public List<DspCnrConttExt> updateSortSeqCnrConttGod(List<DspCnrContt> list, Long cnrSn, Long cnrSetSn, Long revSn);

	/**
	 * 전시코너 연결 상품 전시순서 임시저장
	 * 
	 * @param dspCnrConttScBoDTO
	 * @param list
	 * @param cnrSn
	 * @param cnrSetSn
	 * @return
	 */
	public DspCnrConttExtDTO updatePrevSortSeqCnrConttGod (DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrContt> list, Long cnrSn, Long cnrSetSn);

	/**
	 * 전시코너 연결 상품 전시순서 업로드 AB테스트 변경
	 * 
	 * @param dspCnrConttScBoDTO
	 * @param list
	 * @param cnrSn
	 * @param cnrSetSn
	 * @return
	 */
	public DspCnrConttExtDTO updateAbTestModSortSeqCnrConttGod (DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrContt> list, Long cnrSn, Long cnrSetSn);
	
	/**
	 * 기획전 타이틀/상품 일괄 업로드
	 * 
	 * @param list
	 * @return
	 */
	public List<DspPromtSprtr> insertPromtSprtrGodExcelUpload(List<DspPromtSprtr> list);
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI) 
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 브랜드 카테고리 정보 수정
	 * 
	 * @param dspCtgryBoDTO
	 * @param rprstImgFile
	 * @return
	 */
	public int updateBrndCategoryInfo (DspCtgryBoDTO dspCtgryBoDTO, List<String> rprstImgFile);
	
	/**
	 * 브랜드 카테고리 정보 수정 for grid
	 *
	 * @param list the list
	 */
	public void updateBrndCtgryList(List<DspCtgryBoDTO> list);
	
	/**
	 * 전시 순서 일괄등록 - 브랜드카테고리
	 * 
	 * @param list
	 * @param dspCtgryNo
	 * @param scDspBrndId
	 * @return
	 */
	public List<DspCtgryCnncGodExt> updateBrndCtgrySortSeqGod(List<DspCtgryCnncGod> list, String dspCtgryNo, String scDspBrndId);
	
	/**
	 * 템플릿 코너 연결 정보 저장
	 * 
	 * @param insList
	 * @param updList
	 * @return
	 */
	public int saveCnrCnncList(List<DspCnrTmplatInfoBoDTO> insList, List<DspCnrTmplatInfoBoDTO> updList);

	/**
	 * 템플릿 코너 연결 정보 저장 - 리비전 변경하여 저장
	 * 
	 * @param insList
	 * @param updList
	 * @return
	 */
	public int saveCnrCnncListRev (List<DspCnrTmplatInfoBoDTO> insList, List<DspCnrTmplatInfoBoDTO> updList);
	
	/**
	 * 카테고리 연결 상품이동
	 * 선택한 리프카테고리로 상품을 복사하고, 기존 카테고리에서는 상품 삭제한다.
	 * 신규카테고리의 상품 전시여부는 "미전시", 전시순서는 "999"로 등록 - 중복 제외
	 * 
	 * @param dspCtgryCnncGodBoDTO
	 * @return
	 */
	public int moveCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO);

	/**
	 * 카테고리 연결 상품 복사시 아웃렛 카테고리인지 체크
	 * 
	 * @param dspCtgryCnncGodBoDTO
	 * @return
	 * @throws Exception
	 */
	public Integer selectOutletCtgForCopy(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception;
	
	/**
	 * 카테고리 순서변경
	 *
	 * @param list the list
	 */
	public void updateCtgSortSeq(List<DspCtgryBoDTO> list);
	
	/**
	 * 카테고리 연결상품 순서변경
	 *
	 * @param list the list
	 */
	public void updateCtgGodSortSeq(List<DspCtgryCnncGodBoDTO> list);
	
	/**
	 * 기획전 이미지 끌어오기
	 * 
	 * @param dspCnrConttScBoDTO
	 */
	public void savePromtImg(DspCnrConttScBoDTO dspCnrConttScBoDTO);

	/**
	 * 컨텐츠 임시저장
	 * 
	 * @param dspCnrConttScBoDTO
	 * @param rprstImgFile
	 * @return
	 */
	public Long savePreviewCornerContent(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<String> rprstImgFile);

	/**
	 * 컨텐츠 임시저장 - 그리드 저장
	 * 
	 * @param list
	 * @param dspCnrConttScBoDTO
	 * @return
	 */
	public Long updatePrevCornerContentsList(List<DspCnrConttScBoDTO> list, DspCnrConttScBoDTO dspCnrConttScBoDTO);

	/**
	 * 컨텐츠 임시저장 - 그리드 삭제
	 * 
	 * @param list
	 * @param dspCnrConttScBoDTO
	 * @return
	 */
	public Long deletePrevCornerContentsList(List<DspCnrConttScBoDTO> list, DspCnrConttScBoDTO dspCnrConttScBoDTO);

	/**
	 * 컨텐츠 전시대상설정 - 임시저장
	 * 
	 * @param dspCnrConttDspTgtBoDTO
	 * @return
	 */
	public Long savePrevContentDspTgt(DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO);

	/**
	 * 기획전 이미지 끌어오기 - 임시저장
	 * 
	 * @param dspCnrConttScBoDTO
	 * @return
	 */
	public Long savePrevPromtImg(DspCnrConttScBoDTO dspCnrConttScBoDTO);

	/**
	 * 텍스트 저장 - 임시저장
	 * 
	 * @param dspCnrConttScBoDTO
	 * @param gridList
	 * @return
	 */
	public Long savePreviewCornerContentsList (DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrConttScBoDTO> gridList);

	/**
	 * 상품 저장 - 임시저장
	 * 
	 * @param dspCnrConttScBoDTO
	 * @param list
	 * @return
	 */
	public DspConttRevResultDTO insertPreviewCornerContentList(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrConttExt> list);

	//abtest
	/**
	 * 컨텐츠 AB-TEST MOD
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @param rprstImgFile the rprst img file
	 * @ the exception
	 */
	public Long saveAbTestModCornerContent(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<String> rprstImgFile);

	/**
	 * 컨텐츠 AB-TEST MOD - 그리드 저장
	 *
	 * @param list the list
	 * @ the exception
	 */
	public Long updateAbTestModCornerContentsList(List<DspCnrConttScBoDTO> list, DspCnrConttScBoDTO dspCnrConttScBoDTO);

	/**
	 * 컨텐츠 AB-TEST MOD - 그리드 삭제
	 *
	 * @param list the list
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @return the long
	 * @ the exception
	 */
	public Long deleteAbTestModCornerContentsList(List<DspCnrConttScBoDTO> list, DspCnrConttScBoDTO dspCnrConttScBoDTO);

	/**
	 * 컨텐츠 전시대상설정 - AB-TEST MOD
	 *
	 * @param dspCnrConttDspTgtBoDTO
	 * @
	 */
	public Long saveAbTestModContentDspTgt(DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO);

	/**
	 * 기획전 이미지 끌어오기 - AB-TEST MOD
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @ the exception
	 */
	public Long saveAbTestModPromtImg(DspCnrConttScBoDTO dspCnrConttScBoDTO);

	/**
	 * 텍스트저장 - AB-TEST MOD
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @param gridList the grid list
	 * @return the long
	 */
	public Long saveAbTestModCornerContentsList (DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrConttScBoDTO> gridList);

	/**
	 * 상품저장 - AB-TEST MOD
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @param list the list
	 * @return the dsp contt rev result DTO
	 */
	public DspConttRevResultDTO insertAbTestModCornerContentList(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrConttExt> list);
	/**
	 * 미리보기 리비전 복사
	 *
	 * @param dspCnrConttScBoDTO
	 * @return
	 * @
	 */
	public Long copyPreviewRevisionSn (DspCnrConttScBoDTO dspCnrConttScBoDTO);

	/**
	 * A/B 테스트 리비전 복사
	 * AB TEST 수정 rev 생성
	 *
	 * 1) mod + 1 생성- dsp_ab_test_set_mod 추가
	 * 	 1.1) 수정사유 저장
	 * 2) abtest set 에 해당하는 모든 컨텐츠 복사
	 *   2.1) dsp_ab_test_rev 조죄
	 *   2.2) 2.1 내용을 복사하여 dsp_rev 추가
	 *   2.3) 2.1 내용을 복사하여 dsp_ab_test_rev 추가
	 *   2.4) 2.1 내용을 복사하여 dsp_rev_cpst 추가
	 *         contents copy
	 *
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @param oldRevSn the old rev sn
	 * @return the long
	 * @ the exception
	 */
	public Long copyAbTestModRevSn (DspCnrConttScBoDTO dspCnrConttScBoDTO, Long oldRevSn);

	/**
	 * 미리보기 운영적용
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @return the int
	 * @ the exception
	 */
	public void savePreviewToBase(DspCnrConttScBoDTO dspCnrConttScBoDTO);


}
