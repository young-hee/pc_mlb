package com.plgrim.ncp.biz.member.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskFaqFoResult;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.framework.page.PageParam;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

/**
 * 회원주문정보 select repository
 */
@Repository
public class MemberOrderSelectRepository extends AbstractRepository  {
 
	/** 1:1문의 주문리스트	*/
	public List<MypageMtmFoResult> selectOrdList(MypageMtmFoDTO mypageMtmFoDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectOrdList", mypageMtmFoDTO);
    }
	
	/** 1:1문의 8월20일 이전 주문내역 확인	*/
	public int selectInquiryOrdMOListBefor0822(MypageMtmFoDTO mypageMtmFoDTO) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectInquiryOrdMOListBefor0822", mypageMtmFoDTO);
    }
	
	/** 1:1문의 주문상품리스트 페이징 MO	*/
	public Page<MypageMtmFoResult> selectInquiryOrdGodMOList(MypageMtmFoDTO mypageMtmFoDTO,PageParam pageParam) {
		
		
		RepositoryHelper.makePageEntityIndex(mypageMtmFoDTO, pageParam);
		
		if(mypageMtmFoDTO.getCalendarFrom() != null && mypageMtmFoDTO.getCalendarTo() != null){
		SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd");		
		test.format(mypageMtmFoDTO.getCalendarFrom());		
		test.format(mypageMtmFoDTO.getCalendarTo());
		}
		List<MypageMtmFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectInquiryGodMOList", mypageMtmFoDTO);
		
	
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectInquiryOrdGodListCount", mypageMtmFoDTO);
	
		//	long totalRow = selectCountFaq(helpdeskFaqFoDTO);
		return new PageImpl<MypageMtmFoResult>(result, pageParam.getPageable(), totalRow);
	   
		//return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectOrdList", mypageMtmFoDTO);
    }
	
	/** 1:1문의 주문상품리스트 페이징	*/
	public Page<MypageMtmFoResult> selectInquiryOrdGodList(MypageMtmFoDTO mypageMtmFoDTO,PageParam pageParam) {
		
	
		RepositoryHelper.makePageEntityIndex(mypageMtmFoDTO, pageParam);
		
		/*if(mypageMtmFoDTO.getCalendarFrom() != null && mypageMtmFoDTO.getCalendarTo() != null){
		SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd");		
		test.format(mypageMtmFoDTO.getCalendarFrom());		
		test.format(mypageMtmFoDTO.getCalendarTo());
		}*/
		List<MypageMtmFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectGodList", mypageMtmFoDTO);
		
	
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectInquiryOrdGodListCount", mypageMtmFoDTO);
	
		//	long totalRow = selectCountFaq(helpdeskFaqFoDTO);
		return new PageImpl<MypageMtmFoResult>(result, pageParam.getPageable(), totalRow);
	   
		//return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectOrdList", mypageMtmFoDTO);
    }
	
	
	
	
	
	
	/** 1:1문의 상품리스트	*/
	public List<MypageMtmFoResult> selectGodList(MypageMtmFoDTO mypageMtmFoDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectGodList", mypageMtmFoDTO);
    }
	
	/** 1:1상담 상품명 조회	 */
	public List<OrdGod> selectOrdGodNm(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectOrdGodNm",ordNo);
	}
	
	public GodGodEvl selectMyGoodsStoreReview(GodGodEvl godGodEvl) {
		GodGodEvl review =  getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyGoodsStoreReview", godGodEvl);
		return review;
	}
	
	public String selectErpGodNoByStatus(String matNo) {
		String godNo =  getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectErpGodNoByStatus", matNo);
		return godNo;
	}
	
	/**
	 * ERP 상품명 조회
	 */
	public String selectErpGoodNm(MypageFoDTO dto) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.mbr.reserve.selectErpGoodNm", dto);
	}
    
    /**
	 * 환불계좌 목록 조회.
	 */
	public List<MbrRfdBnkAcct> selectMemberRefundAccountList(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		return getSession1().selectList("com.plgrim.ncp.biz.mbr.rfd.bnk.acct.selectMemberRefundAccountList", mbrRfdBnkAcct);
	}
	
	/**
	 * 픽업매장 -> 일반배송 전환 가능여부 조회
	 * 
	 * @param mypageOrderInfoDTO
	 * @return
	 * @
	 */
	public MypageOrderInfoDTO getPickupDeliveryChangeYn(MypageOrderInfoDTO mypageOrderInfoDTO)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.getPickupDeliveryChangeYn", mypageOrderInfoDTO);
	}

	/**
	 * 픽업매장 -> 일반배송 전환 가능여부 조회 ( 옵션 판매상태 기준 : #47710 픽업판매중인 상품이 있는 경우 일반배송으로 전환 불가 )
	 *
	 * @param ordNo
	 * @return String
	 */
	public String getPickupDeliveryChangeYnByItmStat(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.getPickupDeliveryChangeYnByItmStat", ordNo);
	}

	/**
     * 배송지 목록 조회.
     *
     * @param mbrDlvsp [설명]
     * @return List [설명]
     */
	public List<MemberBoResult> selectDeliveryLocationList(MbrDlvsp mbrDlvsp) {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.dlvsp.selectDeliveryLocationList", mbrDlvsp);
    }

	/**
     * 배송지 목록 조회 Page.
     *
     * @param mbrDlvsp [설명]
     * @return List, Integer [설명]
     */
    public Page<MemberBoResult> selectDeliveryLocationPageList(MbrDlvsp mbrDlvsp,  PageParam pageParam)  {
        RepositoryHelper.makePageEntityIndex(mbrDlvsp, pageParam);
        List<MemberBoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.dlvsp.selectDeliveryLocationPageList", mbrDlvsp);
        Integer totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.mbr.dlvsp.selectDeliveryLocationCnt", mbrDlvsp);
        return new PageImpl<MemberBoResult>(results, pageParam.getPageable(), totalRow);
    }
    
    /**
     * 회원배송지 순번 얻어오기
     *
     * @param mbrDlvsp [설명]
     * @return Integer [설명]
     * @since 2016. 11. 17
     */
	public Integer getMbrDeliveryAdbukTurn(MbrDlvsp mbrDlvsp) {
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.dlvsp.getMbrDeliveryAdbukTurn", mbrDlvsp);
    }
	
}
