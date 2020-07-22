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
 * @since       2015. 12. 1       
 */
package com.plgrim.ncp.cmp.display.fo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndImg;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatHistExtends;
import com.plgrim.ncp.biz.brand.data.BrandResultDTO;
import com.plgrim.ncp.biz.display.data.DisplayPageResultDTO;
import com.plgrim.ncp.biz.display.data.DspCnrScFrDTO;
import com.plgrim.ncp.biz.display.data.DspCnrSearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryResultMbDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.DspCtgrySearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspPlanDetailResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspPlanResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspPlanSearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.data.DspStrendResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspStrendSearchFoDTO;
import com.plgrim.ncp.biz.display.data.GnbResultFoDTO;
import com.plgrim.ncp.biz.display.data.ThemaPageResultFoDTO;
import com.plgrim.ncp.biz.display.result.DspBrndImgResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttStrndFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrGodFoResult;
import com.plgrim.ncp.biz.display.result.DspCommonGodFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryBrndFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.DspPromtFrResult;
import com.plgrim.ncp.biz.display.result.DspStrendFoResult;
import com.plgrim.ncp.biz.display.result.DspTmplatFrResult;
import com.plgrim.ncp.biz.display.result.V2DspFoCtgryResult;
import com.plgrim.ncp.biz.goods.data.GoodsListMbResultDTO;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

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
 * @since 2015. 11. 13
 */
public interface DisplaySelectComponent {
	
	/**
	 * 코너컨텐츠 목록 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 12
	 */
	public List<DspCnrFrResult> selectDspCnrList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception;   
	
	/**
	 * 템플릿 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return Dsp tmplat fr result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 15
	 */
	public List<DspTmplatFrResult> selectTmplatPageInfo(DspCnrScFrDTO dspCnrScFrDTO) throws Exception;
	
	/**
	 * 전시브랜드카테고리 전체 브랜드 목록 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 11
	 */
	public List<DspCtgryBrndFrResult> selectBrandAllList(DspCtgryScFrDTO dto) throws Exception;
	
	/**
	 * 전시브랜드카테고리별 브랜드 수 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 12. 1
	 */
	public List<Map<String, Object>> selectBrandAllListCount(DspCtgryScFrDTO dto) throws Exception;
	
	/**
	 * 브랜드 LNG 카테고리 목록조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 3
	 */
	public List<DspCtgryFrResult> selectBrandLnbCtgryList(DspCtgryScFrDTO dto) throws Exception;
	
	/**
	 * 상품 목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public List<DspCommonGodFrResult> selectCtgryGodFrList(DspCtgryScFrDTO dto) throws Exception ;
	
	/**
	 * 상품 목록 총 수.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 6
	 */
	public int selectCtgryGodFrTotCnt(DspCtgryScFrDTO dto) throws Exception ;
	
	/**
	 * 전시카테고리 기본정보.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Dsp ctgry fr result [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 13
	 */
	public DspCtgryFrResult selectDisplayCategoryInfo(DspCtgryScFrDTO dto) throws Exception ;
	
	
	/**
	 * 로케이션 목록
	 * 
	 * <p/>
	 * 
	 *
	 * @param dto [설명]
	 * @return the location list
	 * @throws Exception the exception
	 * @since 2015. 11. 16
	 */
	public List<DspCtgryFrResult> getLocationList(String locType, DspCtgryScFrDTO dto, DspCtgryFrResult brandShopInfo) throws Exception;
	
	/**
	 * 카테고리 연결 상품목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 16
	 */
	public List<String> selectCnncGodNos(DspCtgryScFrDTO dto) throws Exception;
	
	/**
	 * 브랜드샵의 카테고리별 연결상품 수 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return Map [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 19
	 */
	public Map<String,Integer> selectBrndCtgGodCount(List<DspCtgryFrResult> treeList, DspCtgryScFrDTO dto) throws Exception;
	
