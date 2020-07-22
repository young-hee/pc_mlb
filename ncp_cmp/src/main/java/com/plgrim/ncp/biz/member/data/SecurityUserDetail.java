package com.plgrim.ncp.biz.member.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;

/**
 * 사용자 상세 정보.
 */
@Data
public class SecurityUserDetail implements UserDetails {

    /**
	 * UID
	 */
    private static final long serialVersionUID = 6799290660745191205L;


    boolean isUnLock = true;
    
	/* 회원 엔티티 */
    Mbr mbr;
    
    /* 회원 인증 엔티티 */
    MbrCrtfc mbrCrtfc;

    /* 기타 파라미터 맵*/
    Map<String, String> parameterMap;
    
    /* 기타 파라미터 맵*/
    Map<String, Object> parameterObjectMap;
    
    /* 권한 파라미터 리스트*/
    List<GrantedAuthority> grantedAuths;
    
//    String passwordType = "ERP";

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	    // TODO Auto-generated method stub
	    return grantedAuths;
    }

	@Override
    public String getPassword() {
	    // TODO Auto-generated method stub
		if (mbr != null ){
			return mbr.getMbrErpPw();
//			if(passwordType != null && "ERP".equals(passwordType)) {
//				this.setPasswordType(null);
//				return mbr.getMbrErpPw();
//			}
//			else {
//				return mbr.getMbrPw();
//			}
		} else {
			return "";
		}
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
	    //return encoder.encode("1111");
    }

	@Override
    public String getUsername() {
	    // TODO Auto-generated method stub
	    return mbr.getMbrId();
    }

	@Override
    public boolean isAccountNonExpired() {
	    // TODO Auto-generated method stub
	    return true;
    }

	@Override
    public boolean isAccountNonLocked() {
	    // TODO Auto-generated method stub
		return isUnLock;
    }

	@Override
    public boolean isCredentialsNonExpired() {
	    // TODO Auto-generated method stub
	    return true;
    }

	@Override
    public boolean isEnabled() {
	    // TODO Auto-generated method stub
	    return true;
    }

//	public void setPasswordType(String type) {
//		this.passwordType = type;
//	}
}

