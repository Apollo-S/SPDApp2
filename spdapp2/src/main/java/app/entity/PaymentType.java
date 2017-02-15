package app.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_type")
public class PaymentType extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
