package entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Specification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "agreement_id")
	private Integer agreementId;
	
	@Column(name = "agreement_tarif_id")
	private Integer agreementTarifId;
	
	@Column(name = "specification_number")
	private Integer specificationNumber;
	
	@Column(name = "date_start")
	private Date dateStart;
	
	@Column(name = "date_finish")
	private Date dateFinish;
	
	@Column(name = "specification_sum")
	private Double specificationSum;
	
	@Column(name = "configuring_hours")
	private Integer configuringHours;
	
	@Column(name = "programming_hours")
	private Integer programmingHours;
	
	@Column(name = "architecting_hours")
	private Integer architectingHours;
	
	@Column(name = "company_id")
	private Integer companyId;

	public Specification() {
	}

	public Specification(int agreementId, int agreementTarifId, int specificationNumber, Date dateStart,
			Date dateFinish, double specificationSum, int configuringHours, int programmingHours, int architectingHours,
			int companyId) {
		this.agreementId = agreementId;
		this.agreementTarifId = agreementTarifId;
		this.specificationNumber = specificationNumber;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.specificationSum = specificationSum;
		this.configuringHours = configuringHours;
		this.programmingHours = programmingHours;
		this.architectingHours = architectingHours;
		this.companyId = companyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getAgreementTarifId() {
		return agreementTarifId;
	}

	public void setAgreementTarifId(int agreementTarifId) {
		this.agreementTarifId = agreementTarifId;
	}

	public int getSpecificationNumber() {
		return specificationNumber;
	}

	public void setSpecificationNumber(int specificationNumber) {
		this.specificationNumber = specificationNumber;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public double getSpecificationSum() {
		return specificationSum;
	}

	public void setSpecificationSum(double specificationSum) {
		this.specificationSum = specificationSum;
	}

	public int getConfiguringHours() {
		return configuringHours;
	}

	public void setConfiguringHours(int configuringHours) {
		this.configuringHours = configuringHours;
	}

	public int getProgrammingHours() {
		return programmingHours;
	}

	public void setProgrammingHours(int programmingHours) {
		this.programmingHours = programmingHours;
	}

	public int getArchitectingHours() {
		return architectingHours;
	}

	public void setArchitectingHours(int architectingHours) {
		this.architectingHours = architectingHours;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
}
