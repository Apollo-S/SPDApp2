package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "value_type")
public class ValueType extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "title")
	private String title;

	public ValueType() {
	}

	public ValueType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
