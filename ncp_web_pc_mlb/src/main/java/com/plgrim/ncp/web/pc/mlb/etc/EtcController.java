package com.plgrim.ncp.web.pc.mlb.etc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.framework.data.SystemPK;

@Controller
@RequestMapping(value = "/etc")
public class EtcController extends AbstractController {
	
	@RequestMapping(value = {"/sns/agreement/view"})
	public String snsAgreement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		if("KOR".equals(pk.getLang())) {
			
			return "tiles:etc/sns.agreement.kor";
		
		} else if("CHI".equals(pk.getLang())) {
			
			return "tiles:etc/sns.agreement.chi";
			
		} else {
			
			return "tiles:etc/sns.agreement.eng";
			
		}
		
	}
	
}