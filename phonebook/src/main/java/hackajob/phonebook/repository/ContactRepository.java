package hackajob.phonebook.repository;

import org.springframework.stereotype.Repository;

import hackajob.phonebook.domain.Contact;
@Repository
public class ContactRepository extends InMemoryRepository<Contact>{

	@Override
	protected void updateIfExists(Contact original, Contact desired) {
		original.setAddress(desired.getAddress());
		original.setName(desired.getName());
		original.setPhoneNumber(desired.getPhoneNumber());
	}

}
