package com.plgrim.ncp.biz.settlement.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventBrnd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventCom;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventExclsGod;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventBasicFormDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventFormDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventSearchDTO;
import com.plgrim.ncp.biz.settlement.result.PrmFeeEventListResult;
import com.plgrim.ncp.framework.page.PageParam;

@Repository
public class SettlementPrmFeeEventRepository extends AbstractRepository  {

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
	 * 수수료행사관리 리스트 건수
	 * @param settlementSearchDTO
	 * @return
	 * @throws Exception
	 */
	public long selectPrmFeeEventListCount(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventListCount", prmFeeEventSearchDTO);
	}
	
	/**
	 * 수수료행사관리 리스트
	 * @param settlementSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<PrmFeeEventListResult> selectPrmFeeEventList(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventList", prmFeeEventSearchDTO);
	}

	/**
	 * 수수료행사관리 리스트 엑셀
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectPrmFeeEventListExcel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventListExcel", prmFeeEventSearchDTO);
	}
	
	/**
	 * 수수료행사관리 상세리스트 엑셀
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectPrmFeeEventDetailListExcel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventDetailListExcel", prmFeeEventSearchDTO);
	}
	
	/**
	 * 제휴대행사 업체 리스트
	 * @return
	 * @throws Exception
	 */
	public List<Com> selectPrmFeeEventAffAgncCom() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventAffAgncCom");
	}
	
	/**
	 * 제휴사, 입점사, 제휴대행사 해당 브랜드
	 * @param prmFeeEventFormDTO
	 * @return
	 * @throws Exception
	 */
	public List<PrmFeeEventListResult> selectPrmFeeEventBrandList(PrmFeeEventFormDTO prmFeeEventFormDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventBrandList", prmFeeEventFormDTO);
	}
	
	/**
	 * 제휴사 선택 브랜드
	 * @param prmFeeEventFormDTO
	 * @return
	 * @throws Exception
	 */
	public List<PrmFeeEventListResult> selectPrmFeeEventBrandList2(PrmFeeEventFormDTO prmFeeEventFormDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventBrandList2", prmFeeEventFormDTO);
	}
	
	
	/**
	 * 수수료행사 정보 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public int insertPrmFeeEvent(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.settlement.prm.fee.event.insertPrmFeeEvent", prmFeeEventFormDTO);
	}
	
	/**
	 * 수수료행사 (대용량)상품정보 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public int insertPrmFeeEventGoods(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.settlement.prm.fee.event.insertPrmFeeEventGoods", prmFeeEventFormDTO);
	}
	
	/**
	 * 수수료행사 업체 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public int insertPrmFeeEventCom(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.settlement.prm.fee.event.insertPrmFeeEventCom", prmFeeEventFormDTO);
	}
	
	/**
	 * 수수료행사 브랜드 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public int insertPrmFeeEventBrnd(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.settlement.prm.fee.event.insertPrmFeeEventBrnd", prmFeeEventFormDTO);
	}
	
	/**
	 * 수수료행사 상품 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public int insertPrmFeeEventAplGod(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.settlement.prm.fee.event.insertPrmFeeEventAplGod", prmFeeEventFormDTO);
	}
	
	/**
	 * 수수료행사 예외상품 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public int insertPrmFeeEventExclsGod(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.settlement.prm.fee.event.insertPrmFeeEventExclsGod", prmFeeEventFormDTO);
	}
	
	/**
	 * 이벤트 상품 리스트(View)
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<PrmFeeEventListResult> selectPrmFeeEventAplGodListOld(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventAplGodListOld", prmFeeEventSearchDTO);
	}
	
	/**
	 * 이벤트 상품 리스트(Edit)
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public Page<PrmFeeEventListResult> selectPrmFeeEventAplGodList(PrmFeeEventSearchDTO prmFeeEventSearchDTO,PageParam pageParam) throws Exception {
		RepositoryHelper.makePageEntityIndex(prmFeeEventSearchDTO, pageParam);
		List<PrmFeeEventListResult> results = getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventAplGodList", prmFeeEventSearchDTO);
		
		// 전체 Row 수 구하기
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventAplGodListCount", prmFeeEventSearchDTO);
		return new PageImpl<PrmFeeEventListResult>(results, pageParam.getPageable(), totalRow);
	}
	
	/**
	 * 이벤트 상품 리스트(Edit) 엑셀
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectPrmFeeEventAplGodList2Excel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventAplGodListExcel", prmFeeEventSearchDTO);
	}
	
	/**
	 * 예외 상품 리스트
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<PrmFeeEventListResult> selectPrmFeeEventExclsGodList(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventExclsGodList", prmFeeEventSearchDTO);
	}
	
	/**
	 * 행사대상 브랜드
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<PrmFeeEventListResult> selectPrmFeeEventTargetBrandList(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventTargetBrandList", prmFeeEventSearchDTO);
	}
	
	/**
	 * 수수료행사 정보
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public PrmFeeEventListResult selectPrmFeeEvent(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEvent", prmFeeEventSearchDTO);
	}
	
	/**
	 * 업체별 행사대상 브랜드
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<PrmFeeEventListResult> selectPrmFeeEventTargetComBrandList(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventTargetComBrandList", prmFeeEventSearchDTO);
	}
	
	/**
	 * 행사 업체 리스트
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Com> selectPrmFeeEventTargetComList(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.settlement.prm.fee.event.selectPrmFeeEventTargetComList", prmFeeEventSearchDTO);
	}
	
	/**
	 * 예외상품 merge
	 * @param prmFeeEventExclsGod
	 * @throws Exception
	 */
	public void mergePrmFeeEventExclsGod(PrmFeeEventExclsGod prmFeeEventExclsGod)throws Exception {
		getSession1().update("com.plgrim.ncp.biz.settlement.prm.fee.event.mergePrmFeeEventExclsGod", prmFeeEventExclsGod);
	}
	
	/**
	 * 행사상품 삭제
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public void deletePrmFeeEventAplGod(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.settlement.prm.fee.event.deletePrmFeeEventAplGod", prmFeeEventFormDTO);
	}
	
	/**
	 * 예외상품 삭제 
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public void deletePrmfeeEventExclsGod(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.settlement.prm.fee.event.deletePrmfeeEventExclsGod", prmFeeEventFormDTO);
	}
	
	/**
	 * 수수료행사 저장
	 * @param prmFeeEventBasicFormDTO
	 * @throws Exception
	 */
	public void updatePrmFeeEventList(PrmFeeEventBasicFormDTO prmFeeEventBasicFormDTO)throws Exception {
		getSession1().update("com.plgrim.ncp.biz.settlement.prm.fee.event.updatePrmFeeEventList", prmFeeEventBasicFormDTO);
	}
	
	/**
	 * 수수료행사 상세팝업 저장
	 * @param prmFeeEventFormDTO
	 * @throws Exception
	 */
	public void updatePrmFeeEvent(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
		getSession1().update("com.plgrim.ncp.biz.settlement.prm.fee.event.updatePrmFeeEvent", prmFeeEventFormDTO);
	}

	public int selectPrmFeeEventCom(PrmFeeEventCom prmFeeEventCom)throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.settlement.prm.fee.event.selectCom", prmFeeEventCom);
    }

	public int selectPrmFeeEventBrnd(PrmFeeEventBrnd prmFeeEventBrnd)throws Exception  {
		return getSession1().selectOne("com.plgrim.ncp.biz.settlement.prm.fee.event.selectSysBrnd", prmFeeEventBrnd);
    }

	public God selectGod(God god) {
		return getSession1().selectOne("com.plgrim.ncp.biz.settlement.prm.fee.event.selectErpGod", god);
    }
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
