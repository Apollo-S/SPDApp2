package beans;

import java.io.Serializable;
import java.sql.Date;

public class SPD implements Serializable {

	private static final long serialVersionUID = 1L;

	int id;
	String surname;
	String firstname;
	String lastname;
	String alias;
	Date birthdate;
	String inn;
	String passport;
	Date employmentDate;
	boolean terminationDateCheck;
	Date terminationDate;

	public SPD() {
	}

	public SPD(String surname, String firstname, String lastname, String alias, Date birthdate, String inn,
			String passport, Date employmentDate, boolean terminationDateCheck, Date terminationDate) {
		this.surname = surname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.alias = alias;
		this.birthdate = birthdate;
		this.inn = inn;
		this.passport = passport;
		this.employmentDate = employmentDate;
		this.terminationDateCheck = terminationDateCheck;
		this.terminationDate = terminationDate;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public boolean isTerminationDateCheck() {
		return terminationDateCheck;
	}

	public void setTerminationDateCheck(boolean terminationDateCheck) {
		this.terminationDateCheck = terminationDateCheck;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

}
