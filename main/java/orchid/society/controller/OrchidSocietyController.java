package orchid.society.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import orchid.society.controller.model.OrchidSocietyMember;
import orchid.society.controller.model.OrchidSocietyData;
import orchid.society.controller.model.OrchidSocietyOrchid;
import orchid.society.service.OrchidSocietyService;



@RestController
@RequestMapping("/orchid_society")
@Slf4j
public class OrchidSocietyController {

	@Autowired //becomes a managed bean
	private OrchidSocietyService orchidSocietyService;
	
	
//CREATE	
	@PostMapping("/orchid_society")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrchidSocietyData insertOrchidSociety (@RequestBody OrchidSocietyData orchidSocietyData) {
		log.info("Creating orchid society {}", orchidSocietyData);
		return orchidSocietyService.saveOrchidSociety(orchidSocietyData);
	
	}

	
//UPDATE
	@PutMapping("/orchid_society/{orchidSocietyId}")
	public OrchidSocietyData updateOrchidSocietyData(@PathVariable Long orchidSocietyId, @RequestBody OrchidSocietyData orchidSocietyData) {
		orchidSocietyData.setOrchidSocietyId(orchidSocietyId);
		log.info("Updating orchid society ()", orchidSocietyId);
		return orchidSocietyService.saveOrchidSociety(orchidSocietyData);

	}
	
		
	
//GET	
	@GetMapping("/orchid_society")
	public List<OrchidSocietyData> retrieveAllOrchidSocieties() {
		log.info("Retrieve all orchid societies.");
		return orchidSocietyService.retrieveAllOrchidSocieties();
	}
	
	@GetMapping("/orchid_society/{orchidSocietyId}")
	public OrchidSocietyData retrieveOrchidSocietyById(@PathVariable Long orchidSocietyId) {
		log.info("Retrieving orchid society with ID={}", orchidSocietyId);
		return orchidSocietyService.retrieveOrchidSocietyById(orchidSocietyId);
	}


//DELETE
	@DeleteMapping("/orchid_society")
	public void deleteAllOrchidSocieties() {
		log.info("Attempting to delete all orchid societies.");
		throw new UnsupportedOperationException("Deleting all orchid societies is not allowed.");
	}
	
	//Delete by Id
	@DeleteMapping("/orchid_society/{orchidSocietyId}")
	public Map<String, String> deleteOrchidSocietyById(@PathVariable Long orchidSocietyId) {
		log.info("Deleting member with ID={}", orchidSocietyId);
		
		orchidSocietyService.deleteOrchidSocietyById(orchidSocietyId);
		
		return Map.of("message", "Deletion of Orchid Society with ID=" + orchidSocietyId + " was successful.");
	}


	
//CREATE ORCHIDS
	@PostMapping("/orchid_society/{orchidSocietyId}/orchid")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrchidSocietyOrchid insertOrchid(@PathVariable Long orchidSocietyId, 
			@RequestBody OrchidSocietyOrchid orchidSocietyOrchid) {
		
	log.info("Creating orchid with ID={}", orchidSocietyOrchid);
	
	return orchidSocietyService.saveOrchid(orchidSocietyId, orchidSocietyOrchid);
	
	}
	

//CREATE MEMBER
	@PostMapping("/orchid_society/{orchidSocietyId}/member")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrchidSocietyMember insertMember(@PathVariable Long orchidSocietyId, 
			@RequestBody OrchidSocietyMember orchidSocietyMember) {
		
	log.info("Creating member with ID={}", orchidSocietyMember);
		
	return orchidSocietyService.saveMember(orchidSocietyId, orchidSocietyMember);
		
	}	
	
}