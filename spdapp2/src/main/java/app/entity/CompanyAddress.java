package app.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company_address")
public class CompanyAddress extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@Column(name = "presentation")
	private String presentation;

	@Column(name = "date_start")
	private Date dateStart;

	public CompanyAddress() {
	}

	public CompanyAddress(Company company, String presentation, Date dateStart) {
		this.company = company;
		this.presentation = presentation;
		this.dateStart = dateStart;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

}
