package boot.app.search.service;

import java.util.List;

import boot.app.entity.ContactDetails;

public interface ContactManagerSearchService {
	
	public List<ContactDetails> searchContactByName(String name);

}
