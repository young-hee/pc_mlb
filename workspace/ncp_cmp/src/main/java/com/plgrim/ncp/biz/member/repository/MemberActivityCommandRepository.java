package com.plgrim.ncp.biz.member.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfcDetail;
import com.plgrim.ncp.biz.goods.data.GoodsReviewDTO;
import com.plgrim.ncp.biz.member.data.MemberOrdGodFoDTO;
import com.plgrim.ncp.biz.member.data.MemberSizeClfcDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.MypageQnaFoDTO;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;

/**
 * 회원활동정보 command repository
 */
@Repository
public class MemberActivityCommandRepository extends AbstractRepository  {
	
	@Autowired
	@Qualifier("sessionTemplate1")
	private SqlSession sqlSession1;
	 
	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int updateMyInquiry(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().update("com.plgrim.ncp.biz.mypage.updateMyInquiry", mypageMtmFoDTO);
    }
	
	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int deleteMyOrdGodInquiry(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteMyOrdGodInquiry", mypageMtmFoDTO);
    }
	
	/** 1:1상담 상품 등록	 
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int insertCsInquiryOrdGod(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().insert("com.plgrim.ncp.biz.mypage.insertCsInquiryOrdGod", mypageMtmFoDTO);
    }
	
	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int deleteMyFileInquiry(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteMyFileInquiry", mypageMtmFoDTO);
	}
	
	/**
	 * @param csoMtmInqAtchFile
	 */
	public void insertCsoMtmInqAtchFile(CsoMtmInqAtchFile csoMtmInqAtchFile)  {
		getSession1().insert("com.plgrim.ncp.biz.mypage.insertCsoMtmInqAtchFile", csoMtmInqAtchFile);
	}
	
	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int deleteMyInquiry(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().update("com.plgrim.ncp.biz.mypage.deleteMyInquiry", mypageMtmFoDTO);
    }
	
	/**
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int updateMyInqAnsEvl(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().update("com.plgrim.ncp.biz.mypage.updateMyInqAnsEvl", mypageMtmFoDTO);
	}
	
	/** Qna 삭제	 
	 * @param mypageQnaFoDTO
	 * @return
	 */
	public int deleteMyQna(MypageQnaFoDTO mypageQnaFoDTO) {
		return getSession1().update("com.plgrim.ncp.biz.mypage.deleteMyQna", mypageQnaFoDTO);
    }
	
	/** Qna 변경	 
	 * @param mypageQnaFoDTO
	 * @return
	 */
	public int updateMyQna(MypageQnaFoDTO mypageQnaFoDTO) {
	    return getSession1().update("com.plgrim.ncp.biz.mypage.updateMyQna", mypageQnaFoDTO);
    }
	
	/**
	 * @param godGodEvl
	 */
	public void insertMyGoodsReview(GodGodEvl godGodEvl) {
		getSession1().insert("com.plgrim.ncp.biz.mypage.insertGodGodEvl", godGodEvl);
    }
	
	/**
	 * @param godGodEvl
	 */
	public void updateMyGoodsReview(GodGodEvl godGodEvl) {
		getSession1().update("com.plgrim.ncp.biz.mypage.updateGodGodEvl", godGodEvl);
    }
	
	/**
	 * 상품 상품평 첨부 파일 삭제.
	 *
	 * @param godGodEvlAtchFile the GodGodEvlAtchFile
	 * @return the GodGodEvlAtchFile
	 */
	public int deleteGodGodEvlAtchFile(MemberOrdGodFoDTO dto)  {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteGodGodEvlAtchFile", dto);
	}
	
	/**
	 * My Wish 삭제
	 *
	 * @param MypageFoDTO [설명]
	 * @return int
	 */
	public int deleteMyWishList(MypageFoDTO dto)  {
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteMyWishList", dto);
	}
	
	public int deleteMainMyWishList(MypageFoDTO dto)  {
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteMainMyWishList", dto);
	}
	
	
	/**
	 * @param dto
	 * @return
	 */
	public int deleteAllMyWishList(MypageFoDTO dto)  {
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteAllMyWishList", dto);
	}
	
	/**
	 * 최근 본 상품 삭제
	 *
	 * @param MypageFoDTO [설명]
	 * @return int
	 */
	public int deleteMyTodayGodList(MypageFoDTO dto)  {
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteMyTodayGodList", dto);
	}
	
