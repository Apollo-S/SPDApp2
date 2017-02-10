package app.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "specification")
public class Specification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

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
	
	@Column(name = "company_id") // TODO  @OneToOne relations
	private Integer companyId;

	public Specification() {
	}

	public Specification(Agreement agreement, Integer specificationNumber, Date dateStart,
			Date dateFinish, Double specificationSum, Integer configuringHours, Integer programmingHours, Integer architectingHours,
			Integer companyId) {
		this.agreement = agreement;
		this.specificationNumber = specificationNumber;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.specificationSum = specificationSum;
		this.configuringHours = configuringHours;
		this.programmingHours = programmingHours;
		this.architectingHours = architectingHours;
		this.companyId = companyId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
