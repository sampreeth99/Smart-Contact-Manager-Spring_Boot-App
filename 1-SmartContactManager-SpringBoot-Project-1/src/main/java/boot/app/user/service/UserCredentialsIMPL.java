package boot.app.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import boot.app.entity.UserCredentials10;
import boot.app.user.repo.UserCredentialsRepository;

@Service
public class UserCredentialsIMPL implements UserCredentialService {
	
	@Autowired
	private UserCredentialsRepository userRepo;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String saveUserCredentials(UserCredentials10 uc) {
		UserCredentials10 c=userRepo.save(uc);
		
		String fMsg="User Registration Failed";
		
		
		if (c==null) 
			return fMsg;
	
		
		return "User Registered Succesfully";
			
		
		
	}

}
