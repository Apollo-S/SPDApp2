package entity;

import java.io.Serializable;
import java.sql.Date;

public class AgreementTarif implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int agreementId;
	private double configuring;
	private double programming;
	private double architecting;
	private Date dateStart;

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public AgreementTarif() {
	}

	public AgreementTarif(int agreementId, double configuring, double programming, double architecting,
			Date datestart) {
		super();
		this.agreementId = agreementId;
		this.configuring = configuring;
		this.programming = programming;
		this.architecting = architecting;
		this.dateStart = datestart;
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

	public double getConfiguring() {
		return configuring;
	}

	public void setConfiguring(double configuring) {
		this.configuring = configuring;
	}

	public double getProgramming() {
		return programming;
	}

	public void setProgramming(double programming) {
		this.programming = programming;
	}

	public double getArchitecting() {
		return architecting;
	}

	public void setArchitecting(double architecting) {
		this.architecting = architecting;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
