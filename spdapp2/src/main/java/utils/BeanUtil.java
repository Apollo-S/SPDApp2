package utils;

import java.sql.Date;

public class BeanUtil {
	
	public static Date requestedDateFormatter(String requestedDate) {
		if (requestedDate.equals(""))  {
			return null;
		}
		return Date.valueOf(requestedDate);
	}

}
