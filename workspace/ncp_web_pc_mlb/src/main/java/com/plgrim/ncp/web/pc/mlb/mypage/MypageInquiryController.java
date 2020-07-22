package com.plgrim.ncp.web.pc.mlb.mypage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.exception.MemberException;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.CommonSelectComponentImpl;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/inquiry")
public class MypageInquiryController extends MypageController {

	@Autowired
	CommonSelectComponent commonSelectComponent;

	@Autowired
	CommonSelectComponentImpl commonSelectComponentImpl;

	@Autowired
	DisplaySelectComponent displaySelectComponent;

	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;

	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;

	@Autowired
	IdGenService idGenService;

	/**1:1 문의*/
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String selectMyInquiry(Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();

		Map<String, String> location = new HashMap<String, String>();

		location.put("url", null);
        location.put("msgKey", "mypage.submain.activeinfo.ttl");
        locationSet.add(location);

        location = new HashMap<String, String>();

        location.put("url", "/mypage/inquiry/list");
        location.put("msgKey", "mypage.inquiry.ttl1");
        locationSet.add(location);

        model.addAttribute("locationSet", locationSet);

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {
			model.addAttribute("dspCtgryScFrDTO", (DspCtgryScFrDTO) flashMap.get("dspCtgryScFrDTO"));
		}

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/inquiry/inquiry.list";
	}


	/**
	 * 1:1 문의 리스트
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/include/listInquiry.ajax")
	public String selectMyInquiryList(HttpServletRequest request, MypageFoDTO mypageFoDTO ,
			@RequestParam(value="pageNo"      , required = false) String pageNo,
			@RequestParam(value="mypageMainYn", required = false) String mypageMainYn, Model model ) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		if (StringService.isEmpty(pageNo)) {
			pageNo = "1";
		}

		PageParam pageParam = null;
		// 노출 갯수 조정
		if( "Y".equals(mypageMainYn) ) {
			pageParam = getPageService().buildPageParam(pageNo, 5);
		}else {
			pageParam = getPageService().buildPageParam(pageNo,10);
		}

		MypageMtmFoDTO mypageMtmFoDTO = new MypageMtmFoDTO();
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			String langCd = pk.getLang();
			mypageMtmFoDTO.setLangCd(langCd);
			model.addAttribute("userDetail", userDetail);

			Page<MypageMtmFoResult> lists = memberActivityFOComponent.selectMyInquiryList(pk, mypageMtmFoDTO, pageParam);

			model.addAttribute("inquiryList", lists.getContent());
			model.addAttribute("mypageMainYn", mypageMainYn);

			PageService.makePageResult(lists, model);

		}

		return "tiles:mypage/inquiry/include/inquiry.list.include";
	}



	/**1:1 문의 리스트 상세 페이지*/
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/detail/{srchMtmInqSn}", method = {RequestMethod.GET,RequestMethod.POST})
	public String selectMyInquiryListDetail(@RequestParam(value ="pageNo", required = false) String pageNo,
											@PathVariable String srchMtmInqSn,
											Model model, HttpServletRequest request	,@ModelAttribute MypageMtmFoDTO mypageMtmFoDTO, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();

		Map<String, String> location = new HashMap<String, String>();

		location.put("url", null);
        location.put("msgKey", "mypage.submain.activeinfo.ttl");
        locationSet.add(location);

        location = new HashMap<String, String>();

        location.put("url", "/mypage/inquiry/list");
        location.put("msgKey", "mypage.inquiry.ttl1");
        locationSet.add(location);

        model.addAttribute("locationSet", locationSet);

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			mypageMtmFoDTO.setSrchMtmInqSn(srchMtmInqSn);

			long inquiryListCount = memberActivityFOComponent.selectMyInquiryListMobileCntWithMall(pk, mypageMtmFoDTO);
			if(inquiryListCount == 0) {
				log.info(">>>>>>>>>>>>>>>>>>>>>>> mbrNo["+userDetail.getMbr().getMbrNo()+"] srchMtmInqSn["+srchMtmInqSn+"]");
				String[] message = {"접근 권한이 없습니다."};
				throw new MemberException(message);
			}

			model.addAttribute("totalCnt", mypageMtmFoDTO.getTotalRow());
			model.addAttribute("srchMtmInqSn", srchMtmInqSn);
			model.addAttribute("userDetail", userDetail);
			model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
			List<MypageMtmFoResult> inquiryList = memberActivityFOComponent.selectMyInquiryListMobileWithMall(pk, mypageMtmFoDTO);
			List<MypageMtmFoResult> inquiryDetail = memberActivityFOComponent.selectCsInquiryDetail(pk, mypageMtmFoDTO);
			List<MypageMtmFoResult> inquiryFileList = memberActivityFOComponent.selectMyInquiryFileListWithMall(pk, mypageMtmFoDTO);
			List<MypageMtmFoResult> inquiryOrdGodList = memberActivityFOComponent.selectMyInquiryOrdGodList(pk, mypageMtmFoDTO);
			if(inquiryList.size() == 0 && inquiryDetail.size() == 0 && inquiryOrdGodList.size() == 0){
				log.info("inquiry exception >>> ");
				throw new MemberException(null);
			}else{
				model.addAttribute("inquiryList", inquiryList);
				model.addAttribute("inquiryDetail", inquiryDetail);
				model.addAttribute("inquiryFileList", inquiryFileList);
				model.addAttribute("inquiryOrdGodList", inquiryOrdGodList);

			}
		}

