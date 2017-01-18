package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import beans.Specification;
import utils.BeanUtils;

import java.sql.Date;

public class BeanUtilsTest {

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
	
		changedSpecification = BeanUtils.editSpecificationSetters(specification, 4, 5, 678, 
				(Date.valueOf("2019-06-06")), (Date.valueOf("2020-02-02")), 54000d, 67, 78, 89, 1);
		
		assertEquals(specification, (changedSpecification));
	}

}
