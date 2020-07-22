package com.plgrim.ncp.commons.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.commons.repository.CodeViewRepository;
import com.plgrim.ncp.commons.result.CodeViewResult;

@Component
public final class CodeUtil implements InitializingBean  {
	@Autowired
	CodeViewRepository commonSelectRepository;
	static final Object lockObj = new Object();
	static final String DEFAULT_LANG = "KOR";
	static final List<Code> EMPTY = new ArrayList<Code>();
	static Map<String, Map<String, List<Code>>> mapGrpLang = new HashMap<String, Map<String, List<Code>>>();
	static Map<String, Code> mapRawCode = new HashMap<String, Code>();
	protected CodeUtil(){
		
	}
	public static List<Code> getCodes(String grp, String lang, boolean includeNotUse, boolean calUseYn, String orderBy, String filterAsstnCd1, String filterAsstnCd2){
		Map<String, List<Code>> sub = mapGrpLang.get(grp);
		if(sub == null){
			return Collections.emptyList();
		}
		List<Code> list = sub.get(lang);
		if(list == null){
			list = sub.get(DEFAULT_LANG);
			if(list == null){
				return Collections.emptyList();
			}
		}
		if(!includeNotUse){
			List<Code> tgt = new ArrayList<Code>();
			for(Code cd : list){
				if(cd.isUse){
					tgt.add(cd);
				}
			}
			list = tgt;
		}
		if(calUseYn){
			List<Code> tgtcal = new ArrayList<Code>();
			for(Code cd : list){
				if(cd.isCalUse){
					tgtcal.add(cd);
				}
			}
			list = tgtcal;
		}
		if(filterAsstnCd1 != null) {
			List<Code> tgtft1 = new ArrayList<Code>();
			for(Code cd : list){
				if(filterAsstnCd1.equals(cd.asstnCd1)){
					tgtft1.add(cd);
				}
			}
			list = tgtft1;
		}
		if(filterAsstnCd2 != null) {
			List<Code> tgtft2 = new ArrayList<Code>();
			for(Code cd : list){
				if(filterAsstnCd2.equals(cd.asstnCd2)){
					tgtft2.add(cd);
				}
			}
			list = tgtft2;
		}
		if("ASC".equalsIgnoreCase(orderBy)) {
			Collections.sort(list, new NameAscCompare());
		}else if("DESC".equalsIgnoreCase(orderBy)) {
			Collections.sort(list, new NameDescCompare());
		}
		
		return Collections.unmodifiableList(list);
	}
	
	public static List<Code> getCodes(String grp, String lang, boolean includeNotUse, boolean isCalUse, String orderBy){
		return getCodes(grp, lang, includeNotUse, isCalUse,orderBy, null, null);
	}	
	public static List<Code> getCodes(String grp, String lang, boolean includeNotUse, boolean isCalUse){
		return getCodes(grp, lang, includeNotUse, isCalUse,null, null, null);
	}	
	public static List<Code> getCodes(String grp, String lang, boolean includeNotUse){
		return getCodes(grp, lang, includeNotUse,false, null, null, null);
	}
	public static List<Code> getCodes(String grp, String lang){
		return getCodes(grp, lang, false, false,null, null, null);
	}
	public static Code getCode(String lang, String cd){
		try{
			return mapRawCode.get(lang+'_'+cd);
		}catch(Exception e){
			
		}
		return null;
	}
	@Override
    public void afterPropertiesSet() throws Exception {
		List<CodeViewResult> list = commonSelectRepository.getAllCodeList();
		Map<String, Map<String, List<Code>>> map = new HashMap<String, Map<String, List<Code>>>();
		Code code;
		for(CodeViewResult cd : list){
			code = new Code(cd);
			addGrpLang(map, code);
		}
		synchronized (lockObj) {
			mapGrpLang = map;
		}
		String group, lang;
		for(Entry<String, Map<String, List<Code>>> entry1 : map.entrySet()){
			group = entry1.getKey();
			for(Entry<String, List<Code>> entry2 : entry1.getValue().entrySet()){
				lang = entry2.getKey();
				for(Code c : entry2.getValue()){
					c.sub = getCodes(c.cd, lang);
				}
			}
		}
		for(CodeViewResult cd : commonSelectRepository.getAllRawCodeList()){
			code = new Code(cd);
			mapRawCode.put(code.getLang()+'_'+code.getCd(), code);
		}
    }
	private void addGrpLang(Map<String, Map<String, List<Code>>> map, Code code){
		Map<String, List<Code>> sub = map.get(code.getUpperCd());
		if(sub == null){
			sub = new HashMap<String, List<Code>>();
			map.put(code.getUpperCd(), sub);
		}
		List<Code> sl = sub.get(code.getLang());
		if(sl == null){
			sl = new ArrayList<Code>();
			sub.put(code.getLang(), sl);
		}
		sl.add(code);
	}
	public class Code implements Serializable{

		/**
		 * UID
		 */
	    private static final long serialVersionUID = 1L;
	    Code(CodeViewResult cvr){
	    	cd = cvr.getCd();
	    	upperCd = cvr.getUpperCd();
	    	lang = cvr.getLang();
	    	cdNm = cvr.getCdNm();
	    	cdDscr = cvr.getCdDscr();
	    	asstnCd1 = cvr.getAsstnCd1();
	    	asstnCd2 = cvr.getAsstnCd2();
	    	isLeaf = cvr.isLeaf();
	    	isUse = cvr.isUse();
	    	isCalUse =cvr.isCalUseYn();
	    }
		/**
		 * 코드
		 */
		private String cd;
		
		/**
		 * 상위코드
		 */
		private String upperCd;

		/**
		 * 언어
		 */
		private String lang;
		
		/**
		 * 코드명
		 */
		private String cdNm;
		
		/**
		 * 설명
		 */
		private String cdDscr;
		
		/**
		 * 보조코드1
		 */
		private String asstnCd1;
		
		/**
		 * 보조코드2
		 */
		
		private String asstnCd2;
		private boolean isLeaf;
		private boolean isUse;
		private boolean isCalUse;
		private List<Code> sub;

		public String getCd() {
			return cd;
		}

		public String getUpperCd() {
			return upperCd;
		}

		public String getLang() {
			return lang;
		}

		public String getCdNm() {
			return cdNm;
		}

		public String getCdDscr() {
			return cdDscr;
		}

		public String getAsstnCd1() {
			return asstnCd1;
		}

		public String getAsstnCd2() {
			return asstnCd2;
		}
		public List<Code> getSub(){
			return sub;
		}

		public boolean isLeaf() {
			return isLeaf;
		}
		public boolean isUse() {
			return isUse;
		}
		public boolean isCalUse() {
			return isCalUse;
		}		
	}
	
	static class NameAscCompare implements Comparator<Code> {
		 
		/**
		 * 오름차순(ASC)
		 */
		public int compare(Code arg0, Code arg1) {
			return arg0.getCdNm().compareTo(arg1.getCdNm());
		}
 
	}
	
	static class NameDescCompare implements Comparator<Code> {
		/**
		 * 내림차순(DESC)
		 */
		public int compare(Code arg0, Code arg1) {
			return arg1.getCdNm().compareTo(arg0.getCdNm());
		}
 
	}
	
}
