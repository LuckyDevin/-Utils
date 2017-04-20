package com.ccx.credit.util;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * this class manipulate strings
 */

public final class StringWorker {

	static String[] mMonthNames = { "January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };
	

	public static final  String sqlB = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_BASIC_INFO WHERE CARD_CODE = ?";
	
	public static final  String sqlP = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_EDUCATION WHERE  CARD_CODE = ?";
	
	
	public static final  String sqlE = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_PROF_STATUS WHERE  CARD_CODE = ?";
	
	public static final  String sqlS = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_SOCIAL_SECUR WHERE  CARD_CODE = ?";
	//个人信用卡表
	public static final  String sqlC = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_CREDIT_CARD WHERE  CARD_CODE = ? ";
	//个人信贷记录表
	public static final  String sqlL = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_LOAN_RECORDS WHERE  CARD_CODE = ? ";
	//违约记录表
	public static final  String sqlD = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_DEFA_REC WHERE  CARD_CODE = ? ";
	//个人收入表
	public static final  String sqlI = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_INCOME_STATE WHERE  CARD_CODE = ? ";
	
	//个人支出表
	public static final  String sqlex = "SELECT COUNT(1) FROM PCCREDIT.TB_PC_EXPENSES  WHERE CARD_CODE = ?"; 

	 
	
	
	
	public static String DECIMAL_SYMBOLS = "0123456789";
	public static String LETTER_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final DecimalFormat mDecimalFormat = new DecimalFormat(
			"####0.00");

	private StringWorker() {
	}

	/**
	 * for example, value="abcd", padding_char='0', int=6 result: value="00abcd"
	 */
	public static String padLeft(String value, char padding_char, int count) {
		while (value.length() < count) {
			value = padding_char + value;
		}
		return value;
	}

	/**
	 * for example, value="abcd", padding_char='0', int=6 result: value="abcd00"
	 */
	public static String padRight(String value, int count,
			char padding_character) {
		while (value.length() < count) {
			value = value + padding_character;
		}
		return value;
	}

	/**
	 * alist=[a,b,c,d] createList(alist,"?")=a?b?c?d
	 */
	public static String createList(Collection<String> collection,
			String delimiter) {
		StringBuffer sb = new StringBuffer();
		boolean isFirst = true;
		Iterator<String> iterator = collection.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
			if (!isFirst) {
				sb.append(delimiter);
			}
			isFirst = false;
			sb.append(o);
		}
		return sb.toString();
	}

	public static String createList(int[] collection, String delimiter) {
		StringBuffer sb = new StringBuffer();
		boolean isFirst = true;
		for (int i = 0; i < collection.length; i++) {
			if (!isFirst) {
				sb.append(delimiter);
			}
			isFirst = false;
			sb.append(collection[i]);
		}
		return sb.toString();
	}

	public static String createList(String[] collection, String delimiter) {
		StringBuffer sb = new StringBuffer();
		boolean isFirst = true;
		for (int i = 0; i < collection.length; i++) {
			if (!isFirst) {
				sb.append(delimiter);
			}
			isFirst = false;
			sb.append(collection[i]);
		}
		return sb.toString();
	}

