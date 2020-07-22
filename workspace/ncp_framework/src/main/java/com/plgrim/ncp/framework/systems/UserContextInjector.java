package com.plgrim.ncp.framework.systems;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 현재 사용중인 사용자의 주요 ID 를 반환한다.
 * 에서는 mbrNo 를 반환하고 BO, PO 에서는 adminId 를 반환한다 Batch 에서는 "batch" 를 반환한다 IF 에서는
 * "IF" 를 반환한다
 * 
 * @author narusas
 *
 */
public interface UserContextInjector {
	String getCurrentUserId();
	String getCurrentUserId(HttpServletRequest request, Object principal);

	String getCurrentUserType(HttpServletRequest request, Object principal);

	UserContextInfo getCurrentUser();

	Integer getCurrentUserPurchaseAmountHistory(String queryFrom, String queryTo);

	Integer getCurrentUserPurchaseCountHistory(String queryFrom, String queryTo);

	public List<Map<String, String>> getCurrentUserAuthMallList();
}
