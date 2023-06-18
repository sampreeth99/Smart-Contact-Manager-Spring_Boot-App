package boot.app.ShowContact.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import boot.app.entity.ContactDetails;

public interface IShowContactService {
	
	
	public List<ContactDetails> showAllCon();
	
	public Object[] showParticularDetails(Integer id);
	
	public ContactDetails getAllContactDetailsById(Integer id) ;
	
	public Page<ContactDetails> showAllContacts(Pageable pageable);

}
