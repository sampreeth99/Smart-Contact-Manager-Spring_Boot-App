package boot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import boot.app.entity.ContactDetails;

public interface IContactDetailsRepository extends JpaRepository<ContactDetails, Integer> {
	
	@Query(value = "  from ContactDetails where status='Disabled'")
	public List<ContactDetails> getAllTrashedContacts();
	
	
	@Query(value = "  from ContactDetails where status='Enabled'")
	public List<ContactDetails> getAllActiveContacts();
	

}
