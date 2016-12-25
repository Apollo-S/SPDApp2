package beans;

import java.io.Serializable;

public class SPD implements Serializable {

	private static final long serialVersionUID = 1L;

	int id;
	String surname;
	String firstname;
	String lastname;
	String alias;
	String inn;
	String passport;

	public SPD() {
	}

	public SPD(String surname, String firstname, String lastname, String alias, String inn, String passport) {
		this.surname = surname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.alias = alias;
		this.inn = inn;
		this.passport = passport;
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

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
