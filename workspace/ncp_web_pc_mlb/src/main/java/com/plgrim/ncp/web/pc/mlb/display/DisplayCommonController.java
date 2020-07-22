package com.plgrim.ncp.web.pc.mlb.display;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.enums.BskEnum.MbrSect;
import com.plgrim.ncp.biz.display.data.DspCtgryDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class DisplayCommonController extends AbstractController{
	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;
    public void defaultSetting(Object obj, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
        DspCtgryDTO dspCtgryDTO = new DspCtgryDTO();
        dspCtgryDTO.setMallId(pk.getMall());

        String device = pk.getDevice();
        if (obj instanceof DspCtgryScFrDTO) {
            String value = ((DspCtgryScFrDTO) obj).getDevice();
            if (StringService.isNotEmpty(value)) {
                device = value;
            }
        }

        dspCtgryDTO.setDevice(device);
        dspCtgryDTO.setLang(pk.getLang());
        GoodsPriceSearchDTO goodsPriceSearchDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
        dspCtgryDTO.setPrcSectCd(goodsPriceSearchDTO.getPrcSectCd());
        if (ContextService.hasRole()) {
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			dspCtgryDTO.setTgtMbrTpCd(userDetail.getMbr().getMbrTpCd());
			dspCtgryDTO.setAbsMbrAtrbCd(userDetail.getMbr().getMbrAtrbCd());  
        }else {
        	dspCtgryDTO.setTgtMbrTpCd(MbrSect.NMBR.toString());
        }
 
		
    	BeanUtils.copyProperties(dspCtgryDTO, obj);
    	
  }
	
}
