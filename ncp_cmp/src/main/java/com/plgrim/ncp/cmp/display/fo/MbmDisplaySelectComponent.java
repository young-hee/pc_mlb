package com.plgrim.ncp.cmp.display.fo;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;
import com.plgrim.ncp.framework.page.PageParam;

public interface MbmDisplaySelectComponent {

     
	/**
	 * LNB 조회
	 *
	 * @param pk System PK
	 * @param searchDTO Search Data
	 * @return 	전시카테고리2D정보
	 * @throws Exception the exception
	 * @since 2018. 5. 9
	 */
	public MallDspFoCtgryResult lnbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	public Page<DspCtgryGodFoResult> selectDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO,PageParam pageParam) throws Exception;

	public DspCtgryGodFoResult selectFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public List<DspCnrFrResult> selectMajorCategoryContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public void selectCommonCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO,PageParam pageParam ) throws Exception;

	public List<DspCnrFrResult> selectMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	public DspCnrFrResult conttAppend(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public void searchDisplayGodList(Model model,DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception;

	public void selectMbCommonCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception;

	public DspCnrFrResult selectMbMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public MallDspFoCtgryResult lnbMbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public Page<GodGodEvlExtend> selectDisplayReviewList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception;

	public List<GodGodEvlExtend> selectDisplayBestReviewList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public List<MallDspFoCtgryResult> bestCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	public List<GoodsInterestsGodFoResult> getTodayCookiesList(InterestSearchFoDTO interestSearchFoDTO) throws Exception;

	public MallDspFoCtgryResult selectLnbCategory(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public List<DspCtgryGodFoResult> selectRecoPickGodList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public void selectCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	/**
	 * 메인 템플릿 코너영역 조회
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	public List<DspCnrConttExt> selectCnrConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	/**
	 * 마이페이지 LNB 조회를 위한 현재 URI에 대한 마이페이지 LNB 정보 조회
	 * 
	 * @param currentUri
	 * @return DspCtgryScFrDTO
	 * @throws Exception
	 */
	public DspCtgryScFrDTO mypageLnbCurrentCtgryList(String currentUri) throws Exception;
	
	/**
	 * GNB카테고리 조회용
	 * @param dspCtgry
	 * @return
	 * @throws Exception
	 */
	public Object selectGNBCategotyList(DspCtgry dspCtgry) throws Exception;
	
	/**
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	public Object selectCommonBanner(DspCtgryScFrDTO dspCtgryScFrDTO, String env) throws Exception;
	
	public Object selectCategotyNavi(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	public void selectGodTeamList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, Locale currentLocale) throws Exception;
	
	public MallDspFoCtgryResult selectLookBookCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	public List<?> selectLookBookCtgryConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

	public void selectMainRankingGod(Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	public List<?> selectLoobookCornerConttList(DspCtgryScFrDTO dspCtgryScFrDTO, Model model, String domain) throws Exception;
	
	public DspCnrConttExt selectLoobookGodInfo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	public  Page<DspCtgryGodFoResult> searchDisplayGodListPage(Model model,DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception;
	
	public void selectMobileLookBookCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;
	
	public DspCnrFrResult selectUnexhibitedContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception;

}
