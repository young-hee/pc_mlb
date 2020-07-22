package com.plgrim.ncp.commons.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCdLang;
import com.plgrim.ncp.commons.data.FormSysPrudDTO;
import com.plgrim.ncp.commons.data.SysPrudDTO;
import com.plgrim.ncp.commons.result.SysPrudResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Sys Prud Repository.
 *
 * @author Dennis
 */

@Slf4j
@Repository
public class SysPrudRepository extends AbstractRepository {
	
	/*################################################################################################
	 * Select
	##################################################################################################*/
	
	/**
	 * Sys PrudCd list 조회.
	 * @param paramData
	 * @return
	 */
	public List<SysPrudResult> selectSysPrudCdList( FormSysPrudDTO  paramData) throws Exception {
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.code.getSysPrudCdList", paramData);
	}
	
	/**
	 * 품목코드 총 갯수 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountSysPrudCd( FormSysPrudDTO  paramData) throws Exception {
		log.info(paramData.toString()); 
		return getSession1().selectOne("com.plgrim.ncp.commons.code.selectCountSysPrudCd", paramData);
	}
	
	/**
	 * 품목코드 목록 엑셀
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectSysPrudCdListExcel(FormSysPrudDTO paramData) {
	    return getSession1().selectList("com.plgrim.ncp.commons.code.getSysPrudCdListExcel", paramData);
    }
	
	/*################################################################################################
	 * Command
	##################################################################################################*/
	
	/**
	 * Sys Prud Cd 저장 처리
	 * @param paramData
	 */
	
	public void mergePrudCd ( SysPrudDTO paramData ) throws Exception {
		log.info(paramData.toString());
		getSession1().update("com.plgrim.ncp.commons.code.mergePrudCd",paramData);
		
	}
	
    public void mergeGodWt ( SysPrudDTO paramData ) throws Exception {
        log.info(paramData.toString());
        getSession1().update("com.plgrim.ncp.commons.code.mergeGodWt",paramData);

    }

    public void deleteGodWt ( SysPrudDTO paramData ) throws Exception {
        log.info(paramData.toString());
        getSession1().update("com.plgrim.ncp.commons.code.deleteGodWt",paramData);

    }

	public void mergePrudCdLang  (SysPrdlstCdLang paramData) throws Exception {
	    log.info(paramData.toString());
	    getSession1().update("com.plgrim.ncp.commons.code.mergePrudCdLang", paramData);
	}

}
