/*
 * Copyright (c) 2012 CORE BUILDER
 * All right reserved.
 */
package com.plgrim.ncp.commons.taglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.utils.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Functions {

	public static HashMap<String, String> beForeMonth() throws Exception {

		HashMap<String, String> map = Maps.newHashMap();
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat fdm2 = new SimpleDateFormat("yyyy-MM-dd");
		currentDate.setTime(new Date());
		String s = fdm2.format(currentDate.getTime());
		map.put("S", s);
		currentDate.add(Calendar.MONTH, -1);
		String e = fdm2.format(currentDate.getTime());
		map.put("E", e);

		return map;
	}

	public static String marshallingJson(Object object) throws Exception {

		return JsonUtil.marshallingJson(object);
	}

	public static String yyyymmdd(Object obj) throws ParseException {

		String dateType = "yyyy-MM-dd";
		if (obj == null) {
			return "";
		}
		if (obj instanceof Date) {
			return convertDate((Date) obj, dateType);
		} else {
			SimpleDateFormat fdm = new SimpleDateFormat("yyyyMMdd");
			Date toDate = fdm.parse((String) obj);
			SimpleDateFormat fdm2 = new SimpleDateFormat(dateType);
			return fdm2.format(toDate);
		}

	}

	public static String yyyymmddKr(Date date) {

		String dateType = "yyyy년 MM 월 dd 일";

		return convertDate(date, dateType);
	}

	public static String yyyymmddhhmmss(Date date) {

		String dateType = "yyyy-MM-dd HH:mm:ss";

		return convertDate(date, dateType);
	}

	public static String convertDate(Date date, String dateType) {

		if (date == null) {
			return "";
		} else {
			Calendar currentDate = Calendar.getInstance();

			DateFormat df = new SimpleDateFormat(dateType);
			currentDate.setTime(date);

			return df.format(currentDate.getTime());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void variAbleSetN(Object obj) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		Class c = Class.forName(obj.getClass().getName());

		Method[] allMs = c.getMethods();

		for (Method method : allMs) {

			if (method.getName().indexOf("set") != -1) {

				String name = method.getName().toUpperCase().substring(method.getName().length() - 2, method.getName().length());

				if (name.indexOf("YN") != -1) {

					Method m = c.getMethod(method.getName(), String.class);

					m.invoke(obj, "N");
				}

			}

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyProperties(Object source, Object target) throws ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Class c = Class.forName(source.getClass().getName());
		Class ct = Class.forName(target.getClass().getName());

		Method[] allMs = c.getMethods();
		Method[] allMst = ct.getMethods();
		for (Method method : allMs) {

			if (method.getName().indexOf("set") != -1) {

				String name = method.getName().substring(3);
				Method m = c.getMethod("get" + name);
				for (Method method2 : allMst) {

					if (method2.getName().indexOf("set") != -1) {

						if (method2.getName().toUpperCase().indexOf(name.toUpperCase()) != -1) {
							method2.invoke(target, m.invoke(source));

						}

					}
				}
			}
		}
	}

	/**
	 * original 의 메서드 명이 destination 의 메서드 명보다 긴경우
	 * 
	 * @param dstObj
	 *            Object
	 * @param orgObj
	 *            Object
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyProperties2(Object dstObj, Object orgObj) throws ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Class dstClass = Class.forName(dstObj.getClass().getName());
		Class orgClass = Class.forName(orgObj.getClass().getName());

		Method[] dstMethods = dstClass.getMethods();
		Method[] orgMethods = orgClass.getMethods();

		for (Method dst : dstMethods) {
			if (dst.getName().indexOf("set") != -1) {
				String dstName = dst.getName().substring(3);
				for (Method org : orgMethods) {
					if (org.getName().indexOf("set") != -1) {
						String orgName = org.getName().substring(3);
						if (orgName.toUpperCase().indexOf(dstName.toUpperCase()) != -1) {
							Method m = orgClass.getMethod("get" + orgName);
							dst.invoke(dstObj, m.invoke(orgObj));
						}
					}
				}
			}
		}
	}

	public static void toDayCookies(String addKey, String newAddKey, String sectCd, String cookieId, String name,
			HttpServletResponse response) throws Exception {

		Cookie cookies[] = ContextService.getCurrentRequest().getCookies();
		List<LinkedHashMap<String, Object>> list = null;
		List<LinkedHashMap<String, Object>> temp = new ArrayList<LinkedHashMap<String, Object>>();
		boolean flag = true;
		String godList = "";

		try {
			for (int i = 0; i < cookies.length; i++) {
				if (StringService.equalsIgnoreCase(cookies[i].getName(), cookieId)) {
					flag = false;
					godList = cookies[i].getValue();
					//godList = "\"[{todayGodSectCd:GOD,godNo:GM0017021364693,evtNm:,dspPromtNm:,regDt:1490880359733}]\"" ;
					//godList = "[{\"todayGodSectCd\":\"GOD\",\"godNo\":\"GM0017031467974\",\"evtNm\":\"\",\"dspPromtNm\":\"\",\"regDt\":1490935172419},{\"todayGodSectCd\":\"GOD\",\"godNo\":\"GM0017031467973\",\"evtNm\":\"\",\"dspPromtNm\":\"\",\"regDt\":1490935179084}]";
					list = getGodMapList(godList);

					if (list != null) {
						for (LinkedHashMap<String, Object> v : list) {
							String key = (String) v.get(addKey); // godNo

							boolean add = true;
							if (newAddKey.equals(key)) {
								add = false;
							} else {
								if(v.get("regDt") == null || StringService.isEmpty(v.get("regDt").toString())) {
									add = false;
								} else {
									Calendar today = Calendar.getInstance();
									Calendar regDt = Calendar.getInstance();
									
									regDt.setTimeInMillis(Long.parseLong(v.get("regDt").toString()));
									regDt.add(Calendar.DATE, 7);
									today.set(Calendar.HOUR_OF_DAY, 0);
									today.set(Calendar.MINUTE, 0);
									today.set(Calendar.SECOND, 0);
									today.set(Calendar.MILLISECOND, 0);
									regDt.set(Calendar.HOUR_OF_DAY, 0);
									regDt.set(Calendar.MINUTE, 0);
									regDt.set(Calendar.SECOND, 0);
									regDt.set(Calendar.MILLISECOND, 0);
									if (today.after(regDt)) {
										add = false;
									}
								}
							}

							if (add) {
								temp.add(v);
							}
						}
					}

					if (StringService.isNotEmpty(newAddKey) && newAddKey.length() > 2) {
						LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
						map.put("todayGodSectCd", sectCd);
						map.put(addKey, newAddKey);
						String val = java.net.URLEncoder.encode(name, "UTF-8");
						//map.put("evtNm", val); 사용하지 않는 항목 제거 20170614 kenny
						//map.put("dspPromtNm", val);
						map.put("regDt", System.currentTimeMillis());
						temp.add(map);
						
						//if(temp.size()>40){ // 쿠키에 저장되는 상품수 제한 20170614 kenny
						while(temp.size()>40){
							temp.remove(0);
						}
						//}
					}

					godList = JsonService.marshalling(temp);
					Cookie cookieSet = new Cookie(cookieId, godList);
					cookieSet.setPath("/");
					cookieSet.setMaxAge(-1);
					response.addCookie(cookieSet);
				}
			}
			if (flag) {	//cookieId 이름의 쿠키가 존재하지 않는 경우 쿠키 생성
				List<LinkedHashMap<String, Object>> list1 = new ArrayList<LinkedHashMap<String, Object>>();
				LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
				if (StringService.isNotEmpty(newAddKey) && newAddKey.length() > 2) {
					map.put("todayGodSectCd", sectCd);
					map.put(addKey, newAddKey);
					String val = java.net.URLEncoder.encode(name, "UTF-8");
					//map.put("evtNm", val); 사용하지 않는 항목 제거 20170614 kenny
					//map.put("dspPromtNm", val);
					map.put("regDt", System.currentTimeMillis());
					list1.add(map);
					String godList1 = JsonService.marshalling(list1);
					Cookie cookieSet = new Cookie(cookieId, godList1);
					cookieSet.setPath("/");
					cookieSet.setMaxAge(-1); // 60*60*24*1
					response.addCookie(cookieSet);
				}
			}
		} catch (Exception e) {
			log.info("skyscraper toDayCookies cast error = {} : {}", godList, list, e);
		}
	}

	public static void cookies(Cookie[] cookies, List<MbrTodayGod> skyScrapers, int i) throws Exception {
		List<LinkedHashMap<String, Object>> list = null;
		String godList = "";
		try {
			MbrTodayGod mt = null;
			if (StringService.equalsIgnoreCase(cookies[i].getName(), "_TODAYALLLIST")) {
				godList = cookies[i].getValue();
				list = getGodMapList(godList);

				if (list != null) {
					for (LinkedHashMap<String, Object> v : list) {
						mt = new MbrTodayGod();
						if (v.get("todayGodSectCd").equals("GOD")) {
							mt.setGodNo((String) v.get("godNo"));
							mt.setTodayGodSectCd("GOD");
						} else if (v.get("todayGodSectCd").equals("EVT")) {
							mt.setEvtNo((String) v.get("evtNo"));
							mt.setTodayGodSectCd("EVT");
						} else {
							if(v.get("promtSn") != null) {
								mt.setPromtSn(Long.valueOf((String) v.get("promtSn")));
								mt.setTodayGodSectCd("PROMT");
							}							
						}
						mt.setRegDt(new Date( Long.parseLong(v.get("regDt").toString()) ));
						skyScrapers.add(mt);
					}
				}
			}
		} catch (Exception e) {
			log.info("skyscraper map cast error = {} : {}", godList, list, e);
		}
		
//		skyScrapers = skyScrapers.stream()
//				.sorted((o1, o2)->o1.getRegDt().compareTo(o2.getRegDt()))
//			    .collect(Collectors.toList());
	}

	public static int getRandomNum(int i) throws Exception {
		return (new Random().nextInt(i) + 1);
	}
	
	/**
	 * 최근본상품 쿠키가 json type 으로 잘못넘어오는 경우 json cast error 발생으로 split 로직 구현
	 */
	public static List<LinkedHashMap<String, Object>> getGodMapList(String godList) throws Exception {
		List<LinkedHashMap<String, Object>> rtnList = new ArrayList<LinkedHashMap<String, Object>>();
		
		String[] godListArr = godList.trim().split("},");
		if(godListArr != null) {
			for(int i=0; i<godListArr.length; i++) {
				LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
	    		String godItem =  godListArr[i].replace("[", "").replace("{", "").replace("]", "").replace("}", "").replace("\\", "").replace("\"", "");
	    		if (StringService.isNotEmpty(godItem)) {
	    			String[] oneItemArr = godItem.split(",");
		    		if(oneItemArr != null) {
		    			for(int j=0; j<oneItemArr.length; j++) {
			    			String[] oneMap = oneItemArr[j].split(":");
			    			if(oneMap != null) {
			    				if(oneMap[0].equals("todayGodSectCd") || oneMap[0].equals("godNo") || oneMap[0].equals("evtNo") 
			    						|| oneMap[0].equals("promtSn") || oneMap[0].equals("evtNm") || oneMap[0].equals("dspPromtNm") || oneMap[0].equals("regDt")) {
			    					if(oneMap.length == 1) {	//evtNm: or dspPromtNm: 인 경우
				    					map.put(oneMap[0], "");
				    				} else if(oneMap.length == 2) {
				    					map.put(oneMap[0], oneMap[1]);
				    				}
			    				}
			    				
			    			}
			    		}
		    		}
	    		}
	    		
	    		if(!map.isEmpty()) {
	    			rtnList.add(map);
	    		}
			}
		}
		
//		rtnList = rtnList.stream()
//				.sorted((o1, o2)->o1.get("regDt").toString().compareTo(o2.get("regDt").toString()))
//			    .collect(Collectors.toList());

		return rtnList;
	}
}
