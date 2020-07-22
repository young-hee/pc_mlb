package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.base.enums.CsoCnsltDetailEnum;
import com.plgrim.ncp.base.enums.CsoCnsltEnum;
import com.plgrim.ncp.biz.callcenter.data.*;
import com.plgrim.ncp.biz.callcenter.result.*;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.repository.cso.CsoCnsltRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;


@Repository
public class CounselRepository extends CsoCnsltRepository {

	@Autowired
	IdGenService idGenService;

	public Long insertCsoCnsltWithGenSn(CsoCnslt csoCnslt) throws Exception {
		Long maxSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_CNSLT", DatabaseType.ORACLE) + "");
		csoCnslt.setCnsltSn(maxSn);
		//insertCsoCnslt(csoCnslt);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCounsel", csoCnslt);

		return maxSn;
	}

	public int insertCounselDetailWithGenTurn(CsoCnsltDetail csoCnsltDetail) throws Exception{
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltDetail.getCnsltSn());

		int cnsltDetailTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_DETAIL", "CNSLT_DETAIL_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltDetail.setCnsltDetailTurn(cnsltDetailTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCounselDetail", csoCnsltDetail);
		return cnsltDetailTurn;
	}


	public long getListCounselTotal(CounselSearchDTO counselSearchDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.getListCounselTotal", counselSearchDTO);
	}
	
	public List<CounselResult> getListCounsel(CounselSearchDTO counselSearchDTO, PageParam pageParam) throws Exception{

		List<CounselResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.getListCounsel", counselSearchDTO);
		return list;
		
	}
	
