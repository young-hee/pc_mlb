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
 * @since       2015. 4. 9       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTpGrp;
import com.plgrim.ncp.base.repository.dsp.DspCnrRepository;
import com.plgrim.ncp.biz.display.data.DspCnrSearchFoDTO;
import com.plgrim.ncp.biz.display.result.DspCnrContt2FoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttGodFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttPromtFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttStrndFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.GodColorFoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;


/**
 * 코너관리를 DB처리하는 Repository
 * 
 * <p>
 * 
 * <ul>
 *   <li> 코너목록 조회
 *   <li> 코너등록/수정/삭제
 *   <li> 코너 컨텐츠 유형 그룹 등록/수정/삭제
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2015. 3. 13
 */
@Repository
@Slf4j
public class DisplayCornerRepository extends DspCnrRepository {

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
	 * 코너 기본정보 등록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param pk SystemPK
	 * @param dspCnr 코너 정보 Entity
	 * @return Long 생성된 코너 일련번호
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public long insertCornerInfo(DspCnr dspCnr) throws Exception {
		//코너 일련번호 SEQ생성
		long cnrSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_CNR",
                DatabaseType.ORACLE).longValue();
		
		dspCnr.setCnrSn(cnrSn);
		insertDspCnr(dspCnr);

		return cnrSn;
	}
	
	/**
	 * 코너정보 수정.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCnr 코너 정보 Entity
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 7
	 */
	public int updateDspCnrInfo(DspCnr dspCnr) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspCnrInfo", dspCnr);
	}
	
	/**
	 * 코너 삭제를 위해 해당 코너에 등록된 코너 컨텐츠 유형 그룹 삭제.
	 * 
	 * <p/>
	 * 
	 * .
	 *
	 * @param dspCnrTpGrp 코너컨텐츠유형그룹 Entity
	 * @return Int 처리결과수
	 * @throws Exception the exception
	 * @since 2015. 4. 9
	 */
	public int deleteDspCnrTpGrpByCnrSn(DspCnrTpGrp dspCnrTpGrp) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspCnrTpGrpByCnrSn", dspCnrTpGrp);
	}
	
	/**
	 * 전시코너의 기본정보 조회
	 * 
	 * @param pk
	 * @param dto
	 * @return
	 * @throws Exception
	 */
//	@Cacheable(
//			value="displayCornerRepository.selectDspCnrDefaultInfo",
//			key="{"
//					+ "#a0.lang,"
//					+ "#a1.dspCnr == null ? null : a1.dspCnr.cnrSn,"
//					+ "#a1.parentStringCd,"
//					+ "#a1.parentType,"
//					+ "#a1.parentIntCd"
//					+ "}")
	public DspCnrFoResult selectDspCnrDefaultInfo(SystemPK pk, DspCnrSearchFoDTO search) throws Exception{
		search.setMallId(pk.getMall());
		search.setLang(pk.getLang());
		search.setDevice(pk.getDevice());
		
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCnrFo", search);
	}
	
