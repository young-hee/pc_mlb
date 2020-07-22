package com.plgrim.ncp.biz.member.repository;

import java.util.Date;
import java.util.Map;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginCookieLog;

@Repository
public class MemberCustomTokenRepository extends AbstractRepository implements PersistentTokenRepository{

	@Override
    public void createNewToken(PersistentRememberMeToken token) {
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setCookieId(token.getSeries());
		cookie.setMbrNo(token.getUsername());
		cookie.setToknId(token.getTokenValue());
		
		getSession1().insert("com.plgrim.ncp.biz.mbr.token.createNewToken", cookie);
	    
    }

	@Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setCookieId(series);
		cookie.setToknId(tokenValue);
		//cookie.setLastLoginDt(lastUsed);
		
		getSession1().update("com.plgrim.ncp.biz.mbr.token.updateToken", cookie);
	    
    }

	@Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setCookieId(seriesId);
		PersistentRememberMeToken token = null;
		Map<String,Object> result = getSession1().selectOne("com.plgrim.ncp.biz.mbr.token.selectTokenForSeries", cookie);
		if(result != null){
			token = new PersistentRememberMeToken(result.get("USERNAME").toString(),result.get("SERIES").toString(),result.get("TOKEN").toString(),(Date)result.get("LAST_USED"));
		}
		return token;
		
		
    }

	@Override
    public void removeUserTokens(String username) {
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setMbrNo(username);
		getSession1().delete("com.plgrim.ncp.biz.mbr.token.removeUserTokens", cookie);
	    
    }


}
