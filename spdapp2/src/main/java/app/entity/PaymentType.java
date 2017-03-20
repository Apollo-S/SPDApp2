package app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_type")
public class PaymentType extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "title")
	private String title;

	@Column(name = "is_percent", nullable = false)
	private boolean isPercent = false;
	
	public PaymentType() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsPercent() {
		return isPercent;
	}

	public void setIsPercent(boolean isPercent) {
		this.isPercent = isPercent;
	}

}
