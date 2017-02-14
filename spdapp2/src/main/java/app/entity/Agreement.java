package app.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "agreement")
public class Agreement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "spd_id")
	private SPD spd;

	@Column(name = "number")
	private String number;

// TODO	 use Calendar class with annotation @Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "agreement")
	@OrderBy("id ASC")
	private Set<AgreementTarif> tarifs;

	@OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "agreement")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SPD getSpd() {
		return spd;
	}

	public void setSpd(SPD spd) {
		this.spd = spd;
	}

	public void setSpdId(SPD spd) {
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

}
