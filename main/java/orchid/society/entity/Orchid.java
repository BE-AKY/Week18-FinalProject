package orchid.society.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Orchid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orchidId;

	private String orchidName;
	private String orchidColor;
	private String orchidMarks;
	


	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orchid_society_id")
	private Society society;
	
}
