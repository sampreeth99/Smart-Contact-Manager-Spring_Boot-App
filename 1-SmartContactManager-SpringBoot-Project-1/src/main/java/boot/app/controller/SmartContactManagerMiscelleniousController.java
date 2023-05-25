package boot.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import boot.app.Trash.service.ITrashService;
import boot.app.entity.ContactDetails;
import boot.app.repository.IContactDetailsRepository;


@Controller
@RequestMapping("/contactManager")
public class SmartContactManagerMiscelleniousController{
	
	@Autowired
	private ITrashService trashService;
	
	@GetMapping("/")
	public String getIndex(Map<String, Object> map) {
		map.put("msg", "Hello Im from index Included into welcome page dynamically");
		return "welcome";
	}
	
	@GetMapping("/trash/History")
	public String CheckTrashHistory(Map<String, Object> map) {
		List<ContactDetails> cd=trashService.checkTrash();
		map.put("TrashContacts", cd);
		
		System.out.println(cd);
		return "TrashHistory";
	}
	
	@GetMapping("/delete/permanently")
	public String deletePermanentlyById(@RequestParam List<Integer> cid) {
		String m=trashService.deletePermanently(cid);
		System.out.println(m);
		return "TrashHistory";
		
	}
	
	
	
}
