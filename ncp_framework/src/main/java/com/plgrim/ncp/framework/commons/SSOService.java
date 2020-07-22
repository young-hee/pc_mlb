/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 4. 8       
 */
package com.plgrim.ncp.framework.commons;

import java.util.StringTokenizer;

import com.plgrim.ncp.framework.utils.EpTrayUtil;
import com.plgrim.ncp.framework.utils.Utils;

/**
 * Created by Administrator on 2015-04-07.
 */
public class SSOService {

	/**
	 * 화면에서 받은 값에 대하여 복호화 하여 USER 에 대한 정보를 보여줌
	 * 
	 * <p/>
	 * 
	 * 
	 * ex)
	 *  String strTotalData = request.getParameter("totaldata");

		StringTokenizer token = new StringTokenizer(strTotalData , ";");
		String strNewDataList = token.nextToken();
		String strMD5SecureKey = token.nextToken();
		String strKeyFolder = token.nextToken();	

		byte[] baPublicKey = SSOService.getPublicKey("C:/Project/workspaces/ncp-project/ncp_web_pc_cps_sf/src/main/resources/META-INF/resources/WEB-INF/resources/sso/eptray/" + strKeyFolder + "/mySingle_key");
		String userInfo = SSOService.DecryptDataList(new String(baPublicKey),strMD5SecureKey,strNewDataList);
	 *
	 * @param pubkey  
	 * @param seckey [설명]
	 * @param encdata [설명]
	 * @return String [설명]
	 * @since 2015. 4. 8
	 */
	public static String DecryptDataList(String pubkey,String seckey, String encdata) {
		return EpTrayUtil.DecryptDataList(pubkey, seckey, encdata);
		
	}

	public static String getMD5String(byte[] b) {
		return EpTrayUtil.getMD5String(b);
	}
	
	public static String pluralStr( long n ) {
		return Utils.pluralStr(n);
	}
	public static String intervalStr( long interval ) {
		return Utils.intervalStr(interval);
	}
	public static int strSpan( String str, String charSet ) {
		return Utils.strSpan(str, charSet);
	}
	public static int strSpan( String str, String charSet, int fromIdx ) {
		return Utils.strSpan(str, charSet, fromIdx);
	}
	public static int strCSpan( String str, String charSet ) {
		return Utils.strCSpan(str, charSet);
	}
	public static int strCSpan( String str, String charSet, int fromIdx ) {
		return Utils.strCSpan(str, charSet, fromIdx);
	}
	public static boolean match( String pattern, String string ) {
		return Utils.match(pattern, string);
	}
	public static int sameSpan( String str1, String str2 ) {
		return Utils.sameSpan(str1, str2);
	}
	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param keyPath mysingle key의 실제 경로
	 * @return Byte[] [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 8
	 */
	public static byte[] getPublicKey(String keyPath) {
		return Utils.getPublicKey(keyPath);
	}
	
}
