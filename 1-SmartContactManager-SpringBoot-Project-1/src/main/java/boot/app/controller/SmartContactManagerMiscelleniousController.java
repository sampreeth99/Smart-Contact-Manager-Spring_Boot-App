package boot.app.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import boot.app.entity.ContactDetails;
import boot.app.repository.IContactDetailsRepository;

@Controller
@RequestMapping("/contactManager")
public class SmartContactManagerMiscelleniousController {

	@GetMapping("/common")
	public String getCommon() {
		return "common_welcome";
	}

	
	@GetMapping("/")
	public String getIndex(Map<String, Object> map,Principal principal) {
		String loggedUser=principal.getName();
		map.put("loggedUserName", loggedUser);
		
		return "welcome";
	}

	@GetMapping("/update/profile")
	public String showChangeProfilePicForm(@RequestParam Integer uploadId, Map<String, Object> map) {
		map.put("uploadid", uploadId);
		return "changeProfile";
	}

}
