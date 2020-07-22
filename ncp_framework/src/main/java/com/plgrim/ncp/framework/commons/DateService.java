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
 * @since       2015. 3. 3       
 */
package com.plgrim.ncp.framework.commons;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 날짜 관련 유틸리티 서비스
 * 
 * <p>
 * .
 *
 * @author tktaeki.kim
 * @since 2015. 3. 3
 */
public class DateService {

	
	/* 기본 날짜 포맷 */
	final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final String[] LAST_MONTH_OF_QUATER = {"03", "06", "09", "12"};

	/**
	 * 연속된 날짜 텍스트를 받아서 Date 포맷에 맞게 변환 한다.
	 * 
	 * <p/>
	 * 
	 * getDateService().parseDateString("20151201", "yyyy-MM-dd") = "2015-12-01"
	 * getDateService().parseDateString("20151201121324", "yyyy-MM-dd HH:mm:ss")
	 * = "2015-12-01 12:13:12"
	 *
	 * @param date 연속된 날짜 텍스트
	 * @param pattern Date 포맷
	 * @return String 변환된 날짜 텍스트
	 * @since 2015. 3. 3
	 */
	public static String parseString(String date, String pattern) {

		String inputPattern = "yyyyMMdd";
		String sourceDate = StringUtils.trimToEmpty(date);

		// 날짜가 8자리 이상일 경우
		if (StringUtils.length(sourceDate) > 8) {

			inputPattern = "yyyyMMddHHmmss";
		}
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern(inputPattern).withZoneUTC();
		DateTime dateTime = dtf.parseDateTime(sourceDate);

		return parseFormat(dateTime, pattern);
	}

