package orchid.society.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orchid.society.controller.model.OrchidSocietyMember;
import orchid.society.controller.model.OrchidSocietyData;
import orchid.society.controller.model.OrchidSocietyOrchid;
import orchid.society.dao.MemberDao;
import orchid.society.dao.OrchidDao;
import orchid.society.dao.OrchidSocietyDao;
import orchid.society.entity.Member;
import orchid.society.entity.Orchid;
import orchid.society.entity.Society;


@Service
public class OrchidSocietyService {

	@Autowired
	private OrchidSocietyDao orchidSocietyDao;
	
	@Autowired
	private OrchidDao orchidDao;
	
	@Autowired
	private MemberDao memberDao;
	
	
//CREATE SOCIETY
	@Transactional(readOnly = false)
	public OrchidSocietyData saveOrchidSociety(OrchidSocietyData orchidSocietyData) {
		
		Society society = findOrCreateOrchidSociety(orchidSocietyData.getOrchidSocietyId());
		
		copyFieldsInOrchidSociety(society, orchidSocietyData);
			
		Society leSociety = orchidSocietyDao.save(society);
		
		return new OrchidSocietyData(leSociety);
			
	}

		private void copyFieldsInOrchidSociety(Society society, OrchidSocietyData orchidSocietyData) {
			society.setOrchidSocietyId(orchidSocietyData.getOrchidSocietyId());
			society.setOrchidSocietyName(orchidSocietyData.getOrchidSocietyName());
			society.setOrchidSocietyPhone(orchidSocietyData.getOrchidSocietyPhone());
			society.setOrchidSocietyState(orchidSocietyData.getOrchidSocietyState());
			society.setOrchidSocietyCity(orchidSocietyData.getOrchidSocietyCity());
			society.setOrchidSocietyAddress(orchidSocietyData.getOrchidSocietyAddress());
			society.setOrchidSocietyZip(orchidSocietyData.getOrchidSocietyZip());
				
		}

//GET	
	private Society findOrCreateOrchidSociety(Long orchidSocietyId) {
		Society society;
				
			if(Objects.isNull(orchidSocietyId)) {
				society = new Society();		
			} else {	
				society = findOrchidSocietyById(orchidSocietyId);
			}
				
			return society;
			}

			
		private Society findOrchidSocietyById(Long orchidSocietyId) {
			return orchidSocietyDao.findById(orchidSocietyId).orElseThrow(() -> new NoSuchElementException("OrchidSociety with ID=" + orchidSocietyId + " does not exist."));
			}

		
		
	@Transactional(readOnly = true)
	public List<OrchidSocietyData> retrieveAllOrchidSocieties() {
		return orchidSocietyDao.findAll()
				.stream()
				.map(OrchidSocietyData::new)
				.toList();
	}
	
	@Transactional(readOnly = true)
	public OrchidSocietyData retrieveOrchidSocietyById(Long orchidSocietyId ) {
		Society society = findOrchidSocietyById(orchidSocietyId);
		return new OrchidSocietyData(society);
	}

	
	
//DELETE	
	@Transactional(readOnly = false)
	public void deleteOrchidSocietyById(Long orchidSocietyId) {
		Society society = orchidSocietyDao.findById(orchidSocietyId).orElseThrow(() -> new NoSuchElementException("Orchid Society with ID=" + orchidSocietyId + " does not exist."));
		orchidSocietyDao.delete(society);
	}

	
//CREATE ORCHID	
    @Transactional(readOnly = false)
    public OrchidSocietyOrchid saveOrchid(Long orchidSocietyId, OrchidSocietyOrchid orchidSocietyOrchid) {

        Society society = findOrchidSocietyById(orchidSocietyId);

        Long orchidId = orchidSocietyOrchid.getOrchidId();

        Orchid orchid = findOrCreateOrchid(orchidId, orchidSocietyId);

        
        copyOrchidFields(orchid, orchidSocietyOrchid);

        	orchid.setSociety(society);

        	society.getOrchids().add(orchid);

        	
        Orchid leOrchid = orchidDao.save(orchid);

        return new OrchidSocietyOrchid(leOrchid);

    }

    public Orchid findOrchidById(Long orchidSocietyId, Long orchidId) {
        Orchid orchid = orchidDao.findById(orchidId).orElse(null);

        if (orchid == null) {
            throw new NoSuchElementException("Orchid with ID=" + orchidId + " does not exist.");
        }

        if (!orchid.getSociety().getOrchidSocietyId().equals(orchidSocietyId)) {
            throw new IllegalArgumentException(
                    "Orchid with ID=" + orchidId + " does not exist in Orchid Society with ID=" + orchidSocietyId);
        }

        return orchid;
    }

    private Orchid findOrCreateOrchid(Long orchidId, Long orchidSocietyId) {

        if (Objects.isNull(orchidId)) {
            return new Orchid();
        }
        return findOrchidById(orchidSocietyId, orchidId);

    }

    private void copyOrchidFields(Orchid orchid, OrchidSocietyOrchid orchidSocietyOrchid) {
        orchid.setOrchidId(orchidSocietyOrchid.getOrchidId());
        orchid.setOrchidName(orchidSocietyOrchid.getOrchidName());
        orchid.setOrchidColor(orchidSocietyOrchid.getOrchidColor());
        orchid.setOrchidMarks(orchidSocietyOrchid.getOrchidMarks());
    }


    
    

//CREATE MEMBER	
    @Transactional(readOnly = false)
    public OrchidSocietyMember saveMember(Long orchidSocietyId, OrchidSocietyMember orchidSocietyMember)  {

        Society society = findOrchidSocietyById(orchidSocietyId);

        Long memberId = orchidSocietyMember.getMemberId();
        
        Member member = findOrCreateMember(memberId, orchidSocietyId);

        
        copyMemberFields(member, orchidSocietyMember);

        	member.getOrchidSocieties().add(society);

        	society.getMembers().add(member);

        	
        Member leMember = memberDao.save(member);

        return new OrchidSocietyMember(leMember);

    }

    public Member findMemberById(Long orchidSocietyId, Long memberId) {
        Member member = memberDao.findById(memberId).orElse(null);

        if (member == null) {
            throw new NoSuchElementException("Member with ID=" + memberId + " does not exist.");
        }

        boolean orchidSocietyExist = member.getOrchidSocieties().stream().anyMatch(orchidSociety -> orchidSociety.getOrchidSocietyId().equals(orchidSocietyId));
        
        if (!orchidSocietyExist) {
            throw new IllegalArgumentException(
                    "Orchid Society with ID=" + orchidSocietyId + " does not exist.");
        }

        return member;
    }

    private Member findOrCreateMember(Long memberId, Long orchidSocietyId) {

        if (Objects.isNull(memberId)) {
            return new Member();
        }
        return findMemberById(orchidSocietyId, memberId);

    }

    private void copyMemberFields(Member member, OrchidSocietyMember orchidSocietyMember) {
        member.setMemberId(orchidSocietyMember.getMemberId());
        member.setMemberFirstName(orchidSocietyMember.getMemberFirstName());
        member.setMemberLastName(orchidSocietyMember.getMemberLastName());
        member.setMemberEmail(orchidSocietyMember.getMemberEmail());	
    }
	
	
}
