package com.plgrim.ncp.framework.utils;

public class HtmlUtil {

	private static final String[] BLACK_LIST_TAG = { "javascript", "eval", "applet", "document", "xml", "create",
			"script", "msgbox", "object", "embed", "frame", "ondragstart", "iframe", "cookie", "onerror", "onmove",
			"onstop", "layer", "onacivae", "onfocusin", "onclick", "onkeydown", "onbeforecut", "onload", "ondragend",
			"onbounce", "ondragleave", "onmovestart", "onmouseout", "alert", "append", "onmouseover", "onscroll",
			"void" };
	
	

	/**
	 * 특수기호를 escape 시킨다.
	 * 
	 */
	public static final String escapeHTML(String s) {
		StringBuffer sb = new StringBuffer();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			// case '&':
			// sb.append("&amp;");
			// break;
			case '"':
				sb.append("&quot;");
				break;

			case '\'':
				sb.append("&#39;");
				break;
				
			case '(':
				sb.append("&#40;");
				break;
				
			case ')':
				sb.append("&#41;");
				break;
			case ',':
				sb.append("&#44;");
				break;
			case '.':
				sb.append("&#46;");
				break;
			case '/':
				sb.append("&#47;");
				break;
			case ';':
				sb.append("&#59;");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	public static String unescapeHTML(String html) {
		if (html == null) {
			return null;
		}
		String temp = html.replaceAll("&lt;", "<");
		temp = temp.replaceAll("&gt;", ">");
		temp = temp.replaceAll("&quot;", "\"");
		temp = temp.replaceAll("&#39;", "\'");
		temp = temp.replaceAll("&amp;nbsp;", "<br />");

		return temp;
	}
	
//	스크립트 정의어 : <SCRIPT>, <OBJECT>, <APPLET>, <EMBED>, <FORM>, <IFRAME>
//	  특수문자 : <, >( <, >로 치환), ",  '(이상 attribute값), &(문자 entity), %(줄바꿈), %00(null)

	/**
	 * html의 태그 열고/닫고는 사용해야 하는 경우가 있어서 blackList에 해당되는 html태그만 사용 못하도록 replace 함
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceBlackListTag(String html) {
		if (html == null) {
			return null;
		}
		int changeCnt = 0;
		String temp = html.toLowerCase();
		for (String tag : BLACK_LIST_TAG) {
			if (temp.indexOf(tag) > -1) {
				temp = temp.replaceAll(tag, "x-" + tag);
				changeCnt = 1;
			}
		}
		return changeCnt == 0 ? html : temp;
	}
}