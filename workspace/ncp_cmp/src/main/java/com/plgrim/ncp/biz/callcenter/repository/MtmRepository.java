package com.plgrim.ncp.biz.callcenter.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.biz.callcenter.data.MtmAddDTO;
import com.plgrim.ncp.biz.callcenter.data.MtmListSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.*;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.google.common.collect.Maps;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class MtmRepository extends AbstractRepository{

	@Autowired
	IdGenService idGenService;

	public  long selectCountMtm(MtmListSearchDTO searchDTO) throws Exception{

		//단일검색 셋팅
		Long results;
		if((!"".equals(searchDTO.getOrdNo()) && null!=searchDTO.getOrdNo())
				||  (!"".equals(searchDTO.getMbrId()) && null!=searchDTO.getMbrId())
				||  (!"".equals(searchDTO.getMtmInqSn()) && null!=searchDTO.getMtmInqSn())){

			MtmListSearchDTO searchDTO2 = new MtmListSearchDTO();
			searchDTO2.setOrdNo(searchDTO.getOrdNo());
			searchDTO2.setMbrId(searchDTO.getMbrId());
			searchDTO2.setMtmInqSn(searchDTO.getMtmInqSn());
			results = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.getListMtmTotal",searchDTO2);

		}else{
			results = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.getListMtmTotal",searchDTO);
		}

		return results;

	}
	
	public List<MtmListResult> getListMtm(MtmListSearchDTO searchDTO, PageParam pageParam) {
		
		
		//////검색조건 셋팅////////////////
		//검색날짜 셋팅
		if(null != searchDTO.getSearchDtStart() && !"".equals(searchDTO.getSearchDtStart())){
			searchDTO.setSearchDtStart(searchDTO.getSearchDtStart().replace("-", "")+"000000");
		}
		if(null != searchDTO.getSearchDtEnd() && !"".equals(searchDTO.getSearchDtEnd())){
			searchDTO.setSearchDtEnd(searchDTO.getSearchDtEnd().replace("-", "")+"235959");
		}

		//단일검색 셋팅
		List<MtmListResult> results = new ArrayList<MtmListResult>();
		if((!"".equals(searchDTO.getOrdNo()) && null!=searchDTO.getOrdNo())
				||  (!"".equals(searchDTO.getMbrId()) && null!=searchDTO.getMbrId())
				||  (!"".equals(searchDTO.getMtmInqSn()) && null!=searchDTO.getMtmInqSn())){

			MtmListSearchDTO searchDTO2 = new MtmListSearchDTO();
			searchDTO2.setOrdNo(searchDTO.getOrdNo());
			searchDTO2.setMbrId(searchDTO.getMbrId());
			searchDTO2.setMtmInqSn(searchDTO.getMtmInqSn());
			searchDTO2.setMallId(searchDTO.getMallId());
			RepositoryHelper.makePageEntityIndex(searchDTO2, pageParam);
			results = getSession1().selectList("com.plgrim.ncp.biz.callcenter.mtm.getListMtm", searchDTO2);

		}else{

			RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
			results = getSession1().selectList("com.plgrim.ncp.biz.callcenter.mtm.getListMtm", searchDTO);

		}

		return results;
    }

	
	public MtmDetailResult getMtmDetail(String mtmInqrySn) {
		MtmDetailResult result = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.getMtmDetail", mtmInqrySn);
	    return result;
    }
	

	public List<String> getInqOrdGodsNo(String ordNo) {
		List<String> result = getSession1().selectList("com.plgrim.ncp.biz.callcenter.mtm.getInqOrdGodsNo", ordNo);
	    return result;
    }

	public MtmUserInfoResult selectMtmUserInfo(long mtmInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectMtmUserInfo", mtmInqSn);
	}

	public MtmInqInfoResult selectMtmInqInfo(long mtmInqSn) {
			
	int ans = 	getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectMtmInqInfoFlag", mtmInqSn);
		
		if(ans > 0){ //답변이 달려있는경우
			return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectAnsedMtmInqInfo", mtmInqSn);
		}else{ // 답변이 안달려있는경우
			return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectMtmInqInfo", mtmInqSn);
		}
		
		
	}

	public int insertCsoMtmInqAnsWithGenTurn(CsoMtmInqAns csoMtmInqAns) throws Exception{

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("MTM_INQ_SN", csoMtmInqAns.getMtmInqSn());

		int mtmInqTurn = idGenService.generateDBOrder(getSession1(), "CSO_MTM_INQ_ANS", "MTM_INQ_ANS_TURN", conditions, DatabaseType.ORACLE);

		csoMtmInqAns.setMtmInqAnsTurn(mtmInqTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.mtm.insertCsoMtmInqAns", csoMtmInqAns);

		return mtmInqTurn;
	}

	public void updateCsoMtmInqAnsEvlAdminAns(CsoMtmInq csoMtmInq) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.mtm.updateCsoMtmInqAnsEvlAdminAns", csoMtmInq);
	}
	public void updateAnsDscnttTp(CsoMtmInq csoMtmInq) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.mtm.updateAnsDscnttTp", csoMtmInq);
	}
	public void updateCsoMtmInq(CsoMtmInq csoMtmInq) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.mtm.updateCsoMtmInq", csoMtmInq);
	}

	public MtmResult selectCsoMtmInq(long mtmInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectCsoMtmInq", mtmInqSn);
	}

	public List<MtmOrdGodResult> selectCsoMtmInqOrdGod(long mtmInqSn) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.mtm.selectCsoMtmInqOrdGod", mtmInqSn);
	}

	public long selectCnsltSn(long mtmInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectCnsltSn", mtmInqSn);
	}

	public List<InquirySelectGodResult> selectGodList(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.mtm.selectGodList", ordNo);
	}
	
	public List<InquirySelectGodResult> selectGodsList(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.mtm.selectGodsList", mypageMtmFoDTO);
	}
	
	public long selectGodsListCount(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectGodsListCount", mypageMtmFoDTO);
	}

	public String mtmInqStatCheck(Long mtmInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.mtmInqStatCheck", mtmInqSn);
	}

	public List<CsoMtmInqAtchFile> selectMtmInqAtchInfo(long mtmInqSn) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.mtm.selectMtmInqAtchInfo", mtmInqSn);
	}
	
	public String selectInqAns(Map map) {
						
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectInqAns", map);
	}

	public CsoCnslt selectCounselInfo(long mtmInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectCounselInfo", mtmInqSn);
	}

	public CsoCnsltDetail selectCounselDetailInfo(CsoCnsltDetail csoCnsltDetail) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectCounselDetailInfo", csoCnsltDetail);
	}

	public CsoMtmInqAns selectmtmInqAnsInfo(CsoMtmInqAns csoMtmInqAns) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectmtmInqAnsInfo", csoMtmInqAns);
	}


	public void upddateCsoMtmInqAns(CsoMtmInqAns csoMtmInqAns) {

		getSession1().update("com.plgrim.ncp.biz.callcenter.mtm.upddateCsoMtmInqAns", csoMtmInqAns);

	}

	public String selectCounselCount(long mtmInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectCounselCount", mtmInqSn);
	}

	public List<CsoCnsltTmplat> selectInquiryTemplate(CsoCnsltTmplat csoCnsltTmplat) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.mtm.selectInquiryTemplate", csoCnsltTmplat);
	}

	public String selectAnsStat(long mtmInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.selectAnsStat", mtmInqSn);
	}




	
/*	public Page<ReservationResult> getListReservation(SystemPK systemPk, ReservationDTO reservationDTO, PageParam pageParam) throws Exception{
		
		//페이지 인덱스 셋팅
		RepositoryHelper.makePageEntityIndex(reservationDTO, pageParam);
		
		List<ReservationResult> results = getSession1().selectList("com.plgrim.ncp.biz.callcenter.reservation.getListReservation", reservationDTO);
		
		//전체 Row 수 
		long totalRow = selectCountReservationAll();
				
		return new PageImpl<ReservationResult>(results, pageParam.getPageable(), totalRow);
	}*/

	
}