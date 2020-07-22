package com.plgrim.ncp.commons.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCdLang;
import com.plgrim.ncp.commons.data.FormSysPrudDTO;
import com.plgrim.ncp.commons.data.SysPrudDTO;
import com.plgrim.ncp.commons.repository.SysPrudRepository;
import com.plgrim.ncp.commons.result.SysPrudResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

@Service
public class SysPrudService extends AbstractService {

	
	@Autowired
	SysPrudRepository sysPrudRepository;
	
	/*################################################################################################
	 * Select
	##################################################################################################*/
	/**
	 * 품목코드 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysPrudResult> selectSysPrudList ( FormSysPrudDTO paramData) throws Exception {
		return sysPrudRepository.selectSysPrudCdList(paramData);
	}
	
	/**
	 * 품목코드 총 횟수 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public long selectCountSysPrudCd( FormSysPrudDTO  paramData) throws Exception {
		return sysPrudRepository.selectCountSysPrudCd(paramData);
	}
	
	/**
	 * 품목코드 목록 엑셀
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectSysPrudCdListExcel(FormSysPrudDTO paramData) {
	    return sysPrudRepository.selectSysPrudCdListExcel(paramData);
    }
	
	/*################################################################################################
	 * Command
	##################################################################################################*/
	
	/**
	 * PrudCd 저장 처리
	 * @param paramList
	 */
	public void mergePrudCd ( List<SysPrudDTO> paramList ) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramList != null) {
			for(SysPrudDTO item : paramList) {
				item.getSysPrdlstCd().setRegtrId(loginId); // 등록자
				item.getSysPrdlstCd().setUdterId(loginId); // 수정자
				
				sysPrudRepository.mergePrudCd(item); // 품목코드 저장
				
				// 영문다국어 처리
				if( item.getPrdlstNmEng() != null && !"".equals(item.getPrdlstNmEng())){
					mergeMlangBrndNm("ENG", item);
				}
				
				// 중국어 다국어 처리
				if( item.getPrdlstNmChi() != null && !"".equals(item.getPrdlstNmChi())){
					mergeMlangBrndNm("CHI", item);
				}
                if (item.getSysPrdlstCd().getGodWt() == null || item.getSysPrdlstCd().getGodWt().intValue() == 0) {
                    sysPrudRepository.deleteGodWt(item);
                }  else  {
                    sysPrudRepository.mergeGodWt(item);
                }

			}
		}
	}
	
	/*################################################################################
	 * Private Function 
	 ###################################################################################*/
	/**
	 * 시스템 브랜드 다국어 등록/수정
	 * @param paramData
	 * @throws Exception
	 */
	private void mergeMlangBrndNm ( String langCd ,  SysPrudDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if( "ENG".equals(langCd) ){
			//영어 다국어 코드명 수정
		    SysPrdlstCdLang engPrdlstCd = new SysPrdlstCdLang();		    
		    engPrdlstCd.setPrdlstCd(paramData.getSysPrdlstCd().getPrdlstCd());
		    engPrdlstCd.setLangCd(langCd);		    
		    engPrdlstCd.setPrdlstNm(paramData.getPrdlstNmEng());
		    engPrdlstCd.setRegtrId(loginId); // 등록자
		    engPrdlstCd.setUdterId(loginId); // 수정자
		    
		    sysPrudRepository.mergePrudCdLang(engPrdlstCd);
		    
		}
		else if( "CHI".equals(langCd) ){
			//중국어 다국어 코드명 등록
            SysPrdlstCdLang chiPrdlstCd = new SysPrdlstCdLang();            
            chiPrdlstCd.setPrdlstCd(paramData.getSysPrdlstCd().getPrdlstCd());
            chiPrdlstCd.setLangCd(langCd);          
            chiPrdlstCd.setPrdlstNm(paramData.getPrdlstNmEng());
            chiPrdlstCd.setRegtrId(loginId); // 등록자
            chiPrdlstCd.setUdterId(loginId); // 수정자
            
            sysPrudRepository.mergePrudCdLang(chiPrdlstCd);
            
		}
	}

}