	/**
	 * ncp 표준 데이터 포맷을 리턴 한다.
	 * 
	 * <p/>
	 * 
	 * getDateService().parseDefaultFormat(new Date()) = "2015-03-03- 13:41:40"
	 *
	 * @param date Date 오브젝트
	 * @return String 기본 포맷으로 변환한 텍스트
	 * @since 2015. 3. 3
	 */
	public static String parseDefaultFormat(Date date) {
		DateTime jodatime = new DateTime(date);
		return parseFormat(jodatime, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 데이트 오브젝트를 날짜 포맷에 맞게 스트링 형태로 리턴 한다.
	 * 
	 * <p/>
	 * 
	 * getDateService().parseDateToString(new Date(), "yyyyMMddHHmmss") =
	 * "20150303134140"
	 *
	 * @param date Date 오브젝트
	 * @param pattern 날짜 포맷
	 * @return String 변환된 텍스트
	 * @since 2015. 3. 3
	 */
	public static String parseString(Date date, String pattern) {
		DateTime jodatime = new DateTime(date);
		return parseFormat(jodatime, pattern);
	}

	/**
	 * 시작일자, 검색일자를 입력 하면 포함된 리스트 월을 리턴 한다
	 * 
	 * <p/>
	 * 
	 * getDateService().getBetweenMonths("20141101", "20150405", "yyyy-MM")
	 * ="[2014-11, 2014-12, 2015-01, 2015-02, 2015-03, 2015-04]"
	 *
	 * @param beginDate 시작날짜 (예:20141101)
	 * @param endDate 마지막날짜(예:20150405)
	 * @param pattern 데이트 포맷 (예:yyyy-MM)
	 * @return List 검색조건에 해당하는 모든 월
	 * @since 2015. 3. 3
	 */
	public static List<String> getBetweenMonths(final String beginDate,
	        final String endDate, final String pattern) {

		final String sourcePattern = "yyyyMMdd";

		DateTimeFormatter dtf = DateTimeFormat.forPattern(sourcePattern);
		DateTime s = dtf.parseDateTime(beginDate);
		DateTime d = dtf.parseDateTime(endDate);

		int months = Months.monthsBetween(s, d).getMonths();

		DateTime tempDateTime;
		String result;
		List<String> results = new ArrayList<String>();

		// 시작일자를 기준으로 종료일자까지 List에 추가 한다.
		// 단 시작일자, 종료일자를 포함 한다.
		for (int i = 0; i <= months; i++) {

			tempDateTime = s.plusMonths(i);
			result = tempDateTime.toString(pattern);
			results.add(result);
		}

		return results;
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * Date d1 = d.createDate("20140101142323", "yyyyMMddHHmmss");<br/>
	 * Date d2 = d.createDate("20150101142323", "yyyyMMddHHmmss");<br/>
	 *
	 * log.debug(getDateService().getBetweenMonths(d1, d2, "yyyy-MM"))
	 * =
	 * "[2014-01, 2014-02, 2014-03, 2014-04, 2014-05, 2014-06, 2014-07, 2014-08, 2014-09, 2014-10, 2014-11, 2014-12, 2015-01]"
	 * <br/>
	 *
	 * @param beginDate 시작일자 텍스트
	 * @param endDate 마지막일자 텍스트
	 * @param pattern 월단위 패턴
	 * @return List 검색조건에 해당하는 모든 월
	 * @since 2015. 3. 3
	 */
	public static List<String> getBetweenMonths(final Date beginDate,
	        final Date endDate, final String pattern) {

		final String sourcePattern = "yyyyMMdd";
		DateTimeFormatter dtf = DateTimeFormat.forPattern(sourcePattern);

		DateTime b = new DateTime(beginDate);
		DateTime e = new DateTime(endDate);
		return getBetweenMonths(dtf.print(b), dtf.print(e), pattern);

	}

	/**
	 * 스트링 텍스트를 Date 형태로 반환 한다.
	 * 
	 * <p/>
	 * 
	 * Date date = getDateService().createDate("20150101142323",
	 * "yyyyMMddHHmmss");<br/>
	 * log.debug(d1) = "Thu Jan 01 14:23:23 KST 2015"
	 *
	 * @param date 날짜 스트링 데이터
	 * @param pattern 날짜 포맷
	 * @return Date 날짜 오브젝트
	 * @since 2015. 3. 3
	 */
	public static Date createDate(final String date, String pattern) {

		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);

		DateTime dt = DateTime.parse(date, fmt);

		return dt.toDate();

	}

	/**
	 * 스트링 텍스트를 Date 형태로 반환 한다.
	 *
	 * <p/>
	 *
	 * Date date = getDateService().createDefaultDate("20150101142323");<br/>
	 * log.debug(d1) = "Thu Jan 01 14:23:23 KST 2015"
	 *
	 * @param date 날짜 스트링 데이터
	 * @param pattern 날짜 포맷
	 * @return Date 날짜 오브젝트
	 * @since 2015. 3. 3
	 */
	public static Date createDefaultDate(final String date) {

		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddHHmmss");

		DateTime dt = DateTime.parse(date, fmt);

		return dt.toDate();

	}

	/**
	 * 지정된 일자 다은 달을 리턴 한다.
	 * 
	 * <p/>
	 * 
	 * getDateService().getNextMonth(new Date(), "yyyy-MM") = 2014-02
	 *
	 * @param beginDate 지정 일자
	 * @param pattern 출력 패턴
	 * @return String 다음날 날짜 스트링
	 * @since 2015. 3. 3
	 */
	public static String getNextMonth(final Date beginDate, final String pattern) {

		DateTime beginDateTime = new DateTime(beginDate).plusMonths(1);

		String results = beginDateTime.toString(pattern);

		return results;
	}

	/**
	 * 지정된 일자의 지정된 달을 리턴 한다.
	 * 
	 * <p/>
	 * 
	 * getDateService().getNextMonth(new Date(), "yyyy-MM",2) = 2014-03
	 *
	 * @param beginDate 지정 일자
	 * @param pattern 출력 패턴
	 * @param plus 카운트 예) 2달은 2, 3달은 3
	 * @return String 다음날 날짜 스트링
	 * @since 2015. 3. 3
	 */
	public static String getNextMonth(final Date beginDate, final String pattern, int plus) {
		DateTime beginDateTime = new DateTime(beginDate).plusMonths(plus);

		String results = beginDateTime.toString(pattern);

		return results;
	}

	/**
	 * 시작일자 와 마지막일자를 계산해서 몇일 차이가 나는지 출력.
	 * 
	 * <p/>
	 * 
	 * Date d1 = getDateService().createDate("20140102142323",
	 * "yyyyMMddHHmmss");<br/>
	 * Date d2 = getDateService().createDate("20150102142323",
	 * "yyyyMMddHHmmss");<br/>
	 *
	 * log.debug(getDateService().getDurationDate(d1, d2)) = 365<br/>
	 *
	 * @param beginDate 시작일자
	 * @param endDate 마지막 일자
	 * @return Long 차이난 일의 수
	 * @since 2015. 3. 3
	 */
	public static long getDurationDate(Date beginDate, Date endDate) {

		DateTime beginDateTime = new DateTime(beginDate);
		DateTime endDateTime = new DateTime(endDate);

		Duration duration = new Duration(beginDateTime, endDateTime);
		return duration.getStandardDays();
	}

	/**
	 * 시작일자 와 마지막일자를 계산해서 몇 시간 차이가 나는지 출력.<br/>
	 * 만약 11시 32분 과 12시 31분일 경우 0을 리턴 한다.<br/>
	 * 하지만 11시 32분 과 12시 32분일 경우 1을 리턴 한다.
	 * 
	 * <p/>
	 * 
	 * Date d1 = getDateService().createDate("20140102142323",
	 * "yyyyMMddHHmmss");<br/>
	 * Date d2 = getDateService().createDate("20150102142323",
	 * "yyyyMMddHHmmss");<br/>
	 *
	 * log.debug(getDateService().getDurationDate(d1, d2)) = 24<br/>
	 *
	 * @param beginDate 시작일자
	 * @param endDate 마지막 일자
	 * @return Long 차이난 시간
	 * @since 2015. 3. 3
	 */
	public static long getDurationHour(Date beginDate, Date endDate) {

		DateTime beginDateTime = new DateTime(beginDate);
		DateTime endDateTime = new DateTime(endDate);

		Duration duration = new Duration(beginDateTime, endDateTime);
		return duration.getStandardHours();
	}
	
	/**
	 * 지정된 일자 만큼 더한 날짜를 리턴 한다..
	 * 
	 * <p/>
	 * 
	 * Date d1 = getDateService().createDate("20140101142423",
	 * "yyyyMMddHHmmss"); getDateService().plusDate(d1, 2) =
	 * "Fri Jan 03 14:24:23 KST 2014"
	 *
	 * @param date 지정된 일자
	 * @param plus 추가할 일자
	 * @return Date 추가 일자가 반영된 날짜
	 * @since 2015. 3. 3
	 */
	public static Date plusDate(Date date, int plus) {

		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.plusDays(plus);
		return dateTime.toDate();
	}

	/**
	 * 지정된 년(year) 만큼 더한 날짜를 리턴 한다..
	 *
	 * <p/>
	 *
	 * Date d1 = getDateService().createDate("20140101142423",
	 * "yyyyMMddHHmmss"); getDateService().plusDate(d1, 2) =
	 * "Fri Jan 03 14:24:23 KST 2014"
	 *
	 * @param date 지정된 일자
	 * @param plus 추가할 일자
	 * @return Date 추가 일자가 반영된 날짜
	 * @since 2015. 3. 3
	 */
	public static Date plusYears(Date date, int plus) {

		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.plusYears(plus);
		return dateTime.toDate();
	}

	/**
	 * 지정된 월(month) 만큼 더한 날짜를 리턴 한다..
	 *
	 * <p/>
	 *
	 * Date d1 = getDateService().createDate("20140101142423",
	 * "yyyyMMddHHmmss"); getDateService().plusDate(d1, 2) =
	 * "Fri Jan 03 14:24:23 KST 2014"
	 *
	 * @param date 지정된 일자
	 * @param plus 추가할 일자
	 * @return Date 추가 일자가 반영된 날짜
	 * @since 2015. 3. 3
	 */
	public static Date plusMonths(Date date, int plus) {

		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.plusMonths(plus);
		return dateTime.toDate();
	}

	/**
	 * 지정된 분(min) 만큼 더한 시간을 리턴
	* <pre>
	*
	* </pre>
	* @param date
	* @param min
	* @return
	* @since 2015. 7. 13.
	 */
	public static Date plusMinutes(Date date, int min) {

		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.plusMinutes(min);
		return dateTime.toDate();
	}

	/**
	 * 지정된 일자 만큼 뺀 날짜를 리턴 한다..
	 *
	 * <p/>
	 *
	 * Date d1 = getDateService().createDate("20140101142423",
	 * "yyyyMMddHHmmss"); getDateService().minusDate(d1, 2) =
	 * "Fri Jan 03 14:24:23 KST 2014"
	 *
	 * @param date 지정된 일자
	 * @param days 뺄 일자
	 * @return Date 추가 일자가 반영된 날짜
	 * @since 2016. 4. 6.
	 */
	public static Date minusDate(Date date, int days) {

		DateTime dateTime = new DateTime(date);
		dateTime = dateTime.minusDays(days);
		return dateTime.toDate();
	}


	/**
	 * 지정된 날짜의 마지막 일자를 리턴 한다.
	 * 
	 * <p/>
	 *  
	 *  getDateService().daysOfMonth(2015, 2) = 28 
	 *
	 * @param year 년
	 * @param month 월
	 * @return Int 마지막 일자
	 * @since 2015. 3. 3
	 */
	public static int daysOfMonth(int year, int month) {
		DateTime dateTime = new DateTime(year, month, 14, 12, 0, 0, 000);
		return dateTime.dayOfMonth().getMaximumValue();
	}
	
	/**
	 * 날짜 오브젝트를 패턴에 맞는 형식으로 스트링 변환 한다.
	 * 
	 * <p/>
	 * 
	 * 
	 * @param dateTime 데이트 오브젝트
	 * @param pattern 날짜 패턴
	 * @return String 변환된 텍스트
	 * @since 2015. 3. 3
	 */
	private static String parseFormat(DateTime dateTime, String pattern) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		return dtf.print(dateTime);
	}
	
    /**
     * 현재 년도를 가져온다.
     * 
     * @return String yeay(yyyy)
     */
    public static String getStringCurrentYear() {
        DateTime dateTime = new DateTime();
        String result = String.valueOf(dateTime.getYear());
        return result;
    }
    
    /**
     * 현재 월을 2자리로 채워 가져온다.
     * 
     * @return String month(mm)
     *          - ex 1월 : 01
     *               2월 : 02
     */
    public static String getStringCurrentMonth() {
        DateTime dateTime = new DateTime();
        String result = String.format("%02d", dateTime.getMonthOfYear());
        return result;
    }
    
    /**
     * 현재 일자를 가져온다. (월 기준일)
     * 
     * @return
     */
    public static String getStringCurrentDay() {
        DateTime dateTime = new DateTime();
        String result = String.format("%02d", dateTime.getDayOfMonth());
        return result;
    }
    
    /**
     * 현재 일자를 가져온다. (yyyymmdd)
     * 
     * @return
     */
    public static String getStringCurrentToday() {
        String result = getStringCurrentYear() + getStringCurrentMonth() + getStringCurrentDay();
        return result;
    }
    
    /**
     * 현재 시간을 가져온다. (시분초 hh24miss)
     * 
     * @return
     */
    public static String getStringCurrentHourMinuteSecond() {
        DateTime dateTime = new DateTime();
        String hd = String.format("%02d", dateTime.getHourOfDay());
        String mh = String.format("%02d", dateTime.getMinuteOfHour());
        String sm = String.format("%02d", dateTime.getSecondOfMinute());
        String result = hd + mh + sm;
        return result;
    }
    
    /**
     * 해당 날짜의 시작 시간 세팅(Start Of Date)
     */
    public static Date getStartOfDate(Date date) {
    	Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    
        return calendar.getTime();
    }
    
    /**
     * 해당 날짜의 마지막 시간 세팅(End Of Date)
     */
    public static Date getEndOfDate(Date date) {
    	Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    
        return calendar.getTime();
    }
    
    public static int getCurrentDateOfQuater() {
		return quarterYear(currentMonth());
	}
	
	public static String getLastQuaterDayOfCurrent() {
		try{
			String month = LAST_MONTH_OF_QUATER [quarterYear(currentMonth()) - 1];
			String yyyy = getCurrentDateString("yyyy");
			return lastDayOfMonth(yyyy + month + "01");
		}catch(Exception e){
			return "";
		}
	}
	
	public static int currentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1; // 현재 월만 반환
	}
	
