package com.plgrim.ncp.cmp.product.fo.price;

import java.util.Date;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.enums.DisplayEnum.PrcSectCd;
import com.plgrim.ncp.base.enums.MemberEnum.MemberAtrbCd;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.StringService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Plgrim
 *
 */
@Slf4j
@Component
public class GoodsPriceFOComponentImpl extends AbstractComponent implements GoodsPriceFOComponent {
	
	
	@Override
	public GoodsPriceSearchDTO getMemberTypeForPriceForPC() {

		String memberAttr="";
		
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			memberAttr = userDetail.getMbr().getMbrAtrbCd();
		}
		
		// b2e인증키 조회
		String b2eKey = (String) ContextService.getCurrentRequest().getSession().getAttribute("B2EKEY");

		if (StringUtils.isEmpty(b2eKey)) {
            try {
				b2eKey = getB2EKeyFromCookie();
            }
            catch ( Exception e) {
                log.error("",e);
            }
		}
		
		String prcSectCd = PrcSectCd.GNRL.toString();
		String spcPrmTp = null;
		
		GoodsPriceSearchDTO searchDTO = new GoodsPriceSearchDTO();
		
		if (StringUtils.isNotEmpty(b2eKey)) {
			spcPrmTp = b2eKey;// b2e
		}

		if (MemberAtrbCd.GNRL_MBR.toString().equals(memberAttr)) {
			prcSectCd = PrcSectCd.GNRL.toString();
		}
		else if (MemberAtrbCd.EMP.toString().equals(memberAttr)) {
			prcSectCd = PrcSectCd.EMP.toString();
		}

		searchDTO.setSpcPrmTp(spcPrmTp);
		searchDTO.setPrcSectCd(prcSectCd);
		return searchDTO;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsPriceInfoSelectComponent#getMemberTypeForPrice()
	 */
	@Deprecated
	@Override
	public String getMemberTypeForPrice() {

		String memberAttr="";
		boolean ilmoYn = false;// 임직원일 경우 일모보유여부
		boolean ilmoApplyYn = false; // 일모 적용 여부
		
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			memberAttr = userDetail.getMbr().getMbrAtrbCd();

		}
		
		// b2e인증키 조회
		String b2eKey = (String) ContextService.getCurrentRequest().getSession().getAttribute("B2EKEY");
		if (StringUtils.isEmpty(b2eKey)) {
            try {
                b2eKey = getB2EKeyFromCookie();
            }
            catch ( Exception e) {
                log.error("",e);
            }
		}
		
		String prcSectCd = PrcSectCd.GNRL.toString();

		if (MemberAtrbCd.GNRL_MBR.toString().equals(memberAttr)) {
			prcSectCd = PrcSectCd.GNRL.toString();
		}
		else if (MemberAtrbCd.EMP.toString().equals(memberAttr)) {
			prcSectCd = PrcSectCd.EMP.toString();
			if (ilmoYn && ilmoApplyYn) {
				prcSectCd = PrcSectCd.ILMO.toString();
			}
		}

		return prcSectCd;
	}

	@Override
	public String getMemberTypeForPriceForMobile() {

		String memberAttr="";
		
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			memberAttr = userDetail.getMbr().getMbrAtrbCd();
		}
		
		// b2e인증키 조회
		String b2eKey = (String) ContextService.getCurrentRequest().getSession().getAttribute("B2EKEY");
		if (StringUtils.isEmpty(b2eKey)) {
            try {
                b2eKey = getB2EKeyFromCookie();
            }
            catch ( Exception e) {
                log.error("",e);
            }
		}
		
		String prcSectCd = PrcSectCd.GNRL.toString();

		if (StringUtils.isNotEmpty(b2eKey)) {
			prcSectCd = b2eKey;//b2e
		}
		else if (MemberAtrbCd.GNRL_MBR.toString().equals(memberAttr)) {
			prcSectCd = PrcSectCd.GNRL.toString();
		}
		else if (MemberAtrbCd.EMP.toString().equals(memberAttr)) {
			prcSectCd = PrcSectCd.EMP.toString();
		}

		return prcSectCd;
	}
	
	/**
	 * 쿠키로 부터 b2e key 조회
	 * 
	 * @return
	 */
	private String getB2EKeyFromCookie() {
		try {
			if (ContextService.getCurrentRequest().getCookies() != null) {
				Cookie cookies[] = ContextService.getCurrentRequest().getCookies();
				if (ArrayUtils.isNotEmpty(cookies)) {
					for (int i = 0; i < cookies.length; i++) {
						if (StringService.equalsIgnoreCase(cookies[i].getName(), "_B2E_")) {
							String value = java.net.URLDecoder.decode(cookies[i].getValue(), "UTF-8");
	
							JSONObject json = new JSONObject(value);
	
							String duedate = json.getString("duedate");
							String todate = DateService.parseString(new Date(), "yyyyMMdd");
	
							if (Integer.parseInt(duedate) >= Integer.parseInt(todate)) {
								return json.getString("key");
							} else {
	
								// // 쿠키 삭제
								// Cookie kc = new Cookie("_B2E_", null) ;
								// kc.setMaxAge(0) ;
								// kc.setPath("/");
								// response.addCookie(kc);
								// return null;
							}
						}
					}
				}
			}
			return null;
		}catch(Exception e) {
			throw new RuntimeException();
		}
	}

}
