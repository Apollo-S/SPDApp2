package app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "esv_tax")
public class ESVTax extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "value")
	private Double value;

	@Column(name = "date_start")
	private Date dateStart;

	public ESVTax() {
		super();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

}
