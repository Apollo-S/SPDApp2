package utils;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import utils.BeanUtil;
import java.sql.Date;

public class BeanUtilTest {
	
	BeanUtil beanUtils = new BeanUtil();
	
	@Test
	public void testRequestedDateFormatterWithTextDate() {
		String textDate = "2017-01-01";
		Date sqlDate = Date.valueOf(textDate);
		assertThat(beanUtils.requestedDateFormatter(textDate), is(sqlDate));
	}
	
	@Test
	public void testRequestedDateFormatterWithEmptyValue() {
		String textDate = "";
		Date sqlDate = null;
		assertThat(beanUtils.requestedDateFormatter(textDate), is(sqlDate));
	}
	
}
