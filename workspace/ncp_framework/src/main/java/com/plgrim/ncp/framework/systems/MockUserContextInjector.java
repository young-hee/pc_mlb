package com.plgrim.ncp.framework.systems;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MockUserContextInjector implements UserContextInjector {
	String id;
	String userType;
	UserContextInfo info;

	@Override
	public String getCurrentUserId(HttpServletRequest request, Object principal) {
		return id;
	}
	
	@Override
	public String getCurrentUserId() {
		return id;
	}

	@Override
	public String getCurrentUserType(HttpServletRequest request, Object principal) {
		return userType;
	}

	@Override
	public UserContextInfo getCurrentUser() {
		return info;
	}

	@Override
	public Integer getCurrentUserPurchaseAmountHistory(String queryFrom, String queryTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCurrentUserPurchaseCountHistory(String queryFrom, String queryTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> getCurrentUserAuthMallList() {
		// TODO Auto-generated method stub
		return null;
	}

}