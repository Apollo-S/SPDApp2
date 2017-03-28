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
@Table(name = "agreement")
public class Agreement extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "spd_id")
	private SPD spd;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@Column(name = "number")
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "agreement")
	@OrderBy("id ASC")
	private Set<AgreementTarif> tarifs;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "agreement")
	@OrderBy("id ASC")
	private Set<Specification> specifications;

	public Agreement() {
	}

	public Agreement(SPD spd, String number, Date dateStart) {
		this.spd = spd;
		this.number = number;
		this.dateStart = dateStart;
	}

	public Set<AgreementTarif> getTarifs() {
		return tarifs;
	}

	public void setTarifs(Set<AgreementTarif> tarifs) {
		this.tarifs = tarifs;
	}

	public Set<Specification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<Specification> specifications) {
		this.specifications = specifications;
	}

	public SPD getSpd() {
		return spd;
	}

	public void setSpd(SPD spd) {
		this.spd = spd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
