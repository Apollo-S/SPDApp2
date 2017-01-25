package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import entity.Specification;
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
	
	@Test
	public void testSpecificationEditSettersValues() {
		Specification specification = new Specification();
		specification.setAgreementId(1);
		specification.setAgreementTarifId(2);
		specification.setSpecificationNumber(345);
		specification.setDateStart(Date.valueOf("2017-02-02"));
		specification.setDateFinish(Date.valueOf("2020-02-02"));
		specification.setSpecificationSum(23000d);
		specification.setConfiguringHours(140);
		specification.setProgrammingHours(150);
		specification.setArchitectingHours(160);
		specification.setCompanyId(1);
		
		Specification changedSpecification = new Specification();
	
		changedSpecification = beanUtils.editSpecificationSetters(specification, 4, 5, 678, 
				(Date.valueOf("2019-06-06")), (Date.valueOf("2020-02-02")), 54000d, 67, 78, 89, 1);
		
		assertEquals(specification, (changedSpecification));
	}

}
