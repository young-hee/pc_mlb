/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 27       
 */
package com.plgrim.ncp.commons;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.god.GodAditDetailExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;
import com.plgrim.ncp.base.entities.datasource3.clm.ClmBnkAcctNoEncrtKey;
import com.plgrim.ncp.commons.data.SysEmailDTO;
import com.plgrim.ncp.commons.data.SysLsmsDTO;
import com.plgrim.ncp.commons.data.ZipcodeDTO;
import com.plgrim.ncp.commons.result.*;
import com.plgrim.ncp.commons.service.*;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.data.mobile.LiteDeviceResolver;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CommonSelectComponentImpl extends AbstractComponent implements CommonSelectComponent {

	/* web.xml에서 선언된 mall 아이디 환경변수 명 */
	final static String MALL_ID_SYSTEM_VARIABLE_ID = "mall.id";

	/**
	 * mobile device 구분자.
	 */
	@Value("${ncp_base.device.mobile}")
	private String mobileDevice;
	/**
	 * mobile app 구분자.
	 */
	@Value("${ncp_base.device.mobile_app}")
	private String mobileApp;

	/**
	 * Code View Service DI
	 */
	@Autowired
	private CodeViewService codeViewService;

	/**
	 * Menu View Service DI
	 */
	@Autowired
	private MenuViewService menuViewService;

	@Autowired
	private ZipcodeService zipcodeService;

	@Autowired
	private SysInflowService sysInflowService;
	
	@Autowired
	private SysCommonService sysCommonService;

	@Autowired
	private SysLsmsService sysLsmsService;

	@Autowired
	private SysEmailService sysEmailService;

	@Autowired
	LiteDeviceResolver deviceResolver;

	@Autowired
	private MallService mallService;
	
	/**
	 * 다국어지원 공통코드 조회.
	 *
	 * @param systemPK
	 *            시스템PK @see SystemPK
	 * @param codeGroup
	 *            코드그룹(상위코드그룹)
	 * @return List<CodeViewResult> @see CodeViewResult
	 * @since 2015. 3. 27
	 */
	public List<CodeViewResult> getCodeList(SystemPK systemPK, String codeGroup) {
		List<CodeViewResult> list = null;
		list = codeViewService.getCodeList(codeGroup, systemPK.getLang().toString());
		return list;
	}

	/**
	 * 다국어지원 공통코드 조회.
	 *
	 * @param systemPK
	 *            시스템PK @see SystemPK
	 * @param codeGroup
	 *            코드그룹(상위코드그룹)
	 * @param code
	 *            [설명]
	 * @return List<CodeViewResult> @see CodeViewResult
	 * @since 2015. 3. 27
	 */
	public CodeViewResult getCode(SystemPK systemPK, String codeGroup, String code) {
		CodeViewResult codeViewResult = null;
		codeViewResult = codeViewService.getCode(codeGroup, code, systemPK.getLang().toString());
		return codeViewResult;
	}

	/**
	 * 메뉴 리스트 조회 메뉴(레벨로 정렬된 테이블형태).
	 *
	 * @param systemPK
	 *            시스템PK @see SystemPK
	 * @param boSiteId
	 *            boSiteId 사이트 ID
	 * @param upperMenuSn
	 *            상위메뉴 ID, 최상위메뉴부터 조회시 null
	 * @return List<MenuViewResult>
	 * @since 2015. 3. 27
	 */
	public List<MenuViewResult> getMenuList(SystemPK systemPK, String boSiteId, BigInteger upperMenuSn) {
		return menuViewService.getMenuList(boSiteId, upperMenuSn);
	}

	/**
	 * 메뉴 트리 조회(Tree Structure 조회).
	 *
	 * @param systemPK
	 *            시스템PK @see SystemPK
	 * @param boSiteId
	 *            boSiteId 사이트 ID
	 * @param upperMenuSn
	 *            상위메뉴 ID, 최상위메뉴부터 조회시 null
	 * @return List<MenuViewResult> Tree Structure List
	 * @since 2015. 3. 27
	 */
	public List<MenuViewResult> getMenuTree(SystemPK systemPK, String boSiteId, BigInteger upperMenuSn) {
		List<MenuViewResult> menuTableList = getMenuList(systemPK, boSiteId, upperMenuSn);
		MenuViewResult menuTree = new MenuViewResult(); // 더미 메뉴 Root
		MenuViewResult menuNode = null;
		if (menuTableList != null) {
			for (MenuViewResult menuViewResult : menuTableList) {
				menuNode = findNode(menuTree, menuViewResult.getUpperMenuSn());

				if (menuNode != null) {
					if (menuNode.getRows() == null) {
						menuNode.setRows(new ArrayList<MenuViewResult>());
					}
					menuNode.getRows().add(menuViewResult);
				} else {

				}

			}
		}

		return menuTree.getRows();
	}

	/**
	 * 전체 권한 메뉴 트리 조회(Tree Structure 조회).
	 * 
	 * @param systemPK
	 * @param boSiteId
	 * @return
	 */
	public List<MenuViewResult> getMenuTree(SystemPK systemPK, String boSiteId) {
		return getMenuTree(systemPK, boSiteId, null);
	}

	@Override
	public List<SysInflow> selectSysInflowAll(SysInflow inflow) {
		// TODO Auto-generated method stub
		return sysInflowService.selectSysInflowAll(inflow);
	}

	/*
	 * #########################################################################
	 * ######################## private function
	 * #########################################################################
	 * #########################
	 */
	/**
	 * 메뉴 트리 탐색
	 *
	 * @param treeRoot
	 *            최상위 트리 Root Node
	 * @param key
	 *            Key
	 * @return MenuViewResult
	 */
	private MenuViewResult findNode(MenuViewResult treeRoot, BigInteger key) {
		MenuViewResult returnNode = null;
		if (key == null || key.equals(treeRoot.getMenuSn())) {
			returnNode = treeRoot;
		} else {
			for (int i = 0; treeRoot.getRows() != null && i < treeRoot.getRows().size(); i++) {
				returnNode = findNode(treeRoot.getRows().get(i), key);
			}
		}
		return returnNode;
	}

	/**
	 * 환불계좌
	 */
	@Override
	public ClmBnkAcctNoEncrtKey getPrivateKeyForCryption(ClmBnkAcctNoEncrtKey key) throws Exception {
		//return sysCommonService.getPrivateKeyForCryption(key);
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void setPrivateKeyForCryption(ClmBnkAcctNoEncrtKey key) throws Exception {
		//sysCommonService.setPrivateKeyForCryption(key);

	}

	@Override
	public Page<ZipcodeResult> getSearchZipcode(ZipcodeDTO zipcodeDTO) throws Exception {

		return zipcodeService.getSearchZipcode(zipcodeDTO);
	}
	
	@Override
	public Page<ZipcodeResult> getSearchZipcode(ZipcodeDTO zipcodeDTO, PageParam pageParam) throws Exception {

		return zipcodeService.getSearchZipcode(zipcodeDTO, pageParam);
	}


	/**
	 * SMS/LMS 자주 사용하는 문구 목록 조회.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param paramData
	 *            [설명]
	 * @return List [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 5. 29
	 */
	public List<SysLsmsResult> selectSysLsmsTxtList(SystemPK systemPK, SysLsmsDTO paramData) throws Exception {
		return sysLsmsService.selectSysLsmsTxtList(paramData);
	}

	/**
	 * 이메일 자주 사용하는 문구 목록 조회.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param paramData
	 *            [설명]
	 * @return List [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 5. 29
	 */
	public List<SysEmailResult> selectSysEmailTxtList(SystemPK systemPK, SysEmailDTO paramData) throws Exception {
		return sysEmailService.selectSysEmailTxtList(paramData);
	}

	/**
	 * 이메일 자주 사용하는 문구 목록 조회.
	 *
	 * @param systemPK
	 *            [설명]
	 * @param paramData
	 *            [설명]
	 * @return List [설명]
	 * @throws Exception
	 *             the exception
	 * @since 2015. 5. 29
	 */
	public List<GodAditDetailExtend> getAddInfoCodeList(String prdlstGrpCd, String langCd, String godNo) {
		return codeViewService.getAddInfoCodeList(prdlstGrpCd, langCd, godNo);
	}

	/**
	 * 이벤트 응모가능여부
	 * 
	 */
    public String selectCampaginEvtInfo(String evtNo) throws Exception {
		return mallService.selectCampaginEvtInfo(evtNo);
	}
    
	/**
	 * 수신동의 유도 캠페인 쿠폰 발급 카운트
	 * 
	 */
    public int selectMyCampaginEvtCount(String mbrNo, String evtNo) throws Exception {
		return mallService.selectMyCampaginEvtCount(mbrNo, evtNo);
	}

	public List<SysMall> selectMallCodeList() {
		return mallService.getMallCodeList();
	}
}