	/**
	 * 해당 브랜드에 등록된 최신 S-Trnd조회..
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 23
	 */
	public Long selectStrndSnForBrnd(DspCnrScFrDTO dspCnrScFrDTO) throws Exception;
	
	/**
	 * 기획전 구분자 상품목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtScDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 25
	 */
	public DspPromtFrResult selectPromtGodList (DspPromtScFrDTO dspPromtScFrDTO) throws Exception;
	
	
	/**
	 * 기획전 로케이션 정보 조회.
	 * 
	 * <p/>
	 * 
	 * @param locType [설명]
	 * @param dspPromtScFrDTO [설명]
	 * @return the promt location
	 * @throws Exception the exception
	 * @since 2015. 11. 25
	 */
	public DspPlanDetailResultFoDTO getPromtLocation(String locType, DspPromtScFrDTO dspPromtScFrDTO) throws Exception;
	
	/**
	 * 특정노출브랜드 기획전 목로조회.
	 * 
	 * <p/>
	 *
	 * @param searchDTO [설명]
	 * @param pk [설명]
	 * @return the promt brand list
	 * @throws Exception the exception
	 * @since 2015. 11. 27
	 */
	public DspPlanResultFoDTO selectPromtBrandList (DspPlanSearchFoDTO searchDTO, DspCnrScFrDTO dspCnrScFrDTO, SystemPK pk) throws Exception;
	
	/**
	 * Strend 목록 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @param dspCnrScFrDTO [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 30
	 */
	public DspStrendResultFoDTO selectStrndBrandList (DspStrendSearchFoDTO searchDTO, DspCnrScFrDTO dspCnrScFrDTO, SystemPK pk) throws Exception;
	
	
	/**
	 * 코너컨텐츠 목록 조회 V2.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 12
	 */
	public List<DspCnrFrResult> selectDspCnrListV2(DspCnrScFrDTO dspCnrScFrDTO) throws Exception;   
	
	/**
	 * 템플릿 정보 조회 V2.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @return Dsp tmplat fr result [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 15
	 */
	public List<DspTmplatFrResult> selectTmplatPageInfoV2(DspCnrScFrDTO dspCnrScFrDTO) throws Exception;
	
	/**
	 * 브랜드카테고리의 전시브랜드ID조회
	 *
	 * @param dspCtgryNo the dsp ctgry no
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectBrndCtgryBrndId(String dspCtgryNo) throws Exception;
	
	/**
	 * Top브랜드 목록 조회
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryBrndFrResult> selectTopBrand(DspCtgryScFrDTO dto) throws Exception ;
	
	/**
	 * 브랜드 대표이미지 조회
	 *
	 * @param brandShopNo the brand shop no
	 * @param brndImgSectCd the brnd img sect cd
	 * @return the list
	 * @throws Exception the exception
	 */
	public SysBrndImg selectBrandImg(SystemPK pk, String brandShopNo, String brndImgSectCd) throws Exception; 
	
	/**
	 * 브랜드카테고리목록조회-비이커서브브랜드메인화면용.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryFrResult> selectBrndCtgryList(DspCtgryScFrDTO dto, SystemPK pk, GoodsPriceSearchDTO goodsPriceSearchFoDTO) throws Exception ;

	/**
	 * 브랜드스토리 이미지 조회
	 * @param brandId
	 * @return
	 * @throws Exception
     */
	public DspBrndImgResult selectBrandStory(String brandId) throws Exception;
	/**
	 * 브랜드스토리 이미지 조회 - 수정
	 * @param brandId
	 * @return
	 * @throws Exception
     */
	public DspBrndImgResult selectBrandStoryInfo(DspCtgryScFrDTO dto) throws Exception;
	/**
	 * All 브랜드 목록 조회
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryBrndFrResult> selectAllBrand(DspCtgryScFrDTO dto) throws Exception ;
	
	
	/**
	 * special brand 목록 조회.
	 *
	 * @return the list
	 */
	public List<DspCtgryBrndFrResult> selectSpecialBrand(DspCtgryScFrDTO scDTO, List<DspCnrFrResult> cnrList) throws Exception ;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dto [설명]
	 * @return Dsp ctgry result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspCtgryResultFoDTO selectDisplayCategroyList(SystemPK pk, DspCtgrySearchFoDTO dto) throws Exception;


