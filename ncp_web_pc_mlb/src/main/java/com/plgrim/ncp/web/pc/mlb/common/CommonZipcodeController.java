package com.plgrim.ncp.web.pc.mlb.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.data.ZipcodeDTO;
import com.plgrim.ncp.commons.result.ZipcodeResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/common")
public class CommonZipcodeController extends AbstractController{

	@Autowired
	CommonSelectComponent commonSelectComponent;
	
    /**
	 * 우편번호 
	 *
	 * @param request [설명]
	 * @param model [설명]
	 * @param dto [설명]
	 * @return String [설명]
	 */
	@RequestMapping("/zipcode/list") 
	public String getSearchZipcode(@RequestParam(value = "pageNo", required = false) String pageNo,
									Model model, ZipcodeDTO zipcodeDTO, HttpServletRequest request) throws Exception {
		String returnUrl = "tiles:common/layerpop/zipcode.list";	
		//zipcodeDTO.setGPageSize(999);
		try{
			
			if (StringService.isEmpty(pageNo)) {
				pageNo = "1";
			}
			
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			zipcodeDTO.setLang(systemPK.getLang());
			
			PageParam pageParam = getPageService().buildPageParam(pageNo, 100);
			
			Page<ZipcodeResult> zipcodeList = commonSelectComponent.getSearchZipcode(zipcodeDTO, pageParam);
			model.addAttribute("zipcodeList", zipcodeList.getContent());
			model.addAttribute("zipcode", zipcodeDTO);	
			PageService.makePageResult(zipcodeList, model);
		}catch(Exception e){
			// TODO Auto-generated catch block
			log.error("",e);
		}
			 	
		return returnUrl;
	}	

}
