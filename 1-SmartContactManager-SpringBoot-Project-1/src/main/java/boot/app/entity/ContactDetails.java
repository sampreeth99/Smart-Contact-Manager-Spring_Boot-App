package boot.app.entity;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CONTACT_DETAILS")
@Setter
@Getter
@ToString
@SQLDelete(sql = "update CONTACT_DETAILS set STATUS='Disabled' where C_ID=?")
//@Where(clause ="STATUS <> 'Disabled'")

public class ContactDetails implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cId;

	@Column(length = 20)
	@NotEmpty
	private String cName;
	
	@Column(length = 20)
	private String cNickName;
	
	//@NotNull
	private Long cNo;
	
	@Column(length = 20)
	private String dest;
	
	@Column(length = 80)
	private String about;
	
	@Column(length = 20)
	private String status="Enabled";
	
	@Column(length = 100)
	private String profilePicPath;
	
	
	@Column(length =100)
	private String originalPicName;
	

}
