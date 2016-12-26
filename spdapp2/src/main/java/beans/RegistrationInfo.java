package beans;

import java.io.Serializable;
import java.sql.Date;

public class RegistrationInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String description;
	private Date dated;

	public RegistrationInfo() {
	}

	public RegistrationInfo(String description, Date dated) {
		this.description = description;
		this.dated = dated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDated() {
		return dated;
	}

	public void setDated(Date dated) {
		this.dated = dated;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
