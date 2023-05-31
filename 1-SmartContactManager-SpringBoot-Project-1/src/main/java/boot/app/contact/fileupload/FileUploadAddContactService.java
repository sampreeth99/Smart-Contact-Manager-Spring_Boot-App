package boot.app.contact.fileupload;

import org.springframework.web.multipart.MultipartFile;

import boot.app.model.ContactManagerModel;

public interface FileUploadAddContactService {
	
	public Boolean uploadProfilePicToFileSystem(ContactManagerModel contactManagerModel);
	
	public Boolean uploadProfilePicToServerFolder(ContactManagerModel contactManagerModel);
	
	
	

	

}
