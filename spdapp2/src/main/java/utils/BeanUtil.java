package utils;

import java.sql.Date;

import entity.Specification;

public class BeanUtil {
	
	public Date requestedDateFormatter (String requestedDate) {
		if (requestedDate.equals(""))  {
			return null;
		}
		return Date.valueOf(requestedDate);
	}
	
	public Specification editSpecificationSetters(
			Specification specification, int agreementId, int agreementTarifId, int specificationNumber, Date dateStart,
			Date dateFinish, double specificationSum, int configuringHours, int programmingHours, int architectingHours, int companyId) {
		specification.setAgreementId(agreementId);
		specification.setAgreementTarifId(agreementTarifId);
		specification.setSpecificationNumber(specificationNumber);
		specification.setDateStart(dateStart);
		specification.setDateFinish(dateFinish);
		specification.setSpecificationSum(specificationSum);
		specification.setConfiguringHours(configuringHours);
		specification.setProgrammingHours(programmingHours);
		specification.setArchitectingHours(architectingHours);
		specification.setCompanyId(companyId);
		return specification;
	}

}
