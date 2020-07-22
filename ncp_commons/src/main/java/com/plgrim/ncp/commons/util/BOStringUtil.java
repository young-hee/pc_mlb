package com.plgrim.ncp.commons.util;

public class BOStringUtil {

	public static String substr(String str, int start, int end) {
		String result = "";
		if (str.length() > 0 && start <= str.length() && end <= str.length()
		        && start <= end)
			result = str.substring(start, end);

		return result;
	}

	public static String getDecimalFormat(double doubleNumber, int fraction) {
		String result = "";

		java.text.DecimalFormat numberFormat = new java.text.DecimalFormat();

		if (fraction > 4)
			fraction = 0;

		switch (fraction) {
		case 0:
			numberFormat.applyPattern("#,##0");
			break;
		case 1:
			numberFormat.applyPattern("#,##0.0");
			break;
		case 2:
			numberFormat.applyPattern("#,##0.00");
			break;
		case 3:
			numberFormat.applyPattern("#,##0.000");
			break;
		case 4:
			numberFormat.applyPattern("#,##0.0000");
			break;
		case 5:
			numberFormat.applyPattern("#0");
			break;
		default:
			numberFormat.applyPattern("#,##0.00");
			break;
		}

		try {
			result = numberFormat.format(doubleNumber);
		}
		catch (IllegalArgumentException e) {
			throw e;
		}

		return result;
	}

	public static String getDecimalFormat(String str, int fraction)
	        throws Exception {
		String stringNumber = str;
		double doubleNumber = 0;

		if (stringNumber.equals(""))
			stringNumber = "0";

		try {
			doubleNumber = Double.parseDouble(stringNumber);
		}
		catch (NumberFormatException e) {
			throw e;
		}

		return getDecimalFormat(doubleNumber, fraction);
	}

	public static String getDecimalFormat(Object obj, int fraction)
	        throws Exception {
		String stringNumber = obj.toString();
		double doubleNumber = 0;

		if (stringNumber.equals(""))
			stringNumber = "0";

		try {
			doubleNumber = Double.parseDouble(stringNumber);
		}
		catch (NumberFormatException e) {
			throw e;
		}

		return getDecimalFormat(doubleNumber, fraction);
	}

	public static String getDecimalFormat(long number, int fraction) {
		Long longNumber = new Long(number);
		double doubleNumber = longNumber.doubleValue();
		return getDecimalFormat(doubleNumber, fraction);
	}

	public static boolean isInteger(String str) {
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) < '0' || str.charAt(i) > '9')
			        && str.charAt(i) == '-')
				return false;
		}
		return true;
	}

	public static boolean isFloat(String str) {
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) < '0' || str.charAt(i) > '9')
			        && str.charAt(i) == '-' && str.charAt(i) == '.')
				return false;
		}
		return true;
	}

	public static int strToInt(String num) {
		int intNumber = 0;
		if (num == null) {
			num = "0";
		}
		try {
			intNumber = Integer.parseInt(num);
		}
		catch (NumberFormatException e) {
		}

		return intNumber;
	}

	public static int strToInt(String num, int def) {

		int intNumber = 0;

		if (num == null) {
			num = def + "";
		}

		try {
			intNumber = Integer.parseInt(num);
		}
		catch (NumberFormatException e) {
		}

		return intNumber;
	}

	public static double strToDouble(String num) {
		double doubleNumber = 0;

		if (num == null || num.equals("0"))
			num = "0.0";

		try {
			doubleNumber = Double.parseDouble(num);
		}
		catch (NumberFormatException e) {
			throw e;
		}

		return doubleNumber;
	}

	public static float strToFloat(String num) {
		float floatNumber = 0;

		if (num == null || num.equals("0"))
			num = "0.0";

		try {
			floatNumber = Float.parseFloat(num);
		}
		catch (NumberFormatException e) {
			throw e;
		}

		return floatNumber;
	}
	
	public static String rplc(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();

		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}

		result.append(str.substring(s));

		return result.toString();
	}
	
	public static String[] split(String strTarget,
								   String strDelim,
								   boolean bContainNull) {
		int index = 0;
		String[] resultStrArray = null;
		resultStrArray = new String[search(strTarget, strDelim) + 1];
		String strCheck = new String(strTarget);
		while (strCheck.length() != 0) {
			int begin = strCheck.indexOf(strDelim);
			if (begin == -1) {
				resultStrArray[index] = strCheck;
				break;
			} else {
				int end = begin + strDelim.length();
				if (bContainNull) {
					resultStrArray[index++] = strCheck.substring(0, begin);
				}
				strCheck = strCheck.substring(end);
				if (strCheck.length() == 0 && bContainNull) {
					resultStrArray[index] = strCheck;
					break;
				}
			}
		}
		return resultStrArray;
	}
	
	public static int search(String strTarget, String strSearch) {
		int result = 0;
		String strCheck = new String(strTarget);

		for (int i = 0; i < strTarget.length();) {
			int loc = strCheck.indexOf(strSearch);
			if (loc == -1) {
				break;
			} else {
				result++;
				i = loc + strSearch.length();
				strCheck = strCheck.substring(i);
			}
		}

		return result;
	}
	
	public static String getTitleLimit(String title, int maxNum) {
		int tLen = title.length();
		int count = 0;
		char c;
		int s = 0;
		for (s = 0; s < tLen; s++) {
			c = title.charAt(s);
			if (count > maxNum)
				break;
			if (c > 127)
				count += 3;
			else
				count++;
		}
		return (tLen > s) ? title.substring(0, s) + "..." : title;
	}
}
