package app.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import app.entity.Address;
import app.entity.RegistrationInfo;

@Entity
@Table(name = "spd")
public class SPD extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "surname")
	private String surname;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "alias")
	private String alias;

	@Column(name = "inn")
	private String inn;

	@Column(name = "passport")
	private String passport;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "registration_info_id")
	private RegistrationInfo registrationInfo;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "spd", orphanRemoval = true) // TODO enable LAZY fetchType
	@OrderBy("id ASC")
	private Set<Account> accounts;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "spd", orphanRemoval = true)
	@OrderBy("id ASC")
	private Set<Agreement> agreements;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "spd", orphanRemoval = true)
	@OrderBy("id ASC")
	private Set<Payment> payments;

	public SPD() {
	}

	public SPD(String surname, String firstname, String lastname, String alias, String inn, String passport,
			Address address, RegistrationInfo registrationInfo) {
		super();
		this.surname = surname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.alias = alias;
		this.inn = inn;
		this.passport = passport;
		this.address = address;
		this.registrationInfo = registrationInfo;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public RegistrationInfo getRegistrationInfo() {
		return registrationInfo;
	}

	public void setRegistrationInfo(RegistrationInfo registrationInfo) {
		this.registrationInfo = registrationInfo;
	}
	
	public Set<Agreement> getAgreements() {
		return agreements;
	}

	public void setAgreements(Set<Agreement> agreements) {
		this.agreements = agreements;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

}