	/**
	 * Get the integer value of a string. If it is not found (null or empty) or
	 * any exceptions occur, the default value will be returned instead. It
	 * supports negative integers too.
	 * 
	 * @param text
	 *            The text string.
	 * @return The value, or default if it's not a int.
	 */
	public static int parseInt(String text, int defaultValue) {
		if (text == null || text.trim().length() == 0) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException ex) {
			return defaultValue;
		}
	}
	public static Long parseInt(String text, Long defaultValue) {
		if (text == null || text.trim().length() == 0) {
			return defaultValue;
		}
		try {
			return Long.parseLong(text);
		} catch (NumberFormatException ex) {
			return defaultValue;
		}
	}

	/**
	 * Get the integer value of a string. This will only consider the leading
	 * characters that are decimal digits.
	 * 
	 * @param text
	 *            The text string.
	 * @return The value, or zero if it's not a number.
	 */
	public static int evalInt(String text) {
		if (text == null) {
			return 0;
		}
		int value = 0;
		boolean negate = false;
		int startIndex = 0;
		if (text.length() > 0) {
			if (text.charAt(0) == '-') {
				startIndex = 1;
				negate = true;
			}
		}
		for (int index = startIndex; index < text.length(); index++) {
			char c = text.charAt(index);
			if (Character.getType(c) != Character.DECIMAL_DIGIT_NUMBER) {
				break;
			} else {
				value *= 10;
				value += (c - '0');
			}
		}
		return negate ? value * -1 : value;
	}

	/**
	 * Get the real value of a string. This will only consider the leading
	 * characters that are decimal digits, and any decimal digits after a
	 * decimal point.
	 * 
	 * @param text
	 *            The text string.
	 * @return The value, or zero if it's not a number.
	 */
	public static double evalDouble(String text) {

		if (text == null) {
			return 0;
		}
		double value = 0;
		char c = 0;
		boolean negate = false;

		int startIndex = 0;
		if (text.length() > 0) {
			if (text.charAt(0) == '-') {
				startIndex = 1;
				negate = true;
			}
		}
		int index = startIndex;
		for (; index < text.length(); index++) {
			c = text.charAt(index);
			if (Character.getType(c) != Character.DECIMAL_DIGIT_NUMBER) {
				break;
			} else {
				value *= 10;
				value += (c - '0');
			}
		}
		if (c == '.') {
			double factor = 1.0;
			index++;
			for (; index < text.length(); index++) {
				c = text.charAt(index);
				if (Character.getType(c) != Character.DECIMAL_DIGIT_NUMBER) {
					break;
				} else {
					factor *= 0.1;
					value += (factor * (c - '0'));
				}
			}
		}
		return negate ? value * -1 : value;
	}

	/**
	 * Get the long value of a string This will only consider the leading
	 * characters that are decimal digits
	 * 
	 * @param text
	 *            The text string.
	 * @return The value, or zero if it's not a number.
	 */
	public static long evalLong(String text) {
		if (text == null) {
			return 0;
		}
		long value = 0;
		boolean negate = false;
		int startIndex = 0;
		if (text.length() > 0) {
			if (text.charAt(0) == '-') {
				startIndex = 1;
				negate = true;
			}
		}
		for (int index = startIndex; index < text.length(); index++) {
			char c = text.charAt(index);
			if (Character.getType(c) != Character.DECIMAL_DIGIT_NUMBER) {
				break;
			} else {
				value *= 10;
				value += (c - '0');
			}
		}
		return negate ? value * -1 : value;
	}

	/**
	 * Evaluate the text as if it were a boolean value. This method understands
	 * "on", "true" and "yes" to be positive, everything else is assumed to be
	 * negative.
	 * 
	 * @param text
	 *            The text to evaluate.
	 * @return True or false.
	 */
	public static boolean evalBoolean(String text) {
		if (text == null) {
			return false;
		}
		text = text.toLowerCase();
		if ("on".equals(text) || "yes".equals(text) || "true".equals(text)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This will return the string representation of the object. If the value is
	 * null, this will return the empty string.
	 * 
	 * @param text
	 *            The object to examine.
	 * @return The string representation of the object, or the empty string.
	 */
	public static String notNull(Object text) {
		return text == null ? "" : text.toString();
	}

	/**
	 * for debug purpose, return all the getmethods in a String
	 * 
	 * @param theObject
	 *            The object to debug.
	 * @return Some text containing the properties of the object.
	 */
	public static String createDebugRepresentationOfDomainObject(
			Object theObject) {
		StringBuffer sb = new StringBuffer();
		Method[] methods = theObject.getClass().getMethods();
		sb.append("[\n");
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("get")) {
				try {
					String value = ""
							+ methods[i].invoke(theObject, (Object[]) null);
					sb.append(methods[i].getName());
					sb.append("=");
					sb.append("[" + value + "]");
					sb.append(",\n");
				} catch (Exception e) {
					// This is debug, we really don't care!
				}
			}
		}
		sb.append("]\n");
		return sb.toString();
	}

	public static void printDebugObject(Object theObject) {
		System.out.println(createDebugRepresentationOfDomainObject(theObject));
	}

	/**
	 * This will evaluate a date string. Dates can be in any of the recognised
	 * formats:
	 * 
	 * The four separators are space, fullstop, slash and minus "2008 Apr 12"
	 * "08.4.12" "2008/04/12" "08-04-12"
	 * 
	 * @param dateString
	 *            The input parameter.
	 * @return The date value.
	 * @throws IllegalArgumentException
	 *             if the date is invalid.
	 */
	public static Date evalDate(String dateString) {
		StringTokenizer tokens = new StringTokenizer(dateString, ".");
		if (tokens.countTokens() != 3) {
			tokens = new StringTokenizer(dateString, "/");
		}
		if (tokens.countTokens() != 3) {
			tokens = new StringTokenizer(dateString, "-");
		}
		if (tokens.countTokens() != 3) {
			tokens = new StringTokenizer(dateString, " ");
		}
		if (tokens.countTokens() != 3) {
			throw new IllegalArgumentException(dateString
					+ " is not a valid date");
		}
		int year = 1;
		int month = 1;
		int day = 1;
		year = StringWorker.evalInt((String) tokens.nextToken());
		if (year < 70) {
			year += 2000;
		} else if (year < 100) {
			year += 1900;
		}
		String monthName = (String) tokens.nextToken();
		month = StringWorker.evalInt(monthName);
		day = StringWorker.evalInt((String) tokens.nextToken());
		if (month == 0) {
			monthName = StringWorker.padRight(monthName.toUpperCase(), 3, ' ')
					.toUpperCase().substring(0, 3);
			int index = 0;
			for (index = 0; index < 12; index++) {
				if (mMonthNames[index].toUpperCase().substring(0, 3).equals(
						monthName)) {
					month = index + 1;
					break;
				}
			}
			if (index == 12) {
				throw new IllegalArgumentException(dateString
						+ " is not a valid date");
			}
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}

	/**
	 * Replace the contents of one string with another
	 * 
	 * @param source
	 *            The whole input string.
	 * @param pattern
	 *            The substring to find.
	 * @param replace
	 *            The value to replace the substring with.
	 * @return A new string with the amendments made.
	 */
	public static String replace(String source, String pattern, String replace) {
		if (source != null) {
			if (pattern == null)
				pattern = "";
			if (replace == null)
				replace = "";
			final int len = pattern.length();
			StringBuffer sb = new StringBuffer();
			int found = -1;
			int start = 0;
			while ((found = source.indexOf(pattern, start)) != -1) {
				sb.append(source.substring(start, found));
				sb.append(replace);
				start = found + len;
			}
			sb.append(source.substring(start));
			return sb.toString();
		} else {
			return "";
		}
	}

	public static void main(String args[]) {
		System.out.println(">>>>>>>>>> ");
	}

	/**
	 * This will convert a number into a string representation of the number in
	 * a number base defined by the symbolset.
	 * 
	 * @param value
	 *            The value to calculate.
	 * @param symbolset
	 *            The digit symbols to use (first denotes zero).
	 * @return A string representing the number in the symbols provided.
	 */
	public static String changeNumberBase(long value, String symbolset) {
		StringBuffer sb = new StringBuffer();
		if (value < 0) {
			value = -value;
		}
		int base = symbolset.length();
		while (value > 0) {
			int v = (int) (value % base);
			value /= base;
			sb.append(symbolset.charAt(v));
		}
		return sb.reverse().toString();
	}

	/**
	 * This will encode the specified value into CSV.
	 * 
	 * @param value
	 *            The value to encode.
	 * @return The CSV-encoded version.
	 */
	public static String encodeCsv(Object value) {
		if (value == null) {
			return "";
		} else {
			String s = value.toString();
			return s.replaceAll("\"", "\"\"");
		}
	}

	/**
	 * This will encode a value in CSV. If the 'use quotes' flag is set, the
	 * encoded value will always have quotes round it. This is particularly
	 * useful for things like phone numbers (where Excel might try to convert
	 * them to long integers). It is also important when the value is a number,
	 * but you want to treat it as a string.
	 * 
	 * @param value
	 *            The value to encode.
	 * @param useQuotes
	 *            Force the usage of quotes when the value is a number.
	 * @return The CSV-encoded version.
	 */
	public static String encodeCsv(Object value, boolean useQuotes) {
		if (value == null) {
			return "";
		} else {
			String s = value.toString();
			if (useQuotes) {
				return "\"" + s.replaceAll("\"", "\"\"") + "\"";
			} else {
				return s.replaceAll("\"", "\"\"");
			}
		}
	}

	public static String formatCurrency(double value) {
		return mDecimalFormat.format(value);
	}

	public static String formatDecimal(double value, int decimalPlaces) {
		DecimalFormat decimalFormat = new DecimalFormat("####0."
				+ repeat("0", decimalPlaces));
		return decimalFormat.format(value);
	}

	public static String formatDecimal(double value) {
		DecimalFormat decimalFormat = new DecimalFormat("####0.###");
		return decimalFormat.format(value);
	}

	public static String repeat(String value, int count) {
		StringBuffer sb = new StringBuffer();
		while (count > 0) {
			count--;
			sb.append(value);
		}
		return sb.toString();
	}

	public static String formatTruncate(String value, int maxLength) {
		if (value == null) {
			return "";
		}
		if (value.length() > maxLength) {
			return value.substring(0, maxLength) + "...";
		} else {
			return value;
		}
	}

	public static boolean match(String value, String pattern) {
		return match(value, pattern, true);
	}

	public static boolean match(String value, String pattern,
			boolean caseSensitive) {
		if (value == null && pattern == null) {
			return true;
		}
		if (value == null || pattern == null) {
			return false;
		}
		if (caseSensitive) {
			return value.matches(createPattern(pattern));
		} else {
			return value.toLowerCase().matches(
					createPattern(pattern.toLowerCase()));
		}
	}

	private static String createPattern(String pattern) {
		StringBuffer sb = new StringBuffer("^");
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			switch (c) {
			case '.':
				sb.append("\\.");
				break;
			case '*':
				sb.append(".*");
				break;
			case '?':
				sb.append(".");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		sb.append('$');

		return sb.toString();
	}

	public static boolean isDigit(int c) {
		return c >= '0' && c <= '9';
	}

	public static boolean isLetter(int c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	public static boolean isWhiteSpace(int c) {
		return c == ' ' || c == '\t';
	}

	public final static String getServletPathName(String path) {
		int i = path.lastIndexOf('/');
		if (i == -1) {
			return path;
		} else {
			return path.substring(i + 1);
		}
	}
}
