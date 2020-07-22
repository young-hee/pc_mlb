package com.plgrim.ncp.biz.helpdesk.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.biz.member.data.BundleOrderFoDTO;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskFaqFoDTO;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskNmbrStplatAgrFoDTO;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskNoticeFoDTO;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskRepairDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskFaqFoResult;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskNoticeFoResult;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskRepairResult;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Repository
public class HelpdeskRepository extends AbstractRepository{



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
	/** faq 리스트	 */
	public Page<HelpdeskFaqFoResult> selectFaqList(HelpdeskFaqFoDTO helpdeskFaqFoDTO, PageParam pageParam) throws Exception{

		RepositoryHelper.makePageEntityIndex(helpdeskFaqFoDTO, pageParam);
		List<HelpdeskFaqFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.help.selectFaqList", helpdeskFaqFoDTO);

		long totalRow = selectCountFaq(helpdeskFaqFoDTO);

		return new PageImpl<HelpdeskFaqFoResult>(results, pageParam.getPageable(), totalRow);
    }

	/** 공지 리스트 */
	public Page<HelpdeskNoticeFoResult> selectNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO, PageParam pageParam) throws Exception{
		RepositoryHelper.makePageEntityIndex(helpdeskNoticeFoDTO, pageParam);


		List<HelpdeskNoticeFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.help.selectNoticeList", helpdeskNoticeFoDTO);

		long totalRow = selectCountNotice(helpdeskNoticeFoDTO);


		return new PageImpl<HelpdeskNoticeFoResult>(results, pageParam.getPageable(), totalRow);
    }
	/** 공지 리스트 for MobilePhone */
	public List<HelpdeskNoticeFoResult> selectMpNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{

		List<HelpdeskNoticeFoResult>results = getSession1().selectList("com.plgrim.ncp.biz.help.selectMpNoticeList", helpdeskNoticeFoDTO);
		return results;
    }
	/** 공지 목록 for Popup */
	public List<HelpdeskNoticeFoResult> selectPopupNoticeList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.help.selectPopupNoticeList", helpdeskNoticeFoDTO);
    }
	/** 고객서비스 비회원 약관 동의**/
	public void insertNmbrStplatAgr(Map map) throws Exception{
		 getSession1().insert("com.plgrim.ncp.biz.help.insertNmbrStplatAgr",map);
	}
	/** 공지사항 타이틀 조회 */
	public List<CodeViewResult> selectNotiTitleList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.help.selectNotiTitleList", helpdeskNoticeFoDTO);
	}
	/** 공지사항 상세 */
	public List<HelpdeskNoticeFoResult> selectNoticeDetail(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return getSession1().selectList("com.plgrim.ncp.biz.help.selectNoticeDetail", helpdeskNoticeFoDTO);
    }
	/** 공지사항 상세 조회수 변경 */
	public void updateNoticeInqireCount(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    getSession1().update("com.plgrim.ncp.biz.help.updateSysNotiInqireCount", helpdeskNoticeFoDTO);
    }

	/** 공지사항 이벤트 상세 */
	public List<HelpdeskNoticeFoResult> selectNoticeEvtDetail(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return getSession1().selectList("com.plgrim.ncp.biz.help.selectNoticeEvtDetail", helpdeskNoticeFoDTO);
    }
	/** 대표공지 리스트	 */
	public List<HelpdeskNoticeFoResult> selectNoticeRprstList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return getSession1().selectList("com.plgrim.ncp.biz.help.selectNoticeRprstList", helpdeskNoticeFoDTO);
    }

	/** 고객센터 메인 FAQ Top10 리스트	 */
	public List<HelpdeskFaqFoResult> selectFaqTop10List(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
	    return getSession1().selectList("com.plgrim.ncp.biz.help.selectFaqTop10List", helpdeskFaqFoDTO);
    }

	/** 고객센터 메인 FAQ Top5 리스트	 */
	public List<HelpdeskFaqFoResult> selectFaqTop5List(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
	    return getSession1().selectList("com.plgrim.ncp.biz.help.selectFaqTop5List", helpdeskFaqFoDTO);
    }

	/** 고객센터 메인 공지 New5 리스트	 */
	public List<HelpdeskNoticeFoResult> selectNoticeNew5List(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return getSession1().selectList("com.plgrim.ncp.biz.help.selectNoticeNew5List", helpdeskNoticeFoDTO);
    }
	/** 고객센터 메인 공지 New3 리스트	 */
	public List<HelpdeskNoticeFoResult> selectNoticeNew3List(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.help.selectNoticeNew3List", helpdeskNoticeFoDTO);
	}
	/** 이전글 보기 */
	public List<HelpdeskNoticeFoResult> selectPreNotiSn(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return getSession1().selectList("com.plgrim.ncp.biz.help.selectPreNotiSn", helpdeskNoticeFoDTO);
    }
	/** 다음글 보기 */
	public List<HelpdeskNoticeFoResult> selectNextNotiSn(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
	    return getSession1().selectList("com.plgrim.ncp.biz.help.selectNextNotiSn", helpdeskNoticeFoDTO);
    }
	/**	Faq 리스트 카운트 */
	public long selectCountFaq(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.help.selectCountFaq", helpdeskFaqFoDTO);
	}
	/** 공지리스트  카운트	 */
	public long selectCountNotice(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.help.selectCountNotice", helpdeskNoticeFoDTO);
	}
	/** 단체주문/제휴문의 등록	 */
	public void insertBundleOrder(BundleOrderFoDTO bundleOrderFoDTO) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.help.insertBundleOrder", bundleOrderFoDTO);
	}
	/** 단체주문/제휴문의 첨부파일 등록	 */
	public void insertOrgztOrdAffInqAtchmnfl(BundleOrderFoDTO bundleOrderFoDTO) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.help.insertOrgztOrdAffInqAtchmnfl", bundleOrderFoDTO);
	}

	public List<HelpdeskRepairResult> selectRepairMbrRceptNo(HelpdeskRepairDTO helpdeskRepairDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.help.selectRepairMbrRceptNo", helpdeskRepairDTO);
	}
	public int updateFaqInqireCount(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
		return getSession1().update("com.plgrim.ncp.biz.help.updateFaqInqireCount", helpdeskFaqFoDTO);
	}
	/** 개인정보 수집이용에 대한 동의 */
	/*
	public List<SysStplat> selectUserAgr(SysStplat sysStplat) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.help.selectUserAgr",sysStplat);
	}
	*/
	/** 이벤트 당첨 여부 */
	public List<HelpdeskNoticeFoResult> selectEventCheck(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.help.selectEventCheck",helpdeskNoticeFoDTO);
	}
	/** faq 타이틀 조회 */
	public List<CodeViewResult> selectFaqTitleList(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.help.selectFaqTitleList", helpdeskFaqFoDTO);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */










}
