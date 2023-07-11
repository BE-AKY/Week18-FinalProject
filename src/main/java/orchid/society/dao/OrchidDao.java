package orchid.society.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import orchid.society.entity.Orchid;

public interface OrchidDao extends JpaRepository<Orchid, Long> {



}
