package com.plgrim.ncp.commons.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstWt;
import com.plgrim.ncp.commons.data.FormSysPrudDTO;
import com.plgrim.ncp.commons.data.SysPrudWtDTO;
import com.plgrim.ncp.commons.result.SysPrudResult;
import com.plgrim.ncp.commons.result.SysPrudWtResult;

import lombok.extern.slf4j.Slf4j;


/**
 * Sys Prud Wt Repository.
 *
 * @author Myun
 */

@Slf4j
@Repository
public class SysPrudWtRepository extends AbstractRepository {
	
	/*################################################################################################
	 * Select
	##################################################################################################*/
	
	/**
	 * Sys PrudCd list 조회.
	 * @param FormSysCodeDTO
	 * @return
	 */
	public List<SysPrudWtResult> selectSysPrudWtCdList( SysPrudWtDTO  paramData) throws Exception {
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.code.getSysPrudWtCdList", paramData);
	}
	
	/**
	 * 품목코드 총 갯수 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountSysPrudWtCd( SysPrudWtDTO  paramData) throws Exception {
		log.info(paramData.toString()); 
		return getSession1().selectOne("com.plgrim.ncp.commons.code.selectCountSysPrudWtCd", paramData);
	}
	
	/**
	 * 품목코드 목록 엑셀
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectSysPrudWtCdListExcel(FormSysPrudDTO paramData) {
	    return getSession1().selectList("com.plgrim.ncp.commons.code.getSysPrudWtCdListExcel", paramData);
    }
	
	/**
	 * 품목코드 등록 페이지 품목 select box
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysPrudResult> selectSysPrdlstCd() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.code.selectSysPrdlstCd");
	}
	
	/*################################################################################################
	 * Command
	##################################################################################################*/
	
	/**
	 * Sys Prud Wt Cd 저장 처리
	 * @param paramData
	 */
	public int insertPrudWtCd ( SysPrudWtDTO paramData ) throws Exception {
        log.info(paramData.toString());
        int ret = 0;
        ret = getSession1().update("com.plgrim.ncp.commons.code.mergePrudWtCd",paramData);
        return ret;
    }
    
    public void updatePrudWtCd ( SysPrdlstWt sysPrdlstWt ) throws Exception {
        log.info(sysPrdlstWt.toString());
        getSession1().update("com.plgrim.ncp.commons.code.updatePrudWtCd",sysPrdlstWt);
    }	

}