	public long getCounselMaxSN() throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.getCounselMaxSN");
	}
	
	public int getMaxCsoCnsltDetail(long sn) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.getMaxCsoCnsltDetail", sn);
	}
	
	
	public int getCsoPromsclTurn(long sn) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.getCsoPromsclTurn", sn);
	}
	
	public int getCsoCnsltTransReqestTurn(long sn) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.getCsoCnsltTransReqestTurn", sn);
	}

	public CounselMemberInfoResult getDetailMemberInfo(long cnsltSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.getDetailMemberInfo", cnsltSn);
    }

	public CounselInfoResult selectCounselInfo(long cnsltSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.selectCounselInfo", cnsltSn);
	}

	public List<DetailCounselResult> getDetailCounselList(CsoCnsltDetail csoCnsltDetail) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.getDetailCounselList", csoCnsltDetail);
    }

	public String getCnsltRegDt(long cnsltSn){
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.getCnsltRegDt", cnsltSn);
	}

	public CounselResult getOrdInfo(OrdGod ordGod) {
		CounselResult results = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.getOrdInfo", ordGod);
		return results;
	}

	public List<CounselResult> selectCCAdmin() {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCCAdmin");
	}

	public List<FastUrlResult> selectCsoGlan(CsGlanSearchDTO csGlanSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCsoGlan", csGlanSearchDTO);
	}

	public void insertCsoGlan(FastUrlGridDTO fastUrlGridDTO) {
		fastUrlGridDTO.setRegtrId(BOSecurityUtil.getLoginId());
		fastUrlGridDTO.setUdterId(BOSecurityUtil.getLoginId());
		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoGlan", fastUrlGridDTO);
	}

	public void updateCsoGlan(FastUrlGridDTO fastUrlGridDTO) {
		fastUrlGridDTO.setRegtrId(BOSecurityUtil.getLoginId());
		fastUrlGridDTO.setUdterId(BOSecurityUtil.getLoginId());
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateCsoGlan", fastUrlGridDTO);
	}

	public void deleteFastUrl(FastUrlGridDTO fastUrlGridDTO) {
		getSession1().delete("com.plgrim.ncp.biz.callcenter.counsel.deleteFastUrl", fastUrlGridDTO);
	}

	public int selectCsoGlanTotal(CsGlanSearchDTO csGlanSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.selectCsoGlanTotal", csGlanSearchDTO);
	}

	public List<FastUrlResult> selectFastUrlPop() {
	    return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectFastUrlPop");
    }
	
	public PersonalInfoResult selectPersonalInfo(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.selectPersonalInfo", loginId);
	}

	public void updatePersonalInfo(PersonalInfoDTO personalInfoDTO) {
	    getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updatePersonalInfo", personalInfoDTO);
	    
    }

	/**
	 * @param csoCnsltOrdGod
	 * @throws Exception
	 *
	 * 상담 이력 주문 상품 등록
	 */
	public void insertCsoCnsltOrdGodWithGenTurn(CsoCnsltOrdGod csoCnsltOrdGod) throws Exception{

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltOrdGod.getCnsltSn());

		int maxTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_ORD_GOD", "CNSLT_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltOrdGod.setCnsltOrdGodTurn(maxTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoCnsltOrdGod", csoCnsltOrdGod);
	}

	public void insertCsoCnsltDetailJobTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoCnsltDetailJobTp", csoCnsltDetailJobTp);
	}

	public void insertCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoCnsltDetailHist", csoCnsltDetailHist);
	}

	public void insertCsoCnsltDetailOrdGodWithGenTurn(CsoCnsltDetailOrdGod csoCnsltDetailOrdGod) throws Exception {
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltDetailOrdGod.getCnsltSn());
		conditions.put("CNSLT_DETAIL_TURN", csoCnsltDetailOrdGod.getCnsltDetailTurn());

		int cnsltDetailOrdGodTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_DETAIL_ORD_GOD", "CNSLT_DETAIL_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltDetailOrdGod.setCnsltDetailOrdGodTurn(cnsltDetailOrdGodTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoCnsltDetailOrdGodWithGenTurn", csoCnsltDetailOrdGod);

	}

	public int insertCsoCnsltTransWithGenTurn(CsoCnsltTrans csoCnsltTrans) throws Exception{

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltTrans.getCnsltSn());
		conditions.put("CNSLT_DETAIL_TURN", csoCnsltTrans.getCnsltDetailTurn());

		int transRequstTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_TRANS", "TRANS_REQUST_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltTrans.setTransRequstTurn(transRequstTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoCnsltTrans", csoCnsltTrans);

		return transRequstTurn;
	}

	public void insertCsoCnsltTransOrdGodWithGenTurn(CsoCnsltTransOrdGod csoCnsltTransOrdGod) throws Exception{

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltTransOrdGod.getCnsltSn());
		conditions.put("CNSLT_DETAIL_TURN", csoCnsltTransOrdGod.getCnsltDetailTurn());
		conditions.put("TRANS_REQUST_TURN", csoCnsltTransOrdGod.getTransRequstTurn());

		int transOrdGodTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_TRANS_ORD_GOD", "TRANS_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltTransOrdGod.setTransOrdGodTurn(transOrdGodTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoCnsltTransOrdGod", csoCnsltTransOrdGod);

	}

	public void insertCsoCnsltTransHist(CsoCnsltTransHist csoCnsltTransHist) {
		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoCnsltTransHist", csoCnsltTransHist);
	}

	public long insertCsoJobRequstWithGenSn(CsoJobRequst csoJobRequst) throws Exception{

		long requstSn = idGenService.generateDBSequence(getSession1(), "SQ_CSO_JOB_REQUST", DatabaseType.ORACLE);
		csoJobRequst.setRequstSn(requstSn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoJobRequst", csoJobRequst);

		return requstSn;
	}

	public void insertCsoJobRequstOrdGodWithGenTurn(CsoJobRequstOrdGod csoJobRequstOrdGod) throws Exception{

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstOrdGod.getRequstSn());

		int requstOrdGodTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ORD_GOD", "REQUST_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstOrdGod.setRequstOrdGodTurn(requstOrdGodTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoJobRequstOrdGod", csoJobRequstOrdGod);

	}

	public void insertCsoPromsclWithGenSn(CsoPromscl csoPromscl) throws Exception{
		long promsclSn = idGenService.generateDBSequence(getSession1(), "SQ_CSO_PROMSCL", DatabaseType.ORACLE);
		csoPromscl.setPromsclSn(promsclSn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.counsel.insertCsoPromscl", csoPromscl);
	}

	public List<CsoCnsltTmplat> selectCounselTemplate(CsTemplateSearchDTO csTemplateSearchDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCounselTemplate", csTemplateSearchDTO);
	}

	public List<EmailSmsTemplateResult> selectCsoSmsEmailTmplat(EmailSmsTemplateSearchDTO emailSmsTemplateSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCsoSmsEmailTmplat", emailSmsTemplateSearchDTO);

	}

	public void updateCounselStatCompt(CsoCnslt csoCnslt) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateCounselStatCompt", csoCnslt);
	}

	public void updateCounselDetailStatCompt(CsoCnsltDetail csoCnsltDetail) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateCounselDetailStatCompt", csoCnsltDetail);
	}

	public void updateTransStatCompt(CsoCnsltTrans csoCnsltTrans) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateTransStatCompt", csoCnsltTrans);
	}

	public void updatePromsclStatCompt(CsoPromscl csoPromscl) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updatePromsclStatCompt", csoPromscl);
	}

	public List<CsoCnsltDetailOrdGod> selectCsoCnsltDetailOrdGod(long cnsltSn) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCsoCnsltDetailOrdGod", cnsltSn);
	}

	public CounselDetailTransferResult selectDetailCounselTransfer(CounselTransferSearchDTO counselTransferSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.selectDetailCounselTransfer", counselTransferSearchDTO);
	}

	public List<CounselTransferBoardDetailAnsResult> selectCounselTransferBoardDetailAns(CounselDetailTransferResult counselDetailTransfer) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCounselTransferBoardDetailAns", counselDetailTransfer);
	}

	public List<CounselTransferBoardAnsAtchmnflResult> selectCounselTransferBoardAnsAtchmnfl(CounselTransferBoardDetailAnsResult counselTransferBoardDetailAns) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCounselTransferBoardAnsAtchmnfl", counselTransferBoardDetailAns);
	}

	public CounselDetailPromsclResult selectCounselDetailPromsclList(CounselPromiseCallSearchDTO counselPromiseCallSearchDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.selectCounselDetailPromscl", counselPromiseCallSearchDTO);
	}

	public void deleteCounselGrid(CounselGridDTO counselGridDTO) {
		counselGridDTO.setDeleteYn(CsoCnsltDetailEnum.deleteYn.Y.toString());
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.deleteCounselGrid", counselGridDTO);
	}

	public List<CounselResult> selectTransRecrAdmin(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectTransRecrAdmin", counselSearchDTO);
	}

	public List<CounselDetailHistResult> selectCounselDetailHist(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCounselDetailHist", counselSearchDTO);
	}


	public CsoCnsltTmplat selectCounselTemplateContent(CsTemplateSearchDTO csTemplateSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.selectCounselTemplateContent", csTemplateSearchDTO);
	}

	public void updateDisplayYn(CsoCnsltDetail csoCnsltDetail) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateDisplayYn", csoCnsltDetail);
	}

	public void deleteTempMemo(CsoCnsltDetail csoCnsltDetail) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.deleteTempMemo", csoCnsltDetail);
	}

	public CsoCnsltDetail selectCounselDetail(CsoCnsltDetail csoCnsltDetail) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counsel.selectCounselDetail", csoCnsltDetail);
	}

	public List<CsoCnsltDetailJobTp> selectCounselDetailJopTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.counsel.selectCounselDetailJopTp", csoCnsltDetailJobTp);
	}

	public void updateCounselDetail(CsoCnsltDetail csoCnsltDetail) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateCounselDetail", csoCnsltDetail);
	}

	public void updateCounsel(CsoCnslt csoCnslt) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateCounsel", csoCnslt);
	}

	public void deleteCsoMtmInq(Long mtmInqSn) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.deleteCsoMtmInq", mtmInqSn);
	}

	public void updateCsoCnsltStat(CsoCnslt csoCnslt) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateCsoCnsltStat", csoCnslt);
	}

	public void updateCsoCnsltDetailStat(CsoCnsltDetail csoCnsltDetail) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.updateCsoCnsltDetailStat", csoCnsltDetail);
	}

	public void deleteCsoGodInq(Long godInqSn) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counsel.deleteCsoGodInq", godInqSn);
	}
}