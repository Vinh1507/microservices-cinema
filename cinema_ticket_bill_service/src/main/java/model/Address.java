package model;


public class Address {
	private int id;
	private String city;
	private String street;
	private String province;
	
	// Constructor
    public Address(int id, String city, String street, String province) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.province = province;
    }
    
    public Address(String city) {
        this.city = city;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getProvince() {
        return province;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
