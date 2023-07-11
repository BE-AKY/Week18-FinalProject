package orchid.society.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import orchid.society.entity.Society;


public interface OrchidSocietyDao extends JpaRepository<Society, Long> {

}
