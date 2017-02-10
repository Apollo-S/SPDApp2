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
@Table(name ="agreement_tarif")
public class AgreementTarif implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "agreement_id")
	private Agreement agreement;

	@Column(name = "configuring")
	private Double configuring;

	@Column(name = "programming")
	private Double programming;

	@Column(name = "architecting")
	private Double architecting;

	@Column(name = "date_start")
	private Date dateStart;

	public AgreementTarif() {
	}

	public AgreementTarif(Agreement agreement, Double configuring, Double programming, Double architecting,
			Date datestart) {
		super();
		this.agreement = agreement;
		this.configuring = configuring;
		this.programming = programming;
		this.architecting = architecting;
		this.dateStart = datestart;
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

	public Double getConfiguring() {
		return configuring;
	}

	public void setConfiguring(Double configuring) {
		this.configuring = configuring;
	}

	public Double getProgramming() {
		return programming;
	}

	public void setProgramming(Double programming) {
		this.programming = programming;
	}

	public Double getArchitecting() {
		return architecting;
	}

	public void setArchitecting(Double architecting) {
		this.architecting = architecting;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

}