		return "tiles:mypage/inquiry/inquiry.detail";
	}

	/** 1:1 상담 수정화면 조회 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/updateMyInquiryList", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateMyInquiryList(@ModelAttribute MypageMtmFoDTO mypageMtmFoDTO,
									  Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();

		Map<String, String> location = new HashMap<String, String>();

		location.put("url", null);
        location.put("msgKey", "mypage.submain.activeinfo.ttl");
        locationSet.add(location);

        location = new HashMap<String, String>();

        location.put("url", "/mypage/inquiry/list");
        location.put("msgKey", "mypage.inquiry.ttl1");
        locationSet.add(location);

        model.addAttribute("locationSet", locationSet);


		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());

			model.addAttribute("srchMtmInqSn", mypageMtmFoDTO.getSrchMtmInqSn());
			model.addAttribute("userDetail", userDetail);
			model.addAttribute("inquiryList", memberActivityFOComponent.selectMyInquiryListMobileWithMall(pk, mypageMtmFoDTO));
			model.addAttribute("inquiryFileList", memberActivityFOComponent.selectMyInquiryFileListWithMall(pk, mypageMtmFoDTO));
			model.addAttribute("inquiryOrdGodList", memberActivityFOComponent.selectMyInquiryOrdGodList(pk, mypageMtmFoDTO));
			mypageMtmFoDTO.setChkDay("60");
			model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
			//model.addAttribute("memberOrd", memberOrderSelectComponent.selectOrdGodList(pk, mypageMtmFoDTO));
		}

		return "tiles:mypage/inquiry/inquiry.modify";
	}


	/**
	 * 1:1 문의 수정
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/updateMyInquiry", method = {RequestMethod.POST,RequestMethod.GET})
	public String updateMyInquiry(@ModelAttribute MypageMtmFoDTO mypageMtmFoDTO, @RequestParam("files") MultipartFile[] files,
		  Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		String chkEmail = mypageMtmFoDTO.getCsoMtmInq().getCstmrEmailRecptnYn();
		String chkSms = mypageMtmFoDTO.getCsoMtmInq().getCstmrSmsRecptnYn();

		if(chkEmail == "" || chkEmail == null){
			mypageMtmFoDTO.getCsoMtmInq().setCstmrEmailRecptnYn("N");
		}
		if(chkSms == "" || chkSms == null){
			mypageMtmFoDTO.getCsoMtmInq().setCstmrSmsRecptnYn("N");
		}
		String mobil = mypageMtmFoDTO.getMobilNo();

		if(StringService.isNotEmpty(mobil)){
			mypageMtmFoDTO.getCsoMtmInq().setCstmrmobilAreaNo(mobil.substring(0,3));
			mypageMtmFoDTO.getCsoMtmInq().setCstmrmobilTlofNo(mobil.substring(3,mobil.length()-4));
			mypageMtmFoDTO.getCsoMtmInq().setCstmrmobilTlofWthnNo(mobil.substring(mobil.length()-4));
		}

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());
		}

		try {
			//수정 이전에 자신의 1:1 문의 건인지 확인 로직 추가
			long inquiryListCount = memberActivityFOComponent.selectMyInquiryListCount(mypageMtmFoDTO);
			if(inquiryListCount == 0) {
				String[] message = {"수정 권한이 없습니다. ", "===> MbrNo : " + mypageMtmFoDTO.getMbrNo() + ", SrchMtmInqSn : " + mypageMtmFoDTO.getSrchMtmInqSn()};
				throw new MemberException(message);
			} else {
				List<MultipartFile> multipartFiles = null;
				if(files !=null &&  files.length > 0){
					multipartFiles = Arrays.asList(files);
				}
				memberActivityFOComponent.updateMyInquiry(pk, mypageMtmFoDTO, multipartFiles);
			}
		}
		catch (Exception e) {
			log.error("",e);
		}

		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);

		return "redirect:/mypage/inquiry/list";

	}

	/** 1:1 문의 수정 new */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/updateMypageInquiry", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateMypageInquiry(MypageMtmFoDTO mypageMtmFoDTO, Model model, HttpServletRequest request)
			throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		mypageMtmFoDTO.setMallId(pk.getMall());
		mypageMtmFoDTO.setDvcCd(pk.getDevice());
		mypageMtmFoDTO.setLangCd(pk.getLang());

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		mypageMtmFoDTO.setMbr(userDetail.getMbr());
		mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
		mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());


		List<OrdGod> ordGodList = new ArrayList<>();
		ordGodList.add(mypageMtmFoDTO.getOrdGod());
		mypageMtmFoDTO.setOrdGodList(ordGodList);


		String chkSms = mypageMtmFoDTO.getCsoMtmInq().getCstmrSmsRecptnYn();

		if (StringUtils.isEmpty(chkSms)) {
			mypageMtmFoDTO.getCsoMtmInq().setCstmrSmsRecptnYn("N");
			mypageMtmFoDTO.getCsoMtmInq().setCstmrEmailRecptnYn("N");
		} else {
			mypageMtmFoDTO.getCsoMtmInq().setCstmrEmailRecptnYn("Y");
		}

		String srchMtmInqSn = mypageMtmFoDTO.getSrchMtmInqSn();

		try {


			memberActivityFOComponent.updateMypageInquiry(pk, mypageMtmFoDTO);



		} catch (Exception e) {
			log.error("", e);
		}

		return "redirect:/mypage/inquiry/list";
	}

	/**
	 * 1:1 문의 삭제
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/deleteMyInquiry", method = RequestMethod.POST)
	public String deleteMyInquiry(@ModelAttribute MypageMtmFoDTO mypageMtmFoDTO,
								  Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());
		}

		try {
			//삭제 이전에 자신의 1:1 문의 건인지 확인 로직 추가
			long inquiryListCount = memberActivityFOComponent.selectMyInquiryListCount(pk, mypageMtmFoDTO);
			if(inquiryListCount == 0) {
				String[] message = {"삭제 권한이 없습니다. ", "===> MbrNo : " + mypageMtmFoDTO.getMbrNo() + ", SrchMtmInqSn : " + mypageMtmFoDTO.getSrchMtmInqSn()};
				throw new MemberException(message);
			} else {
				memberActivityFOComponent.deleteMyInquiry(pk, mypageMtmFoDTO);
			}
		}
		catch (Exception e) {
			log.error("",e);
		}

		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);

		return "redirect:/mypage/inquiry/list";

	}
}
