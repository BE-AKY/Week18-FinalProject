package orchid.society.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import orchid.society.entity.Member;
import orchid.society.entity.Orchid;
import orchid.society.entity.Society;

@Data
@NoArgsConstructor
public class OrchidSocietyData {

	private Long orchidSocietyId;
	
	private String orchidSocietyName;
	private String orchidSocietyAddress;
	private String orchidSocietyCity;
	private String orchidSocietyState;
	private String orchidSocietyZip;
	private String orchidSocietyPhone;
		
	
	private Set<OrchidSocietyMember> members = new HashSet<>();

	
	private Set<OrchidSocietyOrchid> orchids = new HashSet<>();
	
	
	
	
	public OrchidSocietyData(Society society) {
		orchidSocietyId = society.getOrchidSocietyId();
		orchidSocietyName = society.getOrchidSocietyName();
		orchidSocietyAddress = society.getOrchidSocietyAddress();
		orchidSocietyCity = society.getOrchidSocietyCity();
		orchidSocietyState = society.getOrchidSocietyState();
		orchidSocietyZip = society.getOrchidSocietyZip();
		orchidSocietyPhone = society.getOrchidSocietyPhone();
		
		for(Member member : society.getMembers()) {
			members.add(new OrchidSocietyMember(member));		
		}
		
		
		for(Orchid orchid : society.getOrchids()) {
			orchids.add(new OrchidSocietyOrchid(orchid));
			
		}
	}

}