	/**
	 * 최근 본 상품 전체 삭제
	 *
	 * @param MypageFoDTO [설명]
	 * @return int
	 */
	public int deleteAllTodayGoodList(MypageFoDTO dto)  {
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteAllTodayGoodList", dto);
	}
	
	/**
	 * @param dto
	 * @return
	 */
	public int deleteMyAlarmsForStock(MypageFoDTO dto)  {
		return getSession1().delete("com.plgrim.ncp.biz.mypage.deleteMyAlarmsForStock", dto);
	}
	
	/** 1:1상담 등록	 
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int insertCsInquiry(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().insert("com.plgrim.ncp.biz.mypage.insertCsInquiry", mypageMtmFoDTO);
    }
	
	/** 1:1상담 첨부파일 등록	 
	 * @param mypageMtmFoDTO
	 * @return
	 */
	public int insertCsInquiryFile(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().insert("com.plgrim.ncp.biz.mypage.insertCsInquiryFile", mypageMtmFoDTO);
    }
	
	/** 회원 SNS공유이력 삭제
     * @param mbrNo
     * @return
     */
    public int deleteMbrSnscnrshist(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrSnscnrshist", mbrNo);
	}
    
    /** 회원 기념일 삭제.*/
    public int deleteMbrAnnvrsry(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrAnnvrsry", mbrNo);
	}

	/** 회원 로그인 로그 삭제.*/
    public int deleteMbrLoginLog(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrLoginLog", mbrNo);
	}

	/** 회원 오늘본상품 삭제.*/
    public int deleteMbrTodayGod(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrTodayGod", mbrNo);
	}
    
    /** 1:1상담 이력 등록	 */
	public int insertMyHistoryModify(MypageMtmFoDTO mypageMtmFoDTO) {
		return getSession1().insert("com.plgrim.ncp.biz.mypage.insertMyHistoryModify", mypageMtmFoDTO);
	}
	
	// 나의 사이즈 등록
	public void mergeMbrSizeClfc(MbrSizeClfc mbrSizeClfc) {
		
		getSession1().update("com.plgrim.ncp.biz.mbr.size.mergeMbrSizeClfc", mbrSizeClfc);
	}

	// 기본사이즈 미지정 설정
	public void updateMbrSizeClfcUnds(MbrSizeClfc mbrSizeClfc) {
		
		getSession1().update("com.plgrim.ncp.biz.mbr.size.updateMbrSizeClfcUnds", mbrSizeClfc);
	}
	
	// 나의 사이즈 상세 등록
	public void mergeMbrSizeClfcDetail(MbrSizeClfcDetail mbrSizeClfcDetail) {
		
		getSession1().update("com.plgrim.ncp.biz.mbr.size.mergeMbrSizeClfcDetail", mbrSizeClfcDetail);
	}
	
	// 나의 사이즈 삭제
	public void deleteMbrSizeClfc(String mbrNo, String mbrSizeTurn) {
		
		Map<String, String> param  = new HashMap<String, String>();
		param.put("mbrNo", mbrNo);
		param.put("mbrSizeTurn", mbrSizeTurn);
		
		getSession1().delete("com.plgrim.ncp.biz.mbr.size.deleteMbrSizeClfc", param);
	}

	// 나의 사이즈 상세 삭제
	public void deleteMbrSizeClfcDetail(String mbrNo, String mbrSizeTurn) {
		
		Map<String, String> param  = new HashMap<String, String>();
		param.put("mbrNo", mbrNo);
		param.put("mbrSizeTurn", mbrSizeTurn);
		
		getSession1().delete("com.plgrim.ncp.biz.mbr.size.deleteMbrSizeClfcDetail", param);
	}
	
    /**
     * 상품리뷰에 연결된 회원 사이즈 정보 저장
     * 
     * @param dto
     * @return
     */
    public int insertMbrSizeInfoConnectedGoodsReview(GoodsReviewDTO dto) {
    	return getSession1().insert("com.plgrim.ncp.biz.mbr.size.insertMbrSizeInfoConnectedGoodsReview", dto);
    }
    
    /**
     * 상품평 삭제
     * 
     * @param dto
     * @return
     */
    public int deleteMyGoodsReviewNtceYn(MemberOrdGodFoDTO dto) {
    	return getSession1().update("com.plgrim.ncp.biz.mypage.deleteMyGoodsReviewNtceYn", dto);
    }
    
}
