package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class SPD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	Integer id;
	
	@Column(name = "surname")
	String surname;
	
	@Column(name = "firstname")
	String firstname;
	
	@Column(name = "lastname")
	String lastname;
	
	@Column(name = "alias")
	String alias;
	
	@Column(name = "inn")
	String inn;
	
	@Column(name = "passport")
	String passport;
	
	@Column(name = "address_id")
	Integer addressId;
	
	@Column(name = "registration_info_id")
	Integer registrationInfoId;

	public SPD() {
	}

	public SPD(String surname, String firstname, String lastname, String alias, String inn, String passport,
			int addressId, int registrationInfoId) {
		super();
		this.surname = surname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.alias = alias;
		this.inn = inn;
		this.passport = passport;
		this.addressId = addressId;
		this.registrationInfoId = registrationInfoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getRegistrationInfoId() {
		return registrationInfoId;
	}

	public void setRegistrationInfoId(int registrationInfoId) {
		this.registrationInfoId = registrationInfoId;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
