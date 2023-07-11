package orchid.society.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Society {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orchidSocietyId;
	
	private String orchidSocietyName;
	private String orchidSocietyAddress;
	private String orchidSocietyCity;
	private String orchidSocietyState;
	private String orchidSocietyZip;
	private String orchidSocietyPhone;

	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "orchid_society_member", 
		joinColumns = @JoinColumn(name = "orchid_society_id"), 
		inverseJoinColumns = @JoinColumn(name = "member_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Member> members = new HashSet<>();

	

	@OneToMany(mappedBy = "society", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Orchid> orchids = new HashSet<>();
}
