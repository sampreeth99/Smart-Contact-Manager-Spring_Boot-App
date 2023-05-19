package boot.app.Trash.service;

import java.util.List;

import boot.app.entity.ContactDetails;

public interface ITrashService {
	
	public List<ContactDetails> checkTrash() ;
	
	public String deletePermanently(List<Integer> id);

}
