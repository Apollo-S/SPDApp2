package utils;

import java.sql.Date;

public class BeanUtil {
	
	public Date requestedDateFormatter (String requestedDate) {
		if (requestedDate.equals(""))  {
			return null;
		}
		return Date.valueOf(requestedDate);
	}

}
