package com.plgrim.ncp.web.pc.mlb;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageTdGodFoResult;
import com.plgrim.ncp.cmp.display.fo.DxmDisplaySelectComponent;
import com.plgrim.ncp.cmp.display.fo.MbmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

/**
 * Mbr에 대한 접근이 필요하기 때문에 ncp_cmp에 있음
 * @author narusas
 *
 */
@SuppressWarnings("unused")
@Slf4j
public class GNBInterceptor extends AbstractController implements HandlerInterceptor{
	
	@Autowired
    MbmDisplaySelectComponent mbmDisplaySelectComponent;
	
	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;
	
	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;
	
	@Autowired
	DxmDisplaySelectComponent dxmDisplaySelectComponent;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {		
		
		String reqUri = request.getRequestURI();
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
				
		//주문서나 장바구니 한국어가 아닐때 접근 막음
		if(reqUri.startsWith("/order")
				|| reqUri.startsWith("/cart")
				|| reqUri.startsWith("/member")
				|| reqUri.startsWith("/mypage")){
			
			if(!"KOR".equals(pk.getLang())){
				response.sendRedirect(request.getContextPath()+"/main/mall/view");
				return false;
			}
		}
		
		
		if (!"XMLHttpRequest".equals(request.getHeader("x-requested-with"))
				 && !reqUri.startsWith("/system/healthcheck")
				 && !reqUri.startsWith("/static")
				 && !reqUri.startsWith("/javascript")
				 && !reqUri.startsWith("/errors")
				 && !reqUri.startsWith("/email")){
			//ajax요청이 아닐때만 세팅 
			DspCtgry dspCtgry = new DspCtgry();
			
			dspCtgry.setUpperDspCtgryNo("MBM");
			dspCtgry.setLang(pk.getLang());
			Object ctgList = mbmDisplaySelectComponent.selectGNBCategotyList(dspCtgry);		
			request.setAttribute("GNBCategoryList", ctgList);
			//log.debug("====uri : " + request.getRequestURI());
			//log.debug("====ctgList : " + ctgList);			
			//log.debug("====requested : " + request.getHeader("x-requested-with"));
		
			DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
	    	dspCtgryScFrDTO.setMallId(pk.getMall());
	    	dspCtgryScFrDTO.setMallGubun(pk.getMall());
	    	dspCtgryScFrDTO.setDevice(pk.getDevice());
	    	dspCtgryScFrDTO.setLang(pk.getLang());
	    	
	    	String env  = getConfigService().getProperty("ncp_base.env");
	    	request.setAttribute("GNBCommonBanner", mbmDisplaySelectComponent.selectCommonBanner(dspCtgryScFrDTO, env));
	    	
	    	//오늘본상품	    	
	    	MypageFoDTO dto = new MypageFoDTO();
			dto.setMallId(pk.getMall());
			dto.setLang(pk.getLang());
			
			GoodsPriceSearchDTO priceDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();

			if(ContextService.hasRole()){
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				dto.setMbr(userDetail.getMbr());
				
				PageParam pageParam = getPageService().buildPageParam("1", 10);			
				dto.setSpcPrmTp(priceDTO.getSpcPrmTp());
				dto.setPrcSectCd(priceDTO.getPrcSectCd());
				Page<MypageTdGodFoResult> lists = memberActivityFOComponent.getMyTodayGodList(pk, dto, pageParam);
				
				request.setAttribute("GNBlistTdGod", lists.getContent());
			}else{
				Cookie cookies[] = ContextService.getCurrentRequest().getCookies();
				List<GoodsInterestsGodFoResult> result = new ArrayList<GoodsInterestsGodFoResult>();
				List<MbrTodayGod> skyScrapers = new ArrayList<MbrTodayGod>();
				if(cookies != null){
					for (int i = 0; i < cookies.length; i++) {
						Functions.cookies(cookies, skyScrapers, i);
					}
				}
				InterestSearchFoDTO interestSearchFoDTO =  new InterestSearchFoDTO();
				interestSearchFoDTO.setMallId(pk.getMall());
				interestSearchFoDTO.setLang(pk.getLang());
				interestSearchFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());			
				interestSearchFoDTO.setEndIndex(10);			
				interestSearchFoDTO.setSesionId(request.getSession().getId());
				if(skyScrapers != null && skyScrapers.size() > 0){
					interestSearchFoDTO.setSkyScrapers(skyScrapers);
					result = dxmDisplaySelectComponent.getTodayCookiesList(interestSearchFoDTO);					
				}				
				request.setAttribute("GNBlistTdGod", result);
			}
			
		}
		return true;
	}



	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	        throws Exception {
		// 필터처리까지 로그가 남을수 있기 때문에 context 는 Filter에서 지운다.		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
