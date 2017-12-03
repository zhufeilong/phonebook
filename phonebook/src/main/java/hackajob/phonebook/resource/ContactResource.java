package hackajob.phonebook.resource;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import hackajob.phonebook.domain.Contact;

public class ContactResource extends ResourceSupport{
	private final Long id;
	private final String name;
	private final String phoneNumber;
	private final String address;
	public ContactResource(Contact contact) {
		id = contact.getId();
		name = contact.getName();
		phoneNumber = contact.getPhoneNumber();
		address = contact.getAddress();
	}
	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	
	
	
}