//	@Cacheable(
//			value="displayCornerRepository.selectDspCnrConttList",
//			key="{"
//					+ "#a0.device,"
//					+ "#a0.lang,"
//					+ "#a1.aplMbrAtrb,"
//					+ "#a1.aplMbrTp,"
//					+ "#a1.dspCnr == null ? null : a1.dspCnr.cnrSn,"
//					+ "#a1.search.dspCnrSet == null ? null : a1.search.dspCnrSet.cnrSetSn,"
//					+ "#a1.grpcoId"
//					+ "}")
	public List<DspCnrConttFoResult> selectDspCnrConttList(SystemPK pk, DspCnrSearchFoDTO search) throws Exception{
		
		search.setMallId(pk.getMall());
		search.setLang(pk.getLang());
		search.setDevice(pk.getDevice());
		
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttFoList", search);
	}

	public List<DspCnrContt2FoResult> selectDspCnrConttList2(SelectDspCnrConttList2Dto search) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttFullData", search);
	}

	@Data
	public static class SelectDspCnrConttList2Dto {
		String aplMbrAtrb;
		String aplMbrTp;
		String device;
		String grpcoId;
		String lang;
		String parentIntCd;
		String parentStringCd;
		String parentType;
		Long tmplatSn;
		public static SelectDspCnrConttList2Dto fromMap(Map<String, Object> map) {
			SelectDspCnrConttList2Dto search = new SelectDspCnrConttList2Dto();
			search.aplMbrAtrb = (String) map.get("aplMbrAtrb");
			search.aplMbrTp = (String) map.get("aplMbrTp");
			search.device = (String) map.get("device");
			search.grpcoId = (String) map.get("grpcoId");
			search.lang = (String) map.get("lang");
			search.parentIntCd = (String) map.get("parentIntCd");
			search.parentStringCd = (String) map.get("parentStringCd");
			search.parentType = (String) map.get("parentType");
			search.tmplatSn = (Long) map.get("tmplatSn");
			return search;
		}
	}

	public DspCnrConttGodFoResult selectDspCnrConttGod(SystemPK pk, DspCnrSearchFoDTO dto) throws Exception{

		dto.setMallId(pk.getMall());
		dto.setLang(pk.getLang());
		dto.setDevice(pk.getDevice());
		
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCnrConttGod", dto);
	}
	public DspCnrConttPromtFoResult selectDspCnrConttPromt(SystemPK pk, DspCnrSearchFoDTO dto) throws Exception{

		dto.setMallId(pk.getMall());
		dto.setLang(pk.getLang());
		dto.setDevice(pk.getDevice());
		
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCnrConttDspPromt", dto);
	}
	public DspCnrConttStrndFoResult selectDspCnrConttStnd(SystemPK pk, DspCnrSearchFoDTO dto) throws Exception{

		dto.setMallId(pk.getMall());
		dto.setLang(pk.getLang());
		dto.setDevice(pk.getDevice());
		
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspCnrConttStrnd", dto);
	}

//	@Cacheable(value="displayCornerRepository.dspCnrContentsCnncGod")
	public List<DspCnrConttGodFoResult> selectDspCnrConttGod2(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttGod2", map);
	}

//	@Cacheable(value="displayCornerRepository.dspCnrContentsCnncPromt")
	public List<DspCnrConttPromtFoResult> selectDspCnrConttPromt2(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttDspPromt2", map);
	}

//	@Cacheable(value="displayCornerRepository.dspCnrContentsCnncStrnd")
	public List<DspCnrConttStrndFoResult> selectDspCnrConttStnd2(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttSTrnd2", map);
	}

	public List<DspCnrConttStrndFoResult> selectDspCnrConttStnd3(Map<String,String> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttSTrnd3", map);
	}

	public List<DspCnrConttStrndFoResult> selectDspCnrConttStnd4(Map<String,String> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspCnrConttSTrnd4", map);
	}

//	@Cacheable(value="displayCategoryRepository.dspCnrSetList")
	public List<DspCnrFoResult> selectDspCnrSetByCnr(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCnrSetByCnr", map);
	}

//	@Cacheable(value="displayCategoryRepository.TopSellerAutoLoading")
	public List<String> selectTopSellerGodNoList1(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectTopSellerGodNoList1", map);
	}

	public List<String> selectBrandTopSellerGodNoList8s(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrandTopSellerGodNoList8s", map);
	}

	public List<String> selectBrandTopSellerGodNoListOthers(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrandTopSellerGodNoListOthers", map);
	}

//	@Cacheable(value="displayCategoryRepository.NewArrivalAutoLoading")
	public List<String> selectNewArrivalGodNoList1(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectNewArrivalGodNoList1", map);
	}

	public List<String> selectBrandNewArrivalGodNoList8s(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrandNewArrivalGodNoList8s", map);
	}

	public List<String> selectBrandNewArrivalGodNoListOthers(Map<String,Object> map) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectBrandNewArrivalGodNoListOthers", map);
	}

	@Cacheable(
			value="goodsRepository.getGoodsColorList", 
			key="{"
					+"#p0['lang'],"
					+"#p0['spcPrmTp'],"
					+"#p0['prcSectCd'],"
					+"#p0['dsgnGrpNo'],"
					+"#p0['language'],"
					+"#p0['mallId'],"
			+ "}")
	public List<GodColorFoResult> getGoodsColorList(Map<String,String> map) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.getGoodsColorList", map);
	}

	public Integer getGoodsOrdCnt(Map<String,Object> map) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.getGoodsOrdCnt", map);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	
}
