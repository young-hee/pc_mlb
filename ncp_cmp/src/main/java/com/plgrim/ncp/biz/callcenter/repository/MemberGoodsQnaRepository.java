package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.biz.callcenter.data.MemberGoodsQnaGridDTO;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.repository.cso.CsoCnsltRepository;
import com.plgrim.ncp.biz.callcenter.data.MemberGoodsQnaDTO;
import com.plgrim.ncp.biz.callcenter.data.MemberGoodsQnaSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.DetailMemberGoodsQnaResult;
import com.plgrim.ncp.biz.callcenter.result.MemberGoodsQnaResult;
import com.plgrim.ncp.biz.callcenter.result.SelectCnsltDetailResult;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;


@Repository
public class MemberGoodsQnaRepository extends AbstractRepository {

	@Autowired
	IdGenService idGenService;

	public List<MemberGoodsQnaResult> selectMemberGoodsQnaList(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO, PageParam pageParam) {
	    return getSession1().selectList("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectMemberGoodsQnaList", memberGoodsQnaSearchDTO);
    }

	public long selectMemberGoodsQnaListTotal(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectMemberGoodsQnaListTotal", memberGoodsQnaSearchDTO);
    }

	public DetailMemberGoodsQnaResult detailMemberGoodsQnaUserInfo(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.detailMemberGoodsQnaUserInfo", memberGoodsQnaSearchDTO); 
    }
	
	public DetailMemberGoodsQnaResult detailMemberGoodsQna(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.detailMemberGoodsQna", memberGoodsQnaSearchDTO);
	}
	
	public int insertMemberGoodsQnaAnsWithGenTurn(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("GOD_INQ_SN", memberGoodsQnaDTO.getGodInqSn());

		int godAnsTurn = idGenService.generateDBOrder(getSession1(), "CSO_GOD_INQ_ANS", "GOD_ANS_TURN", conditions, DatabaseType.ORACLE);
		
        memberGoodsQnaDTO.setGodAnsTurn(godAnsTurn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertMemberGoodsQnaAns", memberGoodsQnaDTO);

		return godAnsTurn;

	}

	public void updateMemberGoodsQna(CsoGodInq csoGodInq) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.callcenter.membergoodsqna.updateMemberGoodsQna", csoGodInq);
    }
	
