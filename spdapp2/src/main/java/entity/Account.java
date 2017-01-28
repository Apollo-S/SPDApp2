package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "spd_id")
	private Integer spdId;
	
	@Column(name = "account_number")	
	private String accountNumber;
	
	@Column(name = "mfo")		
	private String mfo;
	
	@Column(name = "bank_name")	
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
