package com.plgrim.ncp.commons.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcVal;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcValExtend;
import com.plgrim.ncp.commons.data.FormSysPolicyDTO;
import com.plgrim.ncp.commons.result.SysPolicyResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SysPolicyRepository extends AbstractRepository {
	
	/**
	 * 정책 리스트 조회
	 * @param FormSysPlcDTO
	 * @return
	 */
	public List<SysPolicyResult> selectListPlc( FormSysPolicyDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.policy.selectListPlc", paramData);
	}
	
	/**
	 * 정책 리스트 조회
	 * @param FormSysPlcDTO
	 * @return
	 */
	public SysPolicyResult selectPlcOne( FormSysPolicyDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.policy.selectListPlc", paramData);
	}
	
	/**
	 * 정책 값 리스트 조회
	 * @param FormSysPlcDTO
	 * @return
	 */
	public List<SysPolicyResult> selectListPlcVal( FormSysPolicyDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectList("com.plgrim.ncp.commons.policy.selectListPlcVal", paramData);
	}
	
	/**
	 * 정책 값 조회
	 * @param FormSysPlcDTO
	 * @return
	 */
	public SysPolicyResult selectPlcValOne( FormSysPolicyDTO paramData) throws Exception {
		log.info(paramData.toString());
		return getSession1().selectOne("com.plgrim.ncp.commons.policy.selectListPlcVal", paramData);
	}
	
	/**
	 * 정책 값 등록
	 * @param paramData
	 * @throws Exception
	 */
	public int insertSysPlcVal( SysPlcValExtend paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		return getSession1().insert("com.plgrim.ncp.commons.policy.insertSysPlcVal", paramData);
	}
	
	/**
	 * 정책 값 수정
	 * @param paramData
	 * @throws Exception
	 */
	public int updateSysPlcVal( SysPlcValExtend paramData) throws Exception {
		paramData.setRegtrId(BOSecurityUtil.getLoginId());
		log.info(paramData.toString());
		return getSession1().update("com.plgrim.ncp.commons.policy.updateSysPlcVal", paramData);
	}
	
	/**
	 * refresh MVIEW 
	 * @throws Exception
	 */
	public void refreshMviewPolicy() throws Exception {
		Map<String, String> paramData = new HashMap<String, String>();
		log.info( "refreshMview 실행");
		getSession1().update("com.plgrim.ncp.commons.policy.refreshMviewPolicy", paramData);
		
	}
	
	/**
	 * 정책 값 시작일시 체크
	 * @param paramData
	 * @return
	 */
	public boolean isValidPlcValBegDt ( FormSysPolicyDTO paramData){
		
		String checkYn = getSession1().selectOne("com.plgrim.ncp.commons.policy.checkPlcValBegDt", paramData);
		
		return ("Y".equals(checkYn)?true:false);
	}
	
	/**
	 * 정책 값 종료일시 체크
	 * @param paramData
	 * @return
	 */
	public boolean isValidPlcValEndDt ( FormSysPolicyDTO paramData){
		
		String checkYn = getSession1().selectOne("com.plgrim.ncp.commons.policy.checkPlcValEndDt", paramData);
		
		return ("Y".equals(checkYn)?true:false);
	}
	
	/**
	 * 중복여부 체크
	 * @param paramData
	 * @return
	 */
	public boolean isDuplicatePeriod ( FormSysPolicyDTO paramData){
		
		String duplYn = getSession1().selectOne("com.plgrim.ncp.commons.policy.checkDuplicatePeriod", paramData);
		
		return ("Y".equals(duplYn)?true:false);
	}
	
	public boolean isPastPlcVal ( FormSysPolicyDTO paramData){
		
		String pastYn = getSession1().selectOne("com.plgrim.ncp.commons.policy.checkPastPlcVal", paramData);
		
		return ("Y".equals(pastYn)?true:false);
	}
	
	/**
	 * 정책 값 조회 MV_SYS_PLC
	 */
	public List<String> getMvSysPlcVal(SysPlcValExtend sysPlcValExtend){
		return getSession1().selectList("com.plgrim.ncp.commons.policy.selectMvSysPlcVal", sysPlcValExtend);
	}
}
