package entity;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int spdId;
	private String accountNumber;
	private String mfo;
	private String bankName;

	public Account() {
	}

	public Account(int spdId, String accountNumber, String mfo, String bankName) {
		this.spdId = spdId;
		this.accountNumber = accountNumber;
		this.mfo = mfo;
		this.bankName = bankName;
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getMfo() {
		return mfo;
	}

	public void setMfo(String mfo) {
		this.mfo = mfo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
