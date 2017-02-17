package app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="spd_id")
	private SPD spd;
	
	@Column(name = "account_number")	
	private String accountNumber;
	
	@Column(name = "mfo")		
	private String mfo;
	
	@Column(name = "bank_name")	
	private String bankName;

	public Account() {
	}

	public Account(SPD spd, String accountNumber, String mfo, String bankName) {
		this.spd = spd;
		this.accountNumber = accountNumber;
		this.mfo = mfo;
		this.bankName = bankName;
	}

	public SPD getSpd() {
		return spd;
	}

	public void setSpd(SPD spd) {
		this.spd = spd;
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