	/**
	 * 전시 기획전 카테고리기준.
	 *
	 * @param searchDTO [설명]
	 * @param specialCategoryNo [설명]
	 * @param pk [설명]
	 * @return the plan category view
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */

	public DspPlanResultFoDTO getPlanCategoryView(DspPlanSearchFoDTO searchDTO,String specialCategoryNo, SystemPK pk) throws Exception;

	/**
	 * 전시 기획전 브랜드기준.
	 *
	 * @param searchDTO [설명]
	 * @param specialCategoryNo [설명]
	 * @param pk [설명]
	 * @return the plan brandy view
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspPlanResultFoDTO getPlanBrandyView(DspPlanSearchFoDTO searchDTO,String specialCategoryNo,SystemPK pk) throws Exception;

	/**
	 * 모바일  기획전 상세보기.
	 *
	 * @param searchDTO [설명]
	 * @param promtSn [설명]
	 * @param mode [설명]
	 * @param prcSectCd [설명]
	 * @param pageNo [설명]
	 * @param pk [설명]
	 * @return Dsp plan detail result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspPlanDetailResultFoDTO selectPlan(DspPlanSearchFoDTO searchDTO, Integer promtSn,String mode, String prcSectCd,String pageNo, SystemPK pk) throws Exception;

	/**
	 * 기획전 상세보기 .
	 *
	 * @param searchDTO [설명]
	 * @param promtSn [설명]
	 * @param mode [설명]
	 * @param pk [설명]
	 * @return Dsp plan detail result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspPlanDetailResultFoDTO selectPlan(DspPlanSearchFoDTO searchDTO, Integer promtSn,String mode, SystemPK pk) throws Exception;

	/**
	 * 기획전 상세보기 미리보기.
	 *
	 * @param searchDTO [설명]
	 * @param promtSn [설명]
	 * @param pk [설명]
	 * @return Dsp plan detail result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspPlanDetailResultFoDTO selectPlan(DspPlanSearchFoDTO searchDTO,
											   Integer promtSn, SystemPK pk) throws Exception;

	/**
	 * 기획전 selectbox조회.
	 *
	 * @param searchDTO [설명]
	 * @param sn [설명]
	 * @param mode [설명]
	 * @param pk [설명]
	 * @return Dsp plan detail result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspPlanDetailResultFoDTO selectPlanList(DspPlanSearchFoDTO searchDTO,String sn,String mode, SystemPK pk) throws Exception;

	/**
	 * 기획전 상품조회.
	 *
	 * @param searchDTO [설명]
	 * @param promtSn [설명]
	 * @param mode [설명]
	 * @param sortValue [설명]
	 * @param sprtr [설명]
	 * @param prcSectCd [설명]
	 * @param pk [설명]
	 * @return Dsp plan detail result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspPlanDetailResultFoDTO selectGodList(DspPlanSearchFoDTO searchDTO,Integer promtSn, String mode,
												  String sortValue, String sprtr,String prcSectCd, SystemPK pk)throws Exception;

	/**
	 * 모바일 기획전 상품조회.
	 *
	 * @param searchDTO [설명]
	 * @param promtSn [설명]
	 * @param sprtr [설명]
	 * @param prcSectCd [설명]
	 * @param pageNo [설명]
	 * @param pk [설명]
	 * @return Dsp plan detail result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspPlanDetailResultFoDTO selectGodList(DspPlanSearchFoDTO searchDTO,
												  int promtSn, String sprtr, String prcSectCd,String pageNo, SystemPK pk) throws Exception;

	/**
	 * CSS Club 테마페이지 컨텐츠 가져오기.
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @param ctgyNoMain [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspCnrFoResult> selectDspCtgrContt(SystemPK pk, DspCtgrySearchFoDTO searchDTO, String ctgyNoMain) throws Exception;

	/**
	 * 전시카테고리 2D 상품리스트 (FODP010100) 정보 조회.
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspCtgryResultFoDTO selectDspCtgryView(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * V2 Location
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<HashMap> v2DspLocationSet(String tp1, String tp2) throws Exception;

	/**
	 * V2 상품 상세 location 조회
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public V2DspFoCtgryResult selectDspGodCtgryView(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId, String etcCtgryNo) throws Exception;

	/**
	 * V2 SiteMap
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public V2DspFoCtgryResult selectDspSisteMapView(SystemPK pk) throws Exception;

	/**
	 * V2 LNB 조회
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public V2DspFoCtgryResult selectDspCtgryView(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId, String etcCtgryNo) throws Exception;

	/**
	 * V2 LNB BEAKER BRAND 조회
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspCtgry> selectBeakerBrandList(SystemPK pk, String dspCtgryNo, String brndDspCtgryNo, String indexKey) throws Exception;

	/**
	 * V2 모발일 GNB 조회
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public V2DspFoCtgryResult selectV2MBDspCtgryView(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId) throws Exception;

	/**
	 * V2 모발일 GNB 상품상세
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public V2DspFoCtgryResult selectV2MBDspGodCtgryView(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId) throws Exception;

	/**
	 * 모바일 V2 하위 카테고리 조회
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public V2DspFoCtgryResult selectDetailCategoryList(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId) throws Exception;

	/**
	 * 대카테고리 메인> NEW.
	 *
	 * @param pk 	System PK
	 * @param searchDTO 조회조건
	 * @return Dsp ctgry result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspCtgryResultFoDTO selectDspCtgry1dNewView(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dto [설명]
	 * @return Dsp ctgry result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspCtgryResultFoDTO selectDspBrandNewView(SystemPK pk, DspCtgrySearchFoDTO dto) throws Exception;


	/**
	 * 통합몰 메인
	 *
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return Thema page result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public ThemaPageResultFoDTO selectThemaPageView(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * 전시페이지(테마/브랜드샵메인/머천다이징 등등) 전시코너를 사용하는 페이지에서 전시코너 로딩에 상용.
	 *
	 * @param pk 			시스템 프라이머리 키
	 * @param dspCnrResultList 전시코너목록
	 * @param serialNumber 	전시대상페이지 식별자
	 * @param tmplatSn 		전시대상페이지에 연결된 템플릿 시리얼번호
	 * @param parentType 	전시대상페이지 유형(테마페이지,전시카테고리,기획전,STRAND)
	 * @param prcSectCd 		전시컨텐츠 중 상품으 전시가격 조건값
	 * @param spcPrmTp [설명]
	 * @return 				반환값은 위에서 주어진 전시코너리스트로 정리되어진다.
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DisplayPageResultDTO selectDisplayPageView(SystemPK pk,
													  List<DspCnrFoResult> dspCnrResultList, String serialNumber,
													  Long tmplatSn, String parentType, String prcSectCd,
													  String spcPrmTp) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrResultList [설명]
	 * @param serialNumber [설명]
	 * @param tmplatSn [설명]
	 * @param parentType [설명]
	 * @param prcSectCd [설명]
	 * @param spcPrmTp [설명]
	 * @param screenNo [설명]
	 * @return Display page result dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DisplayPageResultDTO selectDisplayPageView(SystemPK pk,
													  List<DspCnrFoResult> dspCnrResultList, String serialNumber,
													  Long tmplatSn, String parentType, String prcSectCd,
													  String spcPrmTp, String screenNo) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrResultList [설명]
	 * @param serialNumber [설명]
	 * @param tmplatSn [설명]
	 * @param parentType [설명]
	 * @param prcSectCd [설명]
	 * @param spcPrmTp [설명]
	 * @param screenNo [설명]
	 * @param brandIds [설명]
	 * @return Display page result dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DisplayPageResultDTO selectDisplayPageView(SystemPK pk,
													  List<DspCnrFoResult> dspCnrResultList, String serialNumber,
													  Long tmplatSn, String parentType, String prcSectCd,
													  String spcPrmTp, String screenNo, String[] brandIds) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCtgryNo [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<SysPopupNoti> selectMainPopupList(SystemPK pk, String dspCtgryNo) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspCtgryGodFoResult> selectDisplayCategoryConnSpecialLimitGoodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspCtgryGodFoResult> selectNewBestGoodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspCnrConttStrndFoResult> selectDspCnrConttStndList(SystemPK pk) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspCnrConttStrndFoResult> selectBeanpoleDspCnrConttStndList(SystemPK pk) throws Exception;

	/**
	 * 전시코너별 전시코너 정보를 조회.
	 *
	 * @param pk System PK
	 * @param dto Search Data
	 * @return 	전시코너 정보
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspCnrFoResult selectDisplayCornerView(SystemPK pk, DspCnrSearchFoDTO dto) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @param pageParam [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendResultFoDTO selectStrend(DspStrendSearchFoDTO searchDTO, PageParam pageParam,
											 SystemPK pk) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dspStrendSearchFoDTO [설명]
	 * @param pageParam [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendResultFoDTO selectLookbook(
			DspStrendSearchFoDTO dspStrendSearchFoDTO, PageParam pageParam,
			SystemPK pk)  throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @param pageParam [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendResultFoDTO selectMovieList(DspStrendSearchFoDTO searchDTO,
												PageParam pageParam, SystemPK pk)throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param strndTp [설명]
	 * @param prcSectCd [설명]
	 * @param spcPrmTp [설명]
	 * @param sTrndSn [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendResultFoDTO selectStrendDetail(String strndTp,String prcSectCd,String spcPrmTp,Integer sTrndSn, SystemPK pk) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param strndTp [설명]
	 * @param prcSectCd [설명]
	 * @param sTrndSn [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendResultFoDTO selectStrendDetail(String strndTp,String prcSectCd,Integer sTrndSn, SystemPK pk) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dspStrendSearchFoDTO [설명]
	 * @param pageParam [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendResultFoDTO selectCoordiList( DspStrendSearchFoDTO dspStrendSearchFoDTO, PageParam pageParam,SystemPK pk) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @param pageParam [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendResultFoDTO selectMagazineList(
			DspStrendSearchFoDTO searchDTO, PageParam pageParam, SystemPK pk)  throws Exception;

	/**
	 * My Brand 브랜드카테고리명 조회.
	 *
	 * @param dspCtgryNos the dsp ctgry nos
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgry> selectMyBrand(List<String> dspCtgryNos) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return Gnb result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public GnbResultFoDTO selectGnbView(SystemPK pk,  DspCtgrySearchFoDTO searchDTO) throws Exception;


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return Sys stplat [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public SysStplat selectMembershipInfo(SystemPK pk) throws Exception;


	/**
	 * @Method Name : selectSysStplatCont
	 * @작성일 : 2017. 11. 30.
	 * @작성자 : steven
	 * @Method 설명 : 시스템약관 불러오기
	 * @param pk
	 * @param stplatCd
	 * @return
	 * @throws Exception
	 */
	public SysStplat selectSysStplatCont(SystemPK pk, String stplatCd) throws Exception;
	
