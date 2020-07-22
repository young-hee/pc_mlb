package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequst;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAnsAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstOrdGod;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.callcenter.data.CounselTransferBoardSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferBoardAdminListResult;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferBoardDetailAnsAtchmnflResult;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferBoardDetailAnsResult;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferBoardDetailAtchmnflResult;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferBoardDetailOrdGodResult;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferBoardDetailResult;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferBoardListResult;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferBoardOrdGodResult;

@Repository
public class CounselTransferBoardRepository  extends AbstractRepository {

	@Autowired
	IdGenService idGenService;

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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
	public List<CounselTransferBoardAdminListResult> selectAdmin() throws Exception{
	
		List<CounselTransferBoardAdminListResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectAdmin");
		return list;
		
	}
	
	public List<CounselTransferBoardListResult> selectTransferBoardList(CounselTransferBoardSearchDTO counselTransferBoardSearchDTO) throws Exception{
		
		List<CounselTransferBoardListResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectTransferBoardList", counselTransferBoardSearchDTO);
		return list;
		
	}
	
	public int selectAssignRoleCount(CounselTransferBoardSearchDTO counselTransferBoardSearchDTO) throws Exception{
		int requstRoleCount = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectAssignRoleCount", counselTransferBoardSearchDTO);
		return requstRoleCount;
	}

	public CounselTransferBoardDetailResult selectTransferBoardDetail(CounselTransferBoardSearchDTO counselTransferBoardSearchDTO) throws Exception{
		CounselTransferBoardDetailResult data = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectTransferBoardDetail", counselTransferBoardSearchDTO); 
		return data;
	}
	
	public List<CounselTransferBoardDetailOrdGodResult> selectTransferBoardDetailOrdGod(CounselTransferBoardSearchDTO counselTransferBoardSearchDTO) throws Exception{
		List<CounselTransferBoardDetailOrdGodResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectTransferBoardDetailOrdGod", counselTransferBoardSearchDTO);
		return list;
	}
	
	public List<CounselTransferBoardDetailAtchmnflResult> selectTransferBoardDetailAtchmnfl(CounselTransferBoardSearchDTO counselTransferBoardSearchDTO) throws Exception{
		List<CounselTransferBoardDetailAtchmnflResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectTransferBoardDetailAtchmnfl", counselTransferBoardSearchDTO);
		return list;
	}
	
	public CounselTransferBoardDetailAnsResult selectTransferBoardDetailAns(CounselTransferBoardSearchDTO counselTransferBoardSearchDTO) throws Exception{
		CounselTransferBoardDetailAnsResult list = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectTransferBoardDetailAns", counselTransferBoardSearchDTO);
		return list;
	}
	
	public List<CounselTransferBoardDetailAnsAtchmnflResult> selectTransferBoardDetailAnsAtch(CounselTransferBoardSearchDTO counselTransferBoardSearchDTO) throws Exception{
		List<CounselTransferBoardDetailAnsAtchmnflResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectTransferBoardDetailAnsAtch", counselTransferBoardSearchDTO);
		return list;
	}
	
	public int selectRequstCount(Long requstSn) throws Exception{
		int requstSnCount = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectRequstCount", requstSn);
		return requstSnCount;
	}

	public int selectTransferBoardListTotal(CounselTransferBoardSearchDTO counselTransferBoardSearchDTO) {
		int totalCount = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectTransferBoardListTotal", counselTransferBoardSearchDTO);
		return totalCount;
	}

	public void insertCounselTransferBoardWithGenSn(CsoJobRequst csoJobRequst) throws Exception{

		// SN 증가
		Long getMaxSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_JOB_REQUST", DatabaseType.ORACLE) + "");
		csoJobRequst.setRequstSn(getMaxSn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counseltransferboard.insertCounselTransferBoardWithGenSn", csoJobRequst);

	}

	public void insertCsoJobRequstOrdGodWithGenTurn(CsoJobRequstOrdGod csoJobRequstOrdGod) throws Exception{
		// TURN 증가
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstOrdGod.getRequstSn());

		int getMaxTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ORD_GOD", "REQUST_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstOrdGod.setRequstOrdGodTurn(getMaxTurn);

		csoJobRequstOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
		csoJobRequstOrdGod.setUdterId(BOSecurityUtil.getLoginId());

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counseltransferboard.insertCsoJobRequstOrdGodWithGenTurn", csoJobRequstOrdGod);

	}

	public void insertCounselTransferOrdGodWithGenTurn(CsoJobRequstOrdGod csoJobRequstOrdGod) throws Exception {
		// TURN 증가
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstOrdGod.getRequstSn());

		int getMaxTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ORD_GOD", "REQUST_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstOrdGod.setRequstOrdGodTurn(getMaxTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counseltransferboard.insertCounselTransferOrdGodWithGenTurn", csoJobRequstOrdGod);
	}

	public void insertCsoJobRequstAnsWithGenTurn(CsoJobRequstAns csoJobRequstAns) throws Exception {
		// TURN 증가
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstAns.getRequstSn());

		int getMaxTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ANS", "JOB_REQUST_ANS_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstAns.setJobRequstAnsTurn(getMaxTurn);

		//insertCsoJobRequstAns(csoJobRequstAns);
		getSession1().insert("com.plgrim.ncp.biz.callcenter.counseltransferboard.insertCsoJobRequstAnsWithGenTurn", csoJobRequstAns);


	}

	public void updateCounselTransferBoard(CsoJobRequst csoJobRequst) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counseltransferboard.updateCounselTransferBoard", csoJobRequst);
	}

	public void updateCounseTransferBoardAns(CsoJobRequstAns csoJobRequstAns) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counseltransferboard.updateCounseTransferBoardAns", csoJobRequstAns);

	}

	public void insertCounselTransferBoardAnsAtchmnflWithGenTurn(CsoJobRequstAnsAtchmnfl csoJobRequstAnsAtchmnfl) throws Exception{
		// TURN 증가
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstAnsAtchmnfl.getRequstSn());
		conditions.put("JOB_REQUST_ANS_TURN", csoJobRequstAnsAtchmnfl.getJobRequstAnsTurn());

		int getMaxTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ANS_ATCHMNFL", "REQUST_ANS_ATCHMNFL_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstAnsAtchmnfl.setRequstAnsAtchmnflTurn(getMaxTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counseltransferboard.insertCounselTransferBoardAnsAtchmnflWithGenTurn", csoJobRequstAnsAtchmnfl);

	}

	public List<CsoJobRequstOrdGod> selectCounselTransferBoardOrdNoList(Long requstSn) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectCounselTransferBoardOrdNoList", requstSn);
	}

	/**
	 * PO Main 업무게시판 / 입점업체
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<CounselTransferBoardListResult> selectPoMainIpTodoTransferBoardList( CounselTransferBoardSearchDTO paramData ) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransferboard.selectPoMainIpTodoTransferBoardList", paramData);
	}

}