	public static int quarterYear(int month) {
		return (int) Math.ceil( month / 3.0 );
	}
	
	public static String getCurrentDateString(String pattern) {
		return convertToString(getCurrentTimeStamp(), pattern);
	}
	
	public static Timestamp getCurrentTimeStamp() {
		Calendar cal = new GregorianCalendar();
		Timestamp result = new Timestamp(cal.getTime().getTime());
		return result;
	}
	
	public static String convertToString(Timestamp dateData, String pattern) {
		return convertToString(dateData, pattern, java.util.Locale.KOREA);
	}
	
	public static String convertToString(	Timestamp dateData,
				String pattern,
				java.util.Locale locale) {
		if (dateData == null) {
		return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
		formatter.applyPattern( pattern );
		return formatter.format(dateData);
	}
	
	public static String lastDayOfMonth(String src) throws ParseException {
		return lastDayOfMonth(src, "yyyyMMdd");
	}
    
	public static String lastDayOfMonth(String src, String format)
			throws ParseException {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
			java.util.Date date = check(src, format);
			java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
			java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
			int year = Integer.parseInt(yearFormat.format(date));
			int month = Integer.parseInt(monthFormat.format(date));
			int day = lastDay(year, month);
			java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
			java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
			String tempDate = String.valueOf(fourDf.format(year))
									+ String.valueOf(twoDf.format(month))
									+ String.valueOf(twoDf.format(day));
			java.util.Date targetDate = check(tempDate, "yyyyMMdd");
			return formatter.format(targetDate);
		}
	
	private static java.util.Date check(String s, String format)
			throws java.text.ParseException {

			if (s == null)
				throw new java.text.ParseException("date string to check is null",	0);

			if (format == null)
				throw new java.text.ParseException("format string to check date is null", 0);

			java.text.SimpleDateFormat formatter =	new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
			java.util.Date date = null;

			try {
				date = formatter.parse(s);
			} catch (java.text.ParseException e) {
				throw new java.text.ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
			}

			if (!formatter.format(date).equals(s))
				throw new java.text.ParseException("Out of bound date:\""	+ s + "\" with format \""	+ format + "\"", 0);

			return date;
		}
	
	private static int lastDay(int year, int month)
			throws java.text.ParseException {
			int day = 0;
			switch (month) {
				case 1 :
				case 3 :
				case 5 :
				case 7 :
				case 8 :
				case 10 :
				case 12 :
					day = 31;
					break;
				case 2 :
					if ((year % 4) == 0) {
						if ((year % 100) == 0 && (year % 400) != 0) {
							day = 28;
						} else {
							day = 29;
						}
					} else {
						day = 28;
					}
					break;
				default :
					day = 30;
			}
			return day;
		}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