	public void updateMemberGoodsQnaAnsEvlAdminAns(CsoGodInq csoGodInq) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.callcenter.membergoodsqna.updateMemberGoodsQnaAnsEvlAdminAns", csoGodInq);
	}
	
	public long insertCsoCnsltWithGenSn(CsoCnslt csoCnslt) throws Exception {
		
		long csoCnsltSn = idGenService.generateDBSequence(getSession1(), "SQ_CSO_CNSLT", DatabaseType.ORACLE);
		csoCnslt.setCnsltSn(csoCnsltSn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnslt", csoCnslt);
		
		return csoCnsltSn;
		
	}

	public MemberGoodsQnaResult selectMemberGoodsQna(long godInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectMemberGoodsQna", godInqSn);
	    
    }

	public int insertCsoCnsltDetailWithGenTurn(CsoCnsltDetail csoCnsltDetail) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltDetail.getCnsltSn());

		int cnsltDetailTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_DETAIL", "CNSLT_DETAIL_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltDetail.setCnsltDetailTurn(cnsltDetailTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnsltDetail", csoCnsltDetail);
		
		return cnsltDetailTurn; 
	    
    }

	public void insertCsoCnsltOrdGodWithGenTurn(CsoCnsltOrdGod csoCnsltOrdGod) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltOrdGod.getCnsltSn());
		
		int cnsltOrdGodTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_ORD_GOD", "CNSLT_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltOrdGod.setCnsltOrdGodTurn(cnsltOrdGodTurn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnsltOrdGod", csoCnsltOrdGod);
	    
    }

	public void insertCsoCnsltDetailJopTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnsltDetailJopTp", csoCnsltDetailJobTp);
    }

	public void insertCsoCnsltDetailOrdGodWithGenTurn(CsoCnsltDetailOrdGod csoCnsltDetailOrdGod) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltDetailOrdGod.getCnsltSn());
		conditions.put("CNSLT_DETAIL_TURN", csoCnsltDetailOrdGod.getCnsltDetailTurn());

		int cnsltDetailOrdGodTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_DETAIL_ORD_GOD", "CNSLT_DETAIL_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltDetailOrdGod.setCnsltDetailOrdGodTurn(cnsltDetailOrdGodTurn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnsltDetailOrdGod", csoCnsltDetailOrdGod);
	    
    }

	public void insertCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist) throws Exception{
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnsltDetailHist", csoCnsltDetailHist);
    }

	public int insertCsoCnsltTransWithGenTurn(CsoCnsltTrans csoCnsltTrans) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltTrans.getCnsltSn());
		conditions.put("CNSLT_DETAIL_TURN", csoCnsltTrans.getCnsltDetailTurn());
	    
		int transRequstTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_TRANS", "TRANS_REQUST_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltTrans.setTransRequstTurn(transRequstTurn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnsltTrans", csoCnsltTrans);
		
		return transRequstTurn;
    }

	public void insertCsoCnsltTransOrdGodWithGenTurn(CsoCnsltTransOrdGod csoCnsltTransOrdGod) throws Exception {

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("CNSLT_SN", csoCnsltTransOrdGod.getCnsltSn());
		conditions.put("CNSLT_DETAIL_TURN", csoCnsltTransOrdGod.getCnsltDetailTurn());
		conditions.put("TRANS_REQUST_TURN", csoCnsltTransOrdGod.getTransRequstTurn());

		int transOrdGodTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_TRANS_ORD_GOD", "TRANS_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoCnsltTransOrdGod.setTransOrdGodTurn(transOrdGodTurn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnsltTransOrdGod", csoCnsltTransOrdGod);
    }

	public void insertCsoCnsltTransHist(CsoCnsltTransHist csoCnsltTransHist) {
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoCnsltTransHist", csoCnsltTransHist);
	    
    }

	public long insertCsoJobRequstWithGenSn(CsoJobRequst csoJobRequst) throws Exception {
		
		long requstSn = idGenService.generateDBSequence(getSession1(), "SQ_CSO_JOB_REQUST", DatabaseType.ORACLE);
		csoJobRequst.setRequstSn(requstSn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoJobRequst", csoJobRequst);
		
		return requstSn;
	    
    }

	public void insertCsoJobRequstOrdGodWithGenTurn(CsoJobRequstOrdGod csoJobRequstOrdGod) throws Exception {
		
		int requstOrdGodTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ORD_GOD", "REQUST_ORD_GOD_TURN", null, DatabaseType.ORACLE);
		csoJobRequstOrdGod.setRequstOrdGodTurn(requstOrdGodTurn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoJobRequstOrdGod", csoJobRequstOrdGod);
	    
    }

	public void insertCsoPromsclWithGenSn(CsoPromscl csoPromscl) throws Exception {
		
		long promsclSn = idGenService.generateDBSequence(getSession1(), "SQ_CSO_PROMSCL", DatabaseType.ORACLE);
		csoPromscl.setPromsclSn(promsclSn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.membergoodsqna.insertCsoPromscl", csoPromscl);
		
	    
    }
	
	public List<SelectCnsltDetailResult> selectCnsltDetail(String godInqSn) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectCnsltDetail", godInqSn);
	}


	public long selectCnsltSn(long godInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectCnsltSn", godInqSn);
	}

	/*public void deleteGridQna(MemberGoodsQnaGridDTO memberGoodsQnaGridDTO) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.membergoodsqna.deleteGridQna", memberGoodsQnaGridDTO);
	}*/

	public CsoGodInqAns selectGodInqAnsInfo(CsoGodInqAns csoGodInqAns) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectGodInqAnsInfo", csoGodInqAns);
	}

	public void upddateCsoGodInqAns(CsoGodInqAns csoGodInqAns) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.membergoodsqna.upddateCsoGodInqAns", csoGodInqAns);
	}

	public String selectCounselCount(long godInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectCounselCount", godInqSn);
	}
	
	public void updateCsoCnsltStat(CsoCnslt csoCnslt) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.callcenter.membergoodsqna.updateCsoCnsltStat", csoCnslt);
    }

	public String selectAnsStat(long godInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectAnsStat", godInqSn);
	}

	public int selectMaxAnsTurn(long godInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectMaxAnsTurn", godInqSn);
	}

	public void updateComptAns(MemberGoodsQnaDTO memberGoodsQnaDTO) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.membergoodsqna.updateComptAns", memberGoodsQnaDTO);
	}

	public CsoCnsltDetail selectCounselDetailCont(MemberGoodsQnaDTO memberGoodsQnaDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.membergoodsqna.selectCounselDetailCont", memberGoodsQnaDTO);
	}

	public void updateCsoCnsltDetailCont(CsoCnsltDetail csoCnsltDetail) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.membergoodsqna.updateCsoCnsltDetailCont", csoCnsltDetail);
	}
}