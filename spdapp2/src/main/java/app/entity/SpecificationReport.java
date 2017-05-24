package app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class SpecificationReport extends UrlEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Agreement agreement;
	private Integer specificationNumber;
	private Date dateStart;
	private Date dateFinish;
	private Double specificationSum = 0.0;
	private Integer configuringHours = 0;
	private Integer programmingHours = 0;
	private Integer architectingHours = 0;
	
	public SpecificationReport() {
	}

	public SpecificationReport(Agreement agreement, Integer specificationNumber, Date dateStart) {
		this.agreement = agreement;
		this.specificationNumber = specificationNumber;
		this.dateStart = dateStart;
	}

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	public Integer getSpecificationNumber() {
		return specificationNumber;
	}

	public void setSpecificationNumber(Integer specificationNumber) {
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

	public Double getSpecificationSum() {
		return specificationSum;
	}

	public void setSpecificationSum(Double specificationSum) {
		this.specificationSum = specificationSum;
	}

	public Integer getConfiguringHours() {
		return configuringHours;
	}

	public void setConfiguringHours(Integer configuringHours) {
		this.configuringHours = configuringHours;
	}

	public Integer getProgrammingHours() {
		return programmingHours;
	}

	public void setProgrammingHours(Integer programmingHours) {
		this.programmingHours = programmingHours;
	}

	public Integer getArchitectingHours() {
		return architectingHours;
	}

	public void setArchitectingHours(Integer architectingHours) {
		this.architectingHours = architectingHours;
	}
	
}
