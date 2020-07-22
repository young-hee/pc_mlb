package com.plgrim.ncp.biz.display.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;
import com.plgrim.ncp.base.repository.dsp.DspStrndRepository;
import com.plgrim.ncp.biz.display.data.DspStrendSearchFoDTO;
import com.plgrim.ncp.biz.display.result.DspStrendFoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.google.common.collect.Maps;

@Repository
public class DisplayStrendRepository extends DspStrndRepository {

	

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
	public List<DspStrendFoResult> selectStrendCorner(
            DspStrendSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectStrendCorner",searchDTO);
    }
	
	public long selectStrendCount(DspStrendSearchFoDTO searchDTO){
		
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectStrendCount",searchDTO);
	}
	
	public List<DspStrendFoResult> selectStrendList(
            DspStrendSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
	    return getSession1().selectList("com.plgrim.ncp.biz.display.selectStrendList",searchDTO);
    }
	
	public List<DspStrendFoResult> selectStrendBrand(
            DspStrendSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
	    return getSession1().selectList("com.plgrim.ncp.biz.display.selectStrendBrand",searchDTO);
    }
	
	public List<DspStrendFoResult> selectStrendSeason(
			DspStrendSearchFoDTO searchDTO) {
		// TODO Auto-generated method stub
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectStrendSeason",searchDTO);
	}

	/**
	 * S-TREND 목록 그리드 일괄수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrnd [설명]
	 * @since 2015. 5. 22
	 */
	public void updateStrndInfo(DspStrnd dspStrnd) {
		getSession1().update("com.plgrim.ncp.biz.display.updateStrndInfo", dspStrnd);
	}
	
	/**
	 * 그리드에서 S-TREND 정보 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrnd [설명]
	 * @since 2015. 5. 29
	 */
	public void updateStrndForGrid(DspStrnd dspStrnd) {
		getSession1().update("com.plgrim.ncp.biz.display.updateStrndForGrid", dspStrnd);
	}
	
	/**
	 * S-TREND 번호
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
		long strndSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_STRND",
                DatabaseType.ORACLE).longValue();
		
		return strndSn;
	}
	
	/**
	 * S-TREND 언어 mearge
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int saveDspStrndLangInfo(DspStrndLang dspStrndLang) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.display.saveDspStrndLangInfo", dspStrndLang);
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
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspStrndLangCnt", dspStrndLang);
	}
	
	/**
	 * S-TREND 언어 - 기획전명 수정 .
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
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspStrndLangNm", dspStrndLang);
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
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspStrndLangImg", dspStrndLang);
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
		return getSession1().update("com.plgrim.ncp.biz.display.updateDspStrndLangImg2", dspStrndLang);
	}
	
	/**
	 * S-TREND 언어 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int deleteDspStrndLangInfo(DspStrndLang dspStrndLang) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspStrndLangInfo", dspStrndLang);
	}
	
	/**
	 * 다음 strend
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndLang [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public int selectNextStrend(DspStrendSearchFoDTO searchDTO) {
	    // TODO Auto-generated method stub
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectNextStrend", searchDTO);
    }
	
	/**
	 * S-TREND 전시대상 순번생성.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspStrndDspTgt [설명]
	 * @return Integer [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public Integer getDspStrndDspTgtTurn(DspStrndDspTgt dspStrndDspTgt) throws Exception {
		
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("STRND_SN", dspStrndDspTgt.getStrndSn());
		
		Integer dspTgtTurn = getIdGenService().generateDBOrder(getSession1(), "DSP_STRND_DSP_TGT", "DSP_TGT_TURN", conditions, DatabaseType.ORACLE);
		
		return dspTgtTurn;
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
	public int deleteDspStrndDspTgtInfo(DspStrndDspTgt dspStrndDspTgt) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.display.deleteDspStrndDspTgtInfo", dspStrndDspTgt);
	}
	
	
	/**
	 * [S-TREND] GET THE STYLE 이전글4개/다음글4개.
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
	public List<DspStrendFoResult> selectGetTheStylePreNextList(DspStrendSearchFoDTO searchDTO) {
		
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGetTheStylePreNextList", searchDTO);
	}
	/**
	 * [S-TREND] GET THE STYLE LNBLIST
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
	public List<DspStrendFoResult> selectGetTheStyleLNBList(DspStrendSearchFoDTO searchDTO) {
		
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGetTheStyleLNBList", searchDTO);
	}
	/**
	 * [S-TREND] GET THE STYLE Detail
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
	public DspStrendFoResult selectGetTheStyleDetail(DspStrendSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectGetTheStyleDetail", searchDTO);
	}

	public List<DspStrendFoResult> selectGetTheStyleList(DspStrendSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGetTheStyleList", searchDTO);
	}

	public int selectGetTheStyleCount(DspStrendSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectGetTheStyleCount", searchDTO);
	}
	public int selectLatestStrndSnOfGetTheStyle(DspStrendSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectLatestStrndSnOfGetTheStyle", searchDTO);
	}
	public List<DspStrendFoResult> selectRecommendGodMoreOfGTS(DspStrendSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectRecommendGodMoreOfGTS", searchDTO);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
	

}
