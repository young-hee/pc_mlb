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

import com.plgrim.ncp.base.entities.datasource1.god.GodAditDetailExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;
import com.plgrim.ncp.base.entities.datasource3.clm.ClmBnkAcctNoEncrtKey;
import com.plgrim.ncp.commons.data.SysEmailDTO;
import com.plgrim.ncp.commons.data.SysLsmsDTO;
import com.plgrim.ncp.commons.data.ZipcodeDTO;
import com.plgrim.ncp.commons.result.*;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.List;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 3. 27
 */
public interface CommonSelectComponent {
	
	/**
	 * 다국어지원 공통코드 조회.
	 *
	 * @param systemPK 시스템PK @see SystemPK
	 * @param codeGroup 코드그룹(상위코드그룹)
	 * @return List<CodeViewResult> @see CodeViewResult
	 * @since 2015. 3. 27
	 */
	public List<CodeViewResult> getCodeList(SystemPK systemPK, String codeGroup);
	
	/**
	 * 다국어지원 공통코드 조회.
	 *
	 * @param systemPK 시스템PK @see SystemPK
	 * @param codeGroup 코드그룹(상위코드그룹)
	 * @param code [설명]
	 * @return List<CodeViewResult> @see CodeViewResult
	 * @since 2015. 3. 27
	 */
	public CodeViewResult getCode(SystemPK systemPK, String codeGroup, String code);

	/**
	 * 메뉴 리스트 조회 메뉴(레벨로 정렬된 테이블형태).
	 *
	 * @param systemPK 시스템PK @see SystemPK
	 * @param boSiteId boSiteId 사이트 ID
	 * @param upperMenuSn 상위메뉴 ID, 최상위메뉴부터 조회시 null
	 * @return List<MenuViewResult>
	 * @since 2015. 3. 27
	 */
	public List<MenuViewResult> getMenuList(SystemPK systemPK, String boSiteId, BigInteger upperMenuSn);
	
	/**
	 * 메뉴 트리 조회(Tree Structure 조회).
	 *
	 * @param systemPK 시스템PK @see SystemPK
	 * @param boSiteId boSiteId 사이트 ID
	 * @param upperMenuSn 상위메뉴 ID, 최상위메뉴부터 조회시 null
	 * @return List<MenuViewResult> Tree Structure List
	 * @since 2015. 3. 27
	 */
	public List<MenuViewResult>getMenuTree(SystemPK systemPK, String boSiteId, BigInteger upperMenuSn);

	 /**
     * 전체 권한 메뉴 트리 조회(Tree Structure 조회).
     * @param systemPK
     * @param boSiteId
     * @return
     */
    public List<MenuViewResult> getMenuTree(SystemPK systemPK, String boSiteId) ;
    
	/**
	 * 
	 * @author ken
	 * @return
	 */
	public List<SysInflow> selectSysInflowAll(SysInflow inflow);

	/**
	 * 환불계좌 암복화를 위한 개인키 읽기. 
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	public ClmBnkAcctNoEncrtKey getPrivateKeyForCryption(ClmBnkAcctNoEncrtKey key) throws Exception;
	
	/**
	 * 환불계좌 암복화를 위한 개인키 저장. 
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	public void setPrivateKeyForCryption(ClmBnkAcctNoEncrtKey key) throws Exception;

	/**
	 * 우편번호 조회.
	 * @param zipcodeDTO
	 * @return
	 * @throws Exception 
	 */
	public Page<ZipcodeResult> getSearchZipcode(ZipcodeDTO zipcodeDTO) throws Exception;
	
	/**
	 * 우편번호 조회.
	 * @param zipcodeDTO
	 * @return
	 * @throws Exception 
	 */
	public Page<ZipcodeResult> getSearchZipcode(ZipcodeDTO zipcodeDTO, PageParam pageParam) throws Exception;

	/**
	 * SMS/LMS 자주 사용하는 문구 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysLsmsResult> selectSysLsmsTxtList( SystemPK systemPK, SysLsmsDTO paramData) throws Exception ;
	
	/**
	 * 이메일 자주 사용하는 문구 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysEmailResult> selectSysEmailTxtList( SystemPK systemPK, SysEmailDTO paramData) throws Exception ;

	/**
	 * SR[22287] 상품상세 페이지 내 상품 추가정보 조회.
	 *
	 * @param codeSearch [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2016. 7. 15
	 */
	public List<GodAditDetailExtend> getAddInfoCodeList(String prdlstGrpCd, String langCd, String godNo);

	/**
	 * 이벤트 응모가능여부
	 * 
	 */
	public String selectCampaginEvtInfo(String evtNo) throws Exception;
	
	/**
	 * 수신동의 유도 캠페인 쿠폰 발급 카운트
	 * 
	 */
	public int selectMyCampaginEvtCount(String mbrNo, String evtNo) throws Exception;

	/**
	 * 몰 코드 조회.
	 *
	 * @return
	 */
	public List<SysMall> selectMallCodeList();

}
