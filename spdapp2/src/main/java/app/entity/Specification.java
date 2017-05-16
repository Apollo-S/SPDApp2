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

@Entity
@Table(name = "specification")
public class Specification extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "agreement_id")
	private Agreement agreement;

	@Column(name = "specification_number")
	private Integer specificationNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_finish")
	private Date dateFinish;

	@Column(name = "specification_sum", nullable = false)
	private Double specificationSum = 0.0;

	@Column(name = "configuring_hours")
	private Integer configuringHours = 0;

	@Column(name = "programming_hours")
	private Integer programmingHours = 0;

	@Column(name = "architecting_hours")
	private Integer architectingHours = 0;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specification", orphanRemoval = true)
	@OrderBy("part_number ASC")
	private Set<Calculation> calculations;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specification", orphanRemoval = true)
	@OrderBy("id ASC")
	private Set<Job> jobs;

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

	public Set<Calculation> getCalculations() {
		return calculations;
	}

	public void setCalculations(Set<Calculation> calculations) {
		this.calculations = calculations;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

}
