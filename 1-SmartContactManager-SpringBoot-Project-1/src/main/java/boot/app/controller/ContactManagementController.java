package boot.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import boot.app.AddContact.service.IAddContactService;
import boot.app.entity.ContactDetails;
import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/management")
public class ContactManagementController {
	
	@Autowired
	private IAddContactService addService;
	
	@GetMapping("/add")
	public String showAddFormPage() {
		return "addForm";
	}
	
	
	@PostMapping("/save")
	public String saveContact(@ModelAttribute ContactDetails contactDetails,Map<String, Object> map) {
		String resultMsg=addService.saveContact(contactDetails);
		map.put("addResponse", resultMsg);
		return "addForm";
		
		
	}

}
