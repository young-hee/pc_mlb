package com.plgrim.ncp.biz.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBukmkBrnd;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.repository.mbr.MbrBukmkBrndRepository;
import com.plgrim.ncp.base.repository.mbr.MbrSizeClfcDetailRepository;
import com.plgrim.ncp.base.repository.mbr.MbrSizeClfcRepository;
import com.plgrim.ncp.biz.member.data.MemberOrdGodFoDTO;
import com.plgrim.ncp.biz.member.data.MemberSizeClfcDTO;
import com.plgrim.ncp.biz.member.data.MyBrndFoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.MypageQnaFoDTO;
import com.plgrim.ncp.biz.member.data.MysizeDTO;
import com.plgrim.ncp.biz.member.repository.MemberActivitySelectRepository;
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
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원활동정보 select service
 */
@Service
@Slf4j
public class MemberActivitySelectService extends AbstractService {

/*
	@Autowired
	private MemberBenefitCommandService memberBenefitCommandService;
*/

	@Autowired
	private MemberActivitySelectRepository memberActivitySelectRepository;

	@Autowired
	MbrBukmkBrndRepository mbrBukmkBrndRepository;
	
	/** IF 공통. */
	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	
	/**
	 * 나의 사이즈  
	 */
	@Autowired
	MbrSizeClfcRepository mbrSizeClfcRepository;
	
	/**
	 * 나의 사이즈 상세
     */
	@Autowired
	MbrSizeClfcDetailRepository mbrSizeClfcDetailRepository;


	/** 1:1 문의 리스트	 */
	public Page<MypageMtmFoResult> selectMyInquiryList(MypageMtmFoDTO mypageMtmFoDTO, PageParam pageParam) {
		return memberActivitySelectRepository.selectMyInquiryList(mypageMtmFoDTO, pageParam);
	}

	/** 1:1 문의 상세 */
	public List<MypageMtmFoResult> selectCsInquiryDetail(MypageMtmFoDTO mypageMtmFoDTO)  {
		return memberActivitySelectRepository.selectCsInquiryDetail(mypageMtmFoDTO);
	}

	public int selectMyInquiryListMobileCnt(MypageMtmFoDTO mypageMtmFoDTO)  {
		return memberActivitySelectRepository.selectMyInquiryListMobileCnt(mypageMtmFoDTO);
	}

	public List<MypageMtmFoResult> selectMyInquiryFileList(MypageMtmFoDTO mypageMtmFoDTO)  {
		return memberActivitySelectRepository.selectMyInquiryFileList(mypageMtmFoDTO);
	}

	public List<MypageMtmFoResult> selectMyInquiryOrdGodList(MypageMtmFoDTO mypageMtmFoDTO)  {
		return memberActivitySelectRepository.selectMyInquiryOrdGodList(mypageMtmFoDTO);
	}

	/**
	 * 1:1문의 리스트 카운트
	 */
	public long selectMyInquiryListCount(MypageMtmFoDTO mypageMtmFoDTO)  {
		return memberActivitySelectRepository.selectMyInquiryListCount(mypageMtmFoDTO);
	}

	/** Qna 리스트	 */
	public Page<MypageQnaFoResult> selectMyQnaList(MypageQnaFoDTO mypageQnaFoDTO, PageParam pageParam) {
		return memberActivitySelectRepository.selectMyQnaList(mypageQnaFoDTO, pageParam);
	}

	/** Qna 리스트 상세	 */
	public List<MypageQnaFoResult> selectMyQnaListDetail(MypageQnaFoDTO mypageQnaFoDTO)  {
		return memberActivitySelectRepository.selectMyQnaListDetail(mypageQnaFoDTO);
	}

	public List<MypageQnaFoResult> selectMyQnaListMobile(MypageQnaFoDTO mypageQnaFoDTO)  {
		return memberActivitySelectRepository.selectMyQnaListMobile(mypageQnaFoDTO);
	}


