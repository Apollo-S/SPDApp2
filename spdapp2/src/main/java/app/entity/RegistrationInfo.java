package app.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "registration_info")
public class RegistrationInfo extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "description")
	private String description;

	@Column(name = "dated") // TODO	 use Calendar class with annotation @Temporal(TemporalType.DATE)
	private Date dated;

	public RegistrationInfo() {
	}

	public RegistrationInfo(String description, Date dated) {
		this.description = description;
		this.dated = dated;
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
	
}
