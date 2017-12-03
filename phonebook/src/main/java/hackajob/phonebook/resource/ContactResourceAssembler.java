package hackajob.phonebook.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import hackajob.phonebook.domain.Contact;
@Component
public class ContactResourceAssembler extends ResourceAssembler<Contact, ContactResource>{

	@Autowired
	protected EntityLinks entityLinks;
	
	private static final String UPDATE_REL = "update";
	private static final String DELETE_REL = "delete";

	@Override
	public ContactResource toResource(Contact domainObject) {
		ContactResource resource = new ContactResource(domainObject);
		final Link selfLink = entityLinks.linkToSingleResource(domainObject);
		
		resource.add(selfLink.withSelfRel());
		resource.add(selfLink.withRel(UPDATE_REL));
		resource.add(selfLink.withRel(DELETE_REL));
		
		return resource;
	}

}
