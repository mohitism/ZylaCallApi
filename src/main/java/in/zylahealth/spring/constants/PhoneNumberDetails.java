package in.zylahealth.spring.constants;

public class PhoneNumberDetails {
	
	private String name;
	private String country;
	private String phonenumber;
	
	
	public PhoneNumberDetails() {
		
	}

	public PhoneNumberDetails(String name, String country, String phonenumber) {
		super();
		this.name = name;
		this.country = country;
		this.phonenumber = phonenumber;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
