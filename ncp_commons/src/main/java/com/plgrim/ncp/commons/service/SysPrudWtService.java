package com.plgrim.ncp.commons.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.commons.data.FormSysPrudDTO;
import com.plgrim.ncp.commons.data.SysPrudWtDTO;
import com.plgrim.ncp.commons.repository.SysPrudWtRepository;
import com.plgrim.ncp.commons.result.SysPrudResult;
import com.plgrim.ncp.commons.result.SysPrudWtResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

@Service
public class SysPrudWtService extends AbstractService {

	
	@Autowired
	SysPrudWtRepository sysPrudWtRepository;
	
	/*################################################################################################
	 * Select
	##################################################################################################*/
	/**
	 * 품목코드 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysPrudWtResult> selectSysPrudWtList ( SysPrudWtDTO paramData) throws Exception {
		return sysPrudWtRepository.selectSysPrudWtCdList(paramData);
	}
	
	/**
	 * 품목코드 총 횟수 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountSysPrudWtCd( SysPrudWtDTO  paramData) throws Exception {
		return sysPrudWtRepository.selectCountSysPrudWtCd(paramData);
	}
	
	/**
	 * 품목코드 목록 엑셀
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectSysPrudWtCdListExcel(FormSysPrudDTO paramData) {
	    return sysPrudWtRepository.selectSysPrudWtCdListExcel(paramData);
    }
	
	/**
	 * 품목코드 등록 페이지 품목 select box
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysPrudResult> selectSysPrdlstCd() throws Exception {
		return sysPrudWtRepository.selectSysPrdlstCd();
	}
	
	/*################################################################################################
	 * Command
	##################################################################################################*/
	
	/**
	 * PrudWtCd insert 
	 * @param paramData
	 */
	public int insertPrudWtCd(SysPrudWtDTO paramData) throws Exception {
		int ret = 0;
		ret = sysPrudWtRepository.insertPrudWtCd(paramData);
		return ret;
	}
	
	public void updatePrudWtCd(List<SysPrudWtDTO> paramList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramList != null){
			for(int i=0; i < paramList.size(); i++){
				paramList.get(i).getSysPrdlstWt().setUdterId(loginId);
				sysPrudWtRepository.updatePrudWtCd(paramList.get(i).getSysPrdlstWt());
			}
		}
	}
}