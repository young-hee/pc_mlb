package com.plgrim.ncp.commons.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.commons.repository.MemberOnlinePurchaseHistoryRepository;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.systems.ResidenceArea;
import com.plgrim.ncp.framework.systems.UserContextInfo;
import com.plgrim.ncp.framework.systems.UserContextInjector;
import com.plgrim.ncp.framework.systems.UserGender;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * ncp_base의 Mbr을 포함하는 USerDetail에 대해 처리 할수 있는 USerContextInjecto
 * 
 * @author narusas
 *
 */
@Slf4j
public class MbrUserContextInjector implements UserContextInjector {
	@Autowired
	MemberOnlinePurchaseHistoryRepository memberOnlinePurchaseHistoryRepository;

	@Override
	public String getCurrentUserId(HttpServletRequest request, Object principal) {
		Mbr mbr = getMbr(principal);
		return mbr == null ? null : mbr.getMbrNo();
	}
	
	@Override
	public String getCurrentUserId() {
		return getCurrentUserId(null, ContextService.getCurrentUserDetail());
	}


	@Override
	public String getCurrentUserType(HttpServletRequest request, Object principal) {
		Mbr mbr = getMbr(principal);
		return mbr == null ? null : mbr.getMbrTpCd();
	}

	private Mbr getMbr(Object principal) {
		try {
			if (principal == null || principal instanceof String) {
				return null;
			}
			return (Mbr) PropertyUtils.getProperty(principal, "mbr");
		} catch (Exception e) {
			log.warn("", e);
			return null;
		}
	}

	@Override
	public UserContextInfo getCurrentUser() {
		Object principal = ContextService.getCurrentUserDetail();
		UserContextInfo info = new UserContextInfo();
		Mbr mbr = getMbr(principal);
		if (mbr == null) {
			return null;
		}
		parseBirthYear(info, mbr);
		parseUserGender(info, mbr);
		parseResidenceArea(info, mbr);
		// fetchPurchase(info, mbr);

		return info;
	}

	private void parseBirthYear(UserContextInfo info, Mbr mbr) {
		String birthday = mbr.getMbrBrthdy();
		if (StringUtils.isNotEmpty(birthday)) {
			info.setBirthYear(Integer.parseInt(birthday.substring(0, 4)));
		}
	}

	private void parseUserGender(UserContextInfo info, Mbr mbr) {
		String genderStr = mbr.getMbrSexSectCd();
		if (genderStr == null) {
			return;
		}
		switch (genderStr.toUpperCase()) {
		case "MALE":
			info.setGender(UserGender.MALE);
			break;
		case "FEMALE":
			info.setGender(UserGender.FEMALE);
			break;
		}
	}

	private void parseResidenceArea(UserContextInfo info, Mbr mbr) {
		String areaCode = mbr.getAudCd();
		log.debug("User Area code: {}", areaCode);
		if (org.apache.commons.lang3.StringUtils.isEmpty(areaCode)) {
			return;
		}

		String 행정동코드 = areaCode.substring(0, 2) + "000";
		ResidenceArea area = ResidenceArea.of(행정동코드);
		log.debug("Residence Area: {}", area);
		info.setArea(area);
	}

	private Integer getPurchaseAmount(Mbr mbr, String queryFrom, String queryTo) {
		HttpSession session = ContextService.getCurrentRequest().getSession();
		Integer purchaseAmount = (Integer) session.getAttribute("purchaseAmount");
		if (purchaseAmount == null) {
			purchaseAmount = memberOnlinePurchaseHistoryRepository.getMemberPurchaseAmount(mbr.getMbrNo(), queryFrom,
					queryTo);
			session.setAttribute("purchaseAmount", purchaseAmount);
		}
		return purchaseAmount;

	}

	private Integer getPurchaseCount(Mbr mbr, String queryFrom, String queryTo) {
		HttpSession session = ContextService.getCurrentRequest().getSession();
		Integer purchaseCount = (Integer) session.getAttribute("purchaseCount");
		if (purchaseCount == null) {
			purchaseCount = memberOnlinePurchaseHistoryRepository.getMemberPurchaseCount(mbr.getMbrNo(), queryFrom,
					queryTo);
			session.setAttribute("purchaseCount", purchaseCount);
		}
		return purchaseCount;
	}

	@Override
	public Integer getCurrentUserPurchaseAmountHistory(String queryFrom, String queryTo) {
		Object principal = ContextService.getCurrentUserDetail();
		Mbr mbr = getMbr(principal);
		if (mbr == null) {
			return null;
		}
		return getPurchaseAmount(mbr, queryFrom, queryTo);
	}

	@Override
	public Integer getCurrentUserPurchaseCountHistory(String queryFrom, String queryTo) {
		Object principal = ContextService.getCurrentUserDetail();
		Mbr mbr = getMbr(principal);
		if (mbr == null) {
			return null;
		}
		return getPurchaseCount(mbr, queryFrom, queryTo);
	}

	@Override
	public List<Map<String, String>> getCurrentUserAuthMallList() {
		// TODO Auto-generated method stub
		return null;
	}
}
