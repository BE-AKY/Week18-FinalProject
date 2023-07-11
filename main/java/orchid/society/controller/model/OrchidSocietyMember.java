package orchid.society.controller.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import orchid.society.entity.Member;

//THIS IS THE DTO (DATE TRANSFER OBJECT) LAYER!!!	

@Data
@NoArgsConstructor
public class OrchidSocietyMember {

	private Long memberId;
	
	private String memberFirstName;
	private String memberLastName;
	
	@Column(unique = true)
	private String memberEmail;
		

	public OrchidSocietyMember(Member member) {
		memberId = member.getMemberId();
		memberFirstName = member.getMemberFirstName();
		memberLastName = member.getMemberLastName();
		memberEmail = member.getMemberEmail();
		

	}
}

