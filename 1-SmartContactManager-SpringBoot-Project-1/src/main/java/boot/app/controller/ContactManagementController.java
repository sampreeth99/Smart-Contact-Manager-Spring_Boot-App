package boot.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.attoparser.config.ParseConfiguration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boot.app.AddContact.service.IAddContactService;
import boot.app.DeleteContact.service.IDeleteContactService;
import boot.app.EditContact.service.IEditContactService;
import boot.app.ShowContact.service.IShowContactService;
import boot.app.contact.file.download.IContactFileDownloadService;
import boot.app.contact.fileupload.FileUploadAddContactService;
import boot.app.entity.ContactDetails;
import boot.app.model.ContactManagerModel;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/management")
public class ContactManagementController {
	
	@Autowired
	private IAddContactService addService;
	
	@Autowired
	private IShowContactService showService;
	
	@Autowired
	private IEditContactService editService;

	@Autowired
	private IDeleteContactService deleteService;

	@Autowired
	private FileUploadAddContactService fileUpService;
	
	@Autowired
	private IContactFileDownloadService downService;
	
	
	@GetMapping("/add")
	public String showAddFormPage(@ModelAttribute("cd")	 ContactManagerModel t) {
		
		return "addForm";
	}
	
	@PostMapping("/save")
	
	public String saveContact(@Validated @ModelAttribute("cd") ContactManagerModel cd,Map<String, Object> map,BindingResult re) {
		  System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
			
		  Boolean flag=fileUpService.uploadProfilePicToFileSystem(cd);
		  Boolean flag1=fileUpService.uploadProfilePicToServerFolder(cd);
		  
		  String resultMsg=null;
		  if (flag=true) {
			  if (flag1=true) {

					 
				  map.put("resultMsg", resultMsg);
				 
			}
		  }
		  
		  
		  
		  cd.setCName(null);
		  cd.setCNickName(null);
		  cd.setCNo(null);
		  cd.setDest(null);
		  cd.setAbout(null);
		  return "addForm";
	}
	
	@GetMapping("/showAllContacts")
	public String showContacts(Map<String, Object> map) {
		List<ContactDetails> list=showService.showAllCon();
		map.put("allContactList", list);
		List<String> all=downService.allOnameOfPic();
		map.put("all", all);
		return "ShowPartialContacts";
		
	}
	
	
	
	Integer ifo=null;
	@GetMapping("/moreContactInfo")
	public String moreInfo(@RequestParam List<Integer> cid,Map<String, Object> map) {
		List<ContactDetails> cd=showService.showParticularDetails(cid);
		
		map.put("contact", cd);
		
		
		
		cid.forEach(t -> {
			ifo=t;
		});
		
		String name=downService.oNameOfPic(ifo);
		
		map.put("pic", name);
		System.out.println("oName from controller"+name);
		
		
		System.out.println("more contact info"+cd);
		System.out.println(cd);
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
	public String saveEditedForm(@ModelAttribute("cm") ContactDetails con,Map<String, Object> map , RedirectAttributes r) {
				String editmsg=editService.editContactById(con);
				map.put("editMsg", editmsg);
				r.addFlashAttribute("editMsg",editmsg );
		return "redirect:/";	
	}
	
	@GetMapping("/delete")
	public String deleteContactById(@RequestParam Integer no1, Map<String, Object> map) {
		String msg=deleteService.removeById(no1);
		map.put("delMsg", msg);
		map.put("note", "Contact is Deleted Temporarily ... Deleted Contacts will be saved for Further references & Will Be Available In 'Trash' Section ");
		return "del";
	}
	
	@GetMapping("/download")
	public  String downloadImage(@RequestParam Integer id , HttpServletResponse res,HttpServletRequest req) throws IOException{
	
		String  pathName=downService.getPaths(id);
		 System.out.println(pathName);
		 
		 File f=new File(pathName);
		 
		 ServletContext sc=req.getServletContext();
		 try {
			FileInputStream fi=new FileInputStream(f);
			long len=f.length();			
			ServletOutputStream s=res.getOutputStream();
			res.setContentLengthLong(len);
			
			String mime=sc.getMimeType("f");
			mime=mime==null?"application/octet-stream":mime;
			res.setContentType(mime);
			System.out.println(mime);
			
			res.setHeader("Content-Disposition", "attachment;fileName="+f.getName());
			
			IOUtils.copy(fi, s);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		 
		 return null;
		 		 
	}
}
