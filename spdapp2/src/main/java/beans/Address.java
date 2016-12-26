package beans;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	int id;
	String country;
	String region;
	String city;
	String street;
	String building;
	String flat;
	String zip;

	public Address() {
	}

	public Address(String country, String region, String city, String street, String building, String flat,
			String zip) {
		super();
		this.country = country;
		this.region = region;
		this.city = city;
		this.street = street;
		this.building = building;
		this.flat = flat;
		this.zip = zip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

}
