package boot.app.contact.file.download;

import jakarta.validation.constraints.AssertFalse.List;

public interface IContactFileDownloadService {
	
	public String oNameOfPic(Integer id);
	
	public java.util.List<String> allOnameOfPic();
	
	public String getPaths(Integer id);
	

}
