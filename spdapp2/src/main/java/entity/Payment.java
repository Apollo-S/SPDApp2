package entity;

import java.io.Serializable;
import java.sql.Date;

public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int spdId;
	private int paymentTypeId;
	private double value;
	private Date dateStart;
	private Date dateFinish;

	public Payment() {
	}

	public Payment(int spdId, int paymentTypeId, double value, Date dateStart, Date dateFinish) {
		this.spdId = spdId;
		this.paymentTypeId = paymentTypeId;
		this.value = value;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
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

	public int getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
