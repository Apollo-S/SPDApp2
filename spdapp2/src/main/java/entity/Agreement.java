package entity;

import java.io.Serializable;
import java.sql.Date;

public class Agreement implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int spdId;
	private String number;
	private Date dateStart;

	public Agreement() {
	}

	public Agreement(int spdId, String number, Date dateStart) {
		this.spdId = spdId;
		this.number = number;
		this.dateStart = dateStart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpdId() {
		return spdId;
	}

	public void setSpdId(int spdId) {
		this.spdId = spdId;
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
