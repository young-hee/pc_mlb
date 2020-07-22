package com.plgrim.ncp.biz.helpdesk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.member.data.BundleOrderFoDTO;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskFaqFoDTO;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskNoticeFoDTO;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskRepairDTO;
import com.plgrim.ncp.biz.helpdesk.repository.HelpdeskRepository;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskFaqFoResult;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskNoticeFoResult;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskRepairResult;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.util.AdapterHeader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HelpdeskService extends AbstractService{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	HelpdeskRepository helpdeskRepository;

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

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
	/**	faq 리스트 */
	public Page<HelpdeskFaqFoResult> selectFaqList(HelpdeskFaqFoDTO helpdeskFaqFoDTO , PageParam pageParam) throws Exception{
		return helpdeskRepository.selectFaqList(helpdeskFaqFoDTO ,pageParam);
    }
	/**	faq 리스트 카운트 */
	public long selectCountFaq(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
		return helpdeskRepository.selectCountFaq(helpdeskFaqFoDTO);
	}

	/** 공지 리스트	 */
	public Page<HelpdeskNoticeFoResult> selectNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO, PageParam pageParam) throws Exception{
	    return helpdeskRepository.selectNoticeList(helpdeskNoticeFoDTO, pageParam);
    }
	/** 공지 리스트	for MobilePhone*/
	public List<HelpdeskNoticeFoResult> selectMpNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
		return helpdeskRepository.selectMpNoticeList(helpdeskNoticeFoDTO);
	}
	/** 공지 리스트 for Popup */
	public List<HelpdeskNoticeFoResult> selectPopupNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return helpdeskRepository.selectPopupNoticeList(helpdeskNoticeFoDTO);
    }
	/** 공지사항 타이틀 리스트	 */
	public List<CodeViewResult> selectNotiTitleList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
		return helpdeskRepository.selectNotiTitleList(helpdeskNoticeFoDTO);
	}
	/** 공지사항 상세	 */
	public List<HelpdeskNoticeFoResult> selectNoticeDetail(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{



		helpdeskRepository.updateNoticeInqireCount(helpdeskNoticeFoDTO);

		return helpdeskRepository.selectNoticeDetail(helpdeskNoticeFoDTO);
    }
	/** 공지사항 이벤트상세	 */
	public List<HelpdeskNoticeFoResult> selectNoticeEvtDetail(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return helpdeskRepository.selectNoticeEvtDetail(helpdeskNoticeFoDTO);
    }
	/** 대표 공지 리스트	 */
	public List<HelpdeskNoticeFoResult> selectNoticeRprstList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return helpdeskRepository.selectNoticeRprstList(helpdeskNoticeFoDTO);
    }
	/** 고객센터 메인 FAQ Top10 리스트	 */
	public List<HelpdeskFaqFoResult> selectFaqTop10List(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
	    return helpdeskRepository.selectFaqTop10List(helpdeskFaqFoDTO);
    }

	/** 고객센터 메인 FAQ Top5 리스트	 */
	public List<HelpdeskFaqFoResult> selectFaqTop5List(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
	    return helpdeskRepository.selectFaqTop5List(helpdeskFaqFoDTO);
    }
	/** 고객센터 메인 공지 New5 리스트	*/
	public List<HelpdeskNoticeFoResult> selectNoticeNew5List(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return helpdeskRepository.selectNoticeNew5List(helpdeskNoticeFoDTO);
    }
	/** 고객센터 메인 공지 New3 리스트	*/
	public List<HelpdeskNoticeFoResult> selectNoticeNew3List(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
		return helpdeskRepository.selectNoticeNew3List(helpdeskNoticeFoDTO);
	}
	/** 이전글 보기*/
	public List<HelpdeskNoticeFoResult> selectPreNotiSn(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception {
	    return helpdeskRepository.selectPreNotiSn(helpdeskNoticeFoDTO);
    }
	/** 다음글 보기*/
	public List<HelpdeskNoticeFoResult> selectNextNotiSn(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return helpdeskRepository.selectNextNotiSn(helpdeskNoticeFoDTO);
    }

	/** 단체주문/제휴문의 등록	 */
	public void insertBundleOrder(BundleOrderFoDTO bundleOrderFoDTO) throws Exception{

		Long bundleSeq = getIdGenService().generateDBSequence(sqlSession1,"SQ_CSO_ORGZT_ORD_AFF_INQ" ,DatabaseType.ORACLE);
		bundleOrderFoDTO.getCsoOrgztOrdAffInq().setOrgztOrdAffInqSn(bundleSeq);


		 helpdeskRepository.insertBundleOrder(bundleOrderFoDTO);

		 /** 단체주문/제휴문의  첨부파일 등록	 */
		 Map<String, Object> conditions = Maps.newHashMap();
		 conditions.put("ORGZT_ORD_AFF_INQ_SN",bundleSeq);// 의심이 좀 간다
		 if(bundleOrderFoDTO.getCsoOrgztOrdAffInqAtchmnfl().getOrgztOrdAffInqAtchFileNm() != null && bundleOrderFoDTO.getCsoOrgztOrdAffInqAtchmnfl().getOrgztOrdAffInqAtchFileNm() != ""){
		 bundleOrderFoDTO.getCsoOrgztOrdAffInqAtchmnfl().setOrgztOrdAffInqAtchmnflTur(getIdGenService().generateDBOrder(sqlSession1,"CSO_ORGZT_ORD_AFF_INQ_ATCHMNFL","ORGZT_ORD_AFF_INQ_ATCHMNFL_TUR", conditions, DatabaseType.ORACLE));
		 helpdeskRepository.insertOrgztOrdAffInqAtchmnfl(bundleOrderFoDTO);
		 }


		 if(bundleOrderFoDTO.getMbr() ==null){//비회원 약관 동의
				Map<String,Object> map= new HashMap<String,Object>();
				Long nmbrStplatAgrSn =getIdGenService().generateDBSequence(sqlSession1, "SQ_CSO_NMBR_STPLAT_AGR",DatabaseType.ORACLE);//약관동의 일련번호
				map.put("nmbrStplatAgrSn", nmbrStplatAgrSn);
				map.put("orgztOrdAffInqSn", bundleSeq);//
				map.put("stplatCd", "NMBR_PSNL_INFO_COLCT_USEF_AGR");
				map.put("stplatIemAgrYn", "Y");
				if(bundleOrderFoDTO.getCsoOrgztOrdAffInq().getOrgztOrdAffInqTpCd().equals("ORGZT_ORD")){
					map.put("udterId", "nonBundleMember");
					map.put("regtrId", "nonBundleMember");
				}else{
					map.put("udterId", "nonAffInqMember");
					map.put("regtrId", "nonAffInqMember");
				}
				helpdeskRepository.insertNmbrStplatAgr(map);
			}


    }
	public List<HelpdeskRepairResult> selectRepairMbrRceptNo(HelpdeskRepairDTO helpdeskRepairDTO) throws Exception{
	    return helpdeskRepository.selectRepairMbrRceptNo(helpdeskRepairDTO);
    }
//	public List<HelpdeskRepairResult> selectRepairList(RepairSDO parameter, AdapterHeader adapterHeader) throws Exception{
//
//		List<HelpdeskRepairResult> resultList = new ArrayList<HelpdeskRepairResult>();
//
//		String repairList = this.memberAdapterSoap.getRepairList(parameter, adapterHeader);
//
//		JSONArray jsonArray = new JSONArray(repairList);
//
//		for(int i=0 ; i<jsonArray.length() ; i++) {
//			HelpdeskRepairResult result = new HelpdeskRepairResult();
//
//			//화면필수
//			result.setQmnum(jsonArray.getJSONObject(i).getString("qmnum"));
//			result.setQmdat(jsonArray.getJSONObject(i).getString("qmdat"));
//			result.setName2(jsonArray.getJSONObject(i).getString("name2"));
//			result.setBautl(jsonArray.getJSONObject(i).getString("bautl"));
//			result.setAstxtNm(jsonArray.getJSONObject(i).getString("astxtNm"));
//			result.setKurztext3(jsonArray.getJSONObject(i).getString("kurztext3"));
//
//			//상세
//			result.setFenum(jsonArray.getJSONObject(i).getString("fenum"));
//			result.setKunum(jsonArray.getJSONObject(i).getString("kunum"));
//			result.setTelf1(jsonArray.getJSONObject(i).getString("telf1"));
//			result.setBautl(jsonArray.getJSONObject(i).getString("bautl"));
//			result.setAstxt(jsonArray.getJSONObject(i).getString("astxt"));
//			result.setAnzfehler(jsonArray.getJSONObject(i).getString("anzfehler"));
//			result.setFegrp(jsonArray.getJSONObject(i).getString("fegrp"));
//			result.setFecod(jsonArray.getJSONObject(i).getString("fecod"));
//			result.setKurztext1(jsonArray.getJSONObject(i).getString("kurztext1"));
//			result.setKurztext2(jsonArray.getJSONObject(i).getString("kurztext2"));
//			result.setCustomwr(jsonArray.getJSONObject(i).getString("customwr"));
//			result.setWaers(jsonArray.getJSONObject(i).getString("waers"));
//			result.setPster(jsonArray.getJSONObject(i).getString("pster"));
//			result.setAusbs(jsonArray.getJSONObject(i).getString("ausbs"));
//			result.setQmnam(jsonArray.getJSONObject(i).getString("qmnam"));
//			result.setFaNme(jsonArray.getJSONObject(i).getString("faNme"));
//			result.setMngrp(jsonArray.getJSONObject(i).getString("mngrp"));
//			result.setParnr(jsonArray.getJSONObject(i).getString("parnr"));
//			result.setBpName(jsonArray.getJSONObject(i).getString("bpName"));
//			result.setDelivD(jsonArray.getJSONObject(i).getString("delivD"));
//			result.setDeliveD(jsonArray.getJSONObject(i).getString("deliveD"));
//			result.setMncod(jsonArray.getJSONObject(i).getString("mncod"));
//			result.setRetAppPrc(jsonArray.getJSONObject(i).getString("retAppPrc"));
//			result.setReciptD(jsonArray.getJSONObject(i).getString("reciptD"));
//			result.setShpTelNo(jsonArray.getJSONObject(i).getString("shpTelNo"));
//			result.setFordat(jsonArray.getJSONObject(i).getString("fordat"));
//			result.setParnr2(jsonArray.getJSONObject(i).getString("parnr2"));
//			result.setAdrnr(jsonArray.getJSONObject(i).getString("adrnr"));
//			result.setUname(jsonArray.getJSONObject(i).getString("uname"));
//
//			resultList.add(result);
//		}
//
//		return resultList;
//    }

	public void updateFaqInqireCount(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
		helpdeskRepository.updateFaqInqireCount(helpdeskFaqFoDTO);
    }
	/** 개인정보 수집이용에 대한 동의 */
	/*
	public List<SysStplat> selectUserAgr(SysStplat sysStplat) throws Exception{
	    return helpdeskRepository.selectUserAgr(sysStplat);
    }
	*/
	/** 이벤트 당첨 여부 */
	public List<HelpdeskNoticeFoResult> selectEventCheck(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return helpdeskRepository.selectEventCheck(helpdeskNoticeFoDTO);
    }
	/** faq 타이틀 조회 */
	public List<CodeViewResult> selectFaqTitleList(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception {
		return helpdeskRepository.selectFaqTitleList(helpdeskFaqFoDTO);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */







}
