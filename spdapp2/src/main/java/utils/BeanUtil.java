package utils;

import java.sql.Date;

public class BeanUtil {
	
	private static final char MINUS = '-';
	private static final String NON_SPACE = "";
	private static final String SPACE = "\\s";
	private static final String DOT = ".";
	private static final String COMMA = ",";

	public static Date requestedDateFormatter(String requestedDate) {
		if (requestedDate.equals(NON_SPACE))  {
			return null;
		}
		return Date.valueOf(requestedDate);
	}
	
	public static Double convertStringToDouble(String parameter) {
		if (parameter.equals(NON_SPACE)) {
			return null;
		}
		parameter = parameter.replace(COMMA, DOT).replaceAll(SPACE, NON_SPACE);
		if (!BeanUtil.isStringNumeric(parameter)) {
			return null;
		}
		return Double.parseDouble(parameter.replace(COMMA, DOT).replaceAll(SPACE, NON_SPACE));
	}
	
	public static boolean isStringNumeric(String str) {
		char localeMinusSign = MINUS;
		boolean isMinus = str.charAt(0) == localeMinusSign; 
		if ((isMinus && str.length() < 2) || ((!isMinus) && !Character.isDigit(str.charAt(0)))) {
			return false; 
		}
		boolean isDecimalSeparatorFound = false;
		char localeDecimalSeparator = '.';
		for (char c : str.substring(1).toCharArray()) {
			if (!Character.isDigit(c)) {
				if (c == localeDecimalSeparator && !isDecimalSeparatorFound) {
					isDecimalSeparatorFound = true;
					continue;
				}
				return false;
			}
		}
		return true;
	}

}
