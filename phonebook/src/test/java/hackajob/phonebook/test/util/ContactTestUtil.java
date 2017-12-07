package hackajob.phonebook.test.util;

import org.junit.Assert;


import hackajob.phonebook.domain.Contact;

public class ContactTestUtil {
	public static Contact generateContact() {
		Contact contact =  new Contact();
		contact.setName("Test name");
		contact.setAddress("Test address");
		contact.setPhoneNumber("Test phoneNumber");
		return contact;
	}
	
	public static void assertAllButIdsMatchBetweenContacts(Contact expected, Contact actual) {
    	Assert.assertEquals(expected.getAddress(), actual.getAddress());
    	Assert.assertEquals(expected.getName(), actual.getName());
    	Assert.assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
    }
	
	public static Contact generateUpdatedContact(Contact original) {
		Contact updated = new Contact();
		updated.setAddress(original.getAddress()+ " updated");
		updated.setName(original.getName()+ " updated");
		updated.setPhoneNumber(original.getPhoneNumber()+ " updated");
    	return updated;
    }
}
