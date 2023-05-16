package boot.app.ShowContact.service;

import java.util.List;

import boot.app.entity.ContactDetails;

public interface IShowContactService {
	
	
	public List<ContactDetails> showAllCon();
	
	public List<ContactDetails> showParticularDetails(Iterable<Integer> id);

}
