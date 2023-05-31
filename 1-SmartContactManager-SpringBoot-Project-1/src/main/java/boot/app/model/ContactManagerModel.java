package boot.app.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ContactManagerModel {
	
	

	private String cName;
	
	private String cNickName;
	
	private Long cNo;
	
	private String dest;
	
	private String about;
	
	private String status="Enabled";
	
	private MultipartFile profilePicMultiPart;
	
	

}
