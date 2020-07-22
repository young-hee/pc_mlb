package com.plgrim.ncp.biz.member.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.repository.mbr.MbrBukmkBrndRepository;
import com.plgrim.ncp.biz.member.data.MemberOrdGodFoDTO;
import com.plgrim.ncp.biz.member.data.MemberSizeClfcDTO;
import com.plgrim.ncp.biz.member.data.MyBrndFoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.MypageQnaFoDTO;
import com.plgrim.ncp.biz.member.data.MysizeDTO;
import com.plgrim.ncp.biz.member.result.MemberOrdGodFoResult;
import com.plgrim.ncp.biz.member.result.MyBrndFoResult;
import com.plgrim.ncp.biz.member.result.MypageAlrmFoResult;
import com.plgrim.ncp.biz.member.result.MypageEvtFoResult;
import com.plgrim.ncp.biz.member.result.MypageMainFoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.biz.member.result.MypageMysizeResult;
import com.plgrim.ncp.biz.member.result.MypageQnaFoResult;
import com.plgrim.ncp.biz.member.result.MypageTdGodFoResult;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 회원활동정보 select repository
 */
@Repository
public class MemberActivitySelectRepository extends AbstractRepository {
 
	@Autowired
	MbrBukmkBrndRepository mbrBukmkBrndRepository;

	/**
	 * 1:1 문의 리스트
	 * 
	 * @param mypageMtmFoDTO
	 * @param pageParam
	 * @return
	 */
	public Page<MypageMtmFoResult> selectMyInquiryList(MypageMtmFoDTO mypageMtmFoDTO, PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(mypageMtmFoDTO, pageParam);
		List<MypageMtmFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyInquiryList", mypageMtmFoDTO); 
		long totalRow = selectMyInquiryListCount(mypageMtmFoDTO);
		return new PageImpl<MypageMtmFoResult>(results, pageParam.getPageable(), totalRow);
	}

	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public long selectMyInquiryListCount(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectInquiryCount", mypageMtmFoDTO);
	}

	/**
	 * 1:1 문의 상세
	 * 
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public List<MypageMtmFoResult> selectCsInquiryDetail(MypageMtmFoDTO mypageMtmFoDTO)  {
	    return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectCsInquiryDetail", mypageMtmFoDTO);
    }

	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int selectMyInquiryListMobileCnt(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyInquiryListMobileCnt", mypageMtmFoDTO);    
	}

	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public List<MypageMtmFoResult> selectMyInquiryListMobile(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyInquiryListMobile", mypageMtmFoDTO);    
	}

	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public List<MypageMtmFoResult> selectMyInquiryFileList(MypageMtmFoDTO mypageMtmFoDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyInquiryFileList", mypageMtmFoDTO);
    }

	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public List<MypageMtmFoResult> selectMyInquiryOrdGodList(MypageMtmFoDTO mypageMtmFoDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyInquiryOrdGodList", mypageMtmFoDTO);
    }

	/**
	 * Qna 리스트
	 * 
	 * @param mypageQnaFoDTO
	 * @param pageParam
	 * @return
	 */
	public Page<MypageQnaFoResult> selectMyQnaList(MypageQnaFoDTO mypageQnaFoDTO, PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(mypageQnaFoDTO, pageParam);
		List<MypageQnaFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyQnaList", mypageQnaFoDTO); 
		long totalRow = selectMyQnaListCount(mypageQnaFoDTO);
		return new PageImpl<MypageQnaFoResult>(results, pageParam.getPageable(), totalRow);
    }

	/**
	 * @param mypageQnaFoDTO
	 * @return
	 */
	public long selectMyQnaListCount(MypageQnaFoDTO mypageQnaFoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectQnaCount", mypageQnaFoDTO);
	}

	/**
	 * Qna 리스트 상세
	 * 
	 * @param mypageQnaFoDTO
	 * @return
	 */
	public List<MypageQnaFoResult> selectMyQnaListDetail(MypageQnaFoDTO mypageQnaFoDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyQnaListDetail", mypageQnaFoDTO);
    }

