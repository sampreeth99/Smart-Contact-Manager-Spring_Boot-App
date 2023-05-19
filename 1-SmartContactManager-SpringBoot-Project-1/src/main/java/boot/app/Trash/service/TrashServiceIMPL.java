package boot.app.Trash.service;

import java.lang.StackWalker.Option;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.app.entity.ContactDetails;
import boot.app.repository.IContactDetailsRepository;

@Service
public class TrashServiceIMPL implements ITrashService {
	
	@Autowired
	private IContactDetailsRepository conRepo;

	@Override
	public List<ContactDetails> checkTrash() {
		return conRepo.getAllTrashedContacts();
	}

	
	@Override
	public String deletePermanently(List<Integer> id) {
		List<ContactDetails> op=conRepo.findAllById(id);
		Iterator<ContactDetails> s=op.iterator();
		s.forEachRemaining(t -> {
			conRepo.deleteAllById(id);
		});;
		return "del";
		

	}
}
