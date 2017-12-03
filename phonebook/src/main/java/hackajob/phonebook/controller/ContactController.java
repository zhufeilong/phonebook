package hackajob.phonebook.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import hackajob.phonebook.domain.Contact;
import hackajob.phonebook.repository.ContactRepository;
import hackajob.phonebook.resource.ContactResource;
import hackajob.phonebook.resource.ContactResourceAssembler;


@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Contact.class)
@RequestMapping(value = "/contact", produces = "application/json")
public class ContactController {
	@Autowired
	private ContactRepository repository;
	
	@Autowired
	private ContactResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<ContactResource>> findAllContacts() {
		List<Contact> Contacts = repository.findAll();
		return new ResponseEntity<>(assembler.toResourceCollection(Contacts), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ContactResource> createContact(@RequestBody Contact Contact) {
		Contact createdContact = repository.create(Contact);
		return new ResponseEntity<>(assembler.toResource(createdContact), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ContactResource> findContactById(@PathVariable Long id) {
		Optional<Contact> Contact = repository.findById(id);

		if (Contact.isPresent()) {
			return new ResponseEntity<>(assembler.toResource(Contact.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
		boolean wasDeleted = repository.delete(id);
		HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(responseStatus);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ContactResource> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
		boolean wasUpdated = repository.update(id, updatedContact);
		
		if (wasUpdated) {
			return findContactById(id);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    

}