	public Page<MemberOrdGodFoResult> selectMyGoodsNoReviewList(MemberOrdGodFoDTO dto,  PageParam pageParam) {
		return memberActivitySelectRepository.selectMyGoodsNoReviewList(dto, pageParam);
	}

	public Page<MemberOrdGodFoResult> selectMyGoodsReviewList(MemberOrdGodFoDTO dto,  PageParam pageParam) {
		return memberActivitySelectRepository.selectMyGoodsReviewList(dto, pageParam);
	}

	public MemberOrdGodFoResult selectMyGoodsStoreNoReview(SystemPK pk, MemberOrdGodFoDTO dto)  {
		return memberActivitySelectRepository.selectMyGoodsStoreNoReview(dto);
	}

	public long selectMyGoodsNoReviewListCount(MemberOrdGodFoDTO dto) {
		return memberActivitySelectRepository.selectMyGoodsNoReviewListCount(dto);
	}
	
	public long selectMyGoodsReviewListCount(MemberOrdGodFoDTO dto) {
		return memberActivitySelectRepository.selectMyGoodsReviewListCount(dto);
	}
	
	public MemberOrdGodFoResult selectMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto) {
		return memberActivitySelectRepository.selectMyGoodsReview(dto);
	}
	
	/**
	 * Select my brnd list.
	 *
	 * @param myBrndFoDTO the my brnd fo dto
	 * @param pageParam the page param
	 * @return the list
	 * @ the exception
	 */
	public Page<MyBrndFoResult> selectMyBrndList(MyBrndFoDTO myBrndFoDTO, PageParam pageParam) {
		return memberActivitySelectRepository.selectMyBrndList(myBrndFoDTO, pageParam);    
	}
	
	/**
	 * WishList 조회
	 *
	 * @param MypageFoDTO [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 5. 6
	 */
	public Page<MypageWishFoResult> getMyWishList(MypageFoDTO dto,  PageParam pageParam)  {
		// 칼라 목록
		if(GoodsEnum.KOR.toString().equals(dto.getLang())){
			dto.setLanguage("KO");
		}else if(GoodsEnum.ENG.toString().equals(dto.getLang())){
			dto.setLanguage("EN");
		}else{
			dto.setLanguage("ZH");
		}
		return memberActivitySelectRepository.selectMyWishList(dto , pageParam);
	}
	
	/**
	 * 재입고알람내역조회
	 *
	 * @param MypageFoDTO [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 5. 6
	 */
	public Page<MypageAlrmFoResult> getMyAlarmsForStockList(MypageFoDTO dto,  PageParam pageParam)  {
		// 칼라 목록
		if(GoodsEnum.KOR.toString().equals(dto.getLang())){
			dto.setLanguage("KO");
		}else if(GoodsEnum.ENG.toString().equals(dto.getLang())){
			dto.setLanguage("EN");
		}else{
			dto.setLanguage("ZH");
		}
		return memberActivitySelectRepository.selectMyAlarmsForStockList(dto , pageParam);
	}

	/**
	 * 메인 알람목록 조회
	 *
	 * @param MypageFoDTO [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 5. 6
	 */
	public List<MypageMainFoResult> getMyPageAlramList(MypageFoDTO dto)  {
		return memberActivitySelectRepository.selectMyPageAlramList(dto);
	}
	
	public List<MypageWishFoResult> getMyWishListMobile(MypageFoDTO dto)  {
		// 칼라 목록
		if(GoodsEnum.KOR.toString().equals(dto.getLang())){
			dto.setLanguage("KO");
		}else if(GoodsEnum.ENG.toString().equals(dto.getLang())){
			dto.setLanguage("EN");
		}else{
			dto.setLanguage("ZH");
		}
		return memberActivitySelectRepository.selectMyWishListMobile(dto);
	}
	
	public Page<MypageEvtFoResult> selectMyEventList(MypageFoDTO dto,PageParam pageParam) {
		return memberActivitySelectRepository.selectMyEventList(dto, pageParam);
	}
	
	public long selectMyEventPrizeListCount(MypageFoDTO dto) {
		return memberActivitySelectRepository.selectMyEventPrizeListCount(dto);
	}
	
	/**
	 * Mypage Main  오늘본 상품 조회
	 *
	 * @param MypageFoDTO [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 5. 6
	 */
	public Page<MypageTdGodFoResult> getMyTodayGodList(MypageFoDTO dto,  PageParam pageParam)  {
		return memberActivitySelectRepository.selectMyTodayGodList(dto , pageParam);
	}
	
	public long checkFirstLogin(Mbr mbr) {
		return memberActivitySelectRepository.checkFirstLogin(mbr);
	}
	
	public List<MypageMtmFoResult> selectMyInquiryListMobile(MypageMtmFoDTO mypageMtmFoDTO) {
		return memberActivitySelectRepository.selectMyInquiryListMobile(mypageMtmFoDTO);    
	}
	
	public List<MysizeDTO> selectMyBasicSize(String mbrNo) {
		return memberActivitySelectRepository.selectMyBasicSize(mbrNo);
	}
	
	public List<MypageMysizeResult> selectMyBasicSizeList(String mbrNo) {
		return memberActivitySelectRepository.selectMyBasicSizeList(mbrNo);
	}
	
	public MemberOrdGodFoResult selectMyGoodsNoReview(SystemPK pk, MemberOrdGodFoDTO dto) {
		return memberActivitySelectRepository.selectMyGoodsNoReview(dto);
	}
	
	public long selectMyTodayGodListCount(MypageFoDTO dto)  {
		return memberActivitySelectRepository.selectMyTodayGodListCount(dto);
	}
	
	public long selectMyQnaListCount(MypageQnaFoDTO mypageQnaFoDTO)  {
		return memberActivitySelectRepository.selectMyQnaListCount(mypageQnaFoDTO); 
	}
	
	public int selectMyQnaListMobileCnt(MypageQnaFoDTO mypageQnaFoDTO) {
		return memberActivitySelectRepository.selectMyQnaListMobileCnt(mypageQnaFoDTO);
	}
	
	public long checkFirstAppDownload(Mbr mbr) {
		return memberActivitySelectRepository.checkFirstAppDownload(mbr);
	}
    
    public long getMyPageAlramListCnt(MypageFoDTO dto)  {
		return memberActivitySelectRepository.selectMyPageAlramListCnt(dto);
	}

	/**
	 * Select my brand.
	 *
	 * @param mbrBukmkBrnd the mbr bukmk brnd
	 * @return the mbr bukmk brnd
	 * @ the exception
	 */
	public MbrBukmkBrnd selectMyBrand(MbrBukmkBrnd mbrBukmkBrnd)  {
		MbrBukmkBrnd res = mbrBukmkBrndRepository.selectMbrBukmkBrnd(mbrBukmkBrnd);

        return res;
	}
    
    public List<MypageMainFoResult> getMyPageAlramListMb(MypageFoDTO dto)  {
		return memberActivitySelectRepository.selectMyPageAlramListMb(dto);
	}
    
	public MypageMysizeResult selectMysizeOne(MemberSizeClfcDTO memberSizeClfcDTO){
		return memberActivitySelectRepository.selectMysizeOne(memberSizeClfcDTO);
	}

	public int selectMysizeCount(String mbrNo){
		return memberActivitySelectRepository.selectMysizeCount(mbrNo);
	}
	
	public MypageWishFoResult selectGodWishList(MypageFoDTO dto)  {
		 
		return memberActivitySelectRepository.selectGodWishList(dto);
	}
	
	public MypageWishFoResult selectGodWishListCount(MypageFoDTO dto)  {
		 
		return memberActivitySelectRepository.selectGodWishListCount(dto);
	}
}