	/**
	 * @Method Name : selectSysStplatHist
	 * @작성일 : 2018. 06. 14.
	 * @작성자 : muba
	 * @Method 설명 : 시스템약관 history 영문 불러오기
	 * @param pk
	 * @param stplatCd
	 * @return
	 * @throws Exception
	 */
	public List<SysStplatHistExtends> selectSysStplatHist(SystemPK pk, String stplatCd) throws Exception;	

	/* [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return Sys stplat [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public SysStplat selectPurpleCoinTermsAndConditions(SystemPK pk) throws Exception;


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return Sys stplat [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public SysStplat selectPrivacyPolicy(SystemPK pk) throws Exception;

	/**
	 * @Method Name : selectPrivacyPolicyHistDt
	 * @작성일 : 2017. 7. 4.
	 * @작성자 : steven
	 * @Method 설명 : 개인정보처리방침 히스토리 날짜 가져오기
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public List<SysStplatHistExtends> selectPrivacyPolicyHistDt(SystemPK pk) throws Exception;

	public SysStplat selectOldPrivacyPolicy(SystemPK pk, String dataVersion) throws Exception;


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @return Sys stplat [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public SysStplat selectNoEmailPolicy(SystemPK pk) throws Exception;


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param promtSn [설명]
	 * @param mode [설명]
	 * @param pk [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public String selectB2eCode(Integer promtSn, String mode,
								SystemPK pk) throws Exception;


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return Gnb result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public GnbResultFoDTO selectSiteView(SystemPK pk,
										 DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * 전시 카테고리 정보 조회(모바일용)
	 * <p/>
	 *
	 * 모바일에서는 전시 카테고리 정보만 조회.
	 *
	 * @param pk [설명]
	 * @param dto [설명]
	 * @return Dsp ctgry fo result [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 3
	 */
	public DspCtgryFoResult selectDisplayCategoryInfo(SystemPK pk, DspCtgrySearchFoDTO dto) throws Exception;

