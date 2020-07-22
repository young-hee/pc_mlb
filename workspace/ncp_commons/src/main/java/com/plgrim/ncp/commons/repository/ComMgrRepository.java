package com.plgrim.ncp.commons.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.commons.data.FormComPopupDTO;
import com.plgrim.ncp.commons.result.ComPopupResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 공통영역 조회 Repository
 * @author Tam
 *
 */
@Slf4j
@Repository
public class ComMgrRepository extends AbstractRepository{

	
	/**
	 * 매장목록 조회
	 * @param searchField
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	public List<ComPopupResult> selectListShopId( String searchField, String searchText) throws Exception {
		
		Map<String, String> paramData = new HashMap<String, String>();
		paramData.put("searchField", searchField);
		paramData.put("searchText", searchText);
		
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.com.selectListShopId", paramData);
	}
	
	/**
	 * 업체목록 조회
	 * @param searchField
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	public List<ComPopupResult> selectListComId( FormComPopupDTO paramData) throws Exception {
		
//		Map<String, String> paramData = new HashMap<String, String>();
//		paramData.put("searchField", searchField);
//		paramData.put("searchText", searchText);
//		if()
		
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.com.selectListComId", paramData);
	}
	
	/**
	 * 브랜드정보 조회
	 * @param searchField
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	public List<ComPopupResult> selectListBrndId(FormComPopupDTO formComPopupDTO) throws Exception {
		
		
		String[] searchTextArr = null;
		if(null != formComPopupDTO.getSearchText() && formComPopupDTO.getSearchText().indexOf(",") != -1){//여러개면
			searchTextArr = formComPopupDTO.getSearchText().split(",");
		}else{
			searchTextArr = new String[1];
			searchTextArr[0] = formComPopupDTO.getSearchText();
		}
		
		Map<String, Object> paramData = new HashMap<String, Object>();
		paramData.put("searchField", formComPopupDTO.getSearchField());
		paramData.put("searchText", formComPopupDTO.getSearchText());
		paramData.put("searchTextArr", searchTextArr);
		paramData.put("searchBrndId", formComPopupDTO.getSearchBrndId());
		paramData.put("searchBrndGrpYn", formComPopupDTO.getSearchBrndGrpYn());
		paramData.put("searchFirstBrndGrpYn", formComPopupDTO.getSearchFirstBrndGrpYn());
		paramData.put("searchComId", formComPopupDTO.getSearchComId());
		paramData.put("affComId", formComPopupDTO.getAffComId());
		
		List<String> brandList = BOSecurityUtil.getStringListFromAuthBrndList();
		log.debug("==aaaa=== : " + brandList);
		
		paramData.put("brandList", brandList);
		
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.com.selectListBrndId", paramData);
	}
	
	
	/**
	 * 상위브랜드코드로 브랜드정보 조회한다.
	 * @param upperBrndId
	 * @return  
	 * @throws Exception
	 */
	public List<ComPopupResult> selectListBrndFromUpperBrndId( FormComPopupDTO paramDataf) throws Exception {
		
		Map<String, String> paramData = new HashMap<String, String>();
		paramData.put("upperBrndId", paramDataf.getSelBrndId());
		paramData.put("brndGrpYn", paramDataf.getSearchBrndGrpYn());
		
		log.info(paramData.toString());
		String admComTpCd = BOSecurityUtil.getCurrentUserDetail().getSysAdmin().getAdminTpCd();		
		if ("AFF_AGNC".equals(admComTpCd)){
			String comId =BOSecurityUtil.getCurrentUserDetail().getCom().getComId();
			paramData.put("admComTpCd", admComTpCd);
			paramData.put("comId", comId);
			return getSession1().selectList("com.plgrim.ncp.commons.com.selectListAffBrndFromUpperBrndId", paramData);
		}else{
			return getSession1().selectList("com.plgrim.ncp.commons.com.selectListBrndFromUpperBrndId", paramData);
		}
				
	}
	
}