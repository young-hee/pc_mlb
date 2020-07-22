/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim 
 * @since       2015. 8. 5       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;
import com.plgrim.ncp.base.repository.god.GodRepository;
import com.plgrim.ncp.biz.display.data.DspCtgrySearchFoDTO;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.ContextService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DisplayGoodsRepository extends GodRepository {
    private List<GoodsInterestsGodFoResult> setSkyScrapers(InterestSearchFoDTO interestSearchFoDTO, String sqlPath) throws Exception {
        Cookie cookies[] = ContextService.getCurrentRequest().getCookies();
        List<GoodsInterestsGodFoResult>  result = new ArrayList<GoodsInterestsGodFoResult>();
        List<MbrTodayGod> skyScrapers = new ArrayList<MbrTodayGod>();
        for (int i = 0; i < cookies.length; i++) {
            Functions.cookies(cookies, skyScrapers, i);
           }
      
        if(skyScrapers != null && skyScrapers.size() > 0){
            interestSearchFoDTO.setSkyScrapers(skyScrapers);
            return getSession1().selectList(sqlPath, interestSearchFoDTO);
        }else{
            return result;
        }
    }
    
	/**
	 * Interests 용 최근 본 상품 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 9
	 */
	public List<GoodsInterestsGodFoResult> selectTodayGodList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	  
        if (ContextService.getCurrentRequest().getCookies() != null && !ContextService.hasRole()) {
            
            return setSkyScrapers(interestSearchFoDTO,"com.plgrim.ncp.web.mb.dx.display.selectTodayCookies");
           
      
       }else{
            
           return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectTodayGodList", interestSearchFoDTO);
            
        }
	}

	/**
	 * Interest 용 주간 BEST 상품 조회(나이 정보가 없는 경우)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 10
	 */
	public List<GoodsInterestsGodFoResult> selectWeeklyBestGodListForNoAge(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectWeeklyBestGodListForNoAge", interestSearchFoDTO);
    }

	/**
	 * Interest 용 주간 BEST 상품 조회(나이 정보가 있는 경우)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 10
	 */
	public List<GoodsInterestsGodFoResult> selectWeeklyBestGodListForAge(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectWeeklyBestGodListForAge", interestSearchFoDTO);
    }
	
	/**
	 * 최근 2주간 본 상품 중 가장 최근 상품의 카테고리 상품의 BEST 상품 2개 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 11
	 */
	public List<GoodsInterestsGodFoResult> selectBestGodListForLastView(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectBestGodListForLastView", interestSearchFoDTO);
    }

	/**
	 * 최근 2주간 본 상품 중 가장 최근 상품의 카테고리 상품의 신상품 3개 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 11
	 */
	public List<GoodsInterestsGodFoResult> selectNewGodListForLastView(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectNewGodListForLastView", interestSearchFoDTO);
    }

	/**
	 * 대안상품 목록 조회(3개)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 11
	 */
	public List<GoodsInterestsGodFoResult> selectAltGodsList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectAltGodsList", interestSearchFoDTO);
    }

	/**
	 * 최근 본 상품이 속한 카테고리(대카) 기획전 목록 조회(3개)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 13
	 */
	public List<GoodsInterestsGodFoResult> selectMostGodsPlanList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    
        if (ContextService.getCurrentRequest().getCookies() != null && !ContextService.hasRole()) {

            return setSkyScrapers(interestSearchFoDTO,"com.plgrim.ncp.web.mb.dx.display.selectMostGodsPlanCookies");
      
       }else{
            
           return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectMostGodsPlanList", interestSearchFoDTO);
            
        }
	   	   	  
    }

	/**
	 * 장바구니에 담긴 상품이 포함된 묶음할인 기획전 목록 조회(3개)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 13
	 */
	public List<GoodsInterestsGodFoResult> selectCartGodsPlanList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectCartGodsPlanList", interestSearchFoDTO);
    }

	/**
	 * 최근(1개월) 장바구니 상품의 코디 상품 목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 13
	 */
	public List<GoodsInterestsGodFoResult> selectCartGodsCordiList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectCartGodsCordiList", interestSearchFoDTO);
    }

	/**
	 * 최근(1개월) 주문(결제완료) 상품의 코디 상품 목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 13
	 */
	public List<GoodsInterestsGodFoResult> selectOrderGodsCordiList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectOrderGodsCordiList", interestSearchFoDTO);
    }

	/**
	 * 최근 등록된 S-Trend 목록 조회(2개)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 14
	 */
	public List<GoodsInterestsGodFoResult> selectBrandStrendList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectBrandStrendList", interestSearchFoDTO);
    }

	/**
	 * 가장 최근 본 상품이 속한 성별 속성 중 BEST 상품 10개 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 14
	 */
	public List<GoodsInterestsGodFoResult> selectMostGodsRecSexBestList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    
        if (ContextService.getCurrentRequest().getCookies() != null && !ContextService.hasRole()) {

            return setSkyScrapers(interestSearchFoDTO,"com.plgrim.ncp.web.mb.dx.display.selectMostGodsRecSexBestCookies");

       }else{
            
           return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectMostGodsRecSexBestList", interestSearchFoDTO);
            
        }
    }

	/**
	 * 최근 1주일간 대카테고리별 BEST 상품 2개씩 총 10개 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 14
	 */
	public List<GoodsInterestsGodFoResult> selectLCtgryBestList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectLCtgryBestList", interestSearchFoDTO);
    }

	/**
	 * Weekly New 상품 리스트 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 21
	 */
	public List<DspCtgryGodFoResult> selectWeeklyNewGoodList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(dspCtgrySearchFoDTO, dspCtgrySearchFoDTO.getPageParam());
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectWeeklyNewGoodList", dspCtgrySearchFoDTO);
	}

	/**
	 * Weekly New 상품 리스트 Cnt 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 21
	 */
	public int selectWeeklyNewGoodListCnt(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.display.selectWeeklyNewGoodListCnt", dspCtgrySearchFoDTO);
	}
	
	/**
	 * 상품 이미지 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 20
	 */
	public List<GodImg> selectGodImgForSize(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectGodImgForSize", interestSearchFoDTO);
    }

	/**
	 * 통합몰 메인의 Weekly New 상품 목록 조회(10개)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 22
	 */
