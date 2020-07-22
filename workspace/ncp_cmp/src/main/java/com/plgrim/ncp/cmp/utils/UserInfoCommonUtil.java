package com.plgrim.ncp.cmp.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.commons.auth.BOSecurityUserDetail;
import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.systems.ApplicationType;

/**
 * BO/FO가 함께 사용하는 공통 컴포넌트에서 접속한 사용자 정보를 얻기 위한 유틸 클래스.
 * 
 * FO 로그인 객체가 컴포넌트에 위치해 있어서 컴포넌트 프로젝트에 위치시킴.
 * 
 * @author seed
 *
 */
public class UserInfoCommonUtil {
	
	private static String FO_NON_MEMBER_ID = "GUEST";
	
	/**
	 * 로그인 ID 조회 (RegtrId, UdterId를 위해 사용)
	 * 
	 * @param appType
	 * @return
	 */
	public static String getLoginId(ApplicationType appType) {
		try {
			switch(appType) {
				case BACK_OFFICE :
					return getBoLoginId();
				default:
					return getFoLoginId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String getBoLoginId() {
		if (hasRole() == false) {
			return "";
		}
		
		BOSecurityUserDetail userDetail = (BOSecurityUserDetail) getCurrentUserDetail();
		
		if (userDetail == null) {
			return "";
		}
		
		if (userDetail.getSysAdmin() == null) {
			return "";
		}
		
		return userDetail.getSysAdmin().getAdminId();
	}
	
	private static String getPoLoginId() {
		if (hasRole() == false) {
			return "";
		}
		
		BOSecurityUserDetail userDetail = (BOSecurityUserDetail) getCurrentUserDetail();
		
		if (userDetail == null) {
			return "";
		}
		
		if (userDetail.getPoSysAdmin() == null) {
			return "";
		}
		
		return userDetail.getPoSysAdmin().getAdminId();
	}
	
	private static String getFoLoginId() {
		
		if (hasRole() == false) {
			return FO_NON_MEMBER_ID;
		}
		
		SecurityUserDetail userDetail = (SecurityUserDetail) getCurrentUserDetail();

		if (userDetail == null) {
			return "";
		}
		
		if (userDetail.getMbr() == null) {
			return "";
		}
		
		return userDetail.getMbr().getMbrNo();
	}

	private static Object getCurrentUserDetail(){
		SecurityContext context = SecurityContextHolder.getContext();
		
		if (context == null) {
			return null;
		}
		
		return context.getAuthentication().getPrincipal();
	}
	
	@SuppressWarnings("unchecked")
	public static boolean hasRole() {
		
		if (SecurityContextHolder.getContext() == null) {
			return false;
		}
		
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return false;
		}
		
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities() == null) {
			return false;
		}
		
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		for (GrantedAuthority authority : authorities) {
	        if (authority.getAuthority().equals(SecurityParam.DEFAULT_ROLE.toString()) == true) {
	            return true;
	        } 
	        
	        if (authority.getAuthority().equals(SecurityParam.ADMIN_ROLE.toString()) == true) {
	        	return true;
	        }
	    }
	    	
    	return false;
    }
	
	
}
