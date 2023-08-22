package boot.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import boot.app.entity.UserCredentials10;
import boot.app.user.service.UserCredentialService;

@Controller
@RequestMapping("user")
public class UserRegistrationController {
	
	@Autowired
	private UserCredentialService userService;

	@GetMapping("/register")
	public String userRegisterForm(@ModelAttribute("us")UserCredentials10 uc) {
		return "user_register";
	}
	
	@PostMapping("doRegistration")
	public String submitRegisterForm(@ModelAttribute("us") UserCredentials10 uc ,Map<String, Object> map){
		System.out.println(uc);
		
		
		String m=userService.saveUserCredentials(uc);
		System.out.println("from service"+m);
		map.put("regSuccesMsg", m);
		return "success";
	}
	
}
