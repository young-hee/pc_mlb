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
 *  beyondj2ee			2015-02-10                     
 */

package com.plgrim.ncp.framework.utils;

import org.apache.commons.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * The Class XSSUtil.
 */
@Slf4j
public class XSSUtil {
	
	/**
	 * <p>
	 * description about class
	 * </p>
	 * Sanitize.
	 * 
	 * @param string the string
	 * @return the string
	 */
	public static String sanitize(String string) {

		if (string == null) {
			return "";
		}
		String value = "";
		try{
			value = string.replaceAll("(?i)<script.*?>.*?</script.*?>", "")
					.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "")
					.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
			value = escapeHTML(value);
		}catch(Exception e){
			log.error("",e);
		}
		return StringUtils.trimToEmpty(string);
	}
	
	/**
	 * <p>
	 * description about class
	 * </p>
	 * Escape html.
	 * 
	 * @param value the value
	 * @return the string
	 */
	public static String escapeHTML (String value) {
		String str = value;
		str = StringUtils.replace(str, "<", "&lt;");
		str = StringUtils.replace(str, ">", "&gt;");
		return str;
	}
	
	/**
	 * <p>
	 * description about class
	 * </p>
	 * Unescape html.
	 * 
	 * @param value the value
	 * @return the string
	 */
	public static String unescapeHTML (String value) {
		String str = value;
		str = StringUtils.replace(str, "&lt;", "<");
		str = StringUtils.replace(str, "&gt;", ">" );
		return str;
	}
	

	/**
	 * <p>
	 * description about class
	 * </p>
	 * .
	 * 
	 * @param vars the arguments
	 */
	public static void main(String[] vars)  {
		String str = "<p>체험판</p>";
		log.debug(sanitize(str));
		log.debug(unescapeHTML(sanitize(str)));
	}
	
	/**
	 * Builds the random id.
	 *
	 * @return the string
	 */
	public static String buildRandomId () {
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public static String stripXSS(String value) {
		if (value != null) {
			try{
				value = value.replaceAll("\"", "");

				Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("<marquee>(.*?)</marquee>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("<a>(.*?)</a>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("<img(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("alert(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				scriptPattern = Pattern.compile("onmouse(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
				
				value = escapeHTML(value);
			}catch(Exception e){
				log.error("",e);
			}
		}
		return value;
	}
	
}
