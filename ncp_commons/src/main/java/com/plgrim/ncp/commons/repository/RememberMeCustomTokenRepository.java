package com.plgrim.ncp.commons.repository;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginCookieLog;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.LocaleService;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Repository
@Slf4j
public class RememberMeCustomTokenRepository extends AbstractRepository implements PersistentTokenRepository{
	private static final String REMEMBER_ME_COOKIE_NAME = "remember-me";
	private static final int TWO_WEEKS_S = 1209600; //1209600 = 2 weeks (14 days), 86400 = 1 day, 18000 = 5 hours.
	private static final boolean REMEMBER_ME_LOG_ENABLED = true;
	
	@Override
    public void createNewToken(PersistentRememberMeToken token) {
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setCookieId(token.getSeries());
		cookie.setMbrNo(token.getUsername());
		cookie.setToknId(token.getTokenValue());		
		getSession1().insert("com.plgrim.ncp.biz.mbr.token.createNewToken", cookie);	    
		
		this.logAppend("createNewToken", "Client Token create complete", token.getSeries(), token.getUsername(), token.getTokenValue());
    }

	@Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setCookieId(series);
		cookie.setToknId(tokenValue);
		//cookie.setLastLoginDt(lastUsed); //쿼리에서 SYSDATE로 변경함. => SYSDATE : 2015/09/10 오전 10:55:31
		
		String[] mbrArr = getMbrInfo(series);
		String mbrNo = "-";
		if(mbrArr != null){
			mbrNo = mbrArr[0];
			cookie.setMbrNo(mbrArr[0]);
		}
		
		this.logAppend("updateToken", series+"/"+tokenValue+"/"+lastUsed, series, mbrNo, tokenValue);
		
		try{
			getSession1().update("com.plgrim.ncp.biz.mbr.token.updateToken", cookie);	
		}catch(Exception e){
			log.error("",e);
		}
				
