package com.plgrim.ncp.biz.display.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTmplatInfoCnnc;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfig;
import com.plgrim.ncp.biz.display.data.DspAbTestBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttAbTestDTO;
import com.plgrim.ncp.biz.display.result.DspAbTestResult;
import com.plgrim.ncp.biz.display.result.DspAbTestRevResult;
import com.plgrim.ncp.biz.display.result.DspAbTestSetFlterResult;
import com.plgrim.ncp.biz.display.result.DspCnrTmplatInfoCnncResult;
import com.plgrim.ncp.biz.display.result.DspConttAbTestResult;
import com.plgrim.ncp.biz.display.result.DspRevCpstResult;
import com.plgrim.ncp.framework.page.PageParam;


/**
 * 전시 개정 Select Repository
 */
@Repository
public class DisplayRevSelectRepository extends AbstractRepository {
	
	/**
	 * AB Test 목록 조회
	 *
	 * @param dspAbTestDTO the dsp ab test dto
	 * @param pageParam the page param
	 * @return the list
	 * @ the exception
	 */
	public List<DspAbTestResult> selectAbTestList(DspAbTestBoDTO dspAbTestDTO, PageParam pageParam)  {
		//페이지 인덱스 셋팅
		RepositoryHelper.makePageEntityIndex(dspAbTestDTO, pageParam);
		
		List<DspAbTestResult> results = getSession1().selectList("com.plgrim.ncp.biz.display.selectAbTestList", dspAbTestDTO);
		
		return results;
    }
	
	/**
	 * AB Test 목록 총 Count.
	 *
	 * @param dspAbTestDTO the dsp ab test dto
	 * @return the integer
	 * @ the exception
	 */
	public Integer selectAbTestTotalCount(DspAbTestBoDTO dspAbTestDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectAbTestTotalCount", dspAbTestDTO);
    }

	public DspAbTestResult selectAbTestDetail(int abTestSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectAbTestDetail", abTestSn);
	}

	/**
	 *  특정 페이지 (KEY)에 존재하는 ABTEST 존재여부 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the string
	 * @ the exception
	 */
	public String selectPageAbTestCount(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPageAbTestCount", dspCnrConttScBoDTO);
    }
	
	/**
	 *  특정 KEY에 존재하는 리비전목록 조회 
	 *
 	 * @param dspCnrConttScBoDTO
	 * @return
	 * @
	 */
	public List<DspRevCpstResult> selectRevCpstList(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		List<DspRevCpstResult> results = getSession1().selectList("com.plgrim.ncp.biz.display.selectRevCpstList", dspCnrConttScBoDTO);

		return results;
	}
	
	/**
	 * 특정 KEY에 존재하는 BASE 리비전 조회 
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the dsp rev cpst result
	 */
	public DspRevCpstResult selectBaseRev(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		DspRevCpstResult result = getSession1().selectOne("com.plgrim.ncp.biz.display.selectBaseRev", dspCnrConttScBoDTO);

		return result;
	}

