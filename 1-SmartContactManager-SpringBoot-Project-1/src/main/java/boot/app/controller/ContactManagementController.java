package boot.app.controller;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import boot.app.AddContact.service.IAddContactService;
import boot.app.EditContact.service.IEditContactService;
import boot.app.ShowContact.service.IShowContactService;
import boot.app.entity.ContactDetails;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/management")
public class ContactManagementController {
	
	@Autowired
	private IAddContactService addService;
	
	@Autowired
	private IShowContactService showService;
	
	

	@Autowired
	private IEditContactService editService;
	
	
	
	
	
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
	
	@GetMapping("/showAllContacts")
	public String showContacts(Map<String, Object> map) {
		List<ContactDetails> list=showService.showAllCon();
		map.put("allContactList", list);
		return "ShowPartialContacts";
		
	}
	
	@GetMapping("/moreContactInfo")
	public String moreInfo(@RequestParam List<Integer> cid,Map<String, Object> map) {
		List<ContactDetails> cd=showService.showParticularDetails(cid);
		map.put("contact", cd);
		System.out.println(cid);
		return "MoreInfo";	
	}

	 
	@GetMapping("/edit")
	public String showEditFormPage(@ModelAttribute("cm") ContactDetails con, @RequestParam List<Integer> no) {
		
		List<ContactDetails> cd=showService.showParticularDetails(no);
		
		 cd.forEach(t -> {
			BeanUtils.copyProperties(t, con);
		 });
		
		
		System.out.println(cd);
		
		System.out.println("ContactManagementController.showEditFormPage()");
		
		return "editForm";
		
	}
	
	@PostMapping("/edit/submit")
	public String saveEditedForm(@ModelAttribute("cm") ContactDetails con,Map<String, Object> map) {
				String editmsg=editService.editContactById(con);
				map.put("editMsg", editmsg);
		return "editForm";
		
		
	}
	

}
