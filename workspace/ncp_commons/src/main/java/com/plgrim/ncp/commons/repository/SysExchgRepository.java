package com.plgrim.ncp.commons.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExchgRt;
import com.plgrim.ncp.commons.data.FormSysExchgDTO;
import com.plgrim.ncp.commons.data.SysExchgDTO;
import com.plgrim.ncp.commons.result.SysExchgResult;

import lombok.extern.slf4j.Slf4j;

/**
 * SysExchg Repository.
 *
 * @author Dennis
 */

@Slf4j
@Repository
public class SysExchgRepository extends AbstractRepository {

	/**
	 * SysExchg list 조회.
	 * @param FormSysCodeDTO
	 * @return
	 */
	
	public List<SysExchgResult> selectSysExchgList ( FormSysExchgDTO paramData ) throws Exception {
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.code.getSysExchgList", paramData);
		
	}
	
	/**
	 * Exchg Rate 저장 처리
	 * @param paramData
	 */
	
	public void mergeExchg ( SysExchgDTO paramData) throws Exception {

		log.info(paramData.toString());
		/* 추후에 추가 */
		//getSession1().update("com.plgrim.ncp.commons.code.mergeExchg", paramData);
		
	}
	
	/**
	 * 환율 목록 엑셀.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysExchgListExcel(FormSysExchgDTO paramData) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.commons.code.selectSysExchgListExcel", paramData);
    }
	
	/**
	 * 환율 목록 개수.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysExchgListCount(FormSysExchgDTO paramData) throws Exception {
	    return getSession1().selectOne("com.plgrim.ncp.commons.code.selectSysExchgListCount", paramData);
    }
	
	/**
	 * 환율 기존 적용건 종료처리.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int updateAplEndDt(SysExchgRt paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.code.updateAplEndDt", paramData);
    }
	
	/**
	 * 환율 등록.
	 *
	 * @param sysExchgRt the SysExchgRt
	 * @throws SQLException the SQL exception
	 */
	public int insertSysExchgRt(SysExchgRt sysExchgRt) throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.code.insertSysExchgRt", sysExchgRt);
	}
	
	/**
	 * 환율 중복 개수.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysExchgDupCount(SysExchgRt paramData) throws Exception {
	    return getSession1().selectOne("com.plgrim.ncp.commons.code.selectSysExchgDupCount", paramData);
    }

	/**
	 * 현재 적용 환율 조회
	 *
	 * @param crncyCd [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public SysExchgRt selectSysExchgCrncyCd(String crncyCd) throws Exception {
	    return getSession1().selectOne("com.plgrim.ncp.commons.code.selectSysExchgCrncyCd", crncyCd);
    }

	/**
	 * 환율 적용 후 전시 상품 가격 프로시저 수동 실행
	 *
	 * @throws Exception
	 */
	public void updateDspGoodsPriceProcedure() throws Exception {
		getSession1().selectOne("com.plgrim.ncp.commons.code.updateDspGoodsPriceProcedure");
	}
}
