/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.base.entities.datasource1.auth;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=false)
//@Alias("member")
public class Member extends AbstractEntity implements UserDetails {
	
	/** 사용자 아이디  */
	String userId;
	
	/** 사용자 이름 */
	String userName;
	

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	    return null;
    }

	@Override
    public String getPassword() {
	    return null;
    }

	@Override
    public String getUsername() {
	    return null;
    }

	@Override
    public boolean isAccountNonExpired() {
	    return false;
    }

	@Override
    public boolean isAccountNonLocked() {
	    return false;
    }

	@Override
    public boolean isCredentialsNonExpired() {
	    return false;
    }

	@Override
    public boolean isEnabled() {
	    return false;
    }
	
}
