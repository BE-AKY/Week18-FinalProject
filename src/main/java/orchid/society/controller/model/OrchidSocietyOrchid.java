package orchid.society.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import orchid.society.entity.Orchid;
import orchid.society.entity.Society;

@Data
@NoArgsConstructor
public class OrchidSocietyOrchid {

	private Long orchidId;

	private String orchidName;
	private String orchidColor;
	private String orchidMarks;

	
	public OrchidSocietyOrchid(Orchid orchid) {
		orchidId = orchid.getOrchidId();
		orchidName = orchid.getOrchidName();
		orchidColor = orchid.getOrchidColor();
		orchidMarks = orchid.getOrchidMarks();
		
	}
}