//	@Cacheable(value="displayGoodsRepository.MBWeeklyNewMainLoading")
	public List<GoodsInterestsGodFoResult> selectWeeklyNewMainList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectWeeklyNewMainList", interestSearchFoDTO);
    }

	/**
	 * 회원이 3개월 이내 주문한 가장 최근 상품 3개 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 23
	 */
	public List<GoodsInterestsGodFoResult> selectLastOrdGodList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectLastOrdGodList", interestSearchFoDTO);
    }
	
	/**
	 * 상품에 대한 연관 카테고리 Best 상품 조회(카테고리 별 2개, 전체 7개)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 28
	 */
	public List<GoodsInterestsGodFoResult> selectRelCtgryBestGodList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectRelCtgryBestGodList", interestSearchFoDTO);
    }

	/**
	 * 상품 아이콘 및 기타 정보 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 29
	 */
	public List<GoodsInterestsGodFoResult> selectGodIconMBList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectGodIconMBList", interestSearchFoDTO);
    }
	
	/**
	 * GNB 검색 배너 정보 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 3
	 */
//	  @Cacheable(
//		      value="mb.displayGoodsRepository.selecta0TextBannerList",
//		      key="{"
//		          + "#a0.aplMbrAtrb,"
//		          + "#a0.aplMbrTp,"
//		          + "#a0.cnrSn,"
//		          + "#a0.device,"
//		          + "#a0.dspCtgryNo,"
//		          + "#a0.grpcoId,"
//		          + "#a0.lang"
//		          + "}")
	public List<GoodsInterestsGodFoResult> selectSearchTextBannerList(InterestSearchFoDTO search) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectSearchTextBannerList", search);
    }
/*	
	public DspBrndBstGod selectBestBrndInfo(List<String> brndIds) throws Exception {
		HashMap<String,List<String>> pMap = new HashMap<String,List<String>>();
		pMap.put("brndIds", brndIds);
		
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.display.selectBestBrndInfo", pMap);
	}
*/	
	/**
	 * 통합몰 메인 TOP SELLER 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<GoodsInterestsGodFoResult> selectTopSellerMainList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectTopSellerMainList", interestSearchFoDTO);
    }

	/**
	 * 통합몰 메인 WHAT'S NEW 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<GoodsInterestsGodFoResult> selectWhatsNewMainList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectWhatsNewMainList", interestSearchFoDTO);
    }

	/**
	 * BEST SELLER 우선노출 상품 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<DspCtgryGodFoResult> selectTopSellerTopList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectTopSellerTopList", dspCtgrySearchFoDTO);
	}

	/**
	 * BEST SELLER 우선노출 상품 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<DspCtgryGodFoResult> selectTopSellerTopListNew(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectTopSellerTopListNew", dspCtgrySearchFoDTO);
	}

	/**
	 * WHAT'S NEW 우선노출 상품 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<DspCtgryGodFoResult> selectWhatsNewTopList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectWhatsNewTopList", dspCtgrySearchFoDTO);
	}

	/**
	 * WHAT'S NEW 우선노출 상품 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<DspCtgryGodFoResult> selectWhatsNewTopListNew(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectWhatsNewTopListNew", dspCtgrySearchFoDTO);
	}

	/**
	 * BEST SELLER, WHAT'S NEW 상품 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<DspCtgryGodFoResult> selectNewBestGodList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		if (dspCtgrySearchFoDTO.getPageParam() != null) {
			RepositoryHelper.makePageEntityIndex(dspCtgrySearchFoDTO, dspCtgrySearchFoDTO.getPageParam());
		}
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectNewBestGodList", dspCtgrySearchFoDTO);
	}

	/**
	 * BEST SELLER, WHAT'S NEW 상품 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<DspCtgryGodFoResult> selectNewBestGodListNew(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectNewBestGodListNew", dspCtgrySearchFoDTO);
	}

	/**
	 * BEST SELLER, WHAT'S NEW 상품수 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCtgrySearchFoDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public int selectNewBestGodListCnt(DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.display.selectNewBestGodListCnt", dspCtgrySearchFoDTO);
	}
	
	/**
	 * 
	 * 인스타 그램 토큰 조회
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public String selectBrndInstagramTokenKey(Map<String, String> paramMap) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.display.selectBrndInstagramTokenKey", paramMap);
	}

	/**
	 * 브랜드 메인 WHAT'S NEW 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2016. 2. 1
	 */
	public List<GoodsInterestsGodFoResult> selectWhatsNewBrndList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectWhatsNewBrndList", interestSearchFoDTO);
    }	

	/**
	 * 브랜드 메인 TOP SELLER 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param interestSearchFoDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 8. 5
	 */
	public List<GoodsInterestsGodFoResult> selectTopSellerBrndList(InterestSearchFoDTO interestSearchFoDTO) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.web.mb.dx.display.selectTopSellerBrndList", interestSearchFoDTO);
    }
}
