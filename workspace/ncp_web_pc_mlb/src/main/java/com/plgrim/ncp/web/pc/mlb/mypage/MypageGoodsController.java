package com.plgrim.ncp.web.pc.mlb.mypage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.member.data.MemberOrdGodFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberOrdGodFoResult;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

@Slf4j
@Controller
@RequestMapping("/mypage/goods")
public class MypageGoodsController extends MypageController{

    @Autowired
    private MemberActivityFOComponent memberActivitySelectComponent;
	
	/**
	 * 상품리뷰 메인 페이지
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_GUEST')")
	@RequestMapping(value = "/reviewView", method = {RequestMethod.GET, RequestMethod.POST})
	public String reviewView(Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) {	
		this.setMypageTitleSetKey(model);
		
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
		location.put("url", null);
        location.put("msgKey", "mypage.submain.activeinfo.ttl");
        locationSet.add(location);
        
        location = new HashMap<String, String>();
        
        location.put("url", "/mypage/goods/reviewView");
        location.put("msgKey", "mypage.goods.review.page.ttl1");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);		

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/review/goods.review.view";
	}	
	
	/**
	 * 상품 리뷰 작성 가능한 목록 조회
	 * 
	 * @param pageNo
	 * @param MemberOrdGodFoDTO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/listNoReview", method = RequestMethod.POST)
	public String listNoReview(HttpServletRequest request, Model model,@RequestParam(value = "pageNo" ,required = false) String pageNo, MemberOrdGodFoDTO dto, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{		
		String returnUrl = "tiles:mypage/review/include/goods.noreview.list";
		try {
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);			
			if(ContextService.hasRole()){
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				Mbr loginMbr = userDetail.getMbr();
				dto.setMbr(loginMbr);
			}
			dto.setLang(systemPK.getLang());
			dto.setMallId(systemPK.getMall());
			Long totalCnt = memberActivitySelectComponent.selectMyGoodsNoReviewListCount(dto);
			PageParam pageParam = getPageService().buildPageParam(StringUtils.defaultIfEmpty(pageNo, "1"), 10);
			Page<MemberOrdGodFoResult> reviewNoList = memberActivitySelectComponent.selectMyGoodsNoReviewList(systemPK, dto, pageParam);
			PageService.makePageResult(reviewNoList, model);
			
			log.info("reviewNoList : {}",reviewNoList.getContent());
			model.addAttribute("totalCnt", totalCnt);
			model.addAttribute("reviewNoList", reviewNoList.getContent());			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("",e);
		}
		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return returnUrl;
	}
	
	/**
	 * 상품 리뷰 작성한 목록 조회
	 * 
	 * @param pageNo
	 * @param MemberOrdGodFoDTO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/listReview.ajax", method = RequestMethod.POST)
	public String listReview(HttpServletRequest request, Model model,@RequestParam(value = "pageNo" ,required = false) String pageNo, MemberOrdGodFoDTO dto, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{		
		String returnUrl = "tiles:mypage/review/include/goods.review.list";
		try {
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);			
			if(ContextService.hasRole()){
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				Mbr loginMbr = userDetail.getMbr();
				dto.setMbr(loginMbr);
			}
			dto.setLang(systemPK.getLang());
			dto.setMallId(systemPK.getMall());
			
			if(dto.getDateStart() == "" || dto.getDateEnd() == ""){
				dto.setDateStart(null);
				dto.setDateEnd(null);
				dto.setSrchMonth("3");
			}
			
			Long totalCnt = memberActivitySelectComponent.selectMyGoodsReviewListCount(dto);
			PageParam pageParam = getPageService().buildPageParam(StringUtils.defaultIfEmpty(pageNo, "1"), 10);
			Page<MemberOrdGodFoResult> reviewList = memberActivitySelectComponent.selectMyGoodsReviewList(systemPK, dto, pageParam);
			PageService.makePageResult(reviewList, model);
			
			log.info("reviewNoList : {}",reviewList.getContent());
			model.addAttribute("totalCnt", totalCnt);
			model.addAttribute("reviewList", reviewList.getContent());
			model.addAttribute("dateStart", dto.getDateStart());
			model.addAttribute("dateEnd", dto.getDateEnd());
			model.addAttribute("srchMonth", dto.getSrchMonth());			
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("",e);
		}
		return returnUrl;
	}	
	
	/**
	 * 상품 리뷰 작성 팝업
	 * @param request
	 * @param model
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/addReview.popup" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String addReview(HttpServletRequest request, Model model, MemberOrdGodFoDTO dto) throws Exception{
		String returnUrl = "tiles:mypage/review/popup/goods.review.add";						
		log.info("MemberOrdGodFoDTO : {}",dto);
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		SecurityUserDetail userDetail = null;
		if(ContextService.hasRole()){
			userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr loginMbr = userDetail.getMbr();
			dto.setMbr(loginMbr);
		}		

		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());

		MemberOrdGodFoResult result;
		result = memberActivitySelectComponent.selectMyGoodsNoReview(systemPK, dto);
		if(result == null){			
			throw new Exception("접근 권한이 없습니다.");
		}
		
		result.setPhotoYn(dto.getPhotoYn());
		model.addAttribute("result",result);
		
		return returnUrl;
	}
	
	/**
	 * 상품 리뷰 수정 팝업
	 * @param request
	 * @param model
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/editReview.popup" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String editReview(HttpServletRequest request, Model model, MemberOrdGodFoDTO dto) throws Exception{
		String returnUrl = "tiles:mypage/review/popup/goods.review.edit";					
		log.info("MemberOrdGodFoDTO : {}",dto);
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		SecurityUserDetail userDetail = null;
		if(ContextService.hasRole()){
			userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr loginMbr = userDetail.getMbr();
			dto.setMbr(loginMbr);
		}		
		
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		
		MemberOrdGodFoResult result;
		result = memberActivitySelectComponent.selectMyGoodsReview(systemPK, dto);
		if(result == null){
			throw new Exception("접근 권한이 없습니다.");
		}
		
		result.setPhotoYn(dto.getPhotoYn());
		model.addAttribute("result",result);
		
		return returnUrl;
	}
	
	/**
	 * 상품리뷰 등록
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/addReviewAction.json", produces = { "application/json" } , method = RequestMethod.POST)
	public void addReviewAction(MultipartHttpServletRequest request, Model model, MemberOrdGodFoDTO dto, @RequestParam(value = "files", required = false) MultipartFile[]  files) throws Exception{
		log.info("files : {}",files.length);
		log.info("MemberOrdGodFoDTO : {}",dto);
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		SecurityUserDetail userDetail = null;
		if(ContextService.hasRole()){
			userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr loginMbr = userDetail.getMbr();
			dto.setMbr(loginMbr);
		}		

		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());

		dto.getGodGodEvl().setMbrNo(dto.getMbr().getMbrNo());
		List<MultipartFile> multipartFiles = null;
		if(files !=null &&  files.length > 0){
			multipartFiles = Arrays.asList(files);
		}

		MemberOrdGodFoResult result;
		
		OrdGod ordGod = new OrdGod();
		ordGod.setOrdNo(dto.getGodGodEvl().getOrdNo());
		ordGod.setGodNo(dto.getGodGodEvl().getGodNo());
		ordGod.setOrdGodTurn(dto.getGodGodEvl().getOrdGodTurn());
		dto.setOrdGod(ordGod);
		
		result = memberActivitySelectComponent.selectMyGoodsNoReview(systemPK, dto);
		if(result == null){
			throw new Exception("접근 권한이 없습니다.");
		}
		memberActivitySelectComponent.insertMyGoodsReview(systemPK, dto, multipartFiles);		
	}	
	
	/**
	 * 상품리뷰 등록
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/editReviewAction.json", produces = { "application/json" } , method = RequestMethod.POST)
	public void editReviewAction(MultipartHttpServletRequest request, Model model, MemberOrdGodFoDTO dto, @RequestParam(value = "files", required = false) MultipartFile[]  files) throws Exception{
		log.info("files : {}",files.length);
		log.info("MemberOrdGodFoDTO : {}",dto);
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		SecurityUserDetail userDetail = null;
		if(ContextService.hasRole()){
			userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr loginMbr = userDetail.getMbr();
			dto.setMbr(loginMbr);
		}		
		
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		
		dto.getGodGodEvl().setMbrNo(dto.getMbr().getMbrNo());
		List<MultipartFile> multipartFiles = null;
		if(files !=null &&  files.length > 0){
			multipartFiles = Arrays.asList(files);
		}
		
		MemberOrdGodFoResult result;
		
		OrdGod ordGod = new OrdGod();
		ordGod.setOrdNo(dto.getGodGodEvl().getOrdNo());
		ordGod.setGodNo(dto.getGodGodEvl().getGodNo());
		ordGod.setOrdGodTurn(dto.getGodGodEvl().getOrdGodTurn());
		dto.setOrdGod(ordGod);
		
		result = memberActivitySelectComponent.selectMyGoodsReview(systemPK, dto);
		if(result == null){
			throw new Exception("접근 권한이 없습니다.");
		}
		memberActivitySelectComponent.updateMyGoodsReview(systemPK, dto, multipartFiles);
			
	}	
	
	/**
	 * 상품리뷰 삭제
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value="/deleteGoodsReview.json", produces = { "application/json" } , method = RequestMethod.POST)
	public void deleteGoodsReview(HttpServletRequest request, Model model, MemberOrdGodFoDTO dto) throws Exception{
		log.info("MemberOrdGodFoDTO : {}",dto);
		SecurityUserDetail userDetail = null;
		if(ContextService.hasRole()){
			userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr loginMbr = userDetail.getMbr();
			dto.setMbr(loginMbr);
		}
		memberActivitySelectComponent.deleteMyGoodsReviewNtceYn(dto);
	}	
	
}
