package hackajob.phonebook.model;

import java.util.Map;

public class Contact {
	private String name;
	private String phoneNumber;
	private String address;

	public Contact(Map<String, Object> term) {
		this.name=(String) term.get("name");
		this.phoneNumber=(String) term.get("phone_number");
		this.address=(String) term.get("address");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAttr(String searchTerm) {
		String result=null;
		switch(searchTerm) {
			case "name":  result=this.name;
			break;
			case "address":  result=this.address;
			break;
			case "phoneNumber":  result=this.phoneNumber;
			break;
		}
		return result;
			
		
	}
	
	
}