	/**
	 * BO 접속 관리자의 미리보기 리비전 번호 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp rev
	 * @return the integer
	 * @ the exception
	 */
	public Long selectRevByAdmin(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectRevByAdmin", dspCnrConttScBoDTO);
    }
	
	
	/**
	 *  BO 접속 관리자의 특정페이지 미리보기 리비전 번호 조회
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo dto
	 * @return the long
	 * @ the exception
	 */
	public Long selectPageRevByAdmin(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPageRevByAdmin", dspCnrConttScBoDTO);
    }
	
	
	/**
	 * 페이지 리비전 존재여부 조회
	 *
	 * @param dspRevCpst the dsp rev cpst
	 * @return the integer
	 * @ the exception
	 */
	public Integer selectDspRevCpstOne(DspRevCpst dspRevCpst)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspRevCpstOne", dspRevCpst);
    }

	/**
	 * 리비전 복사 대상 코너 존재여부
	 *
	 * @param dspCnrConttScBoDTO
	 * @return
	 * @
	 */
	public Integer checkDspCnrRev(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.checkDspCnrRev", dspCnrConttScBoDTO);
	}
	
	/**
	 * 리비전 복사 대상 코너 목록
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the dsp cnr rev list
	 */
	public List<Long> getDspCnrRevList(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectList("com.plgrim.ncp.biz.display.getDspCnrRevList", dspCnrConttScBoDTO);
	}

	/**
	 * 리비전 복사 대상 코너컨텐츠유형 존재여부
	 *
	 * @param dspCnrConttScBoDTO
	 * @return
	 * @
	 */
	public Integer checkDspCnrTpGrpRev(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.checkDspCnrTpGrpRev", dspCnrConttScBoDTO);
	}

	/**
	 * 미리보기 URL 조회
	 *
	 * @param dspCnrConttScBoDTO
	 * @return
	 * @
	 */
	public DspRevCpst selectPreviewUrl(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPreviewUrl", dspCnrConttScBoDTO);
	}
	
	/**
	 * 컨텐츠 관리의 AB-TEST 리비전 목록
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the list
	 * @ the exception
	 */
	public DspConttAbTestResult selectConttAbTestRevList(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectConttAbTestRevList", dspCnrConttScBoDTO);
	}
	
	/**
	 * A/B테스트 분석결과 데이터 존재여부 체크
	 *
	 * @param dspAbTest the dsp ab test
	 * @return the integer
	 */
	public Integer selectAbTestAnlCount(DspAbTest dspAbTest)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectAbTestAnlCount", dspAbTest);
	}
	
	/**
	 * AB테스트 리비전 등록 시 추가 템플릿 연결정보 목록조회
	 *
	 * @param dspCnrTmplatInfoCnnc the dsp cnr tmplat info cnnc
	 * @return the list
	 * @ the exception
	 */
	public List<DspCnrTmplatInfoCnncResult> selectCnrTmplatInfoCnncRevList(DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc)  {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectCnrTmplatInfoCnncRevList", dspCnrTmplatInfoCnnc);
	}
	
	/**
	 * AB테스트 세트존재여부 확인
	 *
	 * @param dspAbTestSet the dsp ab test set
	 * @return the integer
	 * @ the exception
	 */
	public Integer selectAbTestSetCount(DspAbTestSet dspAbTestSet)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectAbTestSetCount", dspAbTestSet);
	}
	
	/**
	 * Select cstmr sgmt percent.
	 *
	 * @param dspConttAbTestDTO the dsp contt ab test DTO
	 * @return the int
	 * @ the exception
	 */
	public int selectCstmrSgmtPercent(DspConttAbTestDTO dspConttAbTestDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectCstmrSgmtPercent", dspConttAbTestDTO);
	}
	
	/**
	 * 현재 mod turn에 해당하는 abtest 개정 목록 조회.
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the integer
	 * @ the exception
	 */
	public List<DspAbTestRevResult> selectDspAbTestRevListByMod(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspAbTestRevListByMod", dspCnrConttScBoDTO);
	}

	/**
	 * AB테스트 개정 count 조회
	 *
	 * @param dspAbTestRev the dsp ab test rev
	 * @return the int
	 * @ the exception
	 */
	public int selectDspAbTestRevCount(DspAbTestRev dspAbTestRev)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectDspAbTestRevCount", dspAbTestRev);
	}


	/**
	 * A/B테스트 시작일, 종료일 변경 시 Dynamic Config 수정을 위해 목록 조회
	 *
	 * @param sysDynmcConfig the sys dynmc config
	 * @return the list
	 * @ the exception
	 */
	public List<SysDynmcConfig> selectAbTestSysConfigList(SysDynmcConfig sysDynmcConfig)  {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectAbTestSysConfigList", sysDynmcConfig);
	}

	/**
	 * A/B테스트 리비전별 글로벌 컨텐츠 존재여부 체크
	 *
	 * @param dspCnrConttScBoDTO the dsp cnr contt sc bo DTO
	 * @return the integer
	 * @ the exception
	 */
	public Integer selectGlobalConttCntRev(DspCnrConttScBoDTO dspCnrConttScBoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectGlobalConttCntRev", dspCnrConttScBoDTO);
	}

	public DspConttAbTestResult selectAbTestSetModList(int abTestSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectAbTestSetModList", abTestSn);
	}

	public String selectMallId(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectMallId", dspCnrConttScBoDTO);
	}

	public List<DspAbTestSetFlterResult> selectDspAbTestSetFilter(DspAbTestSetFlter dspAbTestSetFlter) {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectDspAbTestSetFlterList", dspAbTestSetFlter);
	}

	public String selectBaseRevTmplatSn(DspCnrConttScBoDTO dspCnrConttScBoDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectBaseRevTmplatSn", dspCnrConttScBoDTO);
	}
}
