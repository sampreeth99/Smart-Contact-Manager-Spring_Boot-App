package boot.app.ShowContact.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.app.entity.ContactDetails;
import boot.app.repository.IContactDetailsRepository;

@Service
public  class ShowContactServiceIMPL implements IShowContactService {
	
	@Autowired
	private IContactDetailsRepository conRepo;

	@Override
	public List<ContactDetails> showAllCon() {
		conRepo.findAll();
		return conRepo.findAll();
	}
	
	
	@Override
	public List<ContactDetails> showParticularDetails(Iterable<Integer> id ) {
		List<ContactDetails> op=conRepo.findAllById(id);
		return op;
		
	}
	
}
