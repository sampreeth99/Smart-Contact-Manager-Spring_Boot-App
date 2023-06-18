package boot.app.contact.fileupload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import boot.app.entity.ContactDetails;
import boot.app.model.ContactManagerModel;
import boot.app.repository.IContactDetailsRepository;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;

@Service
public class FileUploadIMPLService implements FileUploadAddContactService {
	
	@Autowired
	private IContactDetailsRepository repo;
	
	
	/*
	@Value(value = "${file.dest.path}")
	private   String FiledestPath;

	
	@Override
	public Boolean uploadProfilePicToFileSystem(ContactManagerModel contactManagerModel) {
		
		String oName=contactManagerModel.getProfilePicMultiPart().getOriginalFilename();
		String cName=null;
		Path m=Paths.get(FiledestPath+File.separator+oName);
	   System.out.println(	m.toString());
		
	   boolean uploaded=false;
		try {
			
			Files.copy(contactManagerModel.getProfilePicMultiPart().getInputStream(),m,StandardCopyOption.REPLACE_EXISTING);
		    
			//converting to entity object
			ContactDetails c=new ContactDetails();
			c.setCName(contactManagerModel.getCName());
			c.setCNickName(contactManagerModel.getCNickName());
			c.setCNo(contactManagerModel.getCNo());
			c.setDest(contactManagerModel.getDest());
			c.setAbout(contactManagerModel.getAbout());
			c.setProfilePicPath(m.toString());
			c.setOriginalPicName(oName);
			
			
			System.out.println(c);
			
			repo.save(c);
			
			uploaded=true;
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		return uploaded;	
	}
	
	*/
	
	@Override
	public Boolean uploadProfilePicToServerFolder(MultipartFile profile, ContactDetails contactDetails) {
		String oName=profile.getOriginalFilename();
		String cName=null;
		//Path m=Paths.get(ServerdestPath+File.separator+oName);
	 
		
	   boolean uploaded=false;
		try {
			String destf="C:\\Smart-Contact-Manager-Spring_Boot-App\\1-SmartContactManager-SpringBoot-Project-1\\src\\main\\webapp\\images";
			Files.copy(profile.getInputStream(),Paths.get(destf+File.separator+oName),StandardCopyOption.REPLACE_EXISTING);
			uploaded=true;
			contactDetails.setProfilePicPath(Paths.get(destf+File.separator+oName).toString());
			contactDetails.setOriginalPicName(oName);
			repo.save(contactDetails);
			System.out.println("from upload service::"+contactDetails);
			uploaded=true;
			} catch (IOException e) {
			e.printStackTrace();
		}
			return uploaded;
	}
	
	
	@Override
	
	public void deleteOldProfilePic(Integer id) {
//		 repo.deleteOldPathById(id);
	
	}
	
	
	
}
