package boot.app.AddContact.service;

import boot.app.entity.ContactDetails;
import boot.app.model.ContactManagerModel;

public interface IAddContactService {
	
	public String saveContact(ContactDetails contactDetails);

}