	/**
	 * @param mypageQnaFoDTO
	 * @return
	 */
	public List<MypageQnaFoResult> selectMyQnaListMobile(MypageQnaFoDTO mypageQnaFoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyQnaListMobile", mypageQnaFoDTO);    
	}

	/**
	 * @param dto
	 * @return
	 */
	public long selectMyGoodsNoReviewListCount(MemberOrdGodFoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyGoodsNoReviewListCount", dto);
	}

	/**
	 * @param dto
	 * @return
	 */
	public long selectMyGoodsReviewListCount(MemberOrdGodFoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyGoodsReviewListCount", dto);
	}

	/**
	 * @param dto
	 * @return
	 */
	public MemberOrdGodFoResult selectMyGoodsStoreNoReview(MemberOrdGodFoDTO dto) {
		MemberOrdGodFoResult review =  getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyGoodsStoreNoReview", dto);
		return review;
	}

	/**
	 * @param dto
	 * @return
	 */
	public MemberOrdGodFoResult selectMyGoodsNoReview(MemberOrdGodFoDTO dto) {
		MemberOrdGodFoResult review =  getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyGoodsNoReview", dto);
		return review;
	}

	/**
	 * @param mbrNo
	 * @return
	 */
	public List<MysizeDTO> selectMyBasicSize(String mbrNo) {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.size.selectMyBasicSize", mbrNo);
    }

	/**
	 * @param mbrNo
	 * @return
	 * @
	 */
	public List<MypageMysizeResult> selectMyBasicSizeList(String mbrNo) {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.size.selectMyBasicSizeList", mbrNo);
    }
	
	/**
	 * @param dto
	 * @param pageParam
	 * @return
	 */
	public Page<MemberOrdGodFoResult> selectMyGoodsReviewList(MemberOrdGodFoDTO dto, PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MemberOrdGodFoResult> reviewList =  getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyGoodsReviewList", dto);
		
		long listCnt = selectMyGoodsReviewListCount(dto);
		
		return new PageImpl<MemberOrdGodFoResult>(reviewList, pageParam.getPageable(),listCnt);
    }

	/**
	 * Select my brnd list.
	 *
	 * @param myBrndFoDTO
	 *            the my brnd fo dto
	 * @param pageParam
	 *            the page param
	 * @return the page
	 */
	public Page<MyBrndFoResult> selectMyBrndList(MyBrndFoDTO myBrndFoDTO, PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(myBrndFoDTO, pageParam);
		List<MyBrndFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyBrndList", myBrndFoDTO); 
		Integer totalRow = selectMyBrndListCount(myBrndFoDTO);
		return new PageImpl<MyBrndFoResult>(results, pageParam.getPageable(), totalRow);
	}

	/**
	 * Select my brnd list count.
	 *
	 * @param myBrndFoDTO
	 *            the my brnd fo dto
	 * @return the long
	 */
	public Integer selectMyBrndListCount(MyBrndFoDTO myBrndFoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyBrndListCount", myBrndFoDTO);
	}

	/**
	 * My Wish List 조회
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return PageImpl<MypageWishFoResult>
	 */
	public Page<MypageWishFoResult> selectMyWishList(MypageFoDTO dto,  PageParam pageParam)  {
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MypageWishFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyWishList", dto);
		long totalRow =  getSession1().selectList("com.plgrim.ncp.biz.mypage.countMyWishList", dto).size();
		return new PageImpl<MypageWishFoResult>(results, pageParam.getPageable(), totalRow);
	}

	/**
	 * 재입고알람조회
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return PageImpl<MypageAlrmFoResult>
	 */
	public Page<MypageAlrmFoResult> selectMyAlarmsForStockList(MypageFoDTO dto ,  PageParam pageParam)  {
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MypageAlrmFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyAlarmsForStockList", dto);
		long totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.mypage.countMyAlarmsForStockList", dto);
		return new PageImpl<MypageAlrmFoResult>(results, pageParam.getPageable(), totalRow);
		
	}

	/**
	 * 메인알람 조회
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return List<MypageMainFoResult>
	 */
	public List<MypageMainFoResult> selectMyPageAlramList(MypageFoDTO dto)  {
		return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyPageAlramList", dto);
	}