	/**
	 * 전시 카테고리에 속한 코너 정보 조회
	 *
	 * <p/>
	 *
	 * 모바일에서는 전시 카테고리에 속한 코너 정보만 별도 조회.
	 *
	 * @param pk [설명]
	 * @param dto [설명]
	 * @return Dsp cnr fo result [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 3
	 */
	public DspCnrFoResult selectDspCnrDefaultInfo(SystemPK pk, DspCnrSearchFoDTO dto) throws Exception;



	/**
	 * InStore 브랜드 베스트셀러 상품 목록 조회.
	 *
	 * @param brndBstGod [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	//public List<God> selectBrndBestGod(DspBrndBstGod brndBstGod) throws Exception;

	/**
	 * 모바일용 기획전 정보 조회
	 *
	 * <p/>.
	 *
	 * @param searchDTO [설명]
	 * @param pk [설명]
	 * @param isFilter Filter 부분 조회 여부(true, false)
	 * @return the plan brandy view for mb
	 * @throws Exception the exception
	 * @since 2015. 6. 24
	 */
	public DspPlanResultFoDTO getPlanBrandyViewForMB(DspPlanSearchFoDTO searchDTO, SystemPK pk, boolean isFilter) throws Exception;


	/**
	 * 모바일 카테고리 조회 .
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspCtgryResultMbDTO> selectMobileDisplayCategoryList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * 모바일 상품 리스트 조회 .
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return Goods list mb result dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public GoodsListMbResultDTO selectMobileGoodsList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public int selectGoodsCount(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return Map [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public Map<String, Object> selectGoodsCSMS(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * 전시카테고리 2D 상품리스트 (FODP010100) 정보 조회(모바일용)
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return Dsp ctgry result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 20
	 */
	public DspCtgryResultFoDTO selectDspCtgryViewForMB(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * 모바일용
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param strndTp [설명]
	 * @param prcSectCd [설명]
	 * @param sTrndSn [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 20
	 */
	public DspStrendResultFoDTO selectStrendDetailForMB(String strndTp,String prcSectCd,Integer sTrndSn, SystemPK pk) throws Exception;

	/**
	 * 모바일용
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @param pageParam [설명]
	 * @param pk [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 20
	 */
	public DspStrendResultFoDTO selectStrendForMB(DspStrendSearchFoDTO searchDTO, PageParam pageParam, SystemPK pk) throws Exception;

	/**
	 * 모바일용
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param searchDTO [설명]
	 * @return Gnb result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 21
	 */
	public GnbResultFoDTO selectGnbViewForMB(SystemPK pk,  DspCtgrySearchFoDTO searchDTO) throws Exception;

	/**
	 * 모바일용
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param pk [설명]
	 * @param dspCnrResultList [설명]
	 * @param serialNumber [설명]
	 * @param tmplatSn [설명]
	 * @param parentType [설명]
	 * @param prcSectCd [설명]
	 * @param spcPrmTp [설명]
	 * @param screenNo [설명]
	 * @param brandIds [설명]
	 * @return Display page result dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 27
	 */
	public DisplayPageResultDTO selectDisplayPageViewForMB(SystemPK pk, List<DspCnrFoResult> dspCnrResultList, String serialNumber, Long tmplatSn, String parentType, String prcSectCd, String spcPrmTp, String screenNo, String[] brandIds) throws Exception;

	/**
	 * 딜형프로모션(이벤트) 기획전 정보 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @param promtSn [설명]
	 * @param mode [설명]
	 * @param pk [설명]
	 * @return Dsp plan detail result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 27
	 */
	public DspPlanDetailResultFoDTO selectPlanEvent (DspPlanSearchFoDTO searchDTO,Integer promtSn,String mode,SystemPK pk) throws Exception;

	/**
	 * 딜형프로모션(이벤트) 기획전 번호 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param listPromtSn [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 9. 3
	 */
	public Integer selectPickPromtSn(List<Integer> listPromtSn) throws Exception;

	/**
	 * 기획전의 노출브랜드 정보 조회 (Beanpole,8Seconds,Beaker,Homme,Other 순으로 최상위 1건).
	 *
	 * @param promtSn the promt sn
	 * @param pk the pk
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectPromtMdBrnd(Integer promtSn, SystemPK pk) throws Exception;

	/**
	 * 모바일용 Left Menu 브랜드카테고리 목록 조회.
	 *
	 * @param pk the pk
	 * @param dto the dto
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DspCtgryFoResult> selectLeftMenuCtgryForMB(SystemPK pk,DspCtgrySearchFoDTO dto) throws Exception;

	/**
	 * 자사 브랜드 / 알파벳 순 정렬
	 *
	 * @param brandInfoResult
	 * @return
	 * @throws Exception
	 */
	public List<BrandResultDTO> selectSortedBrandInfoList(List<SysBrnd> brandInfoResult) throws Exception;


	/**
	 * 코너 정보 조회를 위한 DTO 정보 세팅
	 * @param dspCnrScFrDTO
	 * @param pk
	 * @throws Exception
	 */
	public void setDTOInfoForCnr(DspCnrScFrDTO dspCnrScFrDTO, SystemPK pk, GoodsPriceSearchDTO goodsPriceSearchFoDTO) throws Exception;

	/**
	 * 템플릿 유형, Key 정보 세팅
	 * @param dspCnrScFrDTO
	 * @param tmplatTp
	 * @param tmplatKey
	 * @throws Exception
	 */
	public void setTmplatInfo(DspCnrScFrDTO dspCnrScFrDTO, String tmplatTp, String tmplatKey) throws Exception;

	/**
	 *	카테고리 정보 조회를 위한 DTO 정보 세팅
	 * @param dspCtgrySearchFoDTO
	 * @param pk
	 * @param dspCtgryNo
	 * @throws Exception
	 */
	public void setDTOInfoForCtgry(DspCtgrySearchFoDTO dspCtgrySearchFoDTO, SystemPK pk, String dspCtgryNo ) throws Exception;

	public void setDTOInfoForGodSearch(DspCtgrySearchFoDTO dspCtgrySearchFoDTO, DspCtgryResultFoDTO dspCtgryInfo, HttpServletRequest request, GoodsPriceSearchDTO priceDTO, DspCtgryScFrDTO dspCtgryFrDTO) throws Exception;

	public int getGodCnrConttCnt(List<DspCnrFrResult> cnrList) throws  Exception;

	public int getGodCnrConttCntMobile(List<DspCnrFrResult> cnrList) throws  Exception;

	public DspCtgryResultFoDTO selectDspCtgryInfo(SystemPK pk, GoodsPriceSearchDTO priceSearchFoDTO, String brandShopNo) throws Exception;

	public DspCtgryResultFoDTO selectDspCtgryGodList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO, SystemPK pk) throws Exception;

	public DspCtgryResultFoDTO selectDisplayCategoryFoInfo(SystemPK pk, String dspCtgryNo, String dspBrndId, String mallGubun) throws Exception;
	/**
	 * 검색 전시카테고리의 상품정렬코드 조회
	 *
	 * @param SystemPK
	 * @param DspCtgrySearchFoDTO
	 * @return getDspCtgryGodSortCdInfo
	 * @author sy1985.kim
	 * @throws Exception
	 */
	public String getDspCtgryGodSortCdInfo(SystemPK pk, DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception;
	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI)
	///////////////////////////////////////////////////////////////////////////////////////////////////
	public DspCtgryResultFoDTO selectCtgryListV2(String dspCtgryNo, String ctgrySectCd) throws Exception;

	public DspCnrGodFoResult selectTopSellers(String dspCtgryNo, String brndId, GoodsPriceSearchDTO priceDTO, SystemPK pk, String cnrDscr, int imgTurn) throws Exception;

	public DspCtgryResultFoDTO selectNewIn(String dspCtgryNo, String brndId, GoodsPriceSearchDTO priceDTO, SystemPK pk, int imgTurn) throws Exception;

	public DspCtgryResultFoDTO selectOnSale(String dspCtgryNo, String brndId, GoodsPriceSearchDTO priceDTO, SystemPK pk, int imgTurn) throws Exception;

	/**
	 * 모바일 라이프스타일 섹션 내 신상품, 세일상품, 인기상품 코너 조회
	 * @param menCtgryNo
	 * @param womenCtgryNo
	 * @param brndId
	 * @param priceDTO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public DspCtgryResultFoDTO selectNewSaleTopLifestyle(String menCtgryNo, String womenCtgryNo, String brndId, GoodsPriceSearchDTO priceDTO, SystemPK pk) throws Exception;

	public List<String> selectCtgryCnncGodNo(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception;

	public List<String> selectTopNewSaleGodNo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public List<DspCtgryFoResult> selectLowerCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public List<String> selectCtgryNmListPC(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception;

	public List<String> selectCtgryNmList(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception;

	public DspCtgryResultFoDTO selectRecommendationList(String godNoList, GoodsPriceSearchDTO priceSearchFoDTO, SystemPK pk) throws Exception;
	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dspStrendSearchFoDTO [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspStrendFoResult> selectGetTheStylePreNextList(
			DspStrendSearchFoDTO searchDTO)  throws Exception;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param selectGetTheStyleLNBList [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspStrendFoResult> selectGetTheStyleLNBList(
			DspStrendSearchFoDTO searchDTO) throws Exception;;

	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param selectGetTheStyleDetail [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendFoResult selectGetTheStyleDetail(
			DspStrendSearchFoDTO searchDTO) throws Exception;;
	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param selectGetTheStyleList [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public DspStrendResultFoDTO selectGetTheStyleList(
			DspStrendSearchFoDTO searchDTO,PageParam pageParam) throws Exception;;
	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param selectGetLatestStrndSnOfGetThestyle [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public Integer selectLatestStrndSnOfGetTheStyle(
			DspStrendSearchFoDTO searchDTO) throws Exception;


	/**
	 * [메서드 설명].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param selectRecommendGodMoreByGTS [설명]
	 * @return Dsp strend result fo dto [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 8
	 */
	public List<DspStrendFoResult> selectRecommendGodMoreOfGTS(
			DspStrendSearchFoDTO searchDTO);

	public int getCornerImgTurn(List<DspCnrFrResult> cnrList, String cnrDscr) throws Exception;

}