		this.logAppend("updateToken", "Client 토큰 변경(TOKEN, 최종접속일자) 완료", series, mbrNo, tokenValue);
    }

	@Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String cDate = "";
		Date dDate = new Date();
		String dateString = "";
		String debugStr = "";
				
		String[] mbrArr = getMbrInfo(seriesId);
		
		this.logAppend("getTokenForSeries", "-", seriesId, (mbrArr!=null?mbrArr[0]:"-"), (mbrArr!=null?mbrArr[2]:"-"));
		
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setCookieId(seriesId);
		PersistentRememberMeToken token = null;
		Map<String,Object> result = getSession1().selectOne("com.plgrim.ncp.biz.mbr.token.selectTokenForSeries", cookie);		
		if(result != null){
			cDate = (String)result.get("LAST_USED");
			try {
	            dDate = sdf.parse(cDate+"060");
            }catch (ParseException e) {
	            log.error("",e);
            }			
			java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyy년MM월dd일 HH시mm분ss초");
			dateString = format1.format(dDate);
			
			token = new PersistentRememberMeToken(result.get("USERNAME").toString(), result.get("SERIES").toString(), result.get("TOKEN").toString(), dDate);
			
			this.logAppend("getTokenForSeries", "조회 ok... :: "+cDate+"/"+dDate+"/"+dateString, seriesId, result.get("USERNAME").toString(), result.get("TOKEN").toString());
			
			boolean isValidToken = isValidToken(result.get("TOKEN").toString());
			if(!isValidToken){
				this.logAppend("getTokenForSeries", "토큰이 휴효하지 않습니다.", seriesId, result.get("USERNAME").toString(), result.get("TOKEN").toString());
				//removeUserAllCookies(result.get("USERNAME").toString());
			}
			
			debugStr = "DB:"+token.getDate().getTime() + TWO_WEEKS_S * 1000L+" <=> Now:"+System.currentTimeMillis();
			
			if (token.getDate().getTime() + TWO_WEEKS_S * 1000L < System.currentTimeMillis()) {				
				this.logAppend("getTokenForSeries", "expire 쿠키 date("+debugStr+")", seriesId, result.get("USERNAME").toString(), result.get("TOKEN").toString());
				removeUserTokens(result.get("USERNAME").toString());
			}else{
				this.logAppend("getTokenForSeries", "유효한 쿠키 date("+debugStr+")", seriesId, result.get("USERNAME").toString(), result.get("TOKEN").toString());
			}
			
		}else{			
			this.logAppend("getTokenForSeries", "쿠키id not found", seriesId, "N/A", (mbrArr!=null?mbrArr[2]:"-"));
		}
		
		return token;
    }

	@Override
    public void removeUserTokens(String username) {
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		String cnt = "O";	
		String cookieValue = getRememberMeCookieValue(REMEMBER_ME_COOKIE_NAME);
		
		this.logAppend("removeUserTokens", "[STEP 1]"+cookieValue, "-", username, "-");
		
		if(cookieValue != null && !"".equals(cookieValue)) {
		
			String[] decodeCookie = decodeCookie(cookieValue);
			
			this.logAppend("removeUserTokens", "[STEP 2]"+decodeCookie[0]+"/"+decodeCookie[1], "-", username, "-");
			
			cookie.setCookieId(decodeCookie[0]);
			Map<String,Object> result = getSession1().selectOne("com.plgrim.ncp.biz.mbr.token.countTokenForSeries", cookie);
			if(result != null){
				cnt = (String)result.get("CNT"); //0:미 발견, 1:발견
			}
			
			this.logAppend("removeUserTokens", "[STEP 3] cnt :: "+cnt, "-", username, "-");
				
			if(cnt.equals("0")){ //User's row all delete	 => not delete
				cookie.setMbrNo(username);
				//getSession1().delete("com.plgrim.ncp.biz.mbr.token.removeUserTokens", cookie);								
				this.logAppend("removeUserTokens", "[theft attack] all row delete => not delete (aready deleted)", decodeCookie[0], username, decodeCookie[1]);
				
			}else{ //logout user 1 row delete
				cookie.setCookieId(decodeCookie[0] );				
				getSession1().delete("com.plgrim.ncp.biz.mbr.token.removeSeriesTokens", cookie);
				
				this.logAppend("removeUserTokens", "[logout] 1 row delete", cookie.getCookieId(), username, decodeCookie[1]);				
			}
			
		}else{	
			this.logAppend("removeUserTokens", "cookieValue is empty ? -> only session killed", "-", username, "-");
			
		}
    }
	
	public void removeUserAllCookies(String username) {
		//System.out.println("Peter : removeUserAllCookies...");
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setMbrNo(username);
		getSession1().delete("com.plgrim.ncp.biz.mbr.token.removeUserTokens", cookie);
		this.logAppend("removeUserTokens", "[theft attack] all row delete = > ok", "-", username, "-");
	}
	
	private String[] getMbrInfo(String cookieId) {
		//System.out.println("Peter : getMbrInfo..."+cookieId);
		String[] mbrArr = null;
		MbrLoginCookieLog cookie = new MbrLoginCookieLog();
		cookie.setCookieId(cookieId);
		Map<String,Object> result = getSession1().selectOne("com.plgrim.ncp.biz.mbr.token.selectTokenForSeries", cookie);
		//System.out.println("Peter : ---1---");
		if(result != null){
			//System.out.println("Peter : ---2---"+(String)result.get("MBR_NO"));
			mbrArr = new String[3];
			mbrArr[0] = (String)result.get("USERNAME");
			mbrArr[1] = (String)result.get("SERIES");
			mbrArr[2] = (String)result.get("TOKEN");
		}
		//System.out.println("Peter : ---3 getMbrInfo end---");
		return mbrArr;
	}
	
	private boolean isValidToken(String cookieToken) {
		//System.out.println("Peter : isValidToken...");
		String cookieValue = getRememberMeCookieValue(REMEMBER_ME_COOKIE_NAME);
		String[] decodeCookie = null;
		String presentedToken = "";
		boolean isValid = true;
		//System.out.println("Peter : ---1---"+cookieToken);
		if(cookieValue != null && !"".equals(cookieValue)) {
			decodeCookie = decodeCookie(cookieValue);
			//System.out.println("Peter : ---2---"+decodeCookie);
			presentedToken = decodeCookie[1];
			//System.out.println("Peter : ---3---"+presentedToken);
			if (!presentedToken.equals(cookieToken)) {
				//System.out.println("Peter : ---4---");
				isValid = false;
			}
		}
		//System.out.println("Peter : ---5 isValidToken end--- :: "+isValid);
		return isValid;
	}
	
	private void logAppend(String actionNm, String actionDscr, String cookieId, String mbrNo, String tokenId) {
		if(REMEMBER_ME_LOG_ENABLED){
			MbrLoginCookieLog cookie = new MbrLoginCookieLog();
			cookie.setCookieId(cookieId);
			cookie.setMbrNo(mbrNo); //mbrID를 넘겨주면 쿼리에서 MbrNo로 교체해서 저장한다.
			cookie.setToknId(tokenId);
			/*
			cookie.setActionNm(actionNm);
			cookie.setActionDscr(actionDscr);
			cookie.setClientIp(getClientIP()+"/"+getRememberMeCookieValue(REMEMBER_ME_COOKIE_NAME));
			cookie.setWasIp(getWasServerIp()+"<-"+getWebServerIp()+" | "+getCurrentLang());
			cookie.setInstanceNm(getEnvValue("system.id"));
			*/
			getSession1().insert("com.plgrim.ncp.biz.mbr.token.rememberMeLogHist", cookie);
		}
	}
	
	private String getRememberMeCookieValue(String cookieName){
		HttpServletRequest req = ContextService.getCurrentRequest();
		Cookie ck[] = req.getCookies();
		String cookieValue = "";
		if (ck != null){
		    for (int i = 0 ; i < ck.length ; i++){
		      if (ck[i].getName().equals(REMEMBER_ME_COOKIE_NAME)){
		    	  cookieValue = ck[i].getValue();
		      }
		    }
		  }
		return cookieValue;
	}
	
	private String[] decodeCookie(String cookieValue) throws InvalidCookieException {
		for (int j = 0; j < cookieValue.length() % 4; j++) {
			cookieValue = cookieValue + "=";
		}

		if (!Base64.isBase64(cookieValue.getBytes())) {
			throw new InvalidCookieException(
					"Cookie token was not Base64 encoded; value was '" + cookieValue + "'");
		}

		String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));

		String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText, ":");

		if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https"))
				&& tokens[1].startsWith("//")) {
			// Assume we've accidentally split a URL (OpenID identifier)
			String[] newTokens = new String[tokens.length - 1];
			newTokens[0] = tokens[0] + ":" + tokens[1];
			System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
			tokens = newTokens;
		}

		return tokens;
	}
	
	private String getClientIP() {
		HttpServletRequest req = ContextService.getCurrentRequest();
		String userip = req.getHeader("X-Forwarded-For"); // 아이피 가져오기 아파치 아래에 웹로직이 있을경우         
		String clientIp = "N/A";
        if ( userip == null  || "".equals(userip) ) { // 아이피 가져오기 , 바로 웹로직이 있을경우
            userip = req.getRemoteAddr();
        }        
        if ( userip == null  || "".equals(userip) ) {
            return "";
        }        
        if(userip != null){
        	String[] userips = userip.split(",");
        	clientIp = userips[0];
        }
        return clientIp;
    }
	
	private String getWebServerIp() {
		HttpServletRequest req = ContextService.getCurrentRequest();
		String h_name = req.getServerName();
		InetAddress inetaddr = null;
		String ip = "N/A";
        try {
	        inetaddr = InetAddress.getByName(h_name);
        } catch (UnknownHostException e) {
        }
        if(inetaddr != null){
        	ip = inetaddr.getHostAddress();
        }
		return ip;
	}
	
	private String getCurrentLang() {
		HttpServletRequest req = ContextService.getCurrentRequest();
		String localLang = "N/A";
		try {
			localLang = LocaleService.getCurrentLang(req);
        } catch (Exception e) {
        }
		return localLang;
	}
	
	@SuppressWarnings("static-access")
    private String getWasServerIp() {
		HttpServletRequest req = ContextService.getCurrentRequest();
		String wasInfoStr = "N/A";
		try
		{
		    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
		    {
		        NetworkInterface intf = en.nextElement();
		        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
		        {
		            InetAddress inetAddress = enumIpAddr.nextElement();
		            if(inetAddress != null){
			            if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
			            {
			            	wasInfoStr = inetAddress.getHostAddress().toString()+"/"+inetAddress.getLocalHost().getHostName();
			            	wasInfoStr = wasInfoStr + "/"+req.getServerName()+"/"+req.getServerPort();
			            	return wasInfoStr;
			            }
		            }
		        }
		    }
		} catch (SocketException ex) {			
		} catch (UnknownHostException e) {}
		return "N/A";
	}
	
	private String getEnvValue(String envNm){
		String instanceNm = "N/A";
		try {
	        instanceNm = getIdGenService().getEnvValue(envNm);
        } catch (Exception e) {
        }
		return instanceNm;
	}
}
