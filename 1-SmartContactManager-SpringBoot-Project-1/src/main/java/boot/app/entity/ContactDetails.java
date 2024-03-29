package boot.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CONTACT_DETAILS")
@Setter
@Getter
@ToString
//@SQLDelete(sql = "update CONTACT_DETAILS set STATUS='Disabled' where C_ID=?")
//@Where(clause ="STATUS <> 'Disabled'")

public class ContactDetails implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cId;

	@Column(length = 20)
	//@NotEmpty
	private String cName;
	
	@Column(length = 20)
	private String cNickName;
	
	//@NotNull
	private Long cNo;
	
	@Column(length = 20)
	private String dest;
	
	@Column(length = 80)
	private String about;
	
	@Column(length = 200)
	private String profilePicPath;
	
	
	@Column(length =100)
	private String originalPicName;
	
	
}