	/**
	 * @param dto
	 * @return
	 */
	public List<MypageWishFoResult> selectMyWishListMobile(MypageFoDTO dto)  {
		return getSession1().selectList("com.plgrim.ncp.biz.mypage.countMyWishList", dto);
	}

	/**
	 * @param dto
	 * @return
	 */
	public long selectMyEventListCount(MypageFoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyEventListCount", dto);
	}

	/**
	 * @param dto
	 * @return
	 */
	public long selectMyEventPrizeListCount(MypageFoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyEventPrizeListCount", dto);
	}

	/**
	 * Mypage Main 오늘본 상품 조회
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return PageImpl<MypageWishFoResult>
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public Page<MypageTdGodFoResult> selectMyTodayGodList(MypageFoDTO dto ,  PageParam pageParam)  {
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MypageTdGodFoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyTodayGodList", dto);
		long totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.mypage.countMyTodayGodList", dto);
		return new PageImpl<MypageTdGodFoResult>(results, pageParam.getPageable(), totalRow);
		
	}

	/**
	 * 상시 프로모션 - 기회원 감사 쿠폰
	 * 
	 * @param mbr
	 * @return
	 */
	public long checkFirstLogin(Mbr mbr) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.mbr.checkFirstLogin", mbr);
    }

	public MemberOrdGodFoResult selectMyGoodsReview(MemberOrdGodFoDTO dto) {
		MemberOrdGodFoResult review =  getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyGoodsReview", dto);
		return review;
    }

	public Page<MemberOrdGodFoResult> selectMyGoodsNoReviewList(MemberOrdGodFoDTO dto, PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MemberOrdGodFoResult> reviewList = getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyGoodsNoReviewList", dto);
		long listCnt = selectMyGoodsNoReviewListCount(dto);
		
		return new PageImpl<MemberOrdGodFoResult>(reviewList, pageParam.getPageable(),listCnt);
	}

	public Page<MypageEvtFoResult> selectMyEventList(MypageFoDTO dto,PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MypageEvtFoResult> evtList =  getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyEventList", dto);
		
		long evtListCnt = selectMyEventListCount(dto);
		
		return new PageImpl<MypageEvtFoResult>(evtList, pageParam.getPageable(),evtListCnt);
    }

	public long selectMyTodayGodListCount(MypageFoDTO dto)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.countMyTodayGodList", dto);
		
	}

	public int selectMyQnaListMobileCnt(MypageQnaFoDTO mypageQnaFoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectMyQnaListMobileCnt", mypageQnaFoDTO);    
	}

	/**
	 * 상시 프로모션 - 첫 앱다운로드
	 * 
	 */
	public long checkFirstAppDownload(Mbr mbr) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.checkFirstAppDownload", mbr);
	}

	public long selectMyPageAlramListCnt(MypageFoDTO dto)  {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.countMyAlarmsForStockList", dto);
	}

	public List<MypageMainFoResult> selectMyPageAlramListMb(MypageFoDTO dto)  {
		return getSession1().selectList("com.plgrim.ncp.biz.mypage.selectMyPageAlramListMb", dto);
		
	}
	
	public MypageMysizeResult selectMysizeOne(MemberSizeClfcDTO memberSizeClfcDTO){
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.size.selectMbrSizeOne", memberSizeClfcDTO);
	}

	public int selectMysizeCount(String mbrNo){
		return getSession1().selectOne("com.plgrim.ncp.biz.mbr.size.selectMysizeCount", mbrNo);
	}

	public int getMbrTableTurn(String tableName, String orderColumn, Map<String, Object> conditions) {
		return getIdGenService().generateDBOrder(getSession1(), tableName, orderColumn, conditions, DatabaseType.ORACLE);
	}
	
	public int getGodEvlTurn(GodGodEvl godGodEvl) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.getGodEvlTurn", godGodEvl);
	}
	
	public MypageWishFoResult selectGodWishList(MypageFoDTO dto)  {
 
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectGodWishList", dto);
	}
	
	public MypageWishFoResult selectGodWishListCount(MypageFoDTO dto)  {
		 
		return getSession1().selectOne("com.plgrim.ncp.biz.mypage.selectGodWishListCount", dto);
	}
}
