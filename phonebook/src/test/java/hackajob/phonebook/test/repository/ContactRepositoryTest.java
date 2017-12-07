package hackajob.phonebook.test.repository;


import static hackajob.phonebook.test.util.ContactTestUtil.assertAllButIdsMatchBetweenContacts;
import static hackajob.phonebook.test.util.ContactTestUtil.generateContact;
import static hackajob.phonebook.test.util.ContactTestUtil.generateUpdatedContact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hackajob.phonebook.domain.Contact;
import hackajob.phonebook.repository.ContactRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {
	
	private static final long NONEXISTENT_ID = 1000;

	@Autowired
	private ContactRepository repository;

	@Before
	public void setUp() throws Exception {
		repository.clear();
	}

	@Test
	public void testFindNonexistentContactEnsureOptionalIsNotPresent() {
		assertNoExistingContacts();
		Optional<Contact> contact = repository.findById(NONEXISTENT_ID);
		Assert.assertFalse(contact.isPresent());
	}

	private void assertNoExistingContacts() {
		assertExistingContactCountIs(0);
	}

	private void assertExistingContactCountIs(int count) {
		Assert.assertEquals(count, repository.getCount());
	}
	
	@Test
	public void testFindExistingContactEnsureOptionalIsPresent() {
		Contact contact = injectContact();
		Optional<Contact> option = repository.findById(contact.getId());
		Assert.assertEquals(true, option.isPresent());
	}

	private Contact injectContact() {
		return repository.create(generateContact());
	}
	@Test
	public void testFindExistingContactEnsureCorrectContactValues() throws Exception {
		Contact injectedContact = injectContact();
		Optional<Contact> foundContact = repository.findById(injectedContact.getId());
		assertContactsMatch(injectedContact, foundContact.get());
	}
	
	private static void assertContactsMatch(Contact expected, Contact actual) {
		Assert.assertEquals(expected.getId(), actual.getId());
		assertAllButIdsMatchBetweenContacts(expected, actual);
		
	}
	
	@Test
	public void testFindAllWithNoExistingContactsEnsureNoContactsFound() throws Exception {
		assertFindAllIsCorrectWithContactCount(0);
	}
	
	private void assertFindAllIsCorrectWithContactCount(int count) {
		injectGivenNumberOfContacts(count);
		assertExistingContactCountIs(count);
		List<Contact> contactsFound = repository.findAll();
		Assert.assertEquals(count, contactsFound.size());
	}
	
	private List<Contact> injectGivenNumberOfContacts(int count) {
		
		List<Contact> injectedContacts = new ArrayList<>();
		
		for (int i = 0; i < count; i++) {
			injectedContacts.add(injectContact());
		}
		
		return injectedContacts;
	}
	
	@Test
	public void testFindAllWithOneExistingContactsEnsureOneContactsFound() throws Exception {
		assertFindAllIsCorrectWithContactCount(1);
	}
	
	@Test
	public void testFindAllWithTwoExistingContactsEnsureTwoContactsFound() throws Exception {
		assertFindAllIsCorrectWithContactCount(2);
	}
	
	@Test
	public void testFindAllWithTwoExistingContactEnsureFirstContactIsCorrect() throws Exception {
		List<Contact> injectedContacts = injectGivenNumberOfContacts(2);
		List<Contact> contactsFound = repository.findAll();
		assertContactsMatch(injectedContacts.get(0), contactsFound.get(0));
	}

	@Test
	public void testFindAllWithTwoExistingContactEnsureSecondContactIsCorrect() throws Exception {
		List<Contact> injectedContacts = injectGivenNumberOfContacts(2);
		List<Contact> contactsFound = repository.findAll();
		assertContactsMatch(injectedContacts.get(1), contactsFound.get(1));
	}
	
	@Test
	public void testDeleteNonexistentContactEnsureNoContactDeleted() throws Exception {
		assertNoExistingContacts();
		boolean wasDeleted = repository.delete(NONEXISTENT_ID);
		Assert.assertFalse(wasDeleted);
	}

	@Test
	public void testDeleteExistingContactEnsureContactDeleted() throws Exception {
		Contact injectedContact = injectContact();
		assertExistingContactCountIs(1);
		boolean wasDeleted = repository.delete(injectedContact.getId());
		Assert.assertTrue(wasDeleted);
		assertNoExistingContacts();
	}
	
	@Test
	public void testUpdateNonexistentContactEnsureNoUpdateMade() throws Exception {
		assertNoExistingContacts();
		boolean wasUpdated = repository.update(NONEXISTENT_ID, new Contact());
		Assert.assertFalse(wasUpdated);
	}
	
	@Test
	public void testUpdateExistingContactEnsureUpdateMade() throws Exception {
		Contact injectedContact = injectContact();
		boolean wasUpdated = repository.update(injectedContact.getId(), new Contact());
		Assert.assertTrue(wasUpdated);
	}
	
	@Test
	public void testUpdateExistingContactEnsureOriginalContactIsUpdated() throws Exception {
		Contact originalContact = injectContact();
		Contact updatedContact = generateUpdatedContact(originalContact);
		repository.update(originalContact.getId(), updatedContact);
		assertAllButIdsMatchBetweenContacts(updatedContact, originalContact);
	}
	
	@Test
	public void testUpdateExistingContactWithNullUpdatedContactEnsureNoUpdateMade() throws Exception {
		Contact injectedContact = injectContact();
		boolean wasUpdated = repository.update(injectedContact.getId(), null);
		Assert.assertFalse(wasUpdated);
	}
}
