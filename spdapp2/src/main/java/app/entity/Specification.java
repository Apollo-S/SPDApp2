package app.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "specification")
public class Specification extends UrlEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "agreement_id")
	private Agreement agreement;
	
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
	
	public Specification() {
	}

	public Specification(Agreement agreement, Integer specificationNumber, Date dateStart) {
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